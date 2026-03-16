import React, { useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function UploadMedia() {
  const { id } = useParams();
  const navigate = useNavigate();
  const fileInputRef = useRef(null);

  const [mediaFile, setMediaFile] = useState(null);
  const [uploading, setUploading] = useState(false);

  // Select file
  const handleFile = (e) => {
    const file = e.target.files[0];
    if (!file) return;

    const preview = URL.createObjectURL(file);

    setMediaFile({
      file,
      preview,
      type: file.type.startsWith("video") ? "video" : "image",
    });
  };

  // Remove file
  const removeFile = () => {
    if (mediaFile) {
      URL.revokeObjectURL(mediaFile.preview);
      setMediaFile(null);
    }
  };

  // Upload file
  const handleUpload = async () => {
    if (!id) {
      alert("Product ID missing");
      return;
    }

    if (!mediaFile) {
      alert("Please select a file first");
      return;
    }

    const formData = new FormData();
    formData.append("file", mediaFile.file);
    formData.append("productId", id);

    try {
      setUploading(true);

      const response = await fetch(
        "http://localhost:9020/product-images",
        {
          method: "POST",
          body: formData,
        }
      );

      if (!response.ok) {
        throw new Error("Upload failed");
      }

      const data = await response.json();
      console.log("Upload success:", data);

      alert("Media uploaded successfully");

      navigate(`/products/preview/${id}`);

    } catch (error) {
      console.error("Upload error:", error);
      alert("Upload failed. Check console.");
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
              Upload an image or video for your product
            </p>
          </div>

          {/* Upload box */}
          <label className="border-dashed border-2 border-gray-300 rounded-lg p-6 text-center cursor-pointer hover:bg-gray-50 block">

            <input
              type="file"
              hidden
              accept="image/*,video/*"
              ref={fileInputRef}
              onChange={handleFile}
            />

            <p className="text-blue-600 font-medium">
              Click to upload media
            </p>

            <p className="text-xs text-gray-400">
              JPG, PNG, MP4 supported
            </p>

          </label>

          {/* Preview */}
          {mediaFile && (
            <div className="relative mt-4 w-48">

              {mediaFile.type === "image" ? (
                <img
                  src={mediaFile.preview}
                  alt="preview"
                  className="h-28 w-full object-cover rounded-lg border"
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

          {/* Buttons */}
          <div className="flex justify-between pt-4 border-t">

            <button
              onClick={handleUpload}
              disabled={uploading}
              className={`px-6 py-2 rounded-md text-white ${
                uploading
                  ? "bg-gray-400"
                  : "bg-blue-600 hover:bg-blue-700"
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