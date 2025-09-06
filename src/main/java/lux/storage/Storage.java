package lux.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lux.data.Task;
import lux.data.TaskList;
import lux.exception.LuxException;

/**
 * The storage class manages saving and loading data
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from data if it exists, else return an empty task list
     *
     * @return loaded tasks if it exist, else empty list
     * @throws LuxException
     */
    public ArrayList<Task> load() throws LuxException {
        File f = new File(filePath);
        if (!f.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(filePath);
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
    public void save(TaskList tasks) throws LuxException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks.getTasks());
        } catch (IOException i) {
            i.printStackTrace();
            throw new LuxException("Exception while saving data to disk");
        }
    }
}
