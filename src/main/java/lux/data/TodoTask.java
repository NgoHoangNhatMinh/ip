package lux.data;

/**
 * Todo task
 */
public class TodoTask extends Task {
    /**
     * Create a new todo task with description
     * @param description
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
