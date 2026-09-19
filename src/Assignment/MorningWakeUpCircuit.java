package Assignment;
public class MorningWakeUpCircuit
{
    interface Ringable {
        String ring();
    }

    static class AlarmClock implements Ringable {
        private String time;

        public AlarmClock(String time) {
            this.time = time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + time;
        }
    }

    static class Doorbell implements Ringable {
        private String location;

        public Doorbell(String location) {
            this.location = location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + location;
        }
    }

    static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }

    public static void main(String[] args) {

        AlarmClock alarm = new AlarmClock("7:00 AM");
        Doorbell doorbell = new Doorbell("Front Door");

        System.out.println(alarm.ring());
        System.out.println(doorbell.ring());

        ringAll(new Ringable[]{alarm, doorbell});
    }
}