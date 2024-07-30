import java.util.ArrayList;
import java.util.List;

interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observerList;
    private double currentStockPrice;

    public StockMarket() {
        this.observerList = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(currentStockPrice);
        }
    }

    public void setStockPrice(double stockPrice) {
        this.currentStockPrice = stockPrice;
        notifyObservers();
    }
}

interface Observer {
    void update(double stockPrice);
}

class MobileApp implements Observer {
    private String applicationName;

    public MobileApp(String applicationName) {
        this.applicationName = applicationName;
    }

    public void update(double stockPrice) {
        System.out.println(applicationName + " received stock price update: " + stockPrice);
    }
}

class WebApp implements Observer {
    private String applicationName;

    public WebApp(String applicationName) {
        this.applicationName = applicationName;
    }

    public void update(double stockPrice) {
        System.out.println(applicationName + " received stock price update: " + stockPrice);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("MobileApp");
        Observer webApp = new WebApp("WebApp");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice(100.00);
        stockMarket.setStockPrice(101.50);

        stockMarket.deregisterObserver(webApp);
        stockMarket.setStockPrice(102.75);
    }
}
