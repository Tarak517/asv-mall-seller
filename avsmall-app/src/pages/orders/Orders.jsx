import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Topbar from "../../components/Topbar";

const STATUS_FLOW = ["New", "Accepted", "Shipped", "Delivered", "Cancelled"];

export default function Orders() {
  const navigate = useNavigate();

  const [orders, setOrders] = useState([]);
  const [search, setSearch] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchOrders() {
      try {
        const res = await fetch("http://localhost:9010/orders");
        if (!res.ok) throw new Error("Failed to fetch orders");

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

  const updateStatus = async (orderId, newStatus) => {
    try {
      const res = await fetch(
        `http://localhost:9010/orders/${orderId}/status`,
        {
          method: "PATCH",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ status: newStatus }),
        }
      );

      if (!res.ok) throw new Error("Failed to update status");

      setOrders((prev) =>
        prev.map((o) => (o.orderId === orderId ? { ...o, status: newStatus } : o))
      );

      alert(`Order ${orderId} status updated to ${newStatus}`);
    } catch (err) {
      console.error(err);
      alert("Failed to update status");
    }
  };

  const filteredOrders = orders.filter((o) => {
    const orderId = o.orderId ? String(o.orderId).toLowerCase() : "";
    const payment = o.paymentMethod ? String(o.paymentMethod).toLowerCase() : "";

    const matchesSearch =
      orderId.includes(search.toLowerCase()) || payment.includes(search.toLowerCase());

    const matchesStatus = statusFilter === "" || o.status === statusFilter;

    return matchesSearch && matchesStatus;
  });

  if (loading)
    return (
      <>
        <Topbar title="All Orders" />
        <div className="pt-16 px-8">Loading orders...</div>
      </>
    );

  if (error)
    return (
      <>
        <Topbar title="All Orders" />
        <div className="pt-16 px-8 text-red-500">{error}</div>
      </>
    );

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
            {STATUS_FLOW.map((s) => (
              <option key={s} value={s}>
                {s}
              </option>
            ))}
          </select>
        </div>

        {/* Orders Table */}
        <div className="bg-white border rounded-xl overflow-x-auto">
          <table className="w-full text-sm">
            <thead className="bg-gray-50 text-gray-600">
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
                filteredOrders.map((order) => (
                  <tr key={order.orderId} className="border-t text-sm">
                    <td className="px-6 py-4">{order.orderId}</td>
                    <td className="px-6 py-4">{order.status}</td>
                    <td className="px-6 py-4">₹{order.amount}</td>
                    <td className="px-6 py-4">{order.paymentMethod}</td>
                    <td className="px-6 py-4 flex gap-2">
                      {/* Order Status Buttons */}
                      {STATUS_FLOW.map((status) => {
                        const currentIndex = STATUS_FLOW.indexOf(order.status);
                        const statusIndex = STATUS_FLOW.indexOf(status);
                        const isNext = statusIndex === currentIndex + 1;
                        const isDisabled =
                          status === order.status || (!isNext && status !== "Cancelled");

                        return (
                          <button
                            key={status}
                            disabled={isDisabled}
                            onClick={() => updateStatus(order.orderId, status)}
                            className={`px-2 py-1 rounded border text-xs font-medium transition ${
                              isDisabled
                                ? "bg-gray-200 cursor-not-allowed"
                                : "hover:bg-blue-600 hover:text-white"
                            }`}
                          >
                            {status}
                          </button>
                        );
                      })}

                      {/* Details Button */}
                      <button
                        onClick={() => navigate(`/orders/${order.orderId}`)}
                        className="text-blue-600 font-medium hover:underline px-2"
                      >
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
