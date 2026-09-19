package Practice.Week6;
public class WeeklyCirculationReport {
    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
        }

        public void borrowBook() {
            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public String displayInfo() {
            return "General";
        }
    }

    static class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(String memberId, int borrowLimit,
                             String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        public String displayInfo() {
            return "Student";
        }

        public String getCourse() {
            return course;
        }
    }

    static String batchPrint(LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {

            report.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;

                report.append(" | Course: ")
                        .append(student.getCourse());
            }

            report.append(" | Books: ")
                    .append(member.getBooksBorrowed());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;

                report.append(" [Course via downcast: ")
                        .append(student.getCourse())
                        .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember plain =
                new LibraryMember("GEN1", 3);

        StudentMember student =
                new StudentMember("STU1", 3, "ECE");

        LibraryMember[] members = {
                plain, student
        };

        System.out.println(batchPrint(members));

        // Unsafe cast example:
        // StudentMember bad = (StudentMember) plain;
        // This causes ClassCastException at runtime.
    }
}