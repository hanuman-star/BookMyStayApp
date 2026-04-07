import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Use Case 4 - Room Search & Availability Check
 * @version 4.0
 */

public class BookMyStayApp {

    // Abstract Room
    static abstract class Room {
        protected String roomType;
        protected double price;

        public Room(String roomType, double price) {
            this.roomType = roomType;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Price     : " + price);
        }
    }

    // Room Types
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 2000);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 3500);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 6000);
        }
    }

    // Inventory (Read-only usage)
    static class RoomInventory {
        private HashMap<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 0); // unavailable
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }
    }

    // Search Service
    static class RoomSearchService {

        public void searchRooms(RoomInventory inventory) {

            Room[] rooms = {
                    new SingleRoom(),
                    new DoubleRoom(),
                    new SuiteRoom()
            };

            System.out.println("\n--- Available Rooms ---\n");

            for (Room room : rooms) {

                int available = inventory.getAvailability(room.roomType);

                // show only available rooms
                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available : " + available);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v4.0     ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        RoomSearchService search = new RoomSearchService();

        // Read-only search
        search.searchRooms(inventory);
    }
}