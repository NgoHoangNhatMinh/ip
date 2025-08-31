package lux.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task with deadline time
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Create a new deadline with description and deadline time
     * @param description
     * @param by
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HHmm");
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
