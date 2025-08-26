package lux.data;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public TaskList find(String searchTerm) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.contains(searchTerm)) {
                foundTasks.addTasks(task);
            }
        }
        return foundTasks;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "  (No tasks yet!)";
        }
        String outString = "";
        for (int i = 0; i < tasks.size(); i++) {
            String item = String.format("  %d. %s\n", i, tasks.get(i));
            outString += item;
        }
        return outString;
    }
}
