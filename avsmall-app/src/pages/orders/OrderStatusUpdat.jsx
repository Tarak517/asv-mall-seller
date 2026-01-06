import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Topbar from "../../components/Topbar";

const STATUS_FLOW = ["PENDING", "ACCEPTED", "SHIPPED", "DELIVERED"];

export default function OrderStatusUpdate() {
  const { id } = useParams();

  const [currentStatus, setCurrentStatus] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // FETCH ORDER STATUS
  useEffect(() => {
    async function fetchOrder() {
      try {
        const res = await fetch(
          `http://localhost:8080/api/orders/${id}`
        );

        if (!res.ok) throw new Error("Failed to fetch order");

        const data = await res.json();
        setCurrentStatus(data.status);
      } catch (err) {
        console.error(err);
        setError("Unable to load order");
      } finally {
        setLoading(false);
      }
    }

    fetchOrder();
  }, [id]);

  // UPDATE STATUS
  const updateStatus = async (status) => {
    try {
      const res = await fetch(
        `http://localhost:9010/orders/${id}/status`,
        {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ status }),
        }
      );

      if (!res.ok) throw new Error("Update failed");

      setCurrentStatus(status);
      alert("Order status updated");
    } catch (err) {
      console.error(err);
      alert("Failed to update status");
    }
  };

  //  STATUS FLOW LOGIC
  const isEnabled = (status) => {
    return (
      STATUS_FLOW.indexOf(status) ===
      STATUS_FLOW.indexOf(currentStatus) + 1
    );
  };

  if (loading) {
    return (
      <>
        <Topbar title="Order Status Update" />
        <div className="pt-16 px-6">Loading...</div>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Topbar title="Order Status Update" />
        <div className="pt-16 px-6 text-red-500">{error}</div>
      </>
    );
  }

  return (
    <>
      <Topbar title={`Order Status Update - ${id}`} />

      <div className="pt-16 px-6 min-h-screen bg-gray-50">
        <h1 className="text-xl font-semibold mb-6">
          Update Order Status
        </h1>

        <div className="bg-white border rounded-xl p-6 max-w-xl">
          <p className="mb-4 text-sm">
            Current Status:{" "}
            <span className="font-semibold text-blue-600">
              {currentStatus}
            </span>
          </p>

          <div className="grid grid-cols-2 gap-4">
            {["ACCEPTED", "REJECTED", "SHIPPED", "DELIVERED"].map(
              (status) => (
                <button
                  key={status}
                  disabled={
                    status === currentStatus ||
                    (status !== "REJECTED" &&
                      !isEnabled(status))
                  }
                  onClick={() => updateStatus(status)}
                  className={`px-4 py-2 rounded-md border text-sm font-medium transition
                    ${
                      status === currentStatus
                        ? "bg-gray-200 cursor-not-allowed"
                        : "hover:bg-[#0074C7] hover:text-white"
                    }
                  `}
                >
                  {status}
                </button>
              )
            )}
          </div>
        </div>
      </div>
    </>
  );
}
