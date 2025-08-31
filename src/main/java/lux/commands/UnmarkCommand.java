package lux.commands;

import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * This command unmark a task as not done
 */
public class UnmarkCommand extends Command {
    private String argument;

    public UnmarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Parse argument to get the index of task to unmark
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Please specify the task number you want to unmark.");
            }
            tasks.getTasks().get(idx).unmark();
            ui.unmarkTask(tasks.getTasks().get(idx));
        } catch (NumberFormatException e) {
            throw new LuxException(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
