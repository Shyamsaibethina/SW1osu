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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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

        int[] coinDenomination = new int[] { 100, 50, 25, 10, 5, 1 };
        int[] coinCounter = new int[6];

        out.println("Amount of cents to make change for: ");
        int change = in.nextInteger();

        for (int i = 0; i < coinDenomination.length; i++) {
            coinCounter[i] = change / coinDenomination[i];
            change = change - coinCounter[i] * coinDenomination[i];
            out.println(coinDenomination[i] + " = " + coinCounter[i]);
        }

        in.close();
        out.close();
    }

}
