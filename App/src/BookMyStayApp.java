import java.util.*;

/**
 * Book My Stay App
 * Use Case 9 - Error Handling & Validation
 * @version 9.0
 */

public class BookMyStayApp {

    // Custom Exception
    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    // Reservation
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // Inventory
    static class RoomInventory {

        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 0);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, -1);
        }

        public void decrement(String roomType) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        }

        public boolean containsRoom(String roomType) {
            return inventory.containsKey(roomType);
        }
    }

    // Validator
    static class InvalidBookingValidator {

        public void validate(Reservation r, RoomInventory inventory)
                throws InvalidBookingException {

            // Validate room type
            if (!inventory.containsRoom(r.roomType)) {
                throw new InvalidBookingException(
                        "Invalid Room Type: " + r.roomType);
            }

            // Validate availability
            if (inventory.getAvailability(r.roomType) <= 0) {
                throw new InvalidBookingException(
                        "No rooms available for: " + r.roomType);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v9.0     ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        InvalidBookingValidator validator = new InvalidBookingValidator();

        // Test bookings
        Reservation[] requests = {
                new Reservation("Rahul", "Single Room"),
                new Reservation("Anita", "Suite Room"), // unavailable
                new Reservation("Kiran", "Luxury Room") // invalid
        };

        for (Reservation r : requests) {

            try {
                System.out.println("\nProcessing: " + r.guestName +
                        " -> " + r.roomType);

                validator.validate(r, inventory);

                inventory.decrement(r.roomType);

                System.out.println("Booking Confirmed for " + r.guestName);

            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }
}