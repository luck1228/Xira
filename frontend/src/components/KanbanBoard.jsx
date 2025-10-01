import React from 'react';
import { useParams } from "react-router-dom";
import './KanbanBoard.css';

const KanbanBoard = () => {
    const { projectId } = useParams(); // Get projectId from URL
    const [boardColumns, setBoardColumns] = React.useState([]);
    const [columnTitle] = React.useState({ "TO DO": "", "IN PROGRESS": "", "DONE": "" }); // Static titles for columns

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
        <div className="flex gap-4">
            {Object.keys(columnTitle).map((title, index) => (
                <div key={index} className="w-1/3 bg-gray-100 p-2 rounded shadow flex flex-col h-full">
                    {/* Column Title */}
                    <h2 className="text-lg font-bold mb-2 text-center sticky top-0 bg-gray-100 z-10">{title}</h2>

                    {/* Column tasks will go here */}
                    <div className="flex-1 flex flex-col gap-2 overflow-y-auto">
                        {boardColumns.filter((column) => column.position === index+1).map((column) => (
                            <div key={column.id} className="bg-white p-2 rounded shadow">
                                {column.name}
                            </div>
                        ))}
                    </div>
                </div>
            ))}
        </div>

    );
}

export default KanbanBoard;