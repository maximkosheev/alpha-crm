-- Роли пользователей
INSERT INTO alpha.op_role (id, "role", description) VALUES(1, 'ROLE_ADMIN', 'Администратор организации');
INSERT INTO alpha.op_role (id, "role", description) VALUES(2, 'ROLE_USER', 'Пользователь');

-- Тарифы
INSERT INTO alpha.op_tariff(id, "name") VALUES(1, 'Демо');
INSERT INTO alpha.op_tariff(id, "name") VALUES(2, 'Эконом');
INSERT INTO alpha.op_tariff(id, "name") VALUES(3, 'Фриланс');
INSERT INTO alpha.op_tariff(id, "name") VALUES(4, 'Премиум');
INSERT INTO alpha.op_tariff(id, "name") VALUES(5, 'Индивидуальный');