-- Function
CREATE OR REPLACE FUNCTION add_task_to_board_columns()
RETURNS trigger AS $$
BEGIN
  INSERT INTO board_columns (project_id, position, task_id)
  VALUES (NEW.project_id, 1, NEW.id);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger
DROP TRIGGER IF EXISTS trg_tasks_to_board_columns ON tasks;
CREATE TRIGGER trg_tasks_to_board_columns
AFTER INSERT ON tasks
FOR EACH ROW
EXECUTE FUNCTION add_task_to_board_columns();