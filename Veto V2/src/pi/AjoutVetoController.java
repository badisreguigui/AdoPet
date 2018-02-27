/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entities.Veto;
import Service.ServiceVeto;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class AjoutVetoController implements Initializable {

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
    private Button uploadbtn;
    @FXML
    private ImageView imagee;
    
    private File file;
    private Image image;
    
    @FXML
    private TextField logintext;
    @FXML
    private TextField mdptext;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void ajouterVeto(ActionEvent event) throws IOException, SQLException {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SORRY");
 

            if (textnom.getText().equals("")) {
           
            alert.setContentText("Heyy! Name box must be filled please!");
            alert.showAndWait();
        }
     
            else if (textprenom.getText().equals("")) {
           
            alert.setContentText("Heyy! First Name box must be filled please!");
            alert.showAndWait();
        }
            else if (textmail.getText().equals("")) {
            alert.setContentText("can you please fill up your mail box");
            alert.showAndWait();
        }
            else if (textetl.getText().equals("") || Integer.parseInt(textetl.getText()) == 0) {
            alert.setContentText("Heyy! can you please fill up your number box correctly");
            alert.showAndWait();
        }
            else if (textrue.getText().equals("")) {
            alert.setContentText("can you please fill up your street box");
            alert.showAndWait();
        }
            else if (villetext.getText().equals("")) {
            alert.setContentText("can you please fill up your city box");
            alert.showAndWait();
        }
            else if (textgouvernorat.getText().equals("")) {
            alert.setContentText("can you please fill up your gouvernorate box");
            alert.showAndWait();
        }
             else if (textcodepostal.getText().equals("") || Integer.parseInt(textcodepostal.getText()) == 0) {
            alert.setContentText(" Heyy! can you please fill up your number box correctly ");
            alert.showAndWait();
        }
             else if (desctext.getText().equals("")) {
            alert.setContentText("can you please fill up your description box");
            alert.showAndWait();
        }
            
             else{       
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //java.util.Date date = new java.util.Date();
            //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
        
        ServiceVeto sv= new ServiceVeto();
        Veto v=new Veto(textnom.getText(),textprenom.getText(),textmail.getText(),Integer.parseInt(textetl.getText()),textrue.getText(),
                villetext.getText(),textgouvernorat.getText(),Integer.parseInt(textcodepostal.getText()),imagetext.getText(),desctext.getText(),
                "Veto",logintext.getText(),mdptext.getText(),sqldate);
        System.out.println(v);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilVeto.fxml"));
        Parent root=loader.load();
        
        ProfilVetoController dpvc =loader.getController();
        dpvc.setVal(textnom.getText());
        dpvc.setNomlabel(textnom.getText());
        dpvc.setDesclabel(desctext.getText());
        dpvc.setPrenomlabel(textprenom.getText());
        dpvc.setImgView(image);
 
        
//        pvc.setNom(textnom.getText()); 
//        
//        System.out.println(pvc.getNom());
//        System.out.println(sv.getVeto(pvc.getNom()).getPrenom());
//        pvc.setPrenomlabel(sv.getVeto(pvc.getNom()).getPrenom());
//        pvc.setDesclabel(sv.getVeto(pvc.getNom()).getDescription());
//        pvc.setImgView(image);
        
        textnom.getScene().setRoot(root);

        try {
            sv.AjouterVeto(v);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutVetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
             }
 
    
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
}
