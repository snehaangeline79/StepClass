package Practice.Week8;

import java.time.LocalDate;

public class EmployeeLeaveRequestWorkflow {

    enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    static abstract class Employee {

        protected String name;

        public Employee(String name) {
            this.name = name;
        }

        public abstract boolean isLeaveAllowed(int days);

        public String getName() {
            return name;
        }
    }

    static class FullTimeEmployee extends Employee {

        public FullTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(int days) {
            return days <= 30;
        }
    }

    static class PartTimeEmployee extends Employee {

        public PartTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(int days) {
            return days <= 10;
        }
    }

    static class Contractor extends Employee {

        public Contractor(String name) {
            super(name);
        }

        @Override
        public boolean isLeaveAllowed(int days) {
            return days <= 5;
        }
    }

    static class LeaveRequest {

        private Employee employee;
        private LocalDate startDate;
        private LocalDate endDate;
        private Status status;

        public LeaveRequest(
                Employee employee,
                LocalDate startDate,
                LocalDate endDate) {

            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = Status.PENDING;
        }

        public void review(boolean approve) {

            if (status != Status.PENDING) {
                System.out.println(
                        "Cannot change leave request status from "
                                + status + " to Pending."
                );
                return;
            }

            int days = (int) (
                    endDate.toEpochDay()
                            - startDate.toEpochDay()
                            + 1
            );

            if (approve && employee.isLeaveAllowed(days)) {
                status = Status.APPROVED;

                System.out.println(
                        employee.getName()
                                + "'s leave request ("
                                + startDate + " to "
                                + endDate
                                + ") approved."
                );
            } else {
                status = Status.REJECTED;

                System.out.println(
                        employee.getName()
                                + "'s leave request ("
                                + startDate + " to "
                                + endDate
                                + ") rejected."
                );
            }

            System.out.println("Status: " + status);
        }

        public void changeToPending() {

            if (status != Status.PENDING) {
                System.out.println(
                        "Cannot change leave request status from "
                                + status + " to Pending."
                );
            }
        }
    }

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest = new LeaveRequest(
                john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5)
        );

        System.out.println(
                "Leave request submitted for John (2026-01-01 to 2026-01-05)."
        );
        System.out.println("Status: PENDING");

        johnRequest.review(true);

        LeaveRequest janeRequest = new LeaveRequest(
                jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11)
        );

        System.out.println(
                "Leave request submitted for Jane (2026-02-10 to 2026-02-11)."
        );
        System.out.println("Status: PENDING");

        janeRequest.review(false);

        johnRequest.changeToPending();
    }
}