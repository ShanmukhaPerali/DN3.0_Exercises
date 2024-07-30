import java.util.*;

class Product {
    int id;
    String name;
    String type;
    int amount;
    int cost;

    Product(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}

class Inventory {
    ArrayList<Product> products;

    Inventory() {
        products = new ArrayList<>();
    }

    public void add(Product item) {
        products.add(item);
    }

    public Product linearSearch(int id) {
        for (Product item : products) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

    public Product binarySearch(int id) {
        Collections.sort(products, Comparator.comparingInt(x -> x.id));
        int low = 0;
        int high = products.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (products.get(mid).id == id) {
                return products.get(mid);
            } else if (products.get(mid).id > id) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }
}

public class EcommercePlatformSearch {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product item1 = new Product(1, "Product1", "Category1");
        Product item2 = new Product(2, "Product2", "Category2");

        inventory.add(item1);
        inventory.add(item2);

        Product result = inventory.linearSearch(1);
        if (result != null) {
            System.out.println("Linear search found: " + result.name);
        } else {
            System.out.println("Linear search did not find the product.");
        }

        result = inventory.binarySearch(2);
        if (result != null) {
            System.out.println("Binary search found: " + result.name);
        } else {
            System.out.println("Binary search did not find the product.");
        }
    }
}
