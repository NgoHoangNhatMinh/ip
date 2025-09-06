package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * The bye command end the program and save the data to storage
 */
public class ByeCommand extends Command {
    /**
     * show bye message and save tasks to storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        try {
            storage.saveTasks(tasks);
            storage.saveAliases(aliases);
            return ui.showBye();
        } catch (LuxException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return true;
    }
}
