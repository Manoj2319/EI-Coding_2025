
public class WebApp implements Observer {
    private String siteName;

    public WebApp(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public void update(float temperature) {
        System.out.println(siteName + " Web App updated: Temperature is " + temperature + "°C");
    }
}
