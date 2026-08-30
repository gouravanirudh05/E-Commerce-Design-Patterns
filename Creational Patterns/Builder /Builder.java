final class Order {
    private final String userId;
    private final String productId;
    private final int quantity;
    private final String address;
    private final String paymentMethod;
    private final String coupon;
    private final boolean giftWrap;
    private final String deliveryInstructions;
    private final String deliveryOption;

    private Order(Builder builder) {
        this.userId = builder.userId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.address = builder.address;
        this.paymentMethod = builder.paymentMethod;
        this.coupon = builder.coupon;
        this.giftWrap = builder.giftWrap;
        this.deliveryInstructions = builder.deliveryInstructions;
        this.deliveryOption = builder.deliveryOption;
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

    static class Builder {
        private String userId;
        private String productId;
        private int quantity;
        private String address;
        private String paymentMethod;
        private String coupon;
        private boolean giftWrap;
        private String deliveryInstructions;
        private String deliveryOption = "Standard";

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setCoupon(String coupon) {
            this.coupon = coupon;
            return this;
        }

        public Builder setGiftWrap(boolean giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public Builder setDeliveryInstructions(String deliveryInstructions) {
            this.deliveryInstructions = deliveryInstructions;
            return this;
        }

        public Builder setDeliveryOption(String deliveryOption) {
            this.deliveryOption = deliveryOption;
            return this;
        }

        public Order build() {
            if (isBlank(userId) || isBlank(productId) || isBlank(address)
                    || isBlank(paymentMethod) || quantity <= 0) {
                throw new IllegalStateException(
                        "userId, productId, quantity, address, and paymentMethod are required");
            }

            return new Order(this);
        }

        private boolean isBlank(String value) {
            return value == null || value.trim().isEmpty();
        }
    }
}

public class Builder {
    public static void main(String[] args) {
        Order orderWithExtras = new Order.Builder()
                .setUserId("U101")
                .setProductId("P500")
                .setQuantity(2)
                .setAddress("Delhi")
                .setPaymentMethod("UPI")
                .setCoupon("AMAZON10")
                .setGiftWrap(true)
                .setDeliveryInstructions("Leave at the front desk")
                .setDeliveryOption("Prime One-Day")
                .build();

        orderWithExtras.showOrder();

        System.out.println("---");

        // Optional fields can be omitted safely.
        Order orderWithoutCoupon = new Order.Builder()
                .setUserId("U202")
                .setProductId("P900")
                .setQuantity(1)
                .setAddress("Mumbai")
                .setPaymentMethod("Card")
                .build();

        orderWithoutCoupon.showOrder();
    }
}
