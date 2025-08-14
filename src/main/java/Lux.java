import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Lux {
    public static void main(String[] args) throws LuxException {
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
            Map<String, String> parsedCommand = null;
            while (parsedCommand == null) {
                try {
                    System.out.print("You: ");
                    String userInput = scanner.nextLine();
                    parsedCommand = parseCommand(userInput, keywords);
                } catch (LuxException e) {
                    System.err.println(e);
                }
            }

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
                } else if (k.equals("mark")) {
                    if (v.equals("")) {
                        System.err
                                .println(new LuxException("Please specify the task number you want to mark as done."));
                        break;
                    }
                    int idx = Integer.parseInt(v);
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).mark();
                        System.out.println("LUX: Task " + idx + " marked as done.");
                    } else {
                        System.out.println("LUX: Invalid task number.");
                    }
                } else if (k.equals("unmark")) {
                    if (v.equals("")) {
                        System.err.println(
                                new LuxException("Please specify the task number you want to mark as not done."));
                        break;
                    }
                    int idx = Integer.parseInt(v);
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).unmark();
                        System.out.println("LUX: Task " + idx + " marked as not done.");
                    } else {
                        System.out.println("LUX: Invalid task number.");
                    }
                } else if (k.equals("todo")) {
                    if (v.equals("")) {
                        System.err.println(new LuxException("Please provide a description for your todo task."));
                        break;
                    }
                    TodoTask t = new TodoTask(v);
                    tasks.add(t);
                    System.out.println("LUX: Added a new todo task:\n    " + t);
                } else if (k.equals("deadline")) {
                    if (v.equals("")) {
                        System.err.println(new LuxException("Please provide a description for your deadline task."));
                        break;
                    }
                    if (parsedCommand.containsKey("/by")) {
                        DeadlineTask t = new DeadlineTask(v, parsedCommand.get("/by"));
                        tasks.add(t);
                        System.out.println("LUX: Added a new deadline task:\n    " + t);
                    } else {
                        System.err.println(new LuxException("Please specify the deadline using /by."));
                    }
                } else if (k.equals("event")) {
                    if (v.equals("")) {
                        System.err.println(new LuxException("Please provide a description for your event task."));
                        break;
                    }
                    if (parsedCommand.containsKey("/from") && parsedCommand.containsKey("/to")) {
                        EventTask t = new EventTask(v, parsedCommand.get("/from"), parsedCommand.get("/to"));
                        tasks.add(t);
                        System.out.println("LUX: Added a new event task:\n    " + t);
                    } else {
                        System.err.println(new LuxException("Please specify both /from and /to for your event."));
                    }
                }
            }
            System.out.println(divider);
        }
    }

    // Parse command into a map of {commands: arguments}
    private static Map<String, String> parseCommand(String userInput, Set<String> keywords) throws LuxException {
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
            throw new LuxException("Sorry, I didn't understand that command. Please try again!");
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
