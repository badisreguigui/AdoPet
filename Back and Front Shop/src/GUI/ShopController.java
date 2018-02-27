/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Kapio
 */

        
public class ShopController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane shoppage;
    @FXML
    private BorderPane root1;
    private ListView list;
    //ObservableList<Integer> ligne=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            VBox vbox = new VBox();
            vbox.setPadding(new javafx.geometry.Insets(10));
            vbox.setSpacing(8);
            TilePane tilePane = new TilePane();
            tilePane.setHgap(50);
            tilePane.setVgap(50);
            tilePane.setPadding(new javafx.geometry.Insets(50, 5, 5, 5));
            ProduitService ps = new ProduitService();
            ResultSet res = ps.shop();
            while(res.next()){
                Image image = new Image("file:///"+res.getString(2));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                Label label1 = new Label();
                label1.setGraphic(imageView);
                Label label2 = new Label();
                label2.setText(res.getString(1));
                label2.setMaxWidth(Double.MAX_VALUE);
                label2.setAlignment(Pos.CENTER);
                label2.setTranslateY(10);
                Label label3 = new Label();
                label3.setText(res.getString(3));
                label3.setVisible(false);
                Label label4 = new Label();
                label4.setText("race : " +res.getString(5));
                
               ResultSet res6 = ps.displayRate1(Integer.parseInt(label3.getText()));
                //System.out.println(res6);
                while(res6.next()){
                    label4.setText(res6.getString(1)+"/5");
                }
                
                Rating rating = new Rating();
                //rating.setUpdateOnHover(true);
                rating.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            ps.rate(Integer.parseInt(label3.getText()), (double) newValue);
                            ResultSet res5 = ps.displayRate(Integer.parseInt(label3.getText()));
                //System.out.println(res5);
                while(res5.next()){
                    label4.setText(res5.getString(1)+"/5");
                }
                        } catch (SQLException ex) {
                            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
               
                
                VBox vbox1 = new VBox();
                vbox1.setPadding(new javafx.geometry.Insets(10));
                vbox1.setSpacing(8);
                vbox1.getChildren().addAll(label1,label2,label3,label4,rating);
                
                tilePane.getChildren().add(vbox1);
                ObservableList<String> ligne=FXCollections.observableArrayList();
                list = new ListView();
                Button btn = new Button("Passe Commande");
                list.getItems().add(btn);
                                        HBox hb = new HBox();

                //list.getItems().set
                label1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("produit.fxml"));
                        Parent root5 = loader.load();
                        ProduitController spc = loader.getController();
                        
                        spc.afficheP(Integer.parseInt(label3.getText()));
                        
                        ligne.setAll(label2.getText());
                        
                        
                        list.getItems().addAll(ligne);
                        
                        //hb.getChildren().addAll(list,btn);
                        
                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });
                list.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("produit.fxml"));
                        Parent root5 = loader.load();
                        ProduitController spc = loader.getController();
                        
                        spc.affichePN(list.getSelectionModel().getSelectedItem().toString());
                        
                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });
                
                list.setOnKeyPressed( new EventHandler<KeyEvent>()
{
  @Override
  public void handle( final KeyEvent keyEvent )
  {

    if ( list.getSelectionModel().getSelectedItem() != null )
    {
      if ( keyEvent.getCode().equals( KeyCode.DELETE ) )
      {
        //Delete or whatever you like:
        //presenter.onEntityDeleteAction( selectedItem );
          list.getItems().remove(list.getSelectionModel().getSelectedItem());
      }

       //... other keyevents
    }
  }
} );
                
                //root1.setRight(list);
                
                
                // TODOtry {
                ScrollPane root = new ScrollPane();
                //BorderPane root1 = new BorderPane();
                root.setPadding(new javafx.geometry.Insets(10));
                
                /*VBox vboxtab = new VBox();
                vboxtab.setPadding(new javafx.geometry.Insets(10));
                vboxtab.setSpacing(8);
                Text title = new Text("Catégories");
                vboxtab.getChildren().add(title);
                title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                ResultSet res1 = ps.listcateg();
                while(res1.next()){
                    Label l1 = new Label();
                    Hyperlink a= new Hyperlink(res1.getString(1), l1);
                    
                    vboxtab.setPrefHeight(100);
                    vboxtab.setPrefWidth(100);
                    vboxtab.getChildren().add(a);
                }
                
                Text race = new Text("Race");
                Hyperlink chat = new Hyperlink("chat");
                Hyperlink chien = new Hyperlink("chien");
                vboxtab.getChildren().addAll(race,chat,chien);
                race.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                
                vboxtab.setPrefHeight(120);
                vboxtab.setPrefWidth(120);
                vboxtab.setTranslateX(10);
                vboxtab.setTranslateY(50);*/
                HBox hbox1 = new HBox();
                hbox1.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
                hbox1.setSpacing(10);
                Label lb5= new Label("Welcome to our Store");
                hbox1.getChildren().add(lb5);
                hbox1.setTranslateX(500);
                root1.setTop(hbox1);
                
                
                
                
                root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Horizontal scroll bar
                root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
                root.setFitToHeight(true);
                root.setFitToWidth(true);
                
                root.setContent(tilePane);
                root1.setCenter(root);
                //root1.setLeft(vboxtab);
                
                
                HBox hbox = new HBox();
                hbox.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
                hbox.setSpacing(10);
                JFXTextField chercher = new JFXTextField();
                chercher.setPrefSize(100, 20);
                JFXButton valider= new JFXButton("Chercher");
                hbox.getChildren().addAll(chercher,valider);
                valider.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                   try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("produit.fxml"));
                        Parent root5 = loader.load();
                        ProduitController spc = loader.getController();
                        
                        spc.affichePN(chercher.getText());
                        
                        //hb.getChildren().addAll(list,btn);
                        
                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                });
                root1.setLeft(hbox);
                
            }
            
            
            
            //String css = NewFXMain1.class.getResource("test.css").toString();
            /*Scene sc = new Scene(root1, 950, 800);
            //sc.getStylesheets().add(css);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();*/
            //shoppage.getScene().setRoot(root1);
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        

    }
    
}
              
                   
    

