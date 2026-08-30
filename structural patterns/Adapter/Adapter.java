/** The shipping contract used by the Amazon-style order service. */
interface AmazonShippingProvider {
    void ship(String orderId, String deliveryAddress);
}

/** A shipping provider that already follows the application's contract. */
class AmazonInHouseDelivery implements AmazonShippingProvider {
    @Override
    public void ship(String orderId, String deliveryAddress) {
        System.out.println("Shipping order " + orderId
                + " with Amazon Delivery to " + deliveryAddress);
    }
}

/**
 * Simulates a third-party courier library. Its method and parameter names
 * cannot be changed by the e-commerce application.
 */
class LegacyCourierApi {
    public void dispatchParcel(String trackingNumber, String destination) {
        System.out.println("Dispatching parcel " + trackingNumber
                + " through Legacy Courier to " + destination);
    }
}

/** Adapts the third-party courier API to the application's shipping contract. */
class LegacyCourierAdapter implements AmazonShippingProvider {
    private final LegacyCourierApi legacyCourierApi;

    public LegacyCourierAdapter(LegacyCourierApi legacyCourierApi) {
        this.legacyCourierApi = legacyCourierApi;
    }

    @Override
    public void ship(String orderId, String deliveryAddress) {
        String trackingNumber = "AMZ-" + orderId;
        legacyCourierApi.dispatchParcel(trackingNumber, deliveryAddress);
    }
}

/** Business logic works only with the application's shipping interface. */
class AmazonOrderShippingService {
    public void shipOrder(
            String orderId, String deliveryAddress, AmazonShippingProvider provider) {
        provider.ship(orderId, deliveryAddress);
    }
}

public class Adapter {
    public static void main(String[] args) {
        AmazonOrderShippingService shippingService = new AmazonOrderShippingService();

        shippingService.shipOrder(
                "1001", "Bengaluru", new AmazonInHouseDelivery());

        AmazonShippingProvider legacyCourier =
                new LegacyCourierAdapter(new LegacyCourierApi());
        shippingService.shipOrder("1002", "Mumbai", legacyCourier);
    }
}
