ALTER TABLE users ADD COLUMN role_id INT REFERENCES roles(id);