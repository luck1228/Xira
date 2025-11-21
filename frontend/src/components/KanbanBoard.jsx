import React from 'react';
import { useParams } from "react-router-dom";
import './KanbanBoard.css';
import DarkModeToggle from "./DarkModeToggle";
import AddTask from './AddTask';

const KanbanBoard = () => {
    const { projectId } = useParams();
    const [boardColumns, setBoardColumns] = React.useState([]);
    const [columnTitle] = React.useState({ "To do": "", "In Progress": "", "Done": "" });
    const [searchText, setSearchText] = React.useState("");
    const [showAddTask, setShowAddTask] = React.useState(false);

    const API_BASE_URL = 'http://localhost:8080/api/board-columns';

    React.useEffect(() => {
        if (projectId) fetchBoardColumns(projectId);
    }, [projectId]);

    const fetchBoardColumns = async (projectId) => {
        try {
            const response = await fetch(`${API_BASE_URL}/project/${projectId}`);
            const data = await response.json();
            setBoardColumns(data);
        } catch (error) {
            console.error('Error fetching board columns:', error);
        }
    };

    return (
        <>  <div className="pl-2 pt-4 pb-[1px] bg-white shadow-md rounded-md mb-4 flex-col items-center justify-between dark:bg-gray-800">
                <div className="flex items-center justify-between mb-4 pr-2">
                    <h2 className="text-lg font-bold text-black dark:text-white text-2xl">
                        Kanban Board
                    </h2>
                        <DarkModeToggle />
                </div>
                <div className='flex items-center justify-between mb-4 pr-2'>
                    <input
                        type="text"
                        placeholder="Search tasks..."
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                        className="border border-gray-300 rounded-md p-2 w-[70%] max-w-[22.4rem] focus:outline-none focus:ring-2 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white" 
                    />
                    <button
                        className="w-[10rem] ml-2 px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        onClick={() => setShowAddTask(true)}
                    >
                        Add task
                    </button>
                </div>
            </div>
                <div className="flex gap-4 pr-2 pl-2">
                {Object.keys(columnTitle).map((title, index) => (
                    <div key={index} className="w-1/3 bg-gray-100 p-2 rounded shadow flex flex-col h-full dark:bg-gray-700">
                        {/* Column Title */}
                        <h2 className="text-lg font-bold mb-2 text-center sticky top-0 bg-gray-100 z-10 dark:bg-gray-700 dark:text-white">{title}</h2>

                        {/* Column tasks will go here */}
                        <div className="flex-1 flex flex-col gap-2 overflow-y-auto">
                            {boardColumns.filter((column) => column.position === index + 1 && (column.name.toLowerCase().includes(searchText.toLowerCase()) || column.description.toLowerCase().includes(searchText.toLowerCase()))).map((column) => (
                                <div key={column.id} className="text-lg font-bold bg-white p-2 rounded shadow dark:bg-gray-800 dark:text-white">
                                    {column.name}
                                    <div className="text-sm font-normal text-gray-500 dark:text-gray-400 mt-1">
                                        {column.description}
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                ))}
                </div>
                <AddTask
                    isOpen={showAddTask}
                    onClose={() => setShowAddTask(false)}
                    projectId={projectId}
                    onCreated={() => {
                        fetchBoardColumns(projectId);
                    }}
                />
            </>
    );
}

export default KanbanBoard;