import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function Inventory() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [variants, setVariants] = useState([]);

  useEffect(() => {
    async function fetchInventory() {
      try {
        const res = await fetch(`/api/products/${id}/inventory`);
        const data = await res.json();
        setVariants(data?.variants || []);
      } catch (err) {
        console.error("Inventory fetch error", err);
      }
    }

    fetchInventory();
  }, [id]);

  return (
    <>
      <Topbar title="Inventory Management" />

      {/* MAIN CONTAINER */}
      <div className="pt-16 px-8 max-w-6xl mx-auto">
        {/* SUB TEXT */}
        <p className="text-gray-500 mb-4">
          Stock, variants, sizes, colors
        </p>

        {/* CARD */}
        <div className="bg-white border rounded-xl">
          <div className="px-6 py-4 border-b">
            <h2 className="text-lg font-semibold">Variants</h2>
          </div>

          {/* TABLE */}
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead className="bg-gray-50 text-gray-600 text-sm">
                <tr>
                  <th className="text-left px-6 py-3">Variant</th>
                  <th className="text-left px-6 py-3">Attributes</th>
                  <th className="text-left px-6 py-3">Stock</th>
                  <th className="text-left px-6 py-3">SKU</th>
                </tr>
              </thead>

              <tbody>
                {variants.length === 0 ? (
                  <tr>
                    <td
                      colSpan="4"
                      className="text-center py-10 text-gray-400"
                    >
                      No variants found
                    </td>
                  </tr>
                ) : (
                  variants.map((v, i) => (
                    <tr key={i} className="border-t text-sm">
                      <td className="px-6 py-4">
                        {v.name || `Variant ${i + 1}`}
                      </td>
                      <td className="px-6 py-4">{v.attributes}</td>
                      <td className="px-6 py-4">{v.stock}</td>
                      <td className="px-6 py-4">{v.sku}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </div>

        {/* FOOTER ACTIONS */}
        <div className="flex justify-between items-center mt-6">
          <button
            onClick={() => navigate(`/products/bulk-upload/${id}`)}
            className="text-blue-600 font-medium hover:underline"
          >
            Bulk Upload
          </button>

          <button className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700">
            Save Inventory
          </button>
        </div>
      </div>
    </>
  );
}
