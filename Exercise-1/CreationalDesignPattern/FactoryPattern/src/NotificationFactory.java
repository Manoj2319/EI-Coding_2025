
public class NotificationFactory {
    public Notification createNotification(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Notification type cannot be null or empty");
        }

        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SMSNotification();
            case "push":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}
