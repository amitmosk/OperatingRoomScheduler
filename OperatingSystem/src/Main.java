import BusinessLayer.*;
import BusinessLayer.Doctors.BrainDoctor;
import BusinessLayer.Doctors.DoctorsRepository;
import BusinessLayer.Doctors.HeartDoctor;
import BusinessLayer.Hospital.Hospital;
import BusinessLayer.Hospital.HospitalsRepository;
import ServiceLayer.OperatingRoomScheduler;
import ServiceLayer.ServiceController;

import static BusinessLayer.Constants.DAEMON_INTERVALS;

public class Main {
    public static void main(String[] args) {
        init();
        ServiceController serviceController = new ServiceController();
        for (int i = 0; i < 7 * 8 * 5; i++) {
            serviceController.operationRequest(i % 2 + 1);
        }
        HospitalsRepository hospitalsRepository = HospitalsRepository.getInstance();
        OperatingRoomScheduler scheduler = OperatingRoomScheduler.getInstance();

        Thread thread = new Thread(new QueueProcessorThread());
        thread.start();
    }

    private static void init() {
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

    private static class QueueProcessorThread implements Runnable {
        @Override
        public void run() {
            OperatingRoomScheduler scheduler = OperatingRoomScheduler.getInstance();
            while (true) {
                try {
                    Thread.sleep(DAEMON_INTERVALS);
                    scheduler.processQueue();
                } catch (Exception e) {
                    break;
                }
            }
        }
    }



}