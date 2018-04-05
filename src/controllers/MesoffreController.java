/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeSitting;
import Entities.OffreSitting;
import Entities.Session;
import Services.ServicePetSitting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class MesoffreController implements Initializable {
    @FXML
    private AnchorPane aafficheoffre;
    @FXML
    private JFXButton formdemande;
    @FXML
    private JFXListView<OffreSitting> list;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton demandeFavoris;
    @FXML
    private JFXButton toutLesOffres;

    /**
     * Initializes the controller class.
     */
    
    ServicePetSitting ps = new ServicePetSitting();
    Session s = Session.getInstance();
    int idCon = s.IdSession();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<OffreSitting> listDemandes = ps.afficherOffres();
            list.setExpanded(Boolean.TRUE);
            list.depthProperty().set(2);
            list.setCellFactory((ListView<OffreSitting> arg0) -> {
                ListCell<OffreSitting> cell = new ListCell<OffreSitting>() {
                    @Override
                    protected void updateItem(OffreSitting de, boolean btl) {
                        super.updateItem(de, btl);
                        
                        if (de != null && de.getId_user() == idCon ) {
                            
                         
                            Image IMAGE_RUBY = new Image("/images/offre.jpg");
                            ImageView imgview = new ImageView(IMAGE_RUBY);
                            imgview.setFitHeight(250);
                            imgview.setFitWidth(250);
                            Rectangle clip = new Rectangle(imgview.getFitWidth(), imgview.getFitHeight());
                            clip.setArcWidth(20);
                            clip.setArcHeight(20);
                            imgview.setClip(clip);
                            SnapshotParameters parameters = new SnapshotParameters();
                            parameters.setFill(Color.TRANSPARENT);
                            WritableImage image = imgview.snapshot(parameters, null);
                            imgview.setClip(null);
                            imgview.setEffect(new DropShadow(20, Color.BLACK));
                            imgview.setImage(image);
                            //titre
                            setText(de.getTitre());
                            //modif supprim
                            JFXButton modif = new JFXButton("Modifier");
                            JFXButton supprim = new JFXButton("Supprimer");
                            supprim.setStyle("-fx-background-color: red");
                            
                            supprim.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if(mouseEvent.getClickCount() == 1){
                                        try {
                                            ButtonType foo = new ButtonType("Oui !", ButtonBar.ButtonData.OK_DONE);
                                            ButtonType bar = new ButtonType("Non !", ButtonBar.ButtonData.CANCEL_CLOSE);
                                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                            "Etes vous sur de vouloir supprimer cette offre ?",
                                            foo,
                                            bar);

                                        alert.setTitle("Information Dialog");
                                        Optional<ButtonType> result = alert.showAndWait();

                                        if (result.isPresent() && result.get() == foo) {
                                            ps.supprimerOffre(de.getId_offre());
                                            getScene().getWindow().hide();
                                            Stage home = new Stage();
                                            Parent root= FXMLLoader.load(getClass().getResource("/fxml/AffichageOffres.fxml"));
                                            Scene scene = new Scene(root);
                                            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                                            home.centerOnScreen();
                                            home.setScene(scene);
                                            home.setResizable(false);
                                            home.show();
                                        }  
                                        } catch (SQLException ex) {
                                            Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                            Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            });
                            modif.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if(mouseEvent.getClickCount() == 1){
                                        try {
                                            final Stage dialog = new Stage();
                                            FXMLLoader loader = new FXMLLoader();
                                            loader.setLocation(getClass().getResource("/FXML/modifoffre.fxml"));
                                            Parent p = loader.load();
                                            
                                            Scene scene = new Scene(p);
                                            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                                            //access the controller and call a method
                                            ModifoffreController controller = loader.getController();
                                            controller.setting(de);
                                            controller.setId(de.getId_offre());
                                            dialog.centerOnScreen();
                                            dialog.setScene(scene);
                                            dialog.setResizable(false);
                                            dialog.show();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                       });
                            
                            
                            //cell param
                            
                            HBox hbox = new HBox();
                            hbox.getChildren().addAll(imgview, modif,supprim);
                            setGraphic(hbox);
                            setFont(Font.font("Arial Bold", 16));
                            
                           
                        }
                    }
                };
                return cell;
            });
            list.setItems(listDemandes);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void creerDemande(ActionEvent event) throws IOException {
        formdemande.getScene().getWindow().hide();
        Stage fordemande = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AjoutOffre.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        fordemande.centerOnScreen();
        fordemande.setScene(scene);
        fordemande.setResizable(false);
        fordemande.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/PetSitting.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }

    @FXML
    private void afficherDemandesFavoris(ActionEvent event) throws IOException {
        demandeFavoris.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/demandesfavorites.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }

    @FXML
    private void afficherToutOffre(ActionEvent event) throws IOException {
        toutLesOffres.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AffichageOffres.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }
    
}
