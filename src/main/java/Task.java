public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void mark() {
        isDone = true;
        System.out.println("OK, I've marked this task as done:");
        System.out.println("[X] " + description);
    }

    public void unmark() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + description);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
