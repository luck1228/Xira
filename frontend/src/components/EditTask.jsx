import './EditTask.css';
import { useState, useEffect } from 'react';

const EditTask = ({ isOpen, onClose, projectId, onCreated, taskId }) => {
  const [newTask, editNewTask] = useState({ name: '', description: '', assigneeId: '' });

    const API_BASE_URL = 'http://localhost:8080/api/tasks';

    useEffect(() => {
        if (!isOpen) return;
        const onKey = (e) => e.key === 'Escape' && onClose?.();
        window.addEventListener('keydown', onKey);
        return () => window.removeEventListener('keydown', onKey);
    }, [isOpen, onClose]);

    useEffect(() => {
        if (isOpen) {
            console.log("Editing task:", taskId);
            // Fetch existing task details for editing
            fetch(`${API_BASE_URL}/${taskId}`)
                .then(response => response.json())
                .then(data => editNewTask({ 
                    name: data.name, 
                    description: data.description, 
                    assigneeId: data.assigneeId || null,
                    status: data.status
                }))
                .catch(error => console.error('Error fetching task details:', error));
            console.log("Loaded task details:", newTask);
        }
    }, [isOpen, taskId]);

    const updateTask = async (e) => {
        e.preventDefault();
        console.log("Updating task with data:", newTask);
        if (!newTask.name.trim()) return;
        const method = 'PUT';
        const url = `${API_BASE_URL}/${taskId}`;
        try {
            const response = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ...newTask, projectId }),
            });
            if (response.ok) {
                editNewTask({ name: '', description: '', assigneeId: '' , status: ''});
                onCreated?.();
                onClose?.();
            }
        } catch (error) {
            console.error('Error creating or updating task:', error);
        }
    };

    if (!isOpen) return null;
    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <div className="modal-header">
                    <h3> Edit Task </h3>
                    <button className="modal-close" onClick={onClose} aria-label="Close">×</button>
                </div>

                <form onSubmit={updateTask} className="task-form">
                    <input
                        type="text"
                        placeholder="Task name"
                        value={newTask.name}
                        onChange={e => editNewTask({ ...newTask, name: e.target.value })}
                        className="task-input"
                        required
                    />
                    <textarea
                        placeholder="Task description (optional)"
                        value={newTask.description}
                        onChange={e => editNewTask({ ...newTask, description: e.target.value })}
                        className="task-textarea"
                    />
                    <input
                        type="text"
                        placeholder="Assignee ID (optional)"
                        value={newTask.assigneeId}
                        onChange={e => editNewTask({ ...newTask, assigneeId: e.target.value })}
                        className="task-input"
                    />
                    <select
                        value={newTask.status}
                        onChange={e => editNewTask({ ...newTask, status: e.target.value })}
                        className="task-input"
                    >
                        <option value="TO_DO">TO DO</option>
                        <option value="IN_PROGRESS">IN PROGRESS</option>
                        <option value="DONE">DONE</option>
                    </select>
                    <div className="modal-actions">
                        <button type="button" className="secondary-btn" onClick={onClose}>
                            Cancel
                        </button>
                        <button type="submit" className="primary-btn">
                            Update Task
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default EditTask;