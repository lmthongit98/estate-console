CREATE DATABASE  IF NOT EXISTS `javasql022022` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `javasql022022`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: javasql022022
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `assignment_building`
--

DROP TABLE IF EXISTS `assignment_building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_building` (
  `staff_id` bigint(20) NOT NULL,
  `building_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`,`building_id`,`staff_id`),
  KEY `FK9j2n5bw1rgp110o03q7a6jsfb` (`building_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_building`
--

LOCK TABLES `assignment_building` WRITE;
/*!40000 ALTER TABLE `assignment_building` DISABLE KEYS */;
INSERT INTO `assignment_building` VALUES (2,2,2),(2,3,8),(1,1,12),(2,1,13),(1,2,14);
/*!40000 ALTER TABLE `assignment_building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `brokeragefee` int(11) DEFAULT NULL,
  `carfee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `decorationtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deposit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `direction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `electricityfee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `floorarea` int(11) DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `linkofbuilding` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `managername` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `managerphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `motorbikefee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `numberofbasement` int(11) DEFAULT NULL,
  `overtimefee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `rentprice` int(11) DEFAULT NULL,
  `rentpricedescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `renttime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `servicefee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `structure` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `ward` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `waterfee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8lsralwnmpmlb745ek0gf3v00` (`district_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,200,NULL,NULL,'anh Thắng',NULL,NULL,'building towner',NULL,2,NULL,NULL,200,NULL,NULL,NULL,'hoàng hoa thám',NULL,'phường 13',NULL,1),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,400,NULL,NULL,'chị Hòa',NULL,NULL,'Cao Thang building',NULL,4,NULL,NULL,300,NULL,NULL,NULL,'lê hồng phong',NULL,'phường 10',NULL,1),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,300,NULL,NULL,'chú Sơn',NULL,NULL,'TMA Building',NULL,3,NULL,NULL,100,NULL,NULL,NULL,'lê văn sỹ',NULL,'phường 1',NULL,2),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'anh Tùng',NULL,NULL,'Center Buidling',NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,2),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cô Thảo',NULL,NULL,'Nam Cao Building',NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Ninh Kiều Building',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building_renttype`
--

DROP TABLE IF EXISTS `building_renttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building_renttype` (
  `building_id` bigint(20) NOT NULL,
  `renttype_id` bigint(20) NOT NULL,
  PRIMARY KEY (`building_id`,`renttype_id`),
  KEY `FKc93dsibiqxlk45kjkk2x5wp1j` (`renttype_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building_renttype`
--

LOCK TABLES `building_renttype` WRITE;
/*!40000 ALTER TABLE `building_renttype` DISABLE KEYS */;
INSERT INTO `building_renttype` VALUES (1,1),(2,2),(2,3),(3,1),(5,1);
/*!40000 ALTER TABLE `building_renttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,NULL,NULL,NULL,NULL,'tan-binh','Tân Bình'),(2,NULL,NULL,NULL,NULL,'phu-nhuan','Phú Nhuận');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentarea`
--

DROP TABLE IF EXISTS `rentarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `building_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2h9n08cj3i1cmi1isfugohoic` (`building_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentarea`
--

LOCK TABLES `rentarea` WRITE;
/*!40000 ALTER TABLE `rentarea` DISABLE KEYS */;
INSERT INTO `rentarea` VALUES (1,NULL,NULL,NULL,NULL,'200',1),(2,NULL,NULL,NULL,NULL,'300',2),(3,NULL,NULL,NULL,NULL,'400',3),(4,NULL,NULL,NULL,NULL,'400',2),(5,NULL,NULL,NULL,NULL,'400',1),(6,NULL,NULL,NULL,NULL,'500',5);
/*!40000 ALTER TABLE `rentarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renttype`
--

DROP TABLE IF EXISTS `renttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renttype`
--

LOCK TABLES `renttype` WRITE;
/*!40000 ALTER TABLE `renttype` DISABLE KEYS */;
INSERT INTO `renttype` VALUES (1,NULL,NULL,NULL,NULL,'tang-tret','tầng trệt'),(2,NULL,NULL,NULL,NULL,'nguyen-can','nguyên căn'),(3,NULL,NULL,NULL,NULL,'noi-that','nội thất');
/*!40000 ALTER TABLE `renttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c36say97xydpmgigg38qv5l2p` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,NULL,'Lê Minh Hiếu','123456',1,'lminhhieu'),(2,NULL,NULL,NULL,NULL,NULL,'Nguyễn Quyết Sinh','123456',1,'nqsinh'),(3,NULL,NULL,NULL,NULL,NULL,'Lý Minh Thông','123456',1,'lminhthong');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-25 11:13:46
