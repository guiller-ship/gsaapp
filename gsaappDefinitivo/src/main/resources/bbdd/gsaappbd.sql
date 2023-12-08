-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gsaappbd
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permisos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fechaFin` date NOT NULL,
  `fechaInicio` date NOT NULL,
  `tipoPermiso` varchar(255) NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9cf7wtlcex9a0r11ll672l7y` (`usuario_id`),
  CONSTRAINT `FKl9cf7wtlcex9a0r11ll672l7y` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` VALUES (2,'2023-12-16','2023-12-14','vacaciones',1,'ACEPTADO'),(3,'2023-12-09','2023-12-08','vacaciones',1,'ACEPTADO'),(4,'2023-12-09','2023-12-08','otro',3,'PENDIENTE');
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'ROLE_USER'),(4,'ROLE_ADMIN'),(5,'ROLE_USER'),(6,'ROLE_USER'),(7,'ROLE_USER'),(8,'ROLE_USER'),(9,'ROLE_USER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `antiguedad` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk3bbc0k0u08fuwfdalj6shbfb` (`email`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'2023-09-06','Prueba User','foto_Perfil_2.jpg','Usuario','usuario123@gmail.com','Usuario','$2a$10$ULwjw9gZbvPndSCgyJ478OBmGtVBNNajBx8r2MevGKxQyK.IBtaEK','usuario123'),(2,'2023-07-12','Prueba Admin','foto_Perfil_3.jpg','Administrador','admin123@gmail.com','Admin','$2a$10$0AmQxYfxbwnDQronkA8IX.4z8q9/GEKLUxNmm/pppv/MXXBMfzwBK','admin123'),(3,'2023-09-13','Rodriguez Rodriguez','foto_Perfil_3.jpg','Arquitecto de software','juan123@correo.com','Juan','$2a$10$3FFFEXzSKmeXvtoDsjPiVOnFN98599sT6Odp4wonnfsxTmhcBV.oS','juanUS456'),(4,'2010-07-06','Gomez Alvarez','foto_Perfil_4.jpg','Administrador','mariaga465@gsaapp.com','Maria','$2a$10$weEgKG.w5xGn6KpUBim.L./MW0nWMEqghYkMLgR//bOp1fh2Rkh/i','mariagaAdmin'),(5,'2016-10-20','Hernandez Ferreiro','foto_Perfil_5.jpg','Programador Back End','jose888@gsaapp.es','Jose','$2a$10$poPe7rHE0M1EPsJPng9nt.E3wIdjbNpXMYV301kH0Zy29PITvs4I2','joseUS888'),(6,'2015-03-05','Gonzalez Garcia','foto_Perfil_1.png','Programador Front End','lucia999@gsaapp.com','Lucia','$2a$10$4t7YQTeCFoBVHr1bcVr8Xe1AXLuaOTqynNfhs242Vp8W58GGnSdsW','lucia999'),(7,'2020-09-07','Gutierrez Iglesias','foto_Perfil_3.jpg','Programador Back End','fernando4RTE@gsaapp.com','Fernando','$2a$10$9LscJxMFAXxXLy9Ph.Twhe0hUAc6djhIR2pay8bUliPbh5yMN1aPu','fernando4RTE'),(8,'2023-02-14','Fernandez Fernandez','foto_Perfil_4.jpg','Programador Front End','alejandro564@gsaapp.com','Alejandro','$2a$10$6417KSboxAD6Cg.ZnNxRDuX5fNM/mlelMEwNE2mGM4RzF4qxhyOAe','alejandro564'),(9,'2013-01-15','Suarez Montes','foto_Perfil_3.jpg','Programador Full Stack','rodrigo333@gsaapp.com','Rodrigo','$2a$10$y2FfFR67W.0CLWboxJM22.mrbq6XljNIRtCq0l9TFmm9dMzlqaGMe','rdrgo333');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  KEY `FK6yxg1lhuv5nievqea7rvn6afm` (`rol_id`),
  KEY `FKqcxu02bqipxpr7cjyj9dmhwec` (`usuario_id`),
  CONSTRAINT `FK6yxg1lhuv5nievqea7rvn6afm` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gsaappbd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-08 20:21:53
