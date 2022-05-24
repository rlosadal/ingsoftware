INSERT INTO estado_solicitud (id,nombre) VALUES (1, 'NUEVA');
INSERT INTO estado_solicitud (id,nombre) VALUES (2, 'EN_PROGRESO');
INSERT INTO estado_solicitud (id,nombre) VALUES (3, 'RESUELTA');
INSERT INTO estado_solicitud (id,nombre) VALUES (4, 'CANCELADA');
INSERT INTO estado_solicitud (id,nombre) VALUES (5, 'CERRADA');

INSERT INTO tipo_solicitud (id,nombre,descripcion) VALUES (1, 'MANTO_SOFTWARE','Mantenimiento de software');
INSERT INTO tipo_solicitud (id,nombre,descripcion) VALUES (2, 'MANTO_HARDWARE','Mantenimiento de hardware');

INSERT INTO areas (id,nombre,abreviatura) VALUES (1, 'SISTEMAS','SYS');
INSERT INTO areas (id,nombre,abreviatura) VALUES (2, 'CALL_CENTER','CCTR');
INSERT INTO areas (id,nombre,abreviatura) VALUES (3, 'TECNOLOGIA','TEC');
INSERT INTO areas (id,nombre,abreviatura) VALUES (4, 'DEPOSITO','DPTO');
INSERT INTO areas (id,nombre,abreviatura) VALUES (5, 'FINANZAS','FNZ');


INSERT INTO roles (id,role,nombre) VALUES (1, 'ADMIN', 'ADMINISTRADOR');
INSERT INTO roles (id,role,nombre) VALUES (2, 'HELP_DESK', 'MESA_DE_AYUDA');
INSERT INTO roles (id,role,nombre) VALUES (3, 'SUPPORT', 'SOPORTE_TECNICO');
INSERT INTO roles (id,role,nombre) VALUES (4, 'USER', 'USUARIO_COMUN');


INSERT INTO usuarios (id, usuario, role_id, area_id, email, password, apellido, nombres, nombre_completo) VALUES(1, 'mvega', 1 , 1, 'mvega@gmail.com', '$2a$12$k5i/Bv1QC1DIc3kpMWBC8uDIkeZ5ug3Zzb5OBp8clJZ2j8BKigJPW', 'Vega', 'Martin','Vega, Martin');
INSERT INTO usuarios (id, usuario, role_id, area_id, email, password, apellido, nombres, nombre_completo) VALUES(2, 'alozada', 1 , 1, 'alozada@gmail.com', '$2a$12$k5i/Bv1QC1DIc3kpMWBC8uDIkeZ5ug3Zzb5OBp8clJZ2j8BKigJPW', 'Lozada', 'Andres','Lozada, Andres');
INSERT INTO usuarios (id, usuario, role_id, area_id, email, password, apellido, nombres, nombre_completo) VALUES(3, 'helpdesk01', 2, 2, 'helpdesk01@gmail.com', '$2a$12$k5i/Bv1QC1DIc3kpMWBC8uDIkeZ5ug3Zzb5OBp8clJZ2j8BKigJPW', 'Apellido HelpDesk01', 'Nombres HelpDesk01','Apellido HelpDesk01, Nombres HelpDesk01');
INSERT INTO usuarios (id, usuario, role_id, area_id, email, password, apellido, nombres, nombre_completo) VALUES(4, 'soporte01', 3, 3, 'soporte01@gmail.com', '$2a$12$k5i/Bv1QC1DIc3kpMWBC8uDIkeZ5ug3Zzb5OBp8clJZ2j8BKigJPW', 'Apellido Soporte01', 'Nombres Soportek01','Apellido Soporte01, Nombres Soporte01');
INSERT INTO usuarios (id, usuario, role_id, area_id, email, password, apellido, nombres, nombre_completo) VALUES(5, 'gerente01', 4, 5, 'gerente01@gmail.com', '$2a$12$k5i/Bv1QC1DIc3kpMWBC8uDIkeZ5ug3Zzb5OBp8clJZ2j8BKigJPW', 'Apellido Gerente01', 'Nombres Gerente01','Apellido Gerente01, Nombres Gerente01');

ALTER SEQUENCE PUBLIC.estado_solicitud_seq restart WITH 6;
ALTER SEQUENCE PUBLIC.tipo_solicitud_seq restart WITH 3;
ALTER SEQUENCE PUBLIC.area_seq restart WITH 6;
ALTER SEQUENCE PUBLIC.role_seq restart WITH 5;
ALTER SEQUENCE PUBLIC.usuario_seq restart WITH 6;

/*select nextval('solicitud_seq')*/

/* encriptar y desencriptar una password*/
/* https://bcrypt-generator.com/*/
