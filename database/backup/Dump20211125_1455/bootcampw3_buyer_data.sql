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
-- Table structure for table `buyer_data`
--

DROP TABLE IF EXISTS `buyer_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer_data` (
  `idbuyer_data` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) NOT NULL,
  `cellphone` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `complemento` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `localidade` varchar(255) NOT NULL,
  `logradouro` varchar(255) NOT NULL,
  `referencia` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `uf` varchar(255) NOT NULL,
  `idbuyer` bigint NOT NULL,
  PRIMARY KEY (`idbuyer_data`),
  KEY `FKktun3pbkd60cop8h85mfk2ymk` (`idbuyer`),
  CONSTRAINT `FKktun3pbkd60cop8h85mfk2ymk` FOREIGN KEY (`idbuyer`) REFERENCES `buyer` (`id_buyer`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_data`
--

LOCK TABLES `buyer_data` WRITE;
/*!40000 ALTER TABLE `buyer_data` DISABLE KEYS */;
INSERT INTO `buyer_data` VALUES (1,'Pitangueiras','11 90000-1111','11410-120','','test@foobar.com','Guarujá','Rua Raul Ricardo de Barros','Bloco A, 3º andar, Apt 31','11 0000-0000','SP',1),(2,'Asa Sul','11 9006-1930','70200-670','','carlota@foobar.com','Brasília','SGAS 607','Bloco D, 5º andar, Apt 55','61 0000-4657','DF',2),(3,'Barra da Tijuca','21 9001-0002','22630-010','de 3102 a 3604 - lado par','carlota@foobar.com','Rio de Janeiro','Avenida Lúcio Costa','Nº3602, Condominio Parque Azul, 8º andar, Apt 84','21 0000-2378','RJ',3),(4,'Centro','48 9008-0089','88010-101','de 251/252 a 452/453','fexavier@foobar.com','Florianópolis','Rua Conselheiro Mafra','Nº251, 4º andar Apt 46','48 0000-8042','SC',4),(5,'São José','81 9003-0011','50020-290','','antoliver@foobar.com','Recife','Rua das Calçadas','Nº12, casa verde ao lado da padaria Dona Filó','81 0000-3344','PE',5),(6,'Centro','41 9004-0084','80020-320','até 0765 - lado ímpar','queen@foobar.com','Curitiba','Rua Marechal Deodoro','Nº0765, Condominio Águas Lindas , casa 26','41 0000-3790','PR',6);
/*!40000 ALTER TABLE `buyer_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-25 14:55:41
