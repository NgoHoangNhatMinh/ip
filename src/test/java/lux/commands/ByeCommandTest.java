package lux.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StubUi extends lux.ui.Ui {
    boolean byeShown = false;

    @Override
    public void showBye() {
        byeShown = true;
    }
}

class StubStorage extends lux.storage.Storage {
    boolean saved = false;

    StubStorage() {
        super("dummy");
    }

    @Override
    public void save(lux.data.TaskList tasks) {
        saved = true;
    }
}

class StubTaskList extends lux.data.TaskList {
}

public class ByeCommandTest {
    @Test
    public void testIsExit() {
        ByeCommand cmd = new ByeCommand();
        assertTrue(cmd.isExit());
    }

    @Test
    public void testExecuteCallsShowByeAndSave() {
        StubUi ui = new StubUi();
        StubStorage storage = new StubStorage();
        StubTaskList tasks = new StubTaskList();
        ByeCommand cmd = new ByeCommand();

        cmd.execute(tasks, ui, storage);

        assertTrue(ui.byeShown, "showBye should be called");
        assertTrue(storage.saved, "save should be called");
    }
}
