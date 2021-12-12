import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
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

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml");

        //Getting setup
        out.println(xml.toString());
        xml.display();

        //The Root
        out.println(xml.isTag());
        out.println(xml.label());

        //The Children
        XMLTree results = xml.child(0);
        out.println(results);

        XMLTree channel = results.child(0);
        out.println(channel.numberOfChildren());

        XMLTree title = channel.child(1);

        XMLTree titleText = title.child(0);
        out.println(titleText.label());

        out.println(xml.child(0).child(0).child(1).child(0));

        //The Attributes
        XMLTree astronomy = channel.child(10);
        out.println(astronomy.hasAttribute("sunrise"));
        out.println(astronomy.hasAttribute("midday"));
        out.println(astronomy.attributeValue("sunrise"));
        out.println(astronomy.attributeValue("sunset"));

        printMiddleNode(channel, out);

        XMLTree item = channel.child(12);
        XMLTree forecast = item.child(9);
        printRootAttributes(forecast, out);

        in.close();
        out.close();
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int numChildren = xt.numberOfChildren();
        XMLTree middleChild = xt.child(numChildren / 2);
        out.println(middleChild.label());
        if (middleChild.isTag()) {
            out.println("Label is a tag");
            out.println(middleChild.numberOfChildren());
        } else {
            out.println("Label is a text");
        }

    }

    /**
     * Output all attributes names and values for the root of the given
     * {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose root's attributes are to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and out.is_open
     * @ensures <pre>
     * out.content =
     *   #out.content *  [the name and value of each attribute of the root of xt]
     * </pre>
     */
    private static void printRootAttributes(XMLTree xt, SimpleWriter out) {
        Iterable<String> attributes = xt.attributeNames();
        for (String s : attributes) {
            out.println(s + " -> " + xt.attributeValue(s));
        }
    }

}
