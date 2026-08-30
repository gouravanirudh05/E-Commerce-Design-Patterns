import java.util.Locale;

/** A delivery option that can be used to ship an Amazon-style order. */
interface AmazonShippingMethod {
    void ship(String orderId);
}

class AmazonRoadShipping implements AmazonShippingMethod {
    @Override
    public void ship(String orderId) {
        System.out.println("Shipping order " + orderId + " by road");
    }
}

class AmazonAirShipping implements AmazonShippingMethod {
    @Override
    public void ship(String orderId) {
        System.out.println("Shipping order " + orderId + " by air");
    }
}

/** Creates the appropriate shipping implementation for an order. */
class AmazonShippingFactory {
    public static AmazonShippingMethod getShippingMethod(String mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Shipping mode cannot be null");
        }

        switch (mode.toLowerCase(Locale.ROOT)) {
            case "air":
                return new AmazonAirShipping();
            case "road":
                return new AmazonRoadShipping();
            default:
                throw new IllegalArgumentException("Unknown shipping mode: " + mode);
        }
    }
}

/** Business logic depends on the interface, not on concrete shipping classes. */
class AmazonOrderService {
    public void shipOrder(String orderId, String mode) {
        AmazonShippingMethod shippingMethod =
                AmazonShippingFactory.getShippingMethod(mode);
        shippingMethod.ship(orderId);
    }
}

public class Factory {
    public static void main(String[] args) {
        AmazonOrderService orderService = new AmazonOrderService();

        orderService.shipOrder("AMZ-1001", "Air");
        orderService.shipOrder("AMZ-1002", "Road");
    }
}
