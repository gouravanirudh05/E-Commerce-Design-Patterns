/** Represents a slow remote catalog request. */
class DirectRemoteAmazonProductDetails {
    private final String productId;

    public DirectRemoteAmazonProductDetails(String productId) {
        this.productId = productId;
        System.out.println("Loading product " + productId + " from the catalog service...");
    }

    public void showDetails() {
        System.out.println("Product " + productId
                + ": Noise-cancelling headphones, ₹4,999.00");
    }
}

/** All product details are loaded immediately, even when shoppers never open them. */
class AmazonSearchResultsWithoutProxy {
    public DirectRemoteAmazonProductDetails addSearchResult(String productId) {
        return new DirectRemoteAmazonProductDetails(productId);
    }
}

public class Problem {
    public static void main(String[] args) {
        AmazonSearchResultsWithoutProxy searchResults =
                new AmazonSearchResultsWithoutProxy();

        DirectRemoteAmazonProductDetails headphones = searchResults.addSearchResult("P-500");
        DirectRemoteAmazonProductDetails keyboard = searchResults.addSearchResult("P-600");

        // The shopper opens only one result, but both remote objects were loaded.
        headphones.showDetails();
    }
}
