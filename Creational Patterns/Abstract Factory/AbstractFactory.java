import java.util.Locale;

// Abstract products
interface AmazonPaymentGateway {
    void processPayment(double amount);
}

interface AmazonInvoice {
    void generateInvoice();
}

// India product family
class AmazonIndiaUPIGateway implements AmazonPaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment through UPI: " + amount);
    }
}

class AmazonIndiaCardGateway implements AmazonPaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment through card gateway: " + amount);
    }
}

class AmazonIndiaGSTInvoice implements AmazonInvoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating GST invoice for Amazon India.");
    }
}

// US product family
class AmazonUSPayPalGateway implements AmazonPaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing USD payment through PayPal: " + amount);
    }
}

class AmazonUSCardGateway implements AmazonPaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing USD payment through card gateway: " + amount);
    }
}

class AmazonUSInvoice implements AmazonInvoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating sales-tax invoice for Amazon US.");
    }
}

// Abstract factory
interface AmazonRegionFactory {
    AmazonPaymentGateway createPaymentGateway(String paymentMethod);
    AmazonInvoice createInvoice();
}

// Concrete factories
class AmazonIndiaFactory implements AmazonRegionFactory {
    @Override
    public AmazonPaymentGateway createPaymentGateway(String paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        switch (paymentMethod.toLowerCase(Locale.ROOT)) {
            case "upi":
                return new AmazonIndiaUPIGateway();
            case "card":
                return new AmazonIndiaCardGateway();
            default:
                throw new IllegalArgumentException(
                        "Unsupported payment method for India: " + paymentMethod);
        }
    }

    @Override
    public AmazonInvoice createInvoice() {
        return new AmazonIndiaGSTInvoice();
    }
}

class AmazonUSFactory implements AmazonRegionFactory {
    @Override
    public AmazonPaymentGateway createPaymentGateway(String paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        switch (paymentMethod.toLowerCase(Locale.ROOT)) {
            case "paypal":
                return new AmazonUSPayPalGateway();
            case "card":
                return new AmazonUSCardGateway();
            default:
                throw new IllegalArgumentException(
                        "Unsupported payment method for the US: " + paymentMethod);
        }
    }

    @Override
    public AmazonInvoice createInvoice() {
        return new AmazonUSInvoice();
    }
}

// Business logic depends only on abstract products and the abstract factory.
class AmazonCheckoutService {
    private final AmazonPaymentGateway paymentGateway;
    private final AmazonInvoice invoice;

    public AmazonCheckoutService(AmazonRegionFactory factory, String paymentMethod) {
        this.paymentGateway = factory.createPaymentGateway(paymentMethod);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        AmazonCheckoutService indiaCheckout =
                new AmazonCheckoutService(new AmazonIndiaFactory(), "upi");
        indiaCheckout.completeOrder(1999.00);

        System.out.println("---");

        AmazonCheckoutService usCheckout =
                new AmazonCheckoutService(new AmazonUSFactory(), "paypal");
        usCheckout.completeOrder(49.99);
    }
}
