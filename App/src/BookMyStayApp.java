import java.util.*;

/**
 * Book My Stay App
 * Use Case 7 - Add-On Service Selection
 * @version 7.0
 */

public class BookMyStayApp {

    // Service Class
    static class AddOnService {
        String name;
        double price;

        public AddOnService(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public void display() {
            System.out.println(name + " - ₹" + price);
        }
    }

    // Service Manager
    static class AddOnServiceManager {

        private Map<String, List<AddOnService>> reservationServices;

        public AddOnServiceManager() {
            reservationServices = new HashMap<>();
        }

        // Add service to reservation
        public void addService(String reservationId, AddOnService service) {
            reservationServices
                    .computeIfAbsent(reservationId, k -> new ArrayList<>())
                    .add(service);

            System.out.println("Added Service: " + service.name + " to " + reservationId);
        }

        // Display services
        public void displayServices(String reservationId) {
            System.out.println("\nServices for Reservation: " + reservationId);

            List<AddOnService> services = reservationServices.get(reservationId);

            if (services == null) {
                System.out.println("No services selected.");
                return;
            }

            double total = 0;

            for (AddOnService s : services) {
                s.display();
                total += s.price;
            }

            System.out.println("Total Add-on Cost: ₹" + total);
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v7.0     ");
        System.out.println("=================================");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "SR101";

        // Guest selects services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1200));
        manager.addService(reservationId, new AddOnService("Extra Bed", 800));

        // Display selected services
        manager.displayServices(reservationId);
    }
}