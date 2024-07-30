public class Employee {
    private int id;
    private String fullName;
    private String title;
    private double compensation;

    public Employee(int id, String fullName, String title, double compensation) {
        this.id = id;
        this.fullName = fullName;
        this.title = title;
        this.compensation = compensation;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public double getCompensation() {
        return compensation;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + fullName + ", Title: " + title + ", Compensation: $" + compensation;
    }
}

public class EmployeeManager {
    private Employee[] employees;
    private int size;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee emp) {
        if (size < employees.length) {
            employees[size++] = emp;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    public Employee findEmployeeById(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    public void printAllEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public boolean removeEmployeeById(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                return true;
            }
        }
        return false;
    }
}

// Main class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(10);

        manager.addEmployee(new Employee(1, "Alice", "Developer", 70000));
        manager.addEmployee(new Employee(2, "Bob", "Manager", 85000));
        manager.addEmployee(new Employee(3, "Charlie", "Analyst", 60000));
        manager.addEmployee(new Employee(4, "David", "Designer", 65000));

        System.out.println("All employees:");
        manager.printAllEmployees();

        System.out.println("\nSearching for employee with ID 3:");
        Employee emp = manager.findEmployeeById(3);
        if (emp != null) {
            System.out.println("Found: " + emp);
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting employee with ID 2:");
        boolean isRemoved = manager.removeEmployeeById(2);
        System.out.println("Deleted: " + isRemoved);

        System.out.println("\nAll employees after deletion:");
        manager.printAllEmployees();
    }
}
