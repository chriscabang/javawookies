-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2019 at 04:27 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `timetracker`
--
CREATE DATABASE IF NOT EXISTS `timetracker`;
-- --------------------------------------------------------

--
-- User: 'admin'
-- Password: 'adminuser'
--
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'adminuser';
-- --------------------------------------------------------


--
-- Table structure for table `deletedid`
--

CREATE TABLE `deletedid` (
  `emp_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `emp_id` varchar(5) NOT NULL,
  `passcode` varchar(15) NOT NULL,
  `emp_fname` varchar(25) NOT NULL,
  `emp_mname` varchar(25) NOT NULL,
  `emp_lname` varchar(25) NOT NULL,
  `emp_age` int(3) NOT NULL,
  `emp_gender` varchar(6) NOT NULL,
  `emp_address` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`emp_id`, `passcode`, `emp_fname`, `emp_mname`, `emp_lname`, `emp_age`, `emp_gender`, `emp_address`) VALUES
('0', '0', 'Administrator', 'Admin', 'Admin', 0, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `loginpc`
--

CREATE TABLE `loginpc` (
  `emp_id` varchar(5) NOT NULL,
  `login_pc` varchar(25) NOT NULL,
  `date_in` date NOT NULL,
  `time_in` time(6) NOT NULL,
  `emp_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `logoutpc`
--

CREATE TABLE `logoutpc` (
  `emp_id` varchar(5) NOT NULL,
  `logout_pc` varchar(25) NOT NULL,
  `date_out` date NOT NULL,
  `time_out` time(6) NOT NULL,
  `emp_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `timelogs`
--

CREATE TABLE `timelogs` (
  `emp_id` varchar(5) NOT NULL,
  `date_in` date NOT NULL,
  `time_in` time(6) NOT NULL,
  `date_out` date NOT NULL,
  `time_out` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deletedid`
--
ALTER TABLE `deletedid`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`emp_id`),
  ADD UNIQUE KEY `emp_id` (`emp_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

GRANT ALL PRIVILEGES ON timetracker.* TO 'admin'@'localhost';
