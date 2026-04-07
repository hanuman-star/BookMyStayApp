import java.util.*;

/**
 * Book My Stay App
 * Use Case 10 - Booking Cancellation & Inventory Rollback
 * @version 10.0
 */

public class BookMyStayApp {

    // Reservation
    static class Reservation {
        String reservationId;
        String roomType;

        public Reservation(String reservationId, String roomType) {
            this.reservationId = reservationId;
            this.roomType = roomType;
        }
    }

    // Inventory
    static class RoomInventory {

        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        public void increment(String roomType) {
            inventory.put(roomType, inventory.get(roomType) + 1);
        }

        public void decrement(String roomType) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        }

        public void display() {
            System.out.println("\nInventory State:");
            for (String key : inventory.keySet()) {
                System.out.println(key + " : " + inventory.get(key));
            }
        }
    }

    // Booking History
    static class BookingHistory {

        private Map<String, Reservation> bookings = new HashMap<>();

        public void add(Reservation r) {
            bookings.put(r.reservationId, r);
        }

        public Reservation get(String id) {
            return bookings.get(id);
        }

        public void remove(String id) {
            bookings.remove(id);
        }
    }

    // Cancellation Service
    static class CancellationService {

        private Stack<String> rollbackStack = new Stack<>();

        public void cancel(String reservationId,
                           BookingHistory history,
                           RoomInventory inventory) {

            Reservation r = history.get(reservationId);

            if (r == null) {
                System.out.println("Cancellation Failed: Reservation not found");
                return;
            }

            // push released room id
            rollbackStack.push(reservationId);

            // restore inventory
            inventory.increment(r.roomType);

            // remove booking
            history.remove(reservationId);

            System.out.println("Cancellation Successful for " + reservationId);
        }

        public void displayRollbackStack() {
            System.out.println("\nRollback Stack: " + rollbackStack);
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v10.0    ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancelService = new CancellationService();

        // confirmed bookings
        history.add(new Reservation("SR101", "Single Room"));
        history.add(new Reservation("DR201", "Double Room"));

        inventory.decrement("Single Room");
        inventory.decrement("Double Room");

        inventory.display();

        // cancel booking
        cancelService.cancel("SR101", history, inventory);

        inventory.display();

        cancelService.displayRollbackStack();
    }
}