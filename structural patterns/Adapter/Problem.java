/** A third-party courier library with an incompatible API. */
class DirectLegacyCourierApi {
    public void dispatchParcel(String trackingNumber, String destination) {
        System.out.println("Dispatching parcel " + trackingNumber
                + " through Legacy Courier to " + destination);
    }
}

/**
 * The order service is coupled to the third-party API and must translate its
 * own order data every time it uses that courier.
 */
class AmazonOrderShippingServiceWithoutAdapter {
    private final DirectLegacyCourierApi legacyCourierApi = new DirectLegacyCourierApi();

    public void shipWithLegacyCourier(String orderId, String deliveryAddress) {
        String trackingNumber = "AMZ-" + orderId;
        legacyCourierApi.dispatchParcel(trackingNumber, deliveryAddress);
    }
}

public class Problem {
    public static void main(String[] args) {
        AmazonOrderShippingServiceWithoutAdapter shippingService =
                new AmazonOrderShippingServiceWithoutAdapter();

        shippingService.shipWithLegacyCourier("1002", "Mumbai");
    }
}
