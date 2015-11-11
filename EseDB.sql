-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 11, 2015 at 02:03 
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `EseDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE IF NOT EXISTS `Address` (
  `id` bigint(20) NOT NULL,
  `street` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`id`, `street`) VALUES
(1, 'EndOfTheWorld');

-- --------------------------------------------------------

--
-- Table structure for table `Course`
--

CREATE TABLE IF NOT EXISTS `Course` (
  `id` bigint(20) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  `subject_id` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Course`
--

INSERT INTO `Course` (`id`, `courseName`, `semester`, `year`, `subject_id`) VALUES
(1, 'ESE', '3', 2015, 1),
(2, 'Make a Kranich', '6', 2016, 2),
(3, 'Entwicklungspsychologie I', '3', 2015, 3),
(4, 'Sozialpsychologie', '3', 2015, 3);

-- --------------------------------------------------------

--
-- Table structure for table `Grade`
--

CREATE TABLE IF NOT EXISTS `Grade` (
  `id` bigint(20) NOT NULL,
  `course` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `remove` tinyint(1) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Request`
--

CREATE TABLE IF NOT EXISTS `Request` (
  `id` bigint(20) NOT NULL,
  `isAccepted` tinyint(1) NOT NULL,
  `isActiv` tinyint(1) NOT NULL,
  `isDeclined` tinyint(1) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  `newAnwser` tinyint(1) NOT NULL,
  `newRequest` tinyint(1) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `tutor_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Subject`
--

CREATE TABLE IF NOT EXISTS `Subject` (
  `id` bigint(20) NOT NULL,
  `degreeLevel` varchar(255) DEFAULT NULL,
  `subjectName` varchar(255) NOT NULL,
  `university_UId` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Subject`
--

INSERT INTO `Subject` (`id`, `degreeLevel`, `subjectName`, `university_UId`) VALUES
(1, '6', 'Informatik', 1),
(2, '6', 'Origami', 2),
(3, '6', 'Psychologie', 1);

-- --------------------------------------------------------

--
-- Table structure for table `TimeSlot`
--

CREATE TABLE IF NOT EXISTS `TimeSlot` (
  `id` bigint(20) NOT NULL,
  `day` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `remove` tinyint(1) NOT NULL,
  `semesterOrSemesterBreak` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Tutor`
--

CREATE TABLE IF NOT EXISTS `Tutor` (
  `id` bigint(20) NOT NULL,
  `tutorsName` varchar(255) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Tutor`
--

INSERT INTO `Tutor` (`id`, `tutorsName`, `course_id`, `user_id`) VALUES
(0, 'ese', 3, 1),
(1, NULL, 2, 1),
(2, NULL, 4, 1),
(3, NULL, 2, 2),
(4, NULL, 3, 3),
(5, NULL, 3, 4),
(6, NULL, 3, 5),
(9, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `University`
--

CREATE TABLE IF NOT EXISTS `University` (
  `UId` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `universityName` varchar(255) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `University`
--

INSERT INTO `University` (`UId`, `city`, `country`, `universityName`, `address_id`) VALUES
(1, 'Bern', 'Switzerland', 'Universität Bern', 1),
(2, 'Hong Kong', 'Hong Kong', 'University of Hong Kong', 1);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` bigint(20) NOT NULL,
  `biography` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(111) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `grades` longblob,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `timeSlots` longblob
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id`, `biography`, `EMAIL`, `enabled`, `grades`, `name`, `password`, `timeSlots`) VALUES
(1, 'Ich bin ese.\r\nI''m awesome!', 'ese@ese.ese', 1, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000002770400000002737200166f72672e73616d706c652e6d6f64656c2e477261646549c47e2b43793d510200065a000672656d6f76654c0006636f757273657400124c6a6176612f6c616e672f537472696e673b4c0005677261646571007e00034c000269647400104c6a6176612f6c616e672f4c6f6e673b4c00077375626a65637471007e00034c000a756e697665727369747971007e0003787000740003455345740001367074000a496e666f726d6174696b740011556e69766572736974c3a474204265726e7371007e000200740019456e747769636b6c756e677370737963686f6c6f6769652049740001367074000b50737963686f6c6f676965740011556e69766572736974c3a474204265726e78, 'ese7', '$2a$10$Al9efYHkINY6UaM8lIRtVe.I2jaNJxexneUCCZnGcAzoeQaaB.j/m', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200196f72672e73616d706c652e6d6f64656c2e54696d65536c6f7400000000000000010200065a000672656d6f76654c00036461797400124c6a6176612f6c616e672f537472696e673b4c0007656e6454696d6571007e00034c000269647400104c6a6176612f6c616e672f4c6f6e673b4c001773656d65737465724f7253656d6573746572427265616b71007e00034c0009737461727454696d6571007e00037870007400064d6f6e74616774000531333a30307074001348657262737473656d6573746572203230313574000531303a303078),
(2, NULL, 's@s.s', 1, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078, 'Simon', '$2a$10$07dSYxvDHGsIzwzBmvtLheXPOs44cmR/wEsuge0rNUIhv7ncRDGh6', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(3, NULL, 'm@m.m', 1, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078, 'Mirko', '$2a$10$o8QgvL0C5wxrEujEh7QO..6a07dxLJ6Z0kJLsRCaGxpRfIoE/05Wa', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(4, NULL, 'j@j.j', 1, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078, 'Jiannis', '$2a$10$w3Z3VdoiKbo6E1cIp8tcT./WKq3HhlSRiz8gMfhfsGoxYsYBkZrVa', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(5, NULL, 'l@l.l', 1, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078, 'Lucien', '$2a$10$TzV0xijqdYSbupy2aWWV4.l5vS8G2ZwtQR/L0bJcJyeoAjvg28R22', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(45) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`id`, `role`, `user_id`) VALUES
(1, 'ROLE_USER', 1),
(2, 'ROLE_USER', 1),
(3, 'ROLE_USER', 1),
(4, 'ROLE_USER', 1),
(5, 'ROLE_USER', 1),
(6, 'ROLE_USER', 1),
(7, 'ROLE_USER', 1),
(8, 'ROLE_USER', 2),
(9, 'ROLE_USER', 3),
(10, 'ROLE_USER', 4),
(11, 'ROLE_USER', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Course`
--
ALTER TABLE `Course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK78A7CC3B53D99FDB` (`subject_id`);

--
-- Indexes for table `Grade`
--
ALTER TABLE `Grade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Request`
--
ALTER TABLE `Request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKA4878A6F7120B219` (`course_id`),
  ADD KEY `FKA4878A6F1CD3EB69` (`student_id`),
  ADD KEY `FKA4878A6F65E2708E` (`tutor_id`);

--
-- Indexes for table `Subject`
--
ALTER TABLE `Subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKF3E2ED0CDDF4946C` (`university_UId`);

--
-- Indexes for table `TimeSlot`
--
ALTER TABLE `TimeSlot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Tutor`
--
ALTER TABLE `Tutor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4D6A7167120B219` (`course_id`),
  ADD KEY `FK4D6A716519857D9` (`user_id`);

--
-- Indexes for table `University`
--
ALTER TABLE `University`
  ADD PRIMARY KEY (`UId`),
  ADD UNIQUE KEY `universityName` (`universityName`),
  ADD KEY `FK821AC6AEF0E6CADB` (`address_id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`),
  ADD UNIQUE KEY `EMAIL_2` (`EMAIL`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK73429949519857D9` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Course`
--
ALTER TABLE `Course`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Grade`
--
ALTER TABLE `Grade`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Request`
--
ALTER TABLE `Request`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Subject`
--
ALTER TABLE `Subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `TimeSlot`
--
ALTER TABLE `TimeSlot`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `University`
--
ALTER TABLE `University`
  MODIFY `UId` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Course`
--
ALTER TABLE `Course`
  ADD CONSTRAINT `FK78A7CC3B53D99FDB` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`);

--
-- Constraints for table `Request`
--
ALTER TABLE `Request`
  ADD CONSTRAINT `FKA4878A6F1CD3EB69` FOREIGN KEY (`student_id`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `FKA4878A6F65E2708E` FOREIGN KEY (`tutor_id`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `FKA4878A6F7120B219` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`);

--
-- Constraints for table `Subject`
--
ALTER TABLE `Subject`
  ADD CONSTRAINT `FKF3E2ED0CDDF4946C` FOREIGN KEY (`university_UId`) REFERENCES `University` (`UId`);

--
-- Constraints for table `Tutor`
--
ALTER TABLE `Tutor`
  ADD CONSTRAINT `FK4D6A716519857D9` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `FK4D6A7167120B219` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`);

--
-- Constraints for table `University`
--
ALTER TABLE `University`
  ADD CONSTRAINT `FK821AC6AEF0E6CADB` FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK73429949519857D9` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
