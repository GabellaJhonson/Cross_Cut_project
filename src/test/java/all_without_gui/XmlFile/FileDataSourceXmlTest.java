package all_without_gui.XmlFile;

import all_without_gui.CalculParse.MatchParser;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileDataSourceXmlTest extends TestCase {

    private MatchParser p;
    private File in;
    private File out;
    private String pathIn;
    private String pathOut;
    private String expected;
    private String expected_AllTegs;
    private String expected_teg_expression2;
    @Before
    public void setUp() throws Exception {
        p = new MatchParser();
        pathIn = "/Users/iisuos/IdeaProjects/Kolok with test/Final_2/src/test/java/all_without_gui/XmlFile/inputTest.xml";
        in = new File(pathIn);
        pathOut = "/Users/iisuos/IdeaProjects/Kolok with test/Final_2/src/test/java/all_without_gui/XmlFile/outputTest.xml";
        out = new File(pathOut);
        String dataIn = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><xmlExpressionList>\n" +
                "    <expressions>\n" +
                "        <expression1>2--2</expression1>\n" +
                "        <expression2>2/0</expression2>\n" +
                "        <expression3>Dan Jukes</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>(((((2-2</expression1>\n" +
                "        <expression2>2+-2</expression2>\n" +
                "        <expression3>(10)</expression3>\n" +
                "    </expressions>\n" +
                "</xmlExpressionList>";
        expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><xmlExpressionList>\n" +
                "    <expressions>\n" +
                "        <expression1>4.0</expression1>\n" +
                "        <expression2>Infinity</expression2>\n" +
                "        <expression3>Dan Jukes</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>(((((0.0</expression1>\n" +
                "        <expression2>0.0</expression2>\n" +
                "        <expression3>10</expression3>\n" +
                "    </expressions>\n" +
                "</xmlExpressionList>";
        expected_teg_expression2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><xmlExpressionList>\n" +
                "    <expressions>\n" +
                "        <expression1>2--2</expression1>\n" +
                "        <expression2>Infinity</expression2>\n" +
                "        <expression3>Dan Jukes</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>(((((2-2</expression1>\n" +
                "        <expression2>0.0</expression2>\n" +
                "        <expression3>(10)</expression3>\n" +
                "    </expressions>\n" +
                "</xmlExpressionList>";
        expected_AllTegs = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><xmlExpressionList>\n" +
                "    <expressions>\n" +
                "        <expression1>4.0</expression1>\n" +
                "        <expression2>Infinity</expression2>\n" +
                "        <expression3>Dan Jukes</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>(((((0.0</expression1>\n" +
                "        <expression2>0.0</expression2>\n" +
                "        <expression3>10</expression3>\n" +
                "    </expressions>\n" +
                "</xmlExpressionList>";
        try (FileOutputStream fos = new FileOutputStream(pathIn)) {
            boolean created = in.createNewFile();
            fos.write(dataIn.getBytes(), 0, dataIn.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void testWriteData() throws Exception {
        FileDataSourceXml outputFile = new FileDataSourceXml(pathOut);
        FileDataSourceXml inputFile = new FileDataSourceXml(pathIn);
        outputFile.writeData(p.Expression(inputFile.readData()));
        Assert.assertEquals(expected, outputFile.readData());
    }
    @Test
    public void testReadData() throws Exception {
        FileDataSourceXml inputFile = new FileDataSourceXml(pathIn);
        Assert.assertEquals(expected, p.Expression(inputFile.readData()));
    }
    @Test
    public void testCompressiveEncryption() throws Exception {
        DataSourceDecoratorXml encoded_output = new CompressionXml(
                new EncryptionXml(
                        new FileDataSourceXml(pathOut)));
        FileDataSourceXml inputFile = new FileDataSourceXml(pathIn);
        encoded_output.writeData(encoded_output.processedByTeg(inputFile.readData(), "all"));
        Assert.assertEquals(expected_AllTegs, encoded_output.readData());
    }
    @Test
    public void testCompEncCompEnc() throws Exception {
        DataSourceDecoratorXml encoded_output = new CompressionXml(
                new EncryptionXml(
                        new CompressionXml(
                                new EncryptionXml(
                                        new FileDataSourceXml(pathOut)))));
        FileDataSourceXml inputFile = new FileDataSourceXml(pathIn);
        encoded_output.writeData(encoded_output.processedByTeg(inputFile.readData(), "all"));
        Assert.assertEquals(expected_AllTegs, encoded_output.readData());
    }
    @Test
    public void testCompEnc_teg() throws Exception {
        DataSourceDecoratorXml encoded_output = new CompressionXml(
                new EncryptionXml(
                        new FileDataSourceXml(pathOut)));
        FileDataSourceXml inputFile = new FileDataSourceXml(pathIn);
        encoded_output.writeData(encoded_output.processedByTeg(inputFile.readData(), "expression2"));
        Assert.assertEquals(expected_teg_expression2, encoded_output.readData());
    }
    @Test
    public void testCompEncCompEnc_teg() throws Exception {
        DataSourceDecoratorXml encoded_output = new CompressionXml(
                new EncryptionXml(
                        new CompressionXml(
                                new EncryptionXml(
                                        new FileDataSourceXml(pathOut)))));
        FileDataSourceXml inputFile = new FileDataSourceXml(pathIn);
        encoded_output.writeData(encoded_output.processedByTeg(inputFile.readData(), "expression2"));
        Assert.assertEquals(expected_teg_expression2, encoded_output.readData());
    }
    @After
    public void clear(){
        boolean delIn = in.delete();
        boolean delOut = out.delete();
    }
}