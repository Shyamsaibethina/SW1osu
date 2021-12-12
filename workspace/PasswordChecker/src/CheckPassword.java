import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        int count = 0;
        final int minLength = 8;
        final int conditions = 3;
        if (s.length() >= minLength) {
            if (containsUpperCaseLetter(s)) {
                count++;
            }
            if (containsLowerCaseLetter(s)) {
                count++;
            }
            if (containsDigit(s)) {
                count++;
            }
            if (containsSpecialCharacter(s)) {
                count++;
            }
        }

        if (count >= conditions) {
            out.println("Good job following directions");
        } else {
            if (!containsUpperCaseLetter(s)) {
                out.println("Missing Uppercase Letters");
            }
            if (!containsLowerCaseLetter(s)) {
                out.println("Missing Lowercase Letters");
            }
            if (!containsDigit(s)) {
                out.println("Missing Digits ");
            }
            if (!containsSpecialCharacter(s)) {
                out.println("Missing Special Characters");
            }
        }

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param s
     *            the String to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains a special character.
     *
     * @param s
     *            the String to check
     * @return true if s contains a special character, false otherwise
     */
    private static boolean containsSpecialCharacter(String s) {
        String check = "!@#$%^&*()_-+={}[]:;,.?";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < check.length(); j++) {
                if (check.charAt(j) == (s.charAt(i))) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Input password: ");
        String answer = in.nextLine();

        while (answer.length() != 0) {
            checkPassword(answer, out);
            out.println("Input password: ");
            answer = in.nextLine();

        }

        in.close();
        out.close();
    }

}
