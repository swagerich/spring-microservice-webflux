create table users (
    id serial  primary key,
    name varchar(255) not null,
    lastname varchar(255) not null,
    balance integer not null,
    email varchar(255)
);


create table user_transaction (
    id serial primary key,
    user_id integer not null,
    amount integer not null,
    localDateTime timestamp not null,
    foreign key (user_id) references users(id) on delete cascade
);
