import java.util.Scanner;

public class SupportSystem {
    public static void main(String[] args) {

        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler manager = new ManagerSupport();

        level1.setNextHandler(level2);
        level2.setNextHandler(manager);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your issue (e.g., 'password reset', 'software installation', 'network outage'):");
        String issue = scanner.nextLine();

        level1.handleRequest(issue);
        scanner.close();
    }
}
