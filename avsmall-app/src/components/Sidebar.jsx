import React from "react";
import { NavLink, useLocation } from "react-router-dom";

const sidebarLinks = [
  { name: "Overview", icon: "📋", path: "/overview" },
  { name: "Dashboard", icon: "🏠", path: "/dashboard" },

  {
    name: "Products",
    icon: "📦",
    path: "/products",
    children: [
      { name: "Add Product", icon: "➕", path: "/products/add" },
      { name: "Edit Product", icon: "✏️", path: "/products/edit/SKU-1001" },
      { name: "Inactive Products", icon: "❌", path: "/products/rejected" },
    ],
  },

  {
    name: "Orders",
    icon: "🛒",
    path: "/orders",
    children: [
      { name: "All Orders", icon: "📄", path: "/orders" },
      { name: "Pickup Request", icon: "📦", path: "/orders/pickup" },
    ],
  },

  {
    name: "Payments",
    icon: "💳",
    path: "/payments",
    children: [
      { name: "Earnings Overview", icon: "💰", path: "/payments/overview" },
      { name: "Transaction History", icon: "📑", path: "/payments/transactions" },
    ],
  },

  {
    name: "Analytics",
    icon: "📊",
    path: "/analytics",
    children: [
      { name: "Analytics", icon: "📊", path: "/analytics" },
    ],
  },

  { name: "Notifications", icon: "🔔", path: "/notifications" },

  {
    name: "Settings",
    icon: "⚙️",
    path: "/settings",
    children: [
      { name: "Profile", icon: "👤", path: "/settings/profile" },
      { name: "Privacy & Terms", icon: "📜", path: "/settings/privacy-terms" },
      { name: "Help Center", icon: "❓", path: "/settings/help-center" },
    ],
  },
];

export default function Sidebar({ isOpen }) {
  const location = useLocation();

  const isActiveParent = (path) =>
    location.pathname === path ||
    location.pathname.startsWith(path + "/");

  return (
    <aside
      className={`
        fixed top-0 left-0
        h-screen w-64
        bg-white border-r
        overflow-y-auto
        transition-transform duration-300
        z-40
        ${isOpen ? "translate-x-0" : "-translate-x-full"}
        md:translate-x-0
      `}
    >
      {/* Header */}
      <div className="flex items-center gap-3 px-4 py-3 border-b">
        <img
          src="/AVSMall.jpg"
          alt="AVSMall Logo"
          className="w-10 h-10 rounded-full object-cover"
        />
        <h1 className="text-lg font-semibold text-gray-800">
          Seller Admin
        </h1>
      </div>

      {/* Navigation */}
      <nav className="mt-4 text-gray-700">
        <ul className="space-y-1">
          {sidebarLinks.map((link, index) => (
            <React.Fragment key={index}>
              <li>
                <NavLink
                  to={link.path}
                  end={!link.children}
                  className={() =>
                    `flex items-center gap-3 px-6 py-2 transition hover:bg-gray-100 ${
                      isActiveParent(link.path)
                        ? "bg-blue-50 text-blue-600 border-r-4 border-blue-600 font-medium"
                        : ""
                    }`
                  }
                >
                  <span>{link.icon}</span>
                  <span>{link.name}</span>
                </NavLink>
              </li>

              {/* CHILD LINKS */}
              {link.children && isActiveParent(link.path) && (
                <ul className="ml-4 space-y-1">
                  {link.children.map((child, idx) => (
                    <li key={idx}>
                      <NavLink
                        to={child.path}
                        className={({ isActive }) =>
                          `flex items-center gap-3 px-10 py-2 text-sm rounded transition hover:bg-gray-100 ${
                            isActive
                              ? "text-blue-600 font-medium"
                              : "text-gray-600"
                          }`
                        }
                      >
                        <span>{child.icon}</span>
                        <span>{child.name}</span>
                      </NavLink>
                    </li>
                  ))}
                </ul>
              )}
            </React.Fragment>
          ))}
        </ul>
      </nav>
    </aside>
  );
}
