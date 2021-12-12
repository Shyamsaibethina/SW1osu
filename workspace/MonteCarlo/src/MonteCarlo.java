import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        double four = 4.0;

        int inCircle = numberOfPointsInCircle(n);
        double estimate = (four * inCircle) / n;
        output.print(
                "Estimate of area of Circle of radius 1 centered at (1,1): "
                        + estimate);
//        /*
//         * Declare counters and initialize them
//         */
//        int ptsInInterval = 0, ptsInSubinterval = 0;
//        /*
//         * Create pseudo-random number generator
//         */
//        Random rnd = new Random1L();
//        /*
//         * Generate points and count how many x,y values fall in [0.0,2.0)
//         * interval
//         */
//        while (ptsInInterval < n) {
//            /*
//             * Generate pseudo-random number in [0.0,2.0) interval
//             */
//            double x = rnd.nextDouble();
//            x = 2 * x;
//
//            double y = rnd.nextDouble();
//            y = 2 * y;
//            /*
//             * Increment total number of generated points
//             */
//            ptsInInterval++;
//            /*
//             * Check if point is in [0.0,0.5) interval and increment counter if
//             * it is
//             */
//            if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1) {
//                ptsInSubinterval++;
//            }
//        }
//        /*
//         * Estimate percentage of points generated in [0.0,1.0) interval that
//         * fall in the [0.0,0.5) subinterval
//         */
//        double estimate = (4.0 * ptsInSubinterval) / ptsInInterval;
//        output.println(
//                "Estimate of area of Circle of radius 1 centered at (1,1): "
//                        + estimate);
//        /*
//         * Close input and output streams
//         */
        input.close();
        output.close();
    }

    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        double x = xCoord;
        double y = yCoord;
        if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1) {
            return true;
        }

        return false;
    }

    private static int numberOfPointsInCircle(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            double xCoord = Math.random() * 2;
            double yCoord = Math.random() * 2;
            if (pointIsInCircle(xCoord, yCoord)) {
                count++;
            }
        }

        return count;
    }

}