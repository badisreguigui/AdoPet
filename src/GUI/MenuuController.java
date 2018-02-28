/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class MenuuController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnaa;
    @FXML
    private JFXButton btnveto;
    @FXML
    private JFXButton btnpets;
    @FXML
    private JFXButton btnevent;
    @FXML
    private JFXButton btnshop;
    @FXML
    private JFXButton btnpub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Adoptionlink(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGmaps.fxml"));
           Parent root = loader.load();
            FXMLGmapsController spc = loader.getController();
            btnshop.getScene().setRoot(root);
    }

    @FXML
    private void Vetoslink(ActionEvent event) {
    }

    @FXML
    private void goToPetSitting(ActionEvent event) {
    }

    @FXML
    private void Eventslink(ActionEvent event) {
    }

    @FXML
    private void shoplink(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));
           Parent root = loader.load();
            ShopController spc = loader.getController();
            btnshop.getScene().setRoot(root);
    }

    @FXML
    private void bavardonslink(ActionEvent event) {
    }

    @FXML
    private void accouplementlink(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
           Parent root = loader.load();
            FXMLDisplayAllController spc = loader.getController();
            btnshop.getScene().setRoot(root);
    }
    
}
