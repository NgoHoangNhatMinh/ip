package lux.commands;

import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * This is the basee abstract command that all other command derives from
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LuxException;

    public abstract boolean isExit();
}
