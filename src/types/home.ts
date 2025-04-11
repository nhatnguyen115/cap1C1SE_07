export type MenuItem = {
  id: number;
  menuCode: string;
  label: string;
  url: string;
  description: string | null;
  icon: string;
  children: MenuItem[];
};
