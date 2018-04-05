/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Entities.Session;
import Services.ServiceCommentaire;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifComController implements Initializable {

    @FXML
    private TextField description;
    int idcommentaire;
    int idpublication;
    Session s=Session.getInstance();
    @FXML
    private AnchorPane back;
    @FXML
    private Label retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet1.jpg')");
           Image img1 = new Image("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\retour.png");
                ImageView image1 = new ImageView(img1);
                image1.setFitHeight(30);
                image1.setFitWidth(30);
                retour.setGraphic(image1);
                  retour.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePublication.fxml"));
                            Parent root5 = null;
                            try {
                                root5 = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            OnePublicationController controller = loader.getController();
                            controller.loadPublication(idpublication);
                            controller.createpage(idpublication);
                            controller.ajoutercommentaire(idpublication);
                            description.getScene().setRoot(root5);
                        } catch (SQLException ex) {
                            Logger.getLogger(ModifComController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        // TODO
    }    

    @FXML
    private void modifiercom(ActionEvent event) throws SQLException {
        ServiceCommentaire sc=new ServiceCommentaire();
        Commentaire com= new Commentaire(idcommentaire, description.getText(), s.IdSession(), idpublication);
        sc.ModifierCommentaire(com);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePublication.fxml"));
                        Parent root5 = null;
                        try {
                            root5 = loader.load();
                        } catch (IOException ex) {
                            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        OnePublicationController controller = loader.getController();
                        controller.loadPublication(idpublication);
                        controller.createpage(idpublication);
                        controller.ajoutercommentaire(idpublication);
                        description.getScene().setRoot(root5);
                        
        
    }

    public void loadCommentaire(int idcom)
    {
        ServiceCommentaire sc= new ServiceCommentaire();
        Commentaire com=sc.afficherCommentaire(idcom);
        description.setText(com.getDescription());
        idcommentaire=com.getIdcom();
        idpublication=com.getIdpub();
        
        
    }
}
