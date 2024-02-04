create table events
(
    id             bigserial,
    timestamp      timestamp with time zone not null,
    operation_type varchar(255)             not null,
    user_id        bigserial,
    book_id        bigserial,
    primary key (id)
);
