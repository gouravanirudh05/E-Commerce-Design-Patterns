import java.util.Arrays;
import java.util.List;

// Element interface
interface Product {
    void accept(ProductVisitor visitor);
}

// Concrete elements
class Book implements Product {
    private final String name;
    private final double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

class Electronics implements Product {
    private final String name;
    private final double price;
    private final double weightInKg;

    public Electronics(String name, double price, double weightInKg) {
        this.name = name;
        this.price = price;
        this.weightInKg = weightInKg;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

class Clothing implements Product {
    private final String name;
    private final double price;

    public Clothing(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

// Visitor interface
interface ProductVisitor {
    void visit(Book book);
    void visit(Electronics electronics);
    void visit(Clothing clothing);
}

// Concrete visitor: calculates marketplace tax for each product type.
class TaxVisitor implements ProductVisitor {
    @Override
    public void visit(Book book) {
        System.out.println(book.getName() + " GST: ₹" + book.getPrice() * 0.05);
    }

    @Override
    public void visit(Electronics electronics) {
        System.out.println(electronics.getName() + " GST: ₹"
                + electronics.getPrice() * 0.18);
    }

    @Override
    public void visit(Clothing clothing) {
        System.out.println(clothing.getName() + " GST: ₹"
                + clothing.getPrice() * 0.12);
    }
}

// Concrete visitor: calculates delivery charges for each product type.
class ShippingVisitor implements ProductVisitor {
    @Override
    public void visit(Book book) {
        System.out.println(book.getName() + " shipping: ₹40.0");
    }

    @Override
    public void visit(Electronics electronics) {
        double shippingCost = 80 + (electronics.getWeightInKg() * 20);
        System.out.println(electronics.getName() + " shipping: ₹" + shippingCost);
    }

    @Override
    public void visit(Clothing clothing) {
        System.out.println(clothing.getName() + " shipping: ₹60.0");
    }
}

// Concrete visitor: applies category-specific discounts.
class DiscountVisitor implements ProductVisitor {
    @Override
    public void visit(Book book) {
        System.out.println(book.getName() + " discount: ₹" + book.getPrice() * 0.10);
    }

    @Override
    public void visit(Electronics electronics) {
        System.out.println(electronics.getName() + " discount: ₹"
                + electronics.getPrice() * 0.05);
    }

    @Override
    public void visit(Clothing clothing) {
        System.out.println(clothing.getName() + " discount: ₹"
                + clothing.getPrice() * 0.15);
    }
}

public class Visitor {
    public static void main(String[] args) {
        List<Product> catalog = Arrays.asList(
                new Book("Java Design Patterns", 800.00),
                new Electronics("Wireless Headphones", 5000.00, 0.5),
                new Clothing("Amazon Essentials Jacket", 2000.00));

        System.out.println("Tax calculation:");
        ProductVisitor taxVisitor = new TaxVisitor();
        for (Product product : catalog) {
            product.accept(taxVisitor);
        }

        System.out.println("---");
        System.out.println("Shipping calculation:");
        ProductVisitor shippingVisitor = new ShippingVisitor();
        for (Product product : catalog) {
            product.accept(shippingVisitor);
        }

        System.out.println("---");
        System.out.println("Discount calculation:");
        ProductVisitor discountVisitor = new DiscountVisitor();
        for (Product product : catalog) {
            product.accept(discountVisitor);
        }
    }
}
