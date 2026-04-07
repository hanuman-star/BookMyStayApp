/**
 * Book My Stay App
 * Use Case 2 - Basic Room Types & Static Availability
 * @version 2.0
 */

public class BookMyStayApp {

    // Abstract Room Class
    static abstract class Room {
        protected String roomType;
        protected int beds;
        protected double price;

        public Room(String roomType, int beds, double price) {
            this.roomType = roomType;
            this.beds = beds;
            this.price = price;
        }

        public void displayRoomDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Beds      : " + beds);
            System.out.println("Price     : " + price);
        }
    }

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 2000);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 3500);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 6000);
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("   Hotel Booking System v2.0     ");
        System.out.println("=================================");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n--- Room Availability ---\n");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailable);
    }
}