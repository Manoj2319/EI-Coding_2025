
import java.util.Scanner;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer mobileApp = new MobileApp("WeatherPro");
        Observer webApp = new WebApp("WeatherNow");
        Observer tvDisplay = new TVDisplay();

        station.addObserver(mobileApp);
        station.addObserver(webApp);
        station.addObserver(tvDisplay);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter new temperature (or 'exit' to quit): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            try {
                float temp = Float.parseFloat(input);
                station.setTemperature(temp);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'exit'.");
            }
        }
        scanner.close();
    }
}
