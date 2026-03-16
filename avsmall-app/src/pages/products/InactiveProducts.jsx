import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function InactiveProducts() {
  const navigate = useNavigate();

  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const API_URL = "http://localhost:9020/api/products?status=INACTIVE"; 

  // Fetch inactive products
  useEffect(() => {
    const fetchInactiveProducts = async () => {
      try {
        const res = await fetch(API_URL);

        if (!res.ok) {
          throw new Error("Failed to fetch inactive products");
        }

        const data = await res.json();

        const mapped = (data.products || []).map((p) => ({
          id: p.id,                    
          sku: p.sku,
          title: p.title,
          reason: p.rejectionReason || "Not specified",
        }));

        setProducts(mapped);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchInactiveProducts();
  }, []);

  //  Search filter
  const filteredProducts = products.filter(
    (p) =>
      p.sku.toLowerCase().includes(search.toLowerCase()) ||
      p.title.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <>
      <Topbar title="Inactive / Rejected Products" />

      <div className="p-4 max-w-6xl mx-auto">
        {/* HEADER */}
        <div className="flex items-center justify-between mb-4">
          <p className="text-gray-500">
            Products that need correction before publishing
          </p>

          <input
            placeholder="Search SKU / Title"
            className="border px-3 py-2 rounded"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>

        {/* STATES */}
        {loading && (
          <p className="text-gray-500">Loading inactive products...</p>
        )}

        {error && (
          <p className="text-red-500">{error}</p>
        )}

        {/* TABLE */}
        {!loading && !error && (
          <div className="bg-white border rounded-xl overflow-hidden">
            <table className="w-full text-sm">
              <thead className="bg-gray-50 text-gray-600">
                <tr>
                  <th className="text-left px-6 py-3">SKU</th>
                  <th className="text-left px-6 py-3">Title</th>
                  <th className="text-left px-6 py-3">Reason</th>
                  <th className="text-left px-6 py-3">Action</th>
                </tr>
              </thead>

              <tbody>
                {filteredProducts.length === 0 ? (
                  <tr>
                    <td
                      colSpan="4"
                      className="text-center py-10 text-gray-400"
                    >
                      No inactive products found
                    </td>
                  </tr>
                ) : (
                  filteredProducts.map((p) => (
                    <tr
                      key={p.id}
                      className="border-t hover:bg-gray-50"
                    >
                      <td className="px-6 py-4">{p.sku}</td>
                      <td className="px-6 py-4">{p.title}</td>
                      <td className="px-6 py-4 text-red-500">
                        {p.reason}
                      </td>
                      <td className="px-6 py-4">
                        <button
                          onClick={() =>
                            navigate(`/products/edit/${p.id}`)
                          }
                          className="text-blue-600 font-medium hover:underline"
                        >
                          Resubmit
                        </button>
                      </td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </>
  );
}
