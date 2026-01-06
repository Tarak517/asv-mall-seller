import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function EditProfile() {
  const navigate = useNavigate();

  const [profile, setProfile] = useState({
    businessName: "",
    email: "",
    phone: "",
    gst: "",
    address: "",
    storeTitle: "",
    theme: "light",
    banner: null,
    description: "",
  });

  useEffect(() => {
    // Fetch current profile from API here
    const fetchProfile = async () => {
      // Replace with your API
      const res = await fetch("/api/seller/profile");
      const data = await res.json();
      setProfile(data);
    };
    fetchProfile();
  }, []);

  const handleChange = (e) => {
    setProfile({ ...profile, [e.target.name]: e.target.value });
  };

  const handleBannerUpload = (e) => {
    setProfile({ ...profile, banner: e.target.files[0] });
  };

  const handleSave = async () => {
    // Call API to update profile
    const formData = new FormData();
    Object.entries(profile).forEach(([key, value]) => {
      formData.append(key, value);
    });

    await fetch("/api/seller/profile/update", {
      method: "POST",
      body: formData,
    });

    navigate("/settings/profile"); 
  };

  return (
    <>
      <Topbar title="Edit Profile" />

      <div className="pt-0 px-0 max-w-5xl mx-auto space-y-8">

        <div className="bg-white border rounded-xl p-6">
          <p className="text-sm text-gray-500 mb-6">Update your business & store info</p>

          {/* Banner */}
          <div className="mb-6">
            <p className="text-sm font-medium mb-1">Banner</p>
            <input type="file" accept="image/*" onChange={handleBannerUpload} />
            {profile.banner && (
              <img
                src={URL.createObjectURL(profile.banner)}
                alt="banner"
                className="w-full h-40 object-cover mt-2 rounded-md"
              />
            )}
          </div>

          {/* Fields */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
            <div>
              <label className="block text-sm font-medium mb-1">Business Name</label>
              <input
                type="text"
                name="businessName"
                value={profile.businessName}
                onChange={handleChange}
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Email</label>
              <input
                type="email"
                name="email"
                value={profile.email}
                onChange={handleChange}
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Phone</label>
              <input
                type="text"
                name="phone"
                value={profile.phone}
                onChange={handleChange}
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">GST</label>
              <input
                type="text"
                name="gst"
                value={profile.gst}
                onChange={handleChange}
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Store Title</label>
              <input
                type="text"
                name="storeTitle"
                value={profile.storeTitle}
                onChange={handleChange}
                className="w-full border rounded-md px-4 py-2"
              />
            </div>


            <div className="md:col-span-2">
              <label className="block text-sm font-medium mb-1">Address</label>
              <textarea
                name="address"
                rows="3"
                value={profile.address}
                onChange={handleChange}
                className="w-full border rounded-md px-4 py-2"
              />
            </div>

          </div>

          {/* Actions */}
          <div className="flex gap-4 mt-6 justify-end">
            <button
              onClick={handleSave}
              className="px-6 py-2 bg-[#0074C7] text-white rounded-md hover:bg-blue-700"
            >
              Save
            </button>
            <button
              onClick={() => navigate("/settings/profile")}
              className="px-6 py-2 border rounded-md hover:bg-gray-100"
            >
              Cancel
            </button>
          </div>

        </div>
      </div>
    </>
  );
}
