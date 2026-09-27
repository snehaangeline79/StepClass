package Practice.Week8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {

    static class Customer {

        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static abstract class Room {

        protected String roomNumber;

        public Room(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public abstract double calculatePrice(int nights);

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static class StandardRoom extends Room {

        public StandardRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int nights) {
            return nights * 100;
        }
    }

    static class DeluxeRoom extends Room {

        public DeluxeRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int nights) {
            return nights * 180;
        }
    }

    static class Suite extends Room {

        public Suite(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int nights) {
            return nights * 300;
        }
    }

    static class Reservation {

        private Customer customer;
        private Room room;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDate cancellationDeadline;
        private boolean cancelled;

        public Reservation(
                Customer customer,
                Room room,
                LocalDate startDate,
                LocalDate endDate,
                LocalDate cancellationDeadline) {

            this.customer = customer;
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
            this.cancellationDeadline = cancellationDeadline;
            this.cancelled = false;
        }

        public boolean overlaps(
                LocalDate newStart,
                LocalDate newEnd) {

            return !newEnd.isBefore(startDate)
                    && !newStart.isAfter(endDate);
        }

        public void cancel(LocalDate currentDate) {

            if (cancelled) {
                System.out.println("Reservation already cancelled.");
                return;
            }

            if (currentDate.isAfter(cancellationDeadline)) {
                System.out.println(
                        "Cancellation deadline has passed."
                );
                return;
            }

            cancelled = true;

            System.out.println(
                    "Reservation for "
                            + customer.getName()
                            + ", "
                            + room.getRoomNumber()
                            + " ("
                            + startDate
                            + " to "
                            + endDate
                            + ") cancelled successfully."
            );
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public Room getRoom() {
            return room;
        }

        public double getPrice() {
            long nights =
                    endDate.toEpochDay()
                            - startDate.toEpochDay();

            return room.calculatePrice((int) nights);
        }
    }

    static class Hotel {

        private List<Reservation> reservations =
                new ArrayList<>();

        public boolean isAvailable(
                Room room,
                LocalDate startDate,
                LocalDate endDate) {

            for (Reservation reservation : reservations) {

                if (reservation.getRoom() == room
                        && !reservation.isCancelled()
                        && reservation.overlaps(startDate, endDate)) {

                    return false;
                }
            }

            return true;
        }

        public void checkAvailability(
                Room room,
                LocalDate startDate,
                LocalDate endDate) {

            if (isAvailable(room, startDate, endDate)) {
                System.out.println(
                        room.getRoomNumber()
                                + " is available from "
                                + startDate
                                + " to "
                                + endDate + "."
                );
            } else {
                System.out.println(
                        room.getRoomNumber()
                                + " is not available from "
                                + startDate
                                + " to "
                                + endDate + "."
                );
            }
        }

        public Reservation reserve(
                Customer customer,
                Room room,
                LocalDate startDate,
                LocalDate endDate,
                LocalDate cancellationDeadline) {

            if (!isAvailable(room, startDate, endDate)) {
                System.out.println(
                        room.getRoomNumber()
                                + " is not available."
                );
                return null;
            }

            Reservation reservation =
                    new Reservation(
                            customer,
                            room,
                            startDate,
                            endDate,
                            cancellationDeadline
                    );

            reservations.add(reservation);

            System.out.println(
                    "Reservation confirmed for "
                            + customer.getName()
                            + ", "
                            + room.getRoomNumber()
                            + " ("
                            + startDate
                            + " to "
                            + endDate
                            + ")."
            );

            System.out.println(
                    "Price: $" + reservation.getPrice()
            );

            return reservation;
        }
    }

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Room standard101 =
                new StandardRoom("Standard Room 101");

        Room deluxe201 =
                new DeluxeRoom("Deluxe Room 201");

        Customer customerA =
                new Customer("Customer A");

        Customer customerB =
                new Customer("Customer B");

        Customer customerC =
                new Customer("Customer C");

        LocalDate jan1 =
                LocalDate.of(2026, 1, 1);

        LocalDate jan5 =
                LocalDate.of(2026, 1, 5);

        LocalDate jan3 =
                LocalDate.of(2026, 1, 3);

        LocalDate jan7 =
                LocalDate.of(2026, 1, 7);

        hotel.checkAvailability(
                standard101,
                jan1,
                jan5
        );

        Reservation reservation =
                hotel.reserve(
                        customerA,
                        standard101,
                        jan1,
                        jan5,
                        LocalDate.of(2025, 12, 30)
                );

        hotel.checkAvailability(
                standard101,
                jan3,
                jan7
        );

        if (reservation != null) {
            reservation.cancel(
                    LocalDate.of(2025, 12, 29)
            );
        }

        hotel.reserve(
                customerC,
                deluxe201,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12),
                LocalDate.of(2026, 2, 5)
        );
    }
}