/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeSitting;
import Entities.OffreSitting;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class DetController implements Initializable {
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
    private TextArea desc;
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
    private JFXButton service;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setting(OffreSitting d){
        titre.setText(d.getTitre());
        desc.setText(d.getDescription());
        desc.setEditable(false);
        lieu.setText("A "+d.getLieu());
        dd.setText("A partir De: "+String.valueOf(d.getDate_debut_dispo()));
        df.setText("Jusq'au: "+String.valueOf(d.getDate_fin_dispo()));
        hd.setText(" à "+String.valueOf(d.getTmp_debut()));
        hf.setText(" à "+String.valueOf(d.getTmp_fin()));
        if (d.isNourriture() == true){
            nourri.setText("Je peux nourrir votre animal lors de sa garde");
        }else{
            nourri.setText("Apportez votre propre nourriture");  
        }
        if (d.isVisite()== true){
            visite.setText("Vous pouvez visiter votre animal"); 
        }else{
            visite.setText("La visite est interdit lors de la garde"); 
        }
        if (d.isPromenade()== true){
            prom.setText("Je peux promener votre animal lors de sa garde");   
        }else{
            prom.setText("Je n'offre pas de promenades");    
        }
        if(d.isChat() == true)
            chat.setText("Chat permis");
        else
            chat.setText("Chat non permis");
        if(d.isChien() == true)
            chien.setText("Chien permis");
        else
            chien.setText("Chien non permis");
        if(d.isAutre())
            autre.setText("D'autre type d'animaux sont permis");
        else
            autre.setText("D'autre type d'animaux ne sont permis");  
    }
    
    @FXML
    private void reserver(ActionEvent event) throws IOException {
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/ReservationDemande.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
        
    }
    
}
