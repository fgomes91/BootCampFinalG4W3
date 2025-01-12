-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: bootcampw3
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `item_of_product`
--

DROP TABLE IF EXISTS `item_of_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_of_product` (
  `id_item_of_product` bigint NOT NULL AUTO_INCREMENT,
  `quantity` bigint DEFAULT NULL,
  `id_purchase_order` bigint DEFAULT NULL,
  `idsales_ad` bigint DEFAULT NULL,
  PRIMARY KEY (`id_item_of_product`),
  KEY `FKr2yedfbfjtur5ypiomhh22p00` (`id_purchase_order`),
  KEY `FK5j52vi3bjtfh0ln1prqq9cuka` (`idsales_ad`),
  CONSTRAINT `FK5j52vi3bjtfh0ln1prqq9cuka` FOREIGN KEY (`idsales_ad`) REFERENCES `sales_ad` (`idsales_ad`),
  CONSTRAINT `FKr2yedfbfjtur5ypiomhh22p00` FOREIGN KEY (`id_purchase_order`) REFERENCES `purchase_order` (`id_purchase_order`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_of_product`
--

LOCK TABLES `item_of_product` WRITE;
/*!40000 ALTER TABLE `item_of_product` DISABLE KEYS */;
INSERT INTO `item_of_product` VALUES (1,0,1,1),(2,0,1,2),(3,0,2,3),(4,0,2,4),(5,10,3,4),(6,40,3,5),(7,40,4,5),(8,30,4,6),(9,40,5,7),(10,70,5,8),(11,60,6,9),(12,100,6,10);
/*!40000 ALTER TABLE `item_of_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-25 14:55:43
