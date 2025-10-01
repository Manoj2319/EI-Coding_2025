
import java.util.Scanner;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        DatabaseConnection db = new DatabaseConnection();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Connect to Database");
            System.out.println("2. Disconnect Database");
            System.out.println("3. Process a Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    db.connect();
                    break;
                case 2:
                    db.disconnect();
                    break;
                case 3:
                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine();
                    service.processTask(taskName);
                    break;
                case 4:
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
