package com.BusinessLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.BusinessLayer.Constants.END_HOUR;
import static com.BusinessLayer.Constants.START_HOUR;

public class OperationRoom {
    private int id;
    private boolean hasECG;
    private boolean hasMRI;
    private boolean hasCT;
    private List<ScheduledOperation> operations; // List of scheduled operations

    public OperationRoom(int id, boolean hasECG, boolean hasMRI, boolean hasCT) {
        this.id = id;
        this.hasECG = hasECG;
        this.hasMRI = hasMRI;
        this.hasCT = hasCT;
        this.operations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public boolean hasECG() {
        return hasECG;
    }

    public boolean hasMRI() {
        return hasMRI;
    }

    public boolean hasCT() {
        return hasCT;
    }

    /**
     * This method begins the search from the current time or resets to the start of the day if the
     * current time exceeds the end hour. It iterates through existing scheduled operations to identify
     * any overlapping time frames. If an overlap is found, the next available time is adjusted to
     * the end of the conflicting operation, and the search continues. If the calculated end of the slot
     * surpasses the designated end hour, the method moves the search to the start time of the next day.
     *
     * @param duration the length (in minutes) of the desired time slot
     * @return the next available LocalDateTime that accommodates the specified duration
     */

    public LocalDateTime findNextSlot(long duration) {
        LocalDateTime nextAvailableTime = initStartTime();
        while (true) {
            boolean found = true;
            LocalDateTime slotEnd = nextAvailableTime.plusMinutes(duration);
            for (ScheduledOperation operation : operations) {
                LocalDateTime operationStart = operation.getStartTime();
                LocalDateTime operationEnd = operation.getEndTime();
                if (nextAvailableTime.isBefore(operationEnd) && slotEnd.isAfter(operationStart)) {
                    nextAvailableTime = operationEnd;
                    found = false;
                    break;
                }
            }
            if (slotEnd.toLocalTime().isAfter(END_HOUR)) {
                LocalDate nextDay = nextAvailableTime.toLocalDate().plusDays(1);
                nextAvailableTime = LocalDateTime.of(nextDay, START_HOUR);
                found = false;
            }
            if (found) {
                return nextAvailableTime;
            }
        }
    }


    /**
     * This method evaluates the current time and decides the correct starting point for scheduling.
     * If the current time exceeds the specified end hour, it sets the start time to the start of
     * the next day. Conversely, if the current time is earlier than the designated start hour,
     * it initializes the start time to the beginning of the current day. If the current time lies
     * within the scheduling range, the method simply returns the current time.

     * @return a LocalDateTime instance representing the scheduling start time
     */

    private LocalDateTime initStartTime() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(LocalDateTime.of(now.toLocalDate(), END_HOUR))) {
            return LocalDateTime.of(now.toLocalDate().plusDays(1), START_HOUR);
        }
        else if (now.isBefore(LocalDateTime.of(now.toLocalDate(), START_HOUR))) {
            return LocalDateTime.of(now.toLocalDate(), START_HOUR);
        }
        return now;
    }


    /**
     * This method adds a new operation to the list of scheduled operations for the room.
     * @param startTime the start time of the operation
     * @param endTime the end time of the operation
     */
    public void scheduleOperation(LocalDateTime startTime, LocalDateTime endTime) {
        operations.add(new ScheduledOperation(startTime, endTime));
    }
}
