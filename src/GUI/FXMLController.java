/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pet;
import Services.ServicePet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLController implements Initializable {

    @FXML
    private Button addProfile;
    @FXML
    private Label titleLabel;
    @FXML
    private Label breedLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private TextField breed;
    @FXML
    private TextField age;
    @FXML
    private TextField color;
    @FXML
    private Label governateLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label zipLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label imageLabel;
    @FXML
    private TextArea description;
    @FXML
    private TextField governate;
    @FXML
    private TextField city;
    @FXML
    private TextField zip;
    @FXML
    private TextField image;
    @FXML
    private TextField sex;
    @FXML
    private Label sexLabel1;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addProfile(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur");
        
        if (name.getText().equals("")) {
            alert.setHeaderText("erreur nom annonce");
            alert.setContentText("nom d'annonce doit être rempli");
            alert.showAndWait();
        }
        
        else {
        ServicePet sp = new ServicePet();
        Pet p = new Pet(name.getText() ,breed.getText(), Integer.parseInt(age.getText()), sex.getText(), color.getText(), governate.getText(), city.getText(), Integer.parseInt(zip.getText()), description.getText(), image.getText());   
        try {
            sp.AjouterPet(p);
            
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Your cute pet '"+ name.getText()+"' has now his own profile in our application" );
            alert2.showAndWait();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();    
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    
}
