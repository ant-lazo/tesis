INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('omar','lazo','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto)VALUES('flor','aguirre','ant.14sag@gmail.com','2020-05-20','');

INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Camara digital Sony', 50000, 'Aqui va el laboratorio', '', NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Televisor curvo LG', 18000, '', '' , NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Laptop Lenovo', 450000, '', '' , NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Refrigeradora Samsung', 35000 , '', '', NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Celular SONY', 50000 , '', '', NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Lavadora Samsung', 50000 , '', '', NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Celular LG', 50000 , '', '', NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Celular Samsung', 50000 , '', '', NOW());
INSERT INTO productos(nombre, precio, laboratorio, foto, create_at)VALUES('Equipo de Sonido Samsung', 50000 , '', '', NOW());

INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 1', null, 1, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(1, 1, 2);

/*Prueba de facturas con cliente null*/
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 2', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(2, 2, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 3', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(3, 3, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 4', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(4, 4, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 5', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(5, 5, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 6', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(6, 6, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 7', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(7, 7, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 8', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(8, 8, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 9', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(9, 9, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 10', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(10, 10, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 11', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(11, 11, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 12', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(12, 12, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 13', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(13, 13, 5);
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala 14', null, null, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(14, 14, 5);

INSERT INTO users (username, password, enabled,nombre, apellido, telefono, foto) VALUES ('lomar','$2a$10$fAIFeHMMLBpTSNDiqleli.pjdMx5Umk4qrPzxt9JuDGCik5UafCH.',1,'omar','lazo','955026134','');
INSERT INTO users (username, password, enabled,nombre, apellido, telefono, foto) VALUES ('admin','$2a$10$ym3oS46aAhPTFhCURYzO2u1msgxRbZbqEwBsS66G0UUHgS4Zga3cC',1,'karina','jorge','956398863','');
INSERT INTO users (username, password, enabled,nombre, apellido, telefono, foto) VALUES ('cgrabiela','$2a$10$ym3oS46aAhPTFhCURYzO2u1msgxRbZbqEwBsS66G0UUHgS4Zga3cC',1,'grabiela','correa','930289465','');

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (3,'ROLE_USER');