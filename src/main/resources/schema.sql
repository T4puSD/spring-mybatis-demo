CREATE TABLE IF NOT EXISTS article (
   id serial PRIMARY KEY,
   title varchar(255) NOT NULL,
   author varchar(100) NOT NULL
);