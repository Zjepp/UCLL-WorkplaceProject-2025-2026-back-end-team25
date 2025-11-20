DROP TABLE IF EXISTS user_question CASCADE;
DROP TABLE IF EXISTS question CASCADE;
DROP TABLE IF EXISTS answer CASCADE;
DROP TABLE IF EXISTS faq CASCADE;
DROP TABLE IF EXISTS user_table CASCADE;

CREATE TABLE user_table (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    score INT,
    role VARCHAR(255)
);

CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    question VARCHAR(255),
    type VARCHAR(255)
);

CREATE TABLE answer (
    id BIGSERIAL PRIMARY KEY,
    answer VARCHAR(255),
    vragen_id BIGINT REFERENCES question(id),
    correct BOOLEAN
);

CREATE TABLE user_question (
    user_id BIGINT,
    answer_id BIGINT,
    answer VARCHAR(255),
    PRIMARY KEY (user_id, answer_id),
    FOREIGN KEY (user_id) REFERENCES user_table(id),
    FOREIGN KEY (answer_id) REFERENCES answer(id)
);

CREATE TABLE faq (
    id BIGSERIAL PRIMARY KEY,
    question VARCHAR(500),
    answer VARCHAR(1000)
);
