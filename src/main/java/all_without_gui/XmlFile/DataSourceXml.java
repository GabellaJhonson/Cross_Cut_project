package all_without_gui.XmlFile;

import java.io.IOException;

public interface DataSourceXml
{
    void writeData(String data);
    String readData() throws IOException;
    String processedByTeg(String dataFile, String teg);
}
