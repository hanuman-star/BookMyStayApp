import java.util.*;

/**
 * Book My Stay App
 * Use Case 8 - Booking History & Reporting
 * @version 8.0
 */

public class BookMyStayApp {

    // Reservation
    static class Reservation {
        String reservationId;
        String guestName;
        String roomType;

        public Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public void display() {
            System.out.println(
                    "ID: " + reservationId +
                            " | Guest: " + guestName +
                            " | Room: " + roomType
            );
        }
    }

    // Booking History
    static class BookingHistory {

        private List<Reservation> history;

        public BookingHistory() {
            history = new ArrayList<>();
        }

        // Add confirmed booking
        public void add(Reservation r) {
            history.add(r);
        }

        // Get history
        public List<Reservation> getAll() {
            return history;
        }
    }

    // Report Service
    static class BookingReportService {

        public void displayAll(BookingHistory history) {

            System.out.println("\n--- Booking History ---\n");

            for (Reservation r : history.getAll()) {
                r.display();
            }
        }

        public void summary(BookingHistory history) {

            System.out.println("\n--- Booking Summary ---");

            Map<String, Integer> count = new HashMap<>();

            for (Reservation r : history.getAll()) {
                count.put(r.roomType,
                        count.getOrDefault(r.roomType, 0) + 1);
            }

            for (String type : count.keySet()) {
                System.out.println(type + " Booked : " + count.get(type));
            }

            System.out.println("Total Bookings : " + history.getAll().size());
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v8.0     ");
        System.out.println("=================================");

        BookingHistory history = new BookingHistory();

        // confirmed bookings
        history.add(new Reservation("SR101", "Rahul", "Single Room"));
        history.add(new Reservation("DR201", "Anita", "Double Room"));
        history.add(new Reservation("SR102", "Kiran", "Single Room"));

        BookingReportService report = new BookingReportService();

        report.displayAll(history);
        report.summary(history);
    }
}