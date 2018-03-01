/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Rateme;
import Service.ServiceRating;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLDisplayProfilCouplingController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label breedLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Rating ratingBar;
    
    private int returnedId;
    @FXML
    private Label checkIfRated;
    @FXML
    private PieChart pieChart;

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText(nameLabel);
    }

    public Label getBreedLabel() {
        return breedLabel;
    }

    public void setBreedLabel(String breedLabel) {
        this.breedLabel.setText(breedLabel);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(Image image) {
        this.imageView.setImage(image);
    }

    public int getReturnedId() {
        return returnedId;
    }

    public void setReturnedId(int returnedId) {
        this.returnedId = returnedId;
    }
    
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ratingBar.setRating(0);

        
    }    
    
//    public void checkIfRatedBefore() throws SQLException{
//        ServiceRating sr = new ServiceRating();
//        System.out.println(returnedId);
//        int check = sr.checkIfRated(2, returnedId);
//        
//        System.out.println(check);
//        
//        if (check == 0) {
//            System.out.println("jdid");
//        }else {
//            System.out.println("deja rata");
//        }
//    }

    @FXML
    private void rateMe(MouseEvent event) throws SQLException {
        
        
        double ratingvalue = ratingBar.getRating();
//        System.out.println(ratingvalue);
        
        Rateme r = new Rateme(ratingvalue, returnedId, 2);
        
        ServiceRating sr = new ServiceRating();
        
        int check = sr.checkIfRated(2, returnedId);
        
        if (check == 0) {
            sr.AjouterRating(r);   
            ratingBar.setDisable(true);
        }else {
            ratingBar.setDisable(true);
            checkIfRated.setText("you already submitted your vote for this pet");
        }

        
        
        // displaying the rating after rateme method
        System.out.println(returnedId);
        int n = sr.nbr5stars(returnedId);
        System.out.println(n);
        
        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                        new PieChart.Data("5 stars", sr.nbr5stars(returnedId)),
                        new PieChart.Data("4 stars", sr.nbr4stars(returnedId)),
                        new PieChart.Data("3 stars", sr.nbr3stars(returnedId)),
                        new PieChart.Data("2 stars", sr.nbr2stars(returnedId)),
                        new PieChart.Data("I stars", sr.nbr1stars(returnedId))
                );
        
        pieChart.setData(pieChartData);
    }
    
}
