/** Simple inventory subsystem. */
class InventoryService {
    public void reserveItems(String orderId) {
        System.out.println("Inventory reserved for " + orderId);
    }
}

/** Simple payment subsystem. */
class PaymentService {
    public void capturePayment(String orderId) {
        System.out.println("Payment captured for " + orderId);
    }
}

/** Simple shipping subsystem. */
class ShippingService {
    public void createShipment(String orderId) {
        System.out.println("Shipment created for " + orderId);
    }
}

/** Simple notification subsystem. */
class NotificationService {
    public void sendOrderConfirmation(String orderId) {
        System.out.println("Order confirmation sent for " + orderId);
    }
}

/** Facade that hides the full checkout workflow behind one method. */
class AmazonCheckoutFacade {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public AmazonCheckoutFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }

    public void placeOrder(String orderId) {
        System.out.println("Starting checkout for " + orderId);
        inventoryService.reserveItems(orderId);
        paymentService.capturePayment(orderId);
        shippingService.createShipment(orderId);
        notificationService.sendOrderConfirmation(orderId);
        System.out.println("Checkout completed for " + orderId);
    }
}

public class Facade {
    public static void main(String[] args) {
        AmazonCheckoutFacade checkoutFacade = new AmazonCheckoutFacade();
        checkoutFacade.placeOrder("AMZ-3001");
    }
}
