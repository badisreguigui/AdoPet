/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class PetSittingController implements Initializable {
    @FXML
    private AnchorPane AAcceuil;
    @FXML
    private SplitPane choix;
    @FXML
    private AnchorPane choix1;
    @FXML
    private JFXButton btndemande;
    @FXML
    private AnchorPane choix2;
    @FXML
    private JFXButton btnoffre;
    @FXML
    private JFXButton voff;
    @FXML
    private JFXButton cdem;
    @FXML
    private JFXButton vdem;
    @FXML
    private JFXButton coff;
    @FXML
    private JFXButton home;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coff.setVisible(false);
        cdem.setVisible(false);
        vdem.setVisible(false);
        voff.setVisible(false);
    }    

    


    @FXML
    private void creerDemande(ActionEvent event) throws IOException {
        cdem.getScene().getWindow().hide();
        Stage fordemande = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AjoutDemande.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        fordemande.centerOnScreen();
        fordemande.setScene(scene);
        fordemande.setResizable(false);
        fordemande.show();
        
        
    }

    @FXML
    private void voirChoixOwner(ActionEvent event) {
        voff.setVisible(true);
       coff.setVisible(true);
    }

    @FXML
    private void voirChoixSitteur(ActionEvent event) {
        vdem.setVisible(true);
        cdem.setVisible(true);
    }

    @FXML
    private void voirOffres(ActionEvent event) throws IOException {
        voff.getScene().getWindow().hide();
        Stage fordemande = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AffichageOffres.fxml"));
        Parent root = loader.load();
        AffichageOffresController cnt = loader.getController();
        
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        fordemande.centerOnScreen();
        fordemande.setScene(scene);
        fordemande.setResizable(false);
        fordemande.show();

    }

    @FXML
    private void voirDemandes(ActionEvent event) throws IOException {
        vdem.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AffichageDemandes.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }

    @FXML
    private void creerOffre(ActionEvent event) throws IOException {
        coff.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AjoutOffre.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        home.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/GUI/Acceuil.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        home.centerOnScreen();
        home.setScene(scene);
        home.setResizable(false);
        home.show();
        
    }
    
}
