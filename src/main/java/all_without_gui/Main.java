package all_without_gui;


import all_without_gui.CalculParse.MatchParser;
import all_without_gui.JsonFile.CompressionJson;
import all_without_gui.JsonFile.DataSourceDecoratorJson;
import all_without_gui.JsonFile.EncryptionJson;
import all_without_gui.JsonFile.FileDataSourceJson;

public class Main {

    public static void main(String[] args) throws Exception {
          MatchParser p = new MatchParser();
//        FileDataSourceJson file_json = new FileDataSourceJson("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/ExampleFiles/input.json");
//        FileDataSourceJson file_json_out = new FileDataSourceJson("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/ExampleFiles/output.json");
//        file_json_out.writeData( file_json.processedByTeg(file_json.readData(), "all"));

        DataSourceDecoratorJson encoded_output = new CompressionJson(
                new EncryptionJson(
                        new CompressionJson(
                                new EncryptionJson(
                                    new FileDataSourceJson("/Users/iisuos/IdeaProjects/Kolok with test/Final_2/src/main/java/all_without_gui/ExampleFiles/output.json")))));
        System.out.println(p.Expression(encoded_output.readData()));
//        encoded_output.writeData(file_json.readData());
//        System.out.println(p.Expression(encoded_output.readData()));
//        DataSourceDecorator encoded_output = new Compression(
//                new Encryption(
//                        new Compression(
//                                new Encryption(
//                                    new FileDataSource("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/output.xml")))));
//        String s = new String(Files.readAllBytes(Paths.get("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/input.xml")));
//        System.out.println(s);
//        encoded_output.writeData(s);
//        FileDataSource f = new FileDataSource("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/output.xml");
//        System.out.println(encoded_output.readData());
//        f.writeData(p.Expression(encoded_output.readData()));

//        DataSourceDecoratorXml encoded_output = new CompressionXml(
//                new EncryptionXml(
//                        new CompressionXml(
//                                new EncryptionXml(
//                                    new FileDataSourceXml("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/output.xml")))));
//       // encoded_output.writeData(encoded_output_xml.readData());
//        FileDataSourceXml output_xml = new FileDataSourceXml("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/input.xml");
//        //System.out.println(output_xml.readData());
//       // encoded_output.writeData(output_xml.readData());
//        //System.out.println(encoded_output.readData());
//        //System.out.println(encoded_output.readData());
//        encoded_output.writeData(encoded_output.processedByTeg(output_xml.readData(), "*"));
//        encoded_output.writeData("2+2");

//        encoded_output_xml.writeData("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/input.xml");
//        System.out.println();
//        encoded_output.writeData(encoded_output_xml.readData());
//        System.out.println(p.Expression(encoded_output.readData()));
        //encoded_output.writeData("4");
        //xmlReader.read(fileName);
        //jsonReader.read();
//        fileName = "/Users/iisuos/IdeaProjects/Kolok with test/Cross_Cut/src/input.txt";
//        TxtReader t = new TxtReader(fileName);
        //System.out.println(p.Expression("hello <people> 2*3*(120-34)) muter"));
    }
}
/*
дается файл в заданном виде,
этот вид мы передаем в read
потом выбирается вид в котором мы хотим сохранить обработанный файл
и вызывается write с этим видом
 */
/*
вид состоит:
имя файла
зашифрован - не зашифрован
архивирован - не архивирован
обработан - не обработан
 */