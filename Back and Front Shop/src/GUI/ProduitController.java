/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Command_Line;
import Entities.Order;
import Entities.Panier;
import Entities.Produit;
import Entities.Produit.race;
import Entities.User;
import Services.Command_LineServices;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class ProduitController implements Initializable {

    @FXML
    private ImageView imgp;
    @FXML
    private Label nomp;
    @FXML
    private Label prixp;
    @FXML
    private Label quantitep;
    @FXML
    private Label categp;
    @FXML
    private Label descp;
    @FXML
    private Label racep;
    @FXML
    private Label boutiquep;
    @FXML
    private Button addtocart;
    
     Panier panier=Panier.getInstance();
    @FXML
    private Label label4;
    @FXML
    private ImageView imgrate;
    @FXML
    //private Rating rating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }  
    
    public void afficheP(int id) throws SQLException{
        ProduitService ps = new ProduitService();
        ResultSet res= ps.unProduit(id);
            
        while(res.next()){
            nomp.setText(res.getString("nomproduit"));
            descp.setText(res.getString("description"));
            prixp.setText(res.getString("prix"));
            quantitep.setText(res.getString("quantite"));
            categp.setText(res.getString("nomcategorie"));
            racep.setText(res.getString("nomraceproduit"));
            boutiquep.setText(res.getString("nomboutiqueproduit"));
            Image img = new Image("file:///"+res.getString("imageproduit"));
           // System.out.println("file:///"+res.getString("imageproduit"));
            imgp.setImage(img);
            
        }
        //System.out.println(id);
        ResultSet res6 = ps.displayRate1(id);
                //System.out.println(res6);
                while(res6.next()){
                    label4.setText(res6.getString(1)+"/5");
                    //System.out.println(label4);
                }
        /*rating.setPartialRating(true);
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        
                        try {
                            ps.rate(id, (double) newValue);
                        } catch (SQLException ex) {
                            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            ResultSet res5 = ps.displayRate(id);
                            while(res5.next()){
                                label4.setText(res5.getString(1)+"/5");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
                    });*/
    }
        
    public void affichePN(String nom) throws SQLException{
        ProduitService ps = new ProduitService();
        ResultSet res= ps.SelectbyNom(nom);
            
        while(res.next()){
            nomp.setText(res.getString("nomproduit"));
            descp.setText(res.getString("description"));
            prixp.setText(res.getString("prix"));
            quantitep.setText(res.getString("quantite"));
            categp.setText(res.getString("nomcategorie"));
            racep.setText(res.getString("nomraceproduit"));
            boutiquep.setText(res.getString("nomboutiqueproduit"));
            Image img = new Image("file:///"+res.getString("imageproduit"));
           // System.out.println("file:///"+res.getString("imageproduit"));
            imgp.setImage(img);
        }
         
    }

    @FXML
    private void fullCart(ActionEvent event) throws IOException, SQLException {
       
         User u=new User();
        Order o=new Order();
        
        
        ProduitService ps=new ProduitService();
        Produit p=ps.SelectbyName(nomp.getText());
        
        Command_Line c=new Command_Line(p.getIdproduit(),1,o,p);
        //System.out.println(c);
       
        panier.addLine(c);
        //panier.removeLine(1);
        //System.out.println(panier);
        panier.show();
        //System.out.println(panier.toString());
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Added to Cart ");
        alert1.show();
        
         FXMLLoader loader=new FXMLLoader(getClass().getResource("Cart.fxml"));
        Parent root=loader.load();
        CartController cc=loader.getController();
        
        nomp.getScene().setRoot(root);
        
        
    }

}

        
        //System.out.println(ll);
        
        
    
    

