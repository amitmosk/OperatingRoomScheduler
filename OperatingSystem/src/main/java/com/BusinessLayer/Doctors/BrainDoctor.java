package com.BusinessLayer.Doctors;

import com.BusinessLayer.OperationRoom;

public class BrainDoctor extends Doctor {
    public BrainDoctor(int id) {
        super(id);
    }

    public boolean canOperateOn(OperationRoom room) {
        return room.hasMRI();
    }
    public long getOperationTime(OperationRoom room) {
        if (canOperateOn(room)) {
            if (room.hasCT()) {
                return 2 * 60;
            }
            else {
                return 3 * 60;
            }
        }
        return -1;
    }
}
