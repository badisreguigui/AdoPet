/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServicePublication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pieChart;
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
            titre.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(AjoutPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
            
                       
                    }
                });
        
        
        
        back.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')");
        titre.setStyle("-fx-text-fill:orange");
        ServicePublication sp=new ServicePublication();
        
        ObservableList<PieChart.Data> pieChartData = null;
        try {
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Nourriture",sp.countpub("Nourriture")),
                    new PieChart.Data("Soins", sp.countpub("Soins")),
                    new PieChart.Data("Dressage", sp.countpub("Dressage"))
            );
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pieChart.setData(pieChartData);
    }    
    
}
