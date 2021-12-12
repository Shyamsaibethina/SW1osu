import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Hailstone2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber} and outputs length of series
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n] length of series
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        out.print(n);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);
        NaturalNumber zero = new NaturalNumber2();
        NaturalNumber copy = new NaturalNumber2(n);
        int length = 1;

        while (n.compareTo(one) != 0) {
            copy.copyFrom(n);

            if (copy.divide(two).compareTo(zero) == 0) {
                n.divide(two);
            } else {
                n.multiply(three);
                n.add(one);
            }
            out.print(", " + n);
            length++;
        }
        out.println();
        out.println("Length: " + length);
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.println("Input a positive integer: ");
        int input = in.nextInteger();
        if (input >= 0) {
            NaturalNumber userInput = new NaturalNumber2(input);
            generateSeries(userInput, out);
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
