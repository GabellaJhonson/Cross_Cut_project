package gui;

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
import java.util.ArrayList;
import java.util.List;

public class Open {
    private Scene scene;
    private Stage stage;
    private Parent root;
    private String filePath;
    private ObservableList<String> temp = FXCollections.observableArrayList();
    @FXML
    private Text labelFilePath;
    @FXML
    private Button addCompress;

    @FXML
    private Button addEncrypt;

    @FXML
    private Button exitButton;

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
    void getFilepath(ActionEvent event) {
        String type = this.GetType();
        FileChooser choose = new FileChooser();
        if(type == "txt") {
            choose.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.zip"));
        }

        if(type == "json"){
            choose.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Json Files", "*.json", "*.zip"));
        }
        if(type == "xml") {
            choose.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XML Files", "*.xml", "*.zip"));
        }
        File file = choose.showOpenDialog(null);
        filePath = file.getAbsolutePath();
        labelFilePath.setText(file.getPath());
    }
    @FXML
    void okButton(ActionEvent event) throws IOException {
        if(filePath.isEmpty()){
            return;
        }
        List<String> l = new ArrayList<String>(temp);
        FXMLLoader openLoader = new FXMLLoader(getClass().getResource("Process.fxml"));
        root = openLoader.load();
        Process processController = openLoader.getController();
        processController.setData(filePath, l, this.GetType());
        //root = FXMLLoader.load(getClass().getResource("Process.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 700, 600);
        stage.setTitle("gabella.prod");
        stage.setScene(scene);
        stage.show();
    }

}