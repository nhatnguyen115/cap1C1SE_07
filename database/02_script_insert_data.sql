INSERT INTO role(role_type, description)
VALUES ('ADMIN', 'ALL PERMISSION'),
       ('USER', 'CUSTOM PERMISSION');

INSERT INTO module(module_id, module_name)
VALUES (100100, 'Practice L&R'),
    (100101, 'Practice S&W'),
    (100102, 'Test'),
    (100103, 'Vocabulary'),
    (100104, 'Grammar');

INSERT INTO section(section_id, module_id, section_name, description, order_number)
VALUES (100200, 100100, 'Section 1: Photographs', 'Four short statements regarding a photograph will be spoken only one time. Of these four statements, select the one. that best describes the photograph.', 1),
    (100201, 100100, 'Section 2: Question-Response', 'Three responses to one question or statement will be spoken only one time. Select the best response for the question.', 2),
    (100202, 100100, 'Section 3: Short Conversations', 'Conversations between two or three people will be spoken only one time. Listen to each conversation and select the best response for the question. There are three questions for each conversation.', 3),
    (100203, 100100, 'Section 4: Short Talks', 'Short talks such as announcements or narrations will be spoken only one time. Listen to each talk and select the best response for the question There are three questions for each talk.', 4),
    (100250, 100100, 'Section 5: Incomplete Sentences', 'Select the best answer of the four choices to complete the sentence.', 5),
    (100251, 100100, 'Section 6: Text Completion', 'Select the best answer of the four choices (words, phrases, or a sentence) to complete the text. There are four questions for each text.', 6),
    (100252, 100100, 'Section 7: Reading Comprehension', 'Read a range of different texts and select the best answer of the four choices. There are multiple questions for each text.', 7),
    (100300, 100101, 'Section 1: Read Aloud', 'In this section of the test, you will read aloud the text on the screen. You will have 45 seconds to prepare. Then you will have 45 seconds to read the text aloud.', 1),
    (100301, 100101, 'Section 2: Describe a Picture', 'In this section, you will describe the picture on your screen in as much detail as you can. You will have 45 seconds to prepare your response and 30 seconds to speak about the picture.', 2),
    (100302, 100101, 'Section 3: Respond to Questions', 'In this section, you will answer three questions. You will have three seconds to prepare after you hear each question, 15 seconds to respond to Q1 and 2, and 30 seconds to respond to Q3.', 3),
    (100303, 100101, 'Section 4: Respond to questions using information provided', 'In this section, you will answer three questions based on information provided. You will have 45s to read information before questions begin. You will have 3s to prepare and 15s to respond to Q1 and 2. You will hear Q3 two times and have 3s to prepare and 30s to respond to Q3.', 4),
    (100304, 100101, 'Section 5: Express an opinion', 'In this section, you will give your opinion about a specific topic. Be sure to say as much as you can in the time allowed. You will have 45 seconds to prepare. Then you will have 60 seconds to speak.', 5),
    (100350, 100101, 'Section 6: Write a sentence based on a picture', 'In this section, you will write ONE sentence that is based on a picture. With each picture, you will be given TWO words or phrases that you must use in your sentence. You can change the forms of words and use the words in any order.', 6),
    (100351, 100101, 'Section 7: Respond to a written request', 'In this section, you will show how well you can write a response to an e-mail.', 7),
    (100352, 100101, 'Section 8: Write an opinion essay', 'In this section, you will write an essay in response to a question that asks you to state, explain, and support your opinion on an issue. Typically, an effective essay will contain a minimum of 300 words.', 8);

INSERT INTO test(test_id, module_id, test_type, description)
VALUES (100201, 100102, 'Simulation Test', 'Computer-based simulation test with the format and testing interface as the actual test will help you carefully prepare for your test day.'),
    (100202, 100102, 'MINI TEST', 'Take mini test with the number of questions and time limit reduced by half'),
    (100203, 100102, 'FULL TEST', 'Take full test with the same number of questions and time limit as the actual test');

INSERT INTO menu(menu_id, menu_code, label, url, parent_id, order_number, status, item_id, icon)
VALUES (0, 'ROOT', 'Home', '/', -1, 0, true, null, ''),
    (200100, 'PRACTICE_LISTENING_READING', 'Practice L&R', '/modules/sections-listening-reading', 0, 1, true, 100100, ''),
    (200101, 'PRACTICE_SPEAKING_WRITING', 'Practice S&W', '/modules/sections-speaking-writing', 0, 2, true, 100101, ''),
    (200102, 'TEST', 'Test', '/modules/tests', 0, 3, true, 100102, ''),
    (200103, 'VOCABULARY', 'Vocabulary', '/modules/vocabularies', 0, 4, true, 100103, ''),
    (200104, 'GRAMMAR', 'Grammar', '/modules/grammars', 0, 5, true, 100104, ''),
    (300100, 'PHOTOGRAPHS', 'Section 1: Photographs', '/sections/photographs', 200100, 1, true, 100200, ''),
    (300101, 'QUESTION_RESPONSE', 'Section 2: Question-Response', '/sections/question-response', 200100, 2, true, 100201, ''),
    (300102, 'SHORT_CONVERSATIONS', 'Section 3: Short Conversations', '/sections/short-conversations', 200100, 3, true, 100202, ''),
    (300103, 'SHORT_TALKS', 'Section 4: Short Talks', '/sections/short-talks', 200100, 4, true, 100203, ''),
    (300150, 'INCOMPLETE_SENTENCES', 'Section 5: Incomplete Sentences', '/sections/incomplete-sentences', 200100, 5, true, 100250, ''),
    (300151, 'TEXT_COMPLETION', 'Section 6: Text Completion', '/sections/text-completion', 200100, 6, true, 100251, ''),
    (300152, 'READING_COMPREHENSION', 'Section 7: Reading Comprehension', '/sections/reading-comprehension', 200100, 7, true, 100252, ''),
    (300200, 'READ_ALOUD', 'Section 1: Read Aloud', '/sections/read-aloud', 200101, 1, true, 100300, ''),
    (300201, 'DESCRIBE_PICTURE', 'Section 2: Describe a Picture', '/sections/describe-questions', 200101, 2, true, 100301, ''),
    (300202, 'RESPOND_QUESTIONS', 'Section 3: Respond to Questions', '/sections/respond-questions', 200101, 3, true, 100302, ''),
    (300203, 'RESPOND_QUESTIONS_USING_INFORMATION_PROVIDED', 'Section 4: Respond to questions using information provided', '/sections/respond-questions-using-information-provided', 200101, 4, true, 100303, ''),
    (300204, 'EXPRESS_OPINION', 'Section 5: Express an opinion', '/sections/express-opinion', 200101, 5, true, 100304, ''),
    (300250, 'WRITE_SENTENCE_BASED_PICTURE', 'Section 6: Write a sentence based on a picture', '/sections/write-sentence-based-picture', 200101, 6, true, 100350, ''),
    (300251, 'RESPOND_WRITTEN_REQUEST', 'Section 7: Respond to a written request', '/sections/respond-written-request', 200101, 7, true, 100351, ''),
    (300252, 'WRITE_OPINION_ESSAY', 'Section 8: Write an opinion essay', '/sections/write-opinion-essay', 200101, 8, true, 100352, ''),
    (300300, 'SIMULATION_TEST', 'Simulation Test', '/tests/simulation-test', 200102, 1, true, 100201, ''),
    (300301, 'MINI_TEST', 'MINI TEST', '/tests/mini-test', 200102, 2, true, 100202, ''),
    (300302, 'FULL_TEST', 'FULL TEST', '/tests/full-test', 200102, 3, true, 100203, '');
