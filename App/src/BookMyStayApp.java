import java.util.*;

/**
 * Book My Stay App
 * Use Case 11 - Concurrent Booking Simulation (Thread Safety)
 * @version 11.0
 */

public class BookMyStayApp {

    // Reservation
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // Thread-safe Inventory
    static class RoomInventory {

        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1);
            inventory.put("Double Room", 1);
        }

        // synchronized critical section
        public synchronized boolean allocate(String roomType) {

            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {
                inventory.put(roomType, available - 1);
                return true;
            }
            return false;
        }

        public synchronized void display() {
            System.out.println("\nInventory:");
            for (String k : inventory.keySet()) {
                System.out.println(k + " : " + inventory.get(k));
            }
        }
    }

    // Shared Booking Queue
    static class BookingQueue {

        private Queue<Reservation> queue = new LinkedList<>();

        public synchronized void add(Reservation r) {
            queue.offer(r);
        }

        public synchronized Reservation get() {
            return queue.poll();
        }
    }

    // Concurrent Booking Processor
    static class BookingProcessor extends Thread {

        private BookingQueue queue;
        private RoomInventory inventory;

        public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
            this.queue = queue;
            this.inventory = inventory;
        }

        public void run() {

            Reservation r = queue.get();

            if (r != null) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " processing " + r.guestName);

                boolean success = inventory.allocate(r.roomType);

                if (success) {
                    System.out.println(
                            "Confirmed: " + r.guestName +
                                    " -> " + r.roomType);
                } else {
                    System.out.println(
                            "Failed: No rooms for " + r.guestName);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v11.0    ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // concurrent requests
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Anita", "Single Room"));
        queue.add(new Reservation("Kiran", "Double Room"));

        // multiple threads
        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);
        Thread t3 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        inventory.display();
    }
}