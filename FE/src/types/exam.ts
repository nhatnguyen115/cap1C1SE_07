export interface ExamType {
  id?: number;
  testId?: number;
  examName: string;
  totalScore: number;
  testType?: string;
  duration: number;
}

export interface QuestionType {
  id: number;
  content: string;
  url: string;
  options: { [key: string]: string };
  correctAnswer: string;
}

export interface PartDetailType {
  part: {
    id: number;
    partName: string;
    description: string;
  };
  questions: QuestionType[];
}
