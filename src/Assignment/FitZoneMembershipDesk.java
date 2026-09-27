package Assignment;

public class FitZoneMembershipDesk {

    interface MembershipPlan {
        String getName();
        int getMonths();
        double calculateFee();
    }

    static class MonthlyPlan implements MembershipPlan {

        public String getName() {
            return "Monthly";
        }

        public int getMonths() {
            return 1;
        }

        public double calculateFee() {
            return 1000;
        }
    }

    static class QuarterlyPlan implements MembershipPlan {

        public String getName() {
            return "Quarterly";
        }

        public int getMonths() {
            return 3;
        }

        public double calculateFee() {
            return 1000 * 3 * 0.90;
        }
    }

    static class AnnualPlan implements MembershipPlan {

        public String getName() {
            return "Annual";
        }

        public int getMonths() {
            return 12;
        }

        public double calculateFee() {
            return 1000 * 12 * 0.75;
        }
    }

    static class HalfYearlyPlan implements MembershipPlan {

        public String getName() {
            return "Half-Yearly";
        }

        public int getMonths() {
            return 6;
        }

        public double calculateFee() {
            return 1000 * 6 * 0.85;
        }
    }

    static class Member {
        private String name;
        private Membership membership;

        public Member(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void buyMembership(
                MembershipPlan plan) {

            membership =
                    new Membership(
                            this,
                            plan
                    );

            System.out.println(
                    plan.getName()
                            + " membership created for "
                            + name + "."
            );

            System.out.printf(
                    "Fee: ₹%.2f%n",
                    plan.calculateFee()
            );

            System.out.println(
                    "Status: Active."
            );
        }

        public void checkIn() {

            if (membership == null) {
                System.out.println(
                        "Check-in denied: "
                                + name
                                + " has no membership."
                );
                return;
            }

            membership.checkIn();
        }

        public void freeze() {
            membership.freeze();
        }

        public void unfreeze() {
            membership.unfreeze();
        }

        public void expire() {
            membership.expire();
        }
    }

    static class Membership {

        enum Status {
            ACTIVE,
            FROZEN,
            EXPIRED
        }

        private Member member;
        private MembershipPlan plan;
        private Status status;

        public Membership(Member member,
                          MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.status = Status.ACTIVE;
        }

        public void checkIn() {

            if (status != Status.ACTIVE) {
                System.out.println(
                        "Check-in denied: "
                                + member.getName()
                                + "'s membership is "
                                + status + "."
                );
                return;
            }

            System.out.println(
                    member.getName()
                            + " checked in successfully."
            );
        }

        public void freeze() {

            if (status == Status.EXPIRED) {
                System.out.println(
                        "Cannot freeze an Expired membership."
                );
                return;
            }

            if (status == Status.FROZEN) {
                System.out.println(
                        member.getName()
                                + "'s membership is already Frozen."
                );
                return;
            }

            status = Status.FROZEN;

            System.out.println(
                    member.getName()
                            + "'s membership frozen."
            );

            System.out.println(
                    "Status: Frozen."
            );
        }

        public void unfreeze() {

            if (status == Status.EXPIRED) {
                System.out.println(
                        "Cannot unfreeze an Expired membership."
                );
                return;
            }

            if (status == Status.ACTIVE) {
                System.out.println(
                        member.getName()
                                + "'s membership is already Active."
                );
                return;
            }

            status = Status.ACTIVE;

            System.out.println(
                    member.getName()
                            + "'s membership unfrozen."
            );

            System.out.println(
                    "Status: Active."
            );
        }

        public void expire() {

            if (status == Status.EXPIRED) {
                System.out.println(
                        "Membership is already Expired."
                );
                return;
            }

            status = Status.EXPIRED;

            System.out.println(
                    member.getName()
                            + "'s membership expired."
            );

            System.out.println(
                    "Status: Expired."
            );
        }
    }

    public static void main(String[] args) {

        Member asha =
                new Member("Asha");

        Member ravi =
                new Member("Ravi");

        asha.buyMembership(
                new QuarterlyPlan()
        );

        ravi.buyMembership(
                new MonthlyPlan()
        );

        asha.checkIn();

        asha.freeze();

        asha.checkIn();

        ravi.expire();

        ravi.freeze();
    }
}