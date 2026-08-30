/** Product details that a catalog page can display. */
interface AmazonProductDetails {
    void showDetails();
}

/** Represents a slow remote catalog request. */
class RemoteAmazonProductDetails implements AmazonProductDetails {
    private final String productId;

    public RemoteAmazonProductDetails(String productId) {
        this.productId = productId;
        System.out.println("Loading product " + productId + " from the catalog service...");
    }

    @Override
    public void showDetails() {
        System.out.println("Product " + productId
                + ": Noise-cancelling headphones, ₹4,999.00");
    }
}

/**
 * A virtual proxy that delays the remote catalog call until product details
 * are actually requested, then reuses the loaded object.
 */
class AmazonProductDetailsProxy implements AmazonProductDetails {
    private final String productId;
    private RemoteAmazonProductDetails realProductDetails;

    public AmazonProductDetailsProxy(String productId) {
        this.productId = productId;
    }

    @Override
    public void showDetails() {
        if (realProductDetails == null) {
            realProductDetails = new RemoteAmazonProductDetails(productId);
        }
        realProductDetails.showDetails();
    }
}

/** The catalog page depends on the common interface, not on remote loading. */
class AmazonCatalogPage {
    public void openProductPage(AmazonProductDetails productDetails) {
        productDetails.showDetails();
    }
}

public class Proxy {
    public static void main(String[] args) {
        AmazonProductDetails headphones = new AmazonProductDetailsProxy("P-500");
        AmazonCatalogPage catalogPage = new AmazonCatalogPage();

        System.out.println("Search results created; product details are not loaded yet.");
        catalogPage.openProductPage(headphones);

        System.out.println("---");
        System.out.println("Opening the same product again:");
        catalogPage.openProductPage(headphones);
    }
}
