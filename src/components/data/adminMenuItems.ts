import {
  FaTachometerAlt,
  FaUsers,
  FaFileAlt,
  FaSignOutAlt,
} from "react-icons/fa";
import { AdminNavItemType } from "../../types/admin";

export const adminMenuItems: AdminNavItemType[] = [
  {
    label: "Thống kê",
    path: "/admin/dashboard",
    icon: FaTachometerAlt,
  },
  {
    label: "Người dùng",
    path: "/admin/usermanagement",
    icon: FaUsers,
  },
  {
    label: "Đề thi",
    path: "/admin/testmanagement",
    icon: FaFileAlt,
  },
  {
    label: "Đăng xuất",
    path: "/logout",
    icon: FaSignOutAlt,
  },
];
