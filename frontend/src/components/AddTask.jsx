import React, { useState, useEffect } from 'react';
import './AddTask.css';

const AddTask = ({ isOpen, onClose, projectId, onCreated }) => {
  const [newTask, setNewTask] = useState({ name: '', description: '' });

  const API_BASE_URL = 'http://localhost:8080/api/tasks';

  useEffect(() => {
    if (!isOpen) return;
    const onKey = (e) => e.key === 'Escape' && onClose?.();
    window.addEventListener('keydown', onKey);
    return () => window.removeEventListener('keydown', onKey);
  }, [isOpen, onClose]);

  useEffect(() => {
    if (isOpen) setNewTask({ name: '', description: '' });
  }, [isOpen]);

  const createTask = async (e) => {
    e.preventDefault();
    if (!newTask.name.trim()) return;

    try {
      const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newTask),
      });

      if (response.ok) {
        setNewTask({ name: '', description: '' });
        onCreated?.();
        onClose?.();
      }
    } catch (error) {
      console.error('Error creating task:', error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div
        className="modal"
        role="dialog"
        aria-modal="true"
        aria-labelledby="add-task-title"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="modal-header">
          <h3 id="add-task-title">Add Task</h3>
          <button className="modal-close" onClick={onClose} aria-label="Close">×</button>
        </div>

        <form onSubmit={createTask} className="task-form">
          <input
            type="text"
            placeholder="Task name"
            value={newTask.name}
            onChange={(e) => setNewTask({ ...newTask, name: e.target.value, projectId: projectId })}
            className="task-input"
            autoFocus
          />
          <textarea
            placeholder="Task description (optional)"
            value={newTask.description}
            onChange={(e) => setNewTask({ ...newTask, description: e.target.value })}
            className="task-textarea"
          />
          <div className="modal-actions">
            <button type="button" className="secondary-btn" onClick={onClose}>
              Cancel
            </button>
            <button type="submit" className="add-btn">Add Task</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddTask;