/** A product in a cart without a composite structure. */
class DirectCartProduct {
    private final String name;
    private final double price;

    public DirectCartProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

/** Bundles need special logic and cannot contain other bundles. */
class AmazonCartWithoutComposite {
    public void showWorkstationBundle(
            DirectCartProduct laptop,
            DirectCartProduct mouse,
            DirectCartProduct keyboard) {
        double bundleTotal = laptop.getPrice() + mouse.getPrice() + keyboard.getPrice();
        System.out.println("Workstation bundle:");
        System.out.println("- " + laptop.getName());
        System.out.println("- " + mouse.getName());
        System.out.println("- " + keyboard.getName());
        System.out.printf("Bundle total: ₹%.2f%n", bundleTotal);
    }
}

public class Problem {
    public static void main(String[] args) {
        DirectCartProduct laptop = new DirectCartProduct("Laptop", 65000.00);
        DirectCartProduct mouse = new DirectCartProduct("Wireless Mouse", 1200.00);
        DirectCartProduct keyboard = new DirectCartProduct("Mechanical Keyboard", 3500.00);

        new AmazonCartWithoutComposite().showWorkstationBundle(laptop, mouse, keyboard);
    }
}
