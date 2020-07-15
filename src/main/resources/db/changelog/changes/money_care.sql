create table users (
id serial not null primary key,
email varchar(100) not null,
password varchar(255) not null,
active boolean not null,
role varchar(10) not null

);

create table incomes(
id serial not null primary key ,
category varchar(100) not null,
amount int not null,
user_id serial not null references users(id)
);

create table expensive(
id serial not null primary key,
category varchar(100) not null,
amount int not null,
user_id serial not null references users(id)
);

create table verification_token(
id serial not null primary key,
expiry_date timestamp,
token varchar(125) not null,
user_id serial not null references users(id)
);
