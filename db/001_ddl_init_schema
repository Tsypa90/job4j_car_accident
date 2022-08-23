CREATE TABLE if not exists types (
	id serial PRIMARY key,
	name text
);

create table if not exists rules (
	id serial primary key,
	name text
);

create table if not exists accident (
	id serial primary key,
	name text,
	text text,
	address text,
	type_id int REFERENCES types(id)
);

create table if not exists accident_rules (
	accident_id int REFERENCES accident(id),
	rule_id int REFERENCES rules(id)
);