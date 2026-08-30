final class OrderWithoutBuilder {
    private final String userId;
    private final String productId;
    private final int quantity;
    private final String address;
    private final String paymentMethod;
    private final String coupon;
    private final boolean giftWrap;
    private final String deliveryInstructions;
    private final String deliveryOption;

    // A long constructor makes optional values and parameter order difficult to remember.
    public OrderWithoutBuilder(
            String userId,
            String productId,
            int quantity,
            String address,
            String paymentMethod,
            String coupon,
            boolean giftWrap,
            String deliveryInstructions,
            String deliveryOption) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.coupon = coupon;
        this.giftWrap = giftWrap;
        this.deliveryInstructions = deliveryInstructions;
        this.deliveryOption = deliveryOption;
    }

    public void showOrder() {
        System.out.println("Order for user: " + userId);
        System.out.println("Product: " + productId + ", Quantity: " + quantity);
        System.out.println("Delivery address: " + address);
        System.out.println("Payment method: " + paymentMethod);
        System.out.println("Coupon: " + (coupon == null ? "None" : coupon));
        System.out.println("Gift wrap: " + giftWrap);
        System.out.println("Delivery instructions: "
                + (deliveryInstructions == null ? "None" : deliveryInstructions));
        System.out.println("Delivery option: " + deliveryOption);
    }
}

public class Problem {
    public static void main(String[] args) {
        OrderWithoutBuilder order = new OrderWithoutBuilder(
                "U101",
                "P500",
                2,
                "Delhi",
                "UPI",
                "AMAZON10",
                true,
                "Leave at the front desk",
                "Prime One-Day");

        order.showOrder();
    }
}
