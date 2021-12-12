import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        /*
         * Constants to use during while loop
         */
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);

        /*
         * Creates lowerBound and upperBound. upperBound is n+1
         */
        NaturalNumber lowerBound = new NaturalNumber2(0);
        NaturalNumber upperBound = new NaturalNumber2(n);
        upperBound.increment();

        /*
         * Creates guess variable
         */
        NaturalNumber guess = new NaturalNumber2();

        /*
         * This is a clone of guess variable so that functions that are done on
         * it aren't copied over to the guess variable
         */
        NaturalNumber temp = new NaturalNumber2();

        /*
         * creates difference between both bounds
         */
        NaturalNumber difference = new NaturalNumber2(lowerBound);
        difference.add(upperBound);

        while (difference.compareTo(one) > 0) {
            /*
             * Halving part, guess will equal to the middle of upperBound and
             * lowerBound
             */
            guess.copyFrom(lowerBound);
            guess.add(upperBound);
            guess.divide(two);

            /*
             * temp will then be copied from guess so that the power function
             * doesn't affect guess
             */
            temp.copyFrom(guess);
            temp.power(r);

            /*
             * if temp^r is less than the original number, then the lowerBound
             * will equal the midpoint. Else the upperBound will equal the
             * midpoint, thereby eliminating half of the options
             */
            if (temp.compareTo(n) <= 0) {
                lowerBound.copyFrom(guess);
            } else {
                upperBound.copyFrom(guess);
            }

            /*
             * The difference will then be recalculated to check if they are a
             * single unit apart. If they are, then the loop ends.
             */
            difference.copyFrom(upperBound);
            difference.subtract(lowerBound);

        }

        /*
         * the loop will end with lowerBound equaling the number we want to
         * return, so n will become the lowerBound
         */
        n.copyFrom(lowerBound);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
