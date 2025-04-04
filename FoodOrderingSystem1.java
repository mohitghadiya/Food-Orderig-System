
import java.util.*;

class FoodOrderingSystem1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int choice = 0;
        do {
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }

            switch (choice) {
                case 1:
                    Admin.adminMenu();
                    break;
                case 2:
                    Customer.customerMenu();
                    break;
                case 3:
                    System.out.println("Thank you for using the Food Ordering System.");
                    break;
            }
        } while (choice != 3);

    }
}