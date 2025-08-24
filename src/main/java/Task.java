import java.io.Serializable;

public abstract class Task implements Serializable {
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
        System.out.println("LUX: Great! I've marked this task as done:");
        System.out.println("    " + this);
    }

    public void unmark() {
        isDone = false;
        System.out.println("LUX: I've marked this task as not done yet:");
        System.out.println("    " + this);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
