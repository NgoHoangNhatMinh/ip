package lux.commands;

import lux.data.Task;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * This is the delete command
 */
public class DeleteCommand extends Command {
    private String argument;

    public DeleteCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Parse argument to get the index of task to delete
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Please specify the task number you want to delete.");
            }
            Task task = tasks.getTasks().remove(idx);
            return ui.deleteTask(task);
        } catch (NumberFormatException e) {
            throw new LuxException("Please specify the task number you want to delete.");

        }
    }

    public boolean isExit() {
        return false;
    }
}
