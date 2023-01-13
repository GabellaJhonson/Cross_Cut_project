import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = "/Users/iisuos/IdeaProjects/Kolok with test/Cross_Cut/src/input.xml";
//        String filepath = "/Users/iisuos/IdeaProjects/Kolok with test/Cross_Cut/src/output.xml";
        XmlReader m = new XmlReader(fileName);
        MatchParser p = new MatchParser();
        System.out.println(p.Expression("hello <people> 2*3*(120-34)) muter"));
    }
}