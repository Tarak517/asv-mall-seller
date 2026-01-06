import React from "react";
import Topbar from "../../components/Topbar";

export default function PrivacyTerms() {
  return (
    <>
      <Topbar title="Privacy & Terms" />

      <div className="pt-0 px-0 max-w-5xl mx-auto">
        <div className="bg-white border rounded-xl p-6">

          <p className="text-sm text-gray-500 mb-6">
            Legal pages
          </p>

          {/* Privacy */}
          <h2 className="text-lg font-semibold mb-2">
            Privacy Policy
          </h2>
          <p className="text-sm text-gray-600 mb-6">
            We respect your privacy. Your data is securely stored and never
            shared with third parties without consent.
          </p>

          {/* Terms */}
          <h2 className="text-lg font-semibold mb-2">
            Terms of Service
          </h2>
          <p className="text-sm text-gray-600">
            By using this platform, you agree to follow our rules, policies,
            and applicable laws governing seller operations.
          </p>

        </div>
      </div>
    </>
  );
}
