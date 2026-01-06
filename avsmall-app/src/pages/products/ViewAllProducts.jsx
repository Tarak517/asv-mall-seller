import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function ViewAllProducts() {
  const navigate = useNavigate();

  const [products, setProducts] = useState([]);
  const [filter, setFilter] = useState("");
  const [status, setStatus] = useState("");
  const [category, setCategory] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const API_URL = "http://localhost:9010/api/products"; // 🔴 YOUR API

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    setLoading(true);
    setError("");

    try {
      const res = await fetch(API_URL);

      if (!res.ok) {
        throw new Error("Failed to fetch products");
      }

      const data = await res.json();

      // 🔹 Map backend DB structure → frontend table
      const mapped = (data.products || []).map((p) => ({
        id: p.id,                       // IMPORTANT
        sku: p.sku,
        title: p.title,
        price: p.price,
        status: p.status || "Active",
        category: p.categoryName || p.category || "Uncategorized",
        stock: p.stock ?? 0,
      }));

      setProducts(mapped);
    } catch (err) {
      setError(err.message);
      setProducts([]);
    } finally {
      setLoading(false);
    }
  };

  // 🔍 Filtering logic
  const filteredProducts = products.filter((p) =>
    (p.title.toLowerCase().includes(filter.toLowerCase()) ||
      p.sku.toLowerCase().includes(filter.toLowerCase())) &&
    (status ? p.status === status : true) &&
    (category ? p.category === category : true)
  );

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">All Products</h2>

      {/* FILTERS */}
      <div className="mb-6 flex flex-wrap gap-3">
        <input
          placeholder="Search Title / SKU"
          className="border px-3 py-2 rounded"
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
        />

        <select
          className="border px-3 py-2 rounded"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="">All Status</option>
          <option value="DRAFT">Draft</option>
          <option value="PUBLISHED">Published</option>
          <option value="INACTIVE">Inactive</option>
        </select>

        <select
          className="border px-3 py-2 rounded"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        >
          <option value="">All Categories</option>
          <option value="Clothing">Clothing</option>
          <option value="Electronics">Electronics</option>
          <option value="Accessories">Accessories</option>
        </select>
      </div>

      {/* STATES */}
      {loading && <p className="text-gray-500">Loading products...</p>}
      {error && <p className="text-red-500">{error}</p>}

      {/* TABLE */}
      {!loading && !error && (
        <div className="overflow-x-auto bg-white rounded-xl border">
          <table className="min-w-full text-sm">
            <thead className="bg-gray-100">
              <tr>
                {[
                  "SKU",
                  "Title",
                  "Price",
                  "Status",
                  "Category",
                  "Stock",
                  "Actions",
                ].map((h) => (
                  <th
                    key={h}
                    className="px-4 py-3 text-left font-semibold"
                  >
                    {h}
                  </th>
                ))}
              </tr>
            </thead>

            <tbody>
              {filteredProducts.length === 0 ? (
                <tr>
                  <td
                    colSpan="7"
                    className="px-4 py-6 text-center text-gray-500"
                  >
                    No products found
                  </td>
                </tr>
              ) : (
                filteredProducts.map((p) => (
                  <tr
                    key={p.id}
                    className="border-t hover:bg-gray-50"
                  >
                    <td className="px-4 py-2">{p.sku}</td>
                    <td className="px-4 py-2">{p.title}</td>
                    <td className="px-4 py-2">₹{p.price}</td>
                    <td className="px-4 py-2">{p.status}</td>
                    <td className="px-4 py-2">{p.category}</td>
                    <td className="px-4 py-2 text-center">{p.stock}</td>

                    <td
                      className="px-4 py-2 text-center text-blue-600 cursor-pointer"
                      onClick={() =>
                        navigate(`/products/edit/${p.id}`)
                      }
                    >
                      Edit
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
