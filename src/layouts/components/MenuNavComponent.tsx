import { Link, useLocation } from "react-router-dom";
import { MenuItem } from "../../types/home";
import { useState } from "react";
import React from "react";
import { http } from "../../service/Http";
import { API_URIS } from "../../api/URIConstant";
import { SectionType } from "../../types/section";
import { sectionMockData } from "../../data/sectionMockData";

type Props = {
  menuItems: MenuItem[];
};

export const MenuNavComponent = ({ menuItems }: Props) => {
  const location = useLocation();
  const [openDropdown, setOpenDropdown] = useState<number | null>(null);
  const [section, setSection] = useState<SectionType[]>([]);

  const isActive = (item: MenuItem): boolean => {
    if (location.pathname === item.url) return true;
    if (item.children) {
      return item.children.some((child) => location.pathname === child.url);
    }
    return false;
  };

  const fetchSections = async (moduleId: number | string) => {
    try {
      const response = await http.get(
        API_URIS.SECTION.GET_ALL_BY_MODULE(moduleId),
      );
      if (response.status === 200) {
        setSection(response.data.data.items);
        // console.log("response.data.data.items", response.data.data.items);
        // return response.data.data.items;
      }
    } catch (error) {
      console.error("Lỗi khi tải dữ liệu sections:", error);
      setSection(sectionMockData);
    }
  };

  return (
    <nav className="flex space-x-6 relative">
      {menuItems.map((item) => {
        const hasChildren = item.children && item.children.length > 0;

        return (
          <div
            key={item.id}
            className="relative"
            onMouseEnter={() => setOpenDropdown(item.id)}
            onMouseLeave={() => setOpenDropdown(null)}
          >
            {/* Desktop parent */}
            <Link
              to={item.url.replace("{id}", String(item.itemId))}
              state={{ moduleId: item.itemId }}
              className={`hidden sm:flex items-center gap-1 px-2 py-1 rounded transition duration-200 transform ${
                isActive(item)
                  ? "text-orange-600 text-xl"
                  : "text-gray-600 hover:text-orange-500 hover:-translate-y-0.5"
              }`}
            >
              <span>{item.label}</span>
              {hasChildren && <span className="text-xs">▼</span>}
            </Link>

            {/* Dropdown (desktop only) */}
            {hasChildren && openDropdown === item.id && (
              <div className="absolute left-0 top-full bg-white border rounded shadow-md z-50 min-w-[180px] sm:block">
                {item.children?.map((child) => (
                  <Link
                    onClick={() => fetchSections(item.itemId)} // Pass the function reference here
                    key={child.id}
                    to={{
                      pathname: child.url.replace("{id}", String(child.itemId)),
                    }}
                    state={{
                      sectionName: child.label,
                      sections: section,
                    }}
                    className={`block px-4 py-2 transition duration-150 ${
                      isActive(child)
                        ? "text-orange-600 bg-gray-100"
                        : "text-gray-700 hover:bg-gray-100"
                    }`}
                  >
                    {child.label}
                  </Link>
                ))}
              </div>
            )}

            {/* Mobile menu */}
            <Link
              to={item.url}
              className={`sm:hidden block px-2 py-1 ${
                isActive(item) ? "text-orange-600" : "text-gray-600"
              }`}
            >
              {item.label}
            </Link>
          </div>
        );
      })}
    </nav>
  );
};
