export interface LessonDetails {
  lessonName: string;
  contentType: string;
  articleText: string | null;
  duration: string | null;
}

export type LessonType = {
  id?: number; // id chỉ có khi edit
  lessonName: string;
  contentType: string;
  articleText: string;
  duration: number | null;
};
