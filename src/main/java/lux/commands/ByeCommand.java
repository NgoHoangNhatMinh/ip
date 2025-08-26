package lux.commands;

import lux.data.TaskList;
import lux.storage.Storage;
import lux.ui.Ui;

public class ByeCommand extends Command {
    /**
     * show bye message and save tasks to storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        storage.save(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
