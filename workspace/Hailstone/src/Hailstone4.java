import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        out.print(n);
        int count = 1;
        int number = n;
        final int three = 3;
        int max = number;
        while (number != 1) {
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = three * number + 1;
            }

            if (max < number) {
                max = number;
            }
            out.print(", " + number);
            count++;
        }
        out.println();
        out.println("The length of the series is: " + count);
        out.println("The maximum of the series is: " + max);
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

        String answer;
        do {
            out.print("Input beginning number: ");
            int user = in.nextInteger();
            generateSeries(user, out);
            out.print("Do you want to calculate another series[y/n]: ");
            answer = in.nextLine();
        } while (answer.equals("y"));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {

        out.print("Input beginning number: ");
        String userInput = in.nextLine();

        while (!FormatChecker.canParseInt(userInput)) {
            out.println("Input positive Integer: ");
            userInput = in.nextLine();
        }

        int converted = Integer.parseInt(userInput);

        return converted;
    }

}
