import java.io.*;
import java.util.*;

/**
 * Book My Stay App
 * Use Case 12 - Data Persistence & System Recovery
 * @version 12.0
 */

public class BookMyStayApp {

    // Reservation
    static class Reservation implements Serializable {
        String reservationId;
        String roomType;

        public Reservation(String reservationId, String roomType) {
            this.reservationId = reservationId;
            this.roomType = roomType;
        }
    }

    // Inventory
    static class RoomInventory implements Serializable {

        Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
        }

        public void display() {
            System.out.println("\nInventory:");
            for (String k : inventory.keySet()) {
                System.out.println(k + " : " + inventory.get(k));
            }
        }
    }

    // Booking History
    static class BookingHistory implements Serializable {

        List<Reservation> history = new ArrayList<>();

        public void add(Reservation r) {
            history.add(r);
        }

        public void display() {
            System.out.println("\nBookings:");
            for (Reservation r : history) {
                System.out.println(r.reservationId + " -> " + r.roomType);
            }
        }
    }

    // Persistence Service
    static class PersistenceService {

        private static final String FILE = "bookmystay.dat";

        // save state
        public void save(RoomInventory inventory,
                         BookingHistory history) {

            try (ObjectOutputStream out =
                         new ObjectOutputStream(
                                 new FileOutputStream(FILE))) {

                out.writeObject(inventory);
                out.writeObject(history);

                System.out.println("\nSystem state saved.");

            } catch (IOException e) {
                System.out.println("Save failed.");
            }
        }

        // load state
        public Object[] load() {

            try (ObjectInputStream in =
                         new ObjectInputStream(
                                 new FileInputStream(FILE))) {

                RoomInventory inventory =
                        (RoomInventory) in.readObject();

                BookingHistory history =
                        (BookingHistory) in.readObject();

                System.out.println("System state restored.");

                return new Object[]{inventory, history};

            } catch (Exception e) {
                System.out.println("No previous data found. Starting fresh.");
                return null;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v12.0    ");
        System.out.println("=================================");

        PersistenceService service = new PersistenceService();

        RoomInventory inventory;
        BookingHistory history;

        // load previous state
        Object[] data = service.load();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            history = (BookingHistory) data[1];
        } else {
            inventory = new RoomInventory();
            history = new BookingHistory();
        }

        // simulate booking
        history.add(new Reservation("SR101", "Single Room"));

        // display current state
        inventory.display();
        history.display();

        // save state
        service.save(inventory, history);
    }
}