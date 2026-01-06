import React, { useState, useEffect } from "react";
import Topbar from "../../components/Topbar";

export default function Notifications() {
  const [search, setSearch] = useState("");
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchNotifications() {
      try {
        const res = await fetch("http://localhost:9010/notifications"); // Replace with your API
        if (!res.ok) throw new Error("Failed to fetch notifications");

        const data = await res.json();
        setNotifications(data); // Assume API returns array of notifications
      } catch (err) {
        console.error(err);
        setError("Unable to load notifications");
      } finally {
        setLoading(false);
      }
    }

    fetchNotifications();
  }, []);

  const filtered = notifications.filter((n) =>
    n.message?.toLowerCase().includes(search.toLowerCase())
  );

  if (loading) return <div className="pt-16 px-8">Loading notifications...</div>;
  if (error) return <div className="pt-16 px-8 text-red-500">{error}</div>;

  return (
    <>
      <Topbar title="Notifications" />

      <div className="pt-0 px-6 max-w-5xl mx-auto">
        {/* Search */}
        <input
          type="text"
          placeholder="Search notifications..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="border px-4 py-2 rounded-md w-full mb-4"
        />

        {/* Notifications List */}
        <div className="space-y-4">
          {filtered.length === 0 && (
            <p className="text-gray-500 text-sm">No notifications found.</p>
          )}

          {filtered.map((n) => (
            <div
              key={n.id}
              className="flex items-start gap-4 p-4 border rounded-lg hover:bg-gray-50"
            >
              {/* Icon */}
              <div className="text-xl">
                {n.type === "Order" && "🛒"}
                {n.type === "Payout" && "💰"}
                {n.type === "Return" && "↩️"}
              </div>

              {/* Content */}
              <div className="flex-1">
                <p className="font-medium text-gray-800">{n.message}</p>
                <p className="text-xs text-gray-500 mt-1">{n.time}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
