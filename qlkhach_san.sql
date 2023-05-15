-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: qlkhach_san
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` int DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gex1lmaqpg0ir5g1f5eftyaa1` (`username`),
  KEY `FKgdpd8e1vs356bjg287jr27pl7` (`role_id`),
  CONSTRAINT `FKgdpd8e1vs356bjg287jr27pl7` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (2,'',NULL,_binary '','sđsfdsffdsfdf',1,'$2a$10$b8nKq2DDwsiGDSa6.LMSP.fCCyRARngx93NGrRSmYlUCmS.twugBK','0377602001','272735854_457969049389431_129832850612814764_n.jpg','dfsdsffsdsfdsfd',2),(6,'Dinh To, Thuan Thanh, Bac Ninh','2022-06-16 00:00:00.000000',_binary '','Nguyễn Văn Lâm',1,'$2a$10$bzhl5q0F1joiwAI6X7gmTetzI3wAXdlnAj.cZu9ej/1K27xLi2DtS','0377602001','64303766_134788391047527_4431616372775179886_n.jpg','nguyenlambn',1),(9,'Dinh To, Thuan Thanh, Bac Ninh','2001-02-21 00:00:00.000000',_binary '','Nguyen Van Lam',1,'$2a$10$xUTa4T1F.swyDgk3SuaHFe8yjaOLN1qRyAuorkhBtlfGqYdkhDD/O','0377602001','272735854_457969049389431_129832850612814764_n.jpg','fsdfsfdsfdfsdsdfsf',1),(11,'Hà nội',NULL,_binary '','Tuấnn',1,'$2a$10$qcZuVDC.gFbTAQj8zwNeueMoa23bWun4VoSAXkaVTwEBHBwtSfVk.','0986776537',NULL,'admin00@gmail.com',1),(12,'Hà Nội','2001-02-23 00:00:00.000000',_binary '','letan',2,'$2a$10$WBRWt.6DOF7MLEX.0aLLmuo4btUzN1hJTa8pQGy1eW8CvWz3lBm9y','0987654321',NULL,'letan',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  `checkin_date` datetime(6) DEFAULT NULL,
  `checkout_date` datetime(6) DEFAULT NULL,
  `customer_id` int NOT NULL,
  `total_member` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq83pan5xy2a6rn0qsl9bckqai` (`room_id`),
  KEY `FKlnnelfsha11xmo2ndjq66fvro` (`customer_id`),
  CONSTRAINT `FKlnnelfsha11xmo2ndjq66fvro` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKq83pan5xy2a6rn0qsl9bckqai` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,'2022-08-19 09:31:20.000000','2022-08-20 09:31:20.000000','2022-08-23 09:31:20.000000',2,3,1),(3,8,'2022-08-23 00:37:56.000000','2022-08-19 00:00:00.000000','2022-08-27 00:00:00.000000',3,3,0),(4,3,'2022-08-23 08:33:38.000000','2022-08-23 08:33:38.000000','2022-08-27 08:33:38.000000',2,3,3);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cmt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dia_chi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gioi_tinh` int DEFAULT NULL,
  `sdt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` int DEFAULT NULL,
  `identity_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,NULL,NULL,'Nguyen Van Lam',NULL,NULL,'Dinh To, Thuan Thanh, Bac Ninh','nguyenlambn19@gmail.com',2,'3234243232433','0377602001'),(3,NULL,NULL,'Martin',NULL,NULL,'Dinh To, Thuan Thanh, Bac Ninh','nvlam222@gmail.com',1,'34242323432','0377602001'),(4,NULL,NULL,'Anh lâm pro',NULL,NULL,'dfdfđfs','badao221@fs.com',1,'032931332','0323434'),(5,NULL,NULL,'Nguyễn Văn Lâm',NULL,NULL,'2','a',1,'2','2'),(6,NULL,NULL,'Nguyễn Văn Huy',NULL,NULL,'a','a3',1,'a','a'),(7,NULL,NULL,'fsd',NULL,NULL,'23132','sdf',1,'23','322');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `payment` int DEFAULT NULL,
  `vat` float DEFAULT NULL,
  `account_id` int NOT NULL,
  `booking_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoevv8h8t2qgym9s0cn7oh069b` (`account_id`),
  KEY `FK4jd6uuk7w0d72riyre2w14fl7` (`booking_id`),
  CONSTRAINT `FK4jd6uuk7w0d72riyre2w14fl7` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
  CONSTRAINT `FKoevv8h8t2qgym9s0cn7oh069b` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'2022-08-23 10:21:33.000000',0,0,6,1),(2,'2022-08-23 11:30:20.000000',1,0,9,3);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Quản lý mọi thứ','Admin'),(2,'Quản lý dịch vụ, booking, lập hóa đơn cho khách hàng','Receptionist');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` int DEFAULT NULL,
  `category_id` int NOT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `floor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn0ob94w07v5iwboglwhu1crng` (`category_id`),
  CONSTRAINT `FKn0ob94w07v5iwboglwhu1crng` FOREIGN KEY (`category_id`) REFERENCES `room_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,0,1,NULL,1,1),(2,1,2,NULL,1,1),(3,0,1,NULL,1,1),(4,1,3,NULL,3,1),(5,0,3,'Sửa tủ',3,1),(6,1,4,'',1,1),(7,0,3,'',3,2),(8,1,1,'',1,2),(9,1,2,'',1,2),(10,1,1,'',1,2),(12,1,2,'',1,3),(13,0,2,'',1,3),(14,1,1,'',3,3),(15,1,3,'',1,3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_category`
--

DROP TABLE IF EXISTS `room_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amenities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `main_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `size` float DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_category`
--

LOCK TABLES `room_category` WRITE;
/*!40000 ALTER TABLE `room_category` DISABLE KEYS */;
INSERT INTO `room_category` VALUES (1,NULL,'Standard là hạng phòng tiêu chuẩn trong khách sạn, có diện tích nhỏ, thường nằm ở tầng thấp, không có view hoặc view không đẹp. Phòng STD được trang bị các vật dụng - trang thiết bị cơ bản và có mức giá thấp nhất.','Cac-loại-phong-khach-san-01.jpg','Standard',350000,0,NULL),(2,NULL,'So với Standard thì Superior là hạng phòng có chất lượng tốt hơn - diện tích rộng - trang thiết bị tiện nghi - view đẹp. Cũng vì thế mà mức giá thuê phòng SUP sẽ cao hơn.','Cac-loại-phong-khach-san-02.jpg','Superior',600000,0,NULL),(3,NULL,'Hạng phòng trên SUP 1 bậc là Deluxe. Đây là loại phòng cao cấp trong các khách sạn, chủ yếu nằm trên tầng cao với không gian rộng, nhiều thiết bị tiện nghi - view ngắm cảnh đẹp.','Cac-loại-phong-khach-san-03.jpg','Deluxe',1000000,0,NULL),(4,NULL,'SUT là hạng phòng cao cấp nhất của mỗi khách sạn. ','Cac-loại-phong-khach-san-04.jpg','Suite',1500000,0,NULL);
/*!40000 ALTER TABLE `room_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_category_image`
--

DROP TABLE IF EXISTS `room_category_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_category_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdqjornyh198bwk4rx3g1jsep8` (`category_id`),
  CONSTRAINT `FKdqjornyh198bwk4rx3g1jsep8` FOREIGN KEY (`category_id`) REFERENCES `room_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_category_image`
--

LOCK TABLES `room_category_image` WRITE;
/*!40000 ALTER TABLE `room_category_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_category_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (2,_binary '','Xe đưa đón sân bay',20000),(3,_binary '','Bơi 4 mùa',30000),(4,_binary '\0','Trông trẻ',60000),(5,_binary '','Thuê xe máy tự lái',20000),(6,_binary '','Spa',80000);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `score` int NOT NULL,
  `current_level` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `using_service`
--

DROP TABLE IF EXISTS `using_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `using_service` (
  `id` int NOT NULL,
  `booking_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `service_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3p6tdcdl6lp4je3u7qwusst07` (`booking_id`),
  KEY `FKot94vnpi04hexagyktnlfnn8e` (`service_id`),
  CONSTRAINT `FK3p6tdcdl6lp4je3u7qwusst07` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
  CONSTRAINT `FKot94vnpi04hexagyktnlfnn8e` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `using_service`
--

LOCK TABLES `using_service` WRITE;
/*!40000 ALTER TABLE `using_service` DISABLE KEYS */;
INSERT INTO `using_service` VALUES (1,1,2,2),(2,1,1,3),(3,3,2,6),(4,3,1,4);
/*!40000 ALTER TABLE `using_service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-15 11:01:43
