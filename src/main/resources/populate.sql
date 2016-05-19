DELETE FROM `User`;
DELETE FROM `UserCredentials`;
DELETE FROM `Address`;
DELETE FROM `Item`;
DELETE FROM `Category`;
DELETE FROM `Item_Category`;

INSERT INTO `UserCredentials` VALUES ('quan','ROLE_USER',b'1','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');
INSERT INTO `User` VALUES (1,24,'Quan','M','Dao','quan');
INSERT INTO `Address`  VALUES (1,'fairfield', 'IA', '4th North', '52577',1);

INSERT INTO `UserCredentials` VALUES ('sammy','ROLE_ADMIN',b'1','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');
INSERT INTO `User` VALUES (2 ,29,'Chi Proeng','M','Dov','sammy');
INSERT INTO `Address` VALUES (2,'fairfield', 'IA', '4th North', '52577',2);

INSERT INTO `UserCredentials` VALUES ('friat','ROLE_USER',b'1','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');
INSERT INTO `User` VALUES (3,27,'friat','F','Hadush','friat');
INSERT INTO `Address`  VALUES (3,'California', 'LA', '4th North', '53456',3);

INSERT INTO `UserCredentials` VALUES ('beimnet','ROLE_ADMIN',b'1','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');
INSERT INTO `User` VALUES (4,29,'Beimnet','M','yimer','beimnet');
INSERT INTO `Address`  VALUES (4,'Cansus', 'MO', '4th North', '12345',4);

INSERT INTO `UserCredentials` VALUES ('david','ROLE_USER',b'1','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');
INSERT INTO `User` VALUES (5,29,'David','M','Bell','david');
INSERT INTO `Address`  VALUES (5,'Salt Lake', 'UT', '4th North', '65784',5);

INSERT INTO `Item` (id, name, description, price, order_id, seller_id) VALUES (1, 'bicycle', 'mountain bicycle for a cheap price', 100, NULL, 1);
INSERT INTO `Item` (id, name, description, price, order_id, seller_id) VALUES (2, 'iphone 6', 'clean looking iphone 6', 400, NULL, 2);
INSERT INTO `Item` (id, name, description, price, order_id, seller_id) VALUES (3, 'laptop', 'Toshiba satellite CORE i3', 500, NULL, 3);
INSERT INTO `Item` (id, name, description, price, order_id, seller_id) VALUES (4, 'Washer', 'Pair Maytag washer & dryer. Good condition with little rust spots on bottom of washer.', 500, NULL, 3);
INSERT INTO `Item` (id, name, description, price, order_id, seller_id) VALUES (5, 'Air Conditioner', '8,000 b t u air conditioner, works great,80.00,obo', 600, NULL, 4);
INSERT INTO `Item` (id, name, description, price, order_id, seller_id) VALUES (6, 'The best of me', 'The book is in a Great condition.', 50, NULL, 5);


INSERT INTO `Category` (id, name, description) VALUES (1, 'electronic', 'electronic devices');
INSERT INTO `Category` (id, name, description) VALUES (2, 'vehicle', 'car, motorbike, bicycle');
INSERT INTO `Category` (id, name, description) VALUES (3, 'appliances', 'refrigirator, Washer, Air Conditioner');
INSERT INTO `Category` (id, name, description) VALUES (4, 'books', 'The best of me, Meet in heaven, cook book');

INSERT INTO `Item_Category` (Item_id, Category_id) VALUES (1,2);
INSERT INTO `Item_Category` (Item_id, Category_id) VALUES (2,1);
INSERT INTO `Item_Category` (Item_id, Category_id) VALUES (3,1);
INSERT INTO `Item_Category` (Item_id, Category_id) VALUES (4,3);
INSERT INTO `Item_Category` (Item_id, Category_id) VALUES (5,3);
INSERT INTO `Item_Category` (Item_id, Category_id) VALUES (6,4);


