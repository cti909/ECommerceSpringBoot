-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: demospringapi
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `date_expire` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price_origin` bigint NOT NULL,
  `price_promotion` bigint NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `category_id` bigint NOT NULL,
  `style_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `review_total` bigint NOT NULL,
  `star_total` bigint NOT NULL,
  `list_url_image` json NOT NULL,
  `product_size` json NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FK3bdhf42l8os4j6p1jid39fvk9` (`style_id`),
  KEY `FKdb050tk37qryv15hd932626th` (`user_id`),
  CONSTRAINT `FK3bdhf42l8os4j6p1jid39fvk9` FOREIGN KEY (`style_id`) REFERENCES `styles` (`id`),
  CONSTRAINT `FKdb050tk37qryv15hd932626th` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (5,'2024-10-02 10:59:07.168141','2024-10-02 03:09:12.820000','sumi sjk fsw',_binary '\0','Vin smoke ',690,620,'2024-10-02 10:59:07.168141',2,1,1,95,1,9,'[\"/uploads/products/CDL4_1_b57a8c07-2095-49b9-90fd-109d34cf974d.jpg\"]','[\"L\", \"XL\"]'),(6,'2024-10-02 11:01:57.755693','2024-10-02 03:09:12.820000','opt ssd miks',_binary '\0','Lu Zor',690,620,'2024-10-02 11:01:57.755693',2,1,1,100,0,0,'[\"/uploads/products/CDL4_1_c21bcfe0-32ae-473a-993b-e248680f79b8.jpg\"]','[\"M\", \"2XL\"]'),(7,'2024-10-02 11:03:32.797855','2024-10-02 03:09:12.820000','Shin opi das vic',_binary '\0','Shin opi das vic',690,620,'2024-10-02 11:03:32.797855',2,1,2,100,0,0,'[\"/uploads/products/CDL4_1_c77d30d7-af3a-41fc-8723-b5f9d02f67b2.jpg\", \"/uploads/products/CDL4_1_01d75c71-4a0f-4764-9048-869ae2500a0c.jpg\"]','[\"S\", \"2XL\"]'),(8,'2024-10-03 23:17:20.102080','2024-10-05 16:12:09.244000','filo sss',_binary '\0','New pro15',120,99,'2024-10-03 23:17:20.102080',2,1,2,200,0,0,'[\"/uploads/products/images_f8b4c2ad-c2a7-46fa-b1ba-2ce5c774a9ec.jpg\"]','[\"S\"]');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-04  9:58:45
