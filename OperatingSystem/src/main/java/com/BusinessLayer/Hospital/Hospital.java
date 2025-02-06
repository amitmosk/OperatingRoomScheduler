package com.BusinessLayer.Hospital;

import com.BusinessLayer.OperationRoom;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<OperationRoom> operationRooms;

    public Hospital() {
        operationRooms = new ArrayList<>();
    }

    public void addOperationRoom(OperationRoom operationRoom) {
        operationRooms.add(operationRoom);
    }

    public List<OperationRoom> getOperationRooms() {
        return operationRooms;
    }

    public void removeOperationRoom(OperationRoom operationRoom) {
        operationRooms.remove(operationRoom);
    }
}
