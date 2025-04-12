import React from "react";
import { Link } from "react-router-dom";
import {
  FaTachometerAlt,
  FaUsers,
  FaQuestionCircle,
  FaFileAlt,
  FaSignOutAlt,
} from "react-icons/fa";
import { adminMenuItems } from "./data/adminMenuItems";

interface LeftSidebarAdminProps {
  customHeight?: string; // Cho phép truyền chiều cao tùy ý
}

const LeftSidebarAdmin: React.FC<LeftSidebarAdminProps> = ({
  customHeight,
}) => {
  const isActive = (url: string) => location.pathname === url;

  return (
    <div
      className={`bg-white flex flex-col p-4 ${
        customHeight ? customHeight : "h-screen w-64 min-w-44"
      }`}
    >
      {/* Phần thông tin người dùng */}
      <div className="flex items-center mb-6">
        <img
          src="./../src/assets/images/ai-image.png" // Thay ảnh avatar của Admin
          alt="Avatar"
          className="w-10 h-10 rounded-full mr-3"
        />
        <div>
          <h2 className="text-lg font-semibold">Socchuot</h2>
          <p className="text-sm text-gray-500 flex items-center">Admin</p>
        </div>
      </div>

      {/* Phần menu */}
      <nav className="flex-1">
        <ul>
          {adminMenuItems.map(({ label, path, icon: Icon }) => (
            <li key={path} className="mb-2">
              <Link
                to={path}
                className={`flex items-center px-2 py-1 rounded transition duration-200 transform ${
                  isActive(path)
                    ? "text-orange-600 text-2xl"
                    : "text-gray-600 hover:text-orange-500 hover:-translate-y-0.5"
                }`}
              >
                <Icon className="mr-3 text-base" />
                <span className="text-sm">{label}</span>
              </Link>
            </li>
          ))}
        </ul>
      </nav>
    </div>
  );
};

export default LeftSidebarAdmin;
