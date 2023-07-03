INSERT INTO rol (nombre) VALUES ('ADMIN');
INSERT INTO rol (nombre) VALUES ('CLIENTE');
INSERT INTO rol (nombre) VALUES ('SOCIO');

INSERT INTO usuario (rol_id, email, nombre, password) VALUES (1, 'admin@mail.com', 'pedro', '$2a$10$T0MdmL5BXsWmaPbNeudHCesEV5fXpeyr0dQsnmJ9/Bp9zy.fjkMaa');
INSERT INTO usuario (rol_id, email, nombre, password) VALUES (2, 'pruebacliete@mail.com', 'juan', '$2a$10$iVOjRYUl/5X3vbow2rE3Reu/Rcb9vdvawmZO.WYusV6x9gr7YqRUm');
INSERT INTO usuario (rol_id, email, nombre, password) VALUES (3, 'pruebasocio@mail.com', 'luis', '$2a$10$iVOjRYUl/5X3vbow2rE3Reu/Rcb9vdvawmZO.WYusV6x9gr7YqRUm'); 

INSERT INTO parqueadero (nombre, registro, cantidad_puestos) VALUES ('parqueadero1', 'asdey', 5);
INSERT INTO parqueadero (nombre, registro, cantidad_puestos) VALUES ('parqueadero2', 'asdfgey', 10);

INSERT INTO vehiculo (placa, usuario_id) Values ('xfd59s', 2);
INSERT INTO vehiculo (placa, usuario_id) Values ('xfdff59s', 1);
INSERT INTO vehiculo (placa, usuario_id) Values ('xfs', 3);
INSERT INTO vehiculo (placa, usuario_id) Values ('xfh9s', 2);
INSERT INTO vehiculo (placa, usuario_id) Values ('xdsfh9s', 1);
INSERT INTO vehiculo (placa, usuario_id) Values ('xfhqw9s',3);