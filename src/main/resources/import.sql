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

INSERT INTO productos(nombre, precio, create_at)VALUES('Camara digital Sony', 50000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Televisor curvo LG', 18000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Laptop Lenovo', 450000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Refrigeradora Samsung', 35000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Celular SONY', 50000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Lavadora Samsung', 50000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Celular LG', 50000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Celular Samsung', 50000 , NOW());
INSERT INTO productos(nombre, precio, create_at)VALUES('Equipo de Sonido Samsung', 50000 , NOW());

INSERT INTO facturas(descripcion, observacion, cliente_id, create_at)VALUES('Factura de equipo para sala', null, 1, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(2, 1, 2);

INSERT INTO users (username, password, enabled) VALUES ('omar','$2a$10$fAIFeHMMLBpTSNDiqleli.pjdMx5Umk4qrPzxt9JuDGCik5UafCH.',1);
INSERT INTO users (username, password, enabled) VALUES ('admin','$2a$10$ym3oS46aAhPTFhCURYzO2u1msgxRbZbqEwBsS66G0UUHgS4Zga3cC',1);

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_USER');