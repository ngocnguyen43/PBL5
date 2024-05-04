CREATE DATABASE  IF NOT EXISTS `pbl5_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pbl5_1`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: pbl5_1
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
-- Table structure for table `carriages`
--

DROP TABLE IF EXISTS `carriages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carriages` (
  `carriage_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `carriage_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_seats` int NOT NULL,
  `train_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`carriage_id`),
  KEY `Carriage_trainTrain_id_fkey` (`train_id`),
  CONSTRAINT `Carriage_trainTrain_id_fkey` FOREIGN KEY (`train_id`) REFERENCES `trains` (`train_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carriages`
--

LOCK TABLES `carriages` WRITE;
/*!40000 ALTER TABLE `carriages` DISABLE KEYS */;
INSERT INTO `carriages` VALUES ('_5Us4JUBFj','i5t6u',40,'SkWJ5jRo6v'),('16qon1N_jK','U7mm_',40,'Gafo29wXXU'),('3z126XQgZs','lz9HY',40,'Gafo29wXXU'),('9eAP0pgBwy','pcqZt',40,'ADfMVskI5e'),('bhaecMm9Ko','LzVHi',40,'IE4I-lmtUw'),('bRJw8DjvXU','vE87x',40,'ADfMVskI5e'),('c-iAewSfsa','l4woQ',40,'bAvIR6TC28'),('c3-qrOis1n','PRPfg',40,'VptTaMGE5o'),('cKmH3XwIWu','Y42qe',40,'SkWJ5jRo6v'),('eQj0PUJXlw','W4xRA',40,'SkWJ5jRo6v'),('FgrJOo8kgO','V8WHz',40,'ADfMVskI5e'),('g3IUml_eDu','aw54l',40,'IE4I-lmtUw'),('hAEfYEg7hr','pRzfx',40,'SkWJ5jRo6v'),('JBkhedX1ZB','A1',40,'VEeF982-r8'),('jqxkuxwz3g','_AHrb',40,'bAvIR6TC28'),('jW0sUOafYT','H36YA',40,'IE4I-lmtUw'),('n6I8kaYObj','VMuda',40,'IE4I-lmtUw'),('QaRFdN1E9k','-thea',40,'ADfMVskI5e'),('Qlz6jzV0PQ','Oo_Tb',40,'bAvIR6TC28'),('R9b_TBLPEs','GH6pR',40,'VptTaMGE5o'),('S_-xMDoHDk','_iFNv',40,'VptTaMGE5o'),('UAekrIR-ae','mTAH8',40,'SkWJ5jRo6v'),('ud-JlYrjuR','se5Yq',40,'VptTaMGE5o'),('UogiedsQzO','OATTN',40,'Gafo29wXXU'),('w02xITb6ah','I1ewa',40,'IE4I-lmtUw'),('wjqgRipamX','lTHHB',40,'Gafo29wXXU'),('Xe_swoacxr','tPVTw',40,'VptTaMGE5o'),('YRT8a0WKc8','s9Bip',40,'bAvIR6TC28'),('yumJqwgpOY','j8AQF',40,'bAvIR6TC28'),('z-gbVilEck','j6gfw',40,'Gafo29wXXU'),('zlIly_gTk2','iw7ZL',40,'ADfMVskI5e');
/*!40000 ALTER TABLE `carriages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
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
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_date` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('Confirmed','Canceled','Pending') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending',
  `paid_date` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `permission_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `permission_name` enum('READ_SELF','CREATE_SELF','UPDATE_SELF','DELETE_SELF','READ_USER','READ_USERS','CREATE_USER','UPDATE_USER','DELETE_USER','READ_TRAIN','READ_TRAINS','CREATE_TRAIN','UPDATE_TRAIN','DELETE_TRAIN','READ_SCHEDULE','READ_SCHEDULES','CREATE_SCHEDULE','UPDATE_SCHEDULE','DELETE_SCHEDULE','READ_CARRIAGE','READ_CARRIAGES','CREATE_CARRIAGE','UPDATE_CARRIAGE','DELETE_CARRIAGE','READ_ORDER','READ_ORDERS','CREATE_ORDER','UPDATE_ORDER','DELETE_ORDER','READ_SCHEDULE_REQUESTS','READ_SCHEDULE_REQUEST','CREATE_SCHEDULE_REQUEST','UPDATE_SCHEDULE_REQUEST','DELETE_SCHEDULE_REUQEST','UNKNOWN') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES ('0065ffef77','UPDATE_SCHEDULE_REQUEST'),('08e73638c7','READ_ORDERS'),('0bb2a4b4a6','READ_SELF'),('0fc8bfddff','READ_USERS'),('12182b8676','CREATE_SELF'),('136aef0363','READ_SCHEDULES'),('152fa17ade','DELETE_SELF'),('1915f266ce','READ_TRAIN'),('1b9467cc78','DELETE_ORDER'),('1bad3a0c13','CREATE_SCHEDULE_REQUEST'),('23932906a0','CREATE_TRAIN'),('287081210c','READ_CARRIAGES'),('30c010ae92','UNKNOWN'),('40fa431f92','UPDATE_ORDER'),('59878da373','DELETE_USER'),('5ec26461f7','DELETE_TRAIN'),('6bd46a35cd','READ_SCHEDULE'),('6db7b7fc6f','CREATE_CARRIAGE'),('7b8a60067b','READ_USER'),('84ad323692','UPDATE_CARRIAGE'),('87ae72d774','DELETE_CARRIAGE'),('89d9d09aaa','UPDATE_SCHEDULE'),('97b6515959','READ_TRAINS'),('9b8b024b78','UPDATE_SELF'),('9eb5162e2e','DELETE_SCHEDULE_REUQEST'),('a8efa9429c','DELETE_SCHEDULE'),('b10ad24175','READ_CARRIAGE'),('c4a0555809','READ_SCHEDULE_REQUEST'),('d2e92c73c7','READ_SCHEDULE_REQUESTS'),('d908123b21','UPDATE_USER'),('da189bccc0','CREATE_SCHEDULE'),('e016924c3c','CREATE_USER'),('ec5245de75','UPDATE_TRAIN'),('f09758deac','CREATE_ORDER'),('fa3775ecd1','READ_ORDER');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
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
  `role_name` enum('ADMIN','EMPLOYEE','PROVIDER','CUSTOMER') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CUSTOMER',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `roles_role_name_key` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('a6d4f02f06','ADMIN'),('14b99af117','EMPLOYEE'),('03b42fc676','PROVIDER'),('2ca9ba75f9','CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_requests`
--

DROP TABLE IF EXISTS `schedule_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_requests` (
  `request_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `schedule_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('Create','Delete') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Create',
  `status` enum('Approved','Pending','Reject') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending',
  `created_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `updated_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`request_id`),
  UNIQUE KEY `schedule_requests_schedule_id_key` (`schedule_id`),
  CONSTRAINT `schedule_requests_schedule_id_fkey` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_requests`
--

LOCK TABLES `schedule_requests` WRITE;
/*!40000 ALTER TABLE `schedule_requests` DISABLE KEYS */;
INSERT INTO `schedule_requests` VALUES ('0hpoYdOen9','YkFOhDRgpj','Create','Approved','1714572412','1714572412'),('4DAX772OBZ','31pu90q2-f','Create','Pending','1714569414','1714569414');
/*!40000 ALTER TABLE `schedule_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedules` (
  `schedule_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trip_code` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `departure_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `arrival_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estimated_travel_time` double NOT NULL,
  `notes` text COLLATE utf8mb4_unicode_ci,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('Approved','Pending','Reject') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending',
  `updated_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `arrival_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `train_id` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `arrival_id` (`arrival_id`),
  KEY `departure_id` (`departure_id`),
  KEY `schedules_train_id_fkey` (`train_id`),
  KEY `schedules_user_id_fkey` (`user_id`),
  CONSTRAINT `arrival_idfk_1` FOREIGN KEY (`arrival_id`) REFERENCES `stations` (`station_id`),
  CONSTRAINT `departure_idfk_1` FOREIGN KEY (`departure_id`) REFERENCES `stations` (`station_id`),
  CONSTRAINT `schedules_train_id_fkey` FOREIGN KEY (`train_id`) REFERENCES `trains` (`train_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `schedules_user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES ('31pu90q2-f','7vsw9UVRhh','0IxiEIW6IR','7XcjSrwmRB','SEdMavmyE9',2.5,'nah','nah','1714569414','Pending','1714569414','1713334575','1713334575','VEeF982-r8'),('YkFOhDRgpj','7vsw9UVRhh','nSZYjw9FuJ','7XcjSrwmRB','SEdMavmyE9',2.5,'nah','nah','1714572412','Pending','1714572412','1713334575','1713334575','VptTaMGE5o');
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seat_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `carriage_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `seat_number` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `seats_carriage_id_fkey` (`carriage_id`),
  CONSTRAINT `seats_carriage_id_fkey` FOREIGN KEY (`carriage_id`) REFERENCES `carriages` (`carriage_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES ('_8qPc22TU5','S_-xMDoHDk',36,124567.09),('_9mR9RHUW-','ud-JlYrjuR',26,124567.09),('_epzU5zcA9','R9b_TBLPEs',33,124567.09),('_W0YS7-9u_','S_-xMDoHDk',11,124567.09),('-8WzCi9ITc','S_-xMDoHDk',27,124567.09),('-m7fz2IoY6','Xe_swoacxr',2,124567.09),('0hze4LHK0z','R9b_TBLPEs',13,124567.09),('0RMswJSMvC','R9b_TBLPEs',22,124567.09),('1sAcipzEN1','Xe_swoacxr',14,124567.09),('1w4xd6iVPa','ud-JlYrjuR',7,124567.09),('1ZnUu65dct','R9b_TBLPEs',2,124567.09),('2__2wpFP3T','R9b_TBLPEs',35,124567.09),('2gXdD1Ja2Q','c3-qrOis1n',40,124567.09),('2tj3CAzumm','c3-qrOis1n',30,124567.09),('2vJpmMTjDw','R9b_TBLPEs',25,124567.09),('3AfO-Y-QVa','Xe_swoacxr',19,124567.09),('3sPVz5gTft','R9b_TBLPEs',18,124567.09),('4h6QyQWezr','Xe_swoacxr',7,124567.09),('4hG52jlXyW','R9b_TBLPEs',12,124567.09),('4MNsWN35G-','R9b_TBLPEs',32,124567.09),('4xDwaRcfDf','R9b_TBLPEs',19,124567.09),('4YvddX9iK3','ud-JlYrjuR',40,124567.09),('5IoGrk_1hc','R9b_TBLPEs',17,124567.09),('5kvNhUl7om','c3-qrOis1n',36,124567.09),('5ODl_83UIX','ud-JlYrjuR',5,124567.09),('5oVYqQFBkt','Xe_swoacxr',31,124567.09),('5Snt9rcTxR','R9b_TBLPEs',30,124567.09),('6cjIP7waFd','Xe_swoacxr',40,124567.09),('6DA5NWE1aL','c3-qrOis1n',25,124567.09),('7ddW3f2Q0k','c3-qrOis1n',33,124567.09),('7rV2W5opHq','S_-xMDoHDk',33,124567.09),('86use7tKWw','S_-xMDoHDk',28,124567.09),('8CEsZPaLyH','Xe_swoacxr',6,124567.09),('8SR8spKrQi','c3-qrOis1n',28,124567.09),('984CKe3WO4','Xe_swoacxr',24,124567.09),('9KKL9yT_Wl','R9b_TBLPEs',8,124567.09),('9poCuW_8w6','ud-JlYrjuR',1,124567.09),('A0Xtmf5Jq3','S_-xMDoHDk',31,124567.09),('alzbevl6b2','R9b_TBLPEs',5,124567.09),('ApaGft52cL','ud-JlYrjuR',22,124567.09),('APW0wN_JMZ','Xe_swoacxr',25,124567.09),('AtI7VxFt8e','c3-qrOis1n',5,124567.09),('atkW8FChsW','R9b_TBLPEs',14,124567.09),('B1hwg5mt4H','ud-JlYrjuR',21,124567.09),('bCh8IVxyjE','Xe_swoacxr',39,124567.09),('BKj8sAtvZn','c3-qrOis1n',13,124567.09),('bPpW8schx9','c3-qrOis1n',17,124567.09),('bTmmRWJs5T','ud-JlYrjuR',2,124567.09),('BYvTBVwxzt','S_-xMDoHDk',40,124567.09),('BZvOIF1SD_','c3-qrOis1n',31,124567.09),('cijh088qpn','S_-xMDoHDk',9,124567.09),('CkGFYhi3FV','S_-xMDoHDk',20,124567.09),('cQtKKAZifG','S_-xMDoHDk',5,124567.09),('cWfGbINpo0','S_-xMDoHDk',26,124567.09),('ddk58Y6nOa','ud-JlYrjuR',4,124567.09),('dGc_bpB72P','R9b_TBLPEs',28,124567.09),('DGdsnwjxBI','S_-xMDoHDk',29,124567.09),('dKWvhvuRuv','ud-JlYrjuR',30,124567.09),('dkXSNJ9-y3','R9b_TBLPEs',29,124567.09),('Doz8ygbvzl','c3-qrOis1n',6,124567.09),('DRWWZe4RlP','ud-JlYrjuR',34,124567.09),('DuFcNIECMN','Xe_swoacxr',33,124567.09),('E-m8cHn0rD','S_-xMDoHDk',3,124567.09),('e9MRWAZhnC','Xe_swoacxr',21,124567.09),('EDAA0vdh_J','S_-xMDoHDk',25,124567.09),('EhWNbiHIu1','S_-xMDoHDk',6,124567.09),('enDJQixQKQ','ud-JlYrjuR',15,124567.09),('EsPpiTbTm3','ud-JlYrjuR',19,124567.09),('eX3GP37xEj','Xe_swoacxr',18,124567.09),('EZLMFvDlUi','R9b_TBLPEs',21,124567.09),('f4uxsfeZkF','c3-qrOis1n',39,124567.09),('f4zRdvL2TK','R9b_TBLPEs',38,124567.09),('f8e1tdtrhH','S_-xMDoHDk',10,124567.09),('FcgNWvzF5J','R9b_TBLPEs',23,124567.09),('FGeiGyljdX','Xe_swoacxr',22,124567.09),('fgG3tHvzoy','c3-qrOis1n',21,124567.09),('Fi0Jcd-35W','S_-xMDoHDk',21,124567.09),('fkRL211dC7','ud-JlYrjuR',29,124567.09),('FRq71ury2k','Xe_swoacxr',13,124567.09),('FtWZpe3IsB','ud-JlYrjuR',18,124567.09),('FWalAYKIcX','Xe_swoacxr',10,124567.09),('FYy-ZbxkqF','Xe_swoacxr',16,124567.09),('g1V2mvFyfx','S_-xMDoHDk',13,124567.09),('g4TwMwS35P','c3-qrOis1n',20,124567.09),('gFHzUEB1ta','Xe_swoacxr',8,124567.09),('GLdkP5lykS','S_-xMDoHDk',23,124567.09),('GPPhZABjAA','Xe_swoacxr',4,124567.09),('gtd7uJVBnN','R9b_TBLPEs',34,124567.09),('h5ZbE5kfiY','Xe_swoacxr',23,124567.09),('hEj_MFFKCg','ud-JlYrjuR',28,124567.09),('HLohZGGD_I','R9b_TBLPEs',31,124567.09),('Hq7NZgGrw9','S_-xMDoHDk',38,124567.09),('I0zfJ7pSnE','Xe_swoacxr',34,124567.09),('Id8j7_xHAf','c3-qrOis1n',8,124567.09),('iDATfAqHJk','S_-xMDoHDk',16,124567.09),('ieL3EGbdqt','ud-JlYrjuR',20,124567.09),('isNWIZjpmk','Xe_swoacxr',29,124567.09),('j6iFMwjhy1','S_-xMDoHDk',17,124567.09),('j7QNVSC722','ud-JlYrjuR',14,124567.09),('j9NjR7q46X','S_-xMDoHDk',15,124567.09),('JAJwRg_1T9','R9b_TBLPEs',36,124567.09),('JDMex3Auuz','c3-qrOis1n',15,124567.09),('jGPtsqw6if','S_-xMDoHDk',8,124567.09),('jQUI6DFDNi','ud-JlYrjuR',31,124567.09),('JR272ZzQqV','S_-xMDoHDk',18,124567.09),('Jy5wkI3Uqi','ud-JlYrjuR',17,124567.09),('k04XCszXNy','c3-qrOis1n',38,124567.09),('KcdQIhiRAw','c3-qrOis1n',24,124567.09),('KIZj0hzisq','ud-JlYrjuR',32,124567.09),('KLS4CoRXrm','Xe_swoacxr',26,124567.09),('KxPVZM22a5','S_-xMDoHDk',34,124567.09),('L-wC6rZnw1','ud-JlYrjuR',6,124567.09),('l2hZX2_RF8','R9b_TBLPEs',40,124567.09),('L8iCWZE8wY','S_-xMDoHDk',1,124567.09),('LcVi1T2JT6','ud-JlYrjuR',10,124567.09),('lgGEXr8-Fq','ud-JlYrjuR',13,124567.09),('lGw2F4RjRw','c3-qrOis1n',9,124567.09),('lihSc7sSJ8','c3-qrOis1n',10,124567.09),('lOmFMKI3h3','ud-JlYrjuR',37,124567.09),('M3ZLj5Mlht','Xe_swoacxr',30,124567.09),('M56ShTBiqZ','ud-JlYrjuR',33,124567.09),('M5gmDB7jGG','ud-JlYrjuR',11,124567.09),('m65TPc_GFJ','ud-JlYrjuR',38,124567.09),('mDgQn7N664','R9b_TBLPEs',10,124567.09),('mgHXO10u-g','c3-qrOis1n',2,124567.09),('mlV_ly2yXP','Xe_swoacxr',15,124567.09),('MqhGISg1hH','R9b_TBLPEs',4,124567.09),('MXhESq-Nch','Xe_swoacxr',38,124567.09),('NHEAURziWN','R9b_TBLPEs',11,124567.09),('NqBrrsMMuQ','R9b_TBLPEs',24,124567.09),('nRyKOK0JNo','Xe_swoacxr',27,124567.09),('ntxPBFxkGH','ud-JlYrjuR',8,124567.09),('o2DpK6dw34','Xe_swoacxr',36,124567.09),('OHUOXt7PrI','S_-xMDoHDk',2,124567.09),('OKLOmOIYdF','ud-JlYrjuR',9,124567.09),('Okr2N7Iaw_','c3-qrOis1n',32,124567.09),('OMrBqXx-FY','S_-xMDoHDk',4,124567.09),('OpNrb8A65W','S_-xMDoHDk',24,124567.09),('oRDDxSCFkj','ud-JlYrjuR',27,124567.09),('P9UC5n6tA5','R9b_TBLPEs',9,124567.09),('pczK6dRSDn','Xe_swoacxr',5,124567.09),('Pn22bsTCjx','c3-qrOis1n',1,124567.09),('PYwcIBVptA','c3-qrOis1n',35,124567.09),('pyZZoRW15L','Xe_swoacxr',32,124567.09),('Pz5saXwfSB','c3-qrOis1n',27,124567.09),('Q-cnuhSTJV','c3-qrOis1n',18,124567.09),('Q6uSVbrYMl','Xe_swoacxr',12,124567.09),('q985dPcS_U','c3-qrOis1n',23,124567.09),('qNoAJhsxDI','Xe_swoacxr',28,124567.09),('Qp1HieZij5','R9b_TBLPEs',27,124567.09),('qsSOcPxRvR','R9b_TBLPEs',26,124567.09),('R3PJHS_nsG','ud-JlYrjuR',35,124567.09),('RcRJIhyn3U','c3-qrOis1n',14,124567.09),('RGbTx467sF','R9b_TBLPEs',16,124567.09),('rm3s4Is5oO','R9b_TBLPEs',37,124567.09),('RoPlytJxbQ','c3-qrOis1n',3,124567.09),('rrBpQ5d7mO','c3-qrOis1n',26,124567.09),('rYiOx9SVs4','S_-xMDoHDk',37,124567.09),('s-gx6r_a3Q','Xe_swoacxr',9,124567.09),('s6D9qslI7S','Xe_swoacxr',35,124567.09),('s90bkEyfue','ud-JlYrjuR',3,124567.09),('Sph3ngqLfW','S_-xMDoHDk',32,124567.09),('spuuH0gmiC','S_-xMDoHDk',22,124567.09),('SZIwhOFhA7','ud-JlYrjuR',39,124567.09),('T7pPFR_KsB','R9b_TBLPEs',7,124567.09),('TnDUnCp5u9','c3-qrOis1n',34,124567.09),('TnmgkHks6L','R9b_TBLPEs',15,124567.09),('TpbYd8ClZt','S_-xMDoHDk',14,124567.09),('tra-l0vQPK','ud-JlYrjuR',12,124567.09),('Tygqt9xhrY','S_-xMDoHDk',30,124567.09),('VJfKC6mHup','S_-xMDoHDk',12,124567.09),('vk2PuV0ANB','c3-qrOis1n',7,124567.09),('vPGSTOhA39','c3-qrOis1n',37,124567.09),('VQYKi5jMcS','Xe_swoacxr',20,124567.09),('w3SlU6Siht','c3-qrOis1n',16,124567.09),('wARAjwKDWz','ud-JlYrjuR',24,124567.09),('wcBbLb1Cv5','R9b_TBLPEs',1,124567.09),('wKojhvKyXI','c3-qrOis1n',19,124567.09),('wLuXnl9tXL','c3-qrOis1n',12,124567.09),('wMZhxhAdp_','ud-JlYrjuR',16,124567.09),('WoZbI8TBNx','ud-JlYrjuR',36,124567.09),('WUR5uNpgah','Xe_swoacxr',37,124567.09),('x_iNsmX0pf','S_-xMDoHDk',39,124567.09),('x4cE-I-sjL','R9b_TBLPEs',6,124567.09),('XYITuauDff','ud-JlYrjuR',23,124567.09),('xzE13KbBsl','c3-qrOis1n',22,124567.09),('yrlFLfJc16','R9b_TBLPEs',3,124567.09),('yUyyKdzrx9','c3-qrOis1n',4,124567.09),('Yz8j_y_oVf','R9b_TBLPEs',20,124567.09),('Z_YTZ22x-9','Xe_swoacxr',17,124567.09),('Z4xks4W18u','S_-xMDoHDk',19,124567.09),('z59-_AP0BE','S_-xMDoHDk',35,124567.09),('z6sP7k_YDm','Xe_swoacxr',11,124567.09),('Zbkps8mcAH','S_-xMDoHDk',7,124567.09),('zgFyUBz35z','ud-JlYrjuR',25,124567.09),('ZGwMvYt1Br','c3-qrOis1n',11,124567.09),('ZmJz2nbYS2','Xe_swoacxr',3,124567.09),('ZplyP8W6BW','R9b_TBLPEs',39,124567.09),('zy6_VBTHll','c3-qrOis1n',29,124567.09),('ZyBoY7Xd5I','Xe_swoacxr',1,124567.09);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats_tickets`
--

DROP TABLE IF EXISTS `seats_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats_tickets` (
  `ticket_id` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `seat_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `schedule_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('Available','Booked','MAINTENANCE') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Available',
  `carriage_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`schedule_id`,`seat_id`),
  KEY `seats_tickets_seat_id_fkey` (`seat_id`),
  KEY `seats_tickets_ticket_id_fkey` (`ticket_id`),
  CONSTRAINT `seats_tickets_schedule_id_fkey` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `seats_tickets_seat_id_fkey` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `seats_tickets_ticket_id_fkey` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats_tickets`
--

LOCK TABLES `seats_tickets` WRITE;
/*!40000 ALTER TABLE `seats_tickets` DISABLE KEYS */;
INSERT INTO `seats_tickets` VALUES (NULL,'_8qPc22TU5','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'_9mR9RHUW-','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'_epzU5zcA9','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'_W0YS7-9u_','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'-8WzCi9ITc','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'-m7fz2IoY6','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'0hze4LHK0z','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'0RMswJSMvC','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'1sAcipzEN1','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'1w4xd6iVPa','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'1ZnUu65dct','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'2__2wpFP3T','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'2gXdD1Ja2Q','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'2tj3CAzumm','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'2vJpmMTjDw','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'3AfO-Y-QVa','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'3sPVz5gTft','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'4h6QyQWezr','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'4hG52jlXyW','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'4MNsWN35G-','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'4xDwaRcfDf','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'4YvddX9iK3','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'5IoGrk_1hc','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'5kvNhUl7om','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'5ODl_83UIX','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'5oVYqQFBkt','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'5Snt9rcTxR','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'6cjIP7waFd','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'6DA5NWE1aL','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'7ddW3f2Q0k','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'7rV2W5opHq','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'86use7tKWw','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'8CEsZPaLyH','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'8SR8spKrQi','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'984CKe3WO4','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'9KKL9yT_Wl','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'9poCuW_8w6','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'A0Xtmf5Jq3','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'alzbevl6b2','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'ApaGft52cL','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'APW0wN_JMZ','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'AtI7VxFt8e','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'atkW8FChsW','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'B1hwg5mt4H','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'bCh8IVxyjE','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'BKj8sAtvZn','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'bPpW8schx9','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'bTmmRWJs5T','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'BYvTBVwxzt','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'BZvOIF1SD_','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'cijh088qpn','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'CkGFYhi3FV','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'cQtKKAZifG','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'cWfGbINpo0','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'ddk58Y6nOa','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'dGc_bpB72P','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'DGdsnwjxBI','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'dKWvhvuRuv','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'dkXSNJ9-y3','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'Doz8ygbvzl','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'DRWWZe4RlP','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'DuFcNIECMN','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'E-m8cHn0rD','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'e9MRWAZhnC','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'EDAA0vdh_J','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'EhWNbiHIu1','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'enDJQixQKQ','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'EsPpiTbTm3','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'eX3GP37xEj','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'EZLMFvDlUi','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'f4uxsfeZkF','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'f4zRdvL2TK','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'f8e1tdtrhH','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'FcgNWvzF5J','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'FGeiGyljdX','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'fgG3tHvzoy','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'Fi0Jcd-35W','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'fkRL211dC7','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'FRq71ury2k','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'FtWZpe3IsB','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'FWalAYKIcX','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'FYy-ZbxkqF','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'g1V2mvFyfx','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'g4TwMwS35P','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'gFHzUEB1ta','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'GLdkP5lykS','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'GPPhZABjAA','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'gtd7uJVBnN','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'h5ZbE5kfiY','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'hEj_MFFKCg','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'HLohZGGD_I','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'Hq7NZgGrw9','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'I0zfJ7pSnE','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'Id8j7_xHAf','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'iDATfAqHJk','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'ieL3EGbdqt','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'isNWIZjpmk','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'j6iFMwjhy1','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'j7QNVSC722','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'j9NjR7q46X','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'JAJwRg_1T9','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'JDMex3Auuz','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'jGPtsqw6if','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'jQUI6DFDNi','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'JR272ZzQqV','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'Jy5wkI3Uqi','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'k04XCszXNy','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'KcdQIhiRAw','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'KIZj0hzisq','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'KLS4CoRXrm','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'KxPVZM22a5','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'L-wC6rZnw1','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'l2hZX2_RF8','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'L8iCWZE8wY','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'LcVi1T2JT6','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'lgGEXr8-Fq','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'lGw2F4RjRw','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'lihSc7sSJ8','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'lOmFMKI3h3','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'M3ZLj5Mlht','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'M56ShTBiqZ','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'M5gmDB7jGG','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'m65TPc_GFJ','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'mDgQn7N664','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'mgHXO10u-g','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'mlV_ly2yXP','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'MqhGISg1hH','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'MXhESq-Nch','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'NHEAURziWN','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'NqBrrsMMuQ','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'nRyKOK0JNo','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'ntxPBFxkGH','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'o2DpK6dw34','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'OHUOXt7PrI','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'OKLOmOIYdF','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'Okr2N7Iaw_','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'OMrBqXx-FY','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'OpNrb8A65W','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'oRDDxSCFkj','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'P9UC5n6tA5','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'pczK6dRSDn','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'Pn22bsTCjx','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'PYwcIBVptA','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'pyZZoRW15L','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'Pz5saXwfSB','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'Q-cnuhSTJV','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'Q6uSVbrYMl','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'q985dPcS_U','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'qNoAJhsxDI','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'Qp1HieZij5','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'qsSOcPxRvR','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'R3PJHS_nsG','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'RcRJIhyn3U','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'RGbTx467sF','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'rm3s4Is5oO','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'RoPlytJxbQ','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'rrBpQ5d7mO','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'rYiOx9SVs4','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'s-gx6r_a3Q','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'s6D9qslI7S','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'s90bkEyfue','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'Sph3ngqLfW','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'spuuH0gmiC','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'SZIwhOFhA7','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'T7pPFR_KsB','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'TnDUnCp5u9','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'TnmgkHks6L','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'TpbYd8ClZt','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'tra-l0vQPK','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'Tygqt9xhrY','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'VJfKC6mHup','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'vk2PuV0ANB','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'vPGSTOhA39','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'VQYKi5jMcS','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'w3SlU6Siht','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'wARAjwKDWz','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'wcBbLb1Cv5','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'wKojhvKyXI','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'wLuXnl9tXL','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'wMZhxhAdp_','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'WoZbI8TBNx','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'WUR5uNpgah','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'x_iNsmX0pf','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'x4cE-I-sjL','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'XYITuauDff','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'xzE13KbBsl','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'yrlFLfJc16','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'yUyyKdzrx9','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'Yz8j_y_oVf','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'Z_YTZ22x-9','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'Z4xks4W18u','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'z59-_AP0BE','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'z6sP7k_YDm','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'Zbkps8mcAH','YkFOhDRgpj','Available','S_-xMDoHDk'),(NULL,'zgFyUBz35z','YkFOhDRgpj','Available','ud-JlYrjuR'),(NULL,'ZGwMvYt1Br','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'ZmJz2nbYS2','YkFOhDRgpj','Available','Xe_swoacxr'),(NULL,'ZplyP8W6BW','YkFOhDRgpj','Available','R9b_TBLPEs'),(NULL,'zy6_VBTHll','YkFOhDRgpj','Available','c3-qrOis1n'),(NULL,'ZyBoY7Xd5I','YkFOhDRgpj','Available','Xe_swoacxr');
/*!40000 ALTER TABLE `seats_tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stations`
--

DROP TABLE IF EXISTS `stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stations` (
  `station_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `station_poin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stations`
--

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES ('7XcjSrwmRB','Ha Noi'),('SEdMavmyE9','Da Nang'),('urhHi82tpI','Ho Chi Minh');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `ticket_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_id` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `photo` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `extra_fee` decimal(10,2) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `tickets_order_id_fkey` (`order_id`),
  CONSTRAINT `tickets_order_id_fkey` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE SET NULL ON UPDATE CASCADE
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
-- Table structure for table `trains`
--

DROP TABLE IF EXISTS `trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trains` (
  `train_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `train_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_carriage` int NOT NULL,
  `status` enum('MAINTENANCE','OPERATIONAL') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'OPERATIONAL',
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trains`
--

LOCK TABLES `trains` WRITE;
/*!40000 ALTER TABLE `trains` DISABLE KEYS */;
INSERT INTO `trains` VALUES ('ADfMVskI5e','HN2',5,'OPERATIONAL'),('bAvIR6TC28','HN2',5,'OPERATIONAL'),('Gafo29wXXU','HN2',5,'OPERATIONAL'),('IE4I-lmtUw','HN2',5,'OPERATIONAL'),('SkWJ5jRo6v','HN2',5,'OPERATIONAL'),('VEeF982-r8','HN1',10,'OPERATIONAL'),('VptTaMGE5o','HN2',5,'OPERATIONAL');
/*!40000 ALTER TABLE `trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permissions`
--

DROP TABLE IF EXISTS `user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_permissions` (
  `id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `permission_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('ACTIVE','INACTIVE') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_permissions_user_id_permission_id_key` (`user_id`,`permission_id`),
  KEY `user_permissions_permission_id_fkey` (`permission_id`),
  CONSTRAINT `user_permissions_permission_id_fkey` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_permissions_user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permissions`
--

LOCK TABLES `user_permissions` WRITE;
/*!40000 ALTER TABLE `user_permissions` DISABLE KEYS */;
INSERT INTO `user_permissions` VALUES ('--tLj54h3D','scF50ib9FV','e016924c3c','ACTIVE'),('-8hl5skU3H','scF50ib9FV','08e73638c7','ACTIVE'),('50I5XEqAfq','7vsw9UVRhh','89d9d09aaa','ACTIVE'),('6dLndCSO0C','scF50ib9FV','59878da373','ACTIVE'),('bBMNwpYueC','7vsw9UVRhh','9b8b024b78','ACTIVE'),('Boctp8C7zJ','scF50ib9FV','287081210c','ACTIVE'),('cemrFOMgb8','scF50ib9FV','84ad323692','ACTIVE'),('dWR2C2x45D','scF50ib9FV','97b6515959','ACTIVE'),('ElVeOx4vC_','scF50ib9FV','5ec26461f7','ACTIVE'),('FASKwtcAZL','scF50ib9FV','30c010ae92','ACTIVE'),('gAGpxFlpgP','scF50ib9FV','0bb2a4b4a6','ACTIVE'),('hjNe5pbw8h','scF50ib9FV','136aef0363','ACTIVE'),('iiHwyck1Wk','scF50ib9FV','12182b8676','ACTIVE'),('KfePtci0gH','scF50ib9FV','b10ad24175','ACTIVE'),('Mnus-Pg6zE','scF50ib9FV','9b8b024b78','ACTIVE'),('Oi9I69e4SU','7vsw9UVRhh','da189bccc0','ACTIVE'),('Omjt2Qo5W4','7vsw9UVRhh','6bd46a35cd','ACTIVE'),('OXEjHW37UN','scF50ib9FV','6bd46a35cd','ACTIVE'),('qaf4Y9f1SV','7vsw9UVRhh','12182b8676','ACTIVE'),('QS6l9l75vv','7vsw9UVRhh','136aef0363','ACTIVE'),('rspKjFmxqr','scF50ib9FV','0fc8bfddff','ACTIVE'),('t_mWXV2zCa','scF50ib9FV','1915f266ce','ACTIVE'),('TH3P_mOvci','7vsw9UVRhh','152fa17ade','ACTIVE'),('TMCxzDudPg','scF50ib9FV','23932906a0','ACTIVE'),('TRaS45VkEf','scF50ib9FV','152fa17ade','ACTIVE'),('v1mTYVu9JD','7vsw9UVRhh','a8efa9429c','ACTIVE'),('X7msGfXkEl','scF50ib9FV','d908123b21','ACTIVE'),('y_K7ASfk5i','scF50ib9FV','7b8a60067b','ACTIVE'),('Y0WiJTsQSu','scF50ib9FV','ec5245de75','ACTIVE'),('yfra-psEQ-','scF50ib9FV','87ae72d774','ACTIVE'),('ZM1Ak0WOct','scF50ib9FV','6db7b7fc6f','ACTIVE'),('zNEYm4VLXD','7vsw9UVRhh','0bb2a4b4a6','ACTIVE');
/*!40000 ALTER TABLE `user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `deleted_at` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `updated_at` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_email_key` (`email`),
  UNIQUE KEY `username` (`username`),
  KEY `users_role_id_fkey` (`role_id`),
  CONSTRAINT `users_role_id_fkey` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('7vsw9UVRhh','provider@gmail.com','abc',NULL,NULL,'provider','$2b$10$wNrv41iwKyDaNF9Ojheo/eXsbxRoeByyzeM9Fl/TAohwcJus3fIZm',NULL,'1714568380','1714568380','03b42fc676'),('scF50ib9FV','admin@gmail.com','abc',NULL,NULL,'admin','$2b$10$BWGmiwC0lD.lNnXHYzecJe7i23MPUZkVeR6Kzu9HXqg5ay9u/5JPa',NULL,'1714567210','1714567210','a6d4f02f06');
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

-- Dump completed on 2024-05-02  8:18:02
