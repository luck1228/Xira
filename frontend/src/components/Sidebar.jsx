import React from 'react';
import './Sidebar.css';
import DarkModeToggle from "./DarkModeToggle";

const toggleDarkMode = () => {
    const html = document.documentElement;
    html.classList.toggle("dark");

    if (html.classList.contains("dark")) {
        localStorage.setItem("theme", "dark");
    } else {
        localStorage.setItem("theme", "light");
    }
};


export default function Sidebar() {
    return (
        <div className="p-4 bg-gray-200 h-full dark:bg-gray-900 text-black dark:text-white">
            <div>
                <DarkModeToggle />
            </div>
            <h2 className="text-xl font-bold">Projects</h2>
            <ul className="pl-2 space-y-1">
                <li>Project 1</li>
                <li>Project 2</li>
            </ul>
            
        </div>
    );
}