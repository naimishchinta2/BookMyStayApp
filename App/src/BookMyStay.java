/**
 * Book My Stay App
 *
 * Demonstrates reservation confirmation and safe room allocation.
 * Booking requests are processed in FIFO order and unique room IDs
 * are generated to prevent double booking.
 *
 * @author Student
 * @version 6.1
 */

import java.util.*;

// Reservation request
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}

// Booking Request Queue
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

// Booking Service (handles allocation)
class BookingService {

    private RoomInventory inventory;

    // Map roomType -> Set of allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    // Global set for uniqueness check
    private Set<String> usedRoomIds;

    public BookingService(RoomInventory inventory) {

        this.inventory = inventory;

        allocatedRooms = new HashMap<>();
        usedRoomIds = new HashSet<>();

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {

        String id;

        do {
            id = roomType.substring(0, 1) + (int) (Math.random() * 1000);
        } while (usedRoomIds.contains(id));

        usedRoomIds.add(id);

        return id;
    }

    // Process booking
    public void processReservation(Reservation reservation) {

        String type = reservation.getRoomType();

        if (inventory.getAvailability(type) > 0) {

            String roomId = generateRoomId(type);

            allocatedRooms.get(type).add(roomId);

            inventory.decrementRoom(type);

            System.out.println(
                    "Reservation Confirmed → Guest: " +
                            reservation.getGuestName() +
                            " | Room Type: " +
                            type +
                            " | Room ID: " +
                            roomId
            );

        } else {

            System.out.println(
                    "Reservation Failed → No available rooms for " +
                            reservation.getGuestName() +
                            " (" + type + ")"
            );
        }
    }

    public void displayAllocations() {

        System.out.println("\nAllocated Rooms:");

        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {

            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

// Main Application
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v6.1 ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Guest booking requests
        requestQueue.addRequest(new Reservation("Alice", "Single"));
        requestQueue.addRequest(new Reservation("Bob", "Suite"));
        requestQueue.addRequest(new Reservation("Charlie", "Double"));
        requestQueue.addRequest(new Reservation("David", "Single"));

        BookingService bookingService = new BookingService(inventory);

        System.out.println("\nProcessing Booking Requests...\n");

        while (requestQueue.hasRequests()) {

            Reservation reservation = requestQueue.getNextRequest();

            bookingService.processReservation(reservation);
        }

        bookingService.displayAllocations();

        inventory.displayInventory();
    }
}