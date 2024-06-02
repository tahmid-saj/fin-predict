CREATE DATABASE  IF NOT EXISTS `predictor`;
USE `predictor`;

--
-- Table structure for table `predictor_daily`
--

DROP TABLE IF EXISTS `predictor_daily`;

CREATE TABLE `predictor_daily` (
  `current_day` varchar(20) DEFAULT NULL,
  `closing_price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data for table `predictor_daily`
--

INSERT INTO `predictor_daily` VALUES
	('2024-05-01', 100),
	('2024-05-02', 150),
	('2024-05-03', 200),
	('2024-05-04', 250),
	('2024-05-05', 50)
