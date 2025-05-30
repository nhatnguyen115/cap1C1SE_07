ALTER TABLE section_part
    RENAME TO section_exam;
ALTER TABLE section_exam
    RENAME COLUMN part_id TO exam_id;
DELETE
FROM section_exam
WHERE TRUE;
ALTER TABLE user_login_data
    ALTER COLUMN email SET DATA TYPE VARCHAR(50);
CREATE TYPE public.test_type AS ENUM (
    'TEST',
    'MINITEST'
    );
ALTER TABLE public.exam
    ADD test public."test_type" NOT NULL;

DROP SEQUENCE lesson_seq;
DROP TABLE lesson;
CREATE SEQUENCE grammar_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE vocabulary_word_seq START WITH 1 INCREMENT BY 50;
CREATE TABLE IF NOT EXISTS grammar
(
    grammar_id   int       default nextval('grammar_seq'),
    section_id   int          not null,
    grammar_name varchar(255) not null,
    grammar_text text,
    media_id     int,
    order_number int,
    created_at   timestamp default CURRENT_TIMESTAMP,
    created_by   uuid,
    updated_at   timestamp,
    updated_by   uuid
);

CREATE TABLE IF NOT EXISTS vocabulary_word
(
    word_id    int       default nextval('vocabulary_word_seq'),
    section_id int          not null,
    word       varchar(200) not null,
    meaning    text,
    created_at timestamp default CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp,
    updated_by uuid
);