import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function ResetPassword() {
  const navigate = useNavigate();
  const [contact, setContact] = useState("");
  const [method, setMethod] = useState("email");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!contact) {
      alert("Please enter your email or mobile");
      return;
    }

    // Example: redirect to OTP verification if mobile
    if (method === "mobile") {
      navigate("/otp-verification");
    } else {
      alert("Reset link sent to your email!");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
      <div className="bg-white p-8 rounded-xl shadow-md w-full max-w-md">
        <h1 className="text-2xl font-semibold mb-6 text-center">
          Reset Password
        </h1>
        <p className="text-sm text-gray-500 mb-6 text-center">
          Reset via Email / Mobile
        </p>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="flex justify-center gap-4 mb-4">
            <button
              type="button"
              onClick={() => setMethod("email")}
              className={`px-4 py-2 rounded-md border ${
                method === "email" ? "bg-blue-600 text-white" : "bg-white text-gray-700"
              }`}
            >
              Email
            </button>
            <button
              type="button"
              onClick={() => setMethod("mobile")}
              className={`px-4 py-2 rounded-md border ${
                method === "mobile" ? "bg-blue-600 text-white" : "bg-white text-gray-700"
              }`}
            >
              Mobile
            </button>
          </div>

          <input
            type="text"
            value={contact}
            onChange={(e) => setContact(e.target.value)}
            placeholder={method === "email" ? "Enter your email" : "Enter your mobile"}
            className="w-full border rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />

          <button
            type="submit"
            className="w-full px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
          >
            Send OTP / Link
          </button>
        </form>
      </div>
    </div>
  );
}
