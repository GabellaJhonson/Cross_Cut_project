package org.example;

import org.example.XmlFile.*;


public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = "/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/input.xml";
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

        DataSourceDecoratorXml encoded_output = new CompressionXml(
                new EncryptionXml(
                        new CompressionXml(
                                new EncryptionXml(
                                    new FileDataSourceXml("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/output.xml")))));
       // encoded_output.writeData(encoded_output_xml.readData());
        FileDataSourceXml output_xml = new FileDataSourceXml("/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/input.xml");
        //System.out.println(output_xml.readData());
       // encoded_output.writeData(output_xml.readData());
        //System.out.println(encoded_output.readData());
        //System.out.println(encoded_output.readData());
        encoded_output.writeData(encoded_output.processedByTeg(output_xml.readData(), "*"));
        encoded_output.writeData("2+2");

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