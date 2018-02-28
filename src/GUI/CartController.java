/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Command_Line;
import Entities.Panier;
import Entities.Produit;
import Services.ProduitService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class CartController implements Initializable {

    @FXML
    private AnchorPane cont;
    @FXML
    private ListView<HBox> liste;
    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Label lb4;

    /**
     * Initializes the controller class.
     */

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        bt1.setStyle(
                
                "-fx-min-width: 20px; " +
                "-fx-min-height: 20px; " +
                "-fx-max-width: 20px; " +
                "-fx-max-height: 20px;"
        );
        Image image2 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/payment.png",50,50,true,true);
        bt1.setGraphic(new ImageView(image2));
        bt1.setText("");
      
        ImageView imageView = new ImageView();
        ObservableList<HBox> l1= FXCollections.observableArrayList();
        
        Panier panier=Panier.getInstance();
        
        int prix=0;
        //System.out.println(panier.p);
        for (Command_Line c : panier.p) {
            //System.out.println(c);
            HBox hb =new HBox();
            ImageView im=new ImageView();
            //URL url2=getClass().getResource(c.getProduct().getImageproduit());
            Image image = new Image("file:///"+c.getProduct().getImageproduit());
            im.setImage(image);
            im.setFitHeight(150);
            im.setFitWidth(150);
            Label lb1=new Label(c.getProduct().getNomproduit());
            lb1.setTranslateX(50);
            lb1.setTranslateY(20);
            lb1.setFont(new Font("Cambria", 18));
            Label lb2=new Label("          "+Integer.toString(c.quantity)+"        ");
            lb2.setTranslateX(50);
            lb2.setTranslateY(20);
            lb2.setFont(new Font("Cambria", 18));
            Label lb3=new Label(Integer.toString(c.quantity*c.getProduct().getPrix())+" Cents     ");
            lb3.setTranslateX(50);
            lb3.setTranslateY(20);
            lb3.setFont(new Font("Cambria", 18));
            TextField t=new TextField();
            t.setTranslateX(50);
            t.setTranslateY(20);
            prix+=c.quantity*c.getProduct().getPrix();
            
            lb4.setText(Integer.toString(prix));
            //lb4.setTranslateX(50);
            
            Button bt3=new Button("supprimer");
             bt3.setStyle(
                
                "-fx-min-width: 20px; " +
                "-fx-min-height: 20px; " +
                "-fx-max-width: 20px; " +
                "-fx-max-height: 20px;"
        );
            bt3.setTranslateX(100);
            bt3.setTranslateY(20);
            Image image4 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/delete.png",50,50,true,true);
        bt3.setGraphic(new ImageView(image4));
        bt3.setText("");
             bt3.addEventHandler(MouseEvent.MOUSE_CLICKED,event->
              {
                  panier.removeLine(c);
                 
                  Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Deleted from Cart");
        alert1.show();
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("Cart.fxml"));
                    Parent root;
                    root = loader.load();
                    GUI.CartController sp=loader.getController();
                    cont.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              }
                    );
            t.addEventHandler(MouseEvent.MOUSE_EXITED, event->
            {
                if(t.getText().isEmpty()){
                    
                }
                else if(Integer.parseInt(t.getText())> c.getProduct().getQuantite()){
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Quantité disponible est : "+c.getProduct().getQuantite());
        alert1.show();
                }
                else 
                c.setQuantity(Integer.parseInt(t.getText()));
                
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("Cart.fxml"));
                    Parent root;
                    root = loader.load();
                    GUI.CartController sp=loader.getController();
                    cont.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
            liste.setOnKeyPressed( new EventHandler<KeyEvent>()
{
  @Override
  public void handle( final KeyEvent keyEvent )
  {

    if ( liste.getSelectionModel().getSelectedItem() != null )
    {
      if ( keyEvent.getCode().equals( KeyCode.DELETE ) )
      {
        //Delete or whatever you like:
        //presenter.onEntityDeleteAction( selectedItem );
          panier.removeLine(c);
          Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Supprimer");
        alert1.show();
      }

       //... other keyevents
    }
  }
} );
            hb.getChildren().add(im);
            hb.getChildren().add(lb1);
            hb.getChildren().add(lb2);
            hb.getChildren().add(lb3);
            hb.getChildren().add(t);
            hb.getChildren().add(bt3);
            l1.add(hb);
        }
        
        liste.setItems(l1);
        liste.setPrefHeight(600);
        liste.setPrefWidth(750);
        liste.setStyle("-fx-control-inner-background: rgba(255,255,255,0.4)");
        
                       cont.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')"+"-fx-background-size 1400 768");

        
    } 

    /*private void retour(ActionEvent event) throws IOException {
        
         FXMLLoader loader=new FXMLLoader(getClass().getResource("ShowListProduct.fxml"));
                Parent root;
                root = loader.load();
                ProduitController sp=loader.getController();
                bt1.getScene().setRoot(root);
    }*/
    
    
    @FXML
    private void validerPanier(ActionEvent event) throws IOException, SQLException {
        int a = Integer.parseInt(lb4.getText());
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Payement.fxml"));
        Parent root=loader.load();
        PayementController pc=loader.getController();
        
        Panier panier = Panier.getInstance();
        
        for(Command_Line c:panier.p)
        {
            ProduitService ps= new ProduitService();
                Produit pr =ps.searchById(c.getLine_id());
                ps.updatequantite(c.getLine_id(),pr.getQuantite()-c.getQuantity() );
        }
        
        Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            if((a>5000)&&(a<10000)){
                a=a-((a*10)/100);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Pour Votre fidélité Une Réduction de 10%");
        alert1.show();
            pc.setPrix(String.valueOf(a));
            }
            else if((a>10000)&&(a<20000)){
                a=a-((a*15)/100);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Pour Votre fidélité Une Réduction de 15%");
        alert1.show();
            pc.setPrix(String.valueOf(a));
            }
            else if((a>20000)&&(a<30000)){
                a=a-((a*20)/100);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Pour Votre fidélité Une Réduction de 20%");
        alert1.show();
            pc.setPrix(String.valueOf(a));
            }
            else if((a>30000)&&(a<40000)){
                a=a-((a*25)/100);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Pour Votre fidélité Une Réduction de 25%");
        alert1.show();
            pc.setPrix(String.valueOf(a));
            }
            else if((a>40000)&&(a<100000)){
                a=a-((a*30)/100);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Pour Votre fidélité Une Réduction de 30%");
        alert1.show();
            pc.setPrix(String.valueOf(a));
            }
            else if(a>100000){
                a=a-((a*50)/100);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Pour Votre fidélité Une Réduction de 50%");
        alert1.show();
            pc.setPrix(String.valueOf(a));
            }
            else{
                pc.setPrix(String.valueOf(a));
            }
            st.show();
            
           
    }
    
       
    
}
