----------------------------------------Sequence----------------------------------------
create sequence role_seq start with 1 increment by 50;
create sequence permission_seq start with 1 increment by 50;
create sequence external_provider_seq start with 1 increment by 50;
create sequence module_seq start with 1 increment by 50;
create sequence section_seq start with 1 increment by 50;
create sequence lesson_seq start with 1 increment by 50;
create sequence test_seq start with 1 increment by 50;
create sequence exam_seq start with 1 increment by 50;
create sequence part_seq start with 1 increment by 50;
create sequence question_seq start with 1 increment by 50;
create sequence media_seq start with 1 increment by 50;
create sequence user_test_attempt_seq start 1 increment by 50;
create sequence user_answer_seq start 1 increment by 50;

----------------------------------------Security----------------------------------------
create type gender_type as enum ('MALE', 'FEMALE', 'OTHER');
create type role_type as enum ('USER', 'ADMIN');

create table user_account
(
    user_id    uuid not null,
    first_name varchar(20),
    last_name  varchar(20),
    gender     gender_type,
    dob        date,
    active     boolean,
    role_id    int,
    created_at timestamp,
    created_by uuid,
    updated_at timestamp,
    updated_by uuid,
    primary key (user_id)
);

create table user_login_data
(
    user_id       uuid not null,
    username      varchar(20),
    password_hash varchar(255),
    email         varchar(20),
    phone_number  varchar(10),
    primary key (user_id)
);

create table role
(
    role_id     int       not null default nextval('role_seq'),
    role_type   role_type not null,
    description text,
    created_at  timestamp,
    created_by  uuid,
    updated_at  timestamp,
    updated_by  uuid,
    primary key (role_id)
);

create table permission
(
    permission_id   int not null default nextval('permission_seq'),
    permission_name varchar(20),
    description     text,
    created_at      timestamp,
    created_by      uuid,
    updated_at      timestamp,
    updated_by      uuid,
    primary key (permission_id)
);

create table external_provider
(
    provider_id   int not null default nextval('external_provider_seq'),
    provider_name varchar(20),
    ws_endpoint   varchar(50),
    created_at    timestamp,
    created_by    uuid,
    updated_at    timestamp,
    updated_by    uuid,
    primary key (provider_id)
);

create table user_login_external
(
    user_id        uuid not null,
    provider_id    int  not null,
    external_token varchar(255),
    primary key (user_id)
);

create table role_permission
(
    role_id       int not null,
    permission_id int not null,
    primary key (role_id, permission_id)
);

----------------------------------------Practice----------------------------------------
create type module_type as enum ('LISTENING', 'READING', 'SPEAKING', 'WRITING');
create type content_type as enum ('VIDEO', 'TEXT');
CREATE TYPE difficulty_level as enum ('BEGINNER', 'INTERMEDIATE', 'ADVANCED');
create type test_type as enum ('FULL_TEST', 'PRACTICE_PART', 'MOCK_TEST', 'CUSTOM');
CREATE TYPE question_type as enum ('MULTIPLE_CHOICE', 'IMAGE_BASED', 'AUDIO_BASED', 'FILL_IN_BLANK');
CREATE TYPE media_type as enum ('AUDIO', 'VIDEO', 'IMAGE');

create table module
(
    module_id   int      not null default nextval('module_seq'),
    module_type module_type not null,
    created_at  timestamp default CURRENT_TIMESTAMP,
    created_by  uuid,
    updated_at  timestamp,
    updated_by  uuid,
    primary key (module_id)
);

create table section
(
    section_id   int       not null default nextval('section_seq'),
    module_id    int          not null,
    section_name varchar(255) not null,
    description  text,
    order_number int          not null,
    created_at   timestamp default CURRENT_TIMESTAMP,
    created_by   uuid,
    updated_at   timestamp,
    updated_by   uuid,
    primary key (section_id)
);

create table lesson
(
    lesson_id    int       not null default nextval('lesson_seq'),
    section_id   int          not null,
    lesson_name  varchar(255) not null,
    content_type content_type default 'TEXT',
    media_id     int          default null,
    article_text text,
    duration     smallint,
    order_number int          not null,
    created_at   timestamp    default CURRENT_TIMESTAMP,
    created_by   uuid,
    updated_at   timestamp,
    updated_by   uuid,
    primary key (lesson_id)
);

create table test
(
    test_id    int    not null default nextval('test_seq'),
    test_type  test_type not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    created_by uuid,
    updated_at timestamp,
    updated_by uuid,
    primary key (test_id)
);

create table exam
(
    exam_id     int not null default nextval('exam_seq'),
    test_id     int    not null,
    exam_name   varchar(255),
    total_score smallint,
    duration    smallint,
    created_at  timestamp default CURRENT_TIMESTAMP,
    created_by  uuid,
    updated_at  timestamp,
    updated_by  uuid,
    primary key (exam_id)
);

create table part
(
    part_id        int        not null default nextval('part_seq'),
    part_name      varchar(255),
    description    text,
    question_type  question_type not null,
    instructions   text,
    media_id       int       default null,
    question_count smallint      not null,
    order_number   smallint      not null,
    created_at     timestamp default CURRENT_TIMESTAMP,
    created_by     uuid,
    updated_at     timestamp,
    updated_by     uuid,
    primary key (part_id)
);

create table section_part
(
    section_id int not null,
    part_id    int not null,
    primary key (section_id, part_id)
);

create table exam_structure
(
    exam_id int not null,
    part_id int not null,
    primary key (exam_id, part_id)
);

create table question
(
    question_id    int not null default nextval('question_seq'),
    media_id       int              default null,
    content        text   not null,
    options        jsonb  not null,
    correct_answer char(1),
    explanation    text,
    difficulty     difficulty_level default 'BEGINNER',
    created_at     timestamp        default CURRENT_TIMESTAMP,
    created_by     uuid,
    updated_at     timestamp,
    updated_by     uuid,
    primary key (question_id)
);

create table media
(
    media_id    int       not null default nextval('media_seq'),
    media_type  media_type   not null,
    url         varchar(512) not null,
    duration    smallint,
    description text,
    transcript  text,
    created_at  timestamp default CURRENT_TIMESTAMP,
    created_by  uuid,
    updated_at     timestamp,
    updated_by     uuid,
    primary key (media_id)
);

create table user_test_attempt
(
    attempt_id  int not null default nextval('user_test_attempt_seq'),
    user_id     uuid   not null,
    exam_id     int    not null,
    start_time  timestamp,
    end_time    timestamp,
    total_score smallint,
    primary key (attempt_id)
);

create table user_answer
(
    answer_id       int not null default nextval('user_answer_seq'),
    attempt_id      int    not null,
    question_id     int    not null,
    selected_answer char(1),
    primary key (answer_id)
);

insert into role(role_type, description)
values ('ADMIN', 'ALL PERMISSION'),
       ('USER', 'CUSTOM PERMISSION');