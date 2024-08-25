-- create schema
CREATE DATABASE `flightdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- create countries
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `risklevel` enum('Low','High') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- create pilots
CREATE TABLE `pilots` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `phonenr` varchar(45) DEFAULT NULL,
  `licenseyear` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- create flights
CREATE TABLE `flights` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pilot1id` int NOT NULL,
  `pilot2id` int NOT NULL,
  `countryfrom` int NOT NULL,
  `countryto` int NOT NULL,
  `flighttime` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pilot1_fk_idx` (`pilot1id`),
  KEY `pilot2_fk_idx` (`pilot2id`),
  KEY `countryTo_fk_idx` (`countryto`),
  KEY `countryFrom_fk2_idx` (`countryfrom`),
  CONSTRAINT `countryFrom_fk2` FOREIGN KEY (`countryfrom`) REFERENCES `countries` (`id`),
  CONSTRAINT `countryTo_fk` FOREIGN KEY (`countryto`) REFERENCES `countries` (`id`),
  CONSTRAINT `pilot1_fk` FOREIGN KEY (`pilot1id`) REFERENCES `pilots` (`id`),
  CONSTRAINT `pilot2_fk` FOREIGN KEY (`pilot2id`) REFERENCES `pilots` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- create pilots_flights
CREATE TABLE `pilots_flights` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pilot_id` int NOT NULL,
  `flight_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pilotid_fk_idx` (`pilot_id`),
  KEY `flightid_fk_idx` (`flight_id`),
  CONSTRAINT `flightid_fk` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`),
  CONSTRAINT `pilotid_fk` FOREIGN KEY (`pilot_id`) REFERENCES `pilots` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
