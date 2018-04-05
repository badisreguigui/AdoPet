/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeSitting;
import Entities.Session;
import Services.ServicePetSitting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
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
public class DemandesfavoritesController implements Initializable {
    @FXML
    private AnchorPane apane;
    @FXML
    private JFXButton formoffre;
    @FXML
    private JFXListView<DemandeSitting> list;
    @FXML
    private JFXButton back;
    @FXML
    private RadioButton demandeFavoris;
    @FXML
    private RadioButton mesdemandes;

    ServicePetSitting ps = new ServicePetSitting();
    Session s = Session.getInstance();
    int idCon = s.IdSession();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Affichage les demandes favoris 
         try {
            ObservableList<DemandeSitting> listDemandes = ps.afficherDemandes();
            list.setExpanded(Boolean.TRUE);
            list.depthProperty().set(2);
            list.setCellFactory((ListView<DemandeSitting> arg0) ->{
                ListCell<DemandeSitting> cell = new ListCell<DemandeSitting>() {
                    @Override
                    protected void updateItem(DemandeSitting de, boolean btl) {
                        try {
                            super.updateItem(de, btl);
                            if((ps.duplicatDemande(idCon,de.getId_demande()) > 0 )){
                                //image
                             
                                Image IMAGE_RUBY = new Image("/images/demande.jpg");
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
                                                ps.supprimerDemandeFav(idCon, de.getId_demande());
                                                btn.getScene().getWindow().hide();
                                                Stage foroffre = new Stage();
                                                Parent root;
                                                try {
                                                    root = FXMLLoader.load(getClass().getResource("/fxml/demandesfavorites.fxml"));
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
                                            DetaillsController controller = loader.getController();
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
    private void creerOffre(ActionEvent event) throws IOException {
        formoffre.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AjoutDemande.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
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
    private void afficherMesDemandes(ActionEvent event) throws IOException {
        mesdemandes.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/mesdemandes.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
        
    }

    @FXML
    private void afficherToutLesDemandes(ActionEvent event) throws IOException {
        mesdemandes.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AffichageDemandes.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }
    
}
