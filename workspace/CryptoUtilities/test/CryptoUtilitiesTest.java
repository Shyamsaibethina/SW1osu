import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Shyam Sai Bethina
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    //boundary
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public void testReduceToGCD_9_3() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(3);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    //challenging/routine
    @Test
    public void testReduceToGCD_100_100() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber nExpected = new NaturalNumber2(100);
        NaturalNumber m = new NaturalNumber2(100);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    //bounday
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //challenging
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    //routine
    @Test
    public void testIsEven_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //routine
    @Test
    public void testIsEven_123456789() {
        NaturalNumber n = new NaturalNumber2(123456789);
        NaturalNumber nExpected = new NaturalNumber2(123456789);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    //boundary
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public void testPowerMod_3_6_7() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(6);
        NaturalNumber pExpected = new NaturalNumber2(6);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //boundary, very big number as modulus
    @Test
    public void testPowerMod_11_23_187() {
        NaturalNumber n = new NaturalNumber2(11);
        NaturalNumber nExpected = new NaturalNumber2(88);
        NaturalNumber p = new NaturalNumber2(23);
        NaturalNumber pExpected = new NaturalNumber2(23);
        NaturalNumber m = new NaturalNumber2(187);
        NaturalNumber mExpected = new NaturalNumber2(187);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    //routine
    @Test
    public void testIsWitnessToCompositeness_10_100() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        NaturalNumber p = new NaturalNumber2(100);
        NaturalNumber pExpected = new NaturalNumber2(100);
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(true, result);
    }

    //boundary
    @Test
    public void testIsWitnessToCompositeness_2_5() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(5);
        NaturalNumber pExpected = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(false, result);
    }

    //routine
    @Test
    public void testIsWitnessToCompositeness_120_122() {
        NaturalNumber n = new NaturalNumber2(120);
        NaturalNumber nExpected = new NaturalNumber2(120);
        NaturalNumber p = new NaturalNumber2(122);
        NaturalNumber pExpected = new NaturalNumber2(122);
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(true, result);
    }

    //routine
    @Test
    public void testIsWitnessToCompositeness_100_103() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber nExpected = new NaturalNumber2(100);
        NaturalNumber p = new NaturalNumber2(103);
        NaturalNumber pExpected = new NaturalNumber2(103);
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(false, result);
    }

    /*
     * Tests of isPrime2
     */
    //routine
    @Test
    public void testIsPrime2_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //challenging
    @Test
    public void testIsPrime2_997() {
        NaturalNumber n = new NaturalNumber2(997);
        NaturalNumber nExpected = new NaturalNumber2(997);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //routine
    @Test
    public void testIsPrime2_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    //challenging
    @Test
    public void testIsPrime2_998() {
        NaturalNumber n = new NaturalNumber2(998);
        NaturalNumber nExpected = new NaturalNumber2(998);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    //boundary
    @Test
    public void testGenerateNextLikelyPrime_996() {
        NaturalNumber n = new NaturalNumber2(996);
        NaturalNumber nExpected = new NaturalNumber2(997);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public void testGenerateNextLikelyPrime_17() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    //boundary
    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    //challenging
    @Test
    public void testGenerateNextLikelyPrime_550() {
        NaturalNumber n = new NaturalNumber2(550);
        NaturalNumber nExpected = new NaturalNumber2(557);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
