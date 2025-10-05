<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private NotificationService notificationService;

    public TaskManager(NotificationService notificationService) {
        this.tasks = new ArrayList<>();
        this.notificationService = notificationService;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        notificationService.sendNotification("New task added: '" + task.getDescription() + "'");
    }

    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show. Your to-do list is empty! 🎉");
            return;
        }
        System.out.println("\n--- Your Tasks ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("------------------\n");
    }

    public Task getTaskByIndex(int index) {
        int actualIndex = index - 1;
        if (actualIndex >= 0 && actualIndex < tasks.size()) {
            return tasks.get(actualIndex);
        }
        return null;
    }

    public boolean deleteTask(int index) {
        Task taskToDelete = getTaskByIndex(index);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            notificationService.sendNotification("Task deleted: '" + taskToDelete.getDescription() + "'");
            return true;
        }
        return false;
    }
}
=======
package PACKAGE_NAME;

public class TaskManager {
}
>>>>>>> 372a2a4a95f115a8f099b326565532ca6e549a0f
