package lux.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class to store custom aliases
 */
public class AliasList implements Serializable {
    private HashMap<String, String> map;

    /**
     * Add predefined aliases here
     */
    public AliasList() {
        map = new HashMap<>();
        this.add("ls", "list");
        this.add("exit", "bye");
        this.add("t", "todo");
        this.add("d", "deadline");
        this.add("e", "event");
    }

    public String process(String input) {
        return map.getOrDefault(input, input);
    }

    public void add(String alias, String command) {
        map.put(alias, command);
    }
}
