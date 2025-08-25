import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save(TaskList tasks) {
        File f = new File(filePath);
        f.getParentFile().mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks.getTasks());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
