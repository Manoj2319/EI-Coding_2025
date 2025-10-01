import java.util.Scanner;
public class BridgePatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        do {
            System.out.println("\n--- Bridge Pattern Demo ---");
            System.out.println("1. Send Text Message");
            System.out.println("2. Send Alert Message");
            System.out.println("3. Exit");
            System.out.print("Choose message type: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("3") || choice.equalsIgnoreCase("exit")) { running = false; }
            else {
                System.out.print("Choose sender (email/sms): ");
                String senderType = scanner.nextLine().trim().toLowerCase();
                MessageSender sender;
                if (senderType.equals("sms")) sender = new SmsSender();
                else sender = new EmailSender();

                System.out.print("Enter message content: ");
                String content = scanner.nextLine();

                Message message;
                if (choice.equals("1")) message = new TextMessage(sender);
                else message = new AlertMessage(sender);

                message.send(content);
            }
        } while (running);
        scanner.close();
    }
}
