package lux;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lux.commands.Command;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.parser.Parser;
import lux.storage.Storage;
import lux.ui.MainWindow;
import lux.ui.Ui;

/**
 * The main class for the Lux chatbot
 */
public class Lux extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String DEFAULT_FILE_PATH = "data/tasks.ser";

    public Lux() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Instantiate a new Lux bot with the given filepath for storage
     * @param filePath path to store and retrieve saved data
     */
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Lux heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLux(this); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Lux(DEFAULT_FILE_PATH).run();
    }
}
