package Practice.Week7;
public class OrchestraWarmUpRoutine {
    static abstract class Instrument {

        public Instrument() {
        }

        public abstract String play();
    }

    static class StringInstrument extends Instrument {

        public StringInstrument() {
            super();
        }

        @Override
        public String play() {
            return "Strumming the strings";
        }
    }

    static class Violin extends StringInstrument {

        public Violin() {
            super();
        }

        @Override
        public String play() {
            return super.play()
                    + ", with a bow drawn across four strings";
        }
    }

    public static void main(String[] args) {

        StringInstrument s = new StringInstrument();
        Violin v = new Violin();

        System.out.println(s.play());
        System.out.println(v.play());
    }
}