package Practice.Week5;
import java.util.Arrays;

class BookingReceipt {
    private final String bookingId;
    private final String customerName;
    private final int[] seatNumbers;

    public BookingReceipt(String bookingId, String customerName, int[] seatNumbers) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.seatNumbers = seatNumbers.clone();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, int newSeat) {
        int[] updatedSeats = seatNumbers.clone();
        updatedSeats[index] = newSeat;

        return new BookingReceipt(
                bookingId,
                customerName,
                updatedSeats
        );
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String customerName,
            int[] seatNumbers,
            int groupSize) {

        super(bookingId, customerName, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class BookingReceiptSettlement {

    public static void main(String[] args) {

        BookingReceipt individual =
                new BookingReceipt(
                        "B101",
                        "Sneha",
                        new int[]{10, 11}
                );

        GroupBookingReceipt group =
                new GroupBookingReceipt(
                        "B102",
                        "Rahul",
                        new int[]{20, 21, 22},
                        3
                );

        BookingReceipt[] receipts = {
                individual,
                group,
                null
        };

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        System.out.println(
                processed + " processed | "
                        + nullSkipped + " null skipped | "
                        + groupCount + " group | "
                        + individualCount + " individual"
        );
    }
}