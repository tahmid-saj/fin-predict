CREATE DATABASE  IF NOT EXISTS `predictor`;
USE `predictor`;

--
-- Table structure for table `predictor_weekly`
--

DROP TABLE IF EXISTS `predictor_weekly`;

CREATE TABLE `predictor_weekly` (
  `current_day` varchar(20) DEFAULT NULL,
  `closing_price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data for table `predictor_weekly`
--

INSERT INTO `predictor_weekly` VALUES
	('2024-05-01', 10),
	('2024-05-02', 150),
	('2024-05-03', 250),
	('2024-05-04', 279),
	('2024-05-05', 50)
