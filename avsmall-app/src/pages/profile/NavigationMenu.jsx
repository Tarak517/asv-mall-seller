import React from "react";

export default function NavigationMenu() {
  return (
    <div className="flex min-h-screen bg-gray-50">

      {/* SIDEBAR */}
      <aside className="w-64 bg-white border-r px-6 py-6">
        <div className="flex items-center gap-3 mb-8">
          <div className="w-10 h-10 bg-blue-600 rounded-lg" />
          <div>
            <h2 className="font-bold text-lg">Seller Admin</h2>
            <p className="text-xs text-gray-500">Wireframes • Tailwind</p>
          </div>
        </div>

        <nav className="space-y-4 text-gray-700">
          {[
            "Overview",
            "Dashboard",
            "Products",
            "Orders",
            "Payments",
            "Analytics",
            "Notifications",
            "Settings",
          ].map((item) => (
            <div
              key={item}
              className="flex items-center gap-3 px-3 py-2 rounded-lg hover:bg-gray-100 cursor-pointer"
            >
              <span>📦</span>
              <span>{item}</span>
            </div>
          ))}
        </nav>

        <div className="absolute bottom-6 left-6 text-sm text-gray-500">
          Brand: <span className="text-blue-600">#0074C7</span>
        </div>
      </aside>

      {/* MAIN CONTENT */}
      <main className="flex-1">

        {/* TOPBAR */}
        <header className="bg-white border-b px-6 py-4 flex justify-between items-center">
          <h1 className="text-xl font-semibold">Navigation Menu</h1>

          <div className="flex items-center gap-4">
            <input
              type="text"
              placeholder="Search..."
              className="border rounded-lg px-4 py-2 w-64"
            />
            <div className="w-10 h-10 bg-blue-600 rounded-full" />
          </div>
        </header>

        {/* CONTENT */}
        <div className="p-6 space-y-6">

          <p className="text-gray-600">
            Sidebar / Bottom nav demo
          </p>

          <div className="bg-white border rounded-xl p-6">
            <h2 className="text-lg font-semibold mb-6">
              Sidebar vs Bottom Nav
            </h2>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

              {/* Sidebar Demo */}
              <div className="border rounded-xl p-4">
                <h3 className="font-medium mb-3 text-gray-700">
                  Sidebar (Desktop)
                </h3>
                <div className="h-48 bg-gray-100 rounded-lg" />
              </div>

              {/* Bottom Nav Demo */}
              <div className="border rounded-xl p-4">
                <h3 className="font-medium mb-3 text-gray-700">
                  Bottom Nav (Mobile)
                </h3>
                <div className="h-48 bg-gray-100 rounded-lg" />
              </div>

            </div>
          </div>

        </div>
      </main>
    </div>
  );
}
