package Practice.Week5;
public class PremiumTicketAccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

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

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected")) {
                return "ALLOWED";
            }
        }

        return "DENIED";
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                classifyAccess(
                        "private",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                classifyAccess(
                        "default",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );
    }
}