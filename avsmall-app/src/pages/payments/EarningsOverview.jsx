import React, { useEffect, useState } from "react";
import Topbar from "../../components/Topbar";

export default function EarningsOverview() {
  const [earnings, setEarnings] = useState({
    total: 0,
    pending: 0,
    available: 0,
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchEarnings() {
      try {
        const res = await fetch("http://localhost:9010/api/payments"); // your Spring Boot API
        if (!res.ok) throw new Error("Failed to fetch earnings");

        const data = await res.json();
        // assuming API returns { total: 480000, pending: 36000, available: 22000 }
        setEarnings(data);
      } catch (err) {
        console.error(err);
        setError("Unable to load earnings");
      } finally {
        setLoading(false);
      }
    }

    fetchEarnings();
  }, []);

  if (loading) {
    return (
      <>
        <Topbar title="Earnings Overview" />
        <div className="pt-16 px-8">Loading earnings...</div>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Topbar title="Earnings Overview" />
        <div className="pt-16 px-8 text-red-500">{error}</div>
      </>
    );
  }

  return (
    <>
      <Topbar title="Earnings Overview" />

      <div className="space-y-6">
        {/* SUMMARY */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="bg-white border rounded-xl p-6">
            <p className="text-gray-500">Total Earnings</p>
            <h2 className="text-2xl font-semibold">₹{(earnings.total / 100).toLocaleString()}</h2>
          </div>

          <div className="bg-white border rounded-xl p-6">
            <p className="text-gray-500">Pending</p>
            <h2 className="text-2xl font-semibold">₹{(earnings.pending / 100).toLocaleString()}</h2>
          </div>

          <div className="bg-white border rounded-xl p-6">
            <p className="text-gray-500">Available</p>
            <h2 className="text-2xl font-semibold">₹{(earnings.available / 100).toLocaleString()}</h2>
          </div>
        </div>
      </div>
    </>
  );
}
