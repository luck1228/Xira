import Sidebar from "../components/Sidebar";
import KanbanBoard from "../components/KanbanBoard";

export default function ProjectBoardPage() {
    return (
        <div className="flex h-screen">
            {/* Sidebar 1/6 width */}
            <div className="w-1/6 bg-gray-200">
                <Sidebar />
            </div>

            {/* Board 5/6 width */}
            <div className="w-5/6 bg-white">
                <KanbanBoard />
            </div>
        </div>
    );
}
