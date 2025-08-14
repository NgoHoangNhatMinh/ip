import java.util.Scanner;
import java.util.Set;
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

        final String divider = "========================================";
        System.out.println(divider);
        System.out.println("LUX: Hello! I'm LUX, your personal assistant chatbot.");
        System.out.println("LUX: How can I assist you today?");
        System.out.println(divider);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            Map<String, String> parsedCommand = parseCommand(userInput, keywords);

            boolean responded = false;
            for (Map.Entry<String, String> entry : parsedCommand.entrySet()) {
                String k = entry.getKey(), v = entry.getValue();
                if (k.equals("bye")) {
                    System.out.println("LUX: Goodbye! Hope to see you again soon!");
                    System.out.println(divider);
                    scanner.close();
                    return;
                } else if (k.equals("list")) {
                    System.out.println("LUX: Here are your current tasks:");
                    list(tasks);
                    responded = true;
                } else if (k.equals("mark")) {
                    int idx = Integer.parseInt(v);
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).mark();
                        System.out.println("LUX: Task " + idx + " marked as done.");
                    } else {
                        System.out.println("LUX: Invalid task number.");
                    }
                    responded = true;
                } else if (k.equals("unmark")) {
                    int idx = Integer.parseInt(v);
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).unmark();
                        System.out.println("LUX: Task " + idx + " marked as not done.");
                    } else {
                        System.out.println("LUX: Invalid task number.");
                    }
                    responded = true;
                } else if (k.equals("add")) {
                    tasks.add(new Task(userInput));
                    System.out.println("LUX: Added a new task: " + userInput);
                    responded = true;
                } else if (k.equals("todo")) {
                    TodoTask t = new TodoTask(v);
                    tasks.add(t);
                    System.out.println("LUX: Added a new todo task:\n    " + t);
                    responded = true;
                } else if (k.equals("deadline")) {
                    if (parsedCommand.containsKey("/by")) {
                        DeadlineTask t = new DeadlineTask(v, parsedCommand.get("/by"));
                        tasks.add(t);
                        System.out.println("LUX: Added a new deadline task:\n    " + t);
                        responded = true;
                    }
                } else if (k.equals("event")) {
                    if (parsedCommand.containsKey("/from") && parsedCommand.containsKey("/to")) {
                        EventTask t = new EventTask(v, parsedCommand.get("/from"), parsedCommand.get("/to"));
                        tasks.add(t);
                        System.out.println("LUX: Added a new event task:\n    " + t);
                        responded = true;
                    }
                }
            }
            if (!responded) {
                System.out.println("LUX: Sorry, I didn't understand that. Please try again.");
            }
            System.out.println(divider);
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
        if (texts.isEmpty()) {
            System.out.println("  (No tasks yet!)");
            return;
        }
        for (int i = 0; i < texts.size(); i++) {
            String item = String.format("  %d. %s", i, texts.get(i));
            System.out.println(item);
        }
    }
}
