import React from "react";
import { useParams } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function Invoice() {
  const { orderId } = useParams();

  const invoice = {
    number: "INV-2025-00021",
    seller: {
      name: "GantaSoft Stores",
      gst: "36ABCDE1234F1Z5",
    },
    buyer: {
      name: "Ravi Kumar",
      address: "Hyderabad",
    },
    items: [
      { name: "Blue Shirt", qty: 2, price: 999 },
      { name: "Black Jeans", qty: 1, price: 1499 },
    ],
  };

  const total = invoice.items.reduce(
    (sum, i) => sum + i.qty * i.price,
    0
  );

  return (
    <>
      <Topbar title="Generate Invoice" />

      <div className="pt-0 px-0 max-w-5xl mx-auto">
        <div className="bg-white border rounded-xl p-8">

          {/* HEADER */}
          <div className="flex justify-between mb-6">
            <h2 className="font-semibold text-lg">
              Invoice #{invoice.number}
            </h2>

            <div className="space-x-3">
              <button className="border px-4 py-1 rounded">Print</button>
              <button className="bg-blue-600 text-white px-4 py-1 rounded">
                Download PDF
              </button>
            </div>
          </div>

          {/* SELLER & BUYER */}
          <div className="grid grid-cols-2 gap-10 mb-6">
            <div>
              <p className="font-semibold">Seller</p>
              <p>{invoice.seller.name}</p>
              <p>GST: {invoice.seller.gst}</p>
            </div>

            <div>
              <p className="font-semibold">Buyer</p>
              <p>{invoice.buyer.name}</p>
              <p>{invoice.buyer.address}</p>
            </div>
          </div>

          {/* ITEMS TABLE */}
          <table className="w-full text-sm border">
            <thead className="bg-gray-100">
              <tr>
                <th className="px-4 py-2">Item</th>
                <th className="px-4 py-2">Qty</th>
                <th className="px-4 py-2">Price</th>
                <th className="px-4 py-2">Total</th>
              </tr>
            </thead>
            <tbody>
              {invoice.items.map((i, idx) => (
                <tr key={idx}>
                  <td className="px-4 py-2">{i.name}</td>
                  <td className="px-4 py-2">{i.qty}</td>
                  <td className="px-4 py-2">₹{i.price}</td>
                  <td className="px-4 py-2">₹{i.qty * i.price}</td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="text-right mt-4 font-semibold">
            Grand Total: ₹{total}
          </div>
        </div>
      </div>
    </>
  );
}
