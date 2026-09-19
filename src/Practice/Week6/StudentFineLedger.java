package Practice.Week6;

import java.util.Arrays;

public class StudentFineLedger {

    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;

        private int[] fineHistory;
        private int fineCount;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            if (borrowLimit <= 0) {
                throw new IllegalArgumentException("Invalid borrow limit");
            }

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.fineHistory = new int[10];
            this.fineCount = 0;
        }

        protected void chargeFine(int amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Fine must be positive");
            }

            if (fineCount < fineHistory.length) {
                fineHistory[fineCount] = amount;
                fineCount++;
            }
        }

        public int[] getFineHistory() {
            return Arrays.copyOf(fineHistory, fineCount);
        }

        public int getTotalFine() {
            int total = 0;

            for (int i = 0; i < fineCount; i++) {
                total += fineHistory[i];
            }

            return total;
        }
    }

    static class StudentMember extends LibraryMember {

        public StudentMember(String memberId, int borrowLimit) {
            super(memberId, borrowLimit);
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {

        StudentMember student =
                new StudentMember("STU10", 3);

        student.chargeFine(100);

        System.out.println("Total fine: " +
                student.getTotalFine());

        int[] history = student.getFineHistory();

        System.out.println("Fine history: " +
                Arrays.toString(history));

        history[0] = 9999;

        System.out.println("After modifying returned array: " +
                Arrays.toString(student.getFineHistory()));

        System.out.println("Actual total fine: " +
                student.getTotalFine());
    }
}
