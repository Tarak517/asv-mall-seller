import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    businessName: "",
    ownerName: "",
    email: "",
    mobile: "",
    password: "",
    confirmPassword: "",
    businessType: "",
    gst: "",
    pan: "",
    agreeTerms: false,
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Simple validation
    if (!form.agreeTerms) {
      alert("You must agree to Terms & Privacy");
      return;
    }
    if (form.password !== form.confirmPassword) {
      alert("Passwords do not match");
      return;
    }
    // Here you can call API to register & verify KYC
    console.log("Form submitted", form);
    navigate("/login"); // redirect after registration
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
      <div className="bg-white p-8 rounded-xl shadow-md w-full max-w-md">
        <h1 className="text-2xl font-semibold mb-6 text-center">Seller Registration</h1>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1">Business Name</label>
            <input
              type="text"
              name="businessName"
              value={form.businessName}
              onChange={handleChange}
              placeholder="Acme Traders"
              className="w-full border rounded-md px-4 py-2"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Owner Name</label>
            <input
              type="text"
              name="ownerName"
              value={form.ownerName}
              onChange={handleChange}
              placeholder="Suresh Babu"
              className="w-full border rounded-md px-4 py-2"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Email</label>
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              placeholder="name@store.com"
              className="w-full border rounded-md px-4 py-2"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Mobile</label>
            <input
              type="tel"
              name="mobile"
              value={form.mobile}
              onChange={handleChange}
              placeholder="98xxxxxx12"
              className="w-full border rounded-md px-4 py-2"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Password</label>
            <input
              type="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              placeholder="••••••••"
              className="w-full border rounded-md px-4 py-2"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Confirm Password</label>
            <input
              type="password"
              name="confirmPassword"
              value={form.confirmPassword}
              onChange={handleChange}
              placeholder="••••••••"
              className="w-full border rounded-md px-4 py-2"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Business Type</label>
            <select
              name="businessType"
              value={form.businessType}
              onChange={handleChange}
              className="w-full border rounded-md px-4 py-2"
              required
            >
              <option value="">Select</option>
              <option value="Individual">Individual</option>
              <option value="Company">Company</option>
              <option value="Partnership">Partnership</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">GST (optional)</label>
            <input
              type="text"
              name="gst"
              value={form.gst}
              onChange={handleChange}
              placeholder="27AAACG1234Z1Z1"
              className="w-full border rounded-md px-4 py-2"
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">PAN (optional)</label>
            <input
              type="text"
              name="pan"
              value={form.pan}
              onChange={handleChange}
              placeholder="ABCDE1234F"
              className="w-full border rounded-md px-4 py-2"
            />
          </div>

          <div className="flex items-center gap-2">
            <input
              type="checkbox"
              name="agreeTerms"
              checked={form.agreeTerms}
              onChange={handleChange}
              className="h-4 w-4"
            />
            <label className="text-sm">
              I agree to <span className="text-blue-600 cursor-pointer">Terms & Privacy</span>
            </label>
          </div>

          <div className="flex justify-between gap-2">
            <button
              type="button"
              className="flex-1 px-4 py-2 border rounded-md hover:bg-gray-100"
            >
              Save Draft
            </button>
            <button
              type="submit"
              className="flex-1 px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
            >
              Register & Verify
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
