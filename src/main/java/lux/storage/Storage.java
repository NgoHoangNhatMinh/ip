package lux.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lux.data.AliasList;
import lux.data.Task;
import lux.data.TaskList;
import lux.exception.LuxException;

/**
 * The storage class manages saving and loading data
 */
public class Storage {
    private String filePath;
    private String taskPath;
    private String aliasPath;

    /**
     * Initialize storage with corresponding filepath for tasks data and aliases data
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskPath = filePath + "tasks.ser";
        this.aliasPath = filePath + "aliases.ser";
    }

    /**
     * Load tasks from data if it exists, else return an empty task list
     *
     * @return loaded tasks if it exist, else empty list
     * @throws LuxException
     */
    public ArrayList<Task> loadTasks() throws LuxException {
        File f = new File(taskPath);
        if (!f.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(taskPath);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedTasks = (ArrayList<Task>) in.readObject();
            return loadedTasks;
        } catch (IOException | ClassNotFoundException c) {
            throw new LuxException(c.getMessage());
        }
    }

    /**
     * Save tasks to local storage
     *
     * @param tasks
     */
    public void saveTasks(TaskList tasks) throws LuxException {
        File f = new File(taskPath);
        f.getParentFile().mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(taskPath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks.getTasks());
        } catch (IOException i) {
            i.printStackTrace();
            throw new LuxException("Exception while saving tasks data to disk");
        }
    }

    /**
     * Load custom aliases from storage
     * @return
     * @throws LuxException
     */
    public AliasList loadAliases() throws LuxException {
        File f = new File(aliasPath);
        if (!f.exists()) {
            return new AliasList();
        }

        try (FileInputStream fileIn = new FileInputStream(aliasPath);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            AliasList aliases = (AliasList) in.readObject();
            return aliases;
        } catch (IOException | ClassNotFoundException c) {
            throw new LuxException(c.getMessage());
        }
    }

    /**
     * Save custom aliases to local storage
     * @param aliases
     * @throws LuxException
     */
    public void saveAliases(AliasList aliases) throws LuxException {
        File f = new File(aliasPath);
        f.getParentFile().mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(aliasPath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(aliases);
        } catch (IOException i) {
            i.printStackTrace();
            throw new LuxException("Exception while saving aliases data to disk");
        }
    }
}
