# Food-Orderig-System

Overview
The Food Ordering System is a Java-based project inspired by popular food delivery platforms like Zomato. It enables customers to browse restaurants, add items to their cart, place orders, and leave reviews. Admins can manage restaurants, food items, and customer orders via CRUD operations.

This system integrates MySQL as the backend database and uses JDBC (Java Database Connectivity) for database interactions. Additionally, key data structures like Stack and Doubly Linked List are implemented to handle orders and restaurant data efficiently.

🚀 Features

👨‍💼 Admin Functionalities

 * CRUD Operations on Restaurants & Food Items
 * Add, update, or remove restaurants from the system.
 * Modify food item details, including price and availability.
 * Order Management
 * View customer orders and update order statuses.
 * View Customer Feedback
 * Monitor restaurant ratings and reviews.

   
🛒 Customer Functionalities

 * Restaurant & Menu Browsing
 * View available restaurants and their menus.
 * Order Placement & Cart Management
 * Add/remove items to/from the cart before confirming an order.
 * Review & Feedback System
 * Provide feedback after an order is completed.


📊 Data Structure Implementations

 * Stack (For maintaining order history)
 * Doubly Linked List (For managing customer orders in adding to cart efficiently)


🛠 Tech Stack

 * Java (Core Logic)
 * MySQL (Database)
 * JDBC (Database Connection)
 * Data Structures: Doubly Linked List

   
📂 Project Structure
/FoodOrderingSystem │── /src │ ├── FoodOrderingSystem1.java # Main entry point │ ├── Admin.java # Admin functionalities │ ├── Customer.java # Customer functionalities │ ├── CartItem.java # Manages items in the cart │ ├── RestaurantRating.java # Handles restaurant reviews │ ├── OrderedItem.java # Stores order details │ ├── Connection_database.java # JDBC MySQL connection │ ├── Stack.java # Implements Stack for order history │ ├── DoublyLinkedList.java # Implements Doubly Linked List for managing orders in cart │── /db │ ├── food_ordering_schema.sql # SQL script to set up the database │── README.md

