import React, { useEffect, useState } from "react";

export default function OrderAnalytics() {
  const [analytics, setAnalytics] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchAnalytics() {
      try {
        const res = await fetch("http://localhost:9010analytics"); // your API
        if (!res.ok) throw new Error("Failed to fetch analytics");

        const data = await res.json();
        setAnalytics(data);
      } catch (err) {
        console.error(err);
        setError("Unable to load analytics");
      } finally {
        setLoading(false);
      }
    }

    fetchAnalytics();
  }, []);

  if (loading) return <div className="p-6">Loading analytics...</div>;
  if (error) return <div className="p-6 text-red-500">{error}</div>;

  return (
    <div className="p-6">
      <h1 className="text-xl font-semibold mb-4">Order Analytics</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-white border rounded-xl p-6">
          <p className="text-gray-500">Total Orders</p>
          <h2 className="text-2xl font-semibold">{analytics.totalOrders}</h2>
        </div>

        <div className="bg-white border rounded-xl p-6">
          <p className="text-gray-500">Delivered</p>
          <h2 className="text-2xl font-semibold">{analytics.deliveredOrders}</h2>
        </div>

        <div className="bg-white border rounded-xl p-6">
          <p className="text-gray-500">Returned</p>
          <h2 className="text-2xl font-semibold">{analytics.returnedOrders}</h2>
        </div>
      </div>

      <div className="mt-6 bg-white border rounded-xl p-6">
        <p className="text-gray-600">
          Orders by Status: New ({analytics.newOrders}), Accepted ({analytics.acceptedOrders}), Shipped ({analytics.shippedOrders})
        </p>
      </div>
    </div>
  );
}
