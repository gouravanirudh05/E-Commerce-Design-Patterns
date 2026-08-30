import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** A product listing that can be copied to create a new product variant. */
class ProductListing implements Cloneable {
    private String name;
    private String color;
    private String storage;
    private double price;
    private String description;
    private List<String> images;
    private Map<String, String> specifications;

    public ProductListing(
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
        this.images = new ArrayList<>(images);
        this.specifications = new LinkedHashMap<>(specifications);
    }

    @Override
    public ProductListing clone() {
        try {
            ProductListing copy = (ProductListing) super.clone();

            // Deep-copy mutable fields so variants do not share collections.
            copy.images = new ArrayList<>(images);
            copy.specifications = new LinkedHashMap<>(specifications);
            return copy;
        } catch (CloneNotSupportedException exception) {
            throw new AssertionError("ProductListing should be cloneable", exception);
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addImage(String image) {
        images.add(image);
    }

    public void showDetails() {
        System.out.println("Product: " + name);
        System.out.println("Color: " + color);
        System.out.println("Storage: " + storage);
        System.out.println("Price: ₹" + String.format("%.2f", price));
        System.out.println("Description: " + description);
        System.out.println("Images: " + images);
        System.out.println("Specifications: " + specifications);
    }
}

public class Prototype {
    public static void main(String[] args) {
        List<String> images = List.of("front-black.jpg", "back-black.jpg");
        Map<String, String> specifications = new LinkedHashMap<>();
        specifications.put("Display", "6.3 inch");
        specifications.put("Battery", "4000 mAh");

        ProductListing blackPhone = new ProductListing(
                "iPhone 17",
                "Black",
                "128 GB",
                70000.00,
                "Latest generation smartphone",
                images,
                specifications);

        // Most data is reused; only variant-specific values are changed.
        ProductListing bluePhone = blackPhone.clone();
        bluePhone.setColor("Blue");
        bluePhone.addImage("front-blue.jpg");

        System.out.println("Original listing:");
        blackPhone.showDetails();

        System.out.println("---");

        System.out.println("Cloned product variant:");
        bluePhone.showDetails();

        System.out.println("---");
        System.out.println("Original and cloned objects are different: "
                + (blackPhone != bluePhone));
    }
}
