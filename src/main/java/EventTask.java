import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime startTime, endTime;

    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        return "[E] " + super.toString() + " (from: " + startTime.format(formatter) + " to: "
                + endTime.format(formatter) + ")";
    }
}
