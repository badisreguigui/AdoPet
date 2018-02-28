/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Order;
import Entities.Panier;
import Entities.Session;
import Entities.User;
import Services.OrderServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class PayementController implements Initializable {

    @FXML
    private Label prix;
    @FXML
    private JFXButton payer;
    @FXML
    private JFXTextField card;
    
    Session session = Session.getInstance();
    @FXML
    private AnchorPane pay;
    //java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
    /**
     * Initializes the controller class.
     */
    public void setPrix(String p){
        prix.setText(p);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Stripe.apiKey = "sk_test_XA950iYFnIrsHN5H9xjtp1In";
            payer.setStyle(
                
                "-fx-min-width: 20px; " +
                "-fx-min-height: 20px; " +
                "-fx-max-width: 20px; " +
                "-fx-max-height: 20px;"
        );
        Image image = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/stripe.png",140,140,true,true);
        payer.setGraphic(new ImageView(image));
        payer.setText("");
    
        // TODO
        
           pay.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')"+"-fx-background-size 1400 768");
      
    }    

    @FXML
    private void payer(ActionEvent event) throws IOException {
        
        try {
            
            
            /*Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", 47);
            chargeParams.put("currency", "usd");
            chargeParams.put("description", "Charge for michael.jackson@example.com");
            chargeParams.put("source", "tok_visa");
            // ^ obtained with Stripe.js
            
            RequestOptions options = RequestOptions
            .builder()
            .setIdempotencyKey("94766id4zEB0tt9X")
            .build();
            
            Charge.create(chargeParams, options);*/
            Date date = new Date();
            String user = session.loginSession();
            System.out.println(prix.getText());
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount",Integer.parseInt(prix.getText()));
            chargeParams.put("currency", "usd");
            chargeParams.put("description", "Charge for Badis");
            if(card.getText().equals("tok_mastercard")|| card.getText().equals("tok_visa")|| card.getText().equals("tok_visa_debit")|| card.getText().equals("tok_tok_mastercard_debit")|| card.getText().equals("tok_mastercard_prepaid")|| card.getText().equals("tok_amex")|| card.getText().equals("tok_discover")|| card.getText().equals("tok_diners")|| card.getText().equals("tok_jcb"))
            {            
        chargeParams.put("source", card.getText());
                OrderServices o = new OrderServices();
                Order or = new Order(Integer.parseInt(prix.getText()),user);
                o.insert(or);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("You payed : "+prix.getText()+" Votre panier est vide");
        alert1.show();
        prix.getScene().getWindow().hide();
        Panier.getInstance().closePanier();
            }
            else if(card.getText().equals("4000000000000069")){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Expired Card");
        alert1.show();
            }
            else{
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Invalid Card");
        alert1.show();
                
            }
// ^ obtained with Stripe.js
Charge.create(chargeParams);
        } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException ex) {
            Logger.getLogger(PayementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
