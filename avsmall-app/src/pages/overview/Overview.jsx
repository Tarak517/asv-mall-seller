import React from "react";
import { NavLink } from "react-router-dom";
import Topbar from "../../components/Topbar";

const screens = [
  { id: 1, name: "Splash Screen", path: "/splash" },
  { id: 2, name: "Welcome Screen", path: "/welcome" },
  { id: 3, name: "Login Screen", path: "/login" },
  { id: 4, name: "Register / Sign-Up", path: "/register" },
  { id: 5, name: "OTP Verification", path: "/otp" },
  { id: 6, name: "Forgot Password", path: "/forgot-password" },
  { id: 7, name: "Business Type Selection", path: "/business-type" },
  { id: 8, name: "KYC Verification", path: "/kyc" },
  { id: 9, name: "Seller Dashboard", path: "/dashboard" },
  { id: 10, name: "Sales Summary", path: "/sales-summary" },
  { id: 11, name: "Sales Trends", path: "/sales-trends" },
  { id: 12, name: "Notifications & Quick Actions", path: "/quick-actions" },
  { id: 13, name: "Navigation Menu", path: "/navigation" },
  { id: 14, name: "All Products", path: "/products" },
  { id: 15, name: "Add New Product", path: "/products/add" },
  { id: 16, name: "Upload Media", path: "/products/upload-media/1" },
  { id: 17, name: "Edit Product", path: "/products/edit/1" },
  { id: 18, name: "Product Preview", path: "/products/preview/1" },
  { id: 19, name: "Inventory Management", path: "/products/inventory/1" },
  { id: 20, name: "Bulk Upload Products", path: "/products/bulk-upload/1" },
  { id: 21, name: "Inactive / Rejected Products", path: "/products/rejected" },
  { id: 22, name: "All Orders", path: "/orders" },
  { id: 23, name: "Order Details", path: "/orders/1" },
  { id: 24, name: "Order Status Update", path: "/orders/1/status" },
  { id: 25, name: "Pickup Request", path: "/orders/1/pickup" },
  { id: 26, name: "Generate Invoice", path: "/generate-invoice/1" },
  { id: 27, name: "Return & Refund Requests", path: "/returns" },
  { id: 28, name: "Earnings Overview", path: "/earnings" },
  { id: 29, name: "Transaction History", path: "/transactions" },
  { id: 30, name: "Withdraw Funds", path: "/withdraw" },
  { id: 31, name: "Bank Account Setup", path: "/bank-setup" },
  { id: 32, name: "Commission & Fees", path: "/commission" },
  { id: 33, name: "Sales Analytics", path: "/analytics" },
  { id: 34, name: "Product Performance", path: "/analytics/products" },
  { id: 35, name: "Customer Insights", path: "/analytics/customers" },
  { id: 36, name: "Inventory Reports", path: "/analytics/inventory" },
  { id: 37, name: "Create Offer", path: "/offers/create" },
  { id: 38, name: "My Offers", path: "/offers" },
  { id: 39, name: "Promote Product", path: "/products/promote/1" },
  { id: 40, name: "Notifications", path: "/notifications" },
  { id: 41, name: "Customer Chat", path: "/chat/customers" },
  { id: 42, name: "Support Chat / Help Center", path: "/support" },
  { id: 43, name: "Seller Profile", path: "/profile" },
  { id: 44, name: "Edit Profile", path: "/profile/edit" },
  { id: 45, name: "Store Settings", path: "/settings/store" },
  { id: 46, name: "Shipping Settings", path: "/settings/shipping" },
  { id: 47, name: "Tax Settings", path: "/settings/tax" },
  { id: 48, name: "App Settings", path: "/settings/app" },
  { id: 49, name: "Privacy & Terms", path: "/privacy" },
];

export default function AllScreens() {
  return (
    <>
      <Topbar title="All Screens (49)" />

      <div className="pt-0 px-0 bg-gray-50 min-h-screen">
        <h1 className="text-2xl font-semibold mb-6">
          Seller App – Screen Overview
        </h1>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-3">
          {screens.map((screen) => (
            <NavLink
              key={screen.id}
              to={screen.path}
              className="flex items-center justify-between px-4 py-3
                         bg-white border rounded-md text-sm
                         hover:bg-[#0074C7] hover:text-white transition"
            >
              <span>
                {screen.id.toString().padStart(2, "0")}. {screen.name}
              </span>
              <span>→</span>
            </NavLink>
          ))}
        </div>
      </div>
    </>
  );
}
