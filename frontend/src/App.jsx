import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import ProjectList from "./components/ProjectList"
import ProjectBoardPage from "./pages/ProjectBoardPage";
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/projects" element={<ProjectList />} />
        <Route path="/project/:projectId" element={<ProjectBoardPage />} />
      </Routes>
    </Router>
  );
}
export default App;
