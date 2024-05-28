-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2024 at 11:07 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4
USE `tabibi`;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tabibi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `name` varchar(255) DEFAULT NULL,
  `test` bigint(20) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`name`, `test`, `uid`) VALUES
('wigo', 0, 9),
(NULL, 0, 10);

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appid` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `price` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `did` int(20) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  `booked` int(11) DEFAULT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `bid` bigint(20) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `appid` bigint(20) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL,
  `appid` bigint(20) NOT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `clinic`
--

CREATE TABLE `clinic` (
  `cid` bigint(20) DEFAULT NULL,
  `cloc` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `cnumber` varchar(255) DEFAULT NULL,
  `reviews` varchar(255) DEFAULT NULL,
  `test` bigint(20) NOT NULL,
  `workhrs` varchar(255) DEFAULT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clinic`
--

INSERT INTO `clinic` (`cid`, `cloc`, `cname`, `cnumber`, `reviews`, `test`, `workhrs`, `uid`) VALUES
(NULL, 'Mansoura', 'UFC', '01060094330', NULL, 0, NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis`
--

CREATE TABLE `diagnosis` (
  `diagnosis_id` bigint(20) NOT NULL,
  `diagnosis_name` varchar(500) DEFAULT NULL,
  `treatment` varchar(500) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `did` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diagnosis`
--

INSERT INTO `diagnosis` (`diagnosis_id`, `diagnosis_name`, `treatment`, `uid`, `did`) VALUES
(3, 'Obesity', 'el hal betaao el tamreen', 8, NULL),
(4, 'testing', 'To run', 8, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dr`
--

CREATE TABLE `dr` (
  `did` bigint(20) DEFAULT NULL,
  `educ` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `reviews` varchar(255) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `test` bigint(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dr`
--

INSERT INTO `dr` (`did`, `educ`, `firstname`, `lastname`, `number`, `reviews`, `specialization`, `test`, `uid`, `cid`) VALUES
(NULL, 'miu', 'Sameh', 'youssef', '01060094330', NULL, 'Pets', 0, 6, NULL),
(NULL, 'miu', 'Sameh', 'bakr', '01060094330', NULL, 'Pyschology', 0, 7, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pages`
--

CREATE TABLE `pages` (
  `pgid` bigint(20) NOT NULL,
  `class` varchar(255) DEFAULT NULL,
  `icons` varchar(255) DEFAULT NULL,
  `linkaddress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pages`
--

INSERT INTO `pages` (`pgid`, `class`, `icons`, `linkaddress`, `name`) VALUES
(1, '', 'iconoir:multiple-pages-add', '/Admin/addpage', 'Add Page'),
(2, '', 'icon-park-outline:permissions', '/Admin/addpermission ', 'Add Permission '),
(3, 'active', 'ri-bar-chart-line', '/Admin/admin-dashboard', 'Overview'),
(4, '', 'mdi:user-add', '/Admin/addadmin', 'Add Admin'),
(5, '', 'ri-bar-chart-line', '/Admin/search', 'Search And Delete'),
(6, '', 'uil:setting', '/patient/accountsettings', 'Account Settings'),
(7, '', '<box-icon name=\'spreadsheet\'></box-icon>', '/patient/diagnoses', 'Diagnoses '),
(8, '', '<box-icon name=\'info-circle\' ></box-icon>', '/Doctor/info', 'Patient Information '),
(9, '', '<box-icon name=\'search-alt-2\' ></box-icon>', '/Doctor/patients', 'Patient Search'),
(10, '', '<box-icon name=\'cog\' ></box-icon>', '/Doctor/accountSettings', 'Doctor account settings'),
(11, '', 'iconamoon--profile', '/Doctor/Profile', 'Doctor Profile'),
(12, '', 'material-symbols--edit-outline', '/Doctor/EditProfile', 'Doctor Edit Profile'),
(13, '', 'ic--baseline-add', '/appointments/add', 'Add Appointments'),
(14, '', 'carbon--view-filled', '/appointments/view', 'View Appointments'),
(15, '', 'material-symbols-light--book-sharp', '/booking', 'Booking Appointments'),
(16, '', 'tabler--logs', '/Admin/userlogs', 'User Logs'),
(17, '', 'clarity--assign-user-line', '/Clinic/assignDoctor', 'Assign Doctors'),
(18, '', 'ic--baseline-home', '/User/patientHomepage', 'Patient HomePage'),
(19, 'active', 'ic--baseline-home', '/User/clinicHomepage', 'Overview'),
(20, '', 'ic--baseline-home', '/User/DoctorHomePage', 'Doctor HomePage'),
(21, '', 'ic--twotone-search', '/User/search', 'Search For Doctors'),
(22, '', 'material-symbols--person-add-outline', '/Clinic/DoctorRegistration', 'Doctor Register '),
(23, '', 'mdi--home-add', '/Admin/ClinicRegistration', 'Clinic Register '),
(24, '', 'material-symbols--edit-outline', '/patient/EditProfile', 'Edit Profile');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `address` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `test` bigint(20) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`address`, `age`, `firstname`, `gender`, `lastname`, `number`, `pid`, `test`, `uid`) VALUES
('madinaty', '22', 'ibrahim', 'M', 'youssef', '01060094330', NULL, 0, 8);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `sid` bigint(20) NOT NULL,
  `day` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `did` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', '17c4c770-e557-4c03-b9b9-7201aebbdad6', 1716325275833, 1716325412749, 1800, 1716327212749, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session_attributes`
--

INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'email', 0xaced000574000c623340676d61696c2e636f6d),
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'firstname', 0xaced0005740003554643),
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'Location', 0xaced00057400084d616e736f757261),
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'number', 0xaced000574000b3031303630303934333330),
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'uid', 0xaced0005737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000004),
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'usertype', 0xaced0005740006636c696e6963),
('6792e6f0-54c0-48c7-bdf3-b732eb4ccee5', 'usertypeID', 0xaced00057372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000000000000002);

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

CREATE TABLE `treatment` (
  `treat_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `treat_name` varchar(255) DEFAULT NULL,
  `did` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userlog`
--

CREATE TABLE `userlog` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `log_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userlog`
--

INSERT INTO `userlog` (`id`, `date`, `user_id`, `log_date`) VALUES
(1, '2024-05-17', 5, NULL),
(2, '2024-05-17', 3, NULL),
(3, '2024-05-17', 3, NULL),
(4, '2024-05-17', 3, NULL),
(5, '2024-05-17', 3, NULL),
(6, '2024-05-17', 6, NULL),
(7, '2024-05-17', 6, NULL),
(8, '2024-05-17', 6, NULL),
(9, '2024-05-17', 6, NULL),
(10, '2024-05-18', 6, NULL),
(11, '2024-05-18', 8, NULL),
(12, '2024-05-18', 6, NULL),
(13, '2024-05-18', 8, NULL),
(14, NULL, 8, '2024-05-21'),
(15, NULL, 8, '2024-05-21'),
(16, NULL, 8, '2024-05-22'),
(17, NULL, 4, '2024-05-22');

-- --------------------------------------------------------

--
-- Table structure for table `usertypes`
--

CREATE TABLE `usertypes` (
  `utid` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usertypes`
--

INSERT INTO `usertypes` (`utid`, `name`) VALUES
(1, 'admin'),
(2, 'clinic'),
(3, 'doctor'),
(4, 'patient');

-- --------------------------------------------------------

--
-- Table structure for table `usertype_pages`
--

CREATE TABLE `usertype_pages` (
  `upid` bigint(20) NOT NULL,
  `pageid` bigint(20) NOT NULL,
  `usertypeid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usertype_pages`
--

INSERT INTO `usertype_pages` (`upid`, `pageid`, `usertypeid`) VALUES
(12, 3, 1),
(13, 2, 1),
(14, 1, 1),
(15, 4, 1),
(16, 5, 1),
(17, 23, 1),
(18, 16, 1),
(19, 21, 4),
(20, 7, 4),
(21, 6, 4),
(22, 19, 2),
(23, 13, 2),
(24, 14, 2),
(25, 22, 2),
(26, 17, 2),
(27, 20, 3),
(28, 11, 3),
(29, 12, 3),
(30, 10, 3),
(31, 9, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_acc`
--

CREATE TABLE `user_acc` (
  `uid` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pass` varchar(255) NOT NULL,
  `usertype_id` bigint(20) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_acc`
--

INSERT INTO `user_acc` (`uid`, `email`, `image`, `pass`, `usertype_id`, `token`) VALUES
(4, 'b3@gmail.com', NULL, '$2a$12$276qESJ/F6R0w0bHMWzVFeVKwhTdcz2fxb0Asb0HXF3FzDn.qqoW2', 2, NULL),
(6, 'youssef@gmail.com', NULL, '$2a$12$X7evHrPoHJXwa8lilt.prO3CR5oh5U560kHlEWumRbareYL2b5BmS', 3, NULL),
(7, 'test12@gmail.com', NULL, '$2a$12$hVarkFaO5M9nd12YNZGaX.JvUOcA/WVDDCvhzM5pyR7MoVZ4P4pNy', 3, NULL),
(8, 'ibrahim2105690@miuegypt.edu.eg', NULL, '$2a$12$pf4URtdmfhH3SPlthvCvD..hAT5NRfTEAFy4RbXf/a73ActQIehz2', 4, '0'),
(9, 'wigoadmin@gmail.com', NULL, '$2a$12$pf4URtdmfhH3SPlthvCvD..hAT5NRfTEAFy4RbXf/a73ActQIehz2', 1, NULL),
(10, 'ahmedadmin@gmail.com', NULL, '$2a$12$.lDJPyJTQH68kvr4SA6dUerJLVkAFp611BqGrqS6htIDIIDhsp6/e', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `workplaces`
--

CREATE TABLE `workplaces` (
  `id` bigint(20) NOT NULL,
  `cid` int(11) NOT NULL,
  `did` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appid`),
  ADD KEY `FKe977df8kbfshg32iqc0nkwnl8` (`cid`),
  ADD KEY `FKsutlb2h5rl8dhtyd67p3jj4o4` (`did`),
  ADD KEY `FKsd7wxrc9xorv9ancmcn0alpwp` (`pid`);

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`bid`),
  ADD KEY `FKt0g1wn0n06p0oyfbodmat2rma` (`appid`),
  ADD KEY `FKihgr3hboglj5dyg4w2saic15s` (`pid`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK52567qw3f22mpevk6ox58fbs2` (`appid`),
  ADD KEY `FKpkl6eqf9g1mx5sbtt1tsplf68` (`pid`);

--
-- Indexes for table `clinic`
--
ALTER TABLE `clinic`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD PRIMARY KEY (`diagnosis_id`),
  ADD KEY `FKf4s9s48579qnn75rhnxv3kp5f` (`uid`),
  ADD KEY `FKmlia22qsnf8a4txe7d7o3yy02` (`did`);

--
-- Indexes for table `dr`
--
ALTER TABLE `dr`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKbqcglthq4x6iuy9etpxcs18hc` (`cid`);

--
-- Indexes for table `pages`
--
ALTER TABLE `pages`
  ADD PRIMARY KEY (`pgid`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`sid`),
  ADD KEY `FKj2wq6knm3d0010hodujd3fyay` (`did`);

--
-- Indexes for table `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indexes for table `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`treat_id`),
  ADD KEY `FKi5rvqj1rgiw4g1xt7mebs93jq` (`did`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userlog`
--
ALTER TABLE `userlog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usertypes`
--
ALTER TABLE `usertypes`
  ADD PRIMARY KEY (`utid`);

--
-- Indexes for table `usertype_pages`
--
ALTER TABLE `usertype_pages`
  ADD PRIMARY KEY (`upid`),
  ADD KEY `FKmh4pgpvo8dqekjuscycwrpyny` (`pageid`),
  ADD KEY `FKg8wfii8jl6v0pyro7lby22dcr` (`usertypeid`);

--
-- Indexes for table `user_acc`
--
ALTER TABLE `user_acc`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `FKmjx5nbhy5cpjx3npnoa0hjj3w` (`usertype_id`);

--
-- Indexes for table `workplaces`
--
ALTER TABLE `workplaces`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmay693ieo0snfi5lxwf1hv1jm` (`cid`),
  ADD KEY `FKro8yk4jij74gcqx8wp8te2tal` (`did`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appid` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `bid` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `diagnosis`
--
ALTER TABLE `diagnosis`
  MODIFY `diagnosis_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pages`
--
ALTER TABLE `pages`
  MODIFY `pgid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `sid` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `treatment`
--
ALTER TABLE `treatment`
  MODIFY `treat_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `userlog`
--
ALTER TABLE `userlog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `usertypes`
--
ALTER TABLE `usertypes`
  MODIFY `utid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `usertype_pages`
--
ALTER TABLE `usertype_pages`
  MODIFY `upid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `user_acc`
--
ALTER TABLE `user_acc`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `workplaces`
--
ALTER TABLE `workplaces`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FKp6e24jsgnumfrfuq4malg7h05` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`);

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FKe977df8kbfshg32iqc0nkwnl8` FOREIGN KEY (`cid`) REFERENCES `clinic` (`uid`),
  ADD CONSTRAINT `FKsd7wxrc9xorv9ancmcn0alpwp` FOREIGN KEY (`pid`) REFERENCES `patient` (`uid`),
  ADD CONSTRAINT `FKsutlb2h5rl8dhtyd67p3jj4o4` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`),
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `user_acc` (`uid`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`did`) REFERENCES `user_acc` (`uid`);

--
-- Constraints for table `billing`
--
ALTER TABLE `billing`
  ADD CONSTRAINT `FKihgr3hboglj5dyg4w2saic15s` FOREIGN KEY (`pid`) REFERENCES `patient` (`uid`),
  ADD CONSTRAINT `FKt0g1wn0n06p0oyfbodmat2rma` FOREIGN KEY (`appid`) REFERENCES `appointments` (`appid`);

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `FK52567qw3f22mpevk6ox58fbs2` FOREIGN KEY (`appid`) REFERENCES `appointments` (`appid`),
  ADD CONSTRAINT `FKpkl6eqf9g1mx5sbtt1tsplf68` FOREIGN KEY (`pid`) REFERENCES `patient` (`uid`);

--
-- Constraints for table `clinic`
--
ALTER TABLE `clinic`
  ADD CONSTRAINT `FKc7dh2g97q5rtw7dtpqg39ayiv` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`);

--
-- Constraints for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD CONSTRAINT `FKf4s9s48579qnn75rhnxv3kp5f` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`),
  ADD CONSTRAINT `FKmlia22qsnf8a4txe7d7o3yy02` FOREIGN KEY (`did`) REFERENCES `user_acc` (`uid`);

--
-- Constraints for table `dr`
--
ALTER TABLE `dr`
  ADD CONSTRAINT `FKbmipuv5iw9tsg4ve05wtwcpex` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`),
  ADD CONSTRAINT `FKbqcglthq4x6iuy9etpxcs18hc` FOREIGN KEY (`cid`) REFERENCES `clinic` (`uid`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `FKhs822k52089mn45d1jg08pdtv` FOREIGN KEY (`uid`) REFERENCES `user_acc` (`uid`);

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `FKj2wq6knm3d0010hodujd3fyay` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`);

--
-- Constraints for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Constraints for table `treatment`
--
ALTER TABLE `treatment`
  ADD CONSTRAINT `FKi5rvqj1rgiw4g1xt7mebs93jq` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`);

--
-- Constraints for table `usertype_pages`
--
ALTER TABLE `usertype_pages`
  ADD CONSTRAINT `FKg8wfii8jl6v0pyro7lby22dcr` FOREIGN KEY (`usertypeid`) REFERENCES `usertypes` (`utid`),
  ADD CONSTRAINT `FKmh4pgpvo8dqekjuscycwrpyny` FOREIGN KEY (`pageid`) REFERENCES `pages` (`pgid`);

--
-- Constraints for table `user_acc`
--
ALTER TABLE `user_acc`
  ADD CONSTRAINT `FKmjx5nbhy5cpjx3npnoa0hjj3w` FOREIGN KEY (`usertype_id`) REFERENCES `usertypes` (`utid`);

--
-- Constraints for table `workplaces`
--
ALTER TABLE `workplaces`
  ADD CONSTRAINT `FKmay693ieo0snfi5lxwf1hv1jm` FOREIGN KEY (`cid`) REFERENCES `clinic` (`uid`),
  ADD CONSTRAINT `FKro8yk4jij74gcqx8wp8te2tal` FOREIGN KEY (`did`) REFERENCES `dr` (`uid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
