-- MySQL dump 10.14  Distrib 5.5.41-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: fx
-- ------------------------------------------------------
-- Server version	5.5.41-MariaDB-1ubuntu0.14.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `n_users`
--

DROP TABLE IF EXISTS `n_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `n_users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `active` tinyint(4) NOT NULL,
  `can_edit_pages` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `n_users`
--

LOCK TABLES `n_users` WRITE;
/*!40000 ALTER TABLE `n_users` DISABLE KEYS */;
INSERT INTO `n_users` VALUES (1,'default','c1edcb27daaf8e427ccc0b61cba5f9cc','Default User / Do Not Erase',NULL,'2009-07-06 19:47:42',1,0),(2,'uwe','c1edcb27daaf8e427ccc0b61cba5f9cc','Uwe Meding','meding@yahoo.com','2009-07-06 19:48:48',1,1),(3,'johel','16ba41ca859e30d5a6d08f40f83e0ffb','Johel Contreras','unclaimedfunds@ufr.name','2009-07-06 21:25:21',1,1),(4,'sari','58dca5b4cf5d10d4d24210fd8ee96322','Sary Santillana',NULL,'2009-07-21 08:47:36',1,1),(5,'cecy','fff9b4e2ec2f001dcecfda9830f56f7f','Cecy Cristales',NULL,'2009-07-20 15:07:08',1,0),(6,'carolina','8f8b50b3a0cf63e5c528b6329296a162','Carolina Cristales',NULL,'2009-07-20 15:54:16',1,0),(7,'carlos c','7bf5f9c722ff13f770804e2f9949106b','Carlos Coreas',NULL,'2009-07-23 15:20:32',0,0),(8,'hilda','ff40baeedd4c4f6909dad27c29118dd5','Hilda Contreras',NULL,'2009-07-23 15:22:15',1,0),(9,'pollet','2eb9788de6ee522c86a1414b1f32a26d','Pollet',NULL,'2010-12-01 11:29:18',0,0),(10,'juan lopez','171f3243d3127d9ec1118f8d8fab89ca','Juan Lopez',NULL,'2011-01-13 15:18:48',0,1),(11,'polette','ac0553faee38996310674eae9cbc29ee','Polette M','customerservice@unclaimedfr.com','2013-04-22 09:40:32',1,1),(12,'dulce','ad9cb278d2db20db715e13ff087d251c','Dulce Alonso',NULL,'2013-12-26 09:37:45',1,0);
/*!40000 ALTER TABLE `n_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-28 12:36:05
