package all_without_gui.JsonFile;

import all_without_gui.CalculParse.MatchParser;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileDataSourceJsonTest extends TestCase {
    private MatchParser p;
    private File in;
    private File out;
    private String pathIn;
    private String pathOut;
    private String expected;
    @Before
    public void setUp() throws Exception {
        p = new MatchParser();
        pathIn = "/Users/iisuos/IdeaProjects/Kolok with test/Final_2/src/test/java/all_without_gui/JsonFile/inputTest.json";
        in = new File(pathIn);
        pathOut = "/Users/iisuos/IdeaProjects/Kolok with test/Final_2/src/test/java/all_without_gui/JsonFile/outputTest.json";
        out = new File(pathOut);
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        list.add("2+2");
        list.add("2+2*2");
        list.add("(Hello) ((2)+-2)");

        obj.put("expressions", list);
        expected = p.Expression(obj.toJSONString());
        try (FileWriter file = new FileWriter(pathIn)) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testWriteData() throws Exception {
        FileDataSourceJson outputFile = new FileDataSourceJson(pathOut);
        FileDataSourceJson inputFile = new FileDataSourceJson(pathIn);
        outputFile.writeData(p.Expression(inputFile.readData()));
        Assert.assertEquals(expected, outputFile.readData());
    }
    @Test
    public void testReadData() throws Exception {
        FileDataSourceJson inputFile = new FileDataSourceJson(pathIn);
        Assert.assertEquals(expected, p.Expression(inputFile.readData()));
    }
    @Test
    public void testCompressiveEncryption() throws Exception {
        DataSourceDecoratorJson encoded_output = new CompressionJson(
                new EncryptionJson(
                        new FileDataSourceJson(pathOut)));
        FileDataSourceJson inputFile = new FileDataSourceJson(pathIn);
        encoded_output.writeData(encoded_output.processedByTeg(inputFile.readData(), "all"));
        Assert.assertEquals(expected, encoded_output.readData());
    }
    @Test
    public void testCompEncCompEnc() throws Exception {
        DataSourceDecoratorJson encoded_output = new CompressionJson(
                new EncryptionJson(
                        new CompressionJson(
                                new EncryptionJson(
                                        new FileDataSourceJson(pathOut)))));
        FileDataSourceJson inputFile = new FileDataSourceJson(pathIn);
        encoded_output.writeData(encoded_output.processedByTeg(inputFile.readData(), "all"));
        Assert.assertEquals(expected, encoded_output.readData());
    }
    @After
    public void clear(){
        boolean delIn = in.delete();
        boolean delOut = out.delete();
    }
}