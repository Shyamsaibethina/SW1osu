import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    @Test
    public void test() {

    }

    @Test
    public void testtoStringzero() {
        NaturalNumber zero = new NaturalNumber2();
        String zeroExpected = "0";
        System.out.println(redirectToMethodUnderTest(zero));
        assertEquals(zeroExpected, redirectToMethodUnderTest(zero));
    }

    @Test
    public void testtoStringmill() {
        NaturalNumber mill = new NaturalNumber2(1000000);
        String millExpected = "1,000,000";
        System.out.println(redirectToMethodUnderTest(mill));
        assertEquals(millExpected, redirectToMethodUnderTest(mill));
    }

    @Test
    public void testtoStringtenth() {
        NaturalNumber tenth = new NaturalNumber2(10000);
        String tenthExpected = "10,000";
        System.out.println(redirectToMethodUnderTest(tenth));
        assertEquals(tenthExpected, redirectToMethodUnderTest(tenth));
    }

    @Test
    public void testtoStringrandom() {
        NaturalNumber random = new NaturalNumber2(1234567586);
        String randomExpected = "1,234,567,586";
        System.out.println(redirectToMethodUnderTest(random));
        assertEquals(randomExpected, redirectToMethodUnderTest(random));
    }

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas6.toStringWithCommas(n);
    }

}
