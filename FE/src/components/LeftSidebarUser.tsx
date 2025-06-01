// LeftSidebar.tsx
import React, { useEffect, useState } from "react";
import { FaCog, FaHistory, FaSignOutAlt } from "react-icons/fa";
import { Link } from "react-router-dom";
import { PATH_CONSTANTS } from "../api/PathConstant";
import { API_URIS } from "../api/URIConstant";
import { LOCAL_STORAGE_CONSTANT } from "../constant/LocalStorageConstant";
import { useUser } from "../context/UserContext";
import { http } from "../service/Http";
import { UserInfoType } from "../types/user";

interface LeftSidebarUserProps {
  customHeight?: string; // Cho phép truyền chiều cao tùy ý
}

const LeftSidebarUser: React.FC<LeftSidebarUserProps> = ({ customHeight }) => {
  const { setUserRole } = useUser();

  const fullName = localStorage.getItem(LOCAL_STORAGE_CONSTANT.FULL_NAME);
  const [user, setUser] = useState<UserInfoType | null>(null);

  function deleteAllCookies() {
    const cookies = document.cookie.split(";");

    for (let cookie of cookies) {
      const eqPos = cookie.indexOf("=");
      const name =
        eqPos > -1 ? cookie.substring(0, eqPos).trim() : cookie.trim();

      // Xoá cookie tại path hiện tại
      document.cookie =
        name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

      // Xoá thêm ở path gốc nếu cần
      document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    }
  }

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await http.get(API_URIS.USER.INFO);
        localStorage.setItem(
          LOCAL_STORAGE_CONSTANT.FULL_NAME,
          response.data.data.lastName + " " + response.data.data.firstName,
        );
        setUser(response.data.data);
      } catch (error) {
        console.error("Lỗi khi lấy thông tin người dùng:", error);
      }
    };

    fetchUser();
  }, []);
  const handleLogout = () => {
    localStorage.clear();
    deleteAllCookies();
    setUserRole(null);
  };
  return (
    <div
      className={`bg-gray-100 flex flex-col p-4 ${
        customHeight ? customHeight : "h-screen w-64 min-w-44"
      }`}
    >
      {/* Phần thông tin người dùng */}
      <div className="flex items-center mb-6">
        <div className="relative w-10 h-10 mr-3">
          <img
            src="src/assets/images/ai-image.png"
            alt="Avatar"
            className="w-10 h-10 rounded-full"
          />
          {user?.isVIP === 1 && (
            <img
              src="src/assets/crown1.png"
              alt="Crown"
              className="w-4 h-4 absolute -top-1 -right-1 rotate-[45deg]"
            />
          )}
        </div>
        <div>
          <h2 className="text-lg font-semibold">{fullName}</h2>
          <p className="text-sm text-gray-500 flex items-center">
            <span className="mr-1">🇻🇳</span> Vietnam
          </p>
        </div>
      </div>

      {/* Phần menu */}
      <nav className="flex-1">
        <ul>
          <li className="mb-2">
            <Link
              to={PATH_CONSTANTS.HISTORY.HISTORY}
              className="flex items-center p-2 text-gray-700 hover:bg-blue-100 rounded"
            >
              <FaHistory className="mr-3" />
              Lịch sử làm bài
            </Link>
          </li>
          <li className="mb-2">
            <Link
              to={PATH_CONSTANTS.SETTING.SETTING}
              className="flex items-center p-2 text-gray-700 hover:bg-blue-100 rounded"
            >
              <FaCog className="mr-3" />
              Cài đặt
            </Link>
          </li>
          <li className="mb-2">
            <Link
              to={PATH_CONSTANTS.ROOT.ROOT}
              onClick={handleLogout}
              className="flex items-center p-2 text-red-500 hover:bg-blue-100 rounded"
            >
              <FaSignOutAlt className="mr-3" />
              Đăng xuất
            </Link>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default LeftSidebarUser;
