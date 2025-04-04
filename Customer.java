import java.util.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
    static DoublyLinkedList Linkedlist = new DoublyLinkedList();
    static Scanner scanner = new Scanner(System.in);
    static int tempCustomerId;
    static Stack stack = new Stack();
    static int order_no = 1;

    static void customerMenu() throws SQLException {
        try {
            System.out.println("1. New Customer");
            System.out.println("2. Existing Customer");
            System.out.println("3. back");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    newCustomer();
                    customerOptions();
                    break;
                case 2:
                    existingCustomer();
                    customerOptions();
                    break;
                case 3:
                    return;
            }
        } catch (

        Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void newCustomer() throws SQLException {
        String name = validateName();
        String email = validateEmail();
        String password = validatePassword();
        String phone = validatePhone();
        System.out.println("enter your address : ");
        String address = scanner.nextLine();

        try {
            Connection connection = connection_database.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call insert_new_customer(?,?,?,?,?)}");
            callableStatement.setString(1, name);
            callableStatement.setString(2, email);
            callableStatement.setString(3, phone);
            callableStatement.setString(4, address);
            callableStatement.setString(5, password);
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                System.out.println("Welcome, " + name + "!");
            }
            String query1 = "SELECT * from Customer where email = '" + email + "' and '" + password + "'";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            if (rs.next()) {
                tempCustomerId = rs.getInt("customer_id");
                System.out.println(tempCustomerId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("your customer id is :" + tempCustomerId);

    }

    static void existingCustomer() throws SQLException {
        try {
            while (true) {
                System.out.println("Enter Email to Login : ");
                String email = scanner.nextLine();
                System.out.println("Enter Password : ");
                String password = scanner.nextLine();
                Connection connection = connection_database.getConnection();
                CallableStatement callableStatement = connection.prepareCall("{Call check_customer(?,?,?,?)}");
                callableStatement.setString("cemail", email);
                callableStatement.setString("cpassword", password);
                callableStatement.execute();
                tempCustomerId = callableStatement.getInt("cid");
                if (tempCustomerId != 0) {
                    System.out.println("Welcome, " + callableStatement.getString("cname") + "!");
                    return;
                } else {
                    System.out.println("Invalid Email or Password!");
                }

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void customerOptions() throws SQLException, Exception {
        pushOrderData();
        int choice;
        do {
            System.out.println("1. Add to Cart");
            System.out.println("2. Remove from Cart");
            System.out.println("3. Update Cart item quantity");
            System.out.println("4. view Cart");
            System.out.println("5. Place Order & payment");
            System.out.println("6. Check Order History");
            System.out.println("7. Show Most Rated Restaurant");
            System.out.println("8. Show Previous Order");
            System.out.println("9. Show Customer ditails");
            System.out.println("10. Update Customer Profile");
            System.out.println("11. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addToCart();
                    break;
                case 2:
                    removeFromCart();
                    break;
                case 3:
                    UpdateCartItemQuantity();
                    break;
                case 4:
                    viewAddtoCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    checkOrderHistory();
                    break;
                case 7:
                    showMostRatedRestaurant();
                    break;
                case 8:
                    showLastTimeOrder();
                    break;
                case 9:
                    ShowCustomerdetailsByCustomerId();
                    break;
                case 10:
                    UpdateCustomerProfile();
                    break;
                case 11:
                    return;

            }
        } while (choice != 11);
    }

    static void UpdateCustomerProfile() {

        String name = validateName();
        String email = validateEmail();
        String password = validatePassword();
        String phone = validatePhone();
        System.out.println("enter your address : ");
        String address = scanner.nextLine();
        String sql = "update customer set name=?,email=?,password=?,phone=?,address=? where customer_id =?";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setInt(6, tempCustomerId);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                System.out.println("data updated successfully");
            } else {
                System.out.println("data not updated ");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    static void addToCart() {
        String Category_name = null;
        String Restaurant_name = null;
        String Item_name = null;
        int Item_price = 0;
        int Restaurant_rating = 0;
        int quantity = 0;

        String categoryQuery = "SELECT category_id, category_name FROM Category";
        try {
            Connection connection = connection_database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(categoryQuery);
            System.out.println("Categories : ");
            while (resultSet.next()) {
                System.out.println("Category_ID = " + resultSet.getInt("category_id") + " Category_name = "
                        + resultSet.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Select Category ID : ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        try {
            String q1 = "SELECT * from category where category_id =?";
            Connection connection = connection_database.getConnection();
            PreparedStatement p1 = connection.prepareStatement(q1);
            p1.setInt(1, categoryId);
            ResultSet rs1 = p1.executeQuery();
            if (rs1.next()) {
                Category_name = rs1.getString("category_name");
            }

            String restaurantQuery = "SELECT restaurant.restaurant_id, restaurant.name, restaurant.address, restaurant.phone_no, restaurant.rating FROM Restaurant inner join temp on temp.restaurant_id = restaurant.restaurant_id inner join category on category.category_id = temp.category_id where category.category_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(restaurantQuery);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Restaurants :");
            while (resultSet.next()) {
                System.out.println("Restaurant_id = " + resultSet.getInt("restaurant_id") + " Restaurant_name = "
                        + resultSet.getString("name") + " Address = "
                        + resultSet.getString("address") + " Phone_no = " + resultSet.getString("phone_no")
                        + " Rating = "
                        + resultSet.getInt("rating"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Select Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        String q2 = "SELECT * from restaurant where restaurant_id =?";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement p2 = connection.prepareStatement(q2);
            p2.setInt(1, restaurantId);
            ResultSet rs2 = p2.executeQuery();
            if (rs2.next()) {
                Restaurant_name = rs2.getString("name");
                Restaurant_rating = rs2.getInt("rating");
            }

            String menuItemQuery = "SELECT item_id, item_name, price, rating FROM MenuItem WHERE restaurant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(menuItemQuery);
            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Menu Items :");
            while (resultSet.next()) {
                System.out.println("item_id = " + resultSet.getInt("item_id") + " item_name = "
                        + resultSet.getString("item_name") + " item_price = "
                        + resultSet.getDouble("price") + " item_rating = " + resultSet.getInt("rating"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Select Item ID : ");
        int itemId = scanner.nextInt();
        System.out.println("Enter Quntity ");
        quantity = scanner.nextInt();
        String q3 = "SELECT * FROM menuitem where item_id=?";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement p3 = connection.prepareStatement(q3);
            p3.setInt(1, itemId);
            ResultSet rs3 = p3.executeQuery();
            if (rs3.next()) {
                Item_name = rs3.getString("item_name");
                Item_price = rs3.getInt("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        scanner.nextLine();

        Linkedlist.addLast(
                new CartItem(itemId, restaurantId, categoryId, Restaurant_name, Category_name, Item_name, Item_price,
                        quantity,
                        Restaurant_rating));
    }

    static void removeFromCart() {
        DoublyLinkedList.Node temp = DoublyLinkedList.head;
        if (temp == null) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Cart Display :");
        int count = 1;
        while (temp != null) {
            System.out.println("Cart_NO: " + count + " Item_ID: " + temp.data.getItemId() + ", Restaurant_ID: "
                    + temp.data.getRestaurantId() + ", Category_ID: " + temp.data.getCategoryId()
                    + ", Restaurant_Name: "
                    + temp.data.getRestaurant_name() + ", Category_Name: " + temp.data.getCategory_name()
                    + ", Item_Name: "
                    + temp.data.getItem_name() + ", Item_Price: " + temp.data.getItem_price() + ", Item_Quantity: "
                    + temp.data.getQuantity()
                    + ", Restaurant_Rating: "
                    + temp.data.getRestaurant_rating());
            count++;
            temp = temp.next;
        }

        System.out.println("Select Cart Number to Remove: ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine();
        Linkedlist.remove(itemNumber);

    }

    static void viewAddtoCart() {
        DoublyLinkedList.printList();
    }

    static void placeOrder() throws SQLException, Exception {
        DoublyLinkedList.Node temp = DoublyLinkedList.head;
        DoublyLinkedList.Node temp1 = DoublyLinkedList.head;
        DoublyLinkedList.Node temp2 = DoublyLinkedList.head;
        DoublyLinkedList.Node temp3 = DoublyLinkedList.head;

        while (temp2 == null) {
            System.out.println("Cart is empty.");
            return;
        }
        double totalPrice = 0;
        while (temp1 != null) {
            totalPrice = totalPrice + (temp1.data.getItem_price() * temp1.data.getQuantity());
            temp1 = temp1.next;
        }
        try {

            String paymentMode;
            int paymentModeChoice;
            System.out.println("Select Payment Mode");
            System.out.println("1. UPI");
            System.out.println("2. Cash");
            System.out.println("3. Credit Card");
            System.out.println("4. Debit Card");
            paymentModeChoice = scanner.nextInt();
            switch (paymentModeChoice) {
                case 1:
                    paymentMode = "UPI";
                    validateUPIID();
                    validatePIN();
                    System.out.println("Generate Bill");
                    break;
                case 2:
                    paymentMode = "Cash";
                    System.out.println("Generate Bill");
                    scanner.nextLine();
                    break;
                case 3:
                    paymentMode = "Credit Card";
                    validateCardno();
                    validatePIN();
                    System.out.println("Generate Bill");
                    break;
                case 4:
                    paymentMode = "Debit Card";
                    validateCardno();
                    validatePIN();
                    System.out.println("Generate Bill");
                    break;
                default:
                    System.out.println("Invalid payment method.");
                    return;
            }

            viewAddtoCart();

            System.out.println(" TOTAL BILL :" + totalPrice);
            System.out.println("Do you want to Pay Bill ? (yes/no)");
            String question = scanner.nextLine();
            Connection connection = connection_database.getConnection();
            connection.setAutoCommit(false);
            if (question.equalsIgnoreCase("yes")) {

                String query = "INSERT INTO payment(payment_type, customer_id, total_price, payment_date) VALUES(?,?,?,CURRENT_TIMESTAMP)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, paymentMode);
                preparedStatement.setInt(2, tempCustomerId);
                preparedStatement.setDouble(3, totalPrice);
                int ans = preparedStatement.executeUpdate();
                if (ans > 0) {
                    System.out.println("payment data inserted successfully");
                } else {
                    System.out.println("data not inserted");
                }

            } else {
                System.out.println("Payment not done ");
                return;
            }

            while (temp != null) {
                String query = "INSERT INTO Orders(customer_id, item_name, quantity, total_price, overall_total ,order_date) VALUES (?, ?, ?, ?, ?, CURRENT_DATE)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, tempCustomerId);
                preparedStatement.setString(2, temp.data.getItem_name());
                preparedStatement.setInt(3, temp.data.getQuantity());
                preparedStatement.setDouble(4, (temp.data.getItem_price() + temp.data.getQuantity()));
                preparedStatement.setDouble(5, totalPrice);
                preparedStatement.executeUpdate();
                temp = temp.next;
                System.out.println("Order placed successfully.");
                connection.setAutoCommit(true);
            }

        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Would you like to rate the restaurant? (yes/no): ");
        String rate = scanner.nextLine();
        if (rate.equalsIgnoreCase("yes")) {
            System.out.println("Enter Rating (1-5): ");
            int rating = scanner.nextInt();
            scanner.nextLine();
            int restaurantId = DoublyLinkedList.head.data.getRestaurantId();
            String feedbackQuery = "INSERT INTO Feedback (customer_id, restaurant_id, rating) VALUES (?, ?, ?)";
            try {
                Connection connection1 = connection_database.getConnection();
                PreparedStatement PS = connection1.prepareStatement(feedbackQuery);
                PS.setInt(1, tempCustomerId);
                PS.setInt(2, restaurantId);
                PS.setInt(3, rating);
                PS.executeUpdate();

                System.out.println("Thank you for your feedback!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        while (temp3 != null) {
            Stack.push(new OrderedItem(order_no, tempCustomerId, temp3.data.getItem_name(), temp3.data.getQuantity(),
                    temp3.data.getItem_price() + temp3.data.getQuantity(), formattedDate, (int) totalPrice));
            order_no++;
            temp3 = temp3.next;
        }
        DoublyLinkedList.removeAllData();
    }

    static void pushOrderData() throws SQLException {
        try {
            String query2 = "SELECT * from Orders where customer_id = ?";
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setInt(1, tempCustomerId);
            ResultSet rs = preparedStatement2.executeQuery();
            while (rs.next()) {
                int custom_id = rs.getInt("customer_id");
                String item_name = rs.getString("item_name");
                int quantity = rs.getInt("quantity");
                double total_price = rs.getDouble("total_price");
                String order_date = rs.getString("order_date");
                int overall_total = rs.getInt("overall_total");
                Stack.push(new OrderedItem(order_no, custom_id, item_name, quantity, total_price, order_date,
                        overall_total));
                order_no++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void checkOrderHistory() {
        stack.display();
    }

    static void showLastTimeOrder() {
        stack.peek();
    }

    static void showMostRatedRestaurant() {
        String query = "SELECT restaurant.restaurant_id,restaurant.name,restaurant.address,restaurant.phone_no,feedback.rating, AVG(feedback.rating) as avg_rating FROM feedback inner join restaurant on feedback.restaurant_id = restaurant.restaurant_id GROUP BY feedback.restaurant_id ORDER BY avg_rating DESC LIMIT 1";
        try {
            Connection connection = connection_database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurant_id");
                String restaurantname = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_no");
                int feedbackrating = resultSet.getInt("rating");
                double avgRating = resultSet.getDouble("avg_rating");
                System.out.println("Most Rated Restaurant ID: " + restaurantId + " Restaurant_name: " + restaurantname
                        + " Address: " + address + " + Phone: " + phone + " feedback_rating: " + feedbackrating
                        + " with Average Rating: " + avgRating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void UpdateCartItemQuantity() {
        viewAddtoCart();
        DoublyLinkedList.Node temp = DoublyLinkedList.head;
        System.out.println("Enter the item_id you want to update");
        int item_no = scanner.nextInt();
        while (temp != null) {
            if (temp.data.getItemId() == item_no) {
                System.out.println("Enter the new quantity");
                int newquantity = scanner.nextInt();
                temp.data.setQuantity(newquantity);
            }
            temp = temp.next;

        }

    }

    private static String validateUPIID() {
        scanner.nextLine();
        while (true) {
            System.out.println("Enter UPI ID : ");
            String UPINO = scanner.nextLine();
            if (UPINO.length() == 10 && isNumeric(UPINO)) {
                return UPINO;
            } else {
                System.out.println("Invalid UPI number.UPI number must be exactly 10 digits long.");
            }
        }
    }

    private static String validatePIN() {
        while (true) {
            System.out.println("Enter UPI PIN: ");
            String UPIPIN = scanner.nextLine();
            if (UPIPIN.length() == 6 && isNumeric(UPIPIN)) {
                return UPIPIN;
            } else {
                System.out.println("Invalid UPI PIN.UPI PIN must be exactly 6 digits long.");
            }
        }
    }

    private static String validateCardno() {
        scanner.nextLine();
        while (true) {
            System.out.println("Enter Card NO : ");
            String Creditno = scanner.nextLine();
            if (Creditno.length() == 16 && isNumeric(Creditno)) {
                return Creditno;
            } else {
                System.out.println("Invalid  Card number. Card number must be exactly 16 digits long.");
            }
        }
    }

    static void ShowCustomerdetailsByCustomerId() {
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer where customer_id = ?");
            preparedStatement.setInt(1, tempCustomerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(" customer_id : " + resultSet.getString("customer_id") + ", Customer_name : "
                        + resultSet.getString("name") + ", Customer_email : "
                        + resultSet.getString("email") + ", customer_phone : " + resultSet.getString("phone")
                        + ", Customer_address : "
                        + resultSet.getString("address"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    private static String validateName() {
        while (true) {
            System.out.println("Enter Name (Only alphabets): ");
            String name = scanner.nextLine();
            if (isAlpha(name)) {
                return name;
            } else {
                System.out.println("Invalid name. Name should contain only alphabets.");
            }
        }
    }

    private static String validateEmail() {
        while (true) {
            System.out.println("Enter Email (Minimum length 15 and ends with '@gmail.com'): ");
            String email = scanner.nextLine();
            if (email.length() >= 15 && email.endsWith("@gmail.com")) {
                return email;
            } else {
                System.out
                        .println("Invalid email. Email must be at least 15 characters long and end with '@gmail.com'.");
            }
        }
    }

    private static String validatePassword() {
        while (true) {
            System.out.println("Enter Password (Length must be 8 and contain at least 1 special character): ");
            String password = scanner.nextLine();
            if (password.length() == 8 && containsSpecialCharacter(password)) {
                return password;
            } else {
                System.out.println(
                        "Invalid password. Password must be exactly 8 characters long and contain at least 1 special character.");
            }
        }
    }

    private static String validatePhone() {
        while (true) {
            System.out.println("Enter Phone (Length must be 10 and contain only digits): ");
            String phone = scanner.nextLine();
            if (phone.length() == 10 && isNumeric(phone)) {
                return phone;
            } else {
                System.out.println("Invalid phone number. Phone number must be exactly 10 digits long.");
            }
        }
    }

    private static boolean isAlpha(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsSpecialCharacter(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

}