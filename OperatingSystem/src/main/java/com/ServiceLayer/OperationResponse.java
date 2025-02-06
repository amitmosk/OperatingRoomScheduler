package com.ServiceLayer;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class OperationResponse {
    private final HttpStatus status;
    private final Integer roomId;
    private final LocalDateTime operationTime;
    private final Integer queuePosition;

    public OperationResponse(HttpStatus status, Integer roomId, LocalDateTime operationTime, Integer queuePosition) {
        this.status = status;
        this.roomId = roomId;
        this.operationTime = operationTime;
        this.queuePosition = queuePosition;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getQueuePosition() {
        return queuePosition;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }
}
