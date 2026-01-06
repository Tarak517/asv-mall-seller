import React, { useState } from "react";
import Topbar from "../../components/Topbar";

export default function Analytics() {
  const [activeTab, setActiveTab] = useState("sales");

  const tabClass = (tab) =>
    `pb-2 cursor-pointer ${
      activeTab === tab
        ? "border-b-2 border-blue-600 text-blue-600 font-medium"
        : "text-gray-500 hover:text-gray-700"
    }`;

  return (
    <>
      <Topbar title="Analytics" />

      <div className="pt-0 px-0 max-w-6xl mx-auto">

        {/* Tabs */}
        <div className="flex gap-6 border-b mb-6">
          <div className={tabClass("sales")} onClick={() => setActiveTab("sales")}>
            Sales
          </div>
          <div
            className={tabClass("inventory")}
            onClick={() => setActiveTab("inventory")}
          >
            Inventory
          </div>
          <div
            className={tabClass("orders")}
            onClick={() => setActiveTab("orders")}
          >
            Orders
          </div>
        </div>

        {/* SALES */}
        {activeTab === "sales" && (
          <div className="bg-white border rounded-xl p-6">
            <h2 className="font-semibold mb-4">Sales Analytics</h2>
            <p className="text-gray-600">
              Revenue trends, daily sales, monthly performance.
            </p>
          </div>
        )}

        {/* INVENTORY */}
        {activeTab === "inventory" && (
          <div className="bg-white border rounded-xl p-6">
            <h2 className="font-semibold mb-4">
              Low stock alerts, slow-moving items
            </h2>

            <table className="w-full border rounded-lg overflow-hidden">
              <thead className="bg-gray-50">
                <tr className="text-left">
                  <th className="p-3">SKU</th>
                  <th className="p-3">Name</th>
                  <th className="p-3">Stock</th>
                  <th className="p-3">Alert</th>
                </tr>
              </thead>
              <tbody>
                {[
                  { sku: "SKU-1000", name: "Item 0", stock: 11, alert: "Low" },
                  { sku: "SKU-1001", name: "Item 1", stock: 8, alert: "Low" },
                  { sku: "SKU-1002", name: "Item 2", stock: 1, alert: "OK" },
                  { sku: "SKU-1003", name: "Item 3", stock: 5, alert: "OK" },
                ].map((item) => (
                  <tr key={item.sku} className="border-t">
                    <td className="p-3">{item.sku}</td>
                    <td className="p-3">{item.name}</td>
                    <td className="p-3">{item.stock}</td>
                    <td className="p-3">
                      <span
                        className={`px-3 py-1 rounded-full text-sm ${
                          item.alert === "Low"
                            ? "bg-red-100 text-red-600"
                            : "bg-green-100 text-green-600"
                        }`}
                      >
                        {item.alert}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {/* ORDERS */}
        {activeTab === "orders" && (
          <div className="bg-white border rounded-xl p-6">
            <h2 className="font-semibold mb-4">Order Analytics</h2>
            <p className="text-gray-600">
              Order volume, delivery performance, cancellations.
            </p>
          </div>
        )}

      </div>
    </>
  );
}
