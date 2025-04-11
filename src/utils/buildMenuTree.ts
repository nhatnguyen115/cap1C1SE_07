import { MenuItem } from "../types/home";
export function buildMenuTree(menuItems: MenuItem[]): MenuItem[] {
  const menuMap: { [key: number]: MenuItem } = {};
  const tree: MenuItem[] = [];

  menuItems.forEach((item) => {
    menuMap[item.menuId] = { ...item, children: [] };
  });

  menuItems.forEach((item) => {
    if (item.parentId) {
      menuMap[item.parentId]?.children?.push(menuMap[item.menuId]);
    } else {
      tree.push(menuMap[item.menuId]);
    }
  });

  return tree.sort((a, b) => a.sort - b.sort);
}
