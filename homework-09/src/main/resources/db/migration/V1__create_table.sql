DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS genres CASCADE;

CREATE TABLE authors
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE books
(
    author_id BIGINT       NOT NULL,
    genre_id  BIGINT       NOT NULL,
    id        BIGSERIAL,
    title     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE comments
(
    book_id BIGINT,
    id      BIGSERIAL,
    text    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE genres
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS books
    ADD CONSTRAINT fk_authors FOREIGN KEY (author_id) REFERENCES authors;
ALTER TABLE IF EXISTS books
    ADD CONSTRAINT fk_genres FOREIGN KEY (genre_id) REFERENCES genres;
ALTER TABLE IF EXISTS comments
    ADD CONSTRAINT fk_books FOREIGN KEY (book_id) REFERENCES books;
