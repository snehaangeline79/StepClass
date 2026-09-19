package Assignment;
public class GalleryDescriptionCards
{
    static abstract class ArtPiece {

        private static int counter = 1000;
        private final String pieceId;

        public ArtPiece() {
            counter++;
            pieceId = "ART-" + counter;
        }

        public abstract String describe();

        public String getPieceId() {
            return pieceId;
        }
    }

    static class Painting extends ArtPiece {

        private String title;

        public Painting(String title) {
            super();
            this.title = title;
        }

        @Override
        public String describe() {
            return "Painting: " + title + ", framed on canvas";
        }
    }

    static class Sculpture extends ArtPiece {

        private String title;

        public Sculpture(String title) {
            super();
            this.title = title;
        }

        @Override
        public String describe() {
            return "Sculpture: " + title + ", carved from stone";
        }
    }

    public static void main(String[] args) {

        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");

        System.out.println(p.describe());
        System.out.println(s.describe());

        System.out.println(p.getPieceId());
        System.out.println(s.getPieceId());
    }
}