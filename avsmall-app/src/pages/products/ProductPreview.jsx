import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import Topbar from "../../components/Topbar";

export default function ProductPreview() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState(null);
  const [saving, setSaving] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    axios
      .get(`http://localhost:9010/api/products/${id}`, {
        auth: {
          username: "user",
          password: "b804e35c-5a21-4a43-a8ff-0c947f278a5d",
        },
      })
      .then((res) => setProduct(res.data))
      .catch(() => setProduct(null))
      .finally(() => setLoading(false));
  }, [id]);

  const saveProduct = async () => {
    try {
      setSaving(true);

      await axios.put(
        `http://localhost:9010/api/products/${id}`,
        product,
        {
          auth: {
            username: "user",
            password: "b804e35c-5a21-4a43-a8ff-0c947f278a5d",
          },
        }
      );

      alert("Product saved successfully");
      navigate("/products");
    } catch (err) {
      alert("Save failed");
    } finally {
      setSaving(false);
    }
  };

  if (loading) {
    return (
      <>
        <Topbar title="Product Preview" />
        <p className="mt-24 text-center text-gray-500">Loading product…</p>
      </>
    );
  }

  if (!product) {
    return (
      <>
        <Topbar title="Product Preview" />
        <p className="mt-24 text-center text-red-500">Product not found</p>
      </>
    );
  }

  return (
    <>
      <Topbar title="Product Preview" />

      <div className="max-w-6xl mx-auto mt-20 grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* LEFT: MEDIA */}
        <div className="bg-white border rounded-xl p-6 flex items-center justify-center h-[420px]">
          {product.media?.length ? (
            <img
              src={product.media[0]}
              alt=""
              className="h-full w-full object-contain"
            />
          ) : (
            <span className="text-gray-400">No media uploaded</span>
          )}
        </div>

        {/* RIGHT: DETAILS */}
        <div className="bg-white border rounded-xl p-6 space-y-4">
          <h1 className="text-2xl font-semibold">{product.name}</h1>

          <p className="text-lg text-blue-600 font-medium">
            ₹{product.price || "—"}
          </p>

          <p className="text-gray-600 text-sm">
            {product.description || "No description"}
          </p>

          <div className="flex gap-4 pt-6">
            <button
              onClick={() => navigate(`/products/edit/${id}`)}
              className="border px-5 py-2 rounded-md hover:bg-gray-100"
            >
              Edit
            </button>

            <button
              onClick={saveProduct}
              disabled={saving}
              className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700 disabled:opacity-50"
            >
              {saving ? "Saving..." : "Save"}
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
