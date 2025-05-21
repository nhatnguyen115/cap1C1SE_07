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
-- Data for Name: exam; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.exam (exam_id, exam_name, total_score, duration, level, question_count, created_at, created_by, updated_at, updated_by) VALUES (751, 'Exam 1', 990, 120, 'BEGINNER', 200, '2025-05-21 00:10:51.888739', NULL, NULL, NULL);
INSERT INTO public.exam (exam_id, exam_name, total_score, duration, level, question_count, created_at, created_by, updated_at, updated_by) VALUES (851, 'Exam 2', 990, 120, 'INTERMEDIATE', 200, '2025-05-21 01:45:38.390023', NULL, '2025-05-21 01:46:00.990619', NULL);


--
-- Data for Name: exam_structure; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.exam_structure (exam_id, part_id) VALUES (751, 51);
INSERT INTO public.exam_structure (exam_id, part_id) VALUES (751, 101);
INSERT INTO public.exam_structure (exam_id, part_id) VALUES (751, 301);
INSERT INTO public.exam_structure (exam_id, part_id) VALUES (751, 351);


--
-- Data for Name: external_provider; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: lesson; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: media; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.media (media_id, media_type, url, duration, description, transcript, created_at, created_by, updated_at, updated_by) VALUES (1, 'IMAGE', '', NULL, NULL, NULL, '2025-05-15 19:42:34.394901', NULL, '2025-05-15 19:42:34.394901', NULL);
INSERT INTO public.media (media_id, media_type, url, duration, description, transcript, created_at, created_by, updated_at, updated_by) VALUES (51, 'IMAGE', '', NULL, NULL, NULL, '2025-05-15 19:45:30.956168', NULL, '2025-05-15 19:45:30.956168', NULL);
INSERT INTO public.media (media_id, media_type, url, duration, description, transcript, created_at, created_by, updated_at, updated_by) VALUES (101, 'AUDIO', 'http://res.cloudinary.com/dhaj1kpxw/video/upload/v1747467418/mgszyjvkzeido4o6jz0s.mp3', NULL, NULL, NULL, '2025-05-17 14:37:10.876402', NULL, '2025-05-17 14:37:10.876402', NULL);


--
-- Data for Name: membership_plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.membership_plan (plan_id, plan_name, description, price, duration_days, is_active, created_at, created_by, updated_at, updated_by) VALUES (1, 'Plus', 'One month', 100000.00, 30, true, '2025-05-14 17:24:14.002032', NULL, NULL, NULL);
INSERT INTO public.membership_plan (plan_id, plan_name, description, price, duration_days, is_active, created_at, created_by, updated_at, updated_by) VALUES (51, 'Pro', 'Three month', 270000.00, 90, true, '2025-05-14 17:25:18.957204', NULL, NULL, NULL);
INSERT INTO public.membership_plan (plan_id, plan_name, description, price, duration_days, is_active, created_at, created_by, updated_at, updated_by) VALUES (1701, 'Ultimate', 'Nine month', 850000.00, 270, true, '2025-05-14 22:08:48.430372', NULL, NULL, NULL);


--
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (0, NULL, '/', -1, 0, NULL, true, NULL, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300100, 'Section 1: Photographs', '/sections/{id}', 200100, 1, NULL, true, 100200, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300101, 'Section 2: Question-Response', '/sections/{id}', 200100, 2, NULL, true, 100201, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300102, 'Section 3: Short Conversations', '/sections/{id}', 200100, 3, NULL, true, 100202, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300103, 'Section 4: Short Talks', '/sections/{id}', 200100, 4, NULL, true, 100203, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300150, 'Section 5: Incomplete Sentences', '/sections/{id}', 200100, 5, NULL, true, 100250, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300151, 'Section 6: Text Completion', '/sections/{id}', 200100, 6, NULL, true, 100251, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300152, 'Section 7: Reading Comprehension', '/sections/{id}', 200100, 7, NULL, true, 100252, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300200, 'Section 1: Read Aloud', '/sections/{id}', 200101, 1, NULL, true, 100300, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300201, 'Section 2: Describe a Picture', '/sections/{id}', 200101, 2, NULL, true, 100301, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300202, 'Section 3: Respond to Questions', '/sections/{id}', 200101, 3, NULL, true, 100302, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300203, 'Section 4: Respond to questions using information provided', '/sections/{id}', 200101, 4, NULL, true, 100303, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300204, 'Section 5: Express an opinion', '/sections/{id}', 200101, 5, NULL, true, 100304, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300250, 'Section 6: Write a sentence based on a picture', '/sections/{id}', 200101, 6, NULL, true, 100350, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300251, 'Section 7: Respond to a written request', '/sections/{id}', 200101, 7, NULL, true, 100351, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300252, 'Section 8: Write an opinion essay', '/sections/{id}', 200101, 8, NULL, true, 100352, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300303, 'Trang Chủ', '/', 0, 0, NULL, true, NULL, NULL, '2025-05-11 20:34:18.934641', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (300304, 'Premium', '/payment', 0, 6, NULL, true, NULL, NULL, '2025-05-19 10:51:49.54925', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (200102, 'Đề Thi Thử', '/tests', 0, 3, NULL, true, 100102, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (200100, 'Luyện L&R', '/sections?moduleId={id}', 0, 1, NULL, true, 100100, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (200101, 'Luyện S&W', '/sections?moduleId={id}', 0, 2, NULL, true, 100101, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (200103, 'Từ Vựng', '/sections?moduleId={id}', 0, 4, NULL, true, 100103, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);
INSERT INTO public.menu (menu_id, label, url, parent_id, order_number, description, status, item_id, icon, created_at, created_by, updated_at, updated_by) VALUES (200104, 'Ngữ Pháp', '/sections?moduleId={id}', 0, 5, NULL, true, 100104, '', '2025-05-11 20:34:18.928612', NULL, NULL, NULL);


--
-- Data for Name: module; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.module (module_id, module_name, created_at, created_by, updated_at, updated_by) VALUES (100100, 'Practice L&R', '2025-05-11 20:34:18.859544', NULL, NULL, NULL);
INSERT INTO public.module (module_id, module_name, created_at, created_by, updated_at, updated_by) VALUES (100101, 'Practice S&W', '2025-05-11 20:34:18.859544', NULL, NULL, NULL);
INSERT INTO public.module (module_id, module_name, created_at, created_by, updated_at, updated_by) VALUES (100103, 'Vocabulary', '2025-05-11 20:34:18.859544', NULL, NULL, NULL);
INSERT INTO public.module (module_id, module_name, created_at, created_by, updated_at, updated_by) VALUES (100104, 'Grammar', '2025-05-11 20:34:18.859544', NULL, NULL, NULL);


--
-- Data for Name: part; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (101, 'Part 2', 'Part 2', 'MULTIPLE_CHOICE', NULL, NULL, 10, 2, '2025-05-11 20:34:40.676304', NULL, NULL, NULL, 'LISTENING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (151, 'Part 3', 'Part 3', 'MULTIPLE_CHOICE', NULL, NULL, 10, 3, '2025-05-11 20:34:40.676304', NULL, NULL, NULL, 'LISTENING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (201, 'Part 4', 'Part 4', 'MULTIPLE_CHOICE', NULL, NULL, 10, 4, '2025-05-11 20:34:40.676304', NULL, NULL, NULL, 'LISTENING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (251, 'Part 5', 'Part 5', 'MULTIPLE_CHOICE', NULL, NULL, 10, 5, '2025-05-11 20:34:40.676304', NULL, NULL, NULL, 'LISTENING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (301, 'Part 6', 'Part 6', 'MULTIPLE_CHOICE', NULL, NULL, 30, 6, '2025-05-11 20:34:40.681527', NULL, NULL, NULL, 'READING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (351, 'Part 7', 'Part 7', 'MULTIPLE_CHOICE', NULL, NULL, 30, 7, '2025-05-11 20:34:40.681527', NULL, NULL, NULL, 'READING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (401, 'Part 8', 'Part 8', 'MULTIPLE_CHOICE', NULL, NULL, 30, 8, '2025-05-11 20:34:40.681527', NULL, NULL, NULL, 'READING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (451, 'Part 9', 'Part 9', 'MULTIPLE_CHOICE', NULL, NULL, 39, 9, '2025-05-11 20:34:40.687107', NULL, NULL, NULL, 'READING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (501, 'Part 10', 'Part 10', 'MULTIPLE_CHOICE', NULL, NULL, 39, 10, '2025-05-11 20:34:40.687107', NULL, NULL, NULL, 'READING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (551, 'Part 11', 'Part 11', 'MULTIPLE_CHOICE', NULL, NULL, 39, 11, '2025-05-11 20:34:40.687107', NULL, NULL, NULL, 'READING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (601, 'Part 12', 'Speaking', 'AUDIO_BASED', 'In this part of the test, you will read aloud the text on the screen. You will have 45 seconds to prepare. Then you will have 45 seconds to read the text aloud.', NULL, 10, 12, '2025-05-13 22:33:26.642283', NULL, NULL, NULL, 'OTHER');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (651, 'Part 1', 'Grammar', 'MULTIPLE_CHOICE', NULL, NULL, 20, 1, '2025-05-14 21:21:39.009531', NULL, NULL, NULL, 'OTHER');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (1201, 'Part 1', 'Phần 1 của bài kiểm tra', 'MULTIPLE_CHOICE', 'Nghe một đoạn audio và nhìn tranh chọn đáp án', NULL, 5, 1, '2025-05-16 15:58:28.206275', NULL, '2025-05-16 15:58:28.206275', NULL, 'OTHER');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (1251, 'Part 2', 'Phần 2 của bài kiểm tra', 'MULTIPLE_CHOICE', 'Nghe một đoạn audio và chọn đáp án đúng nhất với câu hỏi', NULL, 2, 2, '2025-05-16 15:58:28.405034', NULL, '2025-05-16 15:58:28.405034', NULL, 'OTHER');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (1301, 'Part 13', 'Writing', 'ESSAY', NULL, NULL, 5, 13, '2025-05-17 17:58:41.866519', NULL, NULL, NULL, 'OTHER');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (1351, 'Part new', 'Phần mới', 'MULTIPLE_CHOICE', '', NULL, 12, 1, '2025-05-21 09:52:06.04003', NULL, '2025-05-21 09:52:06.04085', NULL, 'LISTENING');
INSERT INTO public.part (part_id, part_name, description, question_type, instructions, media_id, question_count, order_number, created_at, created_by, updated_at, updated_by, grading) VALUES (51, 'Part 1', 'Part 1', 'MULTIPLE_CHOICE', NULL, 101, 10, 1, '2025-05-11 20:34:40.676304', NULL, '2025-05-17 14:37:10.988736', NULL, 'LISTENING');


--
-- Data for Name: permission; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25151, NULL, 51, '1', '{"A": "She is riding a moving walk.", "B": "She is pulling her luggage along.", "C": "She is packing her bags for a trip.", "D": "She is lining up at the check-in desk."}', 'B', NULL, 1, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25201, NULL, 51, '2', '{"A": "They’re enjoying the view.", "B": "They’re reviewing some documents.", "C": "They’re having a discussion.", "D": "They’re sitting in a circle."}', 'B', NULL, 2, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25251, NULL, 51, '3', '{"A": "He’s buying a newspaper.", "B": "He’s sending a text message.", "C": "He’s holding a publication.", "D": "He’s adjusting his tie."}', 'C', NULL, 3, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25301, NULL, 51, '4', '{"A": "The loaves are being taken out of the oven.", "B": "The loaves have been arranged on the shelves.", "C": "The loaves are being prepared by the baker.", "D": "The loaves have been displayed on the table."}', 'B', NULL, 4, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25351, NULL, 51, '5', '{"A": "The lighthouse is under construction.", "B": "The beach house is being tom down.", "C": "One structure is taller than the others.", "D": "There is a path through the field."}', 'C', NULL, 5, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25401, NULL, 51, '6', '{"A": "He’s making notes in his planner.", "B": "He’s typing on the keyboard.", "C": "He’s facing the monitor.", "D": "He’s turning the computer on."}', 'A', NULL, 6, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25451, NULL, 51, '7', '{"A": "All of the flowers are blooming.", "B": "There are trees along the path.", "C": "It’s snowing in the forest.", "D": "There are piles of logs on the road."}', 'A', NULL, 7, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25501, NULL, 51, '8', '{"A": "He’s entering a library.", "B": "She’s fixing a bookcase.", "C": "He’s indicating something on a page.", "D": "She’s turning the pages."}', 'C', NULL, 8, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25551, NULL, 51, '9', '{"A": "The lamps are being installed.", "B": "The windows have been left open.", "C": "The kitchen table is being set.", "D": "The chairs are placed around the table."}', 'C', NULL, 9, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25601, NULL, 51, '10', '{"A": "The people are meeting with a travel agent.", "B": "The people are boarding an airplane.", "C": "The people are waiting in a terminal.", "D": "The people are booking their flights."}', 'C', NULL, 10, '2025-05-21 02:29:08.346104', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25651, NULL, 101, 'They’re discussing a document.', '{"A": "They’re copying some papers.", "B": "They’re discussing a document.", "C": "They’re writing a book.", "D": "They’re looking for a folder."}', 'B', NULL, 1, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25701, NULL, 101, 'He’s working at his desk.', '{"A": "He’s eating lunch outside.", "B": "He’s working at his desk.", "C": "He’s making a phone call.", "D": "He’s sitting on the grass."}', 'B', NULL, 2, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25751, NULL, 101, 'She’s preparing dinner.', '{"A": "She’s washing her clothes.", "B": "She’s doing the dishes.", "C": "She’s cleaning her bedroom.", "D": "She’s preparing dinner."}', 'D', NULL, 3, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25801, NULL, 101, 'A woman is distributing handouts.', '{"A": "A woman is giving out presents.", "B": "A woman is leading a workshop.", "C": "A woman is distributing handouts.", "D": "A woman is registering for a seminar."}', 'C', NULL, 4, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25851, NULL, 101, 'The lake is being used for recreation.', '{"A": "The waves are crashing against the rocks.", "B": "The park is surrounded by water.", "C": "There’s a fountain in the water.", "D": "The lake is being used for recreation."}', 'D', NULL, 5, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25901, NULL, 101, 'The woman is being examined.', '{"A": "The woman is making an appointment.", "B": "The woman is being examined.", "C": "The doctor is looking at the chart.", "D": "The doctor is being paged."}', 'B', NULL, 6, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (25951, NULL, 101, 'There are houses on both sides of the river.', '{"A": "There is a ferry on the canal.", "B": "People are diving into the water.", "C": "Most of the houses are being restored.", "D": "There are houses on both sides of the river."}', 'D', NULL, 7, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26001, NULL, 101, 'He’s buying a train ticket.', '{"A": "He’s wearing a business suit.", "B": "He’s getting on the train.", "C": "He’s unloading the bags.", "D": "He’s buying a train ticket."}', 'D', NULL, 8, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26051, NULL, 101, 'They’re putting on a performance.', '{"A": "They’re putting on a performance.", "B": "They’re singing in a choir.", "C": "They’re unpacking their instruments.", "D": "They’re going to a concert."}', 'A', NULL, 9, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26101, NULL, 101, 'The waiter is carrying plates of food.', '{"A": "The waiter is carrying plates of food.", "B": "The cafeteria has been closed down.", "C": "The food is displayed in glass cabinets.", "D": "The cafe is inside a shopping mall."}', 'A', NULL, 10, '2025-05-21 02:29:08.449152', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26151, NULL, 151, 'She’s coming down the stairs.', '{"A": "She’s coming down the stairs.", "B": "She''s riding the escalator.", "C": "She’s taking the elevator.", "D": "She’s going up the ladder."}', 'A', NULL, 1, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26201, NULL, 151, 'They’re doing the dishes.', '{"A": "They’re doing the dishes.", "B": "They’re moving the furniture.", "C": "They’re unpacking some boxes.", "D": "They’re installing some equipment."}', 'A', NULL, 2, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26251, NULL, 151, 'A man is registering for a conference.', '{"A": "A man is registering for a conference.", "B": "A man is signing some books.", "C": "A man is browsing around the library.", "D": "A man is reading a pamphlet."}', 'A', NULL, 3, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26301, NULL, 151, 'There is a tree behind the bench.', '{"A": "There is a tree behind the bench.", "B": "The sidewalk is being repaved.", "C": "The hedge is being trimmed.", "D": "The bench is positioned next to a house."}', 'A', NULL, 4, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26351, NULL, 151, 'People are buying train tickets.', '{"A": "People are buying train tickets.", "B": "People are using public transportation.", "C": "People are waiting at a bus terminal.", "D": "People are looking at a subway map."}', 'A', NULL, 5, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26401, NULL, 151, 'The café is closed for business.', '{"A": "The café is closed for business.", "B": "Some of the trees are losing their leaves.", "C": "All of the parasols are being put away.", "D": "The tables are arranged around the courtyard."}', 'A', NULL, 6, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26451, NULL, 151, 'The woman has checked out of the hotel.', '{"A": "The woman has checked out of the hotel.", "B": "The woman is ordering room service.", "C": "The woman has left her bag on the ground.", "D": "The woman is weighing her luggage."}', 'A', NULL, 7, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26501, NULL, 151, 'The waste containers are labeled.', '{"A": "The waste containers are labeled.", "B": "The garbage is being collected.", "C": "The trash cans are being emptied.", "D": "The litter is lying on the floor."}', 'A', NULL, 8, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26551, NULL, 151, 'The tables have been stacked up.', '{"A": "The tables have been stacked up.", "B": "The leftovers have been cleared away.", "C": "The restaurant is full of customers.", "D": "The dining hall has been set up."}', 'A', NULL, 9, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26601, NULL, 151, 'They’re working in the mall.', '{"A": "They’re working in the mall.", "B": "They’re holding hands.", "C": "They’re putting on their backpacks.", "D": "They’re hiking through the woods."}', 'A', NULL, 10, '2025-05-21 02:29:08.462605', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26651, NULL, 201, 'He’s changing the tires.', '{"A": "He’s changing the tires.", "B": "He’s washing the vehicle.", "C": "He’s checking the engine.", "D": "He’s pushing the car."}', 'A', NULL, 1, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26701, NULL, 201, 'The taxis are stuck in traffic.', '{"A": "The taxis are stuck in traffic.", "B": "The taxis are lined up in a row.", "C": "The taxis have stopped at an intersection.", "D": "The taxis are being serviced."}', 'A', NULL, 2, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26751, NULL, 201, 'The workers are installing the computer.', '{"A": "The workers are installing the computer.", "B": "One woman is drinking from a glass.", "C": "The workers are attending a presentation.", "D": "One woman is pointing at the screen."}', 'A', NULL, 3, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26801, NULL, 201, 'They’re looking at a photograph.', '{"A": "They’re looking at a photograph.", "B": "They’re walking along the path.", "C": "They’re having their picture taken.", "D": "They’re buying a camera."}', 'A', NULL, 4, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26851, NULL, 201, 'The yachts are facing in the same direction.', '{"A": "The yachts are facing in the same direction.", "B": "There are several boats in the harbor.", "C": "There are many ships tied up together.", "D": "The wharf is being constructed."}', 'A', NULL, 5, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26901, NULL, 201, 'There is a bus terminal across the street.', '{"A": "There is a bus terminal across the street.", "B": "The pedestrians are using the underpass.", "C": "Some of the buildings are being renovated.", "D": "Most of the people are wearing coats."}', 'A', NULL, 6, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (26951, NULL, 201, 'The library is full of visitors.', '{"A": "The library is full of visitors.", "B": "The shelves are being restocked.", "C": "The books have been left lying around.", "D": "The reading materials have been assembled."}', 'A', NULL, 7, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27001, NULL, 201, 'The people are leaning against the wall.', '{"A": "The people are leaning against the wall.", "B": "The woman is posting a notice on the wall.", "C": "The people are talking beside a bulletin board.", "D": "The woman is indicating something on a chart."}', 'A', NULL, 8, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27051, NULL, 201, 'They’re preparing breakfast.', '{"A": "They’re preparing breakfast.", "B": "They’re eating out together.", "C": "They’re reading the menu.", "D": "They’re pointing at the waiter."}', 'A', NULL, 9, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27101, NULL, 201, 'He’s repairing his bicycle.', '{"A": "He’s repairing his bicycle.", "B": "He’s riding a motorcycle.", "C": "He’s holding the handlebars.", "D": "He’s adjusting the bike seat."}', 'A', NULL, 10, '2025-05-21 02:29:08.476394', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27151, NULL, 251, 'They’re holding tickets for the train.', '{"A": "They’re holding tickets for the train.", "B": "They’re walking through the train.", "C": "They’re doing some window-shopping.", "D": "They’re strolling along the platform."}', 'A', NULL, 1, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27201, NULL, 251, 'A man is swimming in the ocean.', '{"A": "A man is swimming in the ocean.", "B": "A man is facing his child.", "C": "A man is sharing some fish.", "D": "A man is standing on the beach."}', 'A', NULL, 2, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27251, NULL, 251, 'The buses have been parked at the terminal.', '{"A": "The buses have been parked at the terminal.", "B": "The buses are filled with passengers.", "C": "The buses have left the station.", "D": "The buses are being washed off."}', 'A', NULL, 3, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27301, NULL, 251, 'He’s holding onto the handrail.', '{"A": "He’s holding onto the handrail.", "B": "He’s focusing on his laptop.", "C": "He’s setting down his backpack.", "D": "He’s coming down the stairs."}', 'A', NULL, 4, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27351, NULL, 251, 'Renovations are underway in the town.', '{"A": "Renovations are underway in the town.", "B": "The houses have been built close to the river.", "C": "Three roadways run under the bridge.", "D": "Buildings stand on both sides of the water."}', 'A', NULL, 5, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27401, NULL, 251, 'The man is helping her exercise.', '{"A": "The man is helping her exercise.", "B": "The woman is walking out of the room.", "C": "The woman is weighing herself.", "D": "The man is lifting a dumbbell."}', 'A', NULL, 6, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27451, NULL, 251, 'The restaurant is crowded with diners.', '{"A": "The restaurant is crowded with diners.", "B": "Food has been served at a party.", "C": "The customer is setting the table.", "D": "Two people are eating outdoors."}', 'A', NULL, 7, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27501, NULL, 251, 'There are many types of fruit on display.', '{"A": "There are many types of fruit on display.", "B": "There are signs hanging from the ceiling.", "C": "There are fruit bins near the register.", "D": "There are pieces of fruit being eaten."}', 'A', NULL, 8, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27551, NULL, 251, 'Construction equipment has been loaded onto a truck.', '{"A": "Construction equipment has been loaded onto a truck.", "B": "The farmers are digging in the field.", "C": "One machine is dumping dirt into the other.", "D": "The highway has been closed for maintenance work."}', 'A', NULL, 9, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27601, NULL, 251, 'They are seeing a patient.', '{"A": "They are seeing a patient.", "B": "They are wearing masks.", "C": "They are behind a counter.", "D": "They are in a laboratory."}', 'A', NULL, 10, '2025-05-21 02:29:08.490926', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27651, NULL, 301, 'Where did you put the file?', '{"A": "Sometime last week.", "B": "No, he hasn’t filed it yet.", "C": "In the office cabinet."}', 'C', '', 11, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27701, NULL, 301, 'Could you give me a copy of the report?', '{"A": "No, thanks. I don’t need one.", "B": "Sure, here you are.", "C": "The photocopier is on the 2nd floor."}', 'B', '', 12, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27751, NULL, 301, 'What is the soup of the day?', '{"A": "It’s tomorrow.", "B": "Clam chowder.", "C": "No, I’ll get something else."}', 'B', '', 13, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27801, NULL, 301, 'How long is the flight from Santiago?', '{"A": "To New York.", "B": "10 hours.", "C": "No, not really."}', 'B', '', 14, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27851, NULL, 301, 'Did anyone call for me while I was away?', '{"A": "How long will you be gone?", "B": "Well, it has been a while.", "C": "Nothing important."}', 'C', '', 15, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27901, NULL, 301, 'Could you give me a wake-up call at 7 A.M.?', '{"A": "I’ve already woken up.", "B": "No, he didn’t call.", "C": "No problem, sir."}', 'C', '', 16, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (27951, NULL, 301, 'I see you bought a new cell phone.', '{"A": "Yes, I lost my old one.", "B": "A little under $200.", "C": "Where did you buy it?"}', 'A', '', 17, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28001, NULL, 301, 'What kind of skirt are you looking for?', '{"A": "Yes, it was very kind of her.", "B": "Preferably something in silk.", "C": "It looks all right."}', 'B', '', 18, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28051, NULL, 301, 'Isn’t Mr. Knight retiring at the end of the year?', '{"A": "Yes, I’m very tired as well.", "B": "Not that I know of.", "C": "Yes, he did."}', 'B', '', 19, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28101, NULL, 301, 'The work schedule has been revised, right?', '{"A": "He’s my advisor.", "B": "Yes, I’ll send it to you now.", "C": "By next weekend at the latest."}', 'C', '', 20, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28151, NULL, 301, 'Where did you end up going on vacation? Miami or Las Vegas?', '{"A": "I finally decided on Miami.", "B": "Yes, they’re both interesting destinations.", "C": "I know! Our vacation is nearly over."}', 'A', '', 21, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28201, NULL, 301, 'Would you mind if I changed the channel?', '{"A": "I didn’t change it.", "B": "Sure, go ahead.", "C": "No, the tunnel isn’t that long."}', 'B', '', 22, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28251, NULL, 301, 'Why is the senator arranging a press conference?', '{"A": "On a wide range of products.", "B": "It was very impressive.", "C": "To discuss the new legislation."}', 'C', '', 23, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28301, NULL, 301, 'Can I have a look at your concert program?', '{"A": "I’m afraid I didn’t get one either.", "B": "I’m very concerned about it, too.", "C": "It’s a computer program."}', 'A', '', 24, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28351, NULL, 301, 'When should we gather at the tour bus?', '{"A": "At 12:30 sharp.", "B": "The gathering was last month.", "C": "At the main entrance."}', 'A', '', 25, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28401, NULL, 301, 'How did Mr. Johnson react to the news?', '{"A": "I never watch the news.", "B": "He was very relieved.", "C": "He is an actor."}', 'B', '', 26, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28451, NULL, 301, 'Ms. Watson is flying in from New Jersey for a meeting.', '{"A": "Isn’t that the old version?", "B": "No, she wasn’t on my flight either.", "C": "You’d better collect her from the airport."}', 'C', '', 27, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28501, NULL, 301, 'Who gave you permission to park there?', '{"A": "The assistant in the booth.", "B": "Admission is $2.", "C": "Don’t worry. I gave it back."}', 'A', '', 28, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28551, NULL, 301, 'What did you do with the handouts?', '{"A": "I distributed them to the participants.", "B": "No, it’s inside.", "C": "Sorry, I can’t help you at the moment."}', 'A', '', 29, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28601, NULL, 301, 'Where is the team lunch being held?', '{"A": "I think it’s Friday next week.", "B": "At the Indian restaurant across the road.", "C": "Yes, the launch was very successful."}', 'B', '', 30, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28651, NULL, 301, 'Will all employees receive a bonus this year, or just a few?', '{"A": "Only the top performers will be rewarded.", "B": "Yes, that’s what I heard as well.", "C": "I worked extra hours."}', 'A', '', 31, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28701, NULL, 301, 'Which seminar are you going to attend?', '{"A": "The one on asset management.", "B": "I didn’t go there.", "C": "Because he tends to change his mind."}', 'A', '', 32, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28751, NULL, 301, 'Human Resources haven’t found a replacement for Mr. Allen, have they?', '{"A": "No, but there are some promising candidates.", "B": "It was founded several years ago.", "C": "My place isn’t far from here."}', 'A', '', 33, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28801, NULL, 301, 'Haven’t you been to the new shopping mall yet?', '{"A": "Yes, it has been.", "B": "Small size, please.", "C": "I’m planning to go there next weekend."}', 'C', '', 34, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28851, NULL, 301, 'We’re thinking about renovating our kitchen and bathroom.', '{"A": "No, I think we should put it in the living room.", "B": "Relocating can be very expensive.", "C": "I can introduce a great builder."}', 'C', '', 35, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28901, NULL, 301, 'Is your office on 1st floor or the 2nd?', '{"A": "Actually it’s on the 5th floor", "B": "In the large office complex downtown", "C": "Thank you for visiting."}', 'A', '', 36, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (28951, NULL, 301, 'Have you seen Andrew around the office lately?', '{"A": "I saw him just this morning.", "B": "Were you late again this morning?", "C": "In the official guidelines."}', 'A', '', 37, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29001, NULL, 301, 'You’re staying at the Pembridge Hotel, aren’t you?', '{"A": "I thought it was very comfortable.", "B": "I’m not going yet.", "C": "Yes, it’s quite close to the station."}', 'C', '', 38, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29051, NULL, 301, 'Who is going to make the opening speech at the conference?', '{"A": "At the Hyatt Hotel.", "B": "A partner from Worrell and Jones.", "C": "Before the event starts on the first day."}', 'B', '', 39, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29101, NULL, 301, 'Why did the marketing team fail to meet the project deadline?', '{"A": "Because the test was extremely difficult.", "B": "On or before the 15th of July.", "C": "Do you think they might be understaffed?"}', 'C', '', 40, '2025-05-21 02:29:08.52344', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29151, NULL, 351, 'Where can I find the marketing report?', '{"A": "It’s on the server.", "B": "I already sent it to you.", "C": "It’s in my office."}', 'A', '', 11, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29201, NULL, 351, 'Can you recommend a good restaurant?', '{"A": "The one across the street is great.", "B": "I don’t like eating out.", "C": "I think the office cafeteria is good."}', 'A', '', 12, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29251, NULL, 351, 'What time does the conference call start?', '{"A": "It starts at 10:00 AM.", "B": "I’m not sure.", "C": "I need to check."}', 'A', '', 13, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29301, NULL, 351, 'How should I send the payment?', '{"A": "You can wire it.", "B": "You can mail it.", "C": "You should pay online."}', 'A', '', 14, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29351, NULL, 351, 'Where is the nearest restroom?', '{"A": "It’s down the hall to the left.", "B": "I don’t know.", "C": "It’s in the building next door."}', 'A', '', 15, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29401, NULL, 351, 'Have you finished the project?', '{"A": "Yes, it’s done.", "B": "Not yet, I’m still working on it.", "C": "I haven’t started yet."}', 'A', '', 16, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29451, NULL, 351, 'Why are you late?', '{"A": "I missed the bus.", "B": "I didn’t feel well.", "C": "I had a meeting."}', 'A', '', 17, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29501, NULL, 351, 'Where did you get that information?', '{"A": "From the email I received.", "B": "From the internet.", "C": "From a colleague."}', 'A', '', 18, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29551, NULL, 351, 'Are you available for a meeting tomorrow?', '{"A": "Yes, I’m free.", "B": "No, I have another appointment.", "C": "I need to check my calendar."}', 'A', '', 19, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29601, NULL, 351, 'How do I reach the customer service department?', '{"A": "You can call the hotline.", "B": "Just visit their website.", "C": "You can send an email."}', 'A', '', 20, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29651, NULL, 351, 'What time does the store close?', '{"A": "At 6:00 PM.", "B": "At 8:00 PM.", "C": "At 10:00 PM."}', 'A', '', 21, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29701, NULL, 351, 'Where did you put my laptop?', '{"A": "It’s on your desk.", "B": "I didn’t touch it.", "C": "I put it in the drawer."}', 'A', '', 22, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29751, NULL, 351, 'Are you familiar with the new software?', '{"A": "Yes, I’ve used it before.", "B": "No, I haven’t used it yet.", "C": "I heard about it."}', 'A', '', 23, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29801, NULL, 351, 'Could you explain this report to me?', '{"A": "I can go over it with you.", "B": "It’s too complicated.", "C": "I can’t explain that."}', 'A', '', 24, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29851, NULL, 351, 'Where is the nearest post office?', '{"A": "It’s down the street.", "B": "I don’t know.", "C": "There’s one on the next block."}', 'A', '', 25, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29901, NULL, 351, 'What are your working hours?', '{"A": "From 9 AM to 5 PM.", "B": "From 8 AM to 4 PM.", "C": "We work flexibly."}', 'A', '', 26, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (29951, NULL, 351, 'Have you completed the task yet?', '{"A": "Yes, I finished it this morning.", "B": "I’m still working on it.", "C": "I haven’t started yet."}', 'A', '', 27, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30001, NULL, 351, 'When is the deadline for the report?', '{"A": "It’s due by Friday.", "B": "The deadline has passed.", "C": "It’s next month."}', 'A', '', 28, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30051, NULL, 351, 'Do you need assistance with the presentation?', '{"A": "Yes, I need help.", "B": "No, I can handle it.", "C": "I need some information."}', 'A', '', 29, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30101, NULL, 351, 'How do I reset my password?', '{"A": "You can reset it online.", "B": "You need to call IT support.", "C": "I can reset it for you."}', 'A', '', 30, '2025-05-21 02:29:08.546575', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30151, NULL, 401, 'Do you need help with your luggage?', '{"A": "No, I’m fine.", "B": "Yes, please.", "C": "I can carry it myself."}', 'B', '', 11, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30201, NULL, 401, 'What time does the meeting start?', '{"A": "At 9:00 AM.", "B": "At 10:00 AM.", "C": "At noon."}', 'A', '', 12, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30251, NULL, 401, 'Have you confirmed your attendance for the event?', '{"A": "Yes, I’ve confirmed it.", "B": "No, I still need to confirm.", "C": "I won’t be able to attend."}', 'A', '', 13, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30301, NULL, 401, 'Where do I find the presentation slides?', '{"A": "They’re in the shared folder.", "B": "I don’t know.", "C": "I haven’t uploaded them yet."}', 'A', '', 14, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30351, NULL, 401, 'Did you send the invitation email?', '{"A": "Yes, I’ve already sent it.", "B": "No, I forgot.", "C": "I’m planning to send it soon."}', 'A', '', 15, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30401, NULL, 401, 'Can you provide an update on the project?', '{"A": "Yes, everything is on track.", "B": "No, there have been some delays.", "C": "I don’t have an update."}', 'A', '', 16, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30451, NULL, 401, 'What should I bring to the conference?', '{"A": "Your business cards.", "B": "Your laptop.", "C": "Just a notebook."}', 'A', '', 17, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30501, NULL, 401, 'How was the flight?', '{"A": "It was smooth.", "B": "It was delayed.", "C": "I missed the flight."}', 'A', '', 18, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30551, NULL, 401, 'Where can I find the team members?', '{"A": "They are in the conference room.", "B": "They are in the cafeteria.", "C": "They are in the parking lot."}', 'A', '', 19, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30601, NULL, 401, 'Would you like to join us for lunch?', '{"A": "Sure, I’d love to.", "B": "No, I’m busy.", "C": "Maybe later."}', 'A', '', 20, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30651, NULL, 401, 'How long will the meeting last?', '{"A": "About an hour.", "B": "A couple of hours.", "C": "It could go all day."}', 'A', '', 21, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30701, NULL, 401, 'When is the deadline for the report?', '{"A": "By the end of the week.", "B": "Next month.", "C": "Next Monday."}', 'A', '', 22, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30751, NULL, 401, 'Where is the nearest pharmacy?', '{"A": "It’s around the corner.", "B": "It’s on the main street.", "C": "I’m not sure."}', 'A', '', 23, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30801, NULL, 401, 'Do you have a moment to discuss the project?', '{"A": "Yes, I’m free.", "B": "I’m in a meeting.", "C": "I’m busy right now."}', 'A', '', 24, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30851, NULL, 401, 'Can you send me the report by email?', '{"A": "Yes, I’ll send it now.", "B": "I’ll send it later.", "C": "I’ll send it tomorrow."}', 'A', '', 25, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30901, NULL, 401, 'Are we going to the conference together?', '{"A": "Yes, we’re meeting at 8 AM.", "B": "No, I’m going separately.", "C": "I haven’t decided yet."}', 'A', '', 26, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (30951, NULL, 401, 'Who is responsible for the marketing campaign?', '{"A": "John is in charge.", "B": "Sarah is handling it.", "C": "We all are."}', 'A', '', 27, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31001, NULL, 401, 'What time is your flight tomorrow?', '{"A": "It’s at 7:30 AM.", "B": "It’s at 9:00 AM.", "C": "It’s at noon."}', 'A', '', 28, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31051, NULL, 401, 'Where is the meeting room?', '{"A": "It’s on the 5th floor.", "B": "It’s in the basement.", "C": "It’s next to the cafeteria."}', 'A', '', 29, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31101, NULL, 401, 'How long will the project take?', '{"A": "About 3 months.", "B": "It will take a few weeks.", "C": "It will take a year."}', 'A', '', 30, '2025-05-21 02:29:08.56108', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31151, NULL, 451, 'Where do the speakers work?', '{"A": "At a hotel.", "B": "At an art gallery.", "C": "At a hardware store.", "D": "At a travel agency."}', 'A', '', 32, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31201, NULL, 451, 'Why was a building temporarily closed?', '{"A": "To take inventory.", "B": "To host an event.", "C": "To complete a renovation.", "D": "To celebrate a holiday."}', 'C', '', 33, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31251, NULL, 451, 'What is the man going to do next?', '{"A": "Post some flyers.", "B": "Send customers an e-mail.", "C": "Place a food order.", "D": "Contact the maintenance department."}', 'B', '', 34, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31301, NULL, 451, 'What are the speakers planning?', '{"A": "A company dinner.", "B": "A conference schedule.", "C": "An upcoming trip.", "D": "A factory inspection."}', 'A', '', 35, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31351, NULL, 451, 'What does the woman suggest?', '{"A": "Inviting a guest speaker.", "B": "Reserving a different venue.", "C": "Checking a budget.", "D": "Postponing a party."}', 'B', '', 36, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31401, NULL, 451, 'What does the woman say will be provided?', '{"A": "Name tags.", "B": "A city tour.", "C": "Transportation.", "D": "Entertainment."}', 'C', '', 37, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31451, NULL, 451, 'What are the speakers mainly discussing?', '{"A": "A store sign.", "B": "A Web site.", "C": "Some uniforms.", "D": "Some business cards."}', 'D', '', 38, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31501, NULL, 451, 'What suggestion does the man make?', '{"A": "Adding display racks.", "B": "Giving a demonstration.", "C": "Researching some suppliers.", "D": "Advertising a business’ hours."}', 'C', '', 39, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31551, NULL, 451, 'What does Ana offer to do?', '{"A": "Lead a training session.", "B": "Greet a client.", "C": "Update a list.", "D": "Revise an announcement."}', 'A', '', 40, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31601, NULL, 451, 'What type of business is the man calling?', '{"A": "A bookstore.", "B": "A restaurant.", "C": "A clothing shop.", "D": "A printing shop."}', 'D', '', 41, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31651, NULL, 451, 'What will happen next week?', '{"A": "A class will begin.", "B": "A shipment will arrive.", "C": "A location will change.", "D": "A sale will end."}', 'B', '', 42, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31701, NULL, 451, 'What information does the woman ask for?', '{"A": "A bank account number.", "B": "A mailing address.", "C": "A discount code.", "D": "A telephone number."}', 'D', '', 43, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31751, NULL, 451, 'What is the woman concerned about?', '{"A": "The quality of some fabric.", "B": "The price of a shipment.", "C": "The size of some furniture.", "D": "The noise from some construction."}', 'C', '', 44, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31801, NULL, 451, 'What does the man say his team will do at the woman’s house?', '{"A": "Paint a living room.", "B": "Assemble a product.", "C": "Take some measurements.", "D": "Remove some machinery."}', 'B', '', 45, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31851, NULL, 451, 'What does the woman ask the man to do?', '{"A": "Resubmit an order form.", "B": "Send some samples.", "C": "Go to a different address.", "D": "Change a delivery date."}', 'D', '', 46, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31901, NULL, 451, 'Why does the man want to hire a temporary employee?', '{"A": "To hand out brochures.", "B": "To design a Web site.", "C": "To sort through some documents.", "D": "To pick up some office equipment."}', 'C', '', 47, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (31951, NULL, 451, 'According to the man, what does the job require?', '{"A": "Sales experience.", "B": "Public speaking skills.", "C": "Interior decorating experience.", "D": "Computer skills."}', 'A', '', 48, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32001, NULL, 451, 'What does the man ask the woman to do?', '{"A": "Check a budget.", "B": "Sign a contract.", "C": "Brainstorm marketing ideas.", "D": "Prepare some invoices."}', 'B', '', 49, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32051, NULL, 451, 'Where do the speakers most likely work?', '{"A": "At a hotel.", "B": "At a restaurant.", "C": "At a convention hall.", "D": "At an auto repair shop."}', 'C', '', 50, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32101, NULL, 451, 'What does the woman say about her car?', '{"A": "She will loan it to a friend.", "B": "She does not use it often.", "C": "It was recently purchased.", "D": "It needs to be fixed."}', 'D', '', 51, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32151, NULL, 451, 'What does the woman mean when she says, "Thursday is my mother’s birthday?"', '{"A": "She is inviting the man to a party.", "B": "She cannot work on Thursday night.", "C": "She has to buy a gift before Thursday.", "D": "She forgot to update a calendar."}', 'B', '', 52, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32201, NULL, 451, 'What most likely is the man’s position?', '{"A": "A maintenance worker.", "B": "A government official.", "C": "An editor.", "D": "An accountant."}', 'A', '', 53, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32251, NULL, 451, 'What do the women do at their company?', '{"A": "They arrange travel.", "B": "They provide legal assistance.", "C": "They organize training sessions.", "D": "They manage company inventory."}', 'C', '', 54, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32301, NULL, 451, 'What does the man ask about?', '{"A": "Free parking.", "B": "Technical support.", "C": "Payment options.", "D": "Printing supplies."}', 'C', '', 55, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32351, NULL, 451, 'Which industry do the speakers most likely work in?', '{"A": "Health care.", "B": "Architecture.", "C": "Tourism.", "D": "Fashion."}', 'C', '', 56, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32401, NULL, 451, 'What does the woman mean when she says, "My meeting was canceled"?', '{"A": "She cannot answer a question.", "B": "She is available to discuss an issue.", "C": "She is confused by a schedule change.", "D": "She is worried a project will be delayed."}', 'B', '', 57, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32451, NULL, 451, 'What will the woman most likely do next?', '{"A": "Call a vendor.", "B": "Distribute a questionnaire.", "C": "Review some designs.", "D": "Contact some colleagues."}', 'C', '', 58, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32501, NULL, 451, 'What is the topic of an upcoming seminar?', '{"A": "Payroll procedures.", "B": "Videoconferencing tools.", "C": "Computer upgrades.", "D": "Password security."}', 'D', '', 59, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32551, NULL, 451, 'What aspect of the seminar do the speakers disagree about?', '{"A": "How long it should last.", "B": "How it should be announced.", "C": "Whether attendance should be required.", "D": "Whether refreshments should be served."}', 'C', '', 60, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32601, NULL, 451, 'What does the woman want to distribute after the seminar?', '{"A": "A survey.", "B": "A manual.", "C": "Some paychecks.", "D": "Some hardware."}', 'A', '', 61, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32651, NULL, 451, 'Who most likely is the man?', '{"A": "A fitness coach.", "B": "A teacher.", "C": "A medical doctor.", "D": "A receptionist."}', 'B', '', 62, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32701, NULL, 451, 'What does the woman ask the man about?', '{"A": "Requirements for a job.", "B": "Alternative types of exercise.", "C": "Available appointment times.", "D": "Operating hours of a business."}', 'C', '', 63, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32751, NULL, 451, 'Look at the graphic. When will the woman probably go to the community center in the spring?', '{"A": "On Tuesdays.", "B": "On Wednesdays.", "C": "On Thursdays.", "D": "On Fridays."}', 'B', '', 64, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32801, NULL, 451, 'What does the man say he is doing tonight?', '{"A": "Taking a flight.", "B": "Eating in a restaurant.", "C": "Seeing a performance.", "D": "Visiting a friend."}', 'C', '', 65, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32851, NULL, 451, 'Look at the graphic. Which train line will the man most likely take?', '{"A": "The North Line.", "B": "The East Line.", "C": "The South Line.", "D": "The West Line."}', 'A', '', 66, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32901, NULL, 451, 'What does the man ask about a bus?', '{"A": "Whether he needs a different ticket.", "B": "Whether there are reserved seats.", "C": "How long the ride will take.", "D": "How often the bus runs."}', 'C', '', 67, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (32951, NULL, 451, 'Why is the woman at the store?', '{"A": "To arrange a delivery.", "B": "To exchange a purchase.", "C": "To request an instruction manual.", "D": "To complain about an incorrect charge."}', 'B', '', 68, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33001, NULL, 451, 'What is the woman’s job?', '{"A": "Jazz musician.", "B": "Studio photographer.", "C": "Carpenter.", "D": "Electrical engineer."}', 'C', '', 69, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33051, NULL, 451, 'Look at the graphic. What model does the man recommend?', '{"A": "F-12.", "B": "A-66.", "C": "N-48.", "D": "C-94."}', 'D', '', 70, '2025-05-21 02:29:08.591669', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33101, NULL, 501, 'Where are the speakers?', '{"A": "At an airport", "B": "At a restaurant", "C": "At a theater", "D": "At a hotel"}', 'A', '', 1, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33151, NULL, 501, 'What does the woman ask the man for?', '{"A": "His reservation number", "B": "His name", "C": "His departure day", "D": "His credit card"}', 'A', '', 2, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33201, NULL, 501, 'What will the man most likely do next?', '{"A": "Park his car", "B": "Upgrade a reservation", "C": "Buy a snack", "D": "Go to the pool"}', 'A', '', 3, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33251, NULL, 501, 'Who most likely is the man?', '{"A": "A painter", "B": "A security guard", "C": "A bank teller", "D": "A property manager"}', 'D', '', 4, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33301, NULL, 501, 'What problem does the woman have?', '{"A": "She found a mistake on a bill.", "B": "She cannot locate a door key.", "C": "An air conditioner is not working.", "D": "An apartment is too noisy."}', 'C', '', 5, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33351, NULL, 501, 'What information does the man ask for?', '{"A": "An address", "B": "A security code", "C": "A telephone number", "D": "An appointment time"}', 'B', '', 6, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33401, NULL, 501, 'Where do the speakers most likely work?', '{"A": "At a manufacturing plant", "B": "At a delivery company", "C": "At a fitness center", "D": "At a grocery store"}', 'B', '', 7, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33451, NULL, 501, 'According to the woman, what does a decision depend on?', '{"A": "Employee availability", "B": "Government regulations", "C": "A price", "D": "A timeline"}', 'D', '', 8, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33501, NULL, 501, 'What does the man say he will do?', '{"A": "Check delivery dates", "B": "Schedule an inspection", "C": "Contact a supplier", "D": "Test a product"}', 'A', '', 9, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33551, NULL, 501, 'What is the man’s job?', '{"A": "University professor", "B": "Newspaper reporter", "C": "Flight attendant", "D": "Tour guide"}', 'D', '', 10, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33601, NULL, 501, 'What does the man say he likes about the job?', '{"A": "Meeting city officials", "B": "Traveling to other countries", "C": "Learning about local history", "D": "Attending special celebrations"}', 'B', '', 11, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33651, NULL, 501, 'What does the woman ask the man to do?', '{"A": "Write an article", "B": "Work more hours", "C": "Train new employees", "D": "Organize an event"}', 'A', '', 12, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33701, NULL, 501, 'What will be the topic of the woman’s article?', '{"A": "Music festivals", "B": "Local restaurants", "C": "Farmers markets", "D": "Sporting events"}', 'C', '', 13, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33751, NULL, 501, 'What does the man ask the woman about?', '{"A": "Interviewing some vendors", "B": "Reformatting some images", "C": "Extending a deadline", "D": "Making travel arrangements"}', 'A', '', 14, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33801, NULL, 501, 'What does the man mean when he says, "I don’t think Elena has any assignments"?', '{"A": "A colleague completes tasks quickly.", "B": "A colleague may be available for a job.", "C": "An office does not need more staff.", "D": "A schedule may be incorrect."}', 'B', '', 15, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33851, NULL, 501, 'Why does the man congratulate the woman?', '{"A": "She recently published a book.", "B": "She just received a promotion.", "C": "She acquired a major account.", "D": "She completed a business course."}', 'B', '', 16, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33901, NULL, 501, 'What does the man ask the woman to do?', '{"A": "Update a reservation", "B": "Meet with a new client", "C": "Submit a budget report", "D": "Give a talk to staff members"}', 'D', '', 17, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (33951, NULL, 501, 'Where does the woman say she will be going?', '{"A": "To a fund-raising event", "B": "To a convention", "C": "On a vacation", "D": "On a promotional tour"}', 'B', '', 18, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34001, NULL, 501, 'Who most likely are the men?', '{"A": "Real estate agents", "B": "Interior designers", "C": "Marketing executives", "D": "Bank managers"}', 'B', '', 19, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34051, NULL, 501, 'What does the woman want to discuss first?', '{"A": "A delivery time", "B": "A rental fee", "C": "Some flooring options", "D": "Some machinery upgrades"}', 'C', '', 20, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34101, NULL, 501, 'What will the woman most likely do next?', '{"A": "Look at a catalog", "B": "Cancel a meeting", "C": "Review a receipt", "D": "Call a supervisor"}', 'A', '', 21, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34151, NULL, 501, 'What is the woman planning to do at two o’clock?', '{"A": "Go to the airport", "B": "Receive a shipment", "C": "Meet with a customer", "D": "Give a workshop"}', 'B', '', 22, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34201, NULL, 501, 'What problem is the woman having?', '{"A": "Her log-in information has expired.", "B": "Her laptop cable does not work.", "C": "Her reservation was not confirmed.", "D": "Her mobile phone is missing."}', 'C', '', 23, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34251, NULL, 501, 'What does the man tell the woman to do?', '{"A": "Search in her office", "B": "Visit an electronics store", "C": "Borrow some equipment", "D": "Contact a service team member"}', 'D', '', 24, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34301, NULL, 501, 'Where is the conversation taking place?', '{"A": "At a park", "B": "At a café", "C": "At a furniture store", "D": "At a supermarket"}', 'B', '', 25, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34351, NULL, 501, 'What does Luisa suggest that the man do?', '{"A": "Open a window", "B": "Use a coupon", "C": "Visit a plant shop", "D": "Extend business hours"}', 'B', '', 26, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34401, NULL, 501, 'What does the man ask Luisa for?', '{"A": "A list of prices", "B": "A deadline extension", "C": "Some coffee", "D": "Some photographs"}', 'A', '', 27, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34451, NULL, 501, 'What problem does the woman mention?', '{"A": "A product is not selling well.", "B": "A position is vacant.", "C": "A proposal was not accepted.", "D": "A supervisor is busy."}', 'A', '', 28, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34501, NULL, 501, 'Why does the woman say, "He’s never done that before"?', '{"A": "To express concern", "B": "To request more help", "C": "To approve a decision", "D": "To offer some praise"}', 'A', '', 29, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34551, NULL, 501, 'What does the man say he will do?', '{"A": "Write a report", "B": "Conduct an interview", "C": "Schedule a meeting", "D": "Post an advertisement"}', 'C', '', 30, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34601, NULL, 501, 'Why does the man want to return an item?', '{"A": "It has a stain.", "B": "It has a tear.", "C": "It is the wrong color.", "D": "It is too large."}', 'D', '', 31, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34651, NULL, 501, 'Look at the graphic. Which item does the man want to return?', '{"A": "The jacket", "B": "The sweater", "C": "The scarf", "D": "The T-shirt"}', 'A', '', 32, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34701, NULL, 501, 'What does the woman offer to do for the man?', '{"A": "Issue a refund to his credit card", "B": "Set up an account for him", "C": "Consult with a manager", "D": "Call another branch store"}', 'A', '', 33, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34751, NULL, 501, 'Who most likely is the woman?', '{"A": "A postal worker", "B": "A delivery driver", "C": "A repair technician", "D": "A building supervisor"}', 'D', '', 34, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34801, NULL, 501, 'What problem does the woman mention?', '{"A": "A package has been damaged.", "B": "A vehicle is not working.", "C": "Some residents are not home.", "D": "Some information is missing."}', 'A', '', 35, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34851, NULL, 501, 'Look at the graphic. Where will the woman go next?', '{"A": "To Building 1", "B": "To Building 2", "C": "To Building 3", "D": "To Building 4"}', 'B', '', 36, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34901, NULL, 501, 'Look at the graphic. What is the status of the woman’s flight?', '{"A": "40 minutes late", "B": "On time", "C": "55 minutes late", "D": "25 minutes late"}', 'B', '', 37, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (34951, NULL, 501, 'What kind of company do the speakers work for?', '{"A": "A fabric manufacturer", "B": "A clothing store", "C": "A travel agency", "D": "A newspaper publisher"}', 'C', '', 38, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35001, NULL, 501, 'What does the man say he is going to do?', '{"A": "Open another bank account", "B": "Extend business hours", "C": "Review a contract", "D": "Change a meeting time"}', 'C', '', 39, '2025-05-21 02:29:08.616029', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35051, NULL, 551, 'Where does the woman say she wants to go?', '{"A": "To an airport", "B": "To a beach", "C": "To a hotel", "D": "To a fitness center"}', 'A', '', 1, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35101, NULL, 551, 'Who most likely is the man?', '{"A": "An auto mechanic", "B": "A local musician", "C": "A security guard", "D": "A bus driver"}', 'C', '', 2, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35151, NULL, 551, 'Why will the woman return home late?', '{"A": "She is working overtime.", "B": "She has a flight delay.", "C": "She is attending a concert.", "D": "She is eating at a restaurant"}', 'B', '', 3, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35201, NULL, 551, 'Where do the speakers most likely work?', '{"A": "At an appliance manufacturer", "B": "At a construction firm", "C": "At a grocery store", "D": "At an apartment complex"}', 'D', '', 4, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35251, NULL, 551, 'What does the woman say she will review?', '{"A": "A budget", "B": "A contract", "C": "A job posting", "D": "An instruction manual"}', 'B', '', 5, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35301, NULL, 551, 'What does the man hope to do this afternoon?', '{"A": "Schedule an interview", "B": "Arrange a discount", "C": "Make a delivery", "D": "Print some brochures"}', 'C', '', 6, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35351, NULL, 551, 'Who most likely is the man?', '{"A": "A flight attendant", "B": "A sales representative", "C": "An event organizer", "D": "A repair technician"}', 'D', '', 7, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35401, NULL, 551, 'Why is Sameera unable to attend a meeting?', '{"A": "Her flight was canceled.", "B": "Her car has broken down.", "C": "She is on vacation.", "D": "She is feeling sick."}', 'A', '', 8, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35451, NULL, 551, 'What does the woman say the man should do before a meeting?', '{"A": "Read some client information", "B": "Prepare a contract", "C": "Make a dinner reservation", "D": "Check some equipment"}', 'A', '', 9, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35501, NULL, 551, 'Where do the speakers work?', '{"A": "At a clothing shop", "B": "At a photography studio", "C": "At a travel agency", "D": "At a furniture store"}', 'C', '', 10, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35551, NULL, 551, 'Why does the man say, “Coffee shops need a lot of tables and chairs?"', '{"A": "To request assistance", "B": "To correct an error", "C": "To express disagreement", "D": "To make a guess"}', 'D', '', 11, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35601, NULL, 551, 'What will the woman do next?', '{"A": "Process an online order", "B": "Call the building’s property manager", "C": "Meet some new neighbors", "D": "Fix a broken piece of equipment"}', 'B', '', 12, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35651, NULL, 551, 'Why does the man apologize?', '{"A": "He used the wrong entrance.", "B": "He is late for an appointment.", "C": "He forgot to bring identification.", "D": "He lost an order number."}', 'B', '', 13, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35701, NULL, 551, 'What is the man’s job?', '{"A": "Delivery driver", "B": "Electrician", "C": "Journalist", "D": "Security guard"}', 'D', '', 14, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35751, NULL, 551, 'Where does the woman direct the man to go?', '{"A": "To a conference room", "B": "To a security desk", "C": "To a construction site", "D": "To a loading dock"}', 'A', '', 15, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35801, NULL, 551, 'Where do the speakers most likely work?', '{"A": "At an advertising agency", "B": "At an electronics shop", "C": "At a furniture store", "D": "At an assembly plant"}', 'A', '', 16, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35851, NULL, 551, 'Why does the woman say, "We’ve already sold out"?', '{"A": "To ask for help", "B": "To refuse a request", "C": "To express agreement", "D": "To show concern"}', 'B', '', 17, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35901, NULL, 551, 'According to the woman, why is a product popular?', '{"A": "It has good online reviews.", "B": "It has a lifetime warranty.", "C": "It is being advertised by celebrities.", "D": "It is being sold at a low price"}', 'A', '', 18, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (35951, NULL, 551, 'Why are the men at the store?', '{"A": "To return a defective item", "B": "To purchase a gift", "C": "To publicize a festival", "D": "To apply for a position"}', 'B', '', 19, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36001, NULL, 551, 'What hobby is mentioned?', '{"A": "Hiking", "B": "Swimming", "C": "Skiing", "D": "Cycling"}', 'D', '', 20, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36051, NULL, 551, 'What does the woman recommend?', '{"A": "Finalizing a schedule", "B": "Consulting a return policy", "C": "Getting a membership", "D": "Downloading a map"}', 'C', '', 21, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36101, NULL, 551, 'Why are the speakers traveling?', '{"A": "To attend a conference", "B": "To train some employees", "C": "To meet a client", "D": "To open a new business location"}', 'C', '', 22, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36151, NULL, 551, 'What does the man say he will do during the flight?', '{"A": "Update a travel itinerary", "B": "Edit some presentation slides", "C": "Proofread a contract", "D": "Review some resumes"}', 'B', '', 23, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36201, NULL, 551, 'What will the speakers most likely do when they land?', '{"A": "Check in to a hotel", "B": "Eat at a restaurant", "C": "Board another flight", "D": "Go to an office building"}', 'A', '', 24, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36251, NULL, 551, 'Where most likely are the speakers?', '{"A": "In a computer store", "B": "In a recording studio", "C": "In a medical clinic", "D": "In an electrical supply shop"}', 'D', '', 25, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36301, NULL, 551, 'What are the speakers mainly discussing?', '{"A": "A work schedule", "B": "A recent illness", "C": "Some pricing options", "D": "Some maintenance problems"}', 'C', '', 26, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36351, NULL, 551, 'What does the woman say the men should do?', '{"A": "Contact a manager", "B": "Complete a form", "C": "Pay a bill", "D": "Provide some identification"}', 'A', '', 27, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36401, NULL, 551, 'What type of business do the speakers most likely operate?', '{"A": "A cooking school", "B": "A kitchen supply store", "C": "A dairy farm", "D": "A specialty bakery"}', 'B', '', 28, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36451, NULL, 551, 'How does the woman want to reduce expenses?', '{"A": "By relocating a business", "B": "By shortening operating hours", "C": "By using local suppliers", "D": "By purchasing in bulk"}', 'C', '', 29, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36501, NULL, 551, 'What is the man concerned about?', '{"A": "A change may affect product quality.", "B": "A new recipe has not been successful.", "C": "An ingredient is no longer being sold.", "D": "A competing business is expanding"}', 'A', '', 30, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36551, NULL, 551, 'What are the speakers mainly discussing?', '{"A": "An inspection of a facility", "B": "Transportation of some parts", "C": "A revised government policy", "D": "Some upcoming road construction"}', 'D', '', 31, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36601, NULL, 551, 'What does the man say he will do tomorrow?', '{"A": "Calculate a distance", "B": "Adjust a budget", "C": "Talk to local officials", "D": "Print a permit"}', 'C', '', 32, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36651, NULL, 551, 'Look at the graphic. According to the speakers, what will be shipped next?', '{"A": "The engine case", "B": "The tower", "C": "The foundation", "D": "The blades"}', 'C', '', 33, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36701, NULL, 551, 'What event are the speakers preparing for?', '{"A": "A professional conference", "B": "A political debate", "C": "An awards ceremony", "D": "A musical concert"}', 'A', '', 34, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36751, NULL, 551, 'What was the man asked to do?', '{"A": "Collect tickets", "B": "Leave empty rows near the stage", "C": "Set up some extra equipment", "D": "Pass out programs"}', 'C', '', 35, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36801, NULL, 551, 'Look at the graphic. Where will the man most likely be working during the event?', '{"A": "At Position 1", "B": "At Position 2", "C": "At Position 3", "D": "At Position 4"}', 'A', '', 36, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36851, NULL, 551, 'What does the woman say about the clients?', '{"A": "Their flight was delayed.", "B": "Their contract has not been signed.", "C": "They prefer to try a seafood restaurant.", "D": "They want to visit museums."}', 'C', '', 37, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36901, NULL, 551, 'What does the man suggest doing?', '{"A": "Updating an event calendar", "B": "Trying some local food", "C": "Taking a bus tour", "D": "Making reservations"}', 'B', '', 38, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (36951, NULL, 551, 'Look at the graphic. Which location will the speakers go to?', '{"A": "65 Elm St.", "B": "2 Peach St.", "C": "41 Ames Ave.", "D": "7 Edson Rd."}', 'A', '', 39, '2025-05-21 02:29:08.64292', NULL, NULL, NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (37051, NULL, 51, 'Tìm A và B', '{"A": "A", "B": "B", "C": "A và B", "D": "C"}', 'C', 'Đáp án thấy mẹ', 11, '2025-05-21 03:13:30.333724', NULL, '2025-05-21 03:14:39.98912', NULL);
INSERT INTO public.question (question_id, media_id, part_id, content, options, correct_answer, explanation, order_number, created_at, created_by, updated_at, updated_by) VALUES (37101, NULL, 51, 'Tìm C và D', '{"A": "A và B", "B": "B và C", "C": "A và C", "D": "C và D"}', 'D', 'Ta thấy C và D ở đáp án D', 12, '2025-05-21 09:35:03.138216', NULL, '2025-05-21 09:35:03.138216', NULL);


--
-- Data for Name: resource_access; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.resource_access (access_id, resource_id, resource_type, table_name, created_at, created_by, updated_at, updated_by) VALUES (1, 51, 'MEMBER', 'EXAM', '2025-05-15 14:04:30.484465', NULL, NULL, NULL);
INSERT INTO public.resource_access (access_id, resource_id, resource_type, table_name, created_at, created_by, updated_at, updated_by) VALUES (101, 1, 'FREE', 'EXAM', '2025-05-15 14:10:50.250853', NULL, NULL, NULL);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (role_id, role_type, description, created_at, created_by, updated_at, updated_by) VALUES (1, 'ADMIN', 'ALL PERMISSION', '2025-05-11 20:34:18.849852', NULL, NULL, NULL);
INSERT INTO public.role (role_id, role_type, description, created_at, created_by, updated_at, updated_by) VALUES (51, 'USER', 'CUSTOM PERMISSION', '2025-05-11 20:34:18.849852', NULL, NULL, NULL);


--
-- Data for Name: role_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: score; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1, 0, 5, 5);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (51, 1, 15, 5);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (101, 2, 20, 5);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (151, 3, 25, 10);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (201, 4, 30, 15);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (251, 5, 35, 20);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (301, 6, 40, 25);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (351, 7, 45, 30);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (401, 8, 50, 35);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (451, 9, 55, 40);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (501, 10, 60, 45);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (551, 11, 65, 50);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (601, 12, 70, 55);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (651, 13, 75, 60);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (701, 14, 80, 65);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (751, 15, 85, 70);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (801, 16, 90, 75);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (851, 17, 95, 80);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (901, 18, 100, 85);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (951, 19, 105, 90);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1001, 20, 110, 95);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1051, 21, 115, 100);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1101, 22, 120, 105);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1151, 23, 125, 110);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1201, 24, 130, 115);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1251, 25, 135, 120);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1301, 26, 140, 125);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1351, 27, 145, 130);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1401, 28, 150, 135);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1451, 29, 155, 140);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1501, 30, 160, 145);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1551, 31, 165, 150);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1601, 32, 170, 155);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1651, 33, 175, 160);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1701, 34, 180, 165);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1751, 35, 185, 170);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1801, 36, 190, 175);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1851, 37, 195, 180);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1901, 38, 200, 185);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (1951, 39, 205, 190);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2001, 40, 210, 195);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2051, 41, 215, 200);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2101, 42, 220, 205);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2151, 43, 225, 210);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2201, 44, 230, 215);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2251, 45, 235, 220);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2301, 46, 240, 225);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2351, 47, 245, 230);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2401, 48, 250, 235);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2451, 49, 255, 240);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2501, 50, 260, 245);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2551, 51, 265, 250);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2601, 52, 270, 255);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2651, 53, 275, 260);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2701, 54, 280, 265);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2751, 55, 285, 270);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2801, 56, 290, 275);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2851, 57, 295, 280);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2901, 58, 300, 285);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (2951, 59, 305, 290);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3001, 60, 310, 295);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3051, 61, 315, 300);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3101, 62, 320, 305);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3151, 63, 325, 310);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3201, 64, 330, 315);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3251, 65, 335, 320);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3301, 66, 340, 325);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3351, 67, 345, 330);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3401, 68, 350, 335);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3451, 69, 355, 340);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3501, 70, 360, 345);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3551, 71, 365, 350);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3601, 72, 370, 355);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3651, 73, 375, 360);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3701, 74, 380, 365);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3751, 75, 385, 370);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3801, 76, 395, 375);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3851, 77, 400, 380);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3901, 78, 405, 385);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (3951, 79, 410, 390);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4001, 80, 415, 395);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4051, 81, 420, 400);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4101, 82, 425, 405);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4151, 83, 430, 410);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4201, 84, 435, 415);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4251, 85, 440, 420);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4301, 86, 445, 425);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4351, 87, 450, 430);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4401, 88, 455, 435);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4451, 89, 460, 440);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4501, 90, 465, 445);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4551, 91, 470, 450);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4601, 92, 475, 455);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4651, 93, 480, 460);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4701, 94, 485, 465);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4751, 95, 490, 470);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4801, 96, 495, 475);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4851, 97, 495, 480);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4901, 98, 495, 485);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (4951, 99, 495, 490);
INSERT INTO public.score (score_id, correct_count, listening_score, reading_score) VALUES (5001, 100, 495, 495);


--
-- Data for Name: section; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100200, 100100, 'Section 1: Photographs', 'LISTENING', 'Four short statements regarding a photograph will be spoken only one time. Of these four statements, select the one. that best describes the photograph.', 1, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100201, 100100, 'Section 2: Question-Response', 'LISTENING', 'Three responses to one question or statement will be spoken only one time. Select the best response for the question.', 2, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100202, 100100, 'Section 3: Short Conversations', 'LISTENING', 'Conversations between two or three people will be spoken only one time. Listen to each conversation and select the best response for the question. There are three questions for each conversation.', 3, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100203, 100100, 'Section 4: Short Talks', 'LISTENING', 'Short talks such as announcements or narrations will be spoken only one time. Listen to each talk and select the best response for the question There are three questions for each talk.', 4, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100250, 100100, 'Section 5: Incomplete Sentences', 'READING', 'Select the best answer of the four choices to complete the sentence.', 5, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100251, 100100, 'Section 6: Text Completion', 'READING', 'Select the best answer of the four choices (words, phrases, or a sentence) to complete the text. There are four questions for each text.', 6, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100252, 100100, 'Section 7: Reading Comprehension', 'READING', 'Read a range of different texts and select the best answer of the four choices. There are multiple questions for each text.', 7, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100300, 100101, 'Section 1: Read Aloud', 'SPEAKING', 'In this section of the test, you will read aloud the text on the screen. You will have 45 seconds to prepare. Then you will have 45 seconds to read the text aloud.', 1, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100301, 100101, 'Section 2: Describe a Picture', 'SPEAKING', 'In this section, you will describe the picture on your screen in as much detail as you can. You will have 45 seconds to prepare your response and 30 seconds to speak about the picture.', 2, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100302, 100101, 'Section 3: Respond to Questions', 'SPEAKING', 'In this section, you will answer three questions. You will have three seconds to prepare after you hear each question, 15 seconds to respond to Q1 and 2, and 30 seconds to respond to Q3.', 3, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100303, 100101, 'Section 4: Respond to questions using information provided', 'SPEAKING', 'In this section, you will answer three questions based on information provided. You will have 45s to read information before questions begin. You will have 3s to prepare and 15s to respond to Q1 and 2. You will hear Q3 two times and have 3s to prepare and 30s to respond to Q3.', 4, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100304, 100101, 'Section 5: Express an opinion', 'SPEAKING', 'In this section, you will give your opinion about a specific topic. Be sure to say as much as you can in the time allowed. You will have 45 seconds to prepare. Then you will have 60 seconds to speak.', 5, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100452, 100104, 'ADJECTIVES', 'GRAMMAR', NULL, 2, '2025-05-14 21:20:06.806996', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100502, 100103, 'Contracts', 'VOCABULARY', NULL, 1, '2025-05-14 21:27:47.313504', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100402, 100104, 'NOUNS', 'VOCABULARY', NULL, 1, '2025-05-14 21:19:23.323413', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100350, 100101, 'Section 6: Write a sentence based on a picture', 'WRITING', 'In this section, you will write ONE sentence that is based on a picture. With each picture, you will be given TWO words or phrases that you must use in your sentence. You can change the forms of words and use the words in any order.', 6, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100351, 100101, 'Section 7: Respond to a written request', 'WRITING', 'In this section, you will show how well you can write a response to an e-mail.', 7, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);
INSERT INTO public.section (section_id, module_id, section_name, section_type, description, order_number, created_at, created_by, updated_at, updated_by) VALUES (100352, 100101, 'Section 8: Write an opinion essay', 'WRITING', 'In this section, you will write an essay in response to a question that asks you to state, explain, and support your opinion on an issue. Typically, an effective essay will contain a minimum of 300 words.', 8, '2025-05-11 20:34:18.881019', NULL, NULL, NULL);


--
-- Data for Name: section_part; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.section_part (section_id, part_id) VALUES (100200, 51);
INSERT INTO public.section_part (section_id, part_id) VALUES (100300, 601);
INSERT INTO public.section_part (section_id, part_id) VALUES (100402, 651);
INSERT INTO public.section_part (section_id, part_id) VALUES (100502, 701);
INSERT INTO public.section_part (section_id, part_id) VALUES (100350, 1301);


--
-- Data for Name: section_part_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1351, 151, 351, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1451, 151, 451, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1001, 151, 1, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1051, 151, 51, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1101, 151, 101, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1151, 151, 151, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1201, 151, 201, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1251, 151, 251, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1301, 151, 301, 'A');
INSERT INTO public.section_part_answer (answer_id, practice_id, question_id, selected_answer) VALUES (1401, 151, 401, 'A');


--
-- Data for Name: section_part_practice; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.section_part_practice (practice_id, user_id, part_id, correct_count, total_time, created_at, created_by, updated_at, updated_by) VALUES (151, '52317f07-685e-4188-a3b6-906e84dffd80', 51, 2, 41, '2025-05-13 23:36:42.597209', NULL, '2025-05-13 23:37:07.128482', NULL);


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_account (user_id, first_name, last_name, gender, dob, active, role_id, created_at, created_by, updated_at, updated_by) VALUES ('52317f07-685e-4188-a3b6-906e84dffd80', 'Nguyen', 'Hoang', 'MALE', NULL, true, 51, '2025-05-11 20:52:56.629872', NULL, '2025-05-11 20:52:56.629872', NULL);
INSERT INTO public.user_account (user_id, first_name, last_name, gender, dob, active, role_id, created_at, created_by, updated_at, updated_by) VALUES ('18953ac6-298a-4cf5-8f8e-ba198a0fa25b', 'Nguyen', 'Huy', 'MALE', NULL, true, 51, '2025-05-12 09:51:02.738481', NULL, '2025-05-12 09:51:02.740481', NULL);
INSERT INTO public.user_account (user_id, first_name, last_name, gender, dob, active, role_id, created_at, created_by, updated_at, updated_by) VALUES ('d70289a7-79b5-428e-9823-7c25dd3e3520', 'Nguyen', 'Hoai', 'MALE', NULL, true, 51, '2025-05-15 20:48:12.846658', NULL, '2025-05-15 20:48:12.846658', NULL);
INSERT INTO public.user_account (user_id, first_name, last_name, gender, dob, active, role_id, created_at, created_by, updated_at, updated_by) VALUES ('0b9f834f-7edf-4dd0-abc8-8cf9813dc7bd', 'Huy', 'Nguyễn', NULL, NULL, true, 51, '2025-05-16 00:30:20.961855', NULL, '2025-05-16 00:30:20.961855', NULL);
INSERT INTO public.user_account (user_id, first_name, last_name, gender, dob, active, role_id, created_at, created_by, updated_at, updated_by) VALUES ('5bfb239a-b099-4866-ba77-2f8006dac4ba', 'Nguyen', 'Hung', 'MALE', NULL, true, 1, '2025-05-12 09:51:10.73038', NULL, '2025-05-12 09:51:10.73038', NULL);


--
-- Data for Name: user_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14001, 1051, 25151, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14051, 1051, 25201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14101, 1051, 27701, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14151, 1051, 27651, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14201, 1051, 27751, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14251, 1051, 27851, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14301, 1051, 27901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14351, 1051, 27951, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14401, 1051, 28001, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14451, 1051, 25751, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14501, 1051, 25801, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14551, 1051, 25851, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (14601, 1051, 25901, 'D');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2001, 701, 25151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2051, 701, 25201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2101, 701, 25251, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2151, 701, 25301, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2201, 701, 25351, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2251, 701, 25401, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2301, 701, 25451, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2351, 701, 25501, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2401, 701, 25551, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2451, 701, 25601, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2501, 701, 37051, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2551, 701, 37101, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2601, 701, 25651, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2651, 701, 25701, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2701, 701, 25751, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2751, 701, 25801, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2801, 701, 25851, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2851, 701, 25901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2901, 701, 25951, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (2951, 701, 26001, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3001, 701, 26051, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3051, 701, 26101, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3101, 701, 27651, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3151, 701, 27701, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3201, 701, 27751, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3251, 701, 27801, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3301, 701, 27851, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3351, 701, 27901, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3401, 701, 27951, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3451, 701, 28001, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3501, 701, 28051, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3551, 701, 28101, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3601, 701, 28151, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3651, 701, 28201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3701, 701, 28251, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3751, 701, 28301, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3801, 701, 28351, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3851, 701, 28401, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3901, 701, 28451, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (3951, 701, 28501, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4001, 701, 28551, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4051, 701, 28601, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4101, 701, 28651, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4151, 701, 28701, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4201, 701, 28751, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4251, 701, 28801, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4301, 701, 28851, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4351, 701, 28901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4401, 701, 28951, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4451, 701, 29001, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4501, 701, 29051, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4551, 701, 29101, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4601, 701, 29151, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4651, 701, 29201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4701, 701, 29251, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4751, 701, 29301, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4801, 701, 29351, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4851, 701, 29401, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4901, 701, 29451, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (4951, 701, 29501, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5001, 701, 29551, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5051, 701, 29601, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5101, 701, 29651, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5151, 701, 29701, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5201, 701, 29751, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5251, 701, 29801, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5301, 701, 29851, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5351, 701, 29901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5401, 701, 29951, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5451, 701, 30001, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5501, 701, 30051, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5551, 701, 30101, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5601, 751, 25151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5651, 751, 25201, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5701, 751, 25251, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5751, 751, 25301, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5801, 751, 25351, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5851, 751, 25401, 'D');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5901, 751, 25451, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (5951, 751, 25501, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6001, 751, 25551, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6051, 751, 25601, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6101, 751, 37051, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6151, 751, 37101, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6201, 751, 25651, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6251, 751, 25701, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6301, 751, 25751, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6351, 751, 25801, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6401, 751, 25851, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6451, 751, 25901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6501, 751, 25951, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6551, 751, 26051, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6601, 751, 26101, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6651, 751, 26001, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6701, 751, 27651, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6751, 751, 27701, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6801, 751, 27801, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6851, 751, 27751, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6901, 751, 27901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (6951, 751, 27851, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7001, 751, 28001, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7051, 751, 27951, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7101, 751, 28051, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7151, 751, 28151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7201, 751, 28101, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7251, 751, 28201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7301, 751, 28251, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7351, 751, 28301, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7401, 751, 28401, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7451, 751, 28351, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7501, 751, 28451, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7551, 751, 28501, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7601, 751, 28551, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7651, 751, 28601, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7701, 751, 28651, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7751, 751, 28701, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7801, 751, 28751, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7851, 751, 28801, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7901, 751, 28851, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (7951, 751, 28901, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8001, 751, 28951, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8051, 751, 29001, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8101, 751, 29051, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8151, 751, 29101, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8201, 751, 29151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8251, 751, 29201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8301, 751, 29251, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8351, 751, 29301, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8401, 751, 29401, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8451, 751, 29351, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8501, 751, 29451, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8551, 751, 29501, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8601, 751, 29551, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8651, 751, 29601, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8701, 751, 29651, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8751, 751, 29701, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8801, 751, 29801, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8851, 751, 29751, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8901, 751, 29851, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (8951, 751, 29901, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9001, 751, 29951, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9051, 751, 30001, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9101, 751, 30051, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9151, 751, 30101, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9201, 801, 25151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9251, 801, 25201, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9301, 801, 25251, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9351, 851, 25151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9401, 851, 25201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9451, 851, 25251, 'D');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9501, 851, 25301, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9551, 851, 25351, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9601, 901, 25151, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9651, 901, 25201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9701, 901, 25251, 'A');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9751, 951, 25151, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9801, 951, 25201, 'C');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9851, 951, 25251, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9901, 951, 25301, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (9951, 1001, 25151, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (10001, 1001, 25201, 'B');
INSERT INTO public.user_answer (answer_id, attempt_id, question_id, selected_answer) VALUES (10051, 1001, 25251, 'B');


--
-- Data for Name: user_login_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_login_data (user_id, username, password, email, phone_number) VALUES ('52317f07-685e-4188-a3b6-906e84dffd80', 'arkdev', '$2a$10$nCIrfWWooK4FEYdAapvPUOLpCQMsY5X2TDA7oAIb345FIeFPwrt9u', 'hoang1109@gmail.com', '0988676896');
INSERT INTO public.user_login_data (user_id, username, password, email, phone_number) VALUES ('18953ac6-298a-4cf5-8f8e-ba198a0fa25b', 'huyav', '$2a$10$vNw8PVB13df.UBCKoDmO/uCWXpZHdB55XxPs2vgjIkY9Ox3GrF1Ta', 'huyvuvi123@gmail.com', '0971561704');
INSERT INTO public.user_login_data (user_id, username, password, email, phone_number) VALUES ('d70289a7-79b5-428e-9823-7c25dd3e3520', 'hoai', '$2a$10$EdDFl3hDTSs1cV139BYSXuRWrWwtxx3I2tPkV5q1SXLAa/cg3WvWS', 'hoai@gmail.com', '0911312311');
INSERT INTO public.user_login_data (user_id, username, password, email, phone_number) VALUES ('0b9f834f-7edf-4dd0-abc8-8cf9813dc7bd', NULL, NULL, 'huymywork1908@gmail.com', NULL);
INSERT INTO public.user_login_data (user_id, username, password, email, phone_number) VALUES ('5bfb239a-b099-4866-ba77-2f8006dac4ba', 'admin', '$2a$10$YgPVugeLU9JTEtYy5Ra/OOTqTULn5NzayhULd1jlACHORMnYzUQ/G', 'hungavtb@gmail.com', '0912312311');


--
-- Data for Name: user_login_external; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: user_membership; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_membership (membership_id, user_id, plan_id, start_date, end_date, status, created_at, created_by, updated_at, updated_by) VALUES (1851, '52317f07-685e-4188-a3b6-906e84dffd80', 51, '2025-05-15 02:55:21.719419', '2025-08-13 02:55:21.719948', 'EXPIRED', '2025-05-15 02:55:21.751027', NULL, '2025-05-15 02:55:51.874092', NULL);


--
-- Data for Name: user_test_attempt; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_test_attempt (attempt_id, user_id, exam_id, total_score, start_time, end_time, complete, listening_score, reading_score, correct_count, incorrect_count, skip_count) VALUES (1051, '18953ac6-298a-4cf5-8f8e-ba198a0fa25b', 751, 25, '2025-05-21 13:54:35.606244', '2025-05-21 14:01:23.517064', true, 20, 5, 4, 9, '187');


--
-- Name: exam_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exam_seq', 851, true);


--
-- Name: external_provider_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.external_provider_seq', 1, false);


--
-- Name: lesson_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lesson_seq', 1, false);


--
-- Name: media_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.media_seq', 101, true);


--
-- Name: membership_plan_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membership_plan_seq', 1701, true);


--
-- Name: menu_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_seq', 1, false);


--
-- Name: module_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.module_seq', 1, false);


--
-- Name: part_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.part_seq', 1351, true);


--
-- Name: permission_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permission_seq', 1, false);


--
-- Name: question_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_seq', 37101, true);


--
-- Name: resource_access_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.resource_access_seq', 101, true);


--
-- Name: role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_seq', 51, true);


--
-- Name: score_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.score_seq', 5001, true);


--
-- Name: section_part_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.section_part_answer_seq', 1451, true);


--
-- Name: section_part_practice_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.section_part_practice_seq', 151, true);


--
-- Name: section_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.section_seq', 100502, true);


--
-- Name: test_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_seq', 1, false);


--
-- Name: user_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_answer_seq', 14601, true);


--
-- Name: user_membership_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_membership_seq', 1851, true);


--
-- Name: user_test_attempt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_test_attempt_seq', 1051, true);


--
-- PostgreSQL database dump complete
--

