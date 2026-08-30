import java.util.ArrayList;
import java.util.List;

/** A line item that can be placed in an Amazon-style shopping cart. */
interface AmazonCartItem {
    double getPrice();
    void showDetails(String indent);
}

/** A single purchasable product. */
class AmazonProduct implements AmazonCartItem {
    private final String name;
    private final double price;

    public AmazonProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void showDetails(String indent) {
        System.out.printf("%s- %s: ₹%.2f%n", indent, name, price);
    }
}

/** A bundle can contain individual products as well as other bundles. */
class AmazonProductBundle implements AmazonCartItem {
    private final String name;
    private final List<AmazonCartItem> items = new ArrayList<>();

    public AmazonProductBundle(String name) {
        this.name = name;
    }

    public void addItem(AmazonCartItem item) {
        items.add(item);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (AmazonCartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public void showDetails(String indent) {
        System.out.printf("%s- %s: ₹%.2f%n", indent, name, getPrice());
        for (AmazonCartItem item : items) {
            item.showDetails(indent + "  ");
        }
    }
}

/** The cart treats products and nested bundles as the same type. */
class AmazonShoppingCart {
    private final List<AmazonCartItem> items = new ArrayList<>();

    public void addItem(AmazonCartItem item) {
        items.add(item);
    }

    public void showCart() {
        double total = 0;
        System.out.println("Shopping cart:");
        for (AmazonCartItem item : items) {
            item.showDetails("");
            total += item.getPrice();
        }
        System.out.printf("Cart total: ₹%.2f%n", total);
    }
}

public class Composite {
    public static void main(String[] args) {
        AmazonProduct laptop = new AmazonProduct("Laptop", 65000.00);
        AmazonProduct mouse = new AmazonProduct("Wireless Mouse", 1200.00);
        AmazonProduct keyboard = new AmazonProduct("Mechanical Keyboard", 3500.00);
        AmazonProduct warranty = new AmazonProduct("Two-year Protection Plan", 4999.00);

        AmazonProductBundle accessories = new AmazonProductBundle("Accessories bundle");
        accessories.addItem(mouse);
        accessories.addItem(keyboard);

        AmazonProductBundle workstation = new AmazonProductBundle("Workstation bundle");
        workstation.addItem(laptop);
        workstation.addItem(accessories);

        AmazonShoppingCart cart = new AmazonShoppingCart();
        cart.addItem(workstation);
        cart.addItem(warranty);
        cart.showCart();
    }
}
