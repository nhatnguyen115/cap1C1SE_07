import { MenuItem } from "../types/home";

export const mockMenuData: MenuItem[] = [
  {
    menuId: 1,
    label: "Luyện tập",
    url: "/practice",
    parentId: null,
    sort: 1,
    children: [
      {
        menuId: 2,
        label: "Toán",
        url: "/practice/math",
        parentId: 1,
        sort: 1,
      },
      {
        menuId: 3,
        label: "Văn",
        url: "/practice/literature",
        parentId: 1,
        sort: 2,
      },
    ],
  },
  {
    menuId: 4,
    label: "Thi thử",
    url: "/mock-test",
    parentId: null,
    sort: 2,
  },
  {
    menuId: 5,
    label: "Tài nguyên",
    url: "/resource",
    parentId: null,
    sort: 3,
  },
  {
    menuId: 6,
    label: "Premium",
    url: "/payment",
    parentId: null,
    sort: 4,
  },
];
