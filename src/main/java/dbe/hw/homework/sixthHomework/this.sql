CREATE TABLE shopping_cart (
    id SERIAL unique not null,
    client_name varchar(255),
    delivery_city varchar(255),
    delivery_street varchar(255),
    delivery_number varchar(10),
    order_status varchar(255),
    order_total decimal
)

CREATE TABLE shopping_cart_item (
    id SERIAL,
    quantity integer,
    unit_price decimal,
    total_price decimal,
    product_code integer,
    shopping_cart_id integer REFERENCES shopping_cart(id),
    product_name varchar(255)
)

DROP TABLE IF EXISTS shopping_cart CASCADE;
DROP TABLE shopping_cart_item;

INSERT INTO
shopping_cart (id, client_name, delivery_city, delivery_street, delivery_number, order_status, order_total)
VALUES
(2, 'Bobo', 'Timisoara', 'Boicesti', '4', 'completed', 23.3)
(3, 'Calin', 'Turda', 'Pieptanarilor', '2', 'in proces', 12.34),
(4, 'Borcea', 'Lehliu', 'Corvezi', '121111', 'not found', 0.609),
(5, 'Alina', 'Constanta', 'Cistita', '45', 'what', 5);

SELECT * FROM shopping_cart;
SELECT * FROM shopping_cart_item;

UPDATE shopping_cart SET delivery_city = 'Galati' WHERE client_name IN ('Ionut', 'Ciprian');
UPDATE shopping_cart SET delivery_city = 'Turda' WHERE client_name LIKE 'I%';

ALTER TABLE shopping_cart_item
ADD COLUMN product_name varchar(255);

INSERT INTO 
shopping_cart_item (quantity, unit_price, total_price, product_code, shopping_cart_id, product_name)
VALUES
(2, 1.244, 2.488, 42214, 20, 'cola'),
(8, 2, 16, 4552214, 20, 'twister clasic'),
(20, 3, 60, 42214, 20, 'orez');

(1000, 1, 1000, 42214, 3, 'punga malai'),
(20, 4, 80, 42214, 2, 'punga faina'),
(13, 3, 39, 42214, 2, 'kg mere')

INSERT INTO 
shopping_cart_item (quantity, unit_price, total_price, product_code, shopping_cart_id, product_name)
VALUES
(2, 1.244, 2.488, 42214, 3, 'fanta'),
(3, 2, 6, 4552214, 3, 'twister clasic'),
(20, 4, 80, 42214, 3, 'punga faina'),
(13, 3, 39, 42214, 3, 'kg mere'),
(1000, 1, 1000, 42214, 4, 'punga malai')

SELECT sci.product_name, sum(sci.total_price) as totals
FROM shopping_cart_item sci
GROUP BY sci.product_name
ORDER BY totals DESC;

SELECT sci.* FROM shopping_cart_item sci
INNER JOIN shopping_cart sc
ON sci.shopping_cart_id = sc.id
WHERE sc.delivery_city = 'Timisoara';