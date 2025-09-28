import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TaskList from "./components/TaskList";
import ProjectList from "./components/ProjectList"
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/tasks" element={<TaskList />} />
        <Route path="/projects" element={<ProjectList />} />
      </Routes>
    </Router>
  );
}
export default App;
