/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Publication;
import Entities.Session;
import Services.ServicePublication;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutPublicationController implements Initializable {

    ObservableList<String> typelist = FXCollections.observableArrayList("Nourriture", "Soins", "Dresssage");

    @FXML
    private TextArea description;
    @FXML
    private Button ajoutImage;
    private File file;
    private Image img;
    @FXML
    private Button ajoutPub;
    @FXML
    private TextArea url;
    @FXML
    private ImageView image;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Label photoName;
    Session s=Session.getInstance();

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setValue("Autres");
        type.setItems(typelist);
    }

    @FXML
    private void ajouterPub(ActionEvent event) throws IOException {
        try {
            ServicePublication sp = new ServicePublication();

            LocalDateTime ldt = LocalDateTime.now();
            String date = ldt.getDayOfMonth() + "-" + ldt.getMonth() + "-" + ldt.getYear();
            String time = ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();

            Publication p = new Publication(type.getValue(), description.getText(), url.getText(), s.IdSession(), date, time,photoName.getText(),",");
            sp.AjouterPublication(p);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagefront.fxml"));
            Parent root = loader.load();
            AffichagefrontController afc = loader.getController();
            description.getScene().setRoot(root);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutPublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AjoutImage(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            url.setText(file.getPath());
            img = new Image(file.toURI().toURL().toString());
            photoName.setText(file.getName());
            System.out.println(file.toURI().toURL().toString());
            image.setImage(img);
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("affichagefront.fxml"));
            Parent root = loader.load();
            AffichagefrontController afc = loader.getController();
            description.getScene().setRoot(root);
    }

}
