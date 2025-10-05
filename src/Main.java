import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotificationService notificationService = new ConsoleNotificationService();
        TaskManager taskManager = new TaskManager(notificationService);

        currentUser = new User("DefaultUser");
        System.out.println("Welcome, " + currentUser.getUsername() + "!");

        while (true) {
            printMenu();
            System.out.print("👉 Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask(scanner, taskManager);
                    break;
                case "2":
                    taskManager.viewAllTasks();
                    break;
                case "3":
                    updateTaskStatus(scanner, taskManager);
                    break;
                case "4":
                    deleteTask(scanner, taskManager);
                    break;
                case "5":
                    System.out.println("👋 Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Personal Task Manager =====");
        System.out.println("1. Add a new Task");
        System.out.println("2. View all Tasks");
        System.out.println("3. Update Task Status");
        System.out.println("4. Delete a Task");
        System.out.println("5. Exit");
        System.out.println("=================================");
    }

    private static void addTask(Scanner scanner, TaskManager taskManager) {
        try {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();

            System.out.print("Enter due date (YYYY-MM-DD): ");
            LocalDate dueDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

            Task newTask = new Task(description, dueDate, priority, currentUser);
            taskManager.addTask(newTask);

        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid priority or status. Please check your input.");
        }
    }

    private static void updateTaskStatus(Scanner scanner, TaskManager taskManager) {
        System.out.print("Enter the task number to update: ");
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            Task task = taskManager.getTaskByIndex(taskNumber);

            if (task != null) {
                System.out.print("Enter new status (PENDING, IN_PROGRESS, COMPLETED): ");
                Status newStatus = Status.valueOf(scanner.nextLine().toUpperCase());
                task.setStatus(newStatus);
                System.out.println("✅ Status updated successfully!");
            } else {
                System.out.println("❌ Task not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number. Please enter a valid task number.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid status. Please enter one of the valid options.");
        }
    }

    private static void deleteTask(Scanner scanner, TaskManager taskManager) {
        System.out.print("Enter the task number to delete: ");
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            boolean isDeleted = taskManager.deleteTask(taskNumber);

            if (isDeleted) {
                System.out.println("✅ Task deleted successfully!");
            } else {
                System.out.println("❌ Task not found. Nothing to delete.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number. Please enter a valid task number.");
        }
    }
}