export interface MenuItem {
  menuId: number;
  label: string;
  url: string;
  parentId: number | null;
  sort: number;
  children?: MenuItem[];
}
