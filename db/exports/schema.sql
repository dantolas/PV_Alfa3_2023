--- KUTA SAMUEL C4b kuta.samuel@gmail.com
-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: alfa3
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.22.04.1

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
-- Table structure for table `Doctor`
--
START TRANSACTION;
BEGIN;

DROP TABLE IF EXISTS `Doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Doctor` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `started_practice` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `InsuranceCompany`
--

DROP TABLE IF EXISTS `InsuranceCompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `InsuranceCompany` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `name` varchar(255) NOT NULL,
  `country_of_origin` varchar(255) NOT NULL,
  `shortcut` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`),
  UNIQUE KEY `shortcut` (`shortcut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `Insurance_Patient_Count`
--

DROP TABLE IF EXISTS `Insurance_Patient_Count`;
/*!50001 DROP VIEW IF EXISTS `Insurance_Patient_Count`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `Insurance_Patient_Count` AS SELECT 
 1 AS `Patients registered`,
 1 AS `Insurance`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Medication`
--

DROP TABLE IF EXISTS `Medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Medication` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `name` varchar(255) NOT NULL,
  `short_description` varchar(500) NOT NULL,
  `detailed_description` blob,
  `type` enum('pills','tablets','syrup','ointment','herbs') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Patient`
--

DROP TABLE IF EXISTS `Patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Patient` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `birth_number` varchar(255) NOT NULL,
  `dof` date NOT NULL,
  `gender` bit(1) NOT NULL,
  `insurance_company_id` binary(16) DEFAULT NULL,
  `insurance_number` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `birth_number` (`birth_number`),
  KEY `insurance_company_id` (`insurance_company_id`),
  CONSTRAINT `Patient_ibfk_1` FOREIGN KEY (`insurance_company_id`) REFERENCES `InsuranceCompany` (`id`),
  CONSTRAINT `gender_check` CHECK (((`gender` = 1) or (`gender` = 0)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `Presc_Summ`
--

DROP TABLE IF EXISTS `Presc_Summ`;
/*!50001 DROP VIEW IF EXISTS `Presc_Summ`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `Presc_Summ` AS SELECT 
 1 AS `Prescribed`,
 1 AS `Doctor`,
 1 AS `Patient`,
 1 AS `Diagnosis`,
 1 AS `Medicine`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Prescription_item`
--

DROP TABLE IF EXISTS `Prescription_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prescription_item` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `prescription_id` binary(16) DEFAULT NULL,
  `medication_id` binary(16) DEFAULT NULL,
  `amount` int NOT NULL,
  `insurance_covered` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prescription` (`prescription_id`),
  KEY `fk_medication` (`medication_id`),
  CONSTRAINT `fk_medication` FOREIGN KEY (`medication_id`) REFERENCES `Medication` (`id`),
  CONSTRAINT `fk_prescription` FOREIGN KEY (`prescription_id`) REFERENCES `ePrescription` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `Prescriptions_Handed_Out`
--

DROP TABLE IF EXISTS `Prescriptions_Handed_Out`;
/*!50001 DROP VIEW IF EXISTS `Prescriptions_Handed_Out`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `Prescriptions_Handed_Out` AS SELECT 
 1 AS `Prescriptions handed`,
 1 AS `Doctor`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ePrescription`
--

DROP TABLE IF EXISTS `ePrescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ePrescription` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `patient_id` binary(16) DEFAULT NULL,
  `doctor_id` binary(16) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `date_prescribed` date NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`),
  KEY `fk_patient` (`patient_id`),
  KEY `fk_doctor` (`doctor_id`),
  CONSTRAINT `fk_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `Doctor` (`id`),
  CONSTRAINT `fk_patient` FOREIGN KEY (`patient_id`) REFERENCES `Patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

COMMIT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `Insurance_Patient_Count`
--

/*!50001 DROP VIEW IF EXISTS `Insurance_Patient_Count`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`charming`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Insurance_Patient_Count` AS select count(`Patient`.`id`) AS `Patients registered`,`InsuranceCompany`.`shortcut` AS `Insurance` from (`InsuranceCompany` join `Patient` on((`InsuranceCompany`.`id` = `Patient`.`insurance_company_id`))) group by `InsuranceCompany`.`shortcut` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Presc_Summ`
--

/*!50001 DROP VIEW IF EXISTS `Presc_Summ`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`charming`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Presc_Summ` AS select `e`.`date_prescribed` AS `Prescribed`,concat(`d`.`fname`,' ',`d`.`lname`) AS `Doctor`,concat(`p`.`fname`,' ',`p`.`lname`) AS `Patient`,`e`.`diagnosis` AS `Diagnosis`,group_concat(`Medication`.`name` separator ',') AS `Medicine` from ((((`ePrescription` `e` join `Doctor` `d` on((`e`.`doctor_id` = `d`.`id`))) join `Patient` `p` on((`e`.`patient_id` = `p`.`id`))) join `Prescription_item` `pi` on((`e`.`id` = `pi`.`prescription_id`))) join `Medication` on((`pi`.`medication_id` = `Medication`.`id`))) group by `e`.`id` order by `Prescribed` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Prescriptions_Handed_Out`
--

/*!50001 DROP VIEW IF EXISTS `Prescriptions_Handed_Out`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`charming`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Prescriptions_Handed_Out` AS select count(`ePrescription`.`id`) AS `Prescriptions handed`,concat(`Doctor`.`fname`,' ',`Doctor`.`lname`) AS `Doctor` from (`Doctor` join `ePrescription` on((`Doctor`.`id` = `ePrescription`.`doctor_id`))) group by `Doctor` order by 'Prescriptions handed' desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-04 13:40:25
