import React from "react";
import { useLocation, useParams } from "react-router-dom";
import SectionDetailsComponent from "../../components/SectionDetailsComponent";

export const PracticeDetailsPage = () => {
  const { sectionId } = useParams<{ sectionId: string }>();
  const location = useLocation();
  const sectionName = location.state?.sectionName;

  return (
    <div>
      {sectionId && sectionName ? (
        <SectionDetailsComponent
          sectionId={sectionId}
          sectionName={sectionName}
        />
      ) : (
        <div className="text-red-500">Invalid section</div>
      )}
    </div>
  );
};
