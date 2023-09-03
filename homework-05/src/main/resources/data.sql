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