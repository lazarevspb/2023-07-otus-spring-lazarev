INSERT INTO genres (name, id)
VALUES ('Genre 1', 1);
--
INSERT INTO genres (name, id)
VALUES ('Genre 2', 2);
--
INSERT INTO authors (name, id)
VALUES ('Author 1', 1);
--
INSERT INTO authors (name, id)
VALUES ('Author 2', 2);

INSERT INTO books (title, author_id, genre_id)
VALUES ('Book 1', 1, 2);
INSERT INTO books (title, author_id, genre_id)
VALUES ('Book 2', '2', '2');

INSERT INTO comments (id, text, book_id)
VALUES (1, 'comment 1 book 1', 1),
       (2, 'comment 2 book 1', 1),
       (3, 'comment 3 book 1', 1),
       (4, 'comment_1 book 2', 2),
       (5, 'comment_2 book 2', 2);


