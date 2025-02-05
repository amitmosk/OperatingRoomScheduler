package ServiceLayer;

import java.time.LocalDateTime;

public class OperationResponse {
    private final Integer roomId;
    private final LocalDateTime operationTime;
    private final Integer queuePosition;

    public OperationResponse(int roomId, LocalDateTime operationTime, int queuePosition) {
        this.roomId = roomId;
        this.operationTime = operationTime;
        this.queuePosition = queuePosition;
    }
}
