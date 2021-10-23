INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0001', 'omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0002', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0003', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0004', 'omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0005', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0006', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0007', 'omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0008', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0009', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(codigo, nombre, apellido, email, create_at, foto)VALUES('CL0010', 'flor','aguirre','ant.14sag@gmail.com','2020-05-20','');



INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0001', 'RIVEVIDON 21 + 7 UND', 15.0, 10, 'MLC', '', NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0002', '11LGATE LUMI/WUITE FCO X250ML', 18.5, 10, 'XCD', '' , NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0003', '3-GEL SUSPENSION X20 SOBRES', 12.0, 10, 'XCD', '' , NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0004', 'AB BRONCOL 1200MG NF AMPOLLA', 5.0, 10, 'MLC', '', NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0005', 'ACEITE BABY OIL 50ML', 11.3, 10, 'ABC', '', NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0006', 'ACEITE DE COCO X30ML', 17.0, 10, 'MLC', '', NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0007', 'ACICLAV 250MG SUSPENCION 60ML', 3.5, 10, 'MLC', '', NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0008', 'SUPRADYN PRONATAL X 30 TAB', 19.0, 10, 'XCC', '', NOW());
INSERT INTO productos(codigo, nombre, precio, stock, laboratorio, foto, create_at)VALUES('PDT0009', 'REUMO FLEX NF POTE 45G', 7.3, 10, 'HHP', '', NOW());

INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0001', 'Comprobante farmacia 1', null, 1, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(1, 1, 2);

/*Prueba de facturas con cliente null*/
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0002', 'Comprobante farmacia 2', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(2, 2, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0003', 'Comprobante farmacia 3', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(3, 3, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0004', 'Comprobante farmacia 4', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(4, 4, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0005', 'Comprobante farmacia 5', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(5, 5, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0006', 'Comprobante farmacia 6', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(6, 6, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0007', 'Comprobante farmacia 7', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(7, 7, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0008', 'Comprobante farmacia 8', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(8, 8, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0009', 'Comprobante farmacia 9', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(9, 9, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0010', 'Comprobante farmacia 10', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(10, 10, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0011', 'Comprobante farmacia 11', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(11, 11, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0012', 'Comprobante farmacia 12', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(12, 12, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0013', 'Comprobante farmacia 13', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(13, 13, 5);
INSERT INTO facturas(codigo, descripcion, observacion, cliente_id, create_at)VALUES('CMP0014', 'Comprobante farmacia 14', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(14, 14, 5);

INSERT INTO users (username, password, enabled,nombre, apellido, telefono, email, foto) VALUES ('lomar','$2a$10$fAIFeHMMLBpTSNDiqleli.pjdMx5Umk4qrPzxt9JuDGCik5UafCH.',1,'omar','lazo','955026134','omar@gmail.com', '');
INSERT INTO users (username, password, enabled,nombre, apellido, telefono, email, foto) VALUES ('admin','$2a$10$ym3oS46aAhPTFhCURYzO2u1msgxRbZbqEwBsS66G0UUHgS4Zga3cC',1,'karina','jorge','956398863','admin@gmail.com', '');
INSERT INTO users (username, password, enabled,nombre, apellido, telefono, email, foto) VALUES ('cgrabiela','$2a$10$ym3oS46aAhPTFhCURYzO2u1msgxRbZbqEwBsS66G0UUHgS4Zga3cC',1,'grabiela','correa','930289465','grabiela@gmail.com', '');

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (3,'ROLE_USER');