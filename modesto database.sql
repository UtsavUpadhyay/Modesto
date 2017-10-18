-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 20, 2015 at 11:29 PM
-- Server version: 5.1.54
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `modesto database`
--

-- --------------------------------------------------------

--
-- Table structure for table `category_place`
--

CREATE TABLE IF NOT EXISTS `category_place` (
  `category` varchar(20) NOT NULL,
  `place_name` varchar(30) NOT NULL,
  `num_reviews` int(10) DEFAULT NULL,
  `num_comments` int(10) DEFAULT NULL,
  `num_ratings` int(10) DEFAULT NULL,
  PRIMARY KEY (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_place`
--


-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` double NOT NULL AUTO_INCREMENT,
  `comments` varchar(1000) DEFAULT NULL,
  `place_name` varchar(100) NOT NULL,
  `category` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `comments`, `place_name`, `category`, `username`) VALUES
(1, 'bfyhb', 'CITYGOLD', 'theatre', 'fhhfyhhhh'),
(2, 'gugghj', 'Cafe Coffee Day', 'cafe', 'fhhfyhhhh'),
(3, 'fhgchbhhb', 'Alimentos', 'restaurant', 'fhhfyhhhh'),
(4, 'fhghg', 'Cafe Coffee Day - Premchand Nagar Road', 'cafe', 'fhhfyhhhh');

-- --------------------------------------------------------

--
-- Table structure for table `community_data`
--

CREATE TABLE IF NOT EXISTS `community_data` (
  `admin` varchar(20) NOT NULL,
  `community_name` varchar(20) NOT NULL,
  `display` blob,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`community_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `community_data`
--


-- --------------------------------------------------------

--
-- Table structure for table `community_review`
--

CREATE TABLE IF NOT EXISTS `community_review` (
  `username` varchar(20) NOT NULL,
  `community_reviews` varchar(1000) DEFAULT NULL,
  `community_comments` varchar(300) DEFAULT NULL,
  `community_ratings` int(2) DEFAULT NULL,
  `place_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `community_review`
--

INSERT INTO `community_review` (`username`, `community_reviews`, `community_comments`, `community_ratings`, `place_name`) VALUES
('fhhfyhhhh', 'fugvjjj', NULL, NULL, 'Alimentos'),
('fhhfyhhhh', 'yuhgg', NULL, NULL, 'Alimentos');

-- --------------------------------------------------------

--
-- Table structure for table `contact_dictionary`
--

CREATE TABLE IF NOT EXISTS `contact_dictionary` (
  `username` varchar(20) NOT NULL,
  `contact_name` varchar(20) NOT NULL,
  `contact_detail_community` varchar(10) NOT NULL,
  PRIMARY KEY (`contact_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact_dictionary`
--


-- --------------------------------------------------------

--
-- Table structure for table `dining_details`
--

CREATE TABLE IF NOT EXISTS `dining_details` (
  `place_name` varchar(30) NOT NULL,
  `address` varchar(300) NOT NULL,
  `category` varchar(20) NOT NULL,
  `cost_person` int(6) DEFAULT NULL,
  `contact_rest` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`place_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dining_details`
--

INSERT INTO `dining_details` (`place_name`, `address`, `category`, `cost_person`, `contact_rest`) VALUES
('650-The Global Kitchen', 'Shreekunj Mandapam, Beside Golden Tulip Bunglows & Tulip Citadel, Manekbaug, Ambavadi, Ahmedabad', 'Dining', 400, '+919824090111'),
('70 Degrees East', 'E-201, Second Floor, Ozone Desire, 100ft, Hebatpur, Adjacent Columbia Asia Hospital, Hebatpur Road, Thaltej, Ahmedabad', 'Dining', 400, '+919913433397'),
('Barbeque Nation', '3, Shivalik III, Near Mile Stone Complex, Drive In Road, Gurukul, Ahmedabad', 'Dining', 375, '07930641647'),
('Nini Kitchen', '12, First Floor, Camps Corner 2, Opposite Prahlad Nagar Garden, Prahlad Nagar, Ahmedabad 380015', 'Dining', 325, '07930641734'),
('Patang', 'Chinubhai Tower, Nehru Bridge Corner, Ashram Road, Ahmedabad', 'Dining', 400, '07926586200'),
('Rajwadu', 'Near Jivraj Tolnaka, Ambaji Temple, Malav Talav, Vejalpur, Ahmedabad', 'Dining', 550, '07926643839'),
('Souq Bistro & Grills', 'Ground Floor, Acropolis Mall, S G Highway, Thaltej, Ahmedabad', 'Dining', 750, '07965101033');

-- --------------------------------------------------------

--
-- Table structure for table `dining_reviews`
--

CREATE TABLE IF NOT EXISTS `dining_reviews` (
  `place_name` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `user_reviews` varchar(1000) DEFAULT NULL,
  `user_ratings` float DEFAULT NULL,
  `user_comments` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dining_reviews`
--


-- --------------------------------------------------------

--
-- Table structure for table `forget_pass`
--

CREATE TABLE IF NOT EXISTS `forget_pass` (
  `random_id` double NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forget_pass`
--


-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE IF NOT EXISTS `friends` (
  `friend_id` double NOT NULL,
  `username` varchar(20) NOT NULL,
  `fcontact_details` varchar(10) NOT NULL,
  `num_freviews` int(10) DEFAULT NULL,
  `num_fcomments` int(10) DEFAULT NULL,
  `num_fratings` int(10) DEFAULT NULL,
  `categories` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--


-- --------------------------------------------------------

--
-- Table structure for table `group_participant`
--

CREATE TABLE IF NOT EXISTS `group_participant` (
  `group_id` double NOT NULL,
  `group_participants` varchar(20) NOT NULL,
  `category` varchar(20) NOT NULL,
  `place_name` varchar(20) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_participant`
--


-- --------------------------------------------------------

--
-- Table structure for table `group_reviews`
--

CREATE TABLE IF NOT EXISTS `group_reviews` (
  `group_id` double NOT NULL,
  `category` varchar(20) NOT NULL,
  `place_name` varchar(20) NOT NULL,
  `group_reviews` varchar(1000) DEFAULT NULL,
  `group_ratings` int(2) DEFAULT NULL,
  `group_comments` varchar(300) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_reviews`
--


-- --------------------------------------------------------

--
-- Table structure for table `hangout_details`
--

CREATE TABLE IF NOT EXISTS `hangout_details` (
  `place_name` varchar(20) NOT NULL,
  `address` varchar(300) NOT NULL,
  `category` varchar(20) NOT NULL,
  `cost_person` int(6) DEFAULT NULL,
  `contact_rest` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`place_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hangout_details`
--

INSERT INTO `hangout_details` (`place_name`, `address`, `category`, `cost_person`, `contact_rest`) VALUES
('CafeLevel', 'Opposite S K Farm, Behind Rajpath Club, S G Highway, Bodakdev, Ahmedabad', 'Hangout Place', 100, '+919898906143'),
('Jaggernaut Arena', '203, Sindhu Bhavan Marg, PRL Colony, Thaltej, Ahmedabad, Gujarat 380054', 'Hangout Place', 300, '07698004941'),
('JAVA+', 'Ramdev Nagar , Satellite Road, Courtyard, Ahmedabad, Gujarat 380015', 'Hangout Place', 150, '07966185000'),
('Project Cafe', 'Yellow Bunglow No. 7, Gujarat Siddharth Society,, Dr Vikram Sarabhai Marg,Gulbai Tekra, Ahmedabad, Gujarat 380009', 'Hangout Place', 350, '07940192287'),
('Shambhoo', 'Shop No. 1, 2 Devangan Apartments, H.L. Commerce Road, Navrangpura, Ahmedabad', 'Hangout Place', 100, '07965101111'),
('Sphere Lounge', 'Prahlad Nagar, Hotel Ramada, Corporate Road, Opposite Prahlad Nagar Garden, Prahlad Nagar, Ahmedabad', 'Hangout Place', 500, '+919033461923'),
('Zen Cafe', 'at the Gufa, University Road, near CEPT, Ahmedabad, Gujarat 380009', 'Hangout Place', 200, '07965459298');

-- --------------------------------------------------------

--
-- Table structure for table `hangout_review`
--

CREATE TABLE IF NOT EXISTS `hangout_review` (
  `place_name` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `user_reviews` varchar(1000) DEFAULT NULL,
  `user_ratings` float DEFAULT NULL,
  `user_comments` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hangout_review`
--


-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE IF NOT EXISTS `places` (
  `place_address` varchar(1000) DEFAULT NULL,
  `place_name` varchar(30) NOT NULL,
  PRIMARY KEY (`place_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `places`
--

INSERT INTO `places` (`place_address`, `place_name`) VALUES
('Shreekunj Mandapam, Beside Golden Tulip Bunglows & Tulip Citadel, Manekbaug, Ambavadi, Ahmedabad', '650-The Global Kitchen'),
('E-201, Second Floor, Ozone Desire, 100ft, Hebatpur, Adjacent Columbia Asia Hospital, Hebatpur Road, Thaltej, Ahmedabad', '70 Degrees East'),
('1st Floor, Sheetal Varsha Arcade, Near Girish Cold Drinks, Navrangpura, Ahmedabad 380009', 'Alimentos'),
('Mohini, Near Shraddha Petrol Pump, Judges Bungalow Road, Bodakdev, Ahmedabad', 'Atithi Restaurant'),
('Armoise Hotel, Opposite Nirman Bhavan, Behind Navarangpura Bus Stand, Off C G Road, Ahmedabad', 'Autograph - Armoise Hotel'),
('3, Shivalik III, Near Mile Stone Complex, Drive In Road, Gurukul, Ahmedabad', 'Barbeque Nation'),
('Dwarkesh Complex, Near Hotel Klassic Gold, Off C G Road, Navrangpura, Ahmedabad', 'Bawarchi Restaurant'),
('511, 3rd Floor, Himalaya Mall, Drive In Road, Ahmedabad - 380052, Near Indraprastha Tower', 'BIG CINEMAS'),
('Opposite S K Farm, Behind Rajpath Club, S G Highway, Bodakdev, Ahmedabad', 'CafeLevel'),
('4th Floor, Gulmohar Park, Satellite, AhmedabadAddress: Ramdev Nagar , Satellite Road, Courtyard, Ahmedabad, Gujarat 380015', 'Cellad Eatery'),
('Red Carpet Building, Sola, Ahmedabad - 380060, Opposite High Court', 'CINEMAX'),
('Ellis Bridge, Ashram Road, Ahmedabad, Gujarat 380009, India', 'CITYGOLD'),
('Drive In Road, Ahmedabad - 380052, Opposite Doordarsan Kendra', 'DRIVE IN'),
('203, Sindhu Bhavan Marg, PRL Colony, Thaltej, Ahmedabad, Gujarat 380054', 'Jaggernaut Arena'),
('Ramdev Nagar , Satellite Road, Courtyard, Ahmedabad, Gujarat 380015', 'JAVA+'),
('JB Tower, Opposite Doordarshan Kendra, Drive In Road, Gurukul, Ahmedabad', 'Kabir Restaurant'),
('4th Floor,Gulmohar Park Mall, Ramdevnagar Satellite Road, Ahmedabad, Gujarat 380015, India', 'MUKTA ARTS'),
('12, First Floor, Camps Corner 2, Opposite Prahlad Nagar Garden, Prahlad Nagar, Ahmedabad 380015', 'Nini Kitchen'),
('Chinubhai Tower, Nehru Bridge Corner, Ashram Road, Ahmedabad', 'Patang'),
('Yellow Bunglow No. 7, Gujarat Siddharth Society,, Dr Vikram Sarabhai Marg,Gulbai Tekra, Ahmedabad, Gujarat 380009', 'Project Cafe'),
('PVR Acropolis Mall, Thaltej Cross Road, Near Gurdwara, Ahmedabad', 'PVR CINEMAS'),
('Near Jivraj Tolnaka, Ambaji Temple, Malav Talav, Vejalpur, Ahmedabad', 'Rajwadu'),
('Sankalp Square, Drive In Road, Gurukul, Ahmedabad', 'Saffron Restaurant'),
('Shop No. 1, 2 Devangan Apartments, H.L. Commerce Road, Navrangpura, Ahmedabad', 'Shambhoo'),
('Ground Floor, Acropolis Mall, S G Highway, Thaltej, Ahmedabad', 'Souq Bistro & Grills'),
('Prahlad Nagar, Hotel Ramada, Corporate Road, Opposite Prahlad Nagar Garden, Prahlad Nagar, Ahmedabad', 'Sphere Lounge'),
('Satellite Crossing, Sarkhej - Gandhinagar Highway, Ahmedabad, Gujarat 380015, India', 'WIDE ANGLE'),
('at the Gufa, University Road, near CEPT, Ahmedabad, Gujarat 380009', 'Zen Cafe');

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

CREATE TABLE IF NOT EXISTS `ratings` (
  `id` double NOT NULL AUTO_INCREMENT,
  `ratings` float DEFAULT NULL,
  `place_name` varchar(100) NOT NULL,
  `category` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ratings`
--

INSERT INTO `ratings` (`id`, `ratings`, `place_name`, `category`) VALUES
(1, 3.5, 'Alimentos', 'restaurant');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_details`
--

CREATE TABLE IF NOT EXISTS `restaurant_details` (
  `place_name` varchar(30) NOT NULL,
  `address` varchar(300) NOT NULL,
  `category` varchar(20) NOT NULL,
  `cost_person` int(6) DEFAULT NULL,
  `contact_rest` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`place_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurant_details`
--

INSERT INTO `restaurant_details` (`place_name`, `address`, `category`, `cost_person`, `contact_rest`) VALUES
('Alimentos', '1st Floor, Sheetal Varsha Arcade, Near Girish Cold Drinks, Navrangpura, Ahmedabad 380009', 'Restaurant', 375, '07965222440'),
('Atithi Restaurant', 'Mohini, Near Shraddha Petrol Pump, Judges Bungalow Road, Bodakdev, Ahmedabad', 'Restaurant', 250, '07926858806'),
('Autograph - Armoise Hotel', 'Armoise Hotel, Opposite Nirman Bhavan, Behind Navarangpura Bus Stand, Off C G Road, Ahmedabad', 'Restaurant', 400, '07930641900'),
('Bawarchi Restaurant', 'Dwarkesh Complex, Near Hotel Klassic Gold, Off C G Road, Navrangpura, Ahmedabad', 'Restaurant', 450, '07926565970'),
('Cellad Eatery', '4th Floor, Gulmohar Park, Satellite, AhmedabadAddress: Ramdev Nagar , Satellite Road, Courtyard, Ahmedabad, Gujarat 380015', 'Restaurant', 350, '+919974405858'),
('Kabir Restaurant', 'JB Tower, Opposite Doordarshan Kendra, Drive In Road, Gurukul, Ahmedabad', 'Restaurant', 300, '07926852786'),
('Saffron Restaurant', 'Sankalp Square, Drive In Road, Gurukul, Ahmedabad', 'Restaurant', 400, '07927462999');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_review`
--

CREATE TABLE IF NOT EXISTS `restaurant_review` (
  `place_name` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `user_reviews` varchar(1000) DEFAULT NULL,
  `user_ratings` float DEFAULT NULL,
  `user_comments` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurant_review`
--

INSERT INTO `restaurant_review` (`place_name`, `username`, `user_reviews`, `user_ratings`, `user_comments`) VALUES
('Alimentos', 'fhhfyhhhh', 'vfhgvggvh', 3.5, 'fhgchbhhb');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `id` double NOT NULL AUTO_INCREMENT,
  `reviews` varchar(1000) DEFAULT NULL,
  `place_name` varchar(100) NOT NULL,
  `category` varchar(20) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `reviews`, `place_name`, `category`, `username`) VALUES
(1, 'vfhgvggvh', 'Alimentos', 'restaurant', 'fhhfyhhhh'),
(2, 'dyfg', 'Cafe Coffee Day - Premchand Nagar Road', 'cafe', 'fhhfyhhhh');

-- --------------------------------------------------------

--
-- Table structure for table `theatre_details`
--

CREATE TABLE IF NOT EXISTS `theatre_details` (
  `place_name` varchar(20) NOT NULL,
  `address` varchar(300) NOT NULL,
  `category` varchar(20) NOT NULL,
  `cost_person` int(6) DEFAULT NULL,
  `contact_rest` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`place_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `theatre_details`
--

INSERT INTO `theatre_details` (`place_name`, `address`, `category`, `cost_person`, `contact_rest`) VALUES
('BIG CINEMAS', '511, 3rd Floor, Himalaya Mall, Drive In Road, Ahmedabad - 380052, Near Indraprastha Tower', 'Theatres', 150, '+917933014218'),
('CINEMAX', 'Red Carpet Building, Sola, Ahmedabad - 380060, Opposite High Court', 'Theatres', 100, '+917965495182'),
('CITYGOLD', 'Ellis Bridge, Ashram Road, Ahmedabad, Gujarat 380009, India', 'Theatres', 100, '07926587780'),
('DRIVE IN', 'Drive In Road, Ahmedabad - 380052, Opposite Doordarsan Kendra', 'Theatres', 50, '+917927454600'),
('MUKTA ARTS', '4th Floor,Gulmohar Park Mall, Ramdevnagar Satellite Road, Ahmedabad, Gujarat 380015, India', 'Theatres', 90, '07930464500'),
('PVR CINEMAS', 'PVR Acropolis Mall, Thaltej Cross Road, Near Gurdwara, Ahmedabad', 'Theatres', 180, '+919601895182'),
('WIDE ANGLE', 'Satellite Crossing, Sarkhej - Gandhinagar Highway, Ahmedabad, Gujarat 380015, India', 'Theatres', 100, '+917926929721');

-- --------------------------------------------------------

--
-- Table structure for table `theatre_review`
--

CREATE TABLE IF NOT EXISTS `theatre_review` (
  `place_name` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `user_reviews` varchar(1000) DEFAULT NULL,
  `user_ratings` float DEFAULT NULL,
  `user_comments` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `theatre_review`
--

INSERT INTO `theatre_review` (`place_name`, `username`, `user_reviews`, `user_ratings`, `user_comments`) VALUES
('CITYGOLD', 'fhhfyhhhh', 'vgfhj', 2, 'bfyhb');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `friend_id` double NOT NULL,
  `username` varchar(20) NOT NULL,
  `contact_details` varchar(10) NOT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_suggestion`
--

CREATE TABLE IF NOT EXISTS `user_suggestion` (
  `username` varchar(20) NOT NULL,
  `recipe_pic` blob NOT NULL,
  `food_snap` blob NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_suggestion`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE IF NOT EXISTS `user_table` (
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `contact_details` varchar(10) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `user_display` blob,
  `id` double NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`username`, `password`, `name`, `address`, `contact_details`, `email`, `user_display`, `id`) VALUES
('dhsjdbhddjdjhf', 'udbddjekdjhjr', 'hdhdhdbdhd', 'vssuhsgsshshdg', '9427804902', 'grejhdbdhdhd', NULL, 1),
('fhhfyhhhh', 'ghjgguuhh', 'vhhgyyhf', 'fdghghuj', '9427804905', 'uyhjjg', NULL, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
