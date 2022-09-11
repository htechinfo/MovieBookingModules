-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: moviebooking_movietheatre
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
INSERT INTO `city` VALUES (1, 'AHMEDABAD'), (2, 'DELHI'), (3, 'SURAT'), (4, 'RAJKOT');
UNLOCK TABLES;

--
-- Dumping data for table `city_sequence`
--

LOCK TABLES `city_sequence` WRITE;
/*!40000 ALTER TABLE `city_sequence` DISABLE KEYS */;
INSERT INTO `city_sequence` VALUES (5);
/*!40000 ALTER TABLE `city_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'2022-09-10 09:15:48.348291',_binary '','b290ee0f-b4c3-4242-b17b-cd640276ddb5','Anke'),(2,'2022-09-10 09:15:48.348291',_binary '','ef833174-a6d3-4023-a387-39ea56adf0b6','RAID');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `movie_sequence`
--

LOCK TABLES `movie_sequence` WRITE;
/*!40000 ALTER TABLE `movie_sequence` DISABLE KEYS */;
INSERT INTO `movie_sequence` VALUES (3);
/*!40000 ALTER TABLE `movie_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `movie_theatre`
--

LOCK TABLES `movie_theatre` WRITE;
/*!40000 ALTER TABLE `movie_theatre` DISABLE KEYS */;
INSERT INTO `movie_theatre` VALUES (1,'2022-09-10 09:15:48.349306','[1663192800000,1663279200000]',1,1),(2,'2022-09-10 09:15:48.349306','[1663192800000,1663279200000]',2,1),(3,'2022-09-10 09:15:48.349306','[1663192800000,1663279200000]',1,2),(4,'2022-09-10 09:15:48.349306','[1663192800000,1663279200000]',2,2);
/*!40000 ALTER TABLE `movie_theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `movie_theatre_sequence`
--

LOCK TABLES `movie_theatre_sequence` WRITE;
/*!40000 ALTER TABLE `movie_theatre_sequence` DISABLE KEYS */;
INSERT INTO `movie_theatre_sequence` VALUES (5);
/*!40000 ALTER TABLE `movie_theatre_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `theatre`
--

LOCK TABLES `theatre` WRITE;
/*!40000 ALTER TABLE `theatre` DISABLE KEYS */;
INSERT INTO `theatre` VALUES (1,'/pvr/arved','AHMEDABAD','2022-09-10 09:15:48.348291',_binary '','PVR Arved','a97d74e4-40c3-4271-83b9-f47f65a7374d'),(2,'/pvr/arved','AHMEDABAD','2022-09-10 09:15:48.348291',_binary '','PVR Acropolish','d4093ee0-dbf3-4aa2-b113-7745098ef309');
/*!40000 ALTER TABLE `theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `theatre_sequence`
--

LOCK TABLES `theatre_sequence` WRITE;
/*!40000 ALTER TABLE `theatre_sequence` DISABLE KEYS */;
INSERT INTO `theatre_sequence` VALUES (3);
/*!40000 ALTER TABLE `theatre_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-10  9:22:25
