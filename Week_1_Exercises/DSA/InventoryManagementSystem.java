import java.util.HashMap;

class Product {
    private int id;
    private String name;
    private int stock;
    private int cost;

    Product(int id, String name, int stock, int cost) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Inventory {
    private HashMap<Integer, Product> inventoryMap;

    Inventory() {
        inventoryMap = new HashMap<>();
    }

    public void add(Product item) {
        inventoryMap.put(item.getId(), item);
    }

    public void delete(int id) {
        inventoryMap.remove(id);
    }

    public void updateName(int id, String name) {
        Product item = inventoryMap.getOrDefault(id, null);
        if (item != null) {
            item.setName(name);
        }
    }

    public void updateStock(int id, int stock) {
        Product item = inventoryMap.getOrDefault(id, null);
        if (item != null) {
            item.setStock(stock);
        }
    }

    public HashMap<Integer, Product> getInventoryMap() {
        return inventoryMap;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product item1 = new Product(1, "Product1", 10, 100);
        Product item2 = new Product(2, "Product2", 5, 200);

        inventory.add(item1);
        inventory.add(item2);

        System.out.println("Product1 Name: " + inventory.getInventoryMap().get(1).getName());
        System.out.println("Product2 Stock: " + inventory.getInventoryMap().get(2).getStock());

        inventory.updateName(1, "UpdatedProduct1");
        inventory.updateStock(2, 15);

        System.out.println("Updated Product1 Name: " + inventory.getInventoryMap().get(1).getName());
        System.out.println("Updated Product2 Stock: " + inventory.getInventoryMap().get(2).getStock());
    }
}
