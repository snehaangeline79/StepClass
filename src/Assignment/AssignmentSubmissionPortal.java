package Assignment;

import java.time.LocalDate;

public class AssignmentSubmissionPortal {

    static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static abstract class Assignment {
        protected String title;
        protected int maxMarks;
        protected LocalDate dueDate;

        public Assignment(String title,
                          int maxMarks,
                          LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        public abstract double applyLatePenalty(
                double marks,
                long lateDays
        );

        public String getTitle() {
            return title;
        }

        public int getMaxMarks() {
            return maxMarks;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }
    }

    static class CodingAssignment extends Assignment {

        public CodingAssignment(String title,
                                int maxMarks,
                                LocalDate dueDate) {
            super(title, maxMarks, dueDate);
        }

        @Override
        public double applyLatePenalty(
                double marks,
                long lateDays) {

            double penalty = lateDays * 0.10;

            return Math.max(0, marks * (1 - penalty));
        }
    }

    static class WrittenAssignment extends Assignment {

        public WrittenAssignment(String title,
                                 int maxMarks,
                                 LocalDate dueDate) {
            super(title, maxMarks, dueDate);
        }

        @Override
        public double applyLatePenalty(
                double marks,
                long lateDays) {

            double penalty = lateDays * 0.20;

            return Math.max(0, marks * (1 - penalty));
        }
    }

    enum Status {
        SUBMITTED,
        GRADED
    }

    static class Submission {
        private Student student;
        private Assignment assignment;
        private LocalDate submissionDate;
        private Status status;
        private double finalMarks;

        public Submission(Student student,
                          Assignment assignment,
                          LocalDate submissionDate) {

            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
            this.status = Status.SUBMITTED;
        }

        public void grade(double awardedMarks) {

            if (status != Status.SUBMITTED) {
                System.out.println(
                        "Cannot grade: submission is already graded."
                );
                return;
            }

            long lateDays = 0;

            if (submissionDate.isAfter(
                    assignment.getDueDate())) {

                lateDays =
                        submissionDate.toEpochDay()
                                - assignment.getDueDate().toEpochDay();
            }

            finalMarks =
                    assignment.applyLatePenalty(
                            awardedMarks,
                            lateDays
                    );

            status = Status.GRADED;

            System.out.printf(
                    "%s graded: %.0f/%d",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks()
            );

            if (lateDays > 0) {
                System.out.printf(
                        " after %d day%s late penalty.",
                        lateDays,
                        lateDays == 1 ? "" : "s"
                );
            }

            System.out.println();
            System.out.println("Status: Graded");
        }

        public void resubmit(LocalDate newDate) {

            if (status == Status.GRADED) {
                System.out.println(
                        "Cannot resubmit: '"
                                + assignment.getTitle()
                                + "' has already been graded."
                );
                return;
            }

            submissionDate = newDate;

            System.out.println(
                    student.getName()
                            + " resubmitted '"
                            + assignment.getTitle()
                            + "'."
            );
        }
    }

    static class Portal {

        public Submission submit(
                Student student,
                Assignment assignment,
                LocalDate date) {

            long lateDays = 0;

            if (date.isAfter(assignment.getDueDate())) {
                lateDays =
                        date.toEpochDay()
                                - assignment.getDueDate().toEpochDay();
            }

            Submission submission =
                    new Submission(
                            student,
                            assignment,
                            date
                    );

            if (lateDays == 0) {
                System.out.println(
                        student.getName()
                                + "'s submission for '"
                                + assignment.getTitle()
                                + "' received (on time)."
                );
            } else {
                System.out.println(
                        student.getName()
                                + "'s submission for '"
                                + assignment.getTitle()
                                + "' received ("
                                + lateDays
                                + " days late)."
                );
            }

            System.out.println("Status: Submitted");

            return submission;
        }
    }

    public static void main(String[] args) {

        Portal portal = new Portal();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10)
                );

        Assignment written =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12)
                );

        Submission ashaSubmission =
                portal.submit(
                        asha,
                        coding,
                        LocalDate.of(2026, 3, 10)
                );

        Submission raviSubmission =
                portal.submit(
                        ravi,
                        written,
                        LocalDate.of(2026, 3, 14)
                );

        ashaSubmission.grade(45);
        raviSubmission.grade(40);

        ashaSubmission.resubmit(
                LocalDate.of(2026, 3, 11)
        );
    }
}