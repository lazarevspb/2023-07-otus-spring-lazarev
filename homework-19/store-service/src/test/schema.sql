DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;

CREATE TABLE authors (
                         id   SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE genres (
                        id   SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
                       id        SERIAL PRIMARY KEY,
                       title     VARCHAR(255) NOT NULL,
                       author_id INT          NOT NULL,
                       genre_id  INT          NOT NULL,
                       FOREIGN KEY (author_id) REFERENCES authors (id),
                       FOREIGN KEY (genre_id) REFERENCES genres (id)
);
