import React from "react";

export default function Topbar({ title, toggleSidebar }) {
  return (
    <header
      className="
        fixed top-0 left-0 md:left-64 right-0
        h-14 bg-white border-b
        flex items-center justify-between
        px-4 md:px-6
        shadow-sm z-50
      "
    >
      {/* Left: Hamburger + Page Title */}
      <div className="flex items-center gap-3">
        {/* Hamburger (mobile only) */}
        <button
          onClick={toggleSidebar}
          className="md:hidden p-2 rounded hover:bg-gray-100"
        >
          ☰
        </button>

        <h1 className="text-lg font-semibold text-gray-800">
          {title}
        </h1>
      </div>

      {/* Right: Search + User */}
      <div className="flex items-center gap-4">
        {/* Search */}
        <input
          type="text"
          placeholder="Search..."
          className="
            hidden sm:block
            border rounded-md px-3 py-1.5
            text-sm
            focus:outline-none focus:ring-2 focus:ring-blue-500
          "
        />
        {/* User Avatar */}
        <img
          src="/AVSMall.jpg"
          alt="User"
          className="w-8 h-8 rounded-full object-cover"
        />
      </div>
    </header>
  );
}
