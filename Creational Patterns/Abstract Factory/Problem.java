// Product interfaces
interface DirectAmazonPaymentGateway {
    void processPayment(double amount);
}

interface DirectAmazonInvoice {
    void generateInvoice();
}

// Concrete products
class DirectIndiaUPIGateway implements DirectAmazonPaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment through UPI: " + amount);
    }
}

class DirectUSPayPalGateway implements DirectAmazonPaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing USD payment through PayPal: " + amount);
    }
}

class DirectIndiaGSTInvoice implements DirectAmazonInvoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating GST invoice for Amazon India.");
    }
}

class DirectUSInvoice implements DirectAmazonInvoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating sales-tax invoice for Amazon US.");
    }
}

// Payment and invoice creation are hardcoded in the checkout service.
class AmazonCheckoutServiceWithoutAbstractFactory {
    private final String region;
    private final String paymentMethod;

    public AmazonCheckoutServiceWithoutAbstractFactory(
            String region, String paymentMethod) {
        this.region = region;
        this.paymentMethod = paymentMethod;
    }

    public void completeOrder(double amount) {
        DirectAmazonPaymentGateway paymentGateway;
        DirectAmazonInvoice invoice;

        if ("india".equalsIgnoreCase(region)
                && "upi".equalsIgnoreCase(paymentMethod)) {
            paymentGateway = new DirectIndiaUPIGateway();
            invoice = new DirectIndiaGSTInvoice();
        } else if ("us".equalsIgnoreCase(region)
                && "paypal".equalsIgnoreCase(paymentMethod)) {
            paymentGateway = new DirectUSPayPalGateway();
            invoice = new DirectUSInvoice();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported region and payment combination");
        }

        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

public class Problem {
    public static void main(String[] args) {
        AmazonCheckoutServiceWithoutAbstractFactory indiaCheckout =
                new AmazonCheckoutServiceWithoutAbstractFactory("India", "UPI");
        indiaCheckout.completeOrder(1999.00);

        System.out.println("---");

        AmazonCheckoutServiceWithoutAbstractFactory usCheckout =
                new AmazonCheckoutServiceWithoutAbstractFactory("US", "PayPal");
        usCheckout.completeOrder(49.99);
    }
}
