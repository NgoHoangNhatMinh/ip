package lux.commands;

import lux.data.TaskList;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * This command lists all tasks
 */
public class ListCommand extends Command {
    /**
     * List all tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
