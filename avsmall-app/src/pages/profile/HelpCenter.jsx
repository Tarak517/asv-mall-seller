import React from "react";
import Topbar from "../../components/Topbar";

export default function HelpCenter() {
  return (
    <>
      <Topbar title="Support / Help Center" />

      <div className="pt-0 px-0 max-w-5xl mx-auto">
        <div className="bg-white border rounded-xl p-6">

          {/* Contact */}
          <p className="text-sm text-gray-500 mb-6">
            Contact admin support
          </p>

          {/* Help Topics */}
          <h2 className="text-lg font-semibold mb-3">Help Center</h2>
          <ul className="space-y-2 mb-8 text-blue-600 text-sm">
            <li className="cursor-pointer hover:underline">
              How to add products?
            </li>
            <li className="cursor-pointer hover:underline">
              How payouts work?
            </li>
            <li className="cursor-pointer hover:underline">
              How to handle returns?
            </li>
          </ul>

          {/* Raise Ticket */}
          <h2 className="text-lg font-semibold mb-4">
            Raise a Ticket
          </h2>

          <div className="space-y-4">

            <div>
              <label className="block text-sm font-medium mb-1">
                Subject
              </label>
              <input
                type="text"
                placeholder="Issue with payout"
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">
                Category
              </label>
              <select className="w-full border rounded-md px-4 py-2">
                <option>Payout</option>
                <option>Orders</option>
                <option>Returns</option>
                <option>Technical</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">
                Description
              </label>
              <textarea
                rows="4"
                placeholder="Explain your issue…"
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

            <button className="px-6 py-2 bg-[#0074C7] text-white rounded-md hover:bg-blue-700">
              Submit Ticket
            </button>

          </div>
        </div>
      </div>
    </>
  );
}
