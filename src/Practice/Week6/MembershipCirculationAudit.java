package Practice.Week6;

public class MembershipCirculationAudit {

    static class LibraryMember {

        private static int memberCounter = 100;

        public final String memberNumber;

        protected int borrowLimit;
        protected int booksBorrowed;
        protected String lastGenre;

        public LibraryMember(int borrowLimit) {

            if (borrowLimit <= 0) {
                throw new IllegalArgumentException("Invalid borrow limit");
            }

            memberCounter++;

            memberNumber = "LIB-" + memberCounter;

            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
            this.lastGenre = "";
        }

        public void borrowBook() {
            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public void borrowBook(String genre) {
            lastGenre = genre;
            borrowBook();
        }

        public static int getMembersEnrolled() {
            return memberCounter - 100;
        }
    }

    static class FacultyMember extends LibraryMember {

        private String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }
    }

    static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                faculty + " faculty | " +
                regular + " regular";
    }

    public static void main(String[] args) {

        LibraryMember m1 =
                new LibraryMember(3);

        System.out.println("Member Number: " +
                m1.memberNumber);

        System.out.println("Members enrolled: " +
                LibraryMember.getMembersEnrolled());

        System.out.println("R12A: " +
                isValidRenewalCode("R12A"));

        System.out.println("R1A: " +
                isValidRenewalCode("R1A"));

        System.out.println("X12A: " +
                isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println("Books borrowed: " +
                m1.booksBorrowed);

        LibraryMember[] members = {
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        };

        System.out.println(processNightlyAudit(members));
    }
}
