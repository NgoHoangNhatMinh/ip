package lux.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A container class to contain the list of all tasks and relevant behaviors
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

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

    /**
     * Search through the task list for task containing substring searchTerm
     * @param searchTerm
     * @return
     */
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
