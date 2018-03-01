/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeFavoris;
import Entities.DemandeSitting;
import Entities.OffreFavoris;
import Entities.OffreSitting;
import Entities.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Services.ServicePetSitting;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.mysql.jdbc.RowData;
import com.mysql.jdbc.RowDataStatic;
import java.awt.Rectangle;
import java.io.File;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jfxtras.scene.control.ImageViewButton;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class OffresfavoritesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ServicePetSitting ps = new ServicePetSitting();
    Session s = Session.getInstance();
    int idCon = s.IdSession();
    @FXML
    private AnchorPane aafficheoffre;
    @FXML
    private JFXButton formdemande;
    @FXML
    private JFXListView<OffreSitting> list;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton toutOffres;
    @FXML
    private JFXButton mesdemandes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Affichage les demandes favoris 
         try {
            ObservableList<OffreSitting> listDemandes = ps.afficherOffres();
            list.setExpanded(Boolean.TRUE);
            list.depthProperty().set(2);
            list.setCellFactory((ListView<OffreSitting> arg0) ->{
                ListCell<OffreSitting> cell = new ListCell<OffreSitting>() {
                    @Override
                    protected void updateItem(OffreSitting de, boolean btl) {
                        try {
                            super.updateItem(de, btl);
                            if((ps.duplicatDemande(idCon,de.getId_offre()) > 0 )){
                                //image
                                
                                Image IMAGE_RUBY = new Image("/images/offre.jpg");
                                ImageView imgview = new ImageView(IMAGE_RUBY);
                                imgview.setFitHeight(250);
                                imgview.setFitWidth(250);
                                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(imgview.getFitWidth(), imgview.getFitHeight());
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
                                
                                //remove favoris
                                Image f = new Image(getClass().getResourceAsStream("/Images/favf.png"));
                                ImageView favf = new ImageView(f);
                                JFXButton btn = new JFXButton();
                                favf.setFitHeight(50);
                                favf.setFitWidth(50);
                                btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        if(mouseEvent.getClickCount() == 1){
                                            
                                            try {
                                                ps.supprimerOffreFav(idCon, de.getId_offre());
                                                btn.getScene().getWindow().hide();
                                                Stage foroffre = new Stage();
                                                Parent root;
                                                try {
                                                    root = FXMLLoader.load(getClass().getResource("/fxml/offresfavorites.fxml"));
                                                    Scene scene = new Scene(root);
                                                    scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                                                    foroffre.centerOnScreen();
                                                    foroffre.setScene(scene);
                                                    foroffre.setResizable(false);
                                                    foroffre.show();
                                                } catch (IOException ex) {
                                                    Logger.getLogger(DemandesfavoritesController.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                
                                            } catch (SQLException ex) {
                                                Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }
                                });
                                
                                
                                
                                //cell param
                                HBox hbox = new HBox();
                                hbox.getChildren().addAll(imgview, btn);
                                setGraphic(hbox);
                                setFont(Font.font("Arial Bold", 16));
                                
                                //popup
                                setOnMouseClicked(event -> {
                                    if (event.getClickCount() == 2) {
                                        try {
                                            final Stage dialog = new Stage();
                                            FXMLLoader loader = new FXMLLoader();
                                            loader.setLocation(getClass().getResource("/FXML/detaills.fxml"));
                                            Parent p = loader.load();
                                            
                                            Scene scene = new Scene(p);
                                            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                                            //access the controller and call a method
                                            DetController controller = loader.getController();
                                            controller.setting(de);
                                            dialog.centerOnScreen();
                                            dialog.setScene(scene);
                                            dialog.setResizable(false);
                                            dialog.show();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void creerDemande(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void afficherToutOffre(ActionEvent event) {
    }

    @FXML
    private void afficherMesDemandes(ActionEvent event) {
    }
}
