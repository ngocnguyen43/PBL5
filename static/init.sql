CREATE DATABASE  IF NOT EXISTS `pbl5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pbl5`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: pbl5
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `ticket_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `booking_date` datetime DEFAULT NULL,
  `status` enum('Confirmed','Canceled') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `user_id` (`user_id`),
  KEY `ticket_id` (`ticket_id`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES ('80001','10003','70001','2024-02-21 10:08:00','Confirmed');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `full_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` char(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` char(12) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('30001','10003','Customer Name','customer@example.com','0987654321','Position Title',NULL),('C750251126','U750251126','Nguyễn Tuấn Anh','tuananh111@gmail.com','0770022202','Quảng Ninh',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `full_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` char(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` char(12) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('20001','10002','Employee Name','nam','employee@example.com','1234567890','Position Title','2003-03-14',NULL),('E17107332','U17107332','Nguyễn Phạm Nhật Vỹ','nữ','nhatvy10@gmail.com','0900920002','Hòa Quý - Đà nẵng','2003-11-21','null'),('E17107333','U17107333','Nguyễn Viết Hoài Bảo','nam','hoaibao11@gmail.com','0900910001','Đà nẵng','2003-09-21','null'),('E171073839','U171073839','Trần Nhật Minh nâshdas','nam','nhatminh@gmail.com','0770022202','Quảng Ninh','2003-02-21','null');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `sender_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `receiver_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `message_content` text COLLATE utf8mb4_general_ci,
  `message_date` datetime DEFAULT NULL,
  `message_type` enum('khách hàng','Nhân viên') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('110001','10003','10002','Message content here','2024-03-14 11:00:00','khách hàng');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenuestatistics`
--

DROP TABLE IF EXISTS `revenuestatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenuestatistics` (
  `id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `date` date DEFAULT NULL,
  `revenue_amount` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenuestatistics`
--

LOCK TABLES `revenuestatistics` WRITE;
/*!40000 ALTER TABLE `revenuestatistics` DISABLE KEYS */;
INSERT INTO `revenuestatistics` VALUES ('100001','2024-03-14',1000.00);
/*!40000 ALTER TABLE `revenuestatistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `role_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('1','Admin'),('3','Customer'),('2','Employee'),('4','Ticket Provider');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedulerequest`
--

DROP TABLE IF EXISTS `schedulerequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedulerequest` (
  `request_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `provider_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `submission_date` date DEFAULT NULL,
  `status` enum('Approved','Pending') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `request_details` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`request_id`),
  KEY `provider_id` (`provider_id`),
  CONSTRAINT `schedulerequest_ibfk_1` FOREIGN KEY (`provider_id`) REFERENCES `ticketproviders` (`providers_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedulerequest`
--

LOCK TABLES `schedulerequest` WRITE;
/*!40000 ALTER TABLE `schedulerequest` DISABLE KEYS */;
INSERT INTO `schedulerequest` VALUES ('60001','40001','2024-03-15','Pending','Request details here');
/*!40000 ALTER TABLE `schedulerequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedules` (
  `schedule_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `provider_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `trip_code` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `departure_id` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `arrival_id` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `departure_datetime` datetime DEFAULT NULL,
  `estimated_travel_time` int DEFAULT NULL,
  `seat_capacity` int DEFAULT NULL,
  `seat_price` decimal(10,2) DEFAULT NULL,
  `notes` text COLLATE utf8mb4_general_ci,
  `photo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `departure_id` (`departure_id`),
  KEY `arrival_id` (`arrival_id`),
  KEY `schedules_ibfk_1` (`provider_id`),
  CONSTRAINT `arrival_idfk_1` FOREIGN KEY (`arrival_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `departure_idfk_1` FOREIGN KEY (`departure_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`provider_id`) REFERENCES `ticketproviders` (`providers_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES ('50001','40001','TRP001','HAN','DAN','2024-03-15 08:00:00',120,100,50.00,'Notes here',NULL);
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seatpositions`
--

DROP TABLE IF EXISTS `seatpositions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seatpositions` (
  `seat_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `schedule_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `seat_number` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` enum('Available','Booked') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`seat_id`) USING BTREE,
  KEY `Schedule_id` (`schedule_id`) USING BTREE,
  CONSTRAINT `seatpositions_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatpositions`
--

LOCK TABLES `seatpositions` WRITE;
/*!40000 ALTER TABLE `seatpositions` DISABLE KEYS */;
INSERT INTO `seatpositions` VALUES ('120001','50001',1,50.00,'Available');
/*!40000 ALTER TABLE `seatpositions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seattickets`
--

DROP TABLE IF EXISTS `seattickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seattickets` (
  `seat_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `tickets_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`seat_id`,`tickets_id`) USING BTREE,
  KEY `Tickets_id` (`tickets_id`) USING BTREE,
  CONSTRAINT `seattickets_ibfk_1` FOREIGN KEY (`seat_id`) REFERENCES `seatpositions` (`seat_id`),
  CONSTRAINT `seattickets_ibfk_2` FOREIGN KEY (`tickets_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seattickets`
--

LOCK TABLES `seattickets` WRITE;
/*!40000 ALTER TABLE `seattickets` DISABLE KEYS */;
INSERT INTO `seattickets` VALUES ('120001','70001');
/*!40000 ALTER TABLE `seattickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `station_poin` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES ('DAN','Đà Nẵng'),('HAN','Hà Nội');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketproviders`
--

DROP TABLE IF EXISTS `ticketproviders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketproviders` (
  `providers_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `full_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` char(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` char(12) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `provider_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `contact_info` text COLLATE utf8mb4_general_ci,
  `is_confirmed` tinyint(1) DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`providers_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `ticketproviders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketproviders`
--

LOCK TABLES `ticketproviders` WRITE;
/*!40000 ALTER TABLE `ticketproviders` DISABLE KEYS */;
INSERT INTO `ticketproviders` VALUES ('40001','10004','Provider Name','provider@example.com','9876543210','Position Title','Provider Co.','Contact info here',1,NULL);
/*!40000 ALTER TABLE `ticketproviders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `ticket_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `schedule_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` enum('Sold','Available') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `schedule_id` (`schedule_id`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES ('70001','50001',100,50.00,'Available',NULL);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionhistory`
--

DROP TABLE IF EXISTS `transactionhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactionhistory` (
  `transaction_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `ticket_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `transaction_date` datetime DEFAULT NULL,
  `quantity_sold` int DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `user_id` (`user_id`),
  KEY `ticket_id` (`ticket_id`),
  CONSTRAINT `transactionhistory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `transactionhistory_ibfk_2` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionhistory`
--

LOCK TABLES `transactionhistory` WRITE;
/*!40000 ALTER TABLE `transactionhistory` DISABLE KEYS */;
INSERT INTO `transactionhistory` VALUES ('90001','10003','70001','2024-03-14 10:00:00',2);
/*!40000 ALTER TABLE `transactionhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('10001','admin_user','admin_pass','1'),('10002','employee_user','employee_pass','2'),('10003','customer_user','customer_pass','3'),('10004','provider_user_rr','provider_pass_ss','4'),('U17107332','nhatvy','nhatvy','2'),('U17107333','hoaibao','hoaibao','2'),('U171073839','nhatminh','nhatminh','2'),('U750251126','customer01','customer05','3');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pbl5'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-06 10:05:11
