package gui;

import all_without_gui.CalculParse.MatchParser;
import all_without_gui.JsonFile.*;
import all_without_gui.TxtFile.*;
import all_without_gui.XmlFile.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Process {
    private Scene scene;
    private Stage stage;
    private Parent root;
    private String filepath;
    private List<String> list;
    private String type;
    ArrayDeque<String> queue;
    @FXML
    private Button buttonLoad;

    @FXML
    private Button buttonProcess;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField tegText;

    @FXML
    private TextArea textInput;

    @FXML
    private TextArea textOutput;

    @FXML
    void Load(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Open.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 300, 400);
        stage.setTitle("Select the type of file to open");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        if(filepath.isEmpty() || textOutput.getText().isEmpty()) return;
        FXMLLoader openLoader = new FXMLLoader(getClass().getResource("Save.fxml"));
        root = openLoader.load();
        Save saveController = openLoader.getController();
        System.out.println(type);
        saveController.setData(type,textOutput.getText());
        //root = FXMLLoader.load(getClass().getResource("Save.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 300, 400);
        stage.setTitle("Choose a save method");
        stage.setScene(scene);
        stage.show();
    }
    void setData(String fp, List<String> li, String ty) throws IOException {
        filepath = fp;
        list = new ArrayList<String>(li);
        type = ty;
        textInput.setText(extract());
    }
    @FXML
    void processByTeg(ActionEvent event) throws Exception {
        if(textInput.getText().isEmpty()) return;
        String t = tegText.getText();
        MatchParser p = new MatchParser();
        if(type == "txt"){
            textOutput.setText(p.Expression(textInput.getText()));
        }
        if(type == "json"){
            FileDataSourceJson file = new FileDataSourceJson(filepath);
            textOutput.setText(file.processedByTeg(textInput.getText(), t));
        }
        if(type == "xml"){
            FileDataSourceXml file = new FileDataSourceXml(filepath);
            textOutput.setText(file.processedByTeg(textInput.getText(), t));
        }
    }
    public String extract() throws IOException {
        if(type == "txt"){
            DataSourceDecorator dec;
            queue = new ArrayDeque<>(list);
            DataSource file = recursTxt();
            return file.readData();
        }
        if(type == "json"){
            DataSourceDecoratorJson dec;
            queue = new ArrayDeque<>(list);
            DataSourceJson file = recursJson();
            return file.readData();
        }
        if(type == "xml"){
            DataSourceDecoratorXml dec;
            queue = new ArrayDeque<>(list);
            DataSourceXml file = recursXml();
            return file.readData();
        }
        return "2+2";
    }
    private DataSource recursTxt(){
        if(queue.isEmpty()){
            return new FileDataSource(filepath);}
        if(queue.getFirst() == "Encoded"){
            queue.removeFirst();
            return new Encryption(recursTxt());

        }
        if(queue.getFirst() == "Compress"){
            queue.removeFirst();
            return new Compression(recursTxt());
        }
        return new FileDataSource(filepath);
    }
    private DataSourceJson recursJson(){
        if(queue.isEmpty()){
            return new FileDataSourceJson(filepath);}
        if(queue.getFirst() == "Encoded"){
            queue.removeFirst();
            return new EncryptionJson(recursJson());

        }
        if(queue.getFirst() == "Compress"){
            queue.removeFirst();
            return new CompressionJson(recursJson());
        }
        return new FileDataSourceJson(filepath);
    }
    private DataSourceXml recursXml(){
        if(queue.isEmpty()){
            return new FileDataSourceXml(filepath);}
        if(queue.getFirst() == "Encoded"){
            queue.removeFirst();
            return new EncryptionXml(recursXml());

        }
        if(queue.getFirst() == "Compress"){
            queue.removeFirst();
            return new CompressionXml(recursXml());
        }
        return new FileDataSourceXml(filepath);
    }

}
