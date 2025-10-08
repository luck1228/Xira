 -- Remove the old name column
ALTER TABLE board_columns
DROP COLUMN IF EXISTS name;

-- Add the new task_id column
ALTER TABLE board_columns
ADD COLUMN task_id INTEGER UNIQUE;

-- Create the foreign key constraint (1-to-1 relationship with tasks table)
ALTER TABLE board_columns
ADD CONSTRAINT fk_board_columns_task
FOREIGN KEY (task_id)
REFERENCES tasks (id)
ON DELETE CASCADE;