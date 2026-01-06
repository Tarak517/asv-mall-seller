import React, { useEffect, useState } from "react";

const InventoryReports = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchInventory() {
      try {
        const res = await fetch("http://localhost:9010/inventory"); // replace with your API
        if (!res.ok) throw new Error("Failed to fetch inventory");

        const result = await res.json();

        // Map API response to expected format
        const mappedData = result.map((item) => ({
          sku: item.sku,
          name: item.title,
          stock: item.stock,
          alert: item.stock < 5 ? "Low" : "OK",
        }));

        setData(mappedData);
      } catch (err) {
        console.error(err);
        setError("Unable to load inventory");
      } finally {
        setLoading(false);
      }
    }

    fetchInventory();
  }, []);

  if (loading) return <div>Loading inventory...</div>;
  if (error) return <div className="text-red-500">{error}</div>;

  return (
    <div>
      <p className="text-sm text-gray-500 mb-4">
        Low stock alerts, slow-moving items
      </p>

      <div className="overflow-x-auto bg-white border rounded-lg">
        <table className="w-full text-sm">
          <thead className="bg-gray-100 text-left">
            <tr>
              <th className="p-3">SKU</th>
              <th className="p-3">Name</th>
              <th className="p-3">Stock</th>
              <th className="p-3">Alert</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.sku} className="border-t">
                <td className="p-3">{item.sku}</td>
                <td className="p-3">{item.name}</td>
                <td className="p-3">{item.stock}</td>
                <td className="p-3">
                  <span
                    className={`px-2 py-1 rounded text-xs ${
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
    </div>
  );
};

export default InventoryReports;
