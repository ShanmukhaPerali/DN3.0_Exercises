import java.util.*;

class Order {
    int id;
    String name;
    int price;

    Order(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class SortingCustomerOrders {
    public static void bubbleSort(Order[] orders) {
        int length = orders.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (orders[j].price > orders[j + 1].price) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(orders, low, high);
        quickSort(orders, low, pivotIndex - 1);
        quickSort(orders, pivotIndex + 1, high);
    }

    private static int partition(Order[] orders, int low, int high) {
        Order pivot = orders[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].price < pivot.price) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orderArray = {
            new Order(1, "Alice", 300),
            new Order(2, "Bob", 150),
            new Order(3, "Charlie", 200)
        };

        System.out.println("Before Bubble Sort:");
        for (Order order : orderArray) {
            System.out.println(order.name + ": " + order.price);
        }

        bubbleSort(orderArray);

        System.out.println("\nAfter Bubble Sort:");
        for (Order order : orderArray) {
            System.out.println(order.name + ": " + order.price);
        }

        Order[] orderArrayForQuickSort = {
            new Order(1, "Alice", 300),
            new Order(2, "Bob", 150),
            new Order(3, "Charlie", 200)
        };

        System.out.println("\nBefore Quick Sort:");
        for (Order order : orderArrayForQuickSort) {
            System.out.println(order.name + ": " + order.price);
        }

        quickSort(orderArrayForQuickSort, 0, orderArrayForQuickSort.length - 1);

        System.out.println("\nAfter Quick Sort:");
        for (Order order : orderArrayForQuickSort) {
            System.out.println(order.name + ": " + order.price);
        }
    }
}
