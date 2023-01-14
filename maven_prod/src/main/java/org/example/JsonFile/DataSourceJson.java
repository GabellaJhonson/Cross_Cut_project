package org.example.JsonFile;

import java.io.IOException;

public interface DataSourceJson
{
    void writeData(String data);
    String readData() throws IOException;
    String processedByTeg(String dataFile, String teg);
}
