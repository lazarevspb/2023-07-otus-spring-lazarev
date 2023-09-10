DROP TABLE IF EXISTS comments;

CREATE TABLE comments
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(255) NOT NULL,
    book_id INT          NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id)
);