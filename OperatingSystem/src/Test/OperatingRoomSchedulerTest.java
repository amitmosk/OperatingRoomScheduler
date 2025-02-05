//package Test;
//
//import BusinessLayer.Doctor;
//import BusinessLayer.DoctorsRepository;
//import BusinessLayer.HospitalsRepository;
//import BusinessLayer.OperationRoom;
//import ServiceLayer.OperatingRoomScheduler;
//import ServiceLayer.OperationResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static javafx.beans.binding.Bindings.when;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class OperatingRoomSchedulerTest {
//
//    private OperatingRoomScheduler scheduler;
//    private DoctorsRepository doctorsRepository;
//    private HospitalsRepository hospitalsRepository;
//
//    @BeforeEach
//    void setUp() {
//        scheduler = OperatingRoomScheduler.getInstance();
//        doctorsRepository = Mockito.mock(DoctorsRepository.class);
//        hospitalsRepository = Mockito.mock(HospitalsRepository.class);
//
//        // Set up the singleton repository instances to return mocks
//        // Assuming they have a static method getInstance() that returns the singleton instance
//        // This is pseudocode; you will have to adjust based on your actual implementation.
//        when(DoctorsRepository.getInstance()).thenReturn(doctorsRepository);
//        when(HospitalsRepository.getInstance()).thenReturn(hospitalsRepository);
//    }
//
//    @Test
//    void testHandleOperationRequest_SuccessfulScheduling() {
//        // Arrange
//        Doctor doctor = mock(Doctor.class);
//        OperationRoom room = mock(OperationRoom.class);
//        List<OperationRoom> rooms = new ArrayList<>();
//        rooms.add(room);
//
//        LocalDateTime now = LocalDateTime.now();
//        long operationDuration = 60; // 60 minutes
//
//        when(doctorsRepository.getById(anyInt())).thenReturn(doctor);
//        when(doctor.getOperationTime(room)).thenReturn(operationDuration);
//        when(hospitalsRepository.getOperationRooms()).thenReturn(rooms);
//        when(room.findNextSlot(anyLong())).thenReturn(now.plusMinutes(10)); // Room available in 10 minutes
//        when(room.scheduleOperation(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);
//
//        // Act
//        OperationResponse response = scheduler.handleOperationRequest(1);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(room.getId(), response.getRoomId());
//        assertTrue(response.getOperationTime().isAfter(now)); // Check if operation time is in the future
//        assertEquals(0, response.getQueuePosition()); // Position should be zero since it was scheduled
//    }
//
//    @Test
//    void testHandleOperationRequest_NoAvailableRoom() {
//        // Arrange
//        Doctor doctor = mock(Doctor.class);
//        List<OperationRoom> rooms = new ArrayList<>();
//
//        when(doctorsRepository.getById(anyInt())).thenReturn(doctor);
//        when(hospitalsRepository.getOperationRooms()).thenReturn(rooms);
//        when(doctor.getOperationTime(any())).thenReturn(60); // 60 minutes
//
//        // Act
//        OperationResponse response = scheduler.handleOperationRequest(1);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(-1, response.getRoomId()); // No room should be available
//        assertNotNull(response.getOperationTime()); // Should be null
//        assertEquals(0, response.getQueuePosition()); // Position should reflect that it is in the queue
//    }
//
//    @Test
//    void testProcessQueue_WithWaitingRequests() {
//        // Arrange
//        Doctor doctor = mock(Doctor.class);
//        OperationRoom room = mock(OperationRoom.class);
//        List<OperationRoom> rooms = new ArrayList<>();
//        rooms.add(room);
//
//        when(doctorsRepository.getById(anyInt())).thenReturn(doctor);
//        when(doctor.getOperationTime(room)).thenReturn(60); // 60 minutes
//        when(hospitalsRepository.getOperationRooms()).thenReturn(rooms);
//        when(room.findNextSlot(anyLong())).thenReturn(LocalDateTime.now().plusMinutes(10)); // Room available in 10 minutes
//
//        // Act: Add to waiting queue
//        scheduler.handleOperationRequest(1);
//        scheduler.handleOperationRequest(2); // Add another request that goes to the queue
//
//        // Act: Process the queue
//        scheduler.processQueue();
//
//        // Assert: Check that the room was scheduled for the waiting requests
//        verify(room, times(1)).scheduleOperation(any(LocalDateTime.class), any(LocalDateTime.class));
//    }
//
//    // More test cases can be added as needed
//}
