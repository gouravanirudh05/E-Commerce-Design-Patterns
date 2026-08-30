class ConfigurationManager {

    private static ConfigurationManager instance;

    private String databaseUrl;
    private String paymentUrl;

    // Private constructor
    private ConfigurationManager() {
        databaseUrl = "AmazonDB";
        paymentUrl = "PaymentAPI";
    }

    // Global access point
    public static ConfigurationManager getInstance() {

        if (instance == null) {
            instance = new ConfigurationManager();
        }

        return instance;
    }

    void showConfig() {
        System.out.println("Database: " + databaseUrl);
        System.out.println("Payment: " + paymentUrl);
    }
}

public class Singleton {
    public static void main(String[] args) {

        // Product Service
        ConfigurationManager productConfig =
                ConfigurationManager.getInstance();

        // Order Service
        ConfigurationManager orderConfig =
                ConfigurationManager.getInstance();

        productConfig.showConfig();
        orderConfig.showConfig();

        // Both references point to the same object
        System.out.println(productConfig == orderConfig);
    }
}
