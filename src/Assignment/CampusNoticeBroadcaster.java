package Assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CampusNoticeBroadcaster {

    interface NotificationChannel {
        void send(Student student, Notice notice);
    }

    static class EmailChannel implements NotificationChannel {

        @Override
        public void send(Student student, Notice notice) {
            System.out.println(
                    "[Email → "
                            + student.getName()
                            + "] "
                            + notice.getTitle()
            );
        }
    }

    static class SmsChannel implements NotificationChannel {

        @Override
        public void send(Student student, Notice notice) {
            System.out.println(
                    "[SMS → "
                            + student.getName()
                            + "] "
                            + notice.getTitle()
            );
        }
    }

    static class AppChannel implements NotificationChannel {

        @Override
        public void send(Student student, Notice notice) {
            System.out.println(
                    "[App → "
                            + student.getName()
                            + "] "
                            + notice.getTitle()
            );
        }
    }

    static class WhatsAppChannel
            implements NotificationChannel {

        @Override
        public void send(Student student, Notice notice) {
            System.out.println(
                    "[WhatsApp → "
                            + student.getName()
                            + "] "
                            + notice.getTitle()
            );
        }
    }

    static class Student {
        private String name;
        private String department;
        private List<NotificationChannel> channels;

        public Student(String name,
                       String department) {

            this.name = name;
            this.department = department;
            this.channels = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public void addChannel(
                NotificationChannel channel) {

            channels.add(channel);
        }

        public List<NotificationChannel> getChannels() {
            return channels;
        }
    }

    static class Notice {
        private String title;
        private Set<String> targetDepartments;

        public Notice(String title,
                      Set<String> targetDepartments) {

            this.title = title;
            this.targetDepartments =
                    new HashSet<>(targetDepartments);
        }

        public String getTitle() {
            return title;
        }

        public Set<String> getTargetDepartments() {
            return targetDepartments;
        }

        public boolean isValid() {
            return title != null
                    && !title.trim().isEmpty()
                    && !targetDepartments.isEmpty();
        }
    }

    static class NoticeBoard {
        private List<Student> students =
                new ArrayList<>();

        public void addStudent(Student student) {
            students.add(student);
        }

        public void postNotice(Notice notice) {

            if (notice == null
                    || notice.getTitle() == null
                    || notice.getTitle().trim().isEmpty()) {

                System.out.println(
                        "Cannot post notice: Title is required."
                );
                return;
            }

            if (notice.getTargetDepartments().isEmpty()) {

                System.out.println(
                        "Cannot post notice: At least one target department is required."
                );
                return;
            }

            System.out.print(
                    "Notice '"
                            + notice.getTitle()
                            + "' posted to "
            );

            int count = 0;

            for (String department
                    : notice.getTargetDepartments()) {

                System.out.print(department);

                count++;

                if (count <
                        notice.getTargetDepartments().size()) {
                    System.out.print(", ");
                }
            }

            System.out.println(".");

            for (Student student : students) {

                if (notice.getTargetDepartments()
                        .contains(student.getDepartment())) {

                    for (NotificationChannel channel
                            : student.getChannels()) {

                        channel.send(
                                student,
                                notice
                        );
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        NoticeBoard board =
                new NoticeBoard();

        Student asha =
                new Student("Asha", "CSE");

        Student ravi =
                new Student("Ravi", "ECE");

        asha.addChannel(
                new EmailChannel()
        );

        asha.addChannel(
                new AppChannel()
        );

        ravi.addChannel(
                new SmsChannel()
        );

        board.addStudent(asha);
        board.addStudent(ravi);

        Set<String> cse =
                new HashSet<>();

        cse.add("CSE");

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        cse
                );

        board.postNotice(notice1);

        Set<String> cseEce =
                new HashSet<>();

        cseEce.add("CSE");
        cseEce.add("ECE");

        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        cseEce
                );

        board.postNotice(notice2);

        Set<String> noDepartment =
                new HashSet<>();

        Notice notice3 =
                new Notice(
                        "Sports Day",
                        noDepartment
                );

        board.postNotice(notice3);
    }
}