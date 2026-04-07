import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 * Use Case 5 - Booking Request (First-Come-First-Served)
 * @version 5.0
 */

public class BookMyStayApp {

    // Reservation Class
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public void display() {
            System.out.println("Guest : " + guestName + " | Room : " + roomType);
        }
    }

    // Booking Queue
    static class BookingRequestQueue {

        private Queue<Reservation> queue;

        public BookingRequestQueue() {
            queue = new LinkedList<>();
        }

        // Add booking request
        public void addRequest(Reservation reservation) {
            queue.offer(reservation);
            System.out.println("Booking Request Added:");
            reservation.display();
        }

        // Display queue
        public void displayQueue() {
            System.out.println("\n--- Booking Request Queue (FIFO) ---\n");

            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v5.0     ");
        System.out.println("=================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guest booking requests
        bookingQueue.addRequest(new Reservation("Rahul", "Single Room"));
        bookingQueue.addRequest(new Reservation("Anita", "Double Room"));
        bookingQueue.addRequest(new Reservation("Kiran", "Suite Room"));

        // Display queue (FIFO order)
        bookingQueue.displayQueue();
    }
}