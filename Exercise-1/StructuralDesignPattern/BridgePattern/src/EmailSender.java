
public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String content) {
        System.out.println("Email sent: " + content);
    }
}
