CREATE TABLE company (
	id integer NOT NULL,
	name character varying,
	CONSTRAINT company_pkey PRIMARY KEY(id)
);

CREATE TABLE person (
	id integer NOT NULL,
	name character varying,
	company_id integer references company(id),
	CONSTRAINT person_pkey PRIMARY KEY(id)
);

INSERT INTO company (id, name) VALUES
(1, 'Company_1'),
(2, 'Company_2'),
(3, 'Company_3'),
(4, 'Company_4'),
(5, 'Company_5');

INSERT INTO person (id, name, company_id) VALUES
(1, 'Person_1', 5),
(2, 'Person_2', 3),
(3, 'Person_3', 3),
(4, 'Person_4', 1),
(5, 'Person_5', 5),
(6, 'Person_6', 5),
(7, 'Person_7', 2),
(8, 'Person_8', 4),
(9, 'Person_9', 1);

--names of all persons that are NOT in the company with id = 5
SELECT p.name, c.id FROM person AS p
INNER JOIN company AS c ON p.company_id = c.id
WHERE NOT c.id = 5;

--company name for each person
SELECT c.name, p.name
FROM company AS c
INNER JOIN person AS p ON c.id = p.company_id;

--Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name, (SELECT count(company_id) AS num FROM company AS c
INNER JOIN person AS p ON c.id = p.company_id
GROUP BY company_id ORDER BY num DESC LIMIT 1) AS number_persons
FROM company AS c
INNER JOIN person AS p ON c.id = p.company_id
GROUP BY c.name HAVING count(p.company_id) =
(SELECT count(company_id) AS num FROM company as c
INNER JOIN person AS p ON c.id = p.company_id
GROUP BY company_id ORDER BY num DESC LIMIT 1);