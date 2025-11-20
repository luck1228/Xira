import Sidebar from "../components/Sidebar";
import KanbanBoard from "../components/KanbanBoard";

export default function ProjectBoardPage() {
    return (
        <div className="flex h-screen">
            {/* Sidebar 1/6 width */}
            <div className="w-1/6 bg-gray-200 dark:bg-gray-900 p-4 overflow-auto">
                <Sidebar />
            </div>

            {/* Board 5/6 width */}
            <div className="w-5/6 bg-white dark:bg-gray-800 p-4 overflow-auto">
                <KanbanBoard />
            </div>
        </div>
    );
}
