import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Test class to test methods from Glossary utility class.
 *
 * @author Shyam Sai Bethina
 *
 */
public class GlossaryTest {

    /*
     * Tests of getTerms method
     */

    /**
     * Routine test case, three simple words.
     */
    @Test
    public void testGetTerms_3Words() {
        SimpleReader in = new SimpleReader1L("test/testGetTerms1");
        Queue<String> words = Glossary.getTerms(in);
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("hello");
        expectedWords.enqueue("bye");
        expectedWords.enqueue("goodbye");
        assertEquals(expectedWords, words);
        in.close();
    }

    /**
     * Boundary test case, singular letter.
     */
    @Test
    public void testGetTerms_singleLetter() {
        SimpleReader in = new SimpleReader1L("test/testGetTerms2");
        Queue<String> words = Glossary.getTerms(in);
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("l");
        assertEquals(expectedWords, words);
        in.close();
    }

    /**
     * Boundary test case, every line has space.
     */
    @Test
    public void testGetTerms_lineSpace() {
        SimpleReader in = new SimpleReader1L("test/testGetTerms3");
        Queue<String> words = Glossary.getTerms(in);
        Queue<String> expectedWords = new Queue1L<>();
        assertEquals(expectedWords, words);
        in.close();
    }

    /**
     * Challenging test case, there are multiple empty lines between terms.
     */
    @Test
    public void testGetTerms_multipleLines() {
        SimpleReader in = new SimpleReader1L("test/testGetTerms4");
        Queue<String> words = Glossary.getTerms(in);
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("hello");
        expectedWords.enqueue("bye");
        expectedWords.enqueue("goodbye");
        assertEquals(expectedWords, words);
        in.close();
    }

    /*
     * Tests of getDefinitions method
     */

    /**
     * Routine test case, 3 definitions.
     */
    @Test
    public void testGetDefinitions_3Definitions() {
        SimpleReader in = new SimpleReader1L("test/testGetDef1");
        Queue<String> definitions = Glossary.getDefinitions(in);
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions.enqueue("A greeting");
        expectedDefinitions.enqueue("A saying when leaving");
        expectedDefinitions.enqueue("A shorthand version of goodbye");
        assertEquals(expectedDefinitions, definitions);
        in.close();
    }

    /**
     * Boundary test case, each definition line is only one character long.
     */
    @Test
    public void testGetDefinitions_1Character() {
        SimpleReader in = new SimpleReader1L("test/testGetDef2");
        Queue<String> definitions = Glossary.getDefinitions(in);
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions.enqueue(" l");
        expectedDefinitions.enqueue(" m");
        assertEquals(expectedDefinitions, definitions);
        in.close();
    }

    /**
     * Boundary test case, each line is part of one definition.
     */
    @Test
    public void testGetDefinitions_1Definition() {
        SimpleReader in = new SimpleReader1L("test/testGetDef3");
        Queue<String> definitions = Glossary.getDefinitions(in);
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions
                .enqueue("a greeting to say when meeting someone you know");
        assertEquals(expectedDefinitions, definitions);
        in.close();
    }

    /**
     * Challenging test case, each definition as three or more lines.
     */
    @Test
    public void testGetDefinitions_3Lines() {
        SimpleReader in = new SimpleReader1L("test/testGetDef4");
        Queue<String> definitions = Glossary.getDefinitions(in);
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions
                .enqueue("a greeting to say when meeting someone you know");
        expectedDefinitions
                .enqueue("a saying to say when leaving someone you know");
        expectedDefinitions.enqueue("an abbreviated version of saying goodbye");

        assertEquals(expectedDefinitions, definitions);
        in.close();
    }

    /*
     * Tests for pageForWords method
     */

    /**
     * Boundary test case, 1 word.
     */
    @Test
    public void testPageForWords_1Word() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("testForPage1");
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("testForPage1");

        Queue<String> definitions = new Queue1L<>();
        definitions.enqueue("A test");
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions.enqueue("A test");

        String location = "test";

        Glossary.pageForWords(words, definitions, location);

        SimpleReader in = new SimpleReader1L("test/testForPage1.html");
        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }
        String expectedResult = "<html>" + "   <head>"
                + "       <title>testForPage1</title>" + "   </head>"
                + "   <body>" + "       <h2>"
                + "           <b><i><font color='red'>testForPage1</font></i></b>"
                + "       </h2>" + "       <blockquote>A test</blockquote>"
                + "       <hr>"
                + "       <p>Return to <a href='index.html'>index</a>.</p>"
                + "   </body>" + "</html>";

        assertEquals(expectedWords, words);
        assertEquals(expectedDefinitions, definitions);
        assertEquals("test", location);
        assertEquals(expectedResult, result);
        in.close();
    }

    /**
     * Routine test case, 2 word.
     */
    @Test
    public void testPageForWords_2Word() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("testUp");
        words.enqueue("testDown");
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("testUp");
        expectedWords.enqueue("testDown");

        Queue<String> definitions = new Queue1L<>();
        definitions.enqueue("Going up");
        definitions.enqueue("Going down");
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions.enqueue("Going up");
        expectedDefinitions.enqueue("Going down");

        String location = "test";

        Glossary.pageForWords(words, definitions, location);

        SimpleReader in = new SimpleReader1L("test/testUp.html");
        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }
        String expectedResult = "<html>" + "   <head>"
                + "       <title>testUp</title>" + "   </head>" + "   <body>"
                + "       <h2>"
                + "           <b><i><font color='red'>testUp</font></i></b>"
                + "       </h2>" + "       <blockquote>Going up</blockquote>"
                + "       <hr>"
                + "       <p>Return to <a href='index.html'>index</a>.</p>"
                + "   </body>" + "</html>";

        SimpleReader in2 = new SimpleReader1L("test/testDown.html");
        String result2 = "";
        while (!in2.atEOS()) {
            result2 += in2.nextLine();
        }
        String expectedResult2 = "<html>" + "   <head>"
                + "       <title>testDown</title>" + "   </head>" + "   <body>"
                + "       <h2>"
                + "           <b><i><font color='red'>testDown</font></i></b>"
                + "       </h2>" + "       <blockquote>Going down</blockquote>"
                + "       <hr>"
                + "       <p>Return to <a href='index.html'>index</a>.</p>"
                + "   </body>" + "</html>";

        assertEquals(expectedWords, words);
        assertEquals(expectedDefinitions, definitions);
        assertEquals("test", location);
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        in.close();
        in2.close();
    }

    /**
     * Challenging test case, 3 words.
     */
    @Test
    public void testPageForWords_3Words() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("testHello");
        words.enqueue("testGoodbye");
        words.enqueue("testBye");
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("testHello");
        expectedWords.enqueue("testGoodbye");
        expectedWords.enqueue("testBye");

        Queue<String> definitions = new Queue1L<>();
        definitions.enqueue("a greeting to say when meeting someone you know");
        definitions.enqueue("a saying to say when leaving someone you know");
        definitions.enqueue("an abbreviated version of saying goodbye");
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions
                .enqueue("a greeting to say when meeting someone you know");
        expectedDefinitions
                .enqueue("a saying to say when leaving someone you know");
        expectedDefinitions.enqueue("an abbreviated version of saying goodbye");

        String location = "test";

        Glossary.pageForWords(words, definitions, location);

        SimpleReader in1 = new SimpleReader1L("test/testHello.html");
        String result1 = "";
        while (!in1.atEOS()) {
            result1 += in1.nextLine();
        }
        String expectedResult1 = "<html>" + "   <head>"
                + "       <title>testHello</title>" + "   </head>" + "   <body>"
                + "       <h2>"
                + "           <b><i><font color='red'>testHello</font></i></b>"
                + "       </h2>"
                + "       <blockquote>a greeting to say when meeting someone "
                + "you know</blockquote>" + "       <hr>"
                + "       <p>Return to <a href='index.html'>index</a>.</p>"
                + "   </body>" + "</html>";

        SimpleReader in2 = new SimpleReader1L("test/testGoodbye.html");
        String result2 = "";
        while (!in2.atEOS()) {
            result2 += in2.nextLine();
        }
        String expectedResult2 = "<html>" + "   <head>"
                + "       <title>testGoodbye</title>" + "   </head>"
                + "   <body>" + "       <h2>"
                + "           <b><i><font color='red'>testGoodbye</font></i></b>"
                + "       </h2>"
                + "       <blockquote>a saying to say when leaving someone you "
                + "know</blockquote>" + "       <hr>"
                + "       <p>Return to <a href='index.html'>index</a>.</p>"
                + "   </body>" + "</html>";

        SimpleReader in3 = new SimpleReader1L("test/testBye.html");
        String result3 = "";
        while (!in3.atEOS()) {
            result3 += in3.nextLine();
        }
        String expectedResult3 = "<html>" + "   <head>"
                + "       <title>testBye</title>" + "   </head>" + "   <body>"
                + "       <h2>"
                + "           <b><i><font color='red'>testBye</font></i></b>"
                + "       </h2>"
                + "       <blockquote>an abbreviated version of saying "
                + "goodbye</blockquote>" + "       <hr>"
                + "       <p>Return to <a href='index.html'>index</a>.</p>"
                + "   </body>" + "</html>";

        assertEquals(expectedWords, words);
        assertEquals(expectedDefinitions, definitions);
        assertEquals("test", location);
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        in1.close();
        in2.close();
        in3.close();
    }

    /*
     * Test for outputHeader method - only had one test since there is only one
     * possible solution which is to print out to the output file stream
     */

    /**
     * Routine test case.
     */
    @Test
    public void testOutputHeader() {
        SimpleWriter out = new SimpleWriter1L("test/testHeader");
        SimpleReader in = new SimpleReader1L("test/testHeader");
        Glossary.outputHeader(out);
        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }

        String resultExpected = "<html>" + "   <head>"
                + "       <title>Glossary</title>" + "   </head>" + "   <body>"
                + "       <h2>Glossary</h2>" + "       <hr />"
                + "       <h3>Index</h3>" + "       <ul>";

        assertEquals(resultExpected, result);
        out.close();
        in.close();
    }

    /*
     * Test for outputFooter method - only had one test since there is only one
     * possible solution which is to print out to the output file stream
     */

    /**
     * Routine test case.
     */
    @Test
    public void testOutputFooter() {
        SimpleWriter out = new SimpleWriter1L("test/testFooter");
        SimpleReader in = new SimpleReader1L("test/testFooter");
        Glossary.outputFooter(out);
        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }

        String resultExpected = "       </ul>" + "   </body>" + "</html>";

        assertEquals(resultExpected, result);
        out.close();
        in.close();
    }

    /*
     * Test for listForWords
     */

    /**
     * Routine test case, 2 words.
     */
    @Test
    public void testListForWords() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("hello");
        words.enqueue("bye");
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("bye");
        expectedWords.enqueue("hello");

        Queue<String> definitions = new Queue1L<>();
        definitions.enqueue("a greeting to say when meeting someone you know");
        definitions.enqueue("a saying to say when leaving someone you know");
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions
                .enqueue("a greeting to say when meeting someone you know");
        expectedDefinitions
                .enqueue("a saying to say when leaving someone you know");

        SimpleWriter out = new SimpleWriter1L("test/testList1");
        SimpleReader in = new SimpleReader1L("test/testList1");
        Glossary.listForWords(out, words, definitions);

        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }

        String expectedResult = "           <li><a href=bye.html>bye</a></li>"
                + "           <li><a href=hello.html>hello</a></li>";

        assertEquals(expectedWords, words);
        assertEquals(expectedDefinitions, definitions);
        assertEquals(expectedResult, result);
        out.close();
        in.close();
    }

    /**
     * Boundary test case, 1 word.
     */
    @Test
    public void testListForWords_1Word() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("hello");
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("hello");

        Queue<String> definitions = new Queue1L<>();
        definitions.enqueue("a greeting to say when meeting someone you know");
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions
                .enqueue("a greeting to say when meeting someone you know");

        SimpleWriter out = new SimpleWriter1L("test/testList2");
        SimpleReader in = new SimpleReader1L("test/testList2");
        Glossary.listForWords(out, words, definitions);

        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }

        String expectedResult = "           <li><a href=hello.html>hello</a></li>";

        assertEquals(expectedWords, words);
        assertEquals(expectedDefinitions, definitions);
        assertEquals(expectedResult, result);
        out.close();
        in.close();
    }

    /**
     * Challenging test case, 3 word, 2 are the same.
     */
    @Test
    public void testListForWords_3words() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("hello");
        words.enqueue("bye");
        words.enqueue("bye");
        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("bye");
        expectedWords.enqueue("bye");
        expectedWords.enqueue("hello");

        Queue<String> definitions = new Queue1L<>();
        definitions.enqueue("a greeting to say when meeting someone you know");
        definitions.enqueue("an abbreviated version of goodbye");
        definitions.enqueue("an abbreviated version of goodbye");
        Queue<String> expectedDefinitions = new Queue1L<>();
        expectedDefinitions
                .enqueue("a greeting to say when meeting someone you know");
        expectedDefinitions.enqueue("an abbreviated version of goodbye");
        expectedDefinitions.enqueue("an abbreviated version of goodbye");

        SimpleWriter out = new SimpleWriter1L("test/testList3");
        SimpleReader in = new SimpleReader1L("test/testList3");
        Glossary.listForWords(out, words, definitions);

        String result = "";
        while (!in.atEOS()) {
            result += in.nextLine();
        }

        String expectedResult = "           <li><a href=bye.html>bye</a></li>"
                + "           <li><a href=bye.html>bye</a></li>"
                + "           <li><a href=hello.html>hello</a></li>";

        assertEquals(expectedWords, words);
        assertEquals(expectedDefinitions, definitions);
        assertEquals(expectedResult, result);
        out.close();
        in.close();
    }

    /*
     * Tests for linkWordsInDefinition method
     */
    /**
     * Routine test case, two words.
     */
    @Test
    public void testLinkWordsInDefinition_2Words() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("hello");
        words.enqueue("bye");

        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("hello");
        expectedWords.enqueue("bye");

        String definition = "hello my name is sai";
        String result = Glossary.linkWordsinDefinition(words, definition);

        String expectedResult = "<a href=hello.html>hello</a> my name is sai";

        assertEquals(expectedWords, words);
        assertEquals(expectedResult, result);
    }

    /**
     * Boundary test case, 1 word.
     */
    @Test
    public void testLinkWordsInDefinition_1Word() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("hello");

        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("hello");

        String definition = "hello my name is sai";
        String result = Glossary.linkWordsinDefinition(words, definition);

        String expectedResult = "<a href=hello.html>hello</a> my name is sai";

        assertEquals(expectedWords, words);
        assertEquals(expectedResult, result);
    }

    /**
     * Boundary test case, 1 word, no links.
     */
    @Test
    public void testLinkWordsInDefinition_1WordNoLinks() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("bye");

        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("bye");

        String definition = "hello my name is sai";
        String result = Glossary.linkWordsinDefinition(words, definition);

        String expectedResult = "hello my name is sai";

        assertEquals(expectedWords, words);
        assertEquals(expectedResult, result);
    }

    /**
     * Challenging test case, 3 words, all linked.
     */
    @Test
    public void testLinkWordsInDefinition_3WordsAllLinked() {
        Queue<String> words = new Queue1L<>();
        words.enqueue("bye");
        words.enqueue("hello");
        words.enqueue("goodbye");

        Queue<String> expectedWords = new Queue1L<>();
        expectedWords.enqueue("bye");
        expectedWords.enqueue("hello");
        expectedWords.enqueue("goodbye");

        String definition1 = "hello my name is sai";
        String result1 = Glossary.linkWordsinDefinition(words, definition1);

        String definition2 = "goodbye see you later";
        String result2 = Glossary.linkWordsinDefinition(words, definition2);

        String definition3 = "bye!";
        String result3 = Glossary.linkWordsinDefinition(words, definition3);

        String expectedResult1 = "<a href=hello.html>hello</a> my name is sai";
        String expectedResult2 = "<a href=goodbye.html>goodbye</a> see you later";
        String expectedResult3 = "<a href=bye.html>bye</a>!";

        assertEquals(expectedWords, words);
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);

    }

    /*
     * Tests for generateElements method
     */

    /**
     * Routine test case, string with length 3 and all different characters.
     */
    @Test
    public void testGenerateElements() {
        Set<Character> charSet = new Set1L<>();
        String test = "bye";
        Glossary.generateElements(test, charSet);

        Set<Character> expectedCharSet = new Set1L<>();
        expectedCharSet.add('b');
        expectedCharSet.add('y');
        expectedCharSet.add('e');

        String expectedTest = "bye";

        assertEquals(expectedCharSet, charSet);
        assertEquals(expectedTest, test);
    }

    /**
     * Boundary test case, string with length 1.
     */
    @Test
    public void testGenerateElements_1Letter() {
        Set<Character> charSet = new Set1L<>();
        String test = "b";
        Glossary.generateElements(test, charSet);

        Set<Character> expectedCharSet = new Set1L<>();
        expectedCharSet.add('b');

        String expectedTest = "b";

        assertEquals(expectedCharSet, charSet);
        assertEquals(expectedTest, test);
    }

    /**
     * Challenging test case, string that is a sentence and has some repetitive
     * characters, also has the same letter but uppercase and lowercase.
     */
    @Test
    public void testGenerateElements_sentence() {
        Set<Character> charSet = new Set1L<>();
        String test = "Whatsup my name is sai, I like to win";
        Glossary.generateElements(test, charSet);

        Set<Character> expectedCharSet = new Set1L<>();
        expectedCharSet.add('W');
        expectedCharSet.add('h');
        expectedCharSet.add('a');
        expectedCharSet.add('t');
        expectedCharSet.add('s');
        expectedCharSet.add('u');
        expectedCharSet.add('p');
        expectedCharSet.add(' ');
        expectedCharSet.add('m');
        expectedCharSet.add('y');
        expectedCharSet.add('n');
        expectedCharSet.add('e');
        expectedCharSet.add('i');
        expectedCharSet.add(',');
        expectedCharSet.add('I');
        expectedCharSet.add('l');
        expectedCharSet.add('k');
        expectedCharSet.add('o');
        expectedCharSet.add('w');

        String expectedTest = "Whatsup my name is sai, I like to win";

        assertEquals(expectedCharSet, charSet);
        assertEquals(expectedTest, test);
    }

    /*
     * Tests for nextWordOrSeparator
     */

    /**
     * Routine test case, simple sentence.
     */
    @Test
    public void testNextWordOrSeparator_simple() {
        final String separatorStr = " \t,!.?(){}[];:'";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = "Bye, have a good day!";

        Queue<String> result = Glossary.nextWordOrSeparator(test, separatorSet);

        Queue<String> expectedResult = new Queue1L<>();
        expectedResult.enqueue("Bye");
        expectedResult.enqueue(", ");
        expectedResult.enqueue("have");
        expectedResult.enqueue(" ");
        expectedResult.enqueue("a");
        expectedResult.enqueue(" ");
        expectedResult.enqueue("good");
        expectedResult.enqueue(" ");
        expectedResult.enqueue("day");
        expectedResult.enqueue("!");

        String expectedTest = "Bye, have a good day!";

        assertEquals(expectedResult, result);
        assertEquals(expectedTest, test);
    }

    /**
     * Boundary test case, only separators.
     */
    @Test
    public void testNextWordOrSeparator_separators() {
        final String separatorStr = " \t,!.?(){}[];:'";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = "[]{}!.?";

        Queue<String> result = Glossary.nextWordOrSeparator(test, separatorSet);

        Queue<String> expectedResult = new Queue1L<>();
        expectedResult.enqueue("[]{}!.?");

        String expectedTest = "[]{}!.?";

        assertEquals(expectedResult, result);
        assertEquals(expectedTest, test);
    }

    /**
     * Boundary test case, only letters.
     */
    @Test
    public void testNextWordOrSeparator_letters() {
        final String separatorStr = " \t,!.?(){}[];:'";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = "abcdefg";

        Queue<String> result = Glossary.nextWordOrSeparator(test, separatorSet);

        Queue<String> expectedResult = new Queue1L<>();
        expectedResult.enqueue("abcdefg");

        String expectedTest = "abcdefg";

        assertEquals(expectedResult, result);
        assertEquals(expectedTest, test);
    }

    /**
     * Challenging test case, only separators inside letters.
     */
    @Test
    public void testNextWordOrSeparator_sepInsideLetters() {
        final String separatorStr = " \t,!.?(){}[];:'";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = "he!?.llo m!y na:me";

        Queue<String> result = Glossary.nextWordOrSeparator(test, separatorSet);

        Queue<String> expectedResult = new Queue1L<>();
        expectedResult.enqueue("he");
        expectedResult.enqueue("!?.");
        expectedResult.enqueue("llo");
        expectedResult.enqueue(" ");
        expectedResult.enqueue("m");
        expectedResult.enqueue("!");
        expectedResult.enqueue("y");
        expectedResult.enqueue(" ");
        expectedResult.enqueue("na");
        expectedResult.enqueue(":");
        expectedResult.enqueue("me");

        String expectedTest = "he!?.llo m!y na:me";

        assertEquals(expectedResult, result);
        assertEquals(expectedTest, test);
    }

}
