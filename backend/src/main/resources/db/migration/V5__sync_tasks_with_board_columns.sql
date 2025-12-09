-- ============================
-- Remove OLD trigger + function
-- ============================

DROP TRIGGER IF EXISTS trg_tasks_to_board_columns ON tasks;
DROP FUNCTION IF EXISTS add_task_to_board_columns();


-- ============================
-- New function: sync tasks to board_columns
-- ============================

CREATE OR REPLACE FUNCTION sync_task_to_board_columns()
RETURNS trigger AS $$
DECLARE
    next_pos INTEGER;
BEGIN
    ----------------------------------------------------------------------
    -- INSERT: new task → add to board_columns
    ----------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN

        SELECT COALESCE(MAX(bc.position), 0) + 1
        INTO next_pos
        FROM board_columns bc
        JOIN tasks t ON bc.task_id = t.id
        WHERE bc.project_id = NEW.project_id
          AND t.status = NEW.status;

        INSERT INTO board_columns (project_id, position, task_id)
        VALUES (NEW.project_id, next_pos, NEW.id);

        RETURN NEW;
    END IF;

    ----------------------------------------------------------------------
    -- UPDATE: status changed → move task between columns
    ----------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN

        IF NEW.status IS DISTINCT FROM OLD.status THEN

            -- Remove old board entry
            DELETE FROM board_columns
            WHERE task_id = NEW.id;

            -- Calculate position in new column
            SELECT COALESCE(MAX(bc.position), 0) + 1
            INTO next_pos
            FROM board_columns bc
            JOIN tasks t ON bc.task_id = t.id
            WHERE bc.project_id = NEW.project_id
              AND t.status = NEW.status;

            INSERT INTO board_columns (project_id, position, task_id)
            VALUES (NEW.project_id, next_pos, NEW.id);

        END IF;

        RETURN NEW;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- ============================
-- Create new trigger
-- ============================

DROP TRIGGER IF EXISTS trg_sync_tasks ON tasks;

CREATE TRIGGER trg_sync_tasks
AFTER INSERT OR UPDATE ON tasks
FOR EACH ROW
EXECUTE FUNCTION sync_task_to_board_columns();
