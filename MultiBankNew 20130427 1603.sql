-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema multibank
--

CREATE DATABASE IF NOT EXISTS multibank;
USE multibank;

--
-- Definition of table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `idaccount` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idbranch` int(10) unsigned NOT NULL,
  `iduser` int(10) unsigned NOT NULL,
  `type` varchar(45) NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idaccount`),
  UNIQUE KEY `unique_account` (`idbranch`,`iduser`),
  KEY `FK_account_1` (`iduser`),
  CONSTRAINT `FK_account_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `FK_account_2` FOREIGN KEY (`idbranch`) REFERENCES `branch` (`idbranch`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`idaccount`,`idbranch`,`iduser`,`type`,`approved`) VALUES 
 (11,11,19,'Savings',1),
 (12,11,20,'Savings',1),
 (13,11,21,'Savings',1),
 (14,11,18,'Savings',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


--
-- Definition of table `bank`
--

DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `idbank` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `short_code` varchar(5) NOT NULL,
  `address` varchar(500) NOT NULL,
  `sec_code` varchar(45) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `web` varchar(200) NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idbank`),
  UNIQUE KEY `unique_bank` (`short_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` (`idbank`,`name`,`short_code`,`address`,`sec_code`,`phone`,`email`,`web`,`approved`) VALUES 
 (3,'State Bank Of Mysore','SBM','Bengaluru','SBM1010','9009009009','sbmonline@sbmonline.com','http:\\\\sbm.com',1),
 (4,'State Bank Of India','SBI','Bengaluru','SBI1213','9000890008','sbionline@sbionline.com','http:\\\\sbi.com',0);
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;


--
-- Definition of table `bank_manager`
--

DROP TABLE IF EXISTS `bank_manager`;
CREATE TABLE `bank_manager` (
  `idbank` int(10) unsigned NOT NULL,
  `iduser` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idbank`),
  KEY `FK_bank_manager_2` (`iduser`),
  CONSTRAINT `FK_bank_manager_1` FOREIGN KEY (`idbank`) REFERENCES `bank` (`idbank`),
  CONSTRAINT `FK_bank_manager_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_manager`
--

/*!40000 ALTER TABLE `bank_manager` DISABLE KEYS */;
INSERT INTO `bank_manager` (`idbank`,`iduser`) VALUES 
 (3,17);
/*!40000 ALTER TABLE `bank_manager` ENABLE KEYS */;


--
-- Definition of table `branch`
--

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `idbranch` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idbank` int(10) unsigned NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`idbranch`),
  UNIQUE KEY `unique_branch` (`name`,`address`,`idbank`),
  KEY `FK_branch_1` (`idbank`),
  CONSTRAINT `FK_branch_1` FOREIGN KEY (`idbank`) REFERENCES `bank` (`idbank`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` (`idbranch`,`idbank`,`name`,`address`,`phone`,`email`) VALUES 
 (11,3,'SBM Vijayanagara','Vijayanagara\r\nNear watertank\r\nBengaluru','9009009009','Vijayanagara@SBM.com'),
 (12,3,'SBM Nagarabhavi','Nagarabhavi\r\nNear Dr Ambedkar college\r\nBengaluru','9998887776','Nagarabhavi@SBM.com');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;


--
-- Definition of table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `idstaff` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idbranch` int(10) unsigned NOT NULL,
  `iduser` int(10) unsigned NOT NULL,
  `ismanager` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idstaff`),
  UNIQUE KEY `unique_staff` (`iduser`),
  KEY `FK_staff_1` (`idbranch`),
  CONSTRAINT `FK_staff_1` FOREIGN KEY (`idbranch`) REFERENCES `branch` (`idbranch`),
  CONSTRAINT `FK_staff_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`idstaff`,`idbranch`,`iduser`,`ismanager`) VALUES 
 (12,11,15,0),
 (13,11,16,0);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;


--
-- Definition of table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `idaccount` int(10) unsigned NOT NULL,
  `debit` float NOT NULL,
  `credit` float NOT NULL,
  `total` float NOT NULL,
  `logtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idtransaction` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idtransaction`),
  KEY `FK_transaction_1` (`idaccount`),
  CONSTRAINT `FK_transaction_1` FOREIGN KEY (`idaccount`) REFERENCES `account` (`idaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`idaccount`,`debit`,`credit`,`total`,`logtime`,`idtransaction`) VALUES 
 (12,0,10000,10000,'2013-04-27 14:58:50',8),
 (12,300,0,9700,'2013-04-27 14:59:10',9);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `iduser` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `dob` varchar(15) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `address` varchar(500) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `unique_user` (`name`,`dob`,`gender`,`mobile`),
  UNIQUE KEY `unique_uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`iduser`,`name`,`dob`,`gender`,`address`,`email`,`mobile`,`uname`,`passwd`) VALUES 
 (15,'Preetham','2013-04-08',1,'Bengaluru','Preetham@gmail.com','9008007006','Preetham','preetham10'),
 (16,'Asha','2013-04-10',0,'Bengaluru','asha@gmail.coom','9988776655','Asha','asha10'),
 (17,'Poornima','1990-04-15',0,'Bengaluru','poornima@gmail.com','9998887770','Poornima','poornima10'),
 (18,'Karthik','1989-06-05',1,'Bengaluru','Karthik@gmail.com','8889997770','Karthik','karthik10'),
 (19,'Sammy','1989-06-05',0,'Bengaluru','sammy@gmail.com','9998889990','Sammy','sammy10'),
 (20,'Sachin','1970-03-03',1,'Bengaluru','sachin@gmail.com','8877999988','cust20','password'),
 (21,'Samir','1989-06-05',1,'Bengaluru','samir@gmail.com','9999888800','cust21','password');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
