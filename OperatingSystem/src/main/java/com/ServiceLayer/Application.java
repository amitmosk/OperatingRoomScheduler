package com.ServiceLayer;
import com.BusinessLayer.Doctors.BrainDoctor;
import com.BusinessLayer.Doctors.HeartDoctor;
import com.BusinessLayer.Hospital.Hospital;
import com.BusinessLayer.Hospital.HospitalsRepository;
import com.BusinessLayer.Doctors.DoctorsRepository;
import com.BusinessLayer.OperationRoom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		initData();
		QueueProcessor queueProcessor = new QueueProcessor();

	}

    private static void initData() {
        HospitalsRepository hospitalsRepository = HospitalsRepository.getInstance();
        Hospital hospital = new Hospital();
        hospital.addOperationRoom(new OperationRoom(1, true, true, true));
        hospital.addOperationRoom(new OperationRoom(2, false, true, true));
        hospital.addOperationRoom(new OperationRoom(3, false, true, true));
        hospital.addOperationRoom(new OperationRoom(4, true, true, false));
        hospital.addOperationRoom(new OperationRoom(5, true, true, false));
        hospitalsRepository.addHospital(hospital);
        DoctorsRepository doctorsRepository = DoctorsRepository.getInstance();
        doctorsRepository.addDoctor(new BrainDoctor(1));
        doctorsRepository.addDoctor(new HeartDoctor(2));
    }
}
