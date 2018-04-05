/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Veto;
import Services.ServiceVeto;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.color;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class UpdateProfilVetoController implements Initializable {

    @FXML
    private TextArea desctext;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField textmail;
    @FXML
    private TextField textetl;
    @FXML
    private TextField textrue;
    @FXML
    private TextField villetext;
    @FXML
    private TextField textgouvernorat;
    @FXML
    private TextField textcodepostal;
    @FXML
    private TextField imagetext;
    @FXML
    private ImageView imagee;
    @FXML
    private Button uploadbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField logintext;
    @FXML
    private TextField mdptext;
    
    private String nomtest;
    private File file;
    private Image image;
    private String val;
    @FXML
    private AnchorPane back;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         back.setStyle("-fx-background-image: url('images/adopet3.jpg');");
         updatebtn.setStyle(
                
                "-fx-min-width: 10px; " +
                "-fx-min-height: 10px; " +
                "-fx-max-width: 10px; " +
                "-fx-max-height: 10px;"
        );
        File file3 = new File("/Users/TESNIME/Desktop/edit.png");
        //Image image = new Image(file.toURI().toString());
        String pt3 = file3.toURI().toString();
        Image image3 = new Image(pt3, 45,45, false, false);
        updatebtn.setGraphic(new ImageView(image3));
        updatebtn.setText("");
        
        uploadbtn.setStyle(
                
                "-fx-min-width: 10px; " +
                "-fx-min-height: 10px; " +
                "-fx-max-width: 10px; " +
                "-fx-max-height: 10px;"
        );
        File file4 = new File("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\upload.png");
        //Image image = new Image(file.toURI().toString());
        String pt4 = file4.toURI().toString();
        Image image4 = new Image(pt4, 45 ,45, false, false);
        uploadbtn.setGraphic(new ImageView(image4));
        uploadbtn.setText("");
    }   
    

        

    @FXML
    private void upoald(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg"),
                 new FileChooser.ExtensionFilter("Text Files", "*.txt"),
         new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
         new FileChooser.ExtensionFilter("All Files", "*.*"));
         file = fileChooser.showOpenDialog(null);
            if(file != null){
                imagetext.setText(file.getPath());
                //System.out.println(file.toPath().toUri().toString());
                 image = new Image(file.toURI().toURL().toString());
                imagee.setImage(image);
        
    }
    }

    @FXML
    private void ModifVeto(ActionEvent event) {
        
         ServiceVeto sv= new ServiceVeto();
        Veto v=new Veto(textnom.getText(),textprenom.getText(),textmail.getText(),Integer.parseInt(textetl.getText()),textrue.getText(),
                villetext.getText(),textgouvernorat.getText(),Integer.parseInt(textcodepostal.getText()),imagetext.getText(),desctext.getText(),"Veto",logintext.getText(),mdptext.getText());
     
        try {
            sv.updateVeto(v, val);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Yaaay :D Your profile is now updated!" );
            alert.showAndWait();
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfilVetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
       
    }
    
   

}
