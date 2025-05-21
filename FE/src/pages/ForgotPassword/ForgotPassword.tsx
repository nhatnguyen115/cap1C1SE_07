import { AxiosError } from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../../api/PathConstant";
import { httpNoAuth } from "../../service/Http";

const ForgotPassword: React.FC = () => {
  const [step, setStep] = useState<"EMAIL" | "OTP" | "RESET" | "SUCCESS">(
    "EMAIL",
  );
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const [countdown, setCountdown] = useState(3);

  const navigate = useNavigate();

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

  const handleSubmitEmail = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);
    setError(null);
    setLoading(true);

    try {
      const res = await httpNoAuth.post("/auth/request", null, {
        params: { email },
      });

      if (res.data?.status === 200) {
        setMessage("OTP đã được gửi về email của bạn.");
        setStep("OTP");
      } else {
        setError(res.data?.message || "Đã xảy ra lỗi.");
      }
    } catch (err) {
      const axiosErr = err as AxiosError<any>;
      setError(axiosErr.response?.data?.message || "Không thể gửi yêu cầu.");
    } finally {
      setLoading(false);
    }
  };

  const handleSubmitOtp = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);
    setError(null);
    setLoading(true);

    try {
      const res = await httpNoAuth.post("/auth/verify", { email, otp });

      if (res.data?.status === 200) {
        setMessage("OTP hợp lệ. Vui lòng nhập mật khẩu mới.");
        setStep("RESET");
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

  const handleSubmitReset = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);
    setError(null);

    if (newPassword !== confirmPassword) {
      setError("Mật khẩu không khớp.");
      return;
    }

    setLoading(true);

    try {
      const res = await httpNoAuth.post("/auth/reset", { email, newPassword });

      if (res.data?.status === 200) {
        setMessage("Mật khẩu đã được thay đổi thành công!");
        setStep("SUCCESS");
        setOtp("");
        setNewPassword("");
        setConfirmPassword("");
      } else {
        setError(res.data?.message || "Lỗi đặt lại mật khẩu.");
      }
    } catch (err) {
      const axiosErr = err as AxiosError<any>;
      setError(
        axiosErr.response?.data?.message || "Không thể đặt lại mật khẩu.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-blue-50">
      <div className="w-full max-w-md p-8 bg-white rounded-lg shadow-lg">
        <h2 className="text-2xl font-bold mb-6 text-center">Quên mật khẩu</h2>

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

        {step === "EMAIL" && (
          <form onSubmit={handleSubmitEmail} className="space-y-4">
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Nhập email"
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
              {loading ? "Đang gửi..." : "Gửi mã OTP"}
            </button>
          </form>
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

        {step === "RESET" && (
          <form onSubmit={handleSubmitReset} className="space-y-4">
            {/* Bỏ input email */}
            <input
              type="password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              placeholder="Mật khẩu mới"
              required
              className="w-full p-3 border rounded-lg"
            />
            <input
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              placeholder="Xác nhận mật khẩu"
              required
              className="w-full p-3 border rounded-lg"
            />
            <button
              type="submit"
              disabled={loading}
              className={`w-full py-3 text-white rounded-lg ${
                loading ? "bg-blue-300" : "bg-blue-600 hover:bg-blue-700"
              }`}
            >
              {loading ? "Đang đặt lại..." : "Đặt lại mật khẩu"}
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

export default ForgotPassword;
