import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import Topbar from "../../components/Topbar";

export default function BulkUploadProducts() {
  const { id } = useParams();
  const fileRef = useRef(null);

  return (
    <>
      <Topbar title="Bulk Upload Products" />

      <div className="pt-0 px-0 max-w-0xl mx-auto">
        {/* SUB TEXT */}
        <p className="text-gray-500 mb-6">
          Upload CSV/XLSX
        </p>

        {/* CARD */}
        <div className="bg-white border rounded-xl p-0 space-y-0">
          {/* DROP ZONE */}
          <div
            onClick={() => fileRef.current.click()}
            className="border-2 border-dashed rounded-xl p-10 text-center cursor-pointer hover:border-blue-500 transition"
          >
            <p className="text-gray-600 text-lg">
              Drop CSV/XLSX here or click to upload
            </p>

            <input
              ref={fileRef}
              type="file"
              accept=".csv,.xlsx"
              hidden
            />
          </div>
        </div>
        {/* ACTION LINKS */}
          <div className=" pt-6 px-4 flex items-center gap-6">
            <a
              href="/templates/inventory-template.xlsx"
              download
              className="text-blue-600 hover:underline"
            >
              Download Template
            </a>

            <button className="border px-6 py-2 rounded-md hover:bg-gray-50">
              Validate
            </button>

            <button className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700">
              Import
            </button>
          </div>
      </div>
    </>
  );
}
