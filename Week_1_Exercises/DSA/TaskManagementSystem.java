public class Task {
    private int id;
    private String name;
    private String state;

    public Task(int id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Task ID: " + id + ", Task Name: " + name + ", Status: " + state;
    }
}

public class Node {
    Task task;
    Node nextNode;

    public Node(Task task) {
        this.task = task;
        this.nextNode = null;
    }
}

public class TaskLinkedList {
    private Node headNode;

    public TaskLinkedList() {
        this.headNode = null;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (headNode == null) {
            headNode = newNode;
        } else {
            Node currentNode = headNode;
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = newNode;
        }
    }

    public Task findTaskById(int id) {
        Node currentNode = headNode;
        while (currentNode != null) {
            if (currentNode.task.getId() == id) {
                return currentNode.task;
            }
            currentNode = currentNode.nextNode;
        }
        return null;
    }

    public void printAllTasks() {
        Node currentNode = headNode;
        while (currentNode != null) {
            System.out.println(currentNode.task);
            currentNode = currentNode.nextNode;
        }
    }

    public boolean removeTaskById(int id) {
        if (headNode == null) return false;
        if (headNode.task.getId() == id) {
            headNode = headNode.nextNode;
            return true;
        }
        Node currentNode = headNode;
        while (currentNode.nextNode != null && currentNode.nextNode.task.getId() != id) {
            currentNode = currentNode.nextNode;
        }
        if (currentNode.nextNode == null) return false;
        currentNode.nextNode = currentNode.nextNode.nextNode;
        return true;
    }
}

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();
        taskList.addTask(new Task(1, "Design UI", "In Progress"));
        taskList.addTask(new Task(2, "Develop Backend", "Not Started"));
        taskList.addTask(new Task(3, "Write Tests", "Not Started"));
        taskList.addTask(new Task(4, "Deploy Application", "Completed"));

        System.out.println("All tasks:");
        taskList.printAllTasks();

        System.out.println("\nSearching for task with ID 3:");
        Task task = taskList.findTaskById(3);
        if (task != null) {
            System.out.println("Found: " + task);
        } else {
            System.out.println("Task not found.");
        }

        System.out.println("\nDeleting task with ID 2:");
        boolean isDeleted = taskList.removeTaskById(2);
        System.out.println("Deleted: " + isDeleted);

        System.out.println("\nAll tasks after deletion:");
        taskList.printAllTasks();
    }
}
