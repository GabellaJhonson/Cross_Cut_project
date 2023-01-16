package all_without_gui.XmlFile;

import all_without_gui.CalculParse.MatchParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSourceXml implements DataSourceXml {
    private  String name;
    public FileDataSourceXml(String name)
    {
        this.name = name;
    }

    @Override
    public  void writeData(String data)
    {
        File file = new File(name);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override

    public  String readData() throws FileNotFoundException
    {
        char[] buffer = null;
        File file = new File(name);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new String(buffer);
    }
    @Override

    public  String processedByTeg(String dataFile, String teg) {
        String proc = null;
        if(teg.equals("all") || teg.isEmpty()) teg = "*";
        try {
            String pathTemp = "/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/XmlFile/temp.xml";
            File temp = new File(pathTemp);
            try (FileOutputStream fos = new FileOutputStream(temp)) {
                boolean created = temp.createNewFile();
                fos.write(dataFile.getBytes(), 0, dataFile.length());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(pathTemp);
            MatchParser p = new MatchParser();
            NodeList list = doc.getElementsByTagName(teg);

            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getChildNodes().getLength() <= 1)
                    list.item(i).setTextContent(p.Expression(list.item(i).getTextContent())); //!!!!!!
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathTemp));
            transformer.transform(source, result);
            proc = new String(Files.readAllBytes(Paths.get(pathTemp)));
            boolean deleted = temp.delete();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return proc;
    }

}