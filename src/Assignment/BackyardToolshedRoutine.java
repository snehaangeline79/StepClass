package Assignment;

public class BackyardToolshedRoutine {

    static abstract class GardenTool {

        public GardenTool() {
        }

        public String use() {
            return "Using the tool in the garden";
        }
    }

    static class CuttingTool extends GardenTool {

        public CuttingTool() {
            super();
        }

        @Override
        public String use() {
            return super.use() + ", blade sharpened first";
        }
    }

    static class Pruner extends CuttingTool {

        public Pruner() {
            super();
        }

        @Override
        public String use() {
            return super.use()
                    + ", then trimming branches precisely";
        }
    }

    public static void main(String[] args) {

        CuttingTool c = new CuttingTool();
        Pruner p = new Pruner();

        System.out.println(c.use());
        System.out.println(p.use());
    }
}