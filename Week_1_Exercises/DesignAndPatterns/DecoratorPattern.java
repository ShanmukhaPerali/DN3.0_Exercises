interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Sending email notification: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier baseNotifier;

    public NotifierDecorator(Notifier baseNotifier) {
        this.baseNotifier = baseNotifier;
    }

    public void send(String message) {
        baseNotifier.send(message);
    }
}

class SmsNotifierDecorator extends NotifierDecorator {
    public SmsNotifierDecorator(Notifier baseNotifier) {
        super(baseNotifier);
    }

    public void send(String message) {
        baseNotifier.send(message);
        sendSmsNotification(message);
    }

    private void sendSmsNotification(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier baseNotifier) {
        super(baseNotifier);
    }

    public void send(String message) {
        baseNotifier.send(message);
        sendSlackNotification(message);
    }

    private void sendSlackNotification(String message) {
        System.out.println("Sending Slack notification: " + message);
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SmsNotifierDecorator(emailNotifier);
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);
        slackNotifier.send("Hello, this is a test notification!");
    }
}
