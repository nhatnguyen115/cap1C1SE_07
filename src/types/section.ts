export interface SectionType {
  id: number;
  sectionName: string;
  description: string;
  sectionType: string;
}

export interface SectionFormData {
  sectionName: string;
  description: string;
  sectionType: string;
  moduleId: string;
}
