import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function OtpVerification() {
  const navigate = useNavigate();
  const [otp, setOtp] = useState(new Array(6).fill(""));
  const [resendTimer, setResendTimer] = useState(30);
  const [canResend, setCanResend] = useState(false);
  const inputsRef = useRef([]);

  // Countdown timer for resend
  useEffect(() => {
    if (resendTimer === 0) {
      setCanResend(true);
      return;
    }
    const timer = setTimeout(() => setResendTimer(resendTimer - 1), 1000);
    return () => clearTimeout(timer);
  }, [resendTimer]);

  const handleChange = (e, index) => {
    const value = e.target.value;
    if (!/^\d*$/.test(value)) return; // only digits

    const newOtp = [...otp];
    newOtp[index] = value;
    setOtp(newOtp);

    // Move focus
    if (value && index < 5) {
      inputsRef.current[index + 1].focus();
    }
  };

  const handleKeyDown = (e, index) => {
    if (e.key === "Backspace") {
      if (otp[index] === "" && index > 0) {
        inputsRef.current[index - 1].focus();
      }
    }
  };

  const handlePaste = (e) => {
    e.preventDefault();
    const pasteData = e.clipboardData
      .getData("text")
      .trim()
      .slice(0, 6)
      .split("");
    const newOtp = [...otp];
    for (let i = 0; i < pasteData.length; i++) {
      if (/^\d$/.test(pasteData[i])) {
        newOtp[i] = pasteData[i];
      }
    }
    setOtp(newOtp);
    // Focus next empty input
    const nextEmpty = newOtp.findIndex((v) => v === "");
    if (nextEmpty !== -1) {
      inputsRef.current[nextEmpty].focus();
    }
  };

  const handleVerify = (e) => {
    e.preventDefault();
    const otpString = otp.join("");
    if (otpString.length !== 6) {
      alert("Please enter all 6 digits");
      return;
    }
    console.log("OTP Verified:", otpString);
    navigate("/login"); // Redirect after success
  };

  const handleResend = () => {
    setOtp(new Array(6).fill(""));
    setResendTimer(30);
    setCanResend(false);
    inputsRef.current[0].focus();
    console.log("OTP Resent");
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
      <div className="bg-white p-8 rounded-xl shadow-md w-full max-w-md">
        <h1 className="text-2xl font-semibold mb-6 text-center">
          OTP Verification
        </h1>

        <p className="text-sm text-gray-500 mb-6 text-center">
          Enter the 6-digit OTP sent to your email/mobile
        </p>

        <form onSubmit={handleVerify} className="space-y-6">
          <div className="flex justify-between gap-2">
            {otp.map((digit, index) => (
              <input
                key={index}
                type="text"
                value={digit}
                onChange={(e) => handleChange(e, index)}
                onKeyDown={(e) => handleKeyDown(e, index)}
                onPaste={handlePaste}
                ref={(el) => (inputsRef.current[index] = el)}
                className="w-12 h-12 border rounded-md text-center text-lg font-medium focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            ))}
          </div>

          <div className="flex justify-between items-center text-sm">
            <button
              type="button"
              disabled={!canResend}
              onClick={handleResend}
              className={`text-blue-600 hover:underline ${
                !canResend ? "opacity-50 cursor-not-allowed" : ""
              }`}
            >
              {canResend ? "Resend" : `Resend (${resendTimer}s)`}
            </button>
          </div>

          <button
            type="submit"
            className="w-full px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
          >
            Verify
          </button>
        </form>
      </div>
    </div>
  );
}
