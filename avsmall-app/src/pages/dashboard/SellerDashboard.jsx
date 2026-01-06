import React, { useEffect, useState } from "react";
import Topbar from "../../components/Topbar";

export default function SellerDashboard() {
  const [orders, setOrders] = useState([]);
  const [payments, setPayments] = useState([]);
  const [products, setProducts] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchData() {
      try {
        const [ordersRes, paymentsRes, productsRes, customersRes] = await Promise.all([
          fetch("http://localhost:9010/orders"),
          fetch("http://localhost:9010/api/payments"),
          fetch("http://localhost:9010/api/products"),
          fetch("http://localhost:9010/customers")
        ]);

        const [ordersData, paymentsData, productsData, customersData] = await Promise.all([
          ordersRes.json(),
          paymentsRes.json(),
          productsRes.json(),
          customersRes.json()
        ]);

        setOrders(ordersData);
        setPayments(paymentsData);
        setProducts(productsData);
        setCustomers(customersData);

      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, []);

  if (loading) return <div className="pt-16 px-8 text-gray-600">Loading dashboard...</div>;

  const today = new Date().toISOString().split("T")[0]; // YYYY-MM-DD

  const todayOrders = orders.filter(o => o.date === today);
  const todaySales = todayOrders.reduce((sum, o) => sum + o.amount, 0);
  const todayPayments = payments.filter(p => p.date === today && p.status === "Paid");
  const todayEarnings = todayPayments.reduce((sum, p) => sum + p.amount, 0);
  const todayVisitors = customers.filter(c => c.registeredDate === today).length;
  const todayProducts = products.filter(p => p.addedDate === today).length;

  // Orders by status
  const statusSummary = orders.reduce((acc, o) => {
    acc[o.status] = (acc[o.status] || 0) + 1;
    return acc;
  }, {});

  // Last 7 days sales
  const last7Days = Array.from({ length: 7 }, (_, i) => {
    const day = new Date();
    day.setDate(day.getDate() - i);
    const dayStr = day.toISOString().split("T")[0];
    const daySales = orders
      .filter(o => o.date === dayStr)
      .reduce((sum, o) => sum + o.amount, 0);
    return daySales;
  }).reverse();

  return (
    <div className="flex min-h-screen bg-gray-50">
      <Topbar title="Seller Dashboard" />

      <main className="flex-1 p-6 space-y-8">
        <div className="grid grid-cols-1 md:grid-cols-5 gap-6">
          {[
            { label: "Today's Sales", value: `₹${todaySales}` },
            { label: "Today's Orders", value: todayOrders.length },
            { label: "Today's Earnings", value: `₹${todayEarnings}` },
            { label: "Today's Visitors", value: todayVisitors },
            { label: "Today's Products", value: todayProducts },
          ].map(item => (
            <div key={item.label} className="bg-white border rounded-xl p-4">
              <p className="text-sm text-gray-500">{item.label}</p>
              <h3 className="text-xl font-bold mt-1">{item.value}</h3>
            </div>
          ))}
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">

          {/* Sales chart */}
          <div className="bg-white border rounded-xl p-6">
            <h3 className="font-semibold mb-4">Sales (Last 7 days)</h3>
            <div className="flex items-end gap-2 h-40">
              {last7Days.map((s, i) => (
                <div
                  key={i}
                  className="bg-blue-600 w-6 rounded-md"
                  style={{ height: `${s / 100}%` }}
                />
              ))}
            </div>
          </div>

          {/* Orders by Status */}
          <div className="bg-white border rounded-xl p-6">
            <h3 className="font-semibold mb-4">Orders by Status</h3>
            <ul className="space-y-3 text-sm">
              {Object.entries(statusSummary).map(([status, count]) => (
                <li key={status} className="flex justify-between">
                  <span>{status}</span>
                  <span>{count}</span>
                </li>
              ))}
            </ul>
          </div>

          {/* Quick Actions */}
          <div className="bg-white border rounded-xl p-6">
            <h3 className="font-semibold mb-4">Quick Actions</h3>
            <div className="flex gap-4 text-blue-600">
              <button className="font-medium">Add Product</button>
              <button className="font-medium">View Orders</button>
              <button className="font-medium">Withdraw</button>
            </div>
          </div>

        </div>
      </main>
    </div>
  );
}
