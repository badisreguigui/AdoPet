/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Publication;
import Entities.Session;
import Services.ServicePublication;
import com.jfoenix.controls.JFXButton;
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
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutPublicationController implements Initializable {

    ObservableList<String> typelist = FXCollections.observableArrayList("Nourriture", "Soins", "Dressage");

    @FXML
    private TextArea description;
    private File file;
    private Image img;
    @FXML
    private TextArea url;
    @FXML
    private ImageView image;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Label photoName;
    Session s=Session.getInstance();
    @FXML
    private JFXButton AjoutImage;
    @FXML
    private JFXButton ajouterPub;
    @FXML
    private AnchorPane back;
    @FXML
    private Label titre;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setValue("Autres");
        type.setItems(typelist);
        System.out.println("hhh");
        back.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')");
        titre.setStyle("-fx-text-fill:orange");
          Image img1 = new Image("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\retour.png");
                ImageView image1 = new ImageView(img1);
                image1.setFitHeight(30);
                image1.setFitWidth(30);
                retour.setGraphic(image1);
                  retour.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichagefront.fxml"));
            Parent root;
                        try {
                            root = loader.load();
                            AffichagefrontController afc = loader.getController();
            description.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(AjoutPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
            
                       
                    }
                });
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

 

  
  

}
