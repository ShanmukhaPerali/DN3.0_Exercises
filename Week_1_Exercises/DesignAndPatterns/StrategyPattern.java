interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public CreditCardPayment(String name, String cardNumber, String cvv, String expiryDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        // Simulating payment process
        System.out.println("Paid $" + amount + " using Credit Card.");
        // Additional validation or processing can be added here
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        // Simulating payment process
        System.out.println("Paid $" + amount + " using PayPal.");
        // Additional validation or processing can be added here
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.pay(amount);
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        // Using CreditCardPayment strategy
        paymentContext.setPaymentStrategy(new CreditCardPayment("John Doe", "1234567890123456", "123", "12/23"));
        paymentContext.executePayment(100.0);

        // Using PayPalPayment strategy
        paymentContext.setPaymentStrategy(new PayPalPayment("john.doe@example.com", "password123"));
        paymentContext.executePayment(200.0);
    }
}
