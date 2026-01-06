import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

export default function EditProduct() {
  const { id } = useParams();
  const navigate = useNavigate();

  const API_BASE = "http://localhost:9010/api/products"; // 🔴 change if needed

  const [product, setProduct] = useState({
    title: "",
    status: "PUBLISHED",
    price: "",
    sku: "",
    description: "",
  });

  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  // 🔹 GET product by ID
  useEffect(() => {
    if (!id) return;

    const fetchProduct = async () => {
      try {
        const res = await fetch(`${API_BASE}/${id}`);

        if (!res.ok) {
          throw new Error("Product not found");
        }

        const data = await res.json();

        setProduct({
          title: data.title || "",
          status: data.status || "PUBLISHED",
          price: data.price || "",
          sku: data.sku || "",
          description: data.description || "",
        });
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  // 🔹 Input handler
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({ ...prev, [name]: value }));
  };

  // 🔹 PUT update product
  const handleSave = async () => {
    setSaving(true);
    setError("");

    try {
      const res = await fetch(`${API_BASE}/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          title: product.title,
          price: product.price,
          status: product.status,
          description: product.description,
        }),
      });

      if (!res.ok) {
        throw new Error("Failed to update product");
      }

      alert("Product updated successfully!");
      navigate("/products"); // back to list
    } catch (err) {
      setError(err.message);
    } finally {
      setSaving(false);
    }
  };

  // ❌ Invalid ID
  if (!id) {
    return (
      <div className="p-6 text-red-500">
        Invalid product ID.
      </div>
    );
  }

  // ⏳ Loading state
  if (loading) {
    return (
      <div className="p-6 text-gray-500">
        Loading product...
      </div>
    );
  }

  return (
    <div className="w-full max-w-5xl bg-white shadow-sm rounded-xl p-6 border border-gray-100">
      <h2 className="text-xl font-semibold mb-6">Edit Product</h2>

      {error && (
        <p className="text-red-500 mb-4">{error}</p>
      )}

      <form className="space-y-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* LEFT */}
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-1">
                Title
              </label>
              <input
                name="title"
                value={product.title}
                onChange={handleChange}
                className="w-full border rounded-md p-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">
                Price
              </label>
              <input
                name="price"
                type="number"
                value={product.price}
                onChange={handleChange}
                className="w-full border rounded-md p-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">
                Description
              </label>
              <textarea
                name="description"
                value={product.description}
                onChange={handleChange}
                rows={4}
                className="w-full border rounded-md p-2"
              />
            </div>
          </div>

          {/* RIGHT */}
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-1">
                Status
              </label>
              <select
                name="status"
                value={product.status}
                onChange={handleChange}
                className="w-full border rounded-md p-2"
              >
                <option value="DRAFT">Draft</option>
                <option value="PUBLISHED">Published</option>
                <option value="INACTIVE">Inactive</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">
                SKU
              </label>
              <input
                value={product.sku}
                disabled
                className="w-full border rounded-md p-2 bg-gray-100"
              />
            </div>
          </div>
        </div>

        {/* BUTTONS */}
        <div className="flex gap-4">
          <button
            type="button"
            disabled={saving}
            onClick={handleSave}
            className="bg-blue-500 text-white px-4 py-2 rounded disabled:opacity-50"
          >
            {saving ? "Updating..." : "Update Product"}
          </button>

          <button
            type="button"
            onClick={() => navigate("/products")}
            className="border px-4 py-2 rounded"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}
