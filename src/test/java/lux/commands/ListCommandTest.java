package lux.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    static class StubUi extends lux.ui.Ui {
        private boolean listed = false;

        @Override
        public void listTasks(lux.data.TaskList tasks) {
            listed = true;
        }
    }

    static class StubTaskList extends lux.data.TaskList {
    }

    static class StubStorage extends lux.storage.Storage {
        StubStorage() {
            super("dummy");
        }
    }

    @Test
    public void testExecuteCallsListTasks() {
        ListCommand cmd = new ListCommand();
        StubUi ui = new StubUi();
        StubTaskList tasks = new StubTaskList();
        StubStorage storage = new StubStorage();
        cmd.execute(tasks, ui, storage);
        assertTrue(ui.listed);
    }

    @Test
    public void testIsExit() {
        assertFalse(new ListCommand().isExit());
    }
}
