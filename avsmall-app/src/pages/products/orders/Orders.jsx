import React, { useEffect, useState } from "react";
import Topbar from "../../components/Topbar";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [search, setSearch] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchOrders() {
      try {
        const res = await fetch("http://localhost:9010/orders");

        if (!res.ok) {
          throw new Error("Failed to fetch orders");
        }

        const data = await res.json();
        setOrders(data);
      } catch (err) {
        console.error(err);
        setError("Unable to load orders");
      } finally {
        setLoading(false);
      }
    }

    fetchOrders();
  }, []);

  const filteredOrders = orders.filter(
    (o) =>
      (o.orderId?.toLowerCase().includes(search.toLowerCase()) ||
        o.paymentMethod?.toLowerCase().includes(search.toLowerCase())) &&
      (statusFilter === "" || o.status === statusFilter)
  );

  if (loading) {
    return (
      <>
        <Topbar title="All Orders" />
        <div className="pt-16 px-8">Loading orders...</div>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Topbar title="All Orders" />
        <div className="pt-16 px-8 text-red-500">{error}</div>
      </>
    );
  }

  return (
    <>
      <Topbar title="All Orders" />

      <div className="pt-16 px-8 max-w-6xl mx-auto">
        {/* Search & Filter */}
        <div className="flex flex-col md:flex-row items-start md:items-center justify-between mb-4 gap-2">
          <input
            type="text"
            placeholder="Search order / payment"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="border px-4 py-2 rounded-md text-sm w-full md:w-64"
          />

          <select
            value={statusFilter}
            onChange={(e) => setStatusFilter(e.target.value)}
            className="border px-4 py-2 rounded-md text-sm"
          >
            <option value="">Filter by status</option>
            <option value="New">New</option>
            <option value="Accepted">Accepted</option>
            <option value="Shipped">Shipped</option>
            <option value="Delivered">Delivered</option>
            <option value="Cancelled">Cancelled</option>
          </select>
        </div>

        {/* Orders Table */}
        <div className="bg-white border rounded-xl overflow-hidden">
          <table className="w-full">
            <thead className="bg-gray-50 text-gray-600 text-sm">
              <tr>
                <th className="text-left px-6 py-3">Order #</th>
                <th className="text-left px-6 py-3">Status</th>
                <th className="text-left px-6 py-3">Amount</th>
                <th className="text-left px-6 py-3">Payment</th>
                <th className="text-left px-6 py-3">Actions</th>
              </tr>
            </thead>

            <tbody>
              {filteredOrders.length === 0 ? (
                <tr>
                  <td colSpan="5" className="text-center py-10 text-gray-400">
                    No orders found
                  </td>
                </tr>
              ) : (
                filteredOrders.map((order, idx) => (
                  <tr key={idx} className="border-t text-sm">
                    <td className="px-6 py-4">{order.orderId}</td>
                    <td className="px-6 py-4">{order.status}</td>
                    <td className="px-6 py-4">₹{order.amount}</td>
                    <td className="px-6 py-4">{order.paymentMethod}</td>
                    <td className="px-6 py-4">
                      <button className="text-blue-600 font-medium hover:underline">
                        Details
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
