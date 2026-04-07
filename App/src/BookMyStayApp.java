import java.util.*;

/**
 * Book My Stay App
 * Use Case 6 - Reservation Confirmation & Room Allocation
 * @version 6.0
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

    // Inventory Service
    static class RoomInventory {

        private HashMap<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 2);
            inventory.put("Suite Room", 1);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public void decrement(String roomType) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        }
    }

    // Booking Queue
    static class BookingRequestQueue {

        Queue<Reservation> queue = new LinkedList<>();

        public void add(Reservation r) {
            queue.offer(r);
        }

        public Reservation next() {
            return queue.poll();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // Booking Service (Allocation)
    static class BookingService {

        private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
        private int idCounter = 1;

        public void processBookings(BookingRequestQueue queue, RoomInventory inventory) {

            while (!queue.isEmpty()) {

                Reservation r = queue.next();
                String type = r.roomType;

                System.out.println("\nProcessing: " + r.guestName + " -> " + type);

                if (inventory.getAvailability(type) > 0) {

                    // generate unique room id
                    String roomId = type.substring(0,2).toUpperCase() + idCounter++;

                    allocatedRooms.putIfAbsent(type, new HashSet<>());
                    allocatedRooms.get(type).add(roomId);

                    inventory.decrement(type);

                    System.out.println("Reservation Confirmed");
                    System.out.println("Guest : " + r.guestName);
                    System.out.println("Room  : " + type);
                    System.out.println("RoomID: " + roomId);

                } else {
                    System.out.println("No rooms available for " + type);
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v6.0     ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        // Requests (FIFO)
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Anita", "Single Room"));
        queue.add(new Reservation("Kiran", "Single Room"));
        queue.add(new Reservation("Vijay", "Suite Room"));

        BookingService service = new BookingService();
        service.processBookings(queue, inventory);
    }
}