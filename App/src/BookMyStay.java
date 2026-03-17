/**
 * Book My Stay App
 *
 * Demonstrates centralized room inventory management using HashMap.
 * Room availability is stored and managed in a single data structure.
 *
 * @author Student
 * @version 3.1
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

// Concrete room types
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

// Inventory management class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}

// Main Application Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v3.1 ");
        System.out.println("=================================\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details
        System.out.println("Room Information:\n");

        single.displayRoomDetails();
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println();

        suite.displayRoomDetails();
        System.out.println();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        System.out.println("\nThank you for using Book My Stay.");
    }
}