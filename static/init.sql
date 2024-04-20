CREATE DATABASE  IF NOT EXISTS `pbl5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
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
  `booking_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ticket_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `booking_date` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('Confirmed','Canceled') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `ticket_id` (`ticket_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` char(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `customer_email_key` (`email`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` char(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` char(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_of_birth` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('imb6Wl1xNx','C9DCHegnqC','Nhat Vyx','Nu','test1@gmail.com','0902301415','Đà Nẵng ',NULL,NULL);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sender_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `receiver_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message_content` text COLLATE utf8mb4_unicode_ci,
  `message_date` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message_type` enum('Customer','Employee') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `sender_id` (`sender_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenuestatistics`
--

DROP TABLE IF EXISTS `revenuestatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenuestatistics` (
  `id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revenue_amount` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenuestatistics`
--

LOCK TABLES `revenuestatistics` WRITE;
/*!40000 ALTER TABLE `revenuestatistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `revenuestatistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_name` enum('ADMIN','EMPLOYEE','CUSTOMER','PROVIDER') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('1','ADMIN'),('3','EMPLOYEE'),('4','CUSTOMER'),('2','PROVIDER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_request`
--

DROP TABLE IF EXISTS `schedule_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_request` (
  `request_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `schedule_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('Approved','Pending','Reject') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending',
  `created_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `updated_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('Create','Delete') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Create',
  PRIMARY KEY (`request_id`),
  KEY `schedule_request_schedule_id_fkey` (`schedule_id`),
  CONSTRAINT `schedule_request_schedule_id_fkey` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_request`
--

LOCK TABLES `schedule_request` WRITE;
/*!40000 ALTER TABLE `schedule_request` DISABLE KEYS */;
INSERT INTO `schedule_request` VALUES ('iu6rv1GTnE','Kn6cQF0f8_','Pending','1713618166','1713618166','Create');
/*!40000 ALTER TABLE `schedule_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedules` (
  `schedule_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `provider_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trip_code` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `departure_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `arrival_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estimated_travel_time` double NOT NULL,
  `seat_capacity` int NOT NULL,
  `seat_price` decimal(10,2) NOT NULL,
  `notes` text COLLATE utf8mb4_unicode_ci,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('Approved','Pending','Reject') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending',
  `updated_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `arrival_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `arrival_id` (`arrival_id`),
  KEY `departure_id` (`departure_id`),
  KEY `schedules_ibfk_1` (`provider_id`),
  CONSTRAINT `arrival_idfk_1` FOREIGN KEY (`arrival_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `departure_idfk_1` FOREIGN KEY (`departure_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`provider_id`) REFERENCES `ticketproviders` (`providers_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES ('Kn6cQF0f8_','w3akEkA20K','X0eLjya1XU','z1Hi-sCfmD','3aZm8R4XEE',2.5,60,3000.00,'nah','nah','1713618166','Pending','1713618166','1713334575','1713334575'),('pNcebYuZBn','w3akEkA20K','CFkUbWcD8e','z1Hi-sCfmD','3aZm8R4XEE',2.5,60,3000.00,'nah','nah','1713451844','Reject','1713451844','1713334575','1713334575'),('tPfqfWIQdz','w3akEkA20K','9HEWZad5CP','eSNOfLj4Ey','z1Hi-sCfmD',1.5,60,1750.00,'yah','yah','1713451375','Pending','1713451375','1713334575','1713334575');
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seatpositions`
--

DROP TABLE IF EXISTS `seatpositions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seatpositions` (
  `seat_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `schedule_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `seat_number` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` enum('Available','Booked') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `Schedule_id` (`schedule_id`),
  CONSTRAINT `seatpositions_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatpositions`
--

LOCK TABLES `seatpositions` WRITE;
/*!40000 ALTER TABLE `seatpositions` DISABLE KEYS */;
/*!40000 ALTER TABLE `seatpositions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seattickets`
--

DROP TABLE IF EXISTS `seattickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seattickets` (
  `seat_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tickets_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`seat_id`,`tickets_id`),
  KEY `Tickets_id` (`tickets_id`),
  CONSTRAINT `seattickets_ibfk_1` FOREIGN KEY (`seat_id`) REFERENCES `seatpositions` (`seat_id`),
  CONSTRAINT `seattickets_ibfk_2` FOREIGN KEY (`tickets_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seattickets`
--

LOCK TABLES `seattickets` WRITE;
/*!40000 ALTER TABLE `seattickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `seattickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `station_poin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES ('3aZm8R4XEE','Ho Chi Minh'),('eSNOfLj4Ey','Ha Noi'),('z1Hi-sCfmD','Da Nang');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketproviders`
--

DROP TABLE IF EXISTS `ticketproviders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketproviders` (
  `providers_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` char(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` char(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `provider_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contact_info` text COLLATE utf8mb4_unicode_ci,
  `is_confirmed` tinyint(1) DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`providers_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `ticketproviders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketproviders`
--

LOCK TABLES `ticketproviders` WRITE;
/*!40000 ALTER TABLE `ticketproviders` DISABLE KEYS */;
INSERT INTO `ticketproviders` VALUES ('w3akEkA20K','C9DCHegnqC','nah','test@gmail.com',NULL,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `ticketproviders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `ticket_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `schedule_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` enum('Sold','Available') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `schedule_id` (`schedule_id`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionhistory`
--

DROP TABLE IF EXISTS `transactionhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactionhistory` (
  `transaction_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ticket_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `transaction_date` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity_sold` int DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `ticket_id` (`ticket_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `transactionhistory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `transactionhistory_ibfk_2` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionhistory`
--

LOCK TABLES `transactionhistory` WRITE;
/*!40000 ALTER TABLE `transactionhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactionhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('C9DCHegnqC','test1','$2b$10$A.THHxk8/xIsaADDSg5dnOeqlVUyQIoHZQxgG.nOlOn2AiIpi6ypq','2'),('uAWWXTO6c9','nhatvy10102003','$2b$10$itNPZWkBwEecPEhiiwYYkua2ORCPKbnP/RPoJ.qQnNH8ywQbLJWn2','3');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-20 20:06:26
