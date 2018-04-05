/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Button openC;
    @FXML
    private Button openA;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane.setStyle("-fx-background-image: url('images/adopet2.jpg');");
        
        openC.setStyle(
                
                "-fx-min-width: 600px; " +
                "-fx-min-height: 400px; " +
                "-fx-max-width: 600px; " +
                "-fx-max-height: 400px;"
        );
        File file4 = new File("/Users/mac/Desktop/accouplement.gif");
        //Image image = new Image(file.toURI().toString());
        String pt4 = file4.toURI().toString();
        Image image4 = new Image(pt4, 600 ,400, false, false);
        openC.setGraphic(new ImageView(image4));
        openC.setText("");
        
        openA.setStyle(
                
                "-fx-min-width: 600px; " +
                "-fx-min-height: 400px; " +
                "-fx-max-width: 600px; " +
                "-fx-max-height: 400px;"
        );
        File file = new File("/Users/mac/Desktop/adoption.gif");
        //Image image = new Image(file.toURI().toString());
        String pt = file.toURI().toString();
        Image image = new Image(pt, 600 ,400, false, false);
        openA.setGraphic(new ImageView(image));
        openA.setText("");
        
    }    

    @FXML
    private void openC(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
        Parent root = loader.load();
        FXMLDisplayAllController afx = loader.getController();
        openA.getScene().setRoot(root);
    }

    @FXML
    private void openA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGmaps.fxml"));
        Parent root = loader.load();
        FXMLGmapsController afx = loader.getController();
        openC.getScene().setRoot(root);
    }
    
    
    
}
