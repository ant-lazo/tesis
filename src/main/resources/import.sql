INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(1, 'CL0001', 'omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(2, 'CL0002', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(3, 'CL0003', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(4, 'CL0004', 'omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(5, 'CL0005', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(6, 'CL0006', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(7, 'CL0007', 'omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(8, 'CL0008', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(9, 'CL0009', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(id, codigo, nombre, apellido, email, create_at, foto)VALUES(10, 'CL0010', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');


INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(1, 'PDT0001', 'RIVEVIDON 21 + 7 UND', 15.0, 10, 'MLC', '', NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(2, 'PDT0002', '11LGATE LUMI/WUITE FCO X250ML', 18.5, 10, 'XCD', '' , NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(3, 'PDT0003', '3-GEL SUSPENSION X20 SOBRES', 12.0, 10, 'XCD', '' , NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(4, 'PDT0004', 'AB BRONCOL 1200MG NF AMPOLLA', 5.0, 10, 'MLC', '', NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(5, 'PDT0005', 'ACEITE BABY OIL 50ML', 11.3, 10, 'ABC', '', NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(6, 'PDT0006', 'ACEITE DE COCO X30ML', 17.0, 10, 'MLC', '', NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(7, 'PDT0007', 'ACICLAV 250MG SUSPENCION 60ML', 3.5, 10, 'MLC', '', NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(8, 'PDT0008', 'SUPRADYN PRONATAL X 30 TAB', 19.0, 10, 'XCC', '', NOW());
INSERT INTO productos(id, codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES(9, 'PDT0009', 'REUMO FLEX NF POTE 45G', 7.3, 10, 'HHP', '', NOW());

INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(1, 'CMP0001', 'Comprobante farmacia 1', null, 1, '2021-10-31', 100, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(1, 1, 2);

INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(2, 'CMP0002', 'Comprobante farmacia 2', null, 1, '2021-10-31', 85.0, 0);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(1, 2, 2);

/*Prueba de facturas con cliente null*/
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(3, 'CMP0003', 'Comprobante farmacia 3', null, null, '2021-10-31', 150, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(2, 3, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(4, 'CMP0004', 'Comprobante farmacia 4', null, null, '2021-10-31', 170, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(3, 4, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(5, 'CMP0005', 'Comprobante farmacia 5', null, null, NOW(), 180, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(4, 5, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(6, 'CMP0006', 'Comprobante farmacia 6', null, null, NOW(), 195.5, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(5, 6, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(7, 'CMP0007', 'Comprobante farmacia 7', null, null, NOW(), 193.3, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(6, 7, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(8, 'CMP0008', 'Comprobante farmacia 8', null, null, NOW(), 250, 0);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(7, 8, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(9, 'CMP0009', 'Comprobante farmacia 9', null, null, NOW(), 750.3, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(8, 9, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(10, 'CMP0010', 'Comprobante farmacia 10', null, null, NOW(), 195.3, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(9, 10, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(11, 'CMP0011', 'Comprobante farmacia 11', null, null, NOW(), 280.5, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(10, 11, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(12, 'CMP0012', 'Comprobante farmacia 12', null, null, NOW(), 350.5, 0);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(9, 12, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(13, 'CMP0013', 'Comprobante farmacia 13', null, null, NOW(), 200.0, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(8, 13, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(14, 'CMP0014', 'Comprobante farmacia 14', null, null, NOW(), 150.0, 0);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(6, 14, 5);
INSERT INTO facturas(id, codigo, descripcion, observacion, cliente_id, create_at, preciototal, enabled)VALUES(15, 'CMP0015', 'Comprobante farmacia 15', null, null, NOW(), 15.5, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(5, 15, 5);


INSERT INTO users (id, username, password, enabled,nombre, apellido, telefono, email, foto) VALUES (1, 'admin','$2a$10$tpc.TWAQJLVnGBG7zd0ahO1znftsuyOc31pqu9msgPM8d/RcqYEdm',1,'karina','jorge','956398863','admin@gmail.com', '');

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_USER');

