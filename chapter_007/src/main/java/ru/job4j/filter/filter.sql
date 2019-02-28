--Написать запрос получение всех продуктов с типом "СЫР".
SELECT * FROM product INNER JOIN type ON product.type_id = type.id WHERE type.name = 'СЫР';

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное".
SELECT * FROM product WHERE name LIKE '%мороженное%';

--Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE expired_date > date_trunc('month', now()) + ('1 month');

--Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product WHERE price = (SELECT max(price) FROM product);

--Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT type.name, count(product.id) FROM product INNER JOIN type ON product.type_id = type.id GROUP BY type.name;

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
SELECT * FROM product INNER JOIN type ON product.type_id = type.id WHERE type.name IN ('СЫР', 'МОЛОКО');

--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type.name FROM product INNER JOIN type ON product.type_id = type.id GROUP BY type.name HAVING count(product.id) < 10;

Вывести все продукты и их тип.
SELECT product.name, type.name FROM product INNER JOIN type ON product.type_id = type.id;