/** Implementation side of the bridge: how a notification is delivered. */
interface AmazonNotificationChannel {
    void send(String recipient, String message);
}

class EmailNotificationChannel implements AmazonNotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Email to " + recipient + ": " + message);
    }
}

class SmsNotificationChannel implements AmazonNotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS to " + recipient + ": " + message);
    }
}

/** Abstraction side of the bridge: which order event is being communicated. */
abstract class AmazonOrderNotification {
    protected final AmazonNotificationChannel channel;

    protected AmazonOrderNotification(AmazonNotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void notifyCustomer(String recipient, String orderId);
}

class OrderPlacedNotification extends AmazonOrderNotification {
    public OrderPlacedNotification(AmazonNotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyCustomer(String recipient, String orderId) {
        channel.send(recipient, "Your order " + orderId + " has been placed.");
    }
}

class OrderShippedNotification extends AmazonOrderNotification {
    public OrderShippedNotification(AmazonNotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyCustomer(String recipient, String orderId) {
        channel.send(recipient, "Your order " + orderId + " has been shipped.");
    }
}

public class Bridge {
    public static void main(String[] args) {
        AmazonOrderNotification placedByEmail =
                new OrderPlacedNotification(new EmailNotificationChannel());
        AmazonOrderNotification shippedBySms =
                new OrderShippedNotification(new SmsNotificationChannel());

        placedByEmail.notifyCustomer("customer@example.com", "AMZ-4001");
        shippedBySms.notifyCustomer("+91-9876543210", "AMZ-4001");
    }
}
