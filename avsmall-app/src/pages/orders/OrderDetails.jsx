import React, { useEffect, useState } from "react";
import { useParams, NavLink } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function OrderDetails() {
  const { id } = useParams();
  const [order, setOrder] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchOrder() {
      try {
        // Replace this URL with your Spring Boot API endpoint
        const res = await fetch(`http://localhost:9010/orders/${id}`);

        if (!res.ok) {
          throw new Error("Failed to fetch order details");
        }

        const data = await res.json();

        // Ensure data matches expected structure
        // Example: { orderNumber, products, buyer, payment, invoice }
        setOrder(data);
      } catch (err) {
        console.error(err);
        setError("Unable to load order details");
      } finally {
        setLoading(false);
      }
    }

    fetchOrder();
  }, [id]);

  if (loading) return <div className="pt-16 px-8">Loading order details...</div>;
  if (error) return <div className="pt-16 px-8 text-red-500">{error}</div>;
  if (!order) return <div className="pt-16 px-8">Order not found.</div>;

  const subtotal = order.products?.reduce((sum, p) => sum + p.qty * p.price, 0) || 0;

  return (
    <>
      <Topbar title={`Order Details - ${order.orderNumber}`} />

      <div className="pt-0 px-0 max-w-6xl mx-auto">
        {/* Two-column layout */}
        <div className="flex flex-col lg:flex-row gap-6">
          {/* Left: Products */}
          <div className="flex-1 bg-white border rounded-xl p-6">
            <h2 className="font-semibold text-lg mb-4">Products</h2>

            <table className="w-full text-left text-sm">
              <thead className="bg-gray-100 text-gray-600">
                <tr>
                  <th className="px-6 py-3">Item</th>
                  <th className="px-6 py-3">Qty</th>
                  <th className="px-6 py-3">Price</th>
                </tr>
              </thead>
              <tbody>
                {order.products?.map((p, i) => (
                  <tr
                    key={i}
                    className={i % 2 === 0 ? "bg-gray-50" : "bg-white"}
                  >
                    <td className="px-6 py-4">{p.name}</td>
                    <td className="px-6 py-4">{p.qty}</td>
                    <td className="px-6 py-4">₹{p.price}</td>
                  </tr>
                ))}
                <tr className="border-t font-semibold">
                  <td className="px-6 py-4 text-right" colSpan={2}>
                    Subtotal:
                  </td>
                  <td className="px-6 py-4">₹{subtotal}</td>
                </tr>
              </tbody>
            </table>
          </div>

          {/* Right: Buyer, Payment, Invoice */}
          <div className="flex-1 flex flex-col gap-6">
            <div className="bg-white border rounded-xl p-6 space-y-2">
              {/* Buyer & Shipping */}
              <h2 className="font-semibold text-lg">Buyer & Shipping</h2>
              <p>{order.buyer?.name}</p>
              <p>{order.buyer?.phone}</p>
              <p>{order.buyer?.address}</p>

              {/* Payment */}
              <h2 className="font-semibold text-lg mt-4">Payment</h2>
              <p>
                {order.payment?.method} • {order.payment?.status}
              </p>

              {/* Invoice button */}
              <NavLink
                to={`/invoice/${order.orderNumber}`}
                className="inline-block mt-4 text-blue-600 hover:underline"
              >
                {order.invoice}
              </NavLink>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
