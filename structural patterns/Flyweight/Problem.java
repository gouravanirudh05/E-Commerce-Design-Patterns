/** Stores the same large product metadata separately for every seller listing. */
class ProductListingWithoutFlyweight {
    private final String sellerId;
    private final double price;
    private final String productName;
    private final String category;
    private final String imageUrl;

    public ProductListingWithoutFlyweight(
            String sellerId, double price, String productName, String category, String imageUrl) {
        this.sellerId = sellerId;
        this.price = price;
        this.productName = productName;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public void display() {
        System.out.println(sellerId + " lists " + productName + " (" + category
                + ", " + imageUrl + ") for \u20b9" + String.format("%.2f", price));
    }
}

public class Problem {
    public static void main(String[] args) {
        ProductListingWithoutFlyweight firstListing = new ProductListingWithoutFlyweight(
                "Seller-A", 4999.00, "Noise-cancelling headphones", "Electronics", "headphones.jpg");
        ProductListingWithoutFlyweight secondListing = new ProductListingWithoutFlyweight(
                "Seller-B", 4799.00, "Noise-cancelling headphones", "Electronics", "headphones.jpg");

        firstListing.display();
        secondListing.display();
    }
}
