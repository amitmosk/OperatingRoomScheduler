package BusinessLayer.Doctors;
import java.util.ArrayList;
import java.util.List;

public class DoctorsRepository {
    private static DoctorsRepository instance;
    List<Doctor> doctors;

    public static DoctorsRepository getInstance() {
        if (instance == null) {
            synchronized (DoctorsRepository.class) {
                if (instance == null) {
                    instance = new DoctorsRepository();
                }
            }
        }
        return instance;
    }

    private DoctorsRepository() {
        doctors = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public Doctor getById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }
}
