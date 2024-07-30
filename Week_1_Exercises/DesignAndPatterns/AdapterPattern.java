interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPal {
    public void makePayment(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through PayPal.");
    }
}

class Stripe {
    public void pay(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through Stripe.");
    }
}

class AmazonPay {
    public void processTransaction(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through Amazon Pay.");
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPal paypalService;

    public PayPalAdapter(PayPal paypalService) {
        this.paypalService = paypalService;
    }

    public void processPayment(double amount) {
        paypalService.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private Stripe stripeService;

    public StripeAdapter(Stripe stripeService) {
        this.stripeService = stripeService;
    }

    public void processPayment(double amount) {
        stripeService.pay(amount);
    }
}

class AmazonPayAdapter implements PaymentProcessor {
    private AmazonPay amazonPayService;

    public AmazonPayAdapter(AmazonPay amazonPayService) {
        this.amazonPayService = amazonPayService;
    }

    public void processPayment(double amount) {
        amazonPayService.processTransaction(amount);
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        PayPal paypal = new PayPal();
        Stripe stripe = new Stripe();
        AmazonPay amazonPay = new AmazonPay();

        PaymentProcessor paypalAdapter = new PayPalAdapter(paypal);
        PaymentProcessor stripeAdapter = new StripeAdapter(stripe);
        PaymentProcessor amazonPayAdapter = new AmazonPayAdapter(amazonPay);

        paypalAdapter.processPayment(100.00);
        stripeAdapter.processPayment(200.00);
        amazonPayAdapter.processPayment(300.00);
    }
}
