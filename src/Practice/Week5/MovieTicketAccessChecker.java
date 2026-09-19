package Practice.Week5;
class MovieTicket {
    private String seatNumber;
    String screenId;          // default
    protected double ticketPrice;
    public String movieTitle;

    MovieTicket(String seatNumber, String screenId,
                double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

public class MovieTicketAccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("default") ||
                    fieldModifier.equals("protected")) {
                return "ALLOWED";
            }
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));
    }
}