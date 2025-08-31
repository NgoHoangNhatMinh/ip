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
    private static final String DEFAULT_FILE_PATH = "data/tasks.ser";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    public Lux() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Instantiate a new Lux bot with the given filepath for storage
     *
     * @param filePath path to store and retrieve saved data
     */
    public Lux(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
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
        System.out.println(ui.showWelcome());
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine());
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (LuxException e) {
                System.err.println(e.getMessage());
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String message = c.execute(tasks, ui, storage);
            System.out.println(ui.showLine());
            System.out.println(message);
            isExit = c.isExit();
            return message;
        } catch (LuxException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        } finally {
            System.out.println(ui.showLine());
        }
    }

    /**
     * Say welcome
     * @return
     */
    public String showWelcome() {
        return ui.showWelcome();
    }

    /**
     * Return the exit status of Lux
     *
     * @return
     */
    public boolean isExit() {
        return isExit;
    }

    public static void main(String[] args) {
        new Lux(DEFAULT_FILE_PATH).run();
    }
}
