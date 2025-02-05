package ServiceLayer;
//@RequestMapping("/service")
//@Controller
public class ServiceController {
    private OperatingRoomScheduler scheduler;

    public ServiceController() {
        scheduler = OperatingRoomScheduler.getInstance();
    }

    public OperationResponse operationRequest(int doctorId) {
       return scheduler.handleOperationRequest(doctorId);
    }

}
