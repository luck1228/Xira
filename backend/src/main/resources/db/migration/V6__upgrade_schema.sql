-- V6__upgrade_schema.sql

-- =========================
-- 1. ADD LOOKUP TABLES
-- =========================

CREATE TABLE IF NOT EXISTS user_roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS project_roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS task_priorities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

-- =========================
-- 2. USERS: replace role -> role_id
-- =========================

ALTER TABLE users ADD COLUMN IF NOT EXISTS role_id INTEGER;

-- (optional) migrate existing data if role column exists
-- assumes role names match
UPDATE users u
SET role_id = ur.id
FROM user_roles ur
WHERE u.role = ur.name;

ALTER TABLE users
ADD CONSTRAINT fk_users_role
FOREIGN KEY (role_id) REFERENCES user_roles(id);

-- optional: drop old column
ALTER TABLE users DROP COLUMN IF EXISTS role;

ALTER TABLE users ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;

-- =========================
-- 3. PROJECT_USERS: role -> role_id
-- =========================

ALTER TABLE project_users ADD COLUMN IF NOT EXISTS role_id INTEGER;

UPDATE project_users pu
SET role_id = pr.id
FROM project_roles pr
WHERE pu.role = pr.name;

ALTER TABLE project_users
ADD CONSTRAINT fk_project_users_role
FOREIGN KEY (role_id) REFERENCES project_roles(id);

ALTER TABLE project_users DROP COLUMN IF EXISTS role;

-- =========================
-- 4. ADD BOARDS TABLE
-- =========================

CREATE TABLE IF NOT EXISTS boards (
    id SERIAL PRIMARY KEY,
    project_id INTEGER NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);

-- =========================
-- 5. MIGRATE board_columns → boards
-- =========================

-- add board_id
ALTER TABLE board_columns ADD COLUMN IF NOT EXISTS board_id INTEGER;

-- create default board per project
INSERT INTO boards (project_id, name, created_at)
SELECT DISTINCT project_id, 'Default Board', NOW()
FROM board_columns
WHERE project_id IS NOT NULL;

-- assign board_id
UPDATE board_columns bc
SET board_id = b.id
FROM boards b
WHERE bc.project_id = b.project_id;

-- add FK
ALTER TABLE board_columns
ADD CONSTRAINT fk_board_columns_board
FOREIGN KEY (board_id) REFERENCES boards(id) ON DELETE CASCADE;

-- remove old project_id
ALTER TABLE board_columns DROP COLUMN IF EXISTS project_id;

ALTER TABLE board_columns ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;

-- =========================
-- 6. TASKS: add column_id + position + priority_id
-- =========================

ALTER TABLE tasks ADD COLUMN IF NOT EXISTS column_id INTEGER;
ALTER TABLE tasks ADD COLUMN IF NOT EXISTS position NUMERIC(20,10);
ALTER TABLE tasks ADD COLUMN IF NOT EXISTS priority_id INTEGER;

-- migrate priority if old column exists
UPDATE tasks t
SET priority_id = tp.id
FROM task_priorities tp
WHERE t.priority = tp.name;

ALTER TABLE tasks
ADD CONSTRAINT fk_tasks_priority
FOREIGN KEY (priority_id) REFERENCES task_priorities(id);

-- remove old status + priority
ALTER TABLE tasks DROP COLUMN IF EXISTS status;
ALTER TABLE tasks DROP COLUMN IF EXISTS priority;

-- FK to column
ALTER TABLE tasks
ADD CONSTRAINT fk_tasks_column
FOREIGN KEY (column_id) REFERENCES board_columns(id) ON DELETE SET NULL;

ALTER TABLE tasks ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;

-- =========================
-- 7. FIX COMMENTS
-- =========================

ALTER TABLE comments ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;

-- =========================
-- 8. LABELS
-- =========================

CREATE TABLE IF NOT EXISTS labels (
    id SERIAL PRIMARY KEY,
    project_id INTEGER NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    name VARCHAR(50) NOT NULL,
    color VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS task_labels (
    task_id INTEGER NOT NULL,
    label_id INTEGER NOT NULL,
    PRIMARY KEY (task_id, label_id),
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (label_id) REFERENCES labels(id) ON DELETE CASCADE
);

-- =========================
-- 9. TASK ACTIVITY
-- =========================

CREATE TABLE IF NOT EXISTS task_activity (
    id SERIAL PRIMARY KEY,
    task_id INTEGER NOT NULL REFERENCES tasks(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
    action VARCHAR(50) NOT NULL,
    old_value TEXT,
    new_value TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

-- =========================
-- 10. INDEXES
-- =========================

CREATE INDEX IF NOT EXISTS idx_tasks_project ON tasks(project_id);
CREATE INDEX IF NOT EXISTS idx_tasks_column ON tasks(column_id);
CREATE INDEX IF NOT EXISTS idx_tasks_position ON tasks(position);
CREATE INDEX IF NOT EXISTS idx_activity_task ON task_activity(task_id);

-- =========================
-- 11. SEED DEFAULT DATA
-- =========================

INSERT INTO user_roles (name)
VALUES ('USER'), ('ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO project_roles (name)
VALUES ('MEMBER'), ('OWNER')
ON CONFLICT DO NOTHING;

INSERT INTO task_priorities (name)
VALUES ('LOW'), ('MEDIUM'), ('HIGH')
ON CONFLICT DO NOTHING;