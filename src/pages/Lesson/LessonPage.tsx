import React from "react";
import { useParams } from "react-router-dom";
import LessonDetailsComponent from "../../components/LessonDetailsComponent";

const LessonPage = () => {
  const { lessonId } = useParams();
  return lessonId ? (
    <LessonDetailsComponent lessonId={lessonId} />
  ) : (
    <p>Invalid lesson ID</p>
  );
};

export default LessonPage;
