SELECT * FROM product INNER JOIN type ON product.type_id = type.id WHERE type.name = 'СЫР';

SELECT * FROM product WHERE name LIKE '%мороженное%';

SELECT * FROM product WHERE expired_date > date_trunc('month', now()) + ('1 month');

SELECT * FROM product WHERE price = (SELECT max(price) FROM product);

SELECT type.name, count(product.id) FROM product INNER JOIN type ON product.type_id = type.id GROUP BY type.name;

SELECT * FROM product INNER JOIN type ON product.type_id = type.id WHERE type.name IN ('СЫР', 'МОЛОКО');

SELECT type.name FROM product INNER JOIN type ON product.type_id = type.id GROUP BY type.name HAVING count(product.id) < 10;

SELECT product.name, type.name FROM product INNER JOIN type ON product.type_id = type.id;