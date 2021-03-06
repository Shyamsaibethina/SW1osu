import components.map.Map;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;
import components.xmltree.XMLTree;

public final class misc {

    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        Stack<Integer> lol = new Stack1L<>();
        Stack<Integer> lol2 = new Stack1L<>();
        lol.push(2);
        lol.push(1);
        lol2.push(4);
        lol2.push(3);

        recursion(lol, lol2);
        output.println(lol);
        output.println(lol2);

        NaturalNumber test = new NaturalNumber1L(03);
        output.println(digitCount(test, 3));

        input.close();
        output.close();

    }

    public misc() {

    }

    //1
    /**
     * Inputs a "menu" of words (items) and their prices from the given file and
     * stores them in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param priceMap
     *            the word -> price map
     * @replaces priceMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "word" (unique in the file) and one price (in cents)
     *  per line, with word and price separated by ','; the "word" may
     *  contain whitespace but no ',']
     * </pre>
     * @ensures [priceMap contains word -> price mapping from file fileName]
     */
    private static void getPriceMap(String fileName,
            Map<String, Integer> priceMap) {
        SimpleReader input = new SimpleReader1L(fileName);
        while (!input.atEOS()) {
            String line = input.nextLine();
            String key = line.substring(0, line.indexOf(','));
            String value = line.substring(line.indexOf(',') + 1, line.length());
            int lineValue = Integer.parseInt(value);
            priceMap.add(key, lineValue);
        }
        input.close();
    }

    //2
    /**
     * Input one pizza order and compute and return the total price.
     *
     * @param input
     *            the input stream
     * @param sizePriceMap
     *            the size -> price map
     * @param toppingPriceMap
     *            the topping -> price map
     * @return the total price (in cents)
     * @updates input
     * @requires <pre>
     * input.is_open and
     * [input.content begins with a pizza order consisting of a size
     *  (something defined in sizePriceMap) on the first line, followed
     *  by zero or more toppings (something defined in toppingPriceMap)
     *  each on a separate line, followed by an empty line]
     * </pre>
     * @ensures <pre>
     * input.is_open and
     * #input.content = [one pizza order (as described
     *              in the requires clause)] * input.content and
     * getOneOrder = [total price (in cents) of that pizza order]
     * </pre>
     */
    private static int getOneOrder(SimpleReader input,
            Map<String, Integer> sizePriceMap,
            Map<String, Integer> toppingPriceMap) {
        String size = input.nextLine();
        int sum = 0;
        sum += sizePriceMap.value(size);
        String topping = input.nextLine();
        while (sizePriceMap.hasKey(topping)) {
            sum += toppingPriceMap.value(topping);
        }
        return sum;
    }

    public static void recursion(Stack<Integer> one, Stack<Integer> two) {
        if (two.length() > 0) {
            int temp = two.pop();
            one.push(temp);
            recursion(one, two);
        }
    }

    public static int sumOfCounts(XMLTree tree) {
        int count = 0;
        for (int i = 0; i < tree.numberOfChildren(); i++) {
            if (tree.child(i).isTag()) {
                if (tree.child(i).hasAttribute("count")) {
                    String temp = tree.child(i).attributeValue("count");
                    int temp1 = Integer.parseInt(temp);
                    count += temp1;
                }
            }
        }
        return count;
    }

    public static int digitCount(NaturalNumber n, int d) {
        int count = 0;
        int temp = n.divideBy10();
        if (temp == d) {
            count = 1;
        }
        if (!n.isZero()) {
            count += digitCount(n, d);
        }
        n.multiplyBy10(temp);
        return count;
    }

    public static void factorial(NaturalNumber n) {
        NaturalNumber temp = new NaturalNumber2(n);
        if (n.isZero()) {
            n.transferFrom(new NaturalNumber2(1));
        } else {
            temp.decrement();
            factorial(temp);
            n.multiply(temp);
        }
    }
}
