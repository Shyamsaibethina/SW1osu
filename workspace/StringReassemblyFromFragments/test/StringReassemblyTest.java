import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Test class to test methods from StringReassembly utility class.
 *
 * @author Shyam Sai Bethina
 *
 */
public class StringReassemblyTest {

    /*
     * Tests of combination method
     */

    /**
     * Boundary test case with one character one character.
     */
    @Test
    public void testCombination_l() {
        String str1 = "l";
        String str1Expected = "l";
        String str2 = "l";
        String str2Expected = "l";
        String result = StringReassembly.combination(str1, str2, 1);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals("l", result);
    }

    /**
     * Challenging test case with the same characters.
     */
    @Test
    public void testCombination_ab() {
        String str1 = "ab";
        String str1Expected = "ab";
        String str2 = "ab";
        String str2Expected = "ab";
        String result = StringReassembly.combination(str1, str2, 2);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals("ab", result);
    }

    /**
     * Routine test case with overlap of 3 characters.
     */
    @Test
    public void testCombination_toastAst3() {
        String str1 = "toast";
        String str1Expected = "toast";
        String str2 = "ast";
        String str2Expected = "ast";
        String result = StringReassembly.combination(str1, str2, 3);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals("toast", result);
    }

    /**
     * Challenging test with not overlap.
     */
    @Test
    public void testCombination_overLap() {
        String str1 = "over";
        String str1Expected = "over";
        String str2 = "lap";
        String str2Expected = "lap";
        String result = StringReassembly.combination(str1, str2, 0);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals("overlap", result);
    }

    /**
     * Challenging test case with both strings being the same character.
     */
    @Test
    public void testCombination_jjjj() {
        String str1 = "jjjj";
        String str1Expected = "jjjj";
        String str2 = "jjjj";
        String str2Expected = "jjjj";
        String result = StringReassembly.combination(str1, str2, 2);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals("jjjjjj", result);
    }

    /*
     * Tests of addToSetAvoidingSubstrings method
     */

    /**
     * Boundary test case with only one element.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_1Element() {
        Set<String> strSet = new Set1L<>();
        strSet.add("b");
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("b");
        String str1 = "b";
        String str1Expected = "b";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str1);
        assertEquals(str1Expected, str1);
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Routine test case with three elements.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_3Elements() {
        Set<String> strSet = new Set1L<>();
        strSet.add("abcd");
        strSet.add("abc");
        strSet.add("bc");
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("abcd");
        strSetExpected.add("abc");
        strSetExpected.add("bc");
        String str1 = "b";
        String str1Expected = "b";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str1);
        assertEquals(str1Expected, str1);
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Challenging test case as the str to be added is the superstring of all
     * the elements in the set.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_3ElementsSuper() {
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");
        strSet.add("ab");
        strSet.add("b");
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("abcd");
        String str1 = "abcd";
        String str1Expected = "abcd";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str1);
        assertEquals(str1Expected, str1);
        assertEquals(strSetExpected, strSet);
    }

    /**
     * Routine test case with three elements but the third is not a substring of
     * any of them.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_3elementNoSubstring() {
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");
        strSet.add("ab");
        strSet.add("b");
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("abc");
        strSetExpected.add("ab");
        strSetExpected.add("b");
        strSetExpected.add("xyz");
        String str1 = "xyz";
        String str1Expected = "xyz";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str1);
        assertEquals(str1Expected, str1);
        assertEquals(strSetExpected, strSet);
    }

    /*
     * Tests of linesFromInput method
     */

    /**
     * Routine test case with two lines of input.
     */
    @Test
    public void testLinesFromInput_helloBye() {
        SimpleReader input = new SimpleReader1L("data/testLineFromInput1");
        Set<String> result = StringReassembly.linesFromInput(input);
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("hello");
        resultExpected.add("bye");
        assertEquals(resultExpected, result);

    }

    /**
     * Challenging test case with one string being the superstring of the
     * others.
     */
    @Test
    public void testLinesFromInput_superstring() {
        SimpleReader input = new SimpleReader1L("data/testLineFromInput2");
        Set<String> result = StringReassembly.linesFromInput(input);
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("abcd");
        assertEquals(resultExpected, result);
    }

    /**
     * Boundary test case with one character.
     */
    @Test
    public void testLinesFromInput_oneCharacter() {
        SimpleReader input = new SimpleReader1L("data/testLineFromInput3");
        Set<String> result = StringReassembly.linesFromInput(input);
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("l");
        assertEquals(resultExpected, result);
    }

    /**
     * Challenging test case with one string not being a substring of the
     * others.
     */
    @Test
    public void testLinesFromInput_noSubstring() {
        SimpleReader input = new SimpleReader1L("data/testLineFromInput4");
        Set<String> result = StringReassembly.linesFromInput(input);
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("abcd");
        resultExpected.add("xyz");
        assertEquals(resultExpected, result);
    }

    /*
     * Tests of printWithLineSeparators method
     */

    /**
     * Routine test case with just one tilde.
     */
    @Test
    public void testPrintWithLinesSeparators_oneTilde() {
        SimpleWriter out = new SimpleWriter1L("data/testPrintWithSep1.txt");
        String input = "hello~bye";
        StringReassembly.printWithLineSeparators(input, out);
        SimpleReader in = new SimpleReader1L("data/testPrintWithSep1.txt");

        String line1 = "hello";
        String line2 = "bye";
        assertEquals(line1, in.nextLine());
        assertEquals(line2, in.nextLine());

        out.close();
        in.close();
    }

    /**
     * Challenging test case with multiple tildes.
     */
    @Test
    public void testPrintWithLinesSeparators_multipleTildes() {
        SimpleWriter out = new SimpleWriter1L("data/testPrintWithSep2.txt");
        String input = "hello~~~bye";
        StringReassembly.printWithLineSeparators(input, out);
        SimpleReader in = new SimpleReader1L("data/testPrintWithSep2.txt");

        String line1 = "hello";
        String line2 = "";
        String line3 = "";
        String line4 = "bye";
        assertEquals(line1, in.nextLine());
        assertEquals(line2, in.nextLine());
        assertEquals(line3, in.nextLine());
        assertEquals(line4, in.nextLine());

        out.close();
        in.close();
    }

    /**
     * Boundary test case with one tilde at the end.
     */
    @Test
    public void testPrintWithLinesSeparators_hello() {
        SimpleWriter out = new SimpleWriter1L("data/testPrintWithSep3.txt");
        String input = "hello~";
        StringReassembly.printWithLineSeparators(input, out);
        SimpleReader in = new SimpleReader1L("data/testPrintWithSep3.txt");

        String line1 = "hello";
        assertEquals(line1, in.nextLine());

        out.close();
        in.close();
    }

    /**
     * Routine test case with a sentence.
     */
    @Test
    public void testPrintWithLinesSeparators_sentence() {
        SimpleWriter out = new SimpleWriter1L("data/testPrintWithSep1.txt");
        String input = "hello~my~name~is~Sai";
        StringReassembly.printWithLineSeparators(input, out);
        SimpleReader in = new SimpleReader1L("data/testPrintWithSep1.txt");

        String line1 = "hello";
        String line2 = "my";
        String line3 = "name";
        String line4 = "is";
        String line5 = "Sai";
        assertEquals(line1, in.nextLine());
        assertEquals(line2, in.nextLine());
        assertEquals(line3, in.nextLine());
        assertEquals(line4, in.nextLine());
        assertEquals(line5, in.nextLine());

        out.close();
        in.close();
    }

    /**
     * Boundary test case with just tilde and nothing else.
     */
    @Test
    public void testPrintWithLinesSeparators_justTilde() {
        SimpleWriter out = new SimpleWriter1L("data/testPrintWithSep5.txt");
        String input = "~";
        StringReassembly.printWithLineSeparators(input, out);
        SimpleReader in = new SimpleReader1L("data/testPrintWithSep5.txt");

        String line1 = "";
        assertEquals(line1, in.nextLine());

        out.close();
        in.close();
    }

}
