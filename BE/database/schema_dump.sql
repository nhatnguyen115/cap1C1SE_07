--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: content_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.content_type AS ENUM (
    'VIDEO',
    'TEXT'
);


--
-- Name: difficulty_level; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.difficulty_level AS ENUM (
    'BEGINNER',
    'INTERMEDIATE',
    'ADVANCED'
);



--
-- Name: gender_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.gender_type AS ENUM (
    'MALE',
    'FEMALE',
    'OTHER'
);



--
-- Name: grading_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.grading_type AS ENUM (
    'LISTENING',
    'READING',
    'OTHER'
);



--
-- Name: media_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.media_type AS ENUM (
    'AUDIO',
    'VIDEO',
    'IMAGE'
);



--
-- Name: question_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.question_type AS ENUM (
    'MULTIPLE_CHOICE',
    'IMAGE_BASED',
    'AUDIO_BASED',
    'FILL_IN_BLANK',
    'ESSAY'
);



--
-- Name: resource_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.resource_type AS ENUM (
    'FREE',
    'MEMBER'
);



--
-- Name: role_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.role_type AS ENUM (
    'USER',
    'ADMIN'
);



--
-- Name: section_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.section_type AS ENUM (
    'LISTENING',
    'READING',
    'SPEAKING',
    'WRITING',
    'GRAMMAR',
    'VOCABULARY'
);



--
-- Name: status_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.status_type AS ENUM (
    'ACTIVE',
    'EXPIRED',
    'CANCELLED',
    'PENDING'
);



--
-- Name: exam_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.exam_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: exam; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam (
    exam_id integer DEFAULT nextval('public.exam_seq'::regclass) NOT NULL,
    exam_name character varying(255),
    total_score smallint,
    duration smallint,
    level public.difficulty_level DEFAULT 'BEGINNER'::public.difficulty_level,
    question_count smallint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: exam_structure; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam_structure (
    exam_id integer NOT NULL,
    part_id integer NOT NULL
);



--
-- Name: external_provider_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.external_provider_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: external_provider; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.external_provider (
    provider_id integer DEFAULT nextval('public.external_provider_seq'::regclass) NOT NULL,
    provider_name character varying(20),
    ws_endpoint character varying(50),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: lesson_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lesson_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: lesson; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lesson (
    lesson_id integer DEFAULT nextval('public.lesson_seq'::regclass) NOT NULL,
    section_id integer NOT NULL,
    lesson_name character varying(255) NOT NULL,
    content_type public.content_type DEFAULT 'TEXT'::public.content_type,
    media_id integer,
    article_text text,
    duration smallint,
    order_number integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: media_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.media_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.media (
    media_id integer DEFAULT nextval('public.media_seq'::regclass) NOT NULL,
    media_type public.media_type NOT NULL,
    url text NOT NULL,
    duration smallint,
    description text,
    transcript text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: membership_plan_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membership_plan_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: membership_plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membership_plan (
    plan_id integer DEFAULT nextval('public.membership_plan_seq'::regclass) NOT NULL,
    plan_name character varying(100) NOT NULL,
    description text,
    price numeric(10,2) NOT NULL,
    duration_days integer NOT NULL,
    is_active boolean DEFAULT true,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: menu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menu_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu (
    menu_id integer DEFAULT nextval('public.menu_seq'::regclass) NOT NULL,
    label character varying(255),
    url text,
    parent_id integer,
    order_number integer,
    description character varying(255),
    status boolean,
    item_id integer,
    icon character varying(255),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: module_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.module_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: module; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.module (
    module_id integer DEFAULT nextval('public.module_seq'::regclass) NOT NULL,
    module_name character varying(100) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: part_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.part_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: part; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.part (
    part_id integer DEFAULT nextval('public.part_seq'::regclass) NOT NULL,
    part_name character varying(255),
    description text,
    question_type public.question_type NOT NULL,
    instructions text,
    media_id integer,
    question_count smallint NOT NULL,
    order_number smallint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid,
    grading public.grading_type NOT NULL
);



--
-- Name: permission_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permission_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission (
    permission_id integer DEFAULT nextval('public.permission_seq'::regclass) NOT NULL,
    permission_name character varying(20),
    description text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: question_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    question_id integer DEFAULT nextval('public.question_seq'::regclass) NOT NULL,
    media_id integer,
    part_id integer NOT NULL,
    content text NOT NULL,
    options jsonb,
    correct_answer text,
    explanation text,
    order_number smallint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: resource_access_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.resource_access_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: resource_access; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resource_access (
    access_id integer DEFAULT nextval('public.resource_access_seq'::regclass) NOT NULL,
    resource_id integer NOT NULL,
    resource_type public.resource_type NOT NULL,
    table_name character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id integer DEFAULT nextval('public.role_seq'::regclass) NOT NULL,
    role_type public.role_type NOT NULL,
    description text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: role_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_permission (
    role_id integer NOT NULL,
    permission_id integer NOT NULL
);



--
-- Name: score_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.score_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: score; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.score (
    score_id integer DEFAULT nextval('public.score_seq'::regclass) NOT NULL,
    correct_count integer NOT NULL,
    listening_score integer NOT NULL,
    reading_score integer NOT NULL
);



--
-- Name: section_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.section_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: section; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.section (
    section_id integer DEFAULT nextval('public.section_seq'::regclass) NOT NULL,
    module_id integer NOT NULL,
    section_name character varying(255) NOT NULL,
    section_type public.section_type,
    description text,
    order_number integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: section_part; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.section_part (
    section_id integer NOT NULL,
    part_id integer NOT NULL
);



--
-- Name: section_part_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.section_part_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: section_part_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.section_part_answer (
    answer_id integer DEFAULT nextval('public.section_part_answer_seq'::regclass) NOT NULL,
    practice_id integer NOT NULL,
    question_id integer NOT NULL,
    selected_answer character(1)
);



--
-- Name: section_part_practice_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.section_part_practice_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: section_part_practice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.section_part_practice (
    practice_id integer DEFAULT nextval('public.section_part_practice_seq'::regclass) NOT NULL,
    user_id uuid NOT NULL,
    part_id integer NOT NULL,
    correct_count smallint,
    total_time smallint,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: test_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.test_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    user_id uuid NOT NULL,
    first_name character varying(20),
    last_name character varying(20),
    gender public.gender_type,
    dob date,
    active boolean,
    role_id integer,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: user_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: user_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_answer (
    answer_id integer DEFAULT nextval('public.user_answer_seq'::regclass) NOT NULL,
    attempt_id integer NOT NULL,
    question_id integer NOT NULL,
    selected_answer text
);



--
-- Name: user_login_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_login_data (
    user_id uuid NOT NULL,
    username character varying(20),
    password character varying(255),
    email character varying(50),
    phone_number character varying(10)
);



--
-- Name: user_login_external; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_login_external (
    user_id uuid NOT NULL,
    provider_id integer NOT NULL,
    external_token character varying(255)
);



--
-- Name: user_membership_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_membership_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: user_membership; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_membership (
    membership_id integer DEFAULT nextval('public.user_membership_seq'::regclass) NOT NULL,
    user_id uuid NOT NULL,
    plan_id integer NOT NULL,
    start_date timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    status public.status_type NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp without time zone,
    updated_by uuid
);



--
-- Name: user_test_attempt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_test_attempt_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: user_test_attempt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_test_attempt (
    attempt_id integer DEFAULT nextval('public.user_test_attempt_seq'::regclass) NOT NULL,
    user_id uuid NOT NULL,
    exam_id integer NOT NULL,
    total_score smallint,
    start_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    end_time timestamp without time zone,
    complete boolean DEFAULT false NOT NULL,
    listening_score smallint,
    reading_score smallint,
    correct_count smallint,
    incorrect_count smallint,
    skip_count character varying
);



--
-- Name: exam exam_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_pkey PRIMARY KEY (exam_id);


--
-- Name: exam_structure exam_structure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam_structure
    ADD CONSTRAINT exam_structure_pkey PRIMARY KEY (exam_id, part_id);


--
-- Name: external_provider external_provider_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.external_provider
    ADD CONSTRAINT external_provider_pkey PRIMARY KEY (provider_id);


--
-- Name: lesson lesson_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT lesson_pkey PRIMARY KEY (lesson_id);


--
-- Name: media media_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_pkey PRIMARY KEY (media_id);


--
-- Name: membership_plan membership_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membership_plan
    ADD CONSTRAINT membership_plan_pkey PRIMARY KEY (plan_id);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (menu_id);


--
-- Name: module module_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module
    ADD CONSTRAINT module_pkey PRIMARY KEY (module_id);


--
-- Name: part part_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.part
    ADD CONSTRAINT part_pkey PRIMARY KEY (part_id);


--
-- Name: permission permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (permission_id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (question_id);


--
-- Name: resource_access resource_access_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resource_access
    ADD CONSTRAINT resource_access_pkey PRIMARY KEY (access_id);


--
-- Name: role_permission role_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (role_id, permission_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: score score_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.score
    ADD CONSTRAINT score_pkey PRIMARY KEY (score_id);


--
-- Name: section_part_answer section_part_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section_part_answer
    ADD CONSTRAINT section_part_answer_pkey PRIMARY KEY (answer_id);


--
-- Name: section_part section_part_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section_part
    ADD CONSTRAINT section_part_pkey PRIMARY KEY (section_id, part_id);


--
-- Name: section_part_practice section_part_practice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section_part_practice
    ADD CONSTRAINT section_part_practice_pkey PRIMARY KEY (practice_id);


--
-- Name: section section_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section
    ADD CONSTRAINT section_pkey PRIMARY KEY (section_id);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (user_id);


--
-- Name: user_answer user_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_answer
    ADD CONSTRAINT user_answer_pkey PRIMARY KEY (answer_id);


--
-- Name: user_login_data user_login_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_login_data
    ADD CONSTRAINT user_login_data_pkey PRIMARY KEY (user_id);


--
-- Name: user_login_external user_login_external_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_login_external
    ADD CONSTRAINT user_login_external_pkey PRIMARY KEY (user_id);


--
-- Name: user_membership user_membership_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_membership
    ADD CONSTRAINT user_membership_pkey PRIMARY KEY (membership_id);


--
-- Name: user_test_attempt user_test_attempt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_test_attempt
    ADD CONSTRAINT user_test_attempt_pkey PRIMARY KEY (attempt_id);


--
-- PostgreSQL database dump complete
--

