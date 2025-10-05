import java.time.LocalDate;

public class Task {
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
    private User owner;

    public Task(String description, LocalDate dueDate, Priority priority, User owner) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.PENDING;
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Task: '" + description + '\'' +
                " | DueDate: " + dueDate +
                " | Priority: " + priority +
                " | Status: " + status +
                " | Owner: " + owner.getUsername();
    }
}