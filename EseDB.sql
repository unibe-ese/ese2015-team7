-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2015 at 03:48 PM
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

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `biography`, `EMAIL`, `enabled`, `firstName`, `lastName`, `password`, `timeSlots`) VALUES
(5, '', 'giannis@gmail.com', 1, 'giannis', 'gl', '$2a$10$txIV8B1GgJRLGAsDAIJTq./RYq0cxqRBOkGdrMm5FpSC1yGjeuNjy', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(6, '', 'argy@gmail.com', 1, 'argy', 'gl', '$2a$10$39PCQj.jPXLvfNHnqGnL9./qhT7GhiJH3X/n2pFyI/OQPqKDw49pm', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200196f72672e73616d706c652e6d6f64656c2e54696d65536c6f7400000000000000010200065a000672656d6f76654c00036461797400124c6a6176612f6c616e672f537472696e673b4c0007656e6454696d6571007e00034c000269647400104c6a6176612f6c616e672f4c6f6e673b4c001773656d65737465724f7253656d6573746572427265616b71007e00034c0009737461727454696d6571007e00037870007400044e6f6e657400044e6f6e65707400044e6f6e657400044e6f6e6578),
(7, NULL, 'test@test.ch', 1, 'test', 'test', '$2a$10$L651.nqoFnQ79uGZItYqkemKzJ6R5HatGkE2xsTQ55mpLfbIiipR.', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(8, '', 'lmadl@gmail.com', 1, 'lucian', 'madl', '$2a$10$5QdRTjkUMt2JFA3TpwgeMOqQ4n3FB0Q37qNeeZYOFJ.Ycqmrm0oxq', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(9, '', 'mirko@gmail.com', 1, 'mirko', 'bristle', '$2a$10$kkus2t0shxWnnkwt6lsVQuOGD3ZqEM0xsO/1EULinimK7UBnZ9Igm', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(10, '', 'simon@gmail.com', 1, 'simon', 'furrer', '$2a$10$esYGdjjL98WI2Bm1xKUbEe27fIsC7rto8O/i15p0J8dHF.pEX9QaS', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(11, 'Born in Ulm, Württemberg, Germany in 1879, Albert Einstein had a passion for inquiry that eventually led him to develop the special and general theories of relativity. In 1921, he won the Nobel Prize for physics.', 'albert.einstein@relativity.com', 1, 'albert', 'einstein', '$2a$10$Zm/1c7czVNQ1URVgz/oaje/Ufa9wD12jUFY1elnundA5ikLjAEaNi', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(12, NULL, 'alice@alice.com', 1, 'alice', 'alice', '$2a$10$iTL/m1VDV5gKhCR5Wd9speQgn2oCVc0YIYtVSibDtjytZxdV1bC.a', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
(13, 'fee> 40./', 'bob@bob.com', 1, 'bob', 'bob', '$2a$10$ySxJSMEoMafdfOnkVV5Vm.k2DA2u6fFiatMp1wlrWB28QuYFD48lS', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078);

--
-- Dumping data for table `usercourse`
--

INSERT INTO `usercourse` (`userCourseId`, `grade`, `teaching`, `course`, `user`) VALUES
(1, 5.5, 1, 1, 5),
(3, 6, 1, 1, 6),
(4, 5.5, 0, 2, 5),
(5, 6, 1, 3, 8),
(6, 0, 1, 5, 9),
(7, 6, 1, 1, 9),
(8, 6, 1, 4, 10),
(9, 4, 0, 1, 11),
(10, 4.5, 0, 3, 11),
(11, 5, 1, 4, 11),
(12, 5.5, 1, 1, 13);

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`id`, `isAccepted`, `isActiv`, `isDeclined`, `isDeleted`, `newRequest`, `userCourseId`, `student_id`, `userCourse_userCourseId`) VALUES
(3, 1, 0, 0, 0, 1, 12, 12, 12),
(5, 1, 0, 0, 0, 1, 3, 5, 3),
(6, 1, 0, 0, 0, 1, 5, 5, 5),
(7, 1, 0, 0, 0, 1, 8, 5, 8),
(8, 1, 0, 0, 0, 1, 1, 10, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
