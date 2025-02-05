package BusinessLayer.Doctors;

import BusinessLayer.OperationRoom;

public abstract class Doctor {
    private int id;
    public Doctor(int id) {
        this.id = id;
    }
    public abstract boolean canOperateOn(OperationRoom room);
    public abstract long getOperationTime(OperationRoom room); // in minutes

    public int getId() {
        return id;
    }
}

