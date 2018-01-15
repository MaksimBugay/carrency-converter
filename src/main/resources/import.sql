INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'USER');

insert into user (active, city, country, birth_date, email, name, password, street, zip_code) values (1, 'Tallinn', 'Estonia', '29-11-1978', 'bmv2mail@gmail.com', 'Maksim', '$2a$10$9UDBermDWdZ.vZO3.Yi5c.KEdKyyuoOqjBzlWbLb3xiM3T1z0K.kG', 'Läänemere', '13914');

insert into user_role(user_id, role_id) values(1, 2);