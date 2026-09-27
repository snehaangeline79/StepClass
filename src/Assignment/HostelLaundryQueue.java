package Assignment;

import java.util.ArrayList;
import java.util.List;

public class HostelLaundryQueue {

    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() {
            return 30;
        }

        public double getCharge() {
            return 20;
        }

        public String getName() {
            return "Quick";
        }
    }

    static class NormalWash implements WashType {
        public int getDuration() {
            return 45;
        }

        public double getCharge() {
            return 30;
        }

        public String getName() {
            return "Normal";
        }
    }

    static class HeavyWash implements WashType {
        public int getDuration() {
            return 60;
        }

        public double getCharge() {
            return 45;
        }

        public String getName() {
            return "Heavy";
        }
    }

    static class DelicateWash implements WashType {
        public int getDuration() {
            return 50;
        }

        public double getCharge() {
            return 40;
        }

        public String getName() {
            return "Delicate";
        }
    }

    static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class WashingMachine {
        private String machineId;
        private boolean busy;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
            this.busy = false;
        }

        public String getMachineId() {
            return machineId;
        }

        public boolean isBusy() {
            return busy;
        }

        private void setBusy(boolean busy) {
            this.busy = busy;
        }
    }

    static class WashCycle {
        private Student student;
        private WashingMachine machine;
        private WashType washType;

        public WashCycle(Student student,
                         WashingMachine machine,
                         WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public void complete() {
            machine.setBusy(false);

            System.out.println(
                    machine.getMachineId() + " cycle completed."
            );
            System.out.println(
                    machine.getMachineId() + " is now free."
            );
        }
    }

    static class LaundrySystem {
        private List<WashCycle> cycles = new ArrayList<>();

        public void startWash(Student student,
                              WashingMachine machine,
                              WashType washType) {

            if (machine.isBusy()) {
                System.out.println(
                        "Machine " + machine.getMachineId()
                                + " is currently busy."
                );
                return;
            }

            machine.setBusy(true);

            WashCycle cycle =
                    new WashCycle(student, machine, washType);

            cycles.add(cycle);

            System.out.printf(
                    "%s wash started on %s for %s (%d min).%n",
                    washType.getName(),
                    machine.getMachineId(),
                    student.getName(),
                    washType.getDuration()
            );

            System.out.printf(
                    "Charge: ₹%.2f%n",
                    washType.getCharge()
            );
        }

        public void completeWash(WashingMachine machine) {
            for (WashCycle cycle : cycles) {
                if (cycle.machine == machine
                        && machine.isBusy()) {
                    cycle.complete();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        LaundrySystem laundry = new LaundrySystem();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 =
                new WashingMachine("M1");

        WashingMachine m2 =
                new WashingMachine("M2");

        laundry.startWash(
                asha, m1, new QuickWash()
        );

        laundry.startWash(
                ravi, m1, new HeavyWash()
        );

        laundry.startWash(
                ravi, m2, new HeavyWash()
        );

        laundry.completeWash(m1);

        laundry.startWash(
                neha, m1, new NormalWash()
        );
    }
}