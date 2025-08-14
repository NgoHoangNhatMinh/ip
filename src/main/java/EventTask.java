public class EventTask extends Task {
    protected String startTime, endTime;

    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
