package Assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CampusPremiereTicketCounter {

    static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    interface Seat {
        String getSeatNumber();
        double getPrice();
    }

    static class RegularSeat implements Seat {
        private String seatNumber;

        public RegularSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 150;
        }
    }

    static class PremiumSeat implements Seat {
        private String seatNumber;

        public PremiumSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 250;
        }
    }

    static class ReclinerSeat implements Seat {
        private String seatNumber;

        public ReclinerSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 400;
        }
    }

    static class Show {
        private String showTime;
        private boolean started;
        private Set<String> bookedSeats = new HashSet<>();

        public Show(String showTime) {
            this.showTime = showTime;
            this.started = false;
        }

        public boolean isStarted() {
            return started;
        }

        public void startShow() {
            started = true;
        }

        public boolean isAvailable(Seat seat) {
            return !bookedSeats.contains(
                    seat.getSeatNumber()
            );
        }

        public boolean bookSeat(Seat seat) {
            if (!isAvailable(seat)) {
                return false;
            }

            bookedSeats.add(seat.getSeatNumber());
            return true;
        }

        public void releaseSeat(Seat seat) {
            bookedSeats.remove(seat.getSeatNumber());
        }

        public String getShowTime() {
            return showTime;
        }
    }

    static class Booking {
        private Customer customer;
        private Show show;
        private List<Seat> seats;
        private boolean cancelled;

        public Booking(Customer customer,
                       Show show,
                       List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = seats;
            this.cancelled = false;
        }

        public double calculateTotal() {
            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        public void cancel() {

            if (cancelled) {
                System.out.println(
                        "Booking is already cancelled."
                );
                return;
            }

            if (show.isStarted()) {
                System.out.println(
                        "Cannot cancel: show has already started."
                );
                return;
            }

            for (Seat seat : seats) {
                show.releaseSeat(seat);
            }

            cancelled = true;

            System.out.println(
                    customer.getName()
                            + "'s booking cancelled."
            );

            System.out.print("Seats ");

            for (int i = 0; i < seats.size(); i++) {
                System.out.print(
                        seats.get(i).getSeatNumber()
                );

                if (i < seats.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(" released.");
        }
    }

    static class TicketCounter {

        public Booking book(Customer customer,
                            Show show,
                            List<Seat> seats) {

            if (seats.isEmpty()) {
                System.out.println(
                        "Booking must contain at least one seat."
                );
                return null;
            }

            if (seats.size() > 6) {
                System.out.println(
                        "Maximum 6 seats allowed per booking."
                );
                return null;
            }

            for (Seat seat : seats) {
                if (!show.isAvailable(seat)) {
                    System.out.println(
                            "Seat "
                                    + seat.getSeatNumber()
                                    + " is already booked for this show."
                    );
                    return null;
                }
            }

            for (Seat seat : seats) {
                show.bookSeat(seat);
            }

            Booking booking =
                    new Booking(customer, show, seats);

            System.out.print(
                    "Booking confirmed for "
                            + customer.getName()
                            + ": "
            );

            for (int i = 0; i < seats.size(); i++) {
                System.out.print(
                        seats.get(i).getSeatNumber()
                );

                if (i < seats.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.printf(
                    ". Total: ₹%.2f%n",
                    booking.calculateTotal()
            );

            return booking;
        }
    }

    public static void main(String[] args) {

        TicketCounter counter =
                new TicketCounter();

        Show show =
                new Show("7 PM");

        Customer asha =
                new Customer("Asha");

        Customer ravi =
                new Customer("Ravi");

        Customer neha =
                new Customer("Neha");

        Seat a1 =
                new RegularSeat("A1");

        Seat a2 =
                new RegularSeat("A2");

        Seat f5 =
                new PremiumSeat("F5");

        Seat r1 =
                new ReclinerSeat("R1");

        List<Seat> ashaSeats =
                new ArrayList<>();

        ashaSeats.add(a1);
        ashaSeats.add(a2);
        ashaSeats.add(f5);

        Booking ashaBooking =
                counter.book(
                        asha,
                        show,
                        ashaSeats
                );

        List<Seat> raviA2 =
                new ArrayList<>();

        raviA2.add(a2);

        counter.book(
                ravi,
                show,
                raviA2
        );

        List<Seat> raviSeats =
                new ArrayList<>();

        raviSeats.add(r1);

        counter.book(
                ravi,
                show,
                raviSeats
        );

        if (ashaBooking != null) {
            ashaBooking.cancel();
        }

        List<Seat> nehaSeats =
                new ArrayList<>();

        nehaSeats.add(a2);

        counter.book(
                neha,
                show,
                nehaSeats
        );
    }
}