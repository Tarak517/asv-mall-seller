import React, { useState } from "react";
import Sidebar from "./Sidebar";
import Topbar from "./Topbar";
import { Outlet, useLocation } from "react-router-dom";

export default function AdminLayout() {
  const { pathname } = useLocation();
  const [sidebarOpen, setSidebarOpen] = useState(false);

  // Auto-generate title from URL
  const title =
    pathname
      .split("/")
      .filter(Boolean)
      .slice(-1)[0]
      ?.replace(/-/g, " ")
      ?.replace(/\b\w/g, (c) => c.toUpperCase()) || "Seller Admin";
  return (
    <div className="min-h-screen bg-gray-50">
      {/* Top Navigation Bar */}
      <Topbar
      title={title}
      toggleSidebar={() => setSidebarOpen(!sidebarOpen)}
      />
      <div className="flex">
        {/* Sidebar Navigation */}
        <Sidebar isOpen={sidebarOpen} />
        {/* Main Content */}
        <div className="flex-1 pt-14 md:ml-64">
          <main className="p-4">
            <Outlet />
          </main>
        </div>
      </div>
    </div>
  );
}  