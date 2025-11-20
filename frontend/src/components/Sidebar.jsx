import React from 'react';
import './Sidebar.css';
import { useNavigate } from "react-router-dom";


const Sidebar = () => {

    const [projects, setProjects] = React.useState([]);
    const navigate = useNavigate();
    
    const API_BASE_URL = 'http://localhost:8080/api/projects';

    React.useEffect(() => {
        fetchProjectIds();
    }, []);

    const fetchProjectIds = async () => {
        try {
            const response = await fetch(API_BASE_URL);
            const data = await response.json();
            setProjects(data.map(project => ({ name: project.name, id: project.id })));
        } catch (error) {
            console.error('Error fetching project IDs:', error);
        }
    };

    return (
        <div className="text-left p-0 bg-gray-200 h-full dark:bg-gray-700 text-black dark:text-white">
            <h2 className="text-left text-xl px-4 font-bold py-4">Projects</h2>

            <ul className="text-left space-y-0 flex flex-col bg">
                {projects.map((project) => (
                    <button
                        key={project.id}
                        onClick={() => navigate(`/project/${project.id}`)}
                        className="block w-full px-4 py-1 text-left bg-gray-200 text-black hover:bg-gray-400 transition dark:bg-gray-700 dark:hover:bg-gray-600 dark:text-white"
                    >
                        {project.name}
                    </button>
                ))}
            </ul>
            
        </div>
    );
}

export default Sidebar;