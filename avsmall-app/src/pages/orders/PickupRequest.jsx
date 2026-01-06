import React, { useEffect, useState } from "react";
import Topbar from "../../components/Topbar";

export default function PickupRequest({ orderId }) {
  const [data, setData] = useState({
    partner: "Shiprocket",
    date: "",
    time: "",
    notes: "",
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  // Fetch order info including pickup
  useEffect(() => {
    async function fetchOrder() {
      try {
        const res = await fetch(`http://localhost:8080/api/orders/${orderId}`);
        if (!res.ok) throw new Error("Failed to fetch order");
        const order = await res.json();

        // if order already has pickup, fill it
        setData(order.pickup || {
          partner: "Shiprocket",
          date: "",
          time: "",
          notes: "",
        });
      } catch (err) {
        console.error(err);
        setError("Unable to load order");
      } finally {
        setLoading(false);
      }
    }

    if (orderId) fetchOrder();
  }, [orderId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    setError("");
    setSuccess("");
    try {
      const res = await fetch(`http://localhost:9010/orders/${orderId}/pickup`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });

      if (!res.ok) throw new Error("Failed to submit pickup request");

      setSuccess("Pickup request submitted successfully!");
    } catch (err) {
      console.error(err);
      setError("Error submitting pickup request");
    }
  };

  if (loading) return <div className="pt-16 px-8">Loading order...</div>;
  if (error) return <div className="pt-16 px-8 text-red-500">{error}</div>;

  return (
    <>
      <Topbar title="Pickup Request" />

      <div className="pt-0 px-0">
        <p className="text-gray-600 mb-4">Request logistics partner</p>

        <div className="bg-white border rounded-xl p-8 min-h-[420px]">
          <div className="grid grid-cols-2 gap-x-32 gap-y-10">
            <div className="space-y-10">
              <div>
                <p className="text-sm text-gray-500">Pickup Date</p>
                <input
                  type="date"
                  name="date"
                  value={data.date}
                  onChange={handleChange}
                  className="border rounded-md p-2 mt-1 w-full"
                />
              </div>

              <div>
                <p className="text-sm text-gray-500">Notes</p>
                <input
                  type="text"
                  name="notes"
                  value={data.notes}
                  onChange={handleChange}
                  placeholder="Any special instructions"
                  className="border rounded-md p-2 mt-1 w-full"
                />
              </div>
            </div>

            <div className="space-y-10">
              <div>
                <p className="text-sm text-gray-500 mb-1">Logistics Partner</p>
                <select
                  name="partner"
                  value={data.partner}
                  onChange={handleChange}
                  className="border rounded-md p-2 w-full"
                >
                  <option>Shiprocket</option>
                  <option>Delhivery</option>
                  <option>Blue Dart</option>
                </select>
              </div>

              <div>
                <p className="text-sm text-gray-500">Pickup Time</p>
                <input
                  type="text"
                  name="time"
                  value={data.time}
                  onChange={handleChange}
                  placeholder="15:00 - 17:00"
                  className="border rounded-md p-2 mt-1 w-full"
                />
              </div>
            </div>
          </div>

          <div className="mt-10 flex flex-col gap-2">
            <button
              onClick={handleSubmit}
              className="bg-[#2F6FED] text-white px-4 py-2 hover:bg-blue-700 transition rounded-md w-48"
            >
              Request Pickup
            </button>
            {success && <p className="text-green-600">{success}</p>}
            {error && <p className="text-red-600">{error}</p>}
          </div>
        </div>
      </div>
    </>
  );
}
