package gui;

import all_without_gui.JsonFile.*;
import all_without_gui.TxtFile.*;
import all_without_gui.XmlFile.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Save {
    private Scene scene;
    private Stage stage;
    private Parent root;
    private ArrayDeque<String> queue;
    private String dataFile;
    private String filepath;
    private ObservableList<String> temp = FXCollections.observableArrayList();
    @FXML
    private Text labelFilePath;

    @FXML
    private RadioButton jsonType;

    @FXML
    private ListView<String> list;

    @FXML
    private RadioButton txtType;

    @FXML
    private RadioButton xmlType;


    public String GetType(){
        if(jsonType.isSelected()){
            return "json";
        }
        if(txtType.isSelected()){
            return "txt";
        }
        if(xmlType.isSelected()){
            return "xml";
        }
        return "Error type";
    }
    @FXML
    void AddCompressToList(ActionEvent event) {
        temp.add("Compress");
        list.setItems(temp);
    }

    @FXML
    void AddEncToList(ActionEvent event) {
        temp.add("Encoded");
        list.setItems(temp);
    }
    @FXML
    void getFilepath(ActionEvent event) throws IOException {
        FileChooser choose = new FileChooser();
        File file = choose.showSaveDialog(null);
        filepath = file.getAbsolutePath();
        labelFilePath.setText(file.getPath());
    }
    @FXML
    void okButton(ActionEvent event) throws IOException {
        if(filepath.isEmpty()){
            return;
        }
        File fileN = new File(filepath);
        boolean cr = fileN.createNewFile();
        List<String> list = new ArrayList<String>(temp);
        if(GetType() == "txt"){
            DataSourceDecorator dec;
            queue = new ArrayDeque<>(list);
            DataSource file = recursTxt();
            file.writeData(dataFile);
        }
        if(GetType() == "json"){
            DataSourceDecoratorJson dec;
            queue = new ArrayDeque<>(list);
            DataSourceJson file = recursJson();
            file.writeData(dataFile);
        }
        if(GetType() == "xml"){
            DataSourceDecoratorXml dec;
            queue = new ArrayDeque<>(list);
            DataSourceXml file = recursXml();
            file.writeData(dataFile);
        }
        root = FXMLLoader.load(getClass().getResource("Process.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 700, 600);
        stage.setTitle("prod.izobarr");
        stage.setScene(scene);
        stage.show();
    }
    void setData(String type, String data){
        if(type == "txt") {
            txtType.setDisable(false);
            txtType.fire();
            txtType.setDisable(true);

            System.out.println(txtType.isSelected());
        }

        if(type == "json"){
            jsonType.setDisable(false);
            jsonType.fire();
            jsonType.setDisable(true);
        }
        if(type == "xml") {
            xmlType.setDisable(false);
            xmlType.fire();
            xmlType.setDisable(true);
        }
        dataFile = data;
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