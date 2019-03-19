CREATE TABLE IF NOT EXISTS items (
	id serial primary key NOT NULL,
	name text,
	description text,
	create_item bigint
);