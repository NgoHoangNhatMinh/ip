package lux;

import lux.commands.Command;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.parser.Parser;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * The main class for the Lux chatbot
 */
public class Lux {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lux(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LuxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LuxException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Lux("data/tasks.ser").run();
    }
}
