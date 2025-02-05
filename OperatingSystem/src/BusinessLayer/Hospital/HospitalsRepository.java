package BusinessLayer.Hospital;

import BusinessLayer.OperationRoom;

import java.util.ArrayList;
import java.util.List;

public class HospitalsRepository {
    private static HospitalsRepository instance;
    private List<Hospital> hospitals;

    public static HospitalsRepository getInstance() {
        if (instance == null) {
            synchronized (HospitalsRepository.class) {
                if (instance == null) {
                    instance = new HospitalsRepository();
                }
            }
        }
        return instance;
    }

    private HospitalsRepository() {
        hospitals = new ArrayList<>();
    }

    public void addHospital(Hospital hospital) {
        hospitals.add(hospital);
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void removeHospital(Hospital hospital) {
        hospitals.remove(hospital);
    }

    public List<OperationRoom> getOperationRooms() {
        List<OperationRoom> rooms = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            rooms.addAll(hospital.getOperationRooms());
        }
        return rooms;
    }
}
