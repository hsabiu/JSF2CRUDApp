# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.22)
# Database: peopleware
# Generation Time: 2018-07-27 06:46:11 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table applicants
# ------------------------------------------------------------

DROP TABLE IF EXISTS `applicants`;

CREATE TABLE `applicants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL DEFAULT '',
  `min_salary` varchar(45) NOT NULL DEFAULT '',
  `working_time` varchar(45) NOT NULL DEFAULT '',
  `degree_level` varchar(45) NOT NULL DEFAULT '',
  `specialization` varchar(45) NOT NULL DEFAULT '',
  `skills` text NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `applicants` WRITE;
/*!40000 ALTER TABLE `applicants` DISABLE KEYS */;

INSERT INTO `applicants` (`id`, `firstname`, `lastname`, `email`, `phone_number`, `min_salary`, `working_time`, `degree_level`, `specialization`, `skills`, `timestamp`)
VALUES
	(1,'John','Smith','john.smith@mail.com','+1 (306) 777-0080','100000','Full-time','BSc','Computer Science','\"Java\":5,\"Bootstrap\":4','2018-07-25 17:50:12'),
	(2,'Anne','Thomas','anne.thomas@mail.com','+1 (306) 555-0080','70000','Part-time','BSc','Computer Science','\"Java\":5,\"Bootstrap\": 2','2018-07-25 17:51:12'),
	(3,'Claire','Jeffery','claire.jeffery@mail.com','+1 (306) 456-3456','90000','Full-time','PhD','Software Engineering','\"Python:5\",\"Java\":4','2018-07-25 17:52:12'),
	(4,'Heather','Gramm','heather.gramm@mail.com','+1 (345) 667-3456','35000','Part-time','MSc','Computer Science','\"Python\":3,\"Java\":5','2018-07-25 18:41:07'),
	(5,'Jonathan','Philip','jonathan.philip@mail.com','+1 (456) 456-6676','40000','Both','BSc','Computer Science','\"Scala\":3,\"Ruby\":2','2018-07-25 22:36:19'),
	(6,'Seema ','Claunch','seema.claunch@mail.com','+1 (667) 896-3445','50000','Part-time','Bsc','Computer Science','\"Java\":5,\"Bootstrap\":3','2018-07-26 18:37:21'),
	(7,'Andy ','Delreal','andy.delreal@mail.com','+1 (445) 664-4444','69000','Both','BSc','Software Engineering','\"Java\":3,\"Bootstrap\":5','2018-07-26 18:37:47'),
	(8,'Virgil','Kidder','virgil.kidder@mail.com','+1 (556) 456-6676','35000','Full-time','PhD','Electrical Engineering','\"Java\":1,\"Bootstrap\":3, \"C\":5, \"Python\":4','2018-07-26 18:40:40'),
	(9,'Wilbe ','Kingery','wilbe.kingery@mail.com','+1 (334) 556-7899','52000','Part-time','BSc','Computer Science','\"Java\":2,\"Bootstrap\":3, \"C++\":4,\"C#\":2','2018-07-26 18:40:43'),
	(10,'Arlen ','Case','arlen.case@mail.com','+1 (445) 667-9975','60000','Full-time','MSc','Computer Science','\"JavaScript\":4,\"PHP\":,\"Bash\":3','2018-07-26 18:40:47');

/*!40000 ALTER TABLE `applicants` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table jobposts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `jobposts`;

CREATE TABLE `jobposts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employer_name` varchar(45) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  `job_title` varchar(500) NOT NULL,
  `job_description` text NOT NULL,
  `salary_range` varchar(45) NOT NULL,
  `working_time` varchar(45) NOT NULL,
  `skills` text NOT NULL,
  `degree` text NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `jobposts` WRITE;
/*!40000 ALTER TABLE `jobposts` DISABLE KEYS */;

INSERT INTO `jobposts` (`id`, `employer_name`, `contact_number`, `job_title`, `job_description`, `salary_range`, `working_time`, `skills`, `degree`, `timestamp`)
VALUES
	(1,'MapleSoft','+1 (800) 777-0080','Software Developer','Java developer with insights in the latest trends of web development','50000 - 100000','Full-time','\"Java\":1,\"Bootstrap\":1','BSc in Computer Science,BSc in Software Engineering','2018-07-25 18:02:26'),
	(2,'Noesis','+1 (667) 334-5645','Data Analysis Software Developer','A very good data analysis software developer with 5 years of experience','60000 - 80000','Part-time','\"Python\":1,\"Java\":1','BSc in Computer Science, BSc in Software Engineering','2018-07-25 19:02:26'),
	(3,'Google','+1 (567) 324-2345','Quality Assuarance','We want someone with knowledge and experience in test automation softwares and systems e.g Jenkins','70000 - 90000','Full-time','\"Jenkins\":4,\"Git\":4','MSc in Electrical Engineering','2018-07-25 19:04:52'),
	(5,'Amazon','+1 (231) 890-2900','Senior Software Developer','A Bachelor’s Degree in Computer Science, Engineering, Mathematics or similar field with an excellent academic record in Computer Science courses. Experience with web technologies (REST, Angular2, CSS, JavaScript, JQuery, D3 and/or RXJS). ','40000 - 50000','Full-time','\"JavaScript\":3,\"Java\":2,\"Scala\":1,\"C++\":4','MSc in Software Engineering','2018-07-25 20:01:52'),
	(6,'Microsoft','+1 (567) 224-5577','Machine Learning Scientist','Help us build an exciting new Cloud Platform on AWS to provide the fundamental building blocks on which next generation cloud security applications are rapidly developed by a community of in-house developers.','60000 - 80000','Part-time','\"Backbone.js\":1,\"Node.ja\":2,\"Bootstrap\":3, \"D3\":1, \"Angular\":1','BSc in Computer Science','2018-07-26 18:56:57'),
	(7,'IBM','+1 (345) 678-2233','Business Intelligence Analyst','Your role: We are looking for someone to join our team to help build the next generation cloud big data products. As a senior member of our software development, team you will work on an agile continuous delivery team collaborating with designers, senior developers and architects to deliver product capabilities and features.','30000 - 60000','Full-time','\"Java\":1,\"Bootstrap\":1,\"Python\":1,\"C/C++\":2','BSc in Software Engineering','2018-07-26 18:57:13'),
	(8,'Apple','+1 (347) 789-1122','Java Developer','You will be responsible for the architecture, design, and integration of a fleet command and control component. Your initial challenge will be to architect a communication layer that permits a single fleet manager instance to control hundreds of robots while using minimal bandwidth and resources. You will work closely with the rest of the development team to make sure that we are delivering properly engineered solutions and will help with the integration of significant fleet deployments. ','70000 - 100000','Part-time','\"Java\":1,\"Bootstrap\":1','MSc in Computer Science','2018-07-26 18:57:27'),
	(9,'Facebook','+1 (456) 123-5688','Software Analyst','The Software Analyst takes care of our customers most important asset, their data. The Database Services team requires an experienced SQL Server Database Administrator.','90000 - 100000','Full-time','\"Javascript\":1,\"HTML\":2,\"CSS\":2,\"NodeJS\":2','PhD in Electrical Engineering','2018-07-26 18:57:37'),
	(10,'Samsung','+1 (800) 556-1123','Data Scientist','We employ a diverse and highly talented team who live and breathe robotics. We believe that work must have a high “cool” factor and every day should bring new knowledge. We need more passionate people on our team who are willing and able to push the boundaries of robotics into focused and practical applications. ','45000 - 55000','Full-time','\"Javascript\":1,\"HTML\":2,\"CSS\":2,\"NodeJS\":2','PhD in Computer Science','2018-07-26 18:59:04'),
	(11,'Oracle','+1 (953) 224-5567','Software Release Engineer','As part of our Agile software development team, you will participate in the creation of the next generation of platforms for  market-leading security products. These products are responsible for the storage, management and processing of video, audio and metadata. They include high throughput networking, video analytics, video processing, and data storage subsystems. ','65000 - 75000','Part-time','\"C++\":1,\"Linux\":1,\"Java\":2','BSc in Computer Science, MSc in Computer Science','2018-07-26 19:00:08'),
	(12,'Intel','+1 (223) 123-3456','Software Engineer','We are looking for fun, intelligent, team oriented people who believe in our values: Customer Focus, Transparency, Diligence, Optimism, and Agility.','80000 - 85000','Full-time','\"Java\":1,\"Bootstrap\":1,\"Python\":1,\"C/C++\":2','BSc in Computer Science','2018-07-26 19:00:18'),
	(13,'Cisco','+1 (786) 231-3345','Software Developer','We are seeking an enthusiastic software professional to join our dynamic, highly successful engineering team. As a software developer working on industry-leading unified-collaboration products. You’ll be making a positive impact on how people live and work.','40000 - 70000','Full-time','\"Javascript\":1,\"HTML\":2,\"CSS\":2,\"NodeJS\":2','BSc in Computer Science','2018-07-26 19:00:47'),
	(14,'NVIDIA','+1 (556) 445-7788','Graphics Tool Developer','The Software Tools team is tasked with creating and supporting multiple test applications which target all aspects of software development for Graphics products on PC platforms. Software engineers on the team apply industrial leading technologies and system programming skills in both Windows and Linux environment.','50000 - 55000','Part-time','\"C/C++\":3,\"Linux\":2,\"Java\":2','BSc in Software Engineering','2018-07-26 19:12:40'),
	(15,'Qualcomm','+1 (234) 554-3123','UI Developer','Looking for a hands-on UI Engineer to assist in the design and development of a next-generation application user interface. The ideal candidate will have experience in or interest and aptitude toward contemporary user interface design.','40000 - 50000','Full-time','\"Backbone.js\":1,\"Node.ja\":2,\"Bootstrap\":3, \"D3\":1, \"Angular\":1','MSc in Computer Science','2018-07-26 19:12:43');

/*!40000 ALTER TABLE `jobposts` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
