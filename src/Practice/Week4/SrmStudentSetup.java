package Practice.Week4;
class SrmStudent {
    static String collegeName;
    static String academicYear;

    static {
        collegeName = "SRM";
        academicYear = "2026-27";
        System.out.println("College info loaded");
    }

    String name;

    SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }
}

public class SrmStudentSetup {
    public static void main(String[] args) {

        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            new SrmStudent(name);
        }
    }
}