DROP TABLE IF EXISTS user_question CASCADE;
DROP TABLE IF EXISTS faq CASCADE;
DROP TABLE IF EXISTS answer CASCADE;
DROP TABLE IF EXISTS question CASCADE;
DROP TABLE IF EXISTS user_table CASCADE;

-- USERS
CREATE TABLE user_table (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    score INT,
    role VARCHAR(255)
);

-- QUESTIONS
CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    category VARCHAR(255)
    -- answer_id removed – circular reference made no sense
);

-- ANSWERS
CREATE TABLE answer (
    id BIGSERIAL PRIMARY KEY,
    answer VARCHAR(255) NOT NULL,
    vragen_id BIGINT REFERENCES question(id) ON DELETE CASCADE,
    correct BOOLEAN
);

-- USER ANSWERS
CREATE TABLE user_question (
    user_id BIGINT REFERENCES user_table(id) ON DELETE CASCADE,
    answer_id BIGINT REFERENCES answer(id) ON DELETE CASCADE,
    answer VARCHAR(255),
    PRIMARY KEY (user_id, answer_id)
);

-- FAQ
CREATE TABLE faq (
    id BIGSERIAL PRIMARY KEY,
    question VARCHAR(500),
    answer VARCHAR(1000)
);
