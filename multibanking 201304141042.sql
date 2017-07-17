-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.18


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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`idaccount`,`idbranch`,`iduser`,`type`,`approved`) VALUES 
 (1,1,9,'Savings',0),
 (5,7,11,'Current',1),
 (7,1,12,'Savings',1),
 (10,3,11,'Current',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` (`idbank`,`name`,`short_code`,`address`,`sec_code`,`phone`,`email`,`web`,`approved`) VALUES 
 (1,'State Bank Of Mysore','sbm','Near Majestic,\r\nBangalore - 1','123123123','34545345345','sbm@sbmonline.com','http://sbmonline.com',1),
 (2,'Canara Bank','can','Near Corporation Circle,\r\nBangalore - 1','123123123','352345345','canara@canaraonline.com','http://canaraonline.com',1);
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
 (1,4),
 (2,14);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` (`idbranch`,`idbank`,`name`,`address`,`phone`,`email`) VALUES 
 (1,1,'Davangere','Near B.T Petrol Bunk, \r\nDavangere','324234543','sbmdvg@sbmonline.com'),
 (2,1,'Harihar','Shimoga Main Road, \r\nNear Harihareshwara Temple,\r\nHarihar','45423523','sbmhrr@sbmonline.com'),
 (3,2,'Davangere','Beside BT Petrol Bunk,\r\nDavangere - 1','23454235','candvg@canaraonline.com'),
 (7,1,'Harapanalli','Hospet Main Road,\r\nHarapanalli','435345564','sbmhpn@sbmonline.com'),
 (9,1,'Tyavanagi','Hospet Main Road,\r\nTyavanagi','435345564','sbmtvn@sbmonline.com'),
 (10,1,'Sira','Poona Bangalore Road,\r\nSira','2454534','sbmsra@sbmonline.com');
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
  CONSTRAINT `FK_staff_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `FK_staff_1` FOREIGN KEY (`idbranch`) REFERENCES `branch` (`idbranch`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`idstaff`,`idbranch`,`iduser`,`ismanager`) VALUES 
 (7,1,5,1),
 (8,1,6,0),
 (11,1,7,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`idaccount`,`debit`,`credit`,`total`,`logtime`,`idtransaction`) VALUES 
 (5,0,1000,1000,'2013-04-14 09:02:12',2),
 (5,0,500,1500,'2013-04-14 09:03:08',3),
 (5,0,500,2000,'2013-04-14 09:28:22',4),
 (5,500,0,1500,'2013-04-14 09:40:15',5),
 (10,0,10000,10000,'2013-04-14 10:32:12',6),
 (10,3000,0,7000,'2013-04-14 10:32:23',7);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`iduser`,`name`,`dob`,`gender`,`address`,`email`,`mobile`,`uname`,`passwd`) VALUES 
 (4,'Vinayaka V Patil','1989-03-03',1,'#637/10,\r\nSultan Pet, \r\nDavangere - 577001','vinnu313@gmail.com','9611715777','vinnu','vinnu'),
 (5,'Yogesh','1989-03-10',1,'Ramanagaram','dsyogi10@gmail.com','5345345','yogi','yogi'),
 (6,'Nithesh','1989-03-27',1,'Nittur','soft.nithesh@gmail.com','5345345','nithesh','nithesh'),
 (7,'pooja','1989-04-28',1,'Tumkur','poojaraj@gmail.com','3456345','pooja','pooja'),
 (9,'harsha','1982-04-06',1,'Davangere','harsha@gmail.com','576576787','harsha','harsha'),
 (11,'Suresh','1978-10-23',1,'Davangere','suresh@gmail.com','45345234','cust11','password'),
 (12,'Santhosh','1985-04-23',1,'Harihara','sam@gmail.com','523452345','cust12','password'),
 (13,'Madhu','1978-04-11',0,'Harapanahalli','madhu@gmail.com','4534534','cust13','password'),
 (14,'mahesh','1989-02-02',1,'Rajeshwar,\r\nBasavakalyan,\r\nBidar','mass331@gmail.com','9886766016','mass','mass');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
