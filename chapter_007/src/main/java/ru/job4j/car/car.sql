CREATE TABLE car_body (
	id serial primary key,
	body_type varchar(700)
);

CREATE TABLE engine (
	id serial primary key,
	engine_type varchar(700)
);

CREATE TABLE transmission (
	id serial primary key,
	transmission_type varchar(700)
);

CREATE TABLE cars (
	id serial primary key,
	model varchar(700),
	body_id integer references car_body(id),
	engine_id integer references engine(id),
	transmission_id integer references transmission(id)
);

INSERT INTO car_body (body_type) VALUES
('Sedan'),
('Pickup'),
('Hatchback');

INSERT INTO engine (engine_type) VALUES
('Disel'),
('Benzine'),
('Turbo');

INSERT INTO transmission (transmission_type) VALUES
('Automatic'),
('Mechanics'),
('Robotic');

INSERT INTO cars (model, body_id, engine_id, transmission_id) VALUES
('BMW', 3, 1, 1),
('Mitsubishi', 1, 2, 2);

--Вывести список всех машин и все привязанные к ним детали.
SELECT c.model, cb.body_type, e.engine_type, t.transmission_type FROM cars AS c
INNER JOIN car_body AS cb ON c.body_id = cb.id
INNER JOIN engine AS e ON c.engine_id = e.id
INNER JOIN transmission AS t ON c.transmission_id = t.id;

--Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

SELECT cb.body_type FROM car_body AS cb
LEFT JOIN cars AS c ON c.body_id = cb.id
WHERE c.model is null;

SELECT e.engine_type FROM engine AS e
LEFT JOIN cars AS c ON c.body_id = e.id
WHERE c.model is null;

SELECT t.transmission_type FROM transmission AS t
LEFT JOIN cars AS c ON c.body_id = t.id
WHERE c.model is null;
