
public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String content) {
        System.out.println("SMS sent: " + content);
    }
}
