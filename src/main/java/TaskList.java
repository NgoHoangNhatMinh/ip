import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    ArrayList<Task> tasks;

    public TaskList() {

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
