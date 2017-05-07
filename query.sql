
CREATE DATABASE  IF NOT EXISTS `orion_applicants`;
USE `orion_applicants`;


DROP TABLE IF EXISTS `applicants`;
CREATE TABLE `applicants` (
  id        INT PRIMARY KEY AUTO_INCREMENT,
  NAMECIRILIC VARCHAR(50),
  NAMELATIN VARCHAR(50),
  REGISTRATIONDATE Date,
  VACANCY VARCHAR(50),
  INVITATIONRECIEVED boolean,
  DATEOFRECEIVINGINVITATION Date,
  COMMENTARY VARCHAR(50),
  CONTACT VARCHAR(50),
  RECRUITER VARCHAR(50)
);

DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `groups` WRITE;
INSERT INTO `groups` VALUES (1,'admins'),(2,'users'),(3,'guests');
UNLOCK TABLES;

DROP TABLE IF EXISTS `group_authorities`;
CREATE TABLE `group_authorities` (
  `group_id` bigint(20) NOT NULL,
  `authority` varchar(50) NOT NULL,
  KEY `fk_group_authorities_group` (`group_id`),
  CONSTRAINT `fk_group_authorities_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `group_authorities` WRITE;
INSERT INTO `group_authorities` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_USER'),(2,'ROLE_USER'),(1,'ROLE_GUEST'),(2,'ROLE_GUEST'),(3,'ROLE_GUEST');
UNLOCK TABLES;


DROP TABLE IF EXISTS `group_members`;
CREATE TABLE `group_members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_group_members_group` (`group_id`),
  CONSTRAINT `fk_group_members_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `group_members` WRITE;
INSERT INTO `group_members` VALUES (1,'admin',1),(2,'chief',1),(3,'user',2),(4,'guest',3);
UNLOCK TABLES;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('admin@admin.ua','admin',1),('chief','chief',1),('guest','guest',1),('user','user',1);
UNLOCK TABLES;
