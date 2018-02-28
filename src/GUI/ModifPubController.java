/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifPubController implements Initializable {

    ObservableList<String> typelist = FXCollections.observableArrayList("Nourriture", "Soins", "Dresssage", "Autres");

    @FXML
    private ComboBox<String> type;
    @FXML
    private TextArea description;
    @FXML
    private TextArea url;
    private File file;
    private Image img;
    @FXML
    private ImageView image;
    int x;
    @FXML
    private Label photo;
    @FXML
    private Label photoName;
    Session s=Session.getInstance();
    String signalll;
    @FXML
    private AnchorPane back;
    @FXML
    private Label titre;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void modifPublication(int id) throws SQLException, MalformedURLException {
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
                            Logger.getLogger(ModifPubController.class.getName()).log(Level.SEVERE, null, ex);
                        }
           
              
            
                       
                    }
                });
                back.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')");
titre.setStyle("-fx-text-fill:orange");
        ServicePublication sp = new ServicePublication();
        Publication pub = sp.afficherPublication2(id);
        type.setValue(pub.getType());
        type.setItems(typelist);
        description.setText(pub.getDescription());
        url.setText(pub.getImage());
        photoName.setText(pub.getPhotoName());
        x = pub.getIdpub();
        signalll=pub.getSignall();
        System.out.println(pub);

        Image img = new Image("file:///" + pub.getImage());
        ImageView imagee = new ImageView(img);
        imagee.setFitHeight(200);
        imagee.setFitWidth(200);
        photo.setGraphic(imagee);
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        try {
            ServicePublication sp = new ServicePublication();

            LocalDateTime ldt = LocalDateTime.now();
            String date = ldt.getDayOfMonth() + "-" + ldt.getMonth() + "-" + ldt.getYear();
            String time = ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();

            Publication p = new Publication(x, type.getValue(), description.getText(), url.getText(), s.IdSession(), date, time, photoName.getText(),signalll);
            sp.ModifierPublication(p);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichagefront.fxml"));
            Parent root = loader.load();
            AffichagefrontController afc = loader.getController();
            description.getScene().setRoot(root);

        } catch (SQLException ex) {
            Logger.getLogger(AjoutPublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modifimage(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        if (file != null) {
            url.setText(file.getPath());
            img = new Image(file.toURI().toURL().toString());
            photoName.setText(file.getName());
            System.out.println(file.toURI().toURL().toString());
            image.setImage(img);
            photo.setVisible(false);
        }
    }

   
}
