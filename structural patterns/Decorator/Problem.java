/** A checkout service with hard-coded service combinations. */
class CheckoutService {
    public void placeBasicOrder(String orderId, double amount) {
        System.out.println("Placing basic order " + orderId + " for Rs. "
                + String.format("%.2f", amount));
    }

    public void placeGiftWrappedOrder(String orderId, double amount) {
        System.out.println("Placing gift-wrapped order " + orderId + " for Rs. "
                + String.format("%.2f", amount + 49));
    }

    public void placeProtectedOrder(String orderId, double amount) {
        System.out.println("Placing protected order " + orderId + " for Rs. "
                + String.format("%.2f", amount + 149));
    }

    public void placeGiftWrappedProtectedOrder(String orderId, double amount) {
        System.out.println("Placing gift-wrapped protected order " + orderId
                + " for Rs. " + String.format("%.2f", amount + 49 + 149));
    }
}

public class Problem {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService();

        checkoutService.placeBasicOrder("AMZ-2001", 1700);
        checkoutService.placeGiftWrappedOrder("AMZ-2002", 1700);
        checkoutService.placeProtectedOrder("AMZ-2003", 1700);
        checkoutService.placeGiftWrappedProtectedOrder("AMZ-2004", 1700);
    }
}
