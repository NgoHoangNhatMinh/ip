package lux.data;

import java.io.Serializable;

/**
 * Parent class of all tasks
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return status icon based on isDone status
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * mark task as done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * mark task as not done
     */
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
