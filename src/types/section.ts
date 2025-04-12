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

export interface Lesson {
  id: number;
  lessonName: string;
  contentType: string;
  duration: number | null;
}

export interface Part {
  partId: number;
  partName: string;
  questionType: string;
  questionCount: number;
}
