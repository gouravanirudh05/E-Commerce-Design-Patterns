import java.util.Locale;

/** Common contract for an Amazon-style order. */
interface AmazonOrder {
    String getOrderId();

    double getTotal();

    void printSummary();
}

/** Core order without add-ons. */
class BaseOrder implements AmazonOrder {
    private final String orderId;
    private final double total;

    public BaseOrder(String orderId, double total) {
        this.orderId = orderId;
        this.total = total;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public void printSummary() {
        System.out.println("Base order: " + orderId);
    }
}

/** Base class for order add-ons. */
abstract class OrderDecorator implements AmazonOrder {
    protected final AmazonOrder wrappedOrder;

    protected OrderDecorator(AmazonOrder wrappedOrder) {
        this.wrappedOrder = wrappedOrder;
    }

    @Override
    public String getOrderId() {
        return wrappedOrder.getOrderId();
    }
}

class GiftWrapDecorator extends OrderDecorator {
    public GiftWrapDecorator(AmazonOrder wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public double getTotal() {
        return wrappedOrder.getTotal() + 49;
    }

    @Override
    public void printSummary() {
        wrappedOrder.printSummary();
        System.out.println("Adding gift wrap for " + getOrderId());
    }
}

class ProtectionPlanDecorator extends OrderDecorator {
    public ProtectionPlanDecorator(AmazonOrder wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public double getTotal() {
        return wrappedOrder.getTotal() + 149;
    }

    @Override
    public void printSummary() {
        wrappedOrder.printSummary();
        System.out.println("Adding protection plan for " + getOrderId());
    }
}

class ExpressDeliveryDecorator extends OrderDecorator {
    public ExpressDeliveryDecorator(AmazonOrder wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public double getTotal() {
        return wrappedOrder.getTotal() + 99;
    }

    @Override
    public void printSummary() {
        wrappedOrder.printSummary();
        System.out.println("Adding express delivery for " + getOrderId());
    }
}

class AmazonCheckoutFormatter {
    public void printFinalBill(AmazonOrder order) {
        order.printSummary();
        System.out.println("Final order total for " + order.getOrderId()
                + ": Rs. " + String.format(Locale.ROOT, "%.2f", order.getTotal()));
    }
}

public class Decorator {
    public static void main(String[] args) {
        AmazonOrder order = new BaseOrder("AMZ-2001", 1602);
        order = new GiftWrapDecorator(order);
        order = new ProtectionPlanDecorator(order);
        order = new ExpressDeliveryDecorator(order);

        AmazonCheckoutFormatter formatter = new AmazonCheckoutFormatter();
        formatter.printFinalBill(order);
    }
}
