-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2015 at 02:45 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `esedb`
--

--
-- Dumping data for table `university`
--

INSERT INTO `university` (`UId`, `city`, `country`, `universityName`, `address_id`) VALUES
(1, NULL, NULL, 'bern', NULL);

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `degreeLevel`, `subjectName`, `university_UId`) VALUES
(2, NULL, 'inf', 1),
(3, NULL, 'Math', 1);

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `courseName`, `semester`, `year`, `subject_id`) VALUES
(1, 'ese', NULL, 5, 2),
(2, 'db', NULL, 3, 2),
(3, 'linear algebra', NULL, 0, 3),
(4, 'analysis', NULL, 0, 3),
(5, 'dml', NULL, 0, 2);



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
