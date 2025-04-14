import React, { useEffect, useState } from "react";
import { CiUser } from "react-icons/ci";
import {
  FaHome,
  FaClipboardList,
  FaFileAlt,
  FaSearch,
  FaCrown,
} from "react-icons/fa"; // Import icons for each tab
import { Link, useLocation } from "react-router-dom";
import { MenuItem } from "../../types/home";
import { MenuNavComponent } from "../components/MenuNavComponent";
import { mockMenuData } from "../../data/MenuItemMock";
import { getMenu } from "./../../service/HomeService";

const Header: React.FC = () => {
  const location = useLocation();
  console.log(location);

  const [menus, setMenus] = useState<MenuItem[]>([]);

  useEffect(() => {
    const fetchMenu = async () => {
      try {
        const data = await getMenu(); // data: MenuItem[]
        setMenus(data);
      } catch (error) {
        console.error("Failed to fetch menu", error);
      }
    };

    fetchMenu();
  }, []);

  return (
    <header className="bg-white shadow-md px-4 flex items-center justify-between py-5">
      {/* Logo Section */}
      <Link to={"/"} className="flex items-center">
        <span className="text-orange-600 font-bold text-lg"></span>
      </Link>

      {/* Navigation Links */}
      <nav className="flex space-x-6">
        <MenuNavComponent menuItems={menus} />
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
