package lux.data;

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
    }

    public void unmark() {
        isDone = false;
    }

    public boolean contains(String searchTerm) {
        return description.contains(searchTerm);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
