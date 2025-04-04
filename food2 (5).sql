-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Aug 27, 2024 at 08:21 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `food2`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_category` (IN `cname` VARCHAR(50))   BEGIN
	INSERT INTO category (category_name) VALUES (cname) ;   
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_menuitem` (IN `mrestaurant_id` INT, IN `mitem_name` VARCHAR(40), IN `mprice` DOUBLE, IN `mcategory_id` INT, IN `mrating` INT)   BEGIN
	INSERT INTO MenuItem (restaurant_id, item_name, price,
    category_id, rating) VALUES (mrestaurant_id, mitem_name, mprice,
    mcategory_id, mrating); 
   END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_restaurant` (IN `rname` VARCHAR(50), IN `raddress` VARCHAR(100), IN `rphone` VARCHAR(11), IN `rrating` INT)   BEGIN 
	INSERT INTO restaurant(name, address, phone_no, rating) VALUES 
    (rname,raddress, rphone, rrating);
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `chack_admin` (IN `ausername` VARCHAR(30), IN `apassword` VARCHAR(30), OUT `aadmin_id` INT)   BEGIN 
	SELECT admin_id into aadmin_id FROM admin WHERE username=ausername and password =apassword;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `check_customer` (IN `cemail` VARCHAR(50), IN `cpassword` VARCHAR(8), OUT `cid` INT, OUT `cname` VARCHAR(50))   BEGIN

	SELECT customer_id , name INTO cid,cname FROM customer WHERE 
    email =cemail and password =cpassword LIMIT 1;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_new_customer` (IN `cname` VARCHAR(50), IN `cemail` VARCHAR(30), IN `cpassword` VARCHAR(10), IN `cphone` VARCHAR(12), IN `caddress` VARCHAR(100))   BEGIN 
	
	INSERT INTO Customer (name, email, phone, address, password) VALUES 
    (cname,cemail,cphone,caddress,cpassword);
    END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `feedback_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `restaurant_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `username`, `password`, `category_id`, `customer_id`, `feedback_id`, `menu_id`, `order_id`, `payment_id`, `restaurant_id`) VALUES
(1, 'admin1', 'pass123@', 1, 1, 1, 1, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'Beverages'),
(2, 'Appetizers'),
(3, 'Main Course'),
(4, 'Desserts'),
(5, 'Salads'),
(6, 'Soups'),
(7, 'Pasta'),
(8, 'Pizza'),
(9, 'Seafood'),
(10, 'Vegan'),
(21, 'vadapav');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `name`, `email`, `phone`, `address`, `password`) VALUES
(1, 'a', 'abcde@gmail.com', '1234123412', 'aers', '1234123@'),
(2, 'Jane Smith', 'jane@gmail.com', '9876543211', '2nd Street, Ahmedabad', 'jane@123'),
(3, 'Jim Brown', 'jim@gmail.com', '9876543212', '3rd Street, Ahmedabad', 'jim@123'),
(4, 'Jenny White', 'jenny@gmail.com', '9876543213', '4th Street, Ahmedabad', 'jenny@123'),
(5, 'Jack Black', 'jack@gmail.com', '9876543214', '5th Street, Ahmedabad', 'jack@123'),
(6, 'jay', 'jayas@gmail.com', '1234512345', 'adarsh', '1234567@'),
(7, 'jay', 'asdfg@gmail.com', '1234567812', 'adarsh', '1234hat@'),
(8, 'abc', 'abcde@gmail.com', '1234567891', 'adarsh', '1234567@'),
(9, 'jay', 'jay10@gmail.com', '1234567890', 'adarsh', 'jay1234@'),
(13, 'ja', 'nkkkkj@gmail.com', 'aa', '1234123@', '1234123412'),
(14, 'sssdsdss', 'sdssds@gmail.com', 'qeq', '1234!@#w', '1234123412'),
(15, 'ajka', 'sdkakj@gmail.com', 'ahhwd', '1234123@', '1324132413'),
(16, 'bhavya', 'bhavya@gmail.com', 'abc', '1234123@', '1234123412'),
(17, 'abbcd', 'abcderr@gmail.com', 'adarsh', '1234123@', '1234123412'),
(18, 'jaay', 'aaklas@gmail.com', 'qqqqqqq', '1234123@', '1212121212'),
(19, 'qqqq', 'qqqqqq@gmail.com', 'adarsh', '1234132@', '1234123412'),
(20, 'wwwww', 'wwwwww@gmail.com', '12hahs', '1234123@', '1234123412'),
(21, 'jjajja', 'hgjqjj@gmail.com', '1gjjaj', '1234123@', '1234123412'),
(22, 'ksksd', 'jkadk@gmail.com', 'ADSRHLJQ', '1234123@', '1234123412'),
(23, 'jshdjks', 'hhhhh@gmail.com', 'whfef', '1231231@', '1234123412'),
(24, 'falguni', 'qwera@gmail.com', 'gaxs', '1234123@', '1234123412');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT 3
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `customer_id`, `restaurant_id`, `rating`) VALUES
(1, 1, 1, 5),
(2, 2, 2, 4),
(3, 3, 3, 5),
(4, 4, 4, 3),
(5, 5, 1, 4),
(6, 7, 2, 3),
(7, 1, 2, 3),
(8, 1, 1, 4),
(9, 9, 2, 4),
(10, 9, 2, 4),
(11, 9, 2, 5),
(12, 9, 3, 4),
(13, 9, 3, 4),
(14, 9, 2, 5),
(15, 9, 2, 4),
(16, 9, 2, 4),
(17, 9, 2, 3),
(18, 9, 2, 3),
(19, 0, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `menuitem`
--

CREATE TABLE `menuitem` (
  `item_id` int(11) NOT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `item_name` varchar(30) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menuitem`
--

INSERT INTO `menuitem` (`item_id`, `restaurant_id`, `item_name`, `price`, `category_id`, `rating`) VALUES
(1, 1, 'Classic Mojito', 120.00, 1, 4),
(2, 1, 'Herbal Tea', 80.00, 1, 5),
(3, 1, 'Fruit Punch', 90.00, 1, 4),
(4, 1, 'Cheese Platter', 200.00, 1, 5),
(5, 1, 'Garlic Bread', 100.00, 1, 4),
(6, 1, 'Stuffed Mushrooms', 150.00, 1, 5),
(7, 1, 'Beef Steak', 400.00, 1, 5),
(8, 1, 'Vegetable Stir Fry', 300.00, 1, 4),
(9, 1, 'Spaghetti Bolognese', 350.00, 1, 5),
(10, 1, 'Chocolate Cake', 180.00, 1, 4),
(11, 2, 'Crab Cakes', 250.00, 2, 5),
(12, 2, 'Stuffed Jalapeños', 120.00, 2, 4),
(13, 2, 'Vegetable Spring Rolls', 100.00, 2, 5),
(14, 2, 'Garlic Butter Shrimp', 200.00, 2, 4),
(15, 2, 'Spicy Chicken Wings', 150.00, 2, 5),
(16, 2, 'Cheesy Nachos', 130.00, 2, 4),
(17, 2, 'Buffalo Cauliflower', 160.00, 2, 5),
(18, 2, 'Stuffed Artichokes', 180.00, 2, 4),
(19, 2, 'Bruschetta', 140.00, 2, 5),
(20, 2, 'Hummus and Pita', 110.00, 2, 4),
(21, 3, 'Lamb Chops', 400.00, 3, 5),
(22, 3, 'Chicken Parmesan', 350.00, 3, 4),
(23, 3, 'Beef Stroganoff', 380.00, 3, 5),
(24, 3, 'Vegetarian Lasagna', 320.00, 3, 4),
(25, 3, 'Seafood Paella', 450.00, 3, 5),
(26, 3, 'Grilled Salmon', 370.00, 3, 4),
(27, 3, 'Pork Ribs', 390.00, 3, 5),
(28, 3, 'Eggplant Parmesan', 330.00, 3, 4),
(29, 3, 'Braised Short Ribs', 420.00, 3, 5),
(30, 3, 'Chicken Alfredo', 340.00, 3, 4),
(31, 4, 'Cheesecake', 220.00, 4, 5),
(32, 4, 'Apple Pie', 200.00, 4, 4),
(33, 4, 'Chocolate Mousse', 180.00, 4, 5),
(34, 4, 'Fruit Tart', 150.00, 4, 4),
(35, 4, 'Tiramisu', 210.00, 4, 5),
(36, 4, 'Crème Brûlée', 240.00, 4, 4),
(37, 4, 'Panna Cotta', 190.00, 4, 5),
(38, 4, 'Lemon Bars', 160.00, 4, 4),
(39, 4, 'Raspberry Sorbet', 170.00, 4, 5),
(40, 4, 'Chocolate Brownie', 180.00, 4, 4),
(41, 5, 'Greek Salad', 130.00, 5, 4),
(42, 5, 'Caesar Salad', 140.00, 5, 5),
(43, 5, 'Caprese Salad', 160.00, 5, 4),
(44, 5, 'Quinoa Salad', 150.00, 5, 5),
(45, 5, 'Cobb Salad', 170.00, 5, 4),
(46, 5, 'Asian Sesame Salad', 140.00, 5, 5),
(47, 5, 'Waldorf Salad', 160.00, 5, 4),
(48, 5, 'Kale Salad', 130.00, 5, 5),
(49, 5, 'Spinach Salad', 120.00, 5, 4),
(50, 5, 'Chickpea Salad', 140.00, 5, 5),
(51, 6, 'Tomato Basil Soup', 110.00, 6, 4),
(52, 6, 'Chicken Noodle Soup', 150.00, 6, 5),
(53, 6, 'Minestrone', 130.00, 6, 4),
(54, 6, 'Clam Chowder', 180.00, 6, 5),
(55, 6, 'Lentil Soup', 120.00, 6, 4),
(56, 6, 'Pumpkin Soup', 140.00, 6, 5),
(57, 6, 'French Onion Soup', 150.00, 6, 4),
(58, 6, 'Butternut Squash Soup', 160.00, 6, 5),
(59, 6, 'Beef Barley Soup', 170.00, 6, 4),
(60, 6, 'Spicy Black Bean Soup', 140.00, 6, 5),
(61, 7, 'Penne Alfredo', 330.00, 7, 5),
(62, 7, 'Fettuccine Carbonara', 340.00, 7, 4),
(63, 7, 'Lasagna', 360.00, 7, 5),
(64, 7, 'Spaghetti Bolognese', 320.00, 7, 4),
(65, 7, 'Mac and Cheese', 300.00, 7, 5),
(66, 7, 'Ravioli', 340.00, 7, 4),
(67, 7, 'Pesto Pasta', 330.00, 7, 5),
(68, 7, 'Linguine with Clam Sauce', 350.00, 7, 4),
(69, 7, 'Pappardelle', 360.00, 7, 5),
(70, 7, 'Tortellini', 340.00, 7, 4),
(71, 8, 'Margherita Pizza', 300.00, 8, 4),
(72, 8, 'Pepperoni Pizza', 320.00, 8, 5),
(73, 8, 'BBQ Chicken Pizza', 340.00, 8, 4),
(74, 8, 'Vegetarian Pizza', 310.00, 8, 5),
(75, 8, 'Hawaiian Pizza', 330.00, 8, 4),
(76, 8, 'Four Cheese Pizza', 340.00, 8, 5),
(77, 8, 'Meat Lover\'s Pizza', 360.00, 8, 4),
(78, 8, 'Buffalo Chicken Pizza', 350.00, 8, 5),
(79, 8, 'Pesto Pizza', 320.00, 8, 4),
(80, 8, 'White Pizza', 310.00, 8, 5),
(81, 9, 'Grilled Salmon', 450.00, 9, 5),
(82, 9, 'Shrimp Scampi', 420.00, 9, 4),
(83, 9, 'Crab Cakes', 380.00, 9, 5),
(84, 9, 'Lobster Roll', 500.00, 9, 4),
(85, 9, 'Clam Chowder', 200.00, 9, 5),
(86, 9, 'Oysters', 220.00, 9, 4),
(87, 9, 'Seafood Paella', 470.00, 9, 5),
(88, 9, 'Tuna Tartare', 300.00, 9, 4),
(89, 9, 'Grilled Shrimp', 350.00, 9, 5),
(90, 9, 'Fish Tacos', 250.00, 9, 4),
(91, 10, 'Vegan Burger', 250.00, 10, 4),
(92, 10, 'Quinoa Salad', 220.00, 10, 5),
(93, 10, 'Avocado Toast', 200.00, 10, 4),
(94, 10, 'Chia Pudding', 180.00, 10, 5),
(95, 10, 'Lentil Soup', 160.00, 10, 4),
(96, 10, 'Sweet Potato Fries', 150.00, 10, 5),
(97, 10, 'Stuffed Bell Peppers', 240.00, 10, 4),
(98, 10, 'Vegan Burrito', 260.00, 10, 5),
(99, 10, 'Tofu Stir Fry', 220.00, 10, 4),
(100, 10, '100', 30.00, 10, 3);

--
-- Triggers `menuitem`
--
DELIMITER $$
CREATE TRIGGER `backup_menuitem` BEFORE DELETE ON `menuitem` FOR EACH ROW BEGIN 
	
    INSERT INTO menuitem_backup  (item_id,restaurant_id,item_name,price,category_id,rating) VALUES(old.item_id,old.restaurant_id,old.item_name,old.price,
           old.category_id,old.rating);
           
    DELETE FROM temp WHERE item_id=old.item_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `menuitem_backup`
--

CREATE TABLE `menuitem_backup` (
  `item_id` int(11) NOT NULL,
  `restaurant_id` int(11) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `category_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menuitem_backup`
--

INSERT INTO `menuitem_backup` (`item_id`, `restaurant_id`, `item_name`, `price`, `category_id`, `rating`) VALUES
(105, 102, 'pavbhaji', 150, 7, 4),
(103, 102, 'jangli vadapav', 30, 21, 4),
(104, 102, 'BBQ vadapav', 50, 21, 5),
(106, 96, 'Vegan', 40, 10, 4);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `item_name` varchar(30) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `order_date` date DEFAULT current_timestamp(),
  `overall_total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `item_name`, `quantity`, `total_price`, `order_date`, `overall_total`) VALUES
(1, 1, 'Butter Chicken', 2, 500.00, '2024-08-15', 0),
(2, 2, 'Spring Rolls', 3, 450.00, '2024-08-15', 0),
(3, 3, 'Spaghetti Carbonara', 1, 350.00, '2024-08-16', 0),
(4, 4, 'Kung Pao Chicken', 2, 600.00, '2024-08-16', 0),
(5, 5, 'Paneer Butter Masala', 1, 200.00, '2024-08-17', 0),
(6, 7, NULL, NULL, 0.00, '2024-08-19', 0),
(7, 1, NULL, NULL, 0.00, '2024-08-19', 0),
(9, 1, 'Butter Chicken', 0, 0.00, '2024-08-19', 0),
(10, 9, 'Herbal Tea', 2, 82.00, '2024-08-19', 798),
(11, 9, 'Cheese Platter', 4, 204.00, '2024-08-19', 798),
(12, 9, 'Garlic Bread', 5, 105.00, '2024-08-19', 798),
(13, 9, 'Beef Steak', 7, 407.00, '2024-08-19', 798),
(14, 9, 'Herbal Tea', 2, 82.00, '2024-08-21', 379),
(15, 9, 'Fruit Punch', 3, 93.00, '2024-08-21', 379),
(16, 9, 'Cheese Platter', 4, 204.00, '2024-08-21', 379),
(17, 9, 'Herbal Tea', 2, 82.00, '2024-08-21', 379),
(18, 9, 'Fruit Punch', 3, 93.00, '2024-08-21', 379),
(19, 9, 'Cheese Platter', 4, 204.00, '2024-08-21', 379),
(28, 9, 'Herbal Tea', 2, 82.00, '2024-08-21', 175),
(29, 9, 'Fruit Punch', 3, 93.00, '2024-08-21', 175),
(32, 12, 'Herbal Tea', 2, 82.00, '2024-08-22', 379),
(33, 12, 'Fruit Punch', 3, 93.00, '2024-08-22', 379),
(34, 12, 'Cheese Platter', 4, 204.00, '2024-08-22', 379),
(35, 9, 'Cheese Platter', 2, 202.00, '2024-08-22', 404),
(36, 9, 'Cheese Platter', 2, 202.00, '2024-08-22', 404),
(37, 9, 'Fruit Punch', 4, 94.00, '2024-08-22', 177),
(38, 9, 'Herbal Tea', 3, 83.00, '2024-08-22', 177),
(39, 9, 'Fruit Punch', 4, 94.00, '2024-08-22', 186),
(40, 9, 'Fruit Punch', 2, 92.00, '2024-08-22', 186),
(41, 9, 'Cheese Platter', 5, 205.00, '2024-08-22', 297),
(42, 9, 'Fruit Punch', 2, 92.00, '2024-08-22', 297),
(43, 9, 'Fruit Punch', 4, 94.00, '2024-08-22', 186),
(44, 9, 'Fruit Punch', 2, 92.00, '2024-08-22', 186),
(45, 9, 'Cheese Platter', 5, 205.00, '2024-08-23', 290),
(46, 9, 'Herbal Tea', 5, 85.00, '2024-08-23', 290),
(47, 0, 'Cheese Platter', 5, 205.00, '2024-08-24', 308),
(48, 0, 'Garlic Bread', 3, 103.00, '2024-08-24', 308),
(49, 9, 'Grilled Salmon', 3, 373.00, '2024-08-24', 373),
(50, 9, 'Classic Mojito', 5, 125.00, '2024-08-24', 125);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `payment_type` varchar(30) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `payment_type`, `customer_id`, `total_price`, `payment_date`) VALUES
(9, 'UPI', 9, 798.00, '2024-08-19 17:19:28'),
(10, 'UPI', 9, 286.00, '2024-08-20 16:07:18'),
(11, 'Credit Card', 9, 379.00, '2024-08-20 16:13:36'),
(13, 'UPI', 9, 482.00, '2024-08-21 16:11:17'),
(15, 'Cash', 9, 379.00, '2024-08-21 16:20:08'),
(17, 'Cash', 9, 379.00, '2024-08-21 16:31:53'),
(18, 'Cash', 9, 94.00, '2024-08-21 16:38:37'),
(19, 'Cash', 0, 296.00, '2024-08-21 16:43:14'),
(20, 'Cash', 9, 175.00, '2024-08-21 16:47:38'),
(21, 'Cash', 0, 180.00, '2024-08-22 14:06:36'),
(22, 'Cash', 12, 379.00, '2024-08-22 14:31:04'),
(23, 'Cash', 9, 404.00, '2024-08-22 14:41:09'),
(24, 'Cash', 9, 177.00, '2024-08-22 15:39:25'),
(25, 'Cash', 9, 186.00, '2024-08-22 17:19:08'),
(26, 'Cash', 9, 297.00, '2024-08-22 17:25:47'),
(27, 'Cash', 9, 186.00, '2024-08-22 17:37:41'),
(28, 'Cash', 9, 290.00, '2024-08-23 05:03:07'),
(29, 'Cash', 0, 308.00, '2024-08-24 01:47:25'),
(30, 'Cash', 9, 373.00, '2024-08-24 05:48:35'),
(31, 'Cash', 9, 125.00, '2024-08-24 06:46:46');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `restaurant_id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone_no` varchar(10) DEFAULT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`restaurant_id`, `name`, `address`, `phone_no`, `rating`) VALUES
(1, 'The Gourmet Grill', '101 Pine Hill Road, Ahmedabad', '9001002003', 4),
(2, 'Appetizer Haven', '102 Oakwood Drive, Ahmedabad', '9001002004', 5),
(3, 'The Grand Course', '103 Maple Grove, Ahmedabad', '9001002005', 5),
(4, 'Dessert Dreams', '104 Elm Court, Ahmedabad', '9001002006', 4),
(5, 'Salad Symphony', '105 Cedar Avenue, Ahmedabad', '9001002007', 3),
(6, 'Soup Delight', '106 Willow Street, Ahmedabad', '9001002008', 4),
(7, 'Pasta Paradise', '107 Birch Lane, Ahmedabad', '9001002009', 5),
(8, 'Pizza Palace', '108 Walnut Street, Ahmedabad', '9001002010', 4),
(9, 'Seafood Sensation', '109 Seaside Road, Ahmedabad', '9001002011', 5),
(10, 'Vegan Ventures', '110 Horizon Avenue, Ahmedabad', '9001002012', 4),
(11, 'Beverage Boulevard', '11 Mango Lane, Ahmedabad', '9898989898', 4),
(12, 'Appetizer Avenue', '22 Pine Avenue, Ahmedabad', '8787878787', 5),
(13, 'Main Course Mansion', '33 Cedar Street, Ahmedabad', '7676767676', 4),
(14, 'Dessert Den', '44 Maple Grove, Ahmedabad', '6565656565', 5),
(15, 'Salad Station', '55 Elm Plaza, Ahmedabad', '5454545454', 3),
(16, 'Soup Sanctuary', '66 Oak Boulevard, Ahmedabad', '4343434343', 4),
(17, 'Pasta Palace', '77 Birch Avenue, Ahmedabad', '3232323232', 4),
(18, 'Pizza Plaza', '88 Willow Lane, Ahmedabad', '2121212121', 5),
(19, 'Seafood Spot', '99 Horizon Road, Ahmedabad', '1010101010', 4),
(20, 'Vegan Valley', '100 Green Park, Ahmedabad', '9090909090', 3),
(21, 'Fresh Brews', '210 Pine Street, Ahmedabad', '9922334455', 4),
(22, 'Starter Station', '220 Oak Street, Ahmedabad', '9933445566', 3),
(23, 'Main Course Magic', '230 Maple Avenue, Ahmedabad', '9944556677', 5),
(24, 'Sweet Tooth', '240 Cedar Lane, Ahmedabad', '9955667788', 4),
(25, 'Healthy Greens', '250 Willow Road, Ahmedabad', '9966778899', 5),
(26, 'Soup Symphony', '260 Birch Street, Ahmedabad', '9977889900', 3),
(27, 'Pasta Palace', '270 Elm Avenue, Ahmedabad', '9988990011', 4),
(28, 'Pizza Planet', '280 Walnut Street, Ahmedabad', '9999001122', 5),
(29, 'Seafood Harbor', '290 Horizon Road, Ahmedabad', '9112233445', 4),
(30, 'Vegan Vibes', '300 Green Park, Ahmedabad', '9223344556', 5),
(31, 'Café Aroma', '310 Lavender Lane, Ahmedabad', '9123105678', 4),
(32, 'Tapas Treats', '320 Olive Street, Ahmedabad', '9231206789', 5),
(33, 'Royal Feast', '330 Cedar Boulevard, Ahmedabad', '9342307890', 5),
(34, 'Sweet Cravings', '340 Rosewood Avenue, Ahmedabad', '9453408901', 4),
(35, 'Green Garden', '350 Pinecrest Drive, Ahmedabad', '9564509012', 3),
(36, 'Soup Symphony', '360 Oak Ridge, Ahmedabad', '9675600123', 4),
(37, 'Pasta Fiesta', '370 Maple Leaf, Ahmedabad', '9786701234', 5),
(38, 'Slice of Heaven', '380 Birch Road, Ahmedabad', '9897802345', 4),
(39, 'Ocean Delights', '390 Seaside Avenue, Ahmedabad', '9908903456', 5),
(40, 'Vegan Bites', '400 Greenway, Ahmedabad', '9019004567', 4),
(41, 'Sip & Relax', '41 Pine Hill Road, Ahmedabad', '9191919191', 4),
(42, 'Snack Attack', '42 Oakwood Drive, Ahmedabad', '9292929292', 5),
(43, 'The Main Course', '43 Maple Grove, Ahmedabad', '9393939393', 5),
(44, 'Sweet Tooth', '44 Elm Court, Ahmedabad', '9494949494', 4),
(45, 'Green Garden', '45 Cedar Avenue, Ahmedabad', '9595959595', 3),
(46, 'Soup Station', '46 Willow Street, Ahmedabad', '9696969696', 4),
(47, 'Pasta Palace', '47 Birch Lane, Ahmedabad', '9797979797', 5),
(48, 'Pizza Hub', '48 Walnut Street, Ahmedabad', '9898989898', 4),
(49, 'Ocean Delights', '49 Seaside Road, Ahmedabad', '9999999999', 5),
(50, 'Vegan Vibes', '50 Horizon Avenue, Ahmedabad', '9090909090', 4),
(51, 'Caffeine Fix', '51 Sunrise Boulevard, Ahmedabad', '9182736455', 4),
(52, 'Quick Bites', '52 Sunset Park, Ahmedabad', '9273648512', 5),
(53, 'Main Course Express', '53 Evergreen Drive, Ahmedabad', '9364758291', 5),
(54, 'Dessert Haven', '54 Blossom Lane, Ahmedabad', '9456839201', 4),
(55, 'Salad Stop', '55 Harvest Street, Ahmedabad', '9547283910', 3),
(56, 'Hot & Sour', '56 Harmony Road, Ahmedabad', '9638945721', 4),
(57, 'Pasta Paradise', '57 Galaxy Avenue, Ahmedabad', '9729456803', 5),
(58, 'Pizza Place', '58 Vista Drive, Ahmedabad', '9812345679', 4),
(59, 'Seafood Sensation', '59 Coastal Road, Ahmedabad', '9908765432', 5),
(60, 'Vegan Delight', '60 Urban Heights, Ahmedabad', '9098765432', 4),
(61, 'Chill & Sip', '61 Lagoon Lane, Ahmedabad', '9111122334', 4),
(62, 'The Nibble Nook', '62 Orchard Drive, Ahmedabad', '9222233445', 5),
(63, 'Main Course Masters', '63 Brookside Avenue, Ahmedabad', '9333344556', 5),
(64, 'Dessert Delight', '64 Crescent Court, Ahmedabad', '9444455667', 4),
(65, 'Fresh Greens', '65 Forest Road, Ahmedabad', '9555566778', 3),
(66, 'Soup & Stew', '66 Prairie Street, Ahmedabad', '9666677889', 4),
(67, 'Pasta Paradise', '67 Garden Way, Ahmedabad', '9777788990', 5),
(68, 'Pizza Paradise', '68 Pine Hill Road, Ahmedabad', '9888899001', 4),
(69, 'Seafood Sensation', '69 Coastal Drive, Ahmedabad', '9999001122', 5),
(70, 'Vegan Corner', '70 Greenway Avenue, Ahmedabad', '9000112233', 4),
(71, 'Beverage Haven', '71 River View Lane, Ahmedabad', '9112233445', 4),
(72, 'Appetizer Alley', '72 Elm Street, Ahmedabad', '9223344556', 5),
(73, 'Main Course Magic', '73 Maple Avenue, Ahmedabad', '9334455667', 5),
(74, 'Dessert Delight', '74 Oakwood Drive, Ahmedabad', '9445566778', 4),
(75, 'Salad Station', '75 Willow Road, Ahmedabad', '9556677889', 3),
(76, 'Soup Stop', '76 Birch Lane, Ahmedabad', '9667788990', 4),
(77, 'Pasta Paradise', '77 Cedar Avenue, Ahmedabad', '9778899001', 5),
(78, 'Pizza Point', '78 Horizon Road, Ahmedabad', '9889900112', 4),
(79, 'Seafood Sensation', '79 Ocean Drive, Ahmedabad', '9990011223', 5),
(80, 'Vegan Corner', '80 Green Park, Ahmedabad', '9001122334', 4),
(81, 'Beverage Haven', '81 Sunflower Street, Ahmedabad', '9111111111', 4),
(82, 'The Appetizer Spot', '82 Maple Leaf Lane, Ahmedabad', '9222222222', 5),
(83, 'Grand Main Course', '83 Pine Cone Road, Ahmedabad', '9333333333', 4),
(84, 'Dessert Dreams', '84 Oak Tree Avenue, Ahmedabad', '9444444444', 5),
(85, 'Salad Symphony', '85 Cedar Grove, Ahmedabad', '9555555555', 3),
(86, 'Soup & Soul', '86 Willow Bend, Ahmedabad', '9666666666', 4),
(87, 'Pasta Paradise', '87 Birchwood Lane, Ahmedabad', '9777777777', 5),
(88, 'Pizza Place', '88 Walnut Park, Ahmedabad', '9888888888', 4),
(89, 'Seafood Sensations', '89 Seaside Drive, Ahmedabad', '9998887777', 5),
(90, 'Vegan Feast', '90 Horizon Hill, Ahmedabad', '9000000000', 4),
(91, 'Cafe Mocha', '91 Riverbank Road, Ahmedabad', '9112345678', 4),
(92, 'Crunchy Bites', '92 Maple Avenue, Ahmedabad', '9223456789', 5),
(93, 'Delicious Dishes', '93 Park Lane, Ahmedabad', '9334567890', 4),
(94, 'Sugar Rush', '94 Elm Street, Ahmedabad', '9445678901', 5),
(95, 'Fresh Greens', '95 Willow Lane, Ahmedabad', '9556789012', 3),
(96, 'a', 'b', '1234567890', 4),
(103, 'jay', 'adarsh', '1234123412', 5);

--
-- Triggers `restaurant`
--
DELIMITER $$
CREATE TRIGGER `backup_restaurant` BEFORE DELETE ON `restaurant` FOR EACH ROW BEGIN 

     INSERT INTO restaurant_backup
     (restaurant_id,name,address,phone_no,rating) VALUES (old.restaurant_id,old.name,old.address,old.phone_no,old.rating);
     
     DELETE FROM temp WHERE restaurant_id = old.restaurant_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_backup`
--

CREATE TABLE `restaurant_backup` (
  `restaurant_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone_no` varchar(100) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurant_backup`
--

INSERT INTO `restaurant_backup` (`restaurant_id`, `name`, `address`, `phone_no`, `rating`) VALUES
(100, 'Vegan Feast', '100 Horizon Street, Ahmedabad', '9001234567', 4),
(99, 'Seafood Haven', '99 Seaside Boulevard, Ahmedabad', '9990123456', 5),
(98, 'Pizza Palace', '98 Cedar Avenue, Ahmedabad', '9889012345', 4),
(97, 'Pasta Paradise', '97 Oak Drive, Ahmedabad', '9778901234', 5),
(102, 'the', 'D', '9313872776', 4);

-- --------------------------------------------------------

--
-- Table structure for table `temp`
--

CREATE TABLE `temp` (
  `temp_id` int(11) NOT NULL,
  `Category_id` int(11) DEFAULT NULL,
  `Restaurant_id` int(11) DEFAULT NULL,
  `Item_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `temp`
--

INSERT INTO `temp` (`temp_id`, `Category_id`, `Restaurant_id`, `Item_id`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 2),
(3, 1, 3, 3),
(4, 1, 4, 4),
(5, 1, 5, 5),
(6, 1, 6, 6),
(7, 1, 7, 7),
(8, 1, 8, 8),
(9, 1, 9, 9),
(10, 1, 10, 10),
(11, 2, 11, 11),
(12, 2, 12, 12),
(13, 2, 13, 13),
(14, 2, 14, 14),
(15, 2, 15, 15),
(16, 2, 16, 16),
(17, 2, 17, 17),
(18, 2, 18, 18),
(19, 2, 19, 19),
(20, 2, 20, 20),
(21, 3, 21, 21),
(22, 3, 22, 22),
(23, 3, 23, 23),
(24, 3, 24, 24),
(25, 3, 25, 25),
(26, 3, 26, 26),
(27, 3, 27, 27),
(28, 3, 28, 28),
(29, 3, 29, 29),
(30, 3, 30, 30),
(31, 4, 31, 31),
(32, 4, 32, 32),
(33, 4, 33, 33),
(34, 4, 34, 34),
(35, 4, 35, 35),
(36, 4, 36, 36),
(37, 4, 37, 37),
(38, 4, 38, 38),
(39, 4, 39, 39),
(40, 4, 40, 40),
(41, 5, 41, 41),
(42, 5, 42, 42),
(43, 5, 43, 43),
(44, 5, 44, 44),
(45, 5, 45, 45),
(46, 5, 46, 46),
(47, 5, 47, 47),
(48, 5, 48, 48),
(49, 5, 49, 49),
(50, 5, 50, 50),
(51, 6, 51, 51),
(52, 6, 52, 52),
(53, 6, 53, 53),
(54, 6, 54, 54),
(55, 6, 55, 55),
(56, 6, 56, 56),
(57, 6, 57, 57),
(58, 6, 58, 58),
(59, 6, 59, 59),
(60, 6, 60, 60),
(61, 7, 61, 61),
(62, 7, 62, 62),
(63, 7, 63, 63),
(64, 7, 64, 64),
(65, 7, 65, 65),
(66, 7, 66, 66),
(67, 7, 67, 67),
(68, 7, 68, 68),
(69, 7, 69, 69),
(70, 7, 70, 70),
(71, 8, 71, 71),
(72, 8, 72, 72),
(73, 8, 73, 73),
(74, 8, 74, 74),
(75, 8, 75, 75),
(76, 8, 76, 76),
(77, 8, 77, 77),
(78, 8, 78, 78),
(79, 8, 79, 79),
(80, 8, 80, 80),
(81, 9, 81, 81),
(82, 9, 82, 82),
(83, 9, 83, 83),
(84, 9, 84, 84),
(85, 9, 85, 85),
(86, 9, 86, 86),
(87, 9, 87, 87),
(88, 9, 88, 88),
(89, 9, 89, 89),
(90, 9, 90, 90),
(91, 10, 91, 91),
(92, 10, 92, 92),
(93, 10, 93, 93),
(94, 10, 94, 94),
(95, 10, 95, 95),
(96, 10, 96, 96);

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_restaurant`
-- (See below for the actual view)
--
CREATE TABLE `view_restaurant` (
`restaurant_id` int(11)
,`name` varchar(30)
,`address` varchar(100)
,`phone_no` varchar(10)
,`rating` int(11)
,`category_id` int(11)
,`category_name` varchar(30)
,`item_id` int(11)
,`item_name` varchar(30)
,`price` decimal(10,2)
);

-- --------------------------------------------------------

--
-- Structure for view `view_restaurant`
--
DROP TABLE IF EXISTS `view_restaurant`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_restaurant`  AS SELECT `restaurant`.`restaurant_id` AS `restaurant_id`, `restaurant`.`name` AS `name`, `restaurant`.`address` AS `address`, `restaurant`.`phone_no` AS `phone_no`, `restaurant`.`rating` AS `rating`, `category`.`category_id` AS `category_id`, `category`.`category_name` AS `category_name`, `menuitem`.`item_id` AS `item_id`, `menuitem`.`item_name` AS `item_name`, `menuitem`.`price` AS `price` FROM (((`restaurant` join `admin` on(`restaurant`.`restaurant_id` = `admin`.`restaurant_id`)) join `category` on(`category`.`category_id` = `admin`.`category_id`)) join `menuitem` on(`menuitem`.`item_id` = `admin`.`menu_id`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `feedback_id` (`feedback_id`),
  ADD KEY `menu_id` (`menu_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `payment_id` (`payment_id`),
  ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `menuitem`
--
ALTER TABLE `menuitem`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `fk_category` (`category_id`),
  ADD KEY `fk_restaurant` (`restaurant_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`restaurant_id`);

--
-- Indexes for table `temp`
--
ALTER TABLE `temp`
  ADD PRIMARY KEY (`temp_id`),
  ADD KEY `Category_id` (`Category_id`),
  ADD KEY `Restaurant_id` (`Restaurant_id`),
  ADD KEY `Item_id` (`Item_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `menuitem`
--
ALTER TABLE `menuitem`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `restaurant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `admin_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `admin_ibfk_3` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`),
  ADD CONSTRAINT `admin_ibfk_4` FOREIGN KEY (`menu_id`) REFERENCES `menuitem` (`item_id`),
  ADD CONSTRAINT `admin_ibfk_5` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `admin_ibfk_6` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  ADD CONSTRAINT `admin_ibfk_7` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);

--
-- Constraints for table `menuitem`
--
ALTER TABLE `menuitem`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);

--
-- Constraints for table `temp`
--
ALTER TABLE `temp`
  ADD CONSTRAINT `temp_ibfk_1` FOREIGN KEY (`Category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `temp_ibfk_2` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  ADD CONSTRAINT `temp_ibfk_3` FOREIGN KEY (`Item_id`) REFERENCES `menuitem` (`item_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
