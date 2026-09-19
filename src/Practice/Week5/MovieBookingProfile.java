package Practice.Week5;
class MovieBookingProfileBean {

    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfileBean() {
        this.name = "";
        this.confirmed = false;
    }

    public MovieBookingProfileBean(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {

        if (otp != null && otp.matches("\\d{4,6}")) {
            this.otp = otp;
        }
    }
}

public class MovieBookingProfile {

    public static void main(String[] args) {

        MovieBookingProfileBean profile =
                new MovieBookingProfileBean("Rahul Dev");

        System.out.println(profile.getName());

        profile.setConfirmed(true);

        System.out.println(profile.isConfirmed());

        profile.setOtp("4471");
    }
}