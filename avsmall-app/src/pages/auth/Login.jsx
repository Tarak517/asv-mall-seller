import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Login() {
  const navigate = useNavigate();
  const [emailOrMobile, setEmailOrMobile] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();

    if (!emailOrMobile || !password) {
      setError("Please fill in all fields.");
      return;
    }

    // Dummy login check
    if (
      (emailOrMobile === "seller@example.com" ||
        emailOrMobile === "9999999999") &&
      password === "123456"
    ) {
      // ✅ IMPORTANT: set login flag
      localStorage.setItem("isLoggedIn", "true");

      // redirect to dashboard
      navigate("/dashboard", { replace: true });
    } else {
      setError("Invalid credentials");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="max-w-md w-full bg-white rounded-xl shadow-md p-8">
        <h1 className="text-2xl font-semibold mb-6 text-center">
          Login
        </h1>

        {error && (
          <div className="bg-red-100 text-red-700 p-2 rounded mb-4 text-sm">
            {error}
          </div>
        )}

        <form onSubmit={handleLogin} className="space-y-4">
          {/* Email / Mobile */}
          <div>
            <label className="block text-sm font-medium mb-1">
              Email / Mobile
            </label>
            <input
              type="text"
              placeholder="Email or Mobile"
              value={emailOrMobile}
              onChange={(e) => setEmailOrMobile(e.target.value)}
              className="w-full border rounded-md px-4 py-2"
            />
          </div>

          {/* Password */}
          <div>
            <label className="block text-sm font-medium mb-1">
              Password
            </label>
            <input
              type="password"
              placeholder="••••••••"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full border rounded-md px-4 py-2"
            />
          </div>

          {/* Forgot Password */}
          <div className="text-right">
            <Link
              to="/reset-password"
              className="text-sm text-blue-600 hover:underline"
            >
              Forgot Password?
            </Link>
          </div>

          {/* Login Button */}
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700"
          >
            Login
          </button>
        </form>

        {/* Register */}
        <p className="text-sm text-center mt-4">
          New seller?{" "}
          <Link
            to="/register"
            className="text-blue-600 hover:underline"
          >
            Create account
          </Link>
        </p>
      </div>
    </div>
  );
}
