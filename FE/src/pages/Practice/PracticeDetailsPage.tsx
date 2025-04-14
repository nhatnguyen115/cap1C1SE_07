import React from "react";
import { useLocation, useParams } from "react-router-dom";
import SectionDetailsComponent from "../../components/SectionDetailsComponent";

export const PracticeDetailsPage = () => {
  const { sectionId } = useParams<{ sectionId: string }>();
  const location = useLocation();
  const sectionName = location.state?.sectionName;
  const sections = location.state?.sections;

  return (
    <div>
      {sectionId && sectionName && sections ? (
        <SectionDetailsComponent
          sectionId={sectionId}
          sectionName={sectionName}
          sections={sections}
        />
      ) : (
        <div className="text-red-500">Invalid section</div>
      )}
    </div>
  );
};
