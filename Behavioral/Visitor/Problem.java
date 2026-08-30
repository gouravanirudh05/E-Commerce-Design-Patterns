/** Product classes that contain every operation directly. */
class DirectBook {
    private final String name;
    private final double price;

    public DirectBook(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void showCharges() {
        System.out.println(name + " | Tax: ₹" + price * 0.05
                + " | Shipping: ₹40.0 | Discount: ₹" + price * 0.10);
    }
}

class DirectElectronics {
    private final String name;
    private final double price;
    private final double weightInKg;

    public DirectElectronics(String name, double price, double weightInKg) {
        this.name = name;
        this.price = price;
        this.weightInKg = weightInKg;
    }

    public void showCharges() {
        double shippingCost = 80 + (weightInKg * 20);
        System.out.println(name + " | Tax: ₹" + price * 0.18
                + " | Shipping: ₹" + shippingCost
                + " | Discount: ₹" + price * 0.05);
    }
}

class DirectClothing {
    private final String name;
    private final double price;

    public DirectClothing(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void showCharges() {
        System.out.println(name + " | Tax: ₹" + price * 0.12
                + " | Shipping: ₹60.0 | Discount: ₹" + price * 0.15);
    }
}

public class Problem {
    public static void main(String[] args) {
        DirectBook book = new DirectBook("Java Design Patterns", 800.00);
        DirectElectronics headphones =
                new DirectElectronics("Wireless Headphones", 5000.00, 0.5);
        DirectClothing jacket = new DirectClothing("Amazon Essentials Jacket", 2000.00);

        book.showCharges();
        headphones.showCharges();
        jacket.showCharges();
    }
}
