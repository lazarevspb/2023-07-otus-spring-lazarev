create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$10$CuKq7g3eOzsc3FKu8O7hoeo0IegTHCKHBKkkN3XdCCwIZw5p5xLVK', 'user@user.com'),
       ('admin', '$2a$10$qtXqk7/TXgpShsLFQ5n8IuM4THQyhQbBw0eRTCvpvnnyAMl.UH3c6', 'admin@admin.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);