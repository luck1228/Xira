import React from 'react';
import TaskList from '../components/TaskList';
import './Dashboard.css';

const Dashboard = () => {
  return (
    <div className="dashboard">
      <header className="dashboard-header">
        <h1>Xira - Task Management Dashboard</h1>
        <p>Organize your tasks efficiently</p>
      </header>
      
      <main className="dashboard-main">
        <TaskList />
      </main>
      
      <footer className="dashboard-footer">
        <p>&copy; 2025 Xira Project</p>
      </footer>
    </div>
  );
};

export default Dashboard;
