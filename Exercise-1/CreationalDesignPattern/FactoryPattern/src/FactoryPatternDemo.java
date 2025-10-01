
import java.util.Scanner;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotificationFactory factory = new NotificationFactory();

        System.out.println("Enter Notification Type (email/sms/push): ");
        String type = scanner.nextLine();

        System.out.println("Enter Message: ");
        String message = scanner.nextLine();

        try {
            Notification notification = factory.createNotification(type);
            notification.notifyUser(message);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
