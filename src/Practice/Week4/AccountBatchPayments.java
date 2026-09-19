package Practice.Week4;
class PaymentFeeAccount {
}

class HostelFeeAccount extends PaymentFeeAccount {
}

class PaymentProcessor {

    int hostelCount = 0;
    int dayScholarCount = 0;

    void processPayment(PaymentFeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
            hostelCount++;
        } else {
            System.out.println("Paid in one go (day-scholar account)");
            dayScholarCount++;
        }
    }

    void printCounts() {
        System.out.println("Hostel accounts processed: " + hostelCount
                + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}

public class AccountBatchPayments {
    public static void main(String[] args) {

        PaymentFeeAccount[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new PaymentFeeAccount(),
                new PaymentFeeAccount()
        };

        double amount = 60000;

        PaymentProcessor processor = new PaymentProcessor();

        for (PaymentFeeAccount account : accounts) {
            processor.processPayment(account, amount);
        }

        processor.printCounts();
    }
}