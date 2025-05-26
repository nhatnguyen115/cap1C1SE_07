import { AxiosError } from "axios";
import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../../api/PathConstant";
import { API_URIS } from "../../api/URIConstant";
import { httpNoAuth } from "../../service/Http";

const VerifyAccountPage: React.FC = () => {
  const [step, setStep] = useState<"EMAIL" | "OTP" | "RESET" | "SUCCESS">(
    "EMAIL",
  );
  const [emailVerify, setEmailVerify] = useState<string>("");
  const [otp, setOtp] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const [countdown, setCountdown] = useState(3);

  const location = useLocation();
  const navigate = useNavigate();
  // Lấy query param từ URL
  const searchParams = new URLSearchParams(location.search);
  const email = searchParams.get("email");

  // Effect xử lý đếm ngược khi ở bước SUCCESS
  useEffect(() => {
    if (step === "SUCCESS") {
      if (countdown === 0) {
        navigate(PATH_CONSTANTS.AUTH.LOGIN);
        return;
      }
      const timer = setTimeout(() => {
        setCountdown(countdown - 1);
      }, 1000);
      return () => clearTimeout(timer);
    }
  }, [countdown, step, navigate]);

  useEffect(() => {
    setStep("OTP");
    setEmailVerify(String(email));
    console.log("email.: ", email);
  }, []);
  useEffect(() => {
    console.log("emailVerify.: ", emailVerify);
  }, [emailVerify]);

  const handleSubmitOtp = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);
    setError(null);
    setLoading(true);

    try {
      const res = await httpNoAuth.post(API_URIS.AUTH.VERIFY, { email, otp });

      if (res.data?.status === 200) {
        setMessage("OTP hợp lệ. Xác minh thành công");
        setStep("SUCCESS");
      } else {
        setError(res.data?.message || "OTP không hợp lệ.");
      }
    } catch (err) {
      const axiosErr = err as AxiosError<any>;
      setError(axiosErr.response?.data?.message || "Xác minh OTP thất bại.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-blue-50">
      <div className="w-full max-w-md p-8 bg-white rounded-lg shadow-lg">
        <h2 className="text-2xl font-bold mb-6 text-center">
          Xác nhận đăng ký tài khoản
        </h2>

        {message && (
          <div className="bg-green-100 text-green-700 p-3 mb-4 rounded flex flex-row items-center justify-center">
            {message}
          </div>
        )}
        {error && (
          <div className="bg-red-100 text-red-700 p-3 mb-4 rounded">
            {error}
          </div>
        )}

        {step === "OTP" && (
          <form onSubmit={handleSubmitOtp} className="space-y-4">
            {/* Bỏ input email */}
            <input
              type="text"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              placeholder="Nhập mã OTP"
              required
              className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
            />
            <button
              type="submit"
              disabled={loading}
              className={`w-full py-3 text-white rounded-lg ${
                loading ? "bg-blue-300" : "bg-blue-600 hover:bg-blue-700"
              }`}
            >
              {loading ? "Đang xác minh..." : "Xác minh OTP"}
            </button>
          </form>
        )}

        {step === "SUCCESS" && (
          <div className="text-center text-blue-700">
            Đang chuyển về trang đăng nhập sau{" "}
            <span className="font-bold">{countdown}</span> giây...
          </div>
        )}

        <div className="mt-6 text-center text-sm">
          <Link
            className="text-blue-600 hover:underline"
            to={PATH_CONSTANTS.AUTH.LOGIN}
          >
            Quay lại đăng nhập
          </Link>
        </div>
      </div>
    </div>
  );
};

export default VerifyAccountPage;
