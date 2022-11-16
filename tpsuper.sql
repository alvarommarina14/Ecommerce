-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: tpsuper
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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Gaseosa'),(2,'Pasta'),(3,'Golosina'),(7,'Galletas'),(8,'Lacteos');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `nroDocumento` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `tipoDocumento` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nombreApellido` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `telefono` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `direccion` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `codPostal` int NOT NULL,
  PRIMARY KEY (`nroDocumento`),
  KEY `fk_Cliente_Localidad_idx` (`codPostal`),
  CONSTRAINT `fk_Cliente_Localidad` FOREIGN KEY (`codPostal`) REFERENCES `localidad` (`codPostal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('41097334','dni','matias baaiss','acssss@afs.com','1998-09-25','2474492062','mar del plata 665',2705);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localidad` (
  `codPostal` int NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `idProvincia` int NOT NULL,
  PRIMARY KEY (`codPostal`),
  KEY `fk_Localidad_Provincia1_idx` (`idProvincia`),
  CONSTRAINT `fk_Localidad_Provincia1` FOREIGN KEY (`idProvincia`) REFERENCES `provincia` (`idProvincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (2705,'Rojas',1);
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precio` (
  `idPrecio` int NOT NULL AUTO_INCREMENT,
  `idProducto` int DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `fechaDesde` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPrecio`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio`
--

LOCK TABLES `precio` WRITE;
/*!40000 ALTER TABLE `precio` DISABLE KEYS */;
INSERT INTO `precio` VALUES (1,3,105,'2021-02-25 22:14:17'),(3,5,80,'2021-02-25 22:14:17'),(5,10,25,'2021-02-25 22:14:17'),(7,11,39,'2021-02-25 22:14:17'),(182,13,15.5,'2022-09-16 12:24:09'),(183,3,106,'2022-09-16 12:51:40'),(184,11,39,'2022-09-16 12:52:03'),(185,5,80.39999999999999,'2022-09-16 12:52:20'),(186,10,25.124999999999996,'2022-09-16 12:52:21'),(187,3,106.52999999999999,'2022-09-16 12:52:22'),(188,11,39.19499999999999,'2022-09-16 12:52:22'),(189,5,104,'2022-09-16 12:52:28'),(190,10,32.5,'2022-09-16 12:52:28'),(191,3,136.5,'2022-09-16 12:52:28'),(192,11,50.7,'2022-09-16 12:52:28'),(193,5,104,'2022-09-16 12:52:37'),(194,10,32.5,'2022-09-16 12:52:37'),(195,3,136.5,'2022-09-16 12:52:37'),(196,11,50.7,'2022-09-16 12:52:37'),(197,14,150,'2022-09-16 12:53:19'),(198,15,150,'2022-09-16 12:54:41'),(199,16,150,'2022-09-16 12:54:44'),(200,17,150,'2022-09-16 12:54:46'),(201,18,150,'2022-09-16 12:54:47'),(202,19,0,'2022-09-16 12:55:20'),(203,20,0,'2022-09-16 12:55:23'),(204,21,0,'2022-09-16 12:55:27'),(205,22,0,'2022-09-16 12:55:29'),(206,23,0,'2022-09-16 12:55:31'),(207,24,12.2,'2022-09-23 10:40:42'),(208,5,104,'2022-09-23 10:41:12'),(209,10,32.5,'2022-09-23 10:41:12'),(210,3,136.5,'2022-09-23 10:41:12'),(211,11,50.7,'2022-09-23 10:41:13'),(212,5,104,'2022-09-23 10:41:19'),(213,10,32.5,'2022-09-23 10:41:19'),(214,3,136.5,'2022-09-23 10:41:19'),(215,11,50.7,'2022-09-23 10:41:19'),(216,5,104,'2022-09-23 10:41:26'),(217,10,32.5,'2022-09-23 10:41:27'),(218,3,136.5,'2022-09-23 10:41:27'),(219,11,50.7,'2022-09-23 10:41:27'),(220,5,104,'2022-09-23 10:47:11'),(221,10,32.5,'2022-09-23 10:47:11'),(222,3,136.5,'2022-09-23 10:47:11'),(223,11,50.7,'2022-09-23 10:47:11');
/*!40000 ALTER TABLE `precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `idProveedor` int DEFAULT NULL,
  `idCategoria` int DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `Categoria_idx` (`idCategoria`),
  KEY `Proveedor_idx` (`idProveedor`),
  CONSTRAINT `Categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `Proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (3,'Fideos',23,1,2),(5,'Block',10,1,2),(10,'Raviol',12,2,2),(11,'Variedad',123,7,7),(13,'Galletitas',10,8,7),(14,'Coca-Cola',100,2,1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `cuil` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tipoTelefono` varchar(45) DEFAULT NULL,
  `nroTelefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Matias','20410973347','matias@gmail.com','cel','2474492062'),(2,'Juan','25115151515','asdas@gmail.com','fijo','2475466644'),(7,'Alvaro','12312',NULL,'cel','123123'),(8,'Pedro','1212312',NULL,'fijo','123123');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `idProvincia` int NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idProvincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'Buenos Aires');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-16 11:26:18
