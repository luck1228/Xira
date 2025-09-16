-- Add created_at column to projects table
ALTER TABLE projects
ADD COLUMN created_at TIMESTAMPTZ NOT NULL DEFAULT now();
