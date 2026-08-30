import java.util.HashMap;
import java.util.Map;

/** Shared, immutable product metadata. */
class AmazonProductType {
    private final String productName;
    private final String category;
    private final String imageUrl;

    public AmazonProductType(String productName, String category, String imageUrl) {
        this.productName = productName;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public void display(String sellerId, double price) {
        System.out.println(sellerId + " lists " + productName + " (" + category
                + ", " + imageUrl + ") for \u20b9" + String.format("%.2f", price));
    }
}

/** Creates and reuses product types instead of duplicating their common data. */
class AmazonProductTypeFactory {
    private final Map<String, AmazonProductType> productTypes = new HashMap<>();

    public AmazonProductType getProductType(String productName, String category, String imageUrl) {
        String key = productName + "|" + category + "|" + imageUrl;
        if (!productTypes.containsKey(key)) {
            productTypes.put(key, new AmazonProductType(productName, category, imageUrl));
            System.out.println("Creating shared product type for " + productName);
        }
        return productTypes.get(key);
    }

    public int getCreatedProductTypeCount() {
        return productTypes.size();
    }
}

/** A seller-specific listing stores only changing, per-listing information. */
class AmazonSellerListing {
    private final String sellerId;
    private final double price;
    private final AmazonProductType productType;

    public AmazonSellerListing(String sellerId, double price, AmazonProductType productType) {
        this.sellerId = sellerId;
        this.price = price;
        this.productType = productType;
    }

    public void display() {
        productType.display(sellerId, price);
    }
}

public class Flyweight {
    public static void main(String[] args) {
        AmazonProductTypeFactory factory = new AmazonProductTypeFactory();

        AmazonProductType headphones = factory.getProductType(
                "Noise-cancelling headphones", "Electronics", "headphones.jpg");
        AmazonSellerListing firstListing = new AmazonSellerListing("Seller-A", 4999.00, headphones);
        AmazonSellerListing secondListing = new AmazonSellerListing("Seller-B", 4799.00,
                factory.getProductType("Noise-cancelling headphones", "Electronics", "headphones.jpg"));

        firstListing.display();
        secondListing.display();
        System.out.println("Shared product types created: " + factory.getCreatedProductTypeCount());
    }
}
