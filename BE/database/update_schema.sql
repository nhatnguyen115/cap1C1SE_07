ALTER TABLE section_part RENAME TO section_exam;
ALTER TABLE section_exam RENAME COLUMN part_id TO exam_id;
DELETE FROM section_exam WHERE TRUE;