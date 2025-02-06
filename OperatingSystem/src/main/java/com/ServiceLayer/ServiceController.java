package com.ServiceLayer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/service")
@RestController
public class ServiceController {
    private OperatingRoomScheduler scheduler;

    public ServiceController() {
        scheduler = OperatingRoomScheduler.getInstance();
    }

    @GetMapping("/operationRequest/{doctorId}")
    public ResponseEntity<OperationResponse> operationRequest(@PathVariable("doctorId") Integer doctorId) {
        OperationResponse response = scheduler.handleOperationRequest(doctorId);
        return createResponse(response.getStatus(), response);
    }

    private <T> ResponseEntity<OperationResponse> createResponse(HttpStatus status, OperationResponse body)
    {
        return	ResponseEntity.status(status).header("roomId", body.getRoomId().toString()).body(body);
    }

}
