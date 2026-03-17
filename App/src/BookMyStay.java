/**
 * Book My Stay App
 *
 * Demonstrates room search and availability check using centralized inventory.
 * Only rooms with availability > 0 are displayed.
 *
 * @author Student
 * @version 4.1
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {

    private String roomType;
    private int beds;
    private int size;
    private double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq ft");
        System.out.println("Price     : $" + price);
    }
}

// Concrete Room Types
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single", 1, 200, 100);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double", 2, 350, 180);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite", 3, 500, 300);
    }
}

// Centralized Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 0); // intentionally unavailable
        inventory.put("Suite", 2);
    }

    // Read-only method
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Return inventory map (read-only usage)
    public Map<String, Integer> getInventory() {
        return inventory;
    }
}

// Search Service (read-only access)
class RoomSearchService {

    public void searchAvailableRooms(Room[] rooms, RoomInventory inventory) {

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {

            int availability = inventory.getAvailability(room.getRoomType());

            // Defensive check
            if (availability > 0) {

                room.displayRoomDetails();
                System.out.println("Available Rooms: " + availability);
                System.out.println("----------------------------");
            }
        }
    }
}

// Main Application Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v4.1 ");
        System.out.println("=================================\n");

        // Create room domain objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = { single, doubleRoom, suite };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        // Guest searches for available rooms
        searchService.searchAvailableRooms(rooms, inventory);

        System.out.println("\nSearch completed. Inventory state unchanged.");
    }
}