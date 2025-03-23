import React from "react";
import { CiUser } from "react-icons/ci";
import { FaHome, FaClipboardList, FaFileAlt, FaSearch } from "react-icons/fa"; // Import icons for each tab
import { Link } from "react-router-dom";

const Header: React.FC = () => {
  return (
    <header className="bg-white shadow-md px-4 flex items-center justify-between py-5">
      {/* Logo Section */}
      <Link to={"/"} className="flex items-center">
        <span className="text-blue-600 font-bold text-lg">LOGO</span>
      </Link>

      {/* Navigation Links */}
      <nav className="flex space-x-6">
        {/* Desktop Links */}
        <Link to={"/"} className="hidden sm:block text-gray-600">
          Trang chủ
        </Link>
        <Link to={"/practice"} className="hidden sm:block text-gray-600">
          Luyện tập
        </Link>
        <Link to={"/mock-test"} className="hidden sm:block text-gray-600">
          Thi thử
        </Link>
        <Link to={"/resource"} className="hidden sm:block text-gray-600">
          Tài nguyên
        </Link>

        {/* Mobile Icons */}
        <Link to={"/"} className="sm:hidden text-gray-600">
          <FaHome className="text-xl" />
        </Link>
        <Link to={"/practice"} className="sm:hidden text-gray-600">
          <FaClipboardList className="text-xl" />
        </Link>
        <Link to={"/mock-test"} className="sm:hidden text-gray-600">
          <FaFileAlt className="text-xl" />
        </Link>
        <Link to={"/resource"} className="sm:hidden text-gray-600">
          <FaSearch className="text-xl" />
        </Link>
      </nav>

      {/* Login Button */}
      <div className="flex items-center space-x-4">
        <Link to={"/settings"}>
          <CiUser size={20} />
        </Link>

        <Link
          to={"/login"}
          className="bg-blue-600 text-white px-4 py-2 rounded-full"
        >
          Login
        </Link>
      </div>
    </header>
  );
};

export default Header;
