package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * This is the find command which search all tasks matching the given substring
 */
public class FindCommand extends Command {
    private String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) {
        return ui.findTasks(tasks.find(arguments));
    }

    public boolean isExit() {
        return false;
    }
}
