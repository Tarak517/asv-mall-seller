import React, { useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function UploadMedia() {
  const { id } = useParams(); // product id from URL
  const navigate = useNavigate();
  const fileInputRef = useRef(null);

  const [mediaFile, setMediaFile] = useState(null); // only one file
  const [uploading, setUploading] = useState(false);

  // Handle file selection (single file)
  const handleFile = (e) => {
    const file = e.target.files[0];
    if (!file) return;

    setMediaFile({
      file,
      preview: URL.createObjectURL(file),
      type: file.type.startsWith("video") ? "video" : "image",
    });
  };

  // Remove selected file
  const removeFile = () => {
    if (mediaFile) {
      URL.revokeObjectURL(mediaFile.preview);
      setMediaFile(null);
    }
  };

  // Upload media to server
  const handleUpload = async () => {
    if (!id) return alert("Product ID missing!");
    if (!mediaFile) return alert("Select a media file first");

    const formData = new FormData();
    formData.append("file", mediaFile.file); // backend expects 'file'
    formData.append("productId", id);

    try {
      setUploading(true);

      const res = await fetch("http://localhost:9010/media-assets", {
        method: "POST",
        body: formData, // do NOT set Content-Type manually
      });

      const text = await res.text();
      console.log("Upload response:", text);

      if (!res.ok) throw new Error("Upload failed");

      navigate(`/products/preview/${id}`);
    } catch (e) {
      console.error(e);
      alert("Upload failed. Check console for details.");
    } finally {
      setUploading(false);
    }
  };

  return (
    <>
      <Topbar title="Upload Product Media" />

      <div className="max-w-6xl mx-auto mt-20 space-y-6">
        <div className="bg-white border rounded-xl p-6 space-y-6">
          <div>
            <h2 className="text-lg font-semibold">Product Media</h2>
            <p className="text-sm text-gray-500">
              Upload an image or video that represents your product
            </p>
          </div>

          {/* File input */}
          <label className="border-dashed border-2 border-gray-300 rounded-lg p-6 text-center cursor-pointer hover:bg-gray-50 block">
            <input
              type="file"
              hidden
              accept="image/*,video/*"
              ref={fileInputRef}
              onChange={handleFile}
            />
            <p className="text-blue-600 font-medium">Click to upload media</p>
            <p className="text-xs text-gray-400">JPG, PNG, MP4 supported</p>
          </label>

          {/* Preview selected media */}
          {mediaFile && (
            <div className="relative mt-4 w-48">
              {mediaFile.type === "image" ? (
                <img
                  src={mediaFile.preview}
                  className="h-28 w-full object-cover rounded-lg border"
                  alt="preview"
                />
              ) : (
                <video
                  src={mediaFile.preview}
                  controls
                  className="h-28 w-full object-cover rounded-lg border"
                />
              )}
              <button
                onClick={removeFile}
                className="absolute top-1 right-1 bg-black/70 text-white w-6 h-6 rounded-full"
              >
                ×
              </button>
            </div>
          )}

          {/* Action buttons */}
          <div className="flex justify-between pt-4 border-t">
            <button
              onClick={handleUpload}
              disabled={uploading}
              className={`px-6 py-2 rounded-md text-white ${
                uploading ? "bg-gray-400" : "bg-blue-600 hover:bg-blue-700"
              }`}
            >
              {uploading ? "Uploading..." : "Save & Continue"}
            </button>

            <button
              onClick={() => navigate(`/products/preview/${id}`)}
              className="text-blue-600 hover:underline"
            >
              Preview Product
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
