/** Separate classes are needed for every notification-type and channel combination. */
class OrderPlacedEmailNotification {
    public void send(String email, String orderId) {
        System.out.println("Email to " + email + ": Your order " + orderId + " has been placed.");
    }
}

class OrderPlacedSmsNotification {
    public void send(String phoneNumber, String orderId) {
        System.out.println("SMS to " + phoneNumber + ": Your order " + orderId + " has been placed.");
    }
}

class OrderShippedEmailNotification {
    public void send(String email, String orderId) {
        System.out.println("Email to " + email + ": Your order " + orderId + " has been shipped.");
    }
}

public class Problem {
    public static void main(String[] args) {
        new OrderPlacedEmailNotification().send("customer@example.com", "AMZ-4001");
        new OrderPlacedSmsNotification().send("+91-9876543210", "AMZ-4001");
    }
}
