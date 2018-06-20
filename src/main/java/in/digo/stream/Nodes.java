package in.digo.stream;

import static java.util.Objects.requireNonNull;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Nodes {
    private final NodeList list;

    public Nodes(NodeList list) {
        this.list = requireNonNull(list);
    }

    public static Nodes of(NodeList list) {
        return new Nodes(list);
    }

    public Stream<Node> stream() {
        return IntStream.range(0, list.getLength()).mapToObj(list::item);
    }

    public Stream<String> strings() {
        return stream().map(Node::getTextContent);
    }

    public Stream<String> names() {
        return stream().map(Node::getNodeName);
    }

    public Stream<Element> elements() {
        return stream().filter(n -> n.getNodeType() == Node.ELEMENT_NODE);
    }

    public Stream<Node> named(String name) {
        return stream().filter(n -> n.getNodeName().equals(name));
    }
}
