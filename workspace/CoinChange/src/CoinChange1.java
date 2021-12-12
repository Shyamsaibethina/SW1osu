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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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

        out.println("Amount of cents to make change for: ");
        int change = in.nextInteger();
        int dollar = 0, halfDollar = 0, quarter = 0, dime = 0, nickel = 0,
                penny = 0;

        dollar = change / 100;
        change = change - dollar * 100;
        out.println(dollar);

        halfDollar = change / 50;
        change = change - halfDollar * 50;
        out.println(halfDollar);

        quarter = change / 25;
        change = change - quarter * 25;
        out.println(quarter);

        dime = change / 25;
        change = change - dime * 25;
        out.println(dime);

        nickel = change / 5;
        change = change - nickel * 5;
        out.println(nickel);

        penny = change / 1;
        change = change - penny;
        out.println(penny);

        in.close();
        out.close();
    }

}
