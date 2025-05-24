ALTER TABLE section_part RENAME TO section_exam;
ALTER TABLE section_exam RENAME COLUMN part_id TO exam_id;
DELETE FROM section_exam WHERE TRUE;
ALTER TABLE user_login_data ALTER COLUMN email SET DATA TYPE VARCHAR(50);
CREATE TYPE public.test_type AS ENUM (
    'TEST',
    'MINITEST'
);
ALTER TABLE public.exam ADD test public."test_type" NOT NULL;