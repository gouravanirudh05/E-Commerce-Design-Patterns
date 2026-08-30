/** A delivery option used by the tightly coupled example. */
interface DirectShippingMethod {
    void ship(String orderId);
}

class DirectRoadShipping implements DirectShippingMethod {
    @Override
    public void ship(String orderId) {
        System.out.println("Shipping order " + orderId + " by road");
    }
}

class DirectAirShipping implements DirectShippingMethod {
    @Override
    public void ship(String orderId) {
        System.out.println("Shipping order " + orderId + " by air");
    }
}

/** Object creation and order-processing logic are mixed together. */
class AmazonOrderServiceWithoutFactory {
    public void shipOrder(String orderId, String mode) {
        if ("air".equalsIgnoreCase(mode)) {
            DirectShippingMethod shippingMethod = new DirectAirShipping();
            shippingMethod.ship(orderId);
        } else if ("road".equalsIgnoreCase(mode)) {
            DirectShippingMethod shippingMethod = new DirectRoadShipping();
            shippingMethod.ship(orderId);
        } else {
            throw new IllegalArgumentException("Unknown shipping mode: " + mode);
        }
    }
}

public class Problem {
    public static void main(String[] args) {
        AmazonOrderServiceWithoutFactory orderService =
                new AmazonOrderServiceWithoutFactory();

        orderService.shipOrder("AMZ-1001", "Air");
        orderService.shipOrder("AMZ-1002", "Road");
    }
}
