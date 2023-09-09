DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id INT          NOT NULL,
    genre_id  INT          NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);