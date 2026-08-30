import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Product listing created repeatedly without using a prototype. */
final class ProductListingWithoutPrototype {
    private final String name;
    private final String color;
    private final String storage;
    private final double price;
    private final String description;
    private final List<String> images;
    private final Map<String, String> specifications;

    public ProductListingWithoutPrototype(
            String name,
            String color,
            String storage,
            double price,
            String description,
            List<String> images,
            Map<String, String> specifications) {
        this.name = name;
        this.color = color;
        this.storage = storage;
        this.price = price;
        this.description = description;
        this.images = images;
        this.specifications = specifications;
    }

    public void showDetails() {
        System.out.println(name + " | " + color + " | " + storage
                + " | ₹" + String.format("%.2f", price));
        System.out.println(description);
        System.out.println("Images: " + images);
        System.out.println("Specifications: " + specifications);
    }
}

public class Problem {
    public static void main(String[] args) {
        List<String> images = Arrays.asList("front-black.jpg", "back-black.jpg");
        Map<String, String> specifications = new LinkedHashMap<>();
        specifications.put("Display", "6.3 inch");
        specifications.put("Battery", "4000 mAh");

        // All common information must be provided again for the blue variant.
        ProductListingWithoutPrototype blackPhone = new ProductListingWithoutPrototype(
                "iPhone 17", "Black", "128 GB", 70000.00,
                "Latest generation smartphone", images, specifications);

        ProductListingWithoutPrototype bluePhone = new ProductListingWithoutPrototype(
                "iPhone 17", "Blue", "128 GB", 70000.00,
                "Latest generation smartphone", images, specifications);

        System.out.println("Black listing:");
        blackPhone.showDetails();
        System.out.println("---");
        System.out.println("Blue listing:");
        bluePhone.showDetails();
    }
}
