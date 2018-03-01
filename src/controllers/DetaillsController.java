/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeSitting;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class DetaillsController implements Initializable {
    @FXML
    private Label titre;
    @FXML
    private Label lieu;
    @FXML
    private Label dd;
    @FXML
    private Label hd;
    @FXML
    private Label df;
    @FXML
    private Label hf;
    @FXML
    private Label visite;
    @FXML
    private Label prom;
    @FXML
    private Label nourri;
    @FXML
    private Label chat;
    @FXML
    private Label chien;
    @FXML
    private Label autre;
    @FXML
    private TextArea desc;
    @FXML
    private JFXButton service;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setting(DemandeSitting d){
        titre.setText(d.getTitre());
        desc.setText(d.getDescription());
        desc.setEditable(false);
        lieu.setText("A "+d.getLieu());
        dd.setText("A partir De: "+String.valueOf(d.getDate_debut_demande()));
        df.setText("Jusq'au: "+String.valueOf(d.getDate_fin_demande()));
        hd.setText(" à "+String.valueOf(d.getTmp_debut()));
        hf.setText(" à "+String.valueOf(d.getTmp_fin()));
        if (d.isNourriture() == true){
            nourri.setText("Je préfere un garde qui peux nourrir mon animal");
        }else{
            nourri.setText("J'apporte ma propre nourriture");  
        }
        if (d.isVisite()== true){
            visite.setText("Je veux visiter mon animal"); 
        }else{
            visite.setText("Je ne visiterai pas mon animal lors de sa garde"); 
        }
        if (d.isPromenade()== true){
            prom.setText("J'aimerai que mon animal soit promené par son garde");   
        }else{
            prom.setText("J'interdit au garde de promener mon animal");    
        }
        if(d.isChat() == true)
            chat.setText("type d'animal: Chat");
        if(d.isChien() == true)
            chien.setText("type d'animal: Chien");
        if(d.isAutre())
            autre.setText("type d'animal: autre");
                            
                            
                   
        
    }

    @FXML
    private void reserver(ActionEvent event) throws IOException {
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/ReservationOffre.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }
}
