DELETE FROM `User`;
DELETE FROM `UserCredentials`;


INSERT INTO `UserCredentials` VALUES ('quan','ROLE_USER','','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m'),('sammy','ROLE_ADMIN','','$2a$10$0twQoQgdh8VYg4.vj1bBOe4sJqHazyl41ErAq/RN4ohLuHGLcKe9m');
INSERT INTO `User` VALUES (1,24,'Chi Proeng','M','Dov','sammy'),(3,29,'Quan','M','Dao','quan');