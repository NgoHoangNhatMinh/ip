package lux.commands;

import lux.data.TaskList;
import lux.storage.Storage;
import lux.ui.Ui;

public class FindCommand extends Command {
    private String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTasks(tasks.find(arguments));
    }

    public boolean isExit() {
        return false;
    }
}
