import Sidebar from "../components/Sidebar";
import KanbanBoard from "../components/KanbanBoard";

export default function ProjectBoardPage() {
    return (
        <div className="flex h-screen">
            {/* Sidebar 1/6 width */}
            <div className="w-1/12 min-w-[8rem] bg-gray-200 dark:bg-gray-900 p-0 overflow-auto">
                <Sidebar />
            </div>

            {/* Board 5/6 width */}
            <div className="w-11/12 min-w-[40rem] bg-white dark:bg-gray-800 p-0 overflow-auto">
                <KanbanBoard />
            </div>
        </div>
    );
}
