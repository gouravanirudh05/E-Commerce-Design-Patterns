/** Client code that talks to every checkout subsystem directly. */
class CheckoutClient {
    public void checkout(String orderId) {
        InventorySubsystem inventorySubsystem = new InventorySubsystem();
        PaymentSubsystem paymentSubsystem = new PaymentSubsystem();
        ShippingSubsystem shippingSubsystem = new ShippingSubsystem();
        NotificationSubsystem notificationSubsystem = new NotificationSubsystem();

        System.out.println("Starting checkout for " + orderId);
        inventorySubsystem.reserve(orderId);
        paymentSubsystem.capture(orderId);
        shippingSubsystem.createShipment(orderId);
        notificationSubsystem.sendConfirmation(orderId);
        System.out.println("Checkout completed for " + orderId);
    }
}

class InventorySubsystem {
    public void reserve(String orderId) {
        System.out.println("Inventory reserved for " + orderId);
    }
}

class PaymentSubsystem {
    public void capture(String orderId) {
        System.out.println("Payment captured for " + orderId);
    }
}

class ShippingSubsystem {
    public void createShipment(String orderId) {
        System.out.println("Shipment created for " + orderId);
    }
}

class NotificationSubsystem {
    public void sendConfirmation(String orderId) {
        System.out.println("Order confirmation sent for " + orderId);
    }
}

public class Problem {
    public static void main(String[] args) {
        CheckoutClient checkoutClient = new CheckoutClient();
        checkoutClient.checkout("AMZ-3001");
    }
}
