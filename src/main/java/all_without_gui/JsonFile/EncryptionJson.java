package all_without_gui.JsonFile;

import java.io.IOException;
import java.util.Base64;

public class EncryptionJson extends DataSourceDecoratorJson {

    public EncryptionJson(DataSourceJson source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    @Override
    public String readData() throws IOException {
        return decode(super.readData());
    }
    @Override
    public String processedByTeg(String dataFile, String teg) throws Exception {
        return super.processedByTeg(dataFile, teg);
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}
