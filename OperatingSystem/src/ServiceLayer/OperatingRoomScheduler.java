package ServiceLayer;

import BusinessLayer.Doctors.Doctor;
import BusinessLayer.Doctors.DoctorsRepository;
import BusinessLayer.Hospital.HospitalsRepository;
import BusinessLayer.OperationRoom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static BusinessLayer.Constants.MAX_DAYS_AHEAD;

public class OperatingRoomScheduler {
    private static OperatingRoomScheduler instance;
    private final HospitalsRepository hospitalsRepository;
    private final DoctorsRepository doctorsRepository;
    private final Queue<OperationRequest> waitingQueue;

    public static OperatingRoomScheduler getInstance() {
        if (instance == null) {
            synchronized (OperatingRoomScheduler.class) {
                if (instance == null) {
                    instance = new OperatingRoomScheduler();
                }
            }
        }
        return instance;
    }

    private OperatingRoomScheduler() {
        this.hospitalsRepository = HospitalsRepository.getInstance();
        this.doctorsRepository = DoctorsRepository.getInstance();
        this.waitingQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * This method is responsible for scheduling an appointment for a specific surgery in a specific room.
     * If you are unable to schedule an appointment within the next week, you will be added to the appointment for later treatment.
     *
     * @param doctorId - represent the doctor who send the current request.
     * @return OperationResponse with room id, operation time (if success) and queue position otherwise.
     */
    public synchronized OperationResponse handleOperationRequest(int doctorId) {
        Doctor doctor = doctorsRepository.getById(doctorId);
        OperationResponse response;
        LocalDateTime now = LocalDateTime.now();
        OperationRoom selectedRoom = null;
        LocalDateTime operationTime = now.plusDays(MAX_DAYS_AHEAD);
        long selectedDuration = 0;
        List<OperationRoom> rooms = hospitalsRepository.getOperationRooms();
        for (OperationRoom room : rooms) {
            long operationDuration = doctor.getOperationTime(room);
            if (operationDuration > 0) {
                LocalDateTime slot = room.findNextSlot(operationDuration);
                if (slot.isBefore(operationTime)) {
                    operationTime = slot;
                    selectedRoom = room;
                    selectedDuration = operationDuration;
                }
            }
        }
        if (selectedRoom != null) {
            selectedRoom.scheduleOperation(operationTime, operationTime.plusMinutes(selectedDuration));
            response = new OperationResponse(selectedRoom.getId(), operationTime, 0);

        }
        else {
            int id = waitingQueue.size() + 1;
            OperationRequest request = new OperationRequest(doctor.getId());
            waitingQueue.offer(request);
            response = new OperationResponse(-1, null, id);
        }
        return response;
    }

    /**
     * This method process the queue by search room for all operations requests.
     */
    public void processQueue() {
        while (!waitingQueue.isEmpty()) {
            OperationRequest request = waitingQueue.poll();
            handleOperationRequest(request.getDoctorId());
        }
    }
}
