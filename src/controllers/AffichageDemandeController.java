/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeFavoris;
import Entities.DemandeSitting;
import Entities.OffreSitting;
import Entities.Session;
import Services.ServicePetSitting;
import Outils.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.text.StyleConstants;
//import jfxtras.scene.control.ImageViewButton;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class AffichageDemandeController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton formoffre;  
    @FXML
    private JFXListView<DemandeSitting> list;
    @FXML
    private JFXButton demandeFavoris;
    @FXML
    private JFXButton mesdemandes;
    @FXML
    private AnchorPane apane;
    @FXML
    private JFXButton back;
    
    ServicePetSitting ps = new ServicePetSitting();
    Session s = Session.getInstance();
    int idCon = s.IdSession();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Affichage tout les demandes 
        try {
            
            ObservableList<DemandeSitting> listDemandes = ps.afficherDemandes();
            ObservableList<DemandeFavoris> favoris = ps.getDFavById(idCon);
            list.setExpanded(Boolean.TRUE);
            list.depthProperty().set(2);
            list.setCellFactory((ListView<DemandeSitting> arg0) -> {
                ListCell<DemandeSitting> cell = new ListCell<DemandeSitting>() {
                    @Override
                    protected void updateItem(DemandeSitting de, boolean btl) {
                        super.updateItem(de, btl);
                        if (de != null) {
                            try {
                            
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
                                
                                //ajout favoris
                                Image f1 = new Image(getClass().getResourceAsStream("/Images/fav.png"));
                                ImageView fav = new ImageView(f1);
                                fav.setFitHeight(50);
                                fav.setFitWidth(50);
                                Image f2 = new Image(getClass().getResourceAsStream("/Images/favf.png"));
                                ImageView favf = new ImageView(f2);
                                JFXButton btn = new JFXButton();
                                favf.setFitHeight(50);
                                favf.setFitWidth(50);
                                if((ps.duplicatDemande(idCon,de.getId_demande()) > 0 )){
                                    btn.setGraphic(favf);
                                }else{
                                    btn.setGraphic(fav);
                                }
                                btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        if(mouseEvent.getClickCount() == 1){
                                            try {
                                                if((ps.duplicatDemande(idCon,de.getId_demande()) > 0 )){
                                                    btn.setGraphic(fav);
                                                    ps.supprimerDemandeFav(idCon, de.getId_demande());
                                                }else{
                                                    DemandeFavoris dfa = new DemandeFavoris();
                                                    dfa.setId_user(idCon);
                                                    dfa.setIddemande(de.getId_demande());
                                                    ps.ajoutDemandeFavori(dfa);
                                                    btn.setGraphic(favf);
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
                            } catch (SQLException ex) {
                                Logger.getLogger(AffichageDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
    private void afficherMesDemandes(ActionEvent event) throws IOException {
        mesdemandes.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/mesdemandes.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        home.centerOnScreen();
        home.setScene(scene);
        home.setResizable(false);
        home.show();
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
        
       
    
    

    

  
    
}       
