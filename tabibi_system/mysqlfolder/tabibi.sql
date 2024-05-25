-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tabibi
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `admin`
--
USE `tabibi`;
DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `aid` bigint DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `uid` int NOT NULL,
  `test` bigint NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `FKp6e24jsgnumfrfuq4malg7h05` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'abdelrahman',4,0);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appid` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `price` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `time` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cid` int DEFAULT NULL,
  `did` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `booked` int DEFAULT NULL,
  PRIMARY KEY (`appid`),
  KEY `FKe977df8kbfshg32iqc0nkwnl8` (`cid`),
  KEY `FKsutlb2h5rl8dhtyd67p3jj4o4` (`did`),
  KEY `FKsd7wxrc9xorv9ancmcn0alpwp` (`pid`),
  CONSTRAINT `FKe977df8kbfshg32iqc0nkwnl8` FOREIGN KEY (`cid`) REFERENCES `clinic` (`uid`),
  CONSTRAINT `FKsd7wxrc9xorv9ancmcn0alpwp` FOREIGN KEY (`pid`) REFERENCES `patient` (`uid`),
  CONSTRAINT `FKsutlb2h5rl8dhtyd67p3jj4o4` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billing` (
  `bid` bigint NOT NULL AUTO_INCREMENT,
  `amount` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `appid` bigint DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `FKt0g1wn0n06p0oyfbodmat2rma` (`appid`),
  KEY `FKihgr3hboglj5dyg4w2saic15s` (`pid`),
  CONSTRAINT `FKihgr3hboglj5dyg4w2saic15s` FOREIGN KEY (`pid`) REFERENCES `patient` (`uid`),
  CONSTRAINT `FKt0g1wn0n06p0oyfbodmat2rma` FOREIGN KEY (`appid`) REFERENCES `appointments` (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing`
--

LOCK TABLES `billing` WRITE;
/*!40000 ALTER TABLE `billing` DISABLE KEYS */;
/*!40000 ALTER TABLE `billing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic` (
  `cloc` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cnumber` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `workhrs` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `uid` int NOT NULL,
  `reviews` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `test` bigint NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `FKc7dh2g97q5rtw7dtpqg39ayiv` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES ('madinaty','alhuda','0122584900','24',2,NULL,0),('tagamo3','alhedaya','0114568881','24',3,NULL,0);
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnosis` (
  `diagnosis_id` bigint NOT NULL AUTO_INCREMENT,
  `diagnosis_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `uid` int DEFAULT NULL,
  PRIMARY KEY (`diagnosis_id`),
  KEY `FKf4s9s48579qnn75rhnxv3kp5f` (`uid`),
  CONSTRAINT `FKf4s9s48579qnn75rhnxv3kp5f` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis`
--

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dr`
--

DROP TABLE IF EXISTS `dr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dr` (
  `educ` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reviews` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `specialization` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `test` bigint NOT NULL,
  `uid` int NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `FKbmipuv5iw9tsg4ve05wtwcpex` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dr`
--

LOCK TABLES `dr` WRITE;
/*!40000 ALTER TABLE `dr` DISABLE KEYS */;
/*!40000 ALTER TABLE `dr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pages` (
  `pgid` bigint NOT NULL AUTO_INCREMENT,
  `class` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icons` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `linkaddress` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`pgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `age` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pid` bigint DEFAULT NULL,
  `test` bigint NOT NULL,
  `uid` int NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `FKhs822k52089mn45d1jg08pdtv` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('1 ssjmak','20','seif','M','sherif','012365594152',NULL,0,1),('nasr city','20','Abdelrahman','M','Samir','01286749530',NULL,0,5);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `sid` bigint NOT NULL AUTO_INCREMENT,
  `day` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `endtime` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `starttime` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `did` int DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `FKj2wq6knm3d0010hodujd3fyay` (`did`),
  CONSTRAINT `FKj2wq6knm3d0010hodujd3fyay` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) COLLATE utf8mb4_general_ci NOT NULL,
  `SESSION_ID` char(36) COLLATE utf8mb4_general_ci NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','ace2036e-4421-4179-bdf7-50f60ad52484',1715792766619,1715794473711,1800,1715796273711,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) COLLATE utf8mb4_general_ci NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','email',_binary '�\�\0t\0samirzzz@email.com'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','firstname',_binary '�\�\0t\0alhedaya'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','Location',_binary '�\�\0t\0tagamo3'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','name',_binary '�\�\0t\0abdelrahman'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','number',_binary '�\�\0t\0\n0114568881'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','uid',_binary '�\�\0sr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','usertype',_binary '�\�\0t\0admin'),('c4be6a56-32bd-4432-8341-0dbf7ae05fc1','usertypeID',_binary '�\�\0sr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0\0\0\0\0');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment` (
  `treat_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `treat_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `did` int DEFAULT NULL,
  PRIMARY KEY (`treat_id`),
  KEY `FKi5rvqj1rgiw4g1xt7mebs93jq` (`did`),
  CONSTRAINT `FKi5rvqj1rgiw4g1xt7mebs93jq` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `dob` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_acc`
--

DROP TABLE IF EXISTS `user_acc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_acc` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `usertype_id` bigint DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `FKmjx5nbhy5cpjx3npnoa0hjj3w` (`usertype_id`),
  CONSTRAINT `FKmjx5nbhy5cpjx3npnoa0hjj3w` FOREIGN KEY (`usertype_id`) REFERENCES `usertypes` (`utid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_acc`
--

LOCK TABLES `user_acc` WRITE;
/*!40000 ALTER TABLE `user_acc` DISABLE KEYS */;
INSERT INTO `user_acc` VALUES (1,'seifop@gmail.com',NULL,'$2a$12$H.1SXpaJNNbC9ADG2kBlAOEo0rQ.j8WeCA/D82usBqRPC1zzC6.Ca',4),(2,'alhuda@gmail.com',NULL,'12345678',3),(3,'alhedaya@gmail.com',NULL,'$2a$12$l81WlE27ba7fpXLHthlD0OVlN6gLd3mRvYL3B4Yn5OyTuB15NIX7m',3),(4,'samirzzz@email.com',NULL,'$2a$12$l81WlE27ba7fpXLHthlD0OVlN6gLd3mRvYL3B4Yn5OyTuB15NIX7m',1),(5,'abdelrahman.200300@gmail.com',NULL,'$2a$12$l81WlE27ba7fpXLHthlD0OVlN6gLd3mRvYL3B4Yn5OyTuB15NIX7m',4);
/*!40000 ALTER TABLE `user_acc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlog`
--

LOCK TABLES `userlog` WRITE;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype_pages`
--

DROP TABLE IF EXISTS `usertype_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype_pages` (
  `upid` bigint NOT NULL AUTO_INCREMENT,
  `pageid` bigint NOT NULL,
  `usertypeid` bigint NOT NULL,
  PRIMARY KEY (`upid`),
  KEY `FKmh4pgpvo8dqekjuscycwrpyny` (`pageid`),
  KEY `FKg8wfii8jl6v0pyro7lby22dcr` (`usertypeid`),
  CONSTRAINT `FKg8wfii8jl6v0pyro7lby22dcr` FOREIGN KEY (`usertypeid`) REFERENCES `usertypes` (`utid`),
  CONSTRAINT `FKmh4pgpvo8dqekjuscycwrpyny` FOREIGN KEY (`pageid`) REFERENCES `pages` (`pgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype_pages`
--

LOCK TABLES `usertype_pages` WRITE;
/*!40000 ALTER TABLE `usertype_pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertype_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertypes`
--

DROP TABLE IF EXISTS `usertypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertypes` (
  `utid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`utid`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertypes`
--

LOCK TABLES `usertypes` WRITE;
/*!40000 ALTER TABLE `usertypes` DISABLE KEYS */;
INSERT INTO `usertypes` VALUES (0,'default'),(1,'admin'),(2,'doctor'),(3,'clinic'),(4,'patient');
/*!40000 ALTER TABLE `usertypes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-15 20:43:27
