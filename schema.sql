drop table if exists role_permission;
drop table if exists user_login_data;
drop table if exists user_login_external;
drop table if exists user_account;
drop table if exists role;
drop table if exists permission;
drop table if exists external_provider;
drop
type if exists gender_type;

create
type gender_type as enum ('MALE', 'FEMALE', 'OTHER');

create table user_account
(
    user_id uuid not null,
    first_name varchar(20),
    last_name  varchar(20),
    gender gender_type,
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
    user_id uuid not null,
    username      varchar(20),
    password_hash varchar(255),
    password_salt varchar(255),
    email         varchar(20),
    phone_number  varchar(10),
    primary key (user_id)
);

create table role
(
    role_id serial2 not null,
    role_name   varchar(10),
    description text,
    created_at  timestamp,
    created_by uuid,
    updated_at  timestamp,
    updated_by uuid,
    primary key (role_id)
);

create table permission
(
    permission_id serial2 not null,
    permission_name varchar(20),
    description     text,
    created_at      timestamp,
    created_by uuid,
    updated_at      timestamp,
    updated_by uuid,
    primary key (permission_id)
);

create table external_provider
(
    provider_id serial2 not null,
    provider_name varchar(20),
    ws_endpoint   varchar(50),
    created_at    timestamp,
    created_by uuid,
    updated_at    timestamp,
    updated_by uuid,
    primary key (provider_id)
);

create table user_login_external
(
    user_id uuid not null,
    provider_id    int not null,
    external_token varchar(255),
    primary key (user_id)
);

create table role_permission
(
    role_id       int not null,
    permission_id int not null,
    primary key (role_id, permission_id)
);

CREATE TABLE Modules
(
    module_id       INT PRIMARY KEY,
    course_id       INT          NOT NULL,
    module_name     VARCHAR(100) NOT NULL,
    order_in_course INT          NOT NULL
);


CREATE TABLE Lessons
(
    lesson_id       INT PRIMARY KEY,
    module_id       INT          NOT NULL,
    part_id         INT          NOT NULL,
    lesson_title    VARCHAR(255) NOT NULL,
    content_type    TEXT,
    video_url       VARCHAR(255),
    article_text    TEXT,
    duration        SMALLINT,
    order_in_module INT          NOT NULL,
    FOREIGN KEY (module_id) REFERENCES Modules (module_id)
);

CREATE TABLE TOEIC_Parts
(
    part_id         INT PRIMARY KEY,
    section         VARCHAR(50),
    part_number     BOOLEAN NOT NULL,
    description     TEXT,
    total_questions BOOLEAN
);

CREATE TABLE TOEIC_Parts
(
    part_id         INT PRIMARY KEY,
    section         VARCHAR(50),
    part_number     BOOLEAN NOT NULL,
    description     TEXT,
    total_questions BOOLEAN
);

