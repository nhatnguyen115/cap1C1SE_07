import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../../api/PathConstant";
import { LOCAL_STORAGE_CONSTANT } from "../../constant/LocalStorageConstant";
import { login } from "../../service/AuthService";
import { LoginType } from "../../types/auth";

const Login: React.FC = () => {
  // const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload: LoginType = {
      username,
      password,
    };

    try {
      const response = await login(payload); // Giả sử login() đã lưu token và role vào localStorage

      const role = localStorage.getItem(LOCAL_STORAGE_CONSTANT.ROLE); // 👈 Lấy role từ localStorage

      if (role === LOCAL_STORAGE_CONSTANT.ROLE_ADMIN) {
        navigate(PATH_CONSTANTS.ADMIN.ADMIN_DASHBOARD, { replace: true });
      } else {
        navigate(PATH_CONSTANTS.ROOT.ROOT);
      }
    } catch (error: any) {
      alert("Đăng nhập thất bại. Vui lòng thử lại.");
      console.error("Login error:", error);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen ">
      <div className="flex max-w-4xl bg-white  rounded-lg w-full overflow-hidden items-center">
        {/* Left Side - Welcome message and image */}
        <div className="flex-1 p-8 max-sm:hidden">
          <h2 className="text-xl font-bold text-gray-800">
            Welcome back, let's get ready!
          </h2>
          <div className="mt-4">
            <img
              src="src\assets\images\login-image.png"
              alt="illustration"
              className="w-full"
            />
          </div>
        </div>

        {/* Right Side - Login Form */}
        <div className="flex-1 p-8 bg-blue-100">
          <h2 className="text-3xl font-bold  mb-6">Đăng nhập</h2>
          <form onSubmit={handleLogin} className="space-y-4">
            <div>
              <label
                htmlFor="username"
                className="text-gray-700 text-sm font-medium"
              >
                Username
              </label>
              <input
                type="username"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Nhập username của bạn"
                className="w-full p-3 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
              />
            </div>
            <div>
              <label
                htmlFor="password"
                className="text-gray-700 text-sm font-medium"
              >
                Mật khẩu
              </label>
              <div className="relative">
                <input
                  type={showPassword ? "text" : "password"}
                  id="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Nhập mật khẩu"
                  className="w-full p-3 mt-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowPassword((prev) => !prev)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-600"
                >
                  {showPassword ? "Ẩn" : "Hiện"}
                </button>
              </div>
            </div>
            <div className="flex items-center justify-between mt-4">
              <label className="flex items-center text-sm text-gray-600">
                <input type="checkbox" className="mr-2" />
                Nhớ mật khẩu
              </label>
              <Link
                to={PATH_CONSTANTS.AUTH.RESET_PASSWORD}
                className="text-sm text-blue-600 hover:underline"
              >
                Quên mật khẩu?
              </Link>
            </div>
            <button
              type="submit"
              className="w-full py-3 mt-6 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
            >
              Đăng nhập
            </button>
          </form>
          <div className="mt-4 text-center">
            <p className="text-sm">
              Bạn chưa có tài khoản?{" "}
              <Link
                className="text-blue-600 hover:underline"
                to={PATH_CONSTANTS.AUTH.REGISTER}
              >
                Đăng ký
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
