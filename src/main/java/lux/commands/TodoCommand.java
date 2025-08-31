package lux.commands;

import lux.data.TaskList;
import lux.data.TodoTask;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Add a new todo task
 */
public class TodoCommand extends Command {
    private String argument;

    public TodoCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Add todo task to list of tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TodoTask task = new TodoTask(argument);
        tasks.addTasks(task);
        return ui.addTodo(task);
    }

    public boolean isExit() {
        return false;
    }
}
