DELETE FROM `User`;
DELETE FROM `UserCredentials`;
DELETE FROM `Item`;

INSERT INTO `UserCredentials` VALUES ('quan','ROLE_USER','','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m'),('sammy','ROLE_ADMIN','','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');

INSERT INTO `User` VALUES (1,24,'Chi Proeng','M','Dov','sammy'),(3,29,'Quan','M','Dao','quan');

INSERT INTO `Item` (name, description, price, order_id, seller_id) VALUES ('bicycle', 'mountain bicycle for a cheap price', 100, NULL, 1);
INSERT INTO `Item` (name, description, price, order_id, seller_id) VALUES ('iphone 6', 'clean looking iphone 6', 400, NULL, 1);

INSERT INTO `Category` (name, description) VALUES ('electronic', 'electronic devices');
INSERT INTO `Category` (name, description) VALUES ('vehicle', 'car, motorbike, bicycle');