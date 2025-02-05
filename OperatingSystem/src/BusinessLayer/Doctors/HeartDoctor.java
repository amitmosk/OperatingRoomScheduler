package BusinessLayer.Doctors;

import BusinessLayer.OperationRoom;

public class HeartDoctor extends Doctor {
    public HeartDoctor(int id) {
        super(id);
    }
    public boolean canOperateOn(OperationRoom room) {
        return room.hasECG();
    }
    public long getOperationTime(OperationRoom room) {
        return 3 * 60;
    }
}
