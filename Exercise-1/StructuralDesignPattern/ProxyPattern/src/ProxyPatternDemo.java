
import java.util.Scanner;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter image filename: ");
        String filename = scanner.nextLine();

        Image image = new ProxyImage(filename);

        System.out.println("\nFirst call to display():");
        image.display(); 

        System.out.println("\nSecond call to display():");
        image.display(); 
        scanner.close();
    }
}
