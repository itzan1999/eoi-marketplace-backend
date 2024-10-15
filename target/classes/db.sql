-- Inserción de datos en la tabla 'usuario'
INSERT INTO usuario (id, nombre, password) VALUES (1, 'Juan', 'password123');
INSERT INTO usuario (id, nombre, password) VALUES (2, 'Maria', 'password456');

-- Inserción de datos en la tabla 'articulo'
INSERT INTO articulo (id, nombre, precio, stock) VALUES (1, 'Producto A', 10.0, 100);
INSERT INTO articulo (id, nombre, precio, stock) VALUES (2, 'Producto B', 20.0, 50);

-- Inserción de datos en la tabla 'pedido'
INSERT INTO pedido (id, fecha, nombre, id_user) VALUES (1, '2023-09-01', 'Pedido 1', 1);
INSERT INTO pedido (id, fecha, nombre, id_user) VALUES (2, '2023-09-02', 'Pedido 2', 2);

-- Inserción de datos en la tabla 'order_items'
INSERT INTO compra (id, cantidad, id_articulo, id_pedido) VALUES (1, 2, 1, 1); -- Pedido 1 tiene 2 unidades de Producto A
INSERT INTO compra (id, cantidad, id_articulo, id_pedido) VALUES (2, 3, 2, 2); -- Pedido 2 tiene 3 unidades de Producto B

-- -- Actualización de los totales en 'orders'
-- UPDATE orders SET total = (SELECT SUM(p.price * oi.quantity) FROM order_items oi JOIN products p ON oi.product_id = p.id WHERE oi.order_id = orders.id);

-- -- Actualización de las ventas de los productos
-- UPDATE articulo SET sales = (SELECT SUM(oi.quantity) FROM order_items oi WHERE oi.product_id = products.id);

-- -- Actualización del dinero gastado por los usuarios
-- UPDATE usuario SET money_expended = (SELECT SUM(o.total) FROM orders o WHERE o.user_id = users.id);