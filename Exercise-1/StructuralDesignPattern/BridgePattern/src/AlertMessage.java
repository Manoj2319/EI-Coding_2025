
public class AlertMessage extends Message {
    public AlertMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String content) {
        sender.sendMessage("[ALERT] " + content);
    }
}
