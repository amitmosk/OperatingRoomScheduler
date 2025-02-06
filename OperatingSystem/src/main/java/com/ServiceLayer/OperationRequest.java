package com.ServiceLayer;

public class OperationRequest {
    private final int doctorId;
    // private JSONObject customerDetails; // TODO scale

    public OperationRequest(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorId() {
        return doctorId;
    }
}
