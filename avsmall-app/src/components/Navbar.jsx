import React from "react";
import { Menu } from "lucide-react"; // optional (npm i lucide-react)

export default function Navbar({ toggleSidebar }) {
  return (
    <header className="fixed top-0 left-0 right-0 h-14 bg-white border-b flex items-center justify-between px-4 z-40">
      <div className="flex items-center gap-3">
        {/* Hamburger */}
        <button
          onClick={toggleSidebar}
          className="p-2 rounded hover:bg-gray-100"
        >
          <Menu size={20} />
        </button>

        <h2 className="font-semibold text-lg">Seller Admin</h2>
      </div>

      <div className="flex items-center gap-4">
        <button className="text-xl">🔔</button>
        <img
          src="/AVSMall.jpg"
          alt="Profile"
          className="w-8 h-8 rounded-full object-cover"
        />
      </div>
    </header>
  );
}
