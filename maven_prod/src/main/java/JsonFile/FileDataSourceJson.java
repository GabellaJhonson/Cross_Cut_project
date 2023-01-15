package JsonFile;

import CalculParse.MatchParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class FileDataSourceJson implements DataSourceJson {
    private  String name;
    public FileDataSourceJson(String name)
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

    public  String processedByTeg(String dataFile, String teg) throws Exception {
        String proc = "";
        JSONParser parser = new JSONParser();
        MatchParser p = new MatchParser();
        proc = p.Expression(dataFile);
        if(!dataFile.isEmpty()) return proc;
        String pathTemp = "/Users/iisuos/IdeaProjects/Kolok with test/maven_prod/src/main/java/org/example/JsonFile/temp.json";
        File temp = new File(pathTemp);
        try (FileOutputStream fos = new FileOutputStream(temp)) {
            boolean created = temp.createNewFile();
            fos.write(dataFile.getBytes(), 0, dataFile.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (Reader reader = new FileReader(pathTemp)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray exp = (JSONArray) jsonObject.get("expression1");
            Iterator<String> iterator = exp.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean deleted = temp.delete();
        return proc;
    }

}