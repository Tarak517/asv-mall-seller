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

  const API_URL = "http://localhost:9020/api/products";
  const IMAGE_API = "http://localhost:9020/product-images/product/";

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

      console.log("Products API:", data);

      const mappedProducts = await Promise.all(

        data.map(async (p) => {

          let imageUrl = "";

          try {

            const imgRes = await fetch(`${IMAGE_API}${p.productId}`);

            if (imgRes.ok) {

              const images = await imgRes.json();

              if (images && images.length > 0) {

                imageUrl = images[0].url; // backend already returns full url

              }

            }

          } catch (err) {
            console.log("Image fetch error:", err);
          }

          return {
            id: p.productId,
            sku: p.sku || "-",
            title: p.name || "",
            price: p.price || 0,
            status: p.status || "DRAFT",

            // FIXED CATEGORY
            category: p.category?.name || p.categoryName || "Uncategorized",

            stock: p.stock ?? 0,
            image: imageUrl
          };

        })

      );

      setProducts(mappedProducts);

    } catch (err) {

      setError(err.message);
      setProducts([]);

    } finally {

      setLoading(false);

    }

  };

  const filteredProducts = products.filter((p) =>
    (p.title.toLowerCase().includes(filter.toLowerCase()) ||
      p.sku.toLowerCase().includes(filter.toLowerCase())) &&
    (status ? p.status === status : true) &&
    (category ? p.category === category : true)
  );

  return (

    <div className="p-4">

      <h2 className="text-xl font-bold mb-4">All Products</h2>

      {/* Filters */}

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
          <option value="ACTIVE">Active</option>
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

      {loading && <p className="text-gray-500">Loading products...</p>}

      {error && <p className="text-red-500">{error}</p>}

      {!loading && !error && (

        <div className="overflow-x-auto bg-white rounded-xl border">

          <table className="min-w-full text-sm">

            <thead className="bg-gray-100">

              <tr>

                <th className="px-4 py-3 text-left">Image</th>
                <th className="px-4 py-3 text-left">SKU</th>
                <th className="px-4 py-3 text-left">Title</th>
                <th className="px-4 py-3 text-left">Price</th>
                <th className="px-4 py-3 text-left">Status</th>
                <th className="px-4 py-3 text-left">Category</th>
                <th className="px-4 py-3 text-left">Stock</th>
                <th className="px-4 py-3 text-left">Actions</th>

              </tr>

            </thead>

            <tbody>

              {filteredProducts.length === 0 ? (

                <tr>
                  <td colSpan="8" className="text-center py-6 text-gray-500">
                    No products found
                  </td>
                </tr>

              ) : (

                filteredProducts.map((p) => (

                  <tr key={p.id} className="border-t hover:bg-gray-50">

                    <td className="px-4 py-2">

                      {p.image ? (

                        <img
                          src={p.image}
                          alt={p.title}
                          style={{
                            width: "50px",
                            height: "50px",
                            objectFit: "cover",
                            borderRadius: "6px"
                          }}
                        />

                      ) : (

                        <span className="text-gray-400">No Image</span>

                      )}

                    </td>

                    <td className="px-4 py-2">{p.sku}</td>
                    <td className="px-4 py-2">{p.title}</td>
                    <td className="px-4 py-2">₹{p.price}</td>
                    <td className="px-4 py-2">{p.status}</td>
                    <td className="px-4 py-2">{p.category}</td>
                    <td className="px-4 py-2 text-center">{p.stock}</td>

                    <td
                      className="px-4 py-2 text-blue-600 cursor-pointer"
                      onClick={() => navigate(`/products/edit/${p.id}`)}
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