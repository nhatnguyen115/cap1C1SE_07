export interface ExamType {
  id?: number;
  examName: string;
  totalScore: number;
  duration?: number;
  questionCount?: number;
  students?: number;
  level?: string;
  image?: string;
}

export type PartType = {
  id: number;
  partName: string;
  questionType: string;
  questionCount: number;
  description: string;
  instructions: string | null;
  mediaType?: string;
  url?: string;
};

export type QuestionType = {
  id: number;
  content: string;
  url: string;
  options: {
    A: string;
    B: string;
    C: string;
    D: string;
  };
  correctAnswer: "A" | "B" | "C" | "D";
  explanation: string | null;
  selectedAnswer?: string;
  mediaType?: string;
};

export type PartWithQuestionsType = {
  part: PartType;
  questions: QuestionType[];
};

export type DoExamType = {
  exam: ExamType;
  details: PartWithQuestionsType[];
};

export type ResultType = {
  id?: number;
  examName: string;
  duration?: number;
  totalScore: number;
  listeningScore: number;
  readingScore: number;
  correctCount: number;
  incorrectCount: number;
  skipCount: number;
}

export type ResultExamType = {
  exam: ResultType;
  details: PartWithQuestionsType[];
}

export type AnswerType = {
  questionId: number;
  selectedOption?: string;
};

export type TestNavigationProps = {
  isView: boolean;
  attemptId: number;
  details?: PartWithQuestionsType[];
  currentQuestion: number;
  answers: AnswerType[];
  duration?: number;
  onNavigate: (index: number) => void;
};

export type AttemptNavigationProps = {
  details?: PartWithQuestionsType[];
  currentQuestion: number;
  onNavigate: (index: number) => void;
}

export type ResultProps = {
  id:number;
  totalScore: number;
  listeningScore: number;
  readingScore: number;
  correctCount: number;
  incorrectCount: number;
  skipCount: number;
}