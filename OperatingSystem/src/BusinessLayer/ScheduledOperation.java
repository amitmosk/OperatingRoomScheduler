package BusinessLayer;

import java.time.LocalDateTime;

public class ScheduledOperation {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScheduledOperation(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
