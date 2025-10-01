import React from 'react';
import './Sidebar.css';

export default function Sidebar() {
    return (
        <div className="p-4 bg-gray-200 h-full">
            <h2 className="text-xl font-bold">Projects</h2>
            <ul>
                <li>Project 1</li>
                <li>Project 2</li>
            </ul>
        </div>
    );
}