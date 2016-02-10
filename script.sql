-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.26 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para android_api
CREATE DATABASE IF NOT EXISTS `android_api` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `android_api`;


-- Volcando estructura para tabla android_api.person
CREATE TABLE IF NOT EXISTS `person` (
  `ID_PERSON` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(500) DEFAULT NULL,
  `FORENAME` varchar(500) DEFAULT NULL,
  `ADDRESS` varchar(500) DEFAULT NULL,
  `PHOTO_NAME` varchar(2000) DEFAULT NULL,
  `ID_USER` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_PERSON`),
  KEY `ID_PERSON` (`ID_PERSON`),
  KEY `ID_USER` (`ID_USER`),
  CONSTRAINT `FK_person_users` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla android_api.person: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


-- Volcando estructura para tabla android_api.plan
CREATE TABLE IF NOT EXISTS `plan` (
  `ID_PLAN` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(2000) NOT NULL,
  `PRICE` decimal(15,2) NOT NULL,
  `STATE` bit(1) NOT NULL,
  PRIMARY KEY (`ID_PLAN`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla android_api.plan: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` (`ID_PLAN`, `NAME`, `DESCRIPTION`, `PRICE`, `STATE`) VALUES
	(1, 'PLAN A', 'Plan de prueba', 50000.00, b'1'),
	(2, 'PLAN B', 'plan de prueba b', 65000.00, b'1'),
	(3, 'PLAN C', 'plan de pruebas deshabilitado', 55000.00, b'0');
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;


-- Volcando estructura para tabla android_api.service
CREATE TABLE IF NOT EXISTS `service` (
  `ID_SERVICE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_REQUEST` datetime NOT NULL,
  `ID_USER_REQUEST` bigint(20) NOT NULL,
  `ID_USER_SERVICE` bigint(20) NOT NULL,
  `ID_PLAN` int(11) NOT NULL,
  `TIME_START` time NOT NULL,
  `TIME_FINISH` time NOT NULL,
  `DATE_SERVICE` date NOT NULL,
  PRIMARY KEY (`ID_SERVICE`),
  KEY `FK_service_users` (`ID_USER_REQUEST`),
  KEY `FK_service_plan` (`ID_PLAN`),
  KEY `FK_service_users_2` (`ID_USER_SERVICE`),
  CONSTRAINT `FK_service_plan` FOREIGN KEY (`ID_PLAN`) REFERENCES `plan` (`ID_PLAN`),
  CONSTRAINT `FK_service_users` FOREIGN KEY (`ID_USER_REQUEST`) REFERENCES `users` (`UID`),
  CONSTRAINT `FK_service_users_2` FOREIGN KEY (`ID_USER_SERVICE`) REFERENCES `users` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla android_api.service: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;


-- Volcando estructura para tabla android_api.users
CREATE TABLE IF NOT EXISTS `users` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UNIQUE_ID` varchar(23) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `ENCRYPTED_PASSWORD` varchar(80) NOT NULL,
  `SALT` varchar(10) NOT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `ROLE` int(11) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `unique_id` (`UNIQUE_ID`),
  UNIQUE KEY `email` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla android_api.users: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`UID`, `UNIQUE_ID`, `NAME`, `EMAIL`, `ENCRYPTED_PASSWORD`, `SALT`, `CREATED_AT`, `UPDATED_AT`, `ROLE`) VALUES
	(1, '55f8e72cbb5498.71617974', 'Carls', 'caom838@gmail.com', 'J1BogGnFCDP2xOj8hgKbDq3SGAFhOTA0MmMzNDBj', 'a9042c340c', '2015-09-15 22:51:08', NULL, NULL),
	(2, '55f8eaa5c7f1b2.01575209', 'Carls', '', 'kMesPCfJIYJRQw077R8/zKpOBBkzZDgxYjFjOWIw', '3d81b1c9b0', '2015-09-15 23:05:57', NULL, NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
