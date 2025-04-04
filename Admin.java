
import java.util.*;
import java.sql.*;

public class Admin {
    static Customer customer = new Customer();
    static Scanner scanner = new Scanner(System.in);

    public static void adminMenu() throws Exception {
        System.out.println("Enter Username : ");
        String username = scanner.nextLine();
        System.out.println("Enter Password : ");
        String password = scanner.nextLine();
        if (authenticateAdmin(username, password)) {
            System.out.println(" Welcome, Admin! ");
            int choice = 0;
            do {
                System.out.println("1. Add Restaurants");
                System.out.println("2. Remove Restaurants");
                System.out.println("3. change Restaurant data");
                System.out.println("4. Add Menu Items");
                System.out.println("5. Remove Menu Items");
                System.out.println("6. Change Menu Item data");
                System.out.println("7. Add Category");
                System.out.println("8. Remove Category");
                System.out.println("9. Change Category data");
                System.out.println("10. Change Price of Food");
                System.out.println("11. Display Restaurant data");
                System.out.println("12. Display all Restaurants");
                System.out.println("13. Display all Categories");
                System.out.println("14. Display all Items");
                System.out.println("15. Show Most Rated Restaurant");
                System.out.println("16. Show Customer details");
                System.out.println("17. Show CUstomer by Payment_id");
                System.out.println("18. Exit");

                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        addRestaurant();
                        break;
                    case 2:
                        removeRestaurant();
                        break;
                    case 3:
                        changeRestaurantData();
                        break;
                    case 4:
                        addMenuItem();
                        break;
                    case 5:
                        removeMenuItem();
                        break;
                    case 6:
                        changeMenuItemData();
                        break;
                    case 7:
                        addCategoryItem();
                        break;
                    case 8:
                        removeCategory();
                        break;
                    case 9:
                        changeCategoryData();
                        break;
                    case 10:
                        changePrice();
                        break;
                    case 11:
                        while (true) {
                            System.out.println("1. Display Restaurant data by Restaurant_id");
                            System.out.println("2. Display Restaurant data by Category_Id");
                            System.out.println("3. Display Restaurant data by Item_Id");
                            System.out.println("4. Back");
                            System.out.println("Enter choice : ");
                            int choice1 = scanner.nextInt();
                            switch (choice1) {
                                case 1:
                                    displayRestaurantByRestaurant_id();
                                    break;
                                case 2:
                                    displayRestaurantByCategory_id();
                                    break;
                                case 3:
                                    displayRestaurantByItem_id();
                                    break;
                                case 4:
                                    return;

                            }

                        }

                    case 12:
                        displayRestaurants();
                        break;
                    case 13:
                        diaplayCategories();
                        break;
                    case 14:
                        displayItems();
                        break;
                    case 15:
                        Customer.showMostRatedRestaurant();
                        break;
                    case 16:
                        while (true) {
                            System.out.println("1. show Customer details By Customer_id");
                            System.out.println("2. show Customer details By Customer_name");
                            System.out.println("3. show Customer details By Customer_email");
                            System.out.println("4. show Customer details By Customer_phone");
                            System.out.println("5. show Customer details By Customer_address");
                            System.out.println("6. Back");
                            System.out.println("Enter choice : ");
                            int choice2 = scanner.nextInt();
                            scanner.nextLine();

                            switch (choice2) {
                                case 1:
                                    System.out.println("Enter Customer_id : ");
                                    int customer_id = scanner.nextInt();
                                    ShowCustomerdetailsByCustomerId(customer_id);
                                    break;
                                case 2:
                                    System.out.println("Enter Customer_name : ");
                                    String customer_name = scanner.nextLine();
                                    ShowCustomerdetailsByCustomername(customer_name);

                                    break;

                                case 3:
                                    System.out.println("Enter Customer_email : ");
                                    String customer_email = scanner.nextLine();
                                    ShowCustomerdetailsByCustomeremail(customer_email);

                                    break;
                                case 4:
                                    System.out.println("Enter Customer_phone : ");
                                    String customer_phone = scanner.nextLine();
                                    ShowCustomerdetailsByCustomerphone(customer_phone);

                                    break;
                                case 5:
                                    System.out.println("Enter Customer_phone : ");
                                    String customer_address = scanner.nextLine();
                                    ShowCustomerdetailsByCustomerphone(customer_address);
                                    break;
                                case 6:
                                    return;

                            }

                            break;
                        }
                        break;
                    case 17:
                        System.out.println(" Enter Payment_id");
                        int payment_id = scanner.nextInt();
                        ShowCustomerdetailsBypaymentId(payment_id);
                        break;

                }
            } while (choice != 18);
        } else {
            System.out.println("Invalid Username or Password!");
        }
    }

    static void ShowCustomerdetailsBypaymentId(int paymentId) {
        String query = " SELECT * FROM customer inner join payment on  customer.customer_id = payment.customer_id WHERE payment.payment_id = ?";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query);
            preparedStatement.setInt(1, paymentId);
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

    static boolean authenticateAdmin(String username, String password) throws SQLException {
        try {
            Connection connection = connection_database.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call chack_admin(?,?,?)}");
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.execute();
            int ans = callableStatement.getInt(3);
            if (ans > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void addRestaurant() throws SQLException {
        System.out.println("Enter Restaurant Name : ");
        String name = scanner.nextLine();
        System.out.println("Enter Restaurant Address : ");
        String address = scanner.nextLine();
        System.out.println("Enter Restaurant Phone No : ");
        String phone = scanner.nextLine();
        System.out.println("Enter Restaurent rating (out of 5 star) : ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        try {
            Connection connection = connection_database.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call add_restaurant(?,?,?,?)}");
            callableStatement.setString(1, name);
            callableStatement.setString(2, address);
            callableStatement.setString(3, phone);
            callableStatement.setInt(4, rating);
            int a1 = callableStatement.executeUpdate();
            if (a1 > 0) {
                System.out.println("Restaurant added successfully.");
            } else {
                System.out.println("Restaurant NOT added.");
            }
            System.out.println("Enter category name");
            String category_name = scanner.nextLine();
            addCategoryItem(category_name);
            String sql4 = "SELECT category_id from category where category_name = ?";
            PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
            preparedStatement4.setString(1, category_name);
            ResultSet rs1 = preparedStatement4.executeQuery();
            int category_id = 0;
            if (rs1.next()) {
                category_id = rs1.getInt("category_id");
            }
            System.out.println(
                    "Enter Menu Items name ,price and rating (out of 5 star) IF No Other Items Add When Type 'done' when finished.");
            while (true) {
                System.out.println("Enter Item Name: ");
                String item_Name = scanner.nextLine();
                if (item_Name.equalsIgnoreCase("done"))
                    break;
                System.out.println("Enter Item Price: ");
                double price = scanner.nextDouble();
                System.out.println("Enter Restaurent rating (out of 5 star) : ");
                int rating2 = scanner.nextInt();
                int restaurant_Id = 0;
                String sql2 = "SELECT restaurant_id from restaurant where name = ?";
                PreparedStatement preparedStatement3 = connection.prepareStatement(sql2);
                preparedStatement3.setString(1, name);
                ResultSet rs = preparedStatement3.executeQuery();
                if (rs.next()) {
                    restaurant_Id = rs.getInt("restaurant_Id");
                }
                scanner.nextLine();
                addMenuItem(restaurant_Id, category_id, item_Name, price, rating2);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void addCategoryItem() throws SQLException {
        System.out.println("Enter Category Name: ");
        String categoryName = scanner.nextLine();
        addCategoryItem(categoryName);
    }

    static void addMenuItem() throws SQLException {
        String sql = "SELECT restaurant.restaurant_id,restaurant.name,category.category_id,category.category_name,restaurant.rating FROM restaurant inner join temp on temp.restaurant_id = restaurant.restaurant_id inner join category on category.category_id = temp.category_id ;";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "Restaurent_id = " + rs.getInt("restaurant_id") + " Restaurent_name = " + rs.getString("name")
                                + "Category_id = " + rs.getInt("category_id") + " category_name = "
                                + rs.getString("category_name")
                                + " rating = "
                                + rs.getInt("rating"));
            }
            System.out.println("Enter Restaurant ID: ");
            int restaurantId = scanner.nextInt();
            diaplayCategories();
            System.out.println("Enter category ID: ");
            int category_id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Item Name: ");
            String itemName = scanner.nextLine();
            System.out.println("Enter Item Price: ");
            double price = scanner.nextDouble();
            System.out.println("Enter Restaurent rating (out of 5 star) : ");
            int rating = scanner.nextInt();
            scanner.nextLine();
            addMenuItem(restaurantId, category_id, itemName, price, rating);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void addCategoryItem(String category_name) throws SQLException {
        try {
            Connection connection = connection_database.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call add_category(?)}");
            callableStatement.setString(1, category_name);
            int a1 = callableStatement.executeUpdate();
            if (a1 > 0) {
                System.out.println("Category added successfully.");
            } else {
                System.out.println("Category NOT added.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void addMenuItem(int restaurantId, int category_id, String itemName, double price, int rating2)
            throws SQLException {
        try {
            Connection connection = connection_database.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{Call add_menuitem(?,?,?,?,?)}");
            callableStatement.setInt(1, restaurantId);
            callableStatement.setString(2, itemName);
            callableStatement.setDouble(3, price);
            callableStatement.setInt(4, category_id);
            callableStatement.setInt(5, rating2);
            int a1 = callableStatement.executeUpdate();
            if (a1 > 0) {
                System.out.println("Item added successfully.");
            } else {
                System.out.println("Item NOT added.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void removeRestaurant() throws SQLException {
        String sql = "SELECT * FROM restaurant";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Restaurent_id = " + rs.getInt("restaurant_id") + " Restaurent_name = "
                        + rs.getString("name") + " address = " + rs.getString("address") + " Phone_no = "
                        + rs.getString("phone_no") + " rating = "
                        + rs.getInt("rating"));
            }
            System.out.println("Enter Restaurant ID to Remove: ");
            int restaurantId = scanner.nextInt();
            scanner.nextLine();
            String deleteMenuItemsQuery = "DELETE FROM MenuItem WHERE restaurant_id = ?";
            String deleteRestaurantQuery = "DELETE FROM Restaurant WHERE restaurant_id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(deleteMenuItemsQuery);
            PreparedStatement preparedStatement2 = connection.prepareStatement(deleteRestaurantQuery);
            preparedStatement1.setInt(1, restaurantId);
            int a1 = preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, restaurantId);
            preparedStatement2.executeUpdate();
            if (a1 > 0) {
                System.out.println("Restaurant and its menu items removed successfully.");
            } else {
                System.out.println("Restaurant and its menu items NOT removed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void removeMenuItem() throws SQLException {
        String sql = "SELECT * from menuitem";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                System.out.println("item_id = " + rs.getInt("item_id") + " item_name = "
                        + rs.getString("item_name") + " Price = " + rs.getDouble("price") + " rating = "
                        + rs.getInt("rating"));
            }
            System.out.println("Enter Item ID to Remove: ");
            int itemId = scanner.nextInt();
            scanner.nextLine();
            String query = "DELETE FROM MenuItem WHERE item_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, itemId);
            int a1 = preparedStatement.executeUpdate();
            if (a1 > 0) {
                System.out.println("Menu item removed successfully.");
            } else {
                System.out.println("Menu item NOT removed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void removeCategory() throws SQLException {
        String sql = "SELECT * FROM category ";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                System.out.println("Category_id = " + rs.getInt("category_id") + " Category_name = "
                        + rs.getString("category_name"));
            }
            System.out.println("Enter Category ID to Remove: ");
            int categoryid = scanner.nextInt();
            scanner.nextLine();
            String query = "DELETE FROM category WHERE category_id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query);
            preparedStatement2.setInt(1, categoryid);
            int a1 = preparedStatement2.executeUpdate();
            if (a1 > 0) {
                System.out.println("Category removed successfully.");
            } else {
                System.out.println("Category NOT removed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void changePrice() throws SQLException {
        String sql = "SELECT * from menuitem";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                System.out.println("item_id = " + rs.getInt("item_id") + " item_name = "
                        + rs.getString("item_name") + " Price = " + rs.getDouble("price") + " rating = "
                        + rs.getInt("rating"));
            }
            System.out.println("Enter Item ID : ");
            int itemId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter New Price : ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            String query = "UPDATE MenuItem SET price = ? WHERE item_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2, itemId);
            int a1 = preparedStatement.executeUpdate();
            if (a1 > 0) {
                System.out.println("Price updated successfully.");
            } else {
                System.out.println("Price NOT updated.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    static void displayRestaurantByRestaurant_id() throws SQLException {
        System.out.println("Enter Restaurant id : ");
        int id = scanner.nextInt();
        String sql = "SELECT * FROM Restaurant where restaurant_id = ?";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3)
                        + "-" + resultSet.getString(4) + "-" + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void displayRestaurantByCategory_id() throws SQLException {
        System.out.println("Enter Category id : ");
        int id = scanner.nextInt();
        String sql = "SELECT restaurant.restaurant_id,name,address,phone_no,rating FROM restaurant inner join temp on temp.restaurant_id = restaurant.restaurant_id inner join Category on temp.category_id = category.category_id where category.category_id =?";

        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3)
                        + "-" + resultSet.getString(4) + "-" + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void displayRestaurantByItem_id() throws SQLException {
        System.out.println("Enter Item id : ");
        int id = scanner.nextInt();
        String sql = "SELECT restaurant.restaurant_id,name,address,phone_no,restaurant.rating FROM restaurant inner join temp on temp.restaurant_id = restaurant.restaurant_id inner join MenuItem on temp.Item_id = MenuItem.Item_id where MenuItem.Item_id =?";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3)
                        + "-" + resultSet.getString(4) + "-" + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void displayRestaurants() throws SQLException {
        String sql = "SELECT * FROM restaurant";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Restaurant_id = " + resultSet.getInt(1) + ", Restaurant_name = "
                        + resultSet.getString(2) + ", Restaurant_Adress = " + resultSet.getString(3)
                        + ", Restaurant_phoneno = " + resultSet.getString(4) + ", Restaurant_rating = "
                        + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void diaplayCategories() throws SQLException {
        String sql = "SELECT * FROM category";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Category_id = " + resultSet.getInt("category_id") + ", Category_name = "
                        + resultSet.getString("category_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void displayItems() throws SQLException {
        String sql = "SELECT * FROM menuitem";
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        "item_id = " + resultSet.getInt(1) + ", Restaurant_id = " + resultSet.getInt(2)
                                + ", item_name = "
                                + resultSet.getString(3) + ", Price = " + resultSet.getInt(4)
                                + ", Category_id = " + resultSet.getInt(5) + ", Item_rating = "
                                + resultSet.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void changeCategoryData() throws SQLException {
        diaplayCategories();
        System.out.println(" Enter Category_id : ");
        int category_id = scanner.nextInt();
        System.out.println(" Enter Category_name : ");
        String category_name = scanner.next();
        System.out.println("DO you want to change data yes / no ");
        String q = scanner.nextLine();
        String query = "UPDATE Category SET Category_name = ? WHERE Category_id = ?";
        try {
            Connection connection = connection_database.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, category_name);
            preparedStatement1.setInt(2, category_id);
            int a1 = preparedStatement1.executeUpdate();
            if (a1 > 0) {
                System.out.println("Category Data Updated Successfully");
            } else {
                System.out.println("Category Data Not Updated");
            }
            if (q.equalsIgnoreCase("yes")) {
                connection.commit();
            }

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    static void changeRestaurantData() throws SQLException {
        displayRestaurants();
        System.out.println("Enter Restaurant_id : ");
        int Rest_id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter  Restaurant_name : ");
        String Rest_name = scanner.nextLine();
        System.out.println("Enter  Restaurant_address : ");
        String Rest_address = scanner.nextLine();
        System.out.println("Enter  Restaurant_phone : ");
        String Rest_phone = scanner.nextLine();
        System.out.println("Enter Restaurant_Rating : ");
        int Rest_Rating = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DO you want to change data yes / no ");
        String q = scanner.nextLine();
        String query = "update Restaurant set name=?,address = ?,phone_no = ?, rating= ? where Restaurant_id = ?";
        try {
            Connection connection = connection_database.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, Rest_name);
            preparedStatement1.setString(2, Rest_address);
            preparedStatement1.setString(3, Rest_phone);
            preparedStatement1.setInt(4, Rest_Rating);
            preparedStatement1.setInt(5, Rest_id);
            int a1 = preparedStatement1.executeUpdate();
            if (a1 > 0) {
                System.out.println("Restaurant Data Updated Successfully");
            } else {
                System.out.println("Restaurant Data Not Updated");
            }
            if (q.equalsIgnoreCase("yes")) {
                connection.commit();
            }

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void changeMenuItemData() throws SQLException {
        displayItems();
        System.out.println("Enter Item_id : ");
        int Menu_id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Item_name : ");
        String Menu_name = scanner.nextLine();
        System.out.println("Enter Item_price : ");
        double Menu_price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter item_Rating : ");
        int Menu_Rating = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DO you want to change data yes / no ");
        String q = scanner.nextLine();
        String query = "update Menuitem set item_name=?,price = ?,rating = ? where item_id = ?";
        try {
            Connection connection = connection_database.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, Menu_name);
            preparedStatement1.setDouble(2, Menu_price);
            preparedStatement1.setInt(3, Menu_Rating);
            preparedStatement1.setInt(4, Menu_id);
            int a1 = preparedStatement1.executeUpdate();
            if (a1 > 0) {
                System.out.println("Item Data Updated Successfully");
            } else {
                System.out.println("Item Data Not Updated");
            }
            if (q.equalsIgnoreCase("yes")) {
                connection.commit();
            }

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void ShowCustomerdetailsByCustomerId(int id) {
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer where  customer_id = ?");
            preparedStatement.setInt(1, id);
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

    static void ShowCustomerdetailsByCustomername(String nam) {
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer where name = ?");
            preparedStatement.setString(1, nam);
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

    static void ShowCustomerdetailsByCustomeremail(String mail) {
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer where  email = ?");
            preparedStatement.setString(1, mail);
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

    static void ShowCustomerdetailsByCustomerphone(String ph) {
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer where  phone = ?");
            preparedStatement.setString(1, ph);
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

    static void ShowCustomerdetailsByCustomeraddress(String addr) {
        try {
            Connection connection = connection_database.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM customer where  address = ?");
            preparedStatement.setString(1, addr);
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
}