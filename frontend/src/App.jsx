import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import TaskList from "./components/TaskList";
import ProjectList from "./components/ProjectList"
import ProjectBoardPage from "./pages/ProjectBoardPage";
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/tasks" element={<TaskList />} />
        <Route path="/projects" element={<ProjectList />} />
        <Route path="/project/:projectId" element={<ProjectBoardPage />} />
      </Routes>
    </Router>
  );
}
export default App;
