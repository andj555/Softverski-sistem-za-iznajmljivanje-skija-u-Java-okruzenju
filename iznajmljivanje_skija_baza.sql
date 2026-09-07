/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - iznajmljivanje_skija
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iznajmljivanje_skija` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `iznajmljivanje_skija`;

/*Table structure for table `clanstvo` */

DROP TABLE IF EXISTS `clanstvo`;

CREATE TABLE `clanstvo` (
  `idClanstvo` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipClanstva` varchar(50) NOT NULL,
  PRIMARY KEY (`idClanstvo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `clanstvo` */

insert  into `clanstvo`(`idClanstvo`,`tipClanstva`) values 
(1,'Standard'),
(2,'Deluxe'),
(3,'Premium');

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `idIznajmljivanje` bigint(20) NOT NULL AUTO_INCREMENT,
  `ukupanIznos` double NOT NULL,
  `popust` double NOT NULL,
  `datumRealizacije` date NOT NULL,
  `idOsoba` bigint(20) NOT NULL,
  `idZaposlen` bigint(20) NOT NULL,
  PRIMARY KEY (`idIznajmljivanje`),
  KEY `idOsoba` (`idOsoba`),
  KEY `idZaposlen` (`idZaposlen`),
  CONSTRAINT `iznajmljivanje_ibfk_1` FOREIGN KEY (`idOsoba`) REFERENCES `osoba` (`idOsoba`),
  CONSTRAINT `iznajmljivanje_ibfk_2` FOREIGN KEY (`idZaposlen`) REFERENCES `zaposleni` (`idZaposlen`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `iznajmljivanje` */

insert  into `iznajmljivanje`(`idIznajmljivanje`,`ukupanIznos`,`popust`,`datumRealizacije`,`idOsoba`,`idZaposlen`) values 
(4,27180,0.1,'2026-08-21',1,1),
(6,9690,0.15,'2026-08-23',5,3),
(7,20100,0,'2026-08-23',2,2),
(8,17900,0,'2026-08-26',2,3),
(9,11815,0.15,'2026-08-27',6,3),
(10,11430,0.1,'2026-08-27',8,1),
(11,15525,0.1,'2026-08-27',10,2),
(13,17850,0.15,'2026-08-28',6,1),
(14,8505,0.1,'2026-08-28',1,1),
(15,12105,0.1,'2026-08-28',8,1),
(16,14850,0.1,'2026-08-28',10,4),
(17,17127.5,0.15,'2026-08-28',5,2),
(18,21375,0.1,'2026-08-28',8,1),
(19,7560,0.1,'2026-08-28',10,4);

/*Table structure for table `osoba` */

DROP TABLE IF EXISTS `osoba`;

CREATE TABLE `osoba` (
  `idOsoba` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `jmbg` varchar(50) NOT NULL,
  `idClanstvo` bigint(20) NOT NULL,
  PRIMARY KEY (`idOsoba`),
  KEY `idClanstvo` (`idClanstvo`),
  CONSTRAINT `osoba_ibfk_1` FOREIGN KEY (`idClanstvo`) REFERENCES `clanstvo` (`idClanstvo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `osoba` */

insert  into `osoba`(`idOsoba`,`ime`,`prezime`,`telefon`,`jmbg`,`idClanstvo`) values 
(1,'Nina','Ninic','0631237','1803978200003',2),
(2,'Marko','Markovic','0645678','1205991279874',1),
(5,'Sara','Saric','0613782','1903929234452',3),
(6,'Andrija','Jelic','0640037','8737665733212',3),
(8,'Aleksa','Kablar','0618440','8863579473829',2),
(10,'edweygd','efefefe','1111144','1111111111111',2);

/*Table structure for table `radnasmena` */

DROP TABLE IF EXISTS `radnasmena`;

CREATE TABLE `radnasmena` (
  `idRadnaSmena` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipSmene` varchar(50) NOT NULL,
  `vremePocetka` time NOT NULL,
  `vremeKraja` time NOT NULL,
  PRIMARY KEY (`idRadnaSmena`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `radnasmena` */

insert  into `radnasmena`(`idRadnaSmena`,`tipSmene`,`vremePocetka`,`vremeKraja`) values 
(1,'Prva','08:00:00','12:00:00'),
(2,'Druga','12:00:01','16:00:00'),
(3,'Treca','16:00:01','20:00:00'),
(4,'medjusmena ujutru','10:00:01','14:00:00'),
(5,'yrfdyf','11:00:01','13:00:00'),
(6,'gyusdgyjdvyd','11:00:01','18:00:00');

/*Table structure for table `skije` */

DROP TABLE IF EXISTS `skije`;

CREATE TABLE `skije` (
  `idSkije` bigint(20) NOT NULL AUTO_INCREMENT,
  `model` varchar(50) NOT NULL,
  `velicina` double NOT NULL,
  `cena` double NOT NULL,
  `tip` varchar(50) NOT NULL,
  PRIMARY KEY (`idSkije`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `skije` */

insert  into `skije`(`idSkije`,`model`,`velicina`,`cena`,`tip`) values 
(1,'Rossignol Experience',160,1200,'Alpske'),
(2,'Rossignol Experience',170,1250,'Alpske'),
(3,'Atomic Redster',155,1350,'Alpske'),
(4,'Atomic Redster',165,1400,'Alpske'),
(5,'Salomon QST',163,1100,'Univerzalne'),
(6,'Salomon QST',173,1150,'Univerzalne'),
(7,'Head Kore',168,1250,'Univerzalne'),
(8,'Head Kore',178,1300,'Univerzalne'),
(9,'Volkl Revolt',172,1450,'Freeride'),
(10,'Volkl Revolt',182,1500,'Freeride'),
(11,'K2 Mindbender',174,1550,'Freeride'),
(12,'K2 Mindbender',184,1600,'Freeride');

/*Table structure for table `stavkaiznajmljivanja` */

DROP TABLE IF EXISTS `stavkaiznajmljivanja`;

CREATE TABLE `stavkaiznajmljivanja` (
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `iznos` double NOT NULL,
  `cenaPoDanu` double NOT NULL,
  `datumPreuzimanja` date NOT NULL,
  `datumPovratka` date NOT NULL,
  `idSkije` bigint(20) NOT NULL,
  `idIznajmljivanje` bigint(20) NOT NULL,
  PRIMARY KEY (`rb`,`idIznajmljivanje`),
  KEY `idSkije` (`idSkije`),
  KEY `idIznajmljivanje` (`idIznajmljivanje`),
  CONSTRAINT `stavkaiznajmljivanja_ibfk_1` FOREIGN KEY (`idSkije`) REFERENCES `skije` (`idSkije`),
  CONSTRAINT `stavkaiznajmljivanja_ibfk_2` FOREIGN KEY (`idIznajmljivanje`) REFERENCES `iznajmljivanje` (`idIznajmljivanje`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaiznajmljivanja` */

insert  into `stavkaiznajmljivanja`(`rb`,`iznos`,`cenaPoDanu`,`datumPreuzimanja`,`datumPovratka`,`idSkije`,`idIznajmljivanje`) values 
(1,5400,1350,'2026-02-08','2026-02-11',3,4),
(1,8100,1350,'2026-01-23','2026-01-28',3,6),
(1,13500,1350,'2026-06-04','2026-06-13',3,7),
(1,7200,1200,'2026-04-23','2026-04-28',1,8),
(1,5500,1100,'2026-01-11','2026-01-15',5,9),
(1,5500,1100,'2026-05-13','2026-05-17',5,10),
(1,3750,1250,'2026-04-20','2026-04-22',7,11),
(1,7700,1100,'2026-04-04','2026-04-10',5,13),
(1,4800,1200,'2026-06-08','2026-06-11',1,14),
(1,6200,1550,'2026-03-22','2026-03-25',11,15),
(1,7250,1450,'2026-01-06','2026-01-10',9,16),
(1,7750,1550,'2026-01-31','2026-02-04',11,17),
(1,1550,1550,'2026-03-31','2026-03-31',11,19),
(2,8400,1200,'2026-05-14','2026-05-20',1,4),
(2,3300,1100,'2026-01-23','2026-01-25',5,6),
(2,6600,1100,'2026-06-08','2026-06-13',5,7),
(2,3300,1100,'2026-04-23','2026-04-25',5,8),
(2,8400,1200,'2026-01-11','2026-01-17',1,9),
(2,7200,1200,'2026-05-12','2026-05-17',1,10),
(2,6750,1350,'2026-04-20','2026-04-24',3,11),
(2,6250,1250,'2026-04-04','2026-04-08',7,13),
(2,4650,1550,'2026-06-08','2026-06-10',11,14),
(2,7250,1450,'2026-03-22','2026-03-26',9,15),
(2,5500,1100,'2026-01-06','2026-01-10',5,16),
(2,6600,1100,'2026-01-28','2026-02-02',5,17),
(2,7500,1250,'2026-02-03','2026-02-08',7,18),
(2,3750,1250,'2026-03-30','2026-04-01',7,19),
(3,5000,1250,'2026-05-14','2026-05-17',7,4),
(3,5000,1250,'2026-04-24','2026-04-27',7,8),
(3,6750,1350,'2026-04-22','2026-04-26',3,11),
(3,2700,1350,'2026-04-04','2026-04-05',3,13),
(3,3750,1250,'2026-01-06','2026-01-08',7,16),
(3,5800,1450,'2026-01-29','2026-02-01',9,17),
(3,3600,1200,'2026-02-04','2026-02-06',1,18),
(3,3100,1550,'2026-03-31','2026-04-01',11,19),
(4,5400,1350,'2026-03-11','2026-03-14',3,4),
(4,2400,1200,'2026-04-24','2026-04-25',1,8),
(4,4350,1450,'2026-04-06','2026-04-08',9,13),
(4,7250,1450,'2026-02-04','2026-02-08',9,18),
(5,6000,1200,'2026-03-11','2026-03-15',1,4),
(5,5400,1350,'2026-02-03','2026-02-06',3,18);

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `idZaposlen` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  PRIMARY KEY (`idZaposlen`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`idZaposlen`,`ime`,`prezime`,`korisnickoIme`,`sifra`) values 
(1,'Mara','Maric','mara123','12345'),
(2,'Laza','Lazic','laza123','56789'),
(3,'Pera','Peric','pera123','11111'),
(4,'Mika','Mikic','mika123','22222');

/*Table structure for table `zaposleniradnasmena` */

DROP TABLE IF EXISTS `zaposleniradnasmena`;

CREATE TABLE `zaposleniradnasmena` (
  `datumSmene` date NOT NULL,
  `idZaposlen` bigint(20) NOT NULL,
  `idRadnaSmena` bigint(20) NOT NULL,
  PRIMARY KEY (`datumSmene`,`idZaposlen`,`idRadnaSmena`),
  KEY `idZaposlen` (`idZaposlen`),
  KEY `idRadnaSmena` (`idRadnaSmena`),
  CONSTRAINT `zaposleniradnasmena_ibfk_1` FOREIGN KEY (`idZaposlen`) REFERENCES `zaposleni` (`idZaposlen`),
  CONSTRAINT `zaposleniradnasmena_ibfk_2` FOREIGN KEY (`idRadnaSmena`) REFERENCES `radnasmena` (`idRadnaSmena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zaposleniradnasmena` */

insert  into `zaposleniradnasmena`(`datumSmene`,`idZaposlen`,`idRadnaSmena`) values 
('2026-02-03',3,1),
('2026-02-06',1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
