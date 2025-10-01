
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(float temperature) {
        System.out.println(appName + " Mobile App updated: Temperature is " + temperature + "°C");
    }
}
