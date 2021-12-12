import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Shyam Sai Bethina
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * [exp to not have 0 as the second child of a division node]
     *  [if there is one(no division by 0)]
     * [All operational results should be positive]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        NaturalNumber result = new NaturalNumber2();

        if (!exp.label().equals("number")) {
            //Each node only has two child nodes
            NaturalNumber firstNum = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber secondNum = new NaturalNumber2(
                    evaluate(exp.child(1)));

            //does the operation depending on what type of label the node is
            if (exp.label().equals("plus")) {
                firstNum.add(secondNum);
            } else if (exp.label().equals("minus")) {
                if (firstNum.compareTo(secondNum) < 0) {
                    /*
                     * Since NN cannot be negative, outputs a message if
                     * secondNum is more than firstNum
                     */
                    Reporter.fatalErrorToConsole(
                            "Subtraction cannot result in negative number");
                }
                firstNum.subtract(secondNum);
            } else if (exp.label().equals("times")) {
                firstNum.multiply(secondNum);
            } else {
                //If secondNum is zero, outputs message saying so
                if (secondNum.isZero()) {
                    Reporter.fatalErrorToConsole(
                            "Illegal Operation - Division by zero ");
                }
                firstNum.divide(secondNum);
            }

            /*
             * copies value of firstNum to result so that the final value
             * persists out of the conditional statements
             */
            result.copyFrom(firstNum);

        } else {
            //if the label does equal number, then returns the value of the number instead
            result = new NaturalNumber2(
                    Integer.parseInt(exp.attributeValue("value")));
        }
        return result;

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
