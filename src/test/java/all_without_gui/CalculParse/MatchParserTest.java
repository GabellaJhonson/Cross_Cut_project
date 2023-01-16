package all_without_gui.CalculParse;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class MatchParserTest extends TestCase {
    private MatchParser p;
    @Before
    public void setUp(){
        p = new MatchParser();
    }
    @org.junit.Test
    public void testParse1() throws Exception {
        Assert.assertEquals("4.0", "" + p.Parse("2+2"));
    }
    @org.junit.Test
    public void testExpression1() throws Exception {
        Assert.assertEquals("hello 4.0", p.Expression("hello 2+2"));
    }
    @org.junit.Test
    public void testParse2() throws Exception {
        Assert.assertEquals("Infinity", "" + p.Parse("2/0"));
    }
    @org.junit.Test
    public void testExpression2() throws Exception {
        Assert.assertEquals("war of Infinity", p.Expression("war of 4/0"));
    }
    @org.junit.Test
    public void testParse3() throws Exception {
        Assert.assertEquals("6.0", "" + p.Parse("2+2*2"));
    }
    @org.junit.Test
    public void testExpression3() throws Exception {
        Assert.assertEquals("23))", p.Expression("(23)))"));
    }
    @org.junit.Test
    public void testParse4() throws Exception {
        Assert.assertEquals("0.95", "" + p.Parse("19/20"));
    }
    @org.junit.Test
    public void testExpression4() throws Exception {
        Assert.assertEquals("0.0", p.Expression("2 + (-2)"));
    }
}