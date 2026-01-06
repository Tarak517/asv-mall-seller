import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function AddNewProducts() {
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    title: "",
    category: "",
    price: "",
    sku: "",
    description: "",
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const API_URL = "http://localhost:9010/api/products";

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({ ...prev, [name]: value }));
  };

  // Save product (draft or published)
  const saveProduct = async (status, redirectToMedia = false) => {
    setLoading(true);
    setError("");

    try {
      const res = await fetch(API_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          
        },
        body: JSON.stringify({
          ...product,
          status, 
        }),
      });

      if (!res.ok) {
        throw new Error("Failed to save product");
      }

      const data = await res.json();

      if (redirectToMedia) {
        navigate(`/products/upload-media/${data.id}`);
      } else {
        alert(
          status === "DRAFT"
            ? "Draft saved successfully!"
            : "Product saved successfully!"
        );
      }
    } catch (err) {
      setError(err.message || "Something went wrong");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Topbar title="Add New Product" />

      <div className="flex justify-center w-full mt-4">
        <div className="w-full max-w-5xl bg-white border rounded-xl p-6 shadow-sm">
          <form className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              {/* LEFT side */}
              <div className="space-y-4">
                <input
                  name="title"
                  placeholder="Blue Cotton Shirt"
                  value={product.title}
                  onChange={handleChange}
                  className="w-full border p-2 rounded"
                />

                <input
                  name="price"
                  type="number"
                  placeholder="999"
                  value={product.price}
                  onChange={handleChange}
                  className="w-full border p-2 rounded"
                />

                <textarea
                  name="description"
                  placeholder="High quality cotton shirt..."
                  value={product.description}
                  onChange={handleChange}
                  rows={4}
                  className="w-full border p-2 rounded"
                />
              </div>

              {/* RIGHT side */}
              <div className="space-y-4">
                <select
                  name="category"
                  value={product.category}
                  onChange={handleChange}
                  className="w-full border p-2 rounded"
                >
                  <option value="">Select Category</option>
                  <option value="Clothing">Clothing</option>
                  <option value="Electronics">Electronics</option>
                  <option value="Accessories">Accessories</option>
                </select>

                <input
                  name="sku"
                  placeholder="SKU-1001"
                  value={product.sku}
                  onChange={handleChange}
                  className="w-full border p-2 rounded"
                />
              </div>
            </div>

            {/* ERROR */}
            {error && (
              <p className="text-red-500 text-sm">{error}</p>
            )}

            {/* ACTION BUTTONS */}
            <div className="flex gap-6 items-center mt-6">
              <button
                type="button"
                disabled={loading}
                onClick={() => saveProduct("DRAFT", true)}
                className="text-blue-600 font-medium hover:underline disabled:opacity-50"
              >
                {loading ? "Saving..." : "Next: Upload Media"}
              </button>

              <button
                type="button"
                disabled={loading}
                onClick={() => saveProduct("DRAFT")}
                className="border px-4 py-2 rounded disabled:opacity-50"
              >
                Save Draft
              </button>

              <button
                type="button"
                disabled={loading}
                onClick={() => saveProduct("PUBLISHED")}
                className="bg-blue-500 text-white px-4 py-2 rounded disabled:opacity-50"
              >
                Save Product
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
