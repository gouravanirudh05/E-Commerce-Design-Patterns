class ConfigurationManager {

    String databaseUrl;
    String paymentUrl;

    ConfigurationManager(String databaseUrl, String paymentUrl) {
        this.databaseUrl = databaseUrl;
        this.paymentUrl = paymentUrl;
    }

    void showConfig() {
        System.out.println("Database: " + databaseUrl);
        System.out.println("Payment: " + paymentUrl);
    }
}

public class Problem {
    public static void main(String[] args) {

        // Product Service creates its own configuration
        ConfigurationManager productConfig =
                new ConfigurationManager("ProductDB", "PaymentAPI");

        // Order Service creates another configuration
        ConfigurationManager orderConfig =
                new ConfigurationManager("OrderDB", "PaymentAPI");

        productConfig.showConfig();
        orderConfig.showConfig();

        // Two different objects exist
        System.out.println(productConfig == orderConfig);
    }
}
