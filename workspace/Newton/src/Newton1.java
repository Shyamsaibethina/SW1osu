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
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double guess = x;
        double error = .0001;
        while (Math.abs(guess * guess - x) / x > error * error) {
            guess = (guess + x / guess) / 2;
        }
        return guess;
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
            out.println("Enter number to calculate square root of: ");
            double number = in.nextDouble();
            double sqrtOfNum = sqrt(number);
            out.println("Square root of " + number + " is " + sqrtOfNum);
            out.println("Do you wish to calculate square root?[y/n]: ");
            answer = in.nextLine();
        } while (answer.equals("y"));

        in.close();
        out.close();
    }

}
