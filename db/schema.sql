CREATE TABLE if not exists types (
	id serial PRIMARY key,
	name VARCHAR(255)
);

create table if not exists rules (
	id serial primary key,
	name VARCHAR(255)
);

create table if not exists accident (
	id serial primary key,
	name VARCHAR(255),
	text VARCHAR(255),
	address VARCHAR(255),
	type_id int REFERENCES types(id)
);

create table if not exists accident_rules (
	accident_id int REFERENCES accident(id),
	rule_id int REFERENCES rules(id)
);


insert into types (name) values ('Две машины');
INSERT into types (name) VALUES ('Машина и человек');
INSERT into types (name) values ('Машина и велосипед');

insert into rules (name) values ('Статья. 1');
insert into rules (name) values ('Статья. 2');
insert into rules (name) values ('Статья. 3');