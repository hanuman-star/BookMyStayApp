import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Use Case 3 - Centralized Room Inventory Management
 * @version 3.0
 */

public class BookMyStayApp {

    // RoomInventory Class
    static class RoomInventory {

        private HashMap<String, Integer> inventory;

        // Constructor
        public RoomInventory() {
            inventory = new HashMap<>();

            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }

        // Get availability
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        // Update availability
        public void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }

        // Display inventory
        public void displayInventory() {
            System.out.println("\n--- Room Inventory ---");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " Available : " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v3.0     ");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Update example
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();
    }
}