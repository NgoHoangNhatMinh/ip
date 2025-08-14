import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Lux {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Set<String> keywords = new HashSet<>();
        keywords.add("list");
        keywords.add("deadline");
        keywords.add("/by");
        keywords.add("/from");
        keywords.add("/to");
        keywords.add("event");
        keywords.add("todo");
        keywords.add("mark");
        keywords.add("unmark");
        keywords.add("bye");

        System.out.println("Hello! I'm LUX\nWhat can I do for you?\n-------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            Map<String, String> parsedCommand = parseCommand(userInput, keywords);

            for (Map.Entry<String, String> entry : parsedCommand.entrySet()) {
                String k = entry.getKey(), v = entry.getValue();
                if (k.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    return;
                } else if (k.equals("list")) {
                    list(tasks);
                } else if (k.equals("mark")) {
                    tasks.get(Integer.parseInt(v)).mark();
                } else if (k.equals("unmark")) {
                    tasks.get(Integer.parseInt(v)).unmark();
                } else if (k.equals("add")) {
                    tasks.add(new Task(userInput));
                    System.out.println("* added: " + userInput);
                } else if (k.equals("todo")) {
                    TodoTask t = new TodoTask(v);
                    tasks.add(t);
                    System.out.println("* Got it. I've added this task: \n" + t);
                } else if (k.equals("deadline")) {
                    if (parsedCommand.containsKey("/by")) {
                        DeadlineTask t = new DeadlineTask(v, parsedCommand.get("/by"));
                        tasks.add(t);
                        System.out.println("* Got it. I've added this task: \n" + t);
                    }
                } else if (k.equals("event")) {
                    if (parsedCommand.containsKey("/from") && parsedCommand.containsKey("/to")) {
                        EventTask t = new EventTask(v, parsedCommand.get("/from"), parsedCommand.get("/to"));
                        tasks.add(t);
                        System.out.println("* Got it. I've added this task: \n" + t);
                    }
                }
            }

            System.out.println("------------------------------\n");
        }
    }

    // Parse command into a map of {commands: arguments}
    private static Map<String, String> parseCommand(String userInput, Set<String> keywords) {
        Map<String, String> parsed = new LinkedHashMap<>();
        String[] tokens = userInput.split(" ");

        String key = null;
        String value = "";

        for (String t : tokens) {
            if (keywords.contains(t)) {
                if (key != null) {
                    parsed.put(key, value.trim());
                }
                key = t;
                value = "";
            } else {
                value += t + " ";
            }
        }

        if (key != null) {
            parsed.put(key, value.trim());
        } else {
            parsed.put("add", value.trim());
        }

        return parsed;
    }

    private static void list(List<Task> texts) {
        for (int i = 0; i < texts.size(); i++) {
            String item = String.format("%d. %s", i, texts.get(i));
            System.out.println(item);
        }
    }
}
