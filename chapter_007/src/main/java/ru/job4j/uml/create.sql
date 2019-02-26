CREATE DATABASE application_system;

CREATE TABLE users (
	id serial primary key,
	login varchar(700),
	password varchar(700),
	roles_id integer references roles(id)
);

CREATE TABLE roles (
	id serial primary key,
	role varchar(700)
);

CREATE TABLE rules (
	id serial primary key,
	rule varchar(700)
);

CREATE TABLE roles_rules (
	id serial primary key,
	roles_id integer references roles(id),
	rules_id integer references rules(id)
);

CREATE TABLE items (
	id serial primary key,
	item varchar(2000),
	date_time timestamp,
	users_id integer references users(id),
	categorys_id integer references categorys(id),
	state_id integer references state(id)
);

CREATE TABLE comments (
	id serial primary key,
	comment text,
	date_time timestamp,
	items_id integer references items(id)
);

CREATE TABLE attachs (
	id serial primary key,
	filename varchar(2000),
	items_id integer references items(id)
);

CREATE TABLE state (
	id serial primary key,
	state varchar(700)
);

CREATE TABLE categorys (
	id serial primary key,
	category varchar(700)
);

INSERT INTO roles (role) VALUES
('administrator'),
('moderator'),
('guest');

INSERT INTO rules (rule) VALUES
('rule1'),
('rule2'),
('rule3'),
('rule4'),
('rule5');

INSERT INTO state (state) VALUES
('new'),
('work'),
('moderation'),
('finish');

INSERT INTO categorys (category) VALUES
('category1'),
('category2'),
('category3'),
('category4');

INSERT INTO users (login, password, roles_id) VALUES
('Roma', 'root', 1),
('Ivan', 'toor', 2),
('Tom', '1234', 3);

INSERT INTO items (item, date_time, users_id, categorys_id, state_id) VALUES
('item1', '2019-02-26 12:00:00', 3, 1, 1),
('item2', '2019-02-26 01:00:00', 3, 3, 1);

INSERT INTO comments (comment, date_time, items_id) VALUES
('item number 1', '2019-02-26 12:00:00', 1),
('item number 2', '2019-02-26 01:00:00', 2);

INSERT INTO attachs (filename, items_id) VALUES
('file1', 1),
('file2', 2);
