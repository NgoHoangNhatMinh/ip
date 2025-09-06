package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Creating custom aliases
 */
public class AliasCommand extends Command {
    private String arguments;

    public AliasCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Create alias
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        String[] parts = arguments.split(" ", 2);
        if (parts.length < 2) {
            throw new LuxException("Must have 2 arguments");
        }

        String alias = parts[0].trim();
        String command = parts[1].trim();
        aliases.add(alias, command);

        return ui.addAlias(alias, command);
    }

    public boolean isExit() {
        return false;
    }
}
