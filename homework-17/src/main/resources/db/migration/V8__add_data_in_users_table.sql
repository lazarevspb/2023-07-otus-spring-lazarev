INSERT INTO users (username, password, email, role_id)
VALUES ('user', '$2y$10$dJ0r9d.Ipp6w6VSgZvvoyu8f1CDF1KQ95VF8ZxW/9bpgf2FgPuOPi', 'user@example.com', 2);

UPDATE users SET role_id = 1 WHERE id = 1;