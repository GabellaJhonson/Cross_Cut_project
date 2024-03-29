package all_without_gui.JsonFile;

import java.io.IOException;

public class DataSourceDecoratorJson implements DataSourceJson {
    private DataSourceJson wrappee;

    DataSourceDecoratorJson(DataSourceJson source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data){
        wrappee.writeData(data);
    }

    @Override
    public String readData() throws IOException {
        return wrappee.readData();
    }
    @Override
    public String processedByTeg(String dataFile, String teg) throws Exception {
        return wrappee.processedByTeg(dataFile, teg);
    }
}

