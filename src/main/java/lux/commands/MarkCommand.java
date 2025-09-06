package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * This command marks the task as done
 */
public class MarkCommand extends Command {
    private String argument;

    public MarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Parse argument to get the index of task to mark
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Please specify the task number you want to mark.");
            }

            tasks.getTasks().get(idx).mark();
            return ui.markTask(tasks.getTasks().get(idx));
        } catch (NumberFormatException e) {
            throw new LuxException("Please specify the task number you want to mark.");

        }
    }

    public boolean isExit() {
        return false;
    }
}
