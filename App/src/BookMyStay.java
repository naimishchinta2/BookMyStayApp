/**
 * Book My Stay App
 *
 * Demonstrates booking request intake using a Queue to maintain
 * First-Come-First-Served (FIFO) ordering of reservation requests.
 *
 * @author Student
 * @version 5.1
 */

import java.util.LinkedList;
import java.util.Queue;

// Reservation class representing a guest booking request
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

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Booking Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request to queue
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display queued requests
    public void displayRequests() {
        System.out.println("\nCurrent Booking Request Queue:");

        for (Reservation reservation : requestQueue) {
            reservation.displayReservation();
        }
    }
}

// Main Application Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Hotel Booking System v5.1 ");
        System.out.println("=================================\n");

        // Initialize booking request queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulated guest booking requests
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Suite");
        Reservation r3 = new Reservation("Charlie", "Double");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue (FIFO order)
        bookingQueue.displayRequests();

        System.out.println("\nRequests stored in arrival order.");
        System.out.println("No inventory changes performed yet.");
    }
}