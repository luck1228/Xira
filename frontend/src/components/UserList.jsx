import React, { useState, useEffect } from 'react';
import './UserList.css';

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [newUser, setNewUser] = useState({ title: '', description: '' });
  const [loading, setLoading] = useState(true);

  const API_BASE_URL = 'http://localhost:8080/api/users';

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await fetch(API_BASE_URL);
      const data = await response.json();
      setUsers(data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching users:', error);
      setLoading(false);
    }
  };

  const createUser = async (e) => {
    e.preventDefault();
    if (!newUser.title.trim()) return;

    try {
      const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newUser),
      });
      
      if (response.ok) {
        setNewUser({ title: '', description: '' });
        fetchUsers();
      }
    } catch (error) {
      console.error('Error creating user:', error);
    }
  };

  const toggleUser = async (id, user) => {
    try {
      const updatedUser = { ...user, completed: !user.completed };
      const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedUser),
      });
      
      if (response.ok) {
        fetchUsers();
      }
    } catch (error) {
      console.error('Error updating user:', error);
    }
  };

  const deleteUser = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'DELETE',
      });
      
      if (response.ok) {
        fetchUsers();
      }
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  if (loading) {
    return <div className="loading">Loading users...</div>;
  }

  return (
    <div className="user-list">
      <h2>User Management</h2>
      
      <form onSubmit={createUser} className="user-form">
        <input
          type="text"
          placeholder="User title"
          value={newUser.title}
          onChange={(e) => setNewUser({ ...newUser, title: e.target.value })}
          className="user-input"
        />
        <textarea
          placeholder="User description (optional)"
          value={newUser.description}
          onChange={(e) => setNewUser({ ...newUser, description: e.target.value })}
          className="user-textarea"
        />
        <button type="submit" className="add-btn">Add User</button>
      </form>

      <div className="users">
        {users.length === 0 ? (
          <p className="no-users">No users yet. Create your first user!</p>
        ) : (
          users.map((user) => (
            <div key={user.id} className={`user-item ${user.completed ? 'completed' : ''}`}>
              <div className="user-content">
                <h3 className="user-title">{user.title}</h3>
                {user.description && <p className="user-description">{user.description}</p>}
              </div>
              <div className="user-actions">
                <button
                  onClick={() => toggleUser(user.id, user)}
                  className={`toggle-btn ${user.completed ? 'complete' : 'incomplete'}`}
                >
                  {user.completed ? '✓' : '○'}
                </button>
                <button
                  onClick={() => deleteUser(user.id)}
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

export default UserList;
