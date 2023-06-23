CREATE TABLE IF NOT EXISTS author
(
    id   serial PRIMARY KEY,
    name varchar(100) NOT NULL,
    bio  text
);

CREATE TABLE IF NOT EXISTS article
(
    id        serial PRIMARY KEY,
    title     varchar(255) NOT NULL,
    author_id int          NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (id)
);
