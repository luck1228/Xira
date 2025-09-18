import React, { useState, useEffect } from 'react';
import './ProjectList.css';

const ProjectList = () => {
  const [projects, setProjects] = useState([]);
  const [newProject, setNewProject] = useState({ title: '', description: '' });
  const [loading, setLoading] = useState(true);

  const API_BASE_URL = 'http://localhost:8080/api/projects';

  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    try {
      const response = await fetch(API_BASE_URL);
      const data = await response.json();
      setProjects(data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching projects:', error);
      setLoading(false);
    }
  };

  const createProject = async (e) => {
    e.preventDefault();
    if (!newProject.title.trim()) return;

    try {
      const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProject),
      });
      
      if (response.ok) {
        setNewProject({ title: '', description: '' });
        fetchProjects();
      }
    } catch (error) {
      console.error('Error creating project:', error);
    }
  };

  const toggleProject = async (id, project) => {
    try {
      const updatedProject = { ...project, completed: !project.completed };
      const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedProject),
      });
      
      if (response.ok) {
        fetchProjects();
      }
    } catch (error) {
      console.error('Error updating project:', error);
    }
  };

  const deleteProject = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'DELETE',
      });
      
      if (response.ok) {
        fetchProjects();
      }
    } catch (error) {
      console.error('Error deleting project:', error);
    }
  };

  if (loading) {
    return <div className="loading">Loading projects...</div>;
  }

  return (
    <div className="project-list">
      <h2>Project Management</h2>
      
      <form onSubmit={createProject} className="project-form">
        <input
          type="text"
          placeholder="Project title"
          value={newProject.title}
          onChange={(e) => setNewProject({ ...newProject, title: e.target.value })}
          className="project-input"
        />
        <textarea
          placeholder="Project description (optional)"
          value={newProject.description}
          onChange={(e) => setNewProject({ ...newProject, description: e.target.value })}
          className="project-textarea"
        />
        <button type="submit" className="add-btn">Add Project</button>
      </form>

      <div className="projects">
        {projects.length === 0 ? (
          <p className="no-projects">No projects yet. Create your first project!</p>
        ) : (
          projects.map((project) => (
            <div key={project.id} className={`project-item ${project.completed ? 'completed' : ''}`}>
              <div className="project-content">
                <h3 className="project-title">{project.title}</h3>
                {project.description && <p className="project-description">{project.description}</p>}
              </div>
              <div className="project-actions">
                <button
                  onClick={() => toggleProject(project.id, project)}
                  className={`toggle-btn ${project.completed ? 'complete' : 'incomplete'}`}
                >
                  {project.completed ? '✓' : '○'}
                </button>
                <button
                  onClick={() => deleteProject(project.id)}
                  className="delete-btn"
                >
                  ✕
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default ProjectList;
