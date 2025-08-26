package lux.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {
    static class StubUi extends lux.ui.Ui {
        boolean todoAdded = false;

        public void addTodo(lux.data.Task task) {
            todoAdded = true;
        }
    }

    static class StubTaskList extends lux.data.TaskList {
        boolean added = false;

        @Override
        public void addTasks(lux.data.Task task) {
            added = true;
        }
    }

    static class StubStorage extends lux.storage.Storage {
        StubStorage() {
            super("dummy");
        }
    }

    @Test
    public void testExecuteAddsTodo() {
        TodoCommand cmd = new TodoCommand("test task");
        StubUi ui = new StubUi();
        StubTaskList tasks = new StubTaskList();
        StubStorage storage = new StubStorage();
        cmd.execute(tasks, ui, storage);
        assertTrue(tasks.added);
        assertTrue(ui.todoAdded);
    }

    @Test
    public void testIsExit() {
        assertFalse(new TodoCommand("test").isExit());
    }
}
