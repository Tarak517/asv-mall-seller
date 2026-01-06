import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function SellerProfile() {
  const navigate = useNavigate();

  const [profile, setProfile] = useState({
    businessName: "GantaSoft Store",
    email: "contact@gantasoft.in",
    phone: "9030942852",
    gst: "36ABCDE1234F1Z5",
    address: "Madhapur, Hyderabad",
    storeTitle: "GantaSoft Store",
    theme: "light",
    banner: null,
    description: "We sell amazing software products",
  });

  useEffect(() => {
    // Fetch seller profile from API here
    console.log("Fetch seller profile and store info");
  }, []);

  return (
    <>
      <Topbar title="Seller Profile" />

      <div className="pt-0 px-0 max-w-5xl mx-auto space-y-8">

        {/* Profile Section */}
        <div className="bg-white border rounded-xl p-6">
          <h2 className="text-lg font-semibold mb-1">Seller Profile</h2>
          <p className="text-sm text-gray-500 mb-6">Business & contact information</p>

          <div className="flex items-center gap-6 mb-8">
            <div className="w-24 h-24 rounded-full bg-blue-600 flex items-center justify-center text-white text-xl font-semibold">
              {profile.businessName.split(" ").map(n => n[0]).join("")}
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-5 text-gray-700">
            <div>
              <p className="text-sm font-medium">Business Name</p>
              <p>{profile.businessName}</p>
            </div>
            <div>
              <p className="text-sm font-medium">Email</p>
              <p>{profile.email}</p>
            </div>
            <div>
              <p className="text-sm font-medium">Phone</p>
              <p>{profile.phone}</p>
            </div>
            <div>
              <p className="text-sm font-medium">GST</p>
              <p>{profile.gst}</p>
            </div>
            <div className="md:col-span-2">
              <p className="text-sm font-medium">Address</p>
              <p>{profile.address}</p>
            </div>
          </div>
        </div>

        {/* Edit Button */}
        <div className="flex justify-end">
          <button
            onClick={() => navigate("/settings/edit-profile")}
            className="px-6 py-2 bg-[#0074C7] text-white rounded-md hover:bg-blue-700"
          >
            Edit Profile
          </button>
        </div>

      </div>
    </>
  );
}
