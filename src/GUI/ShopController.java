/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Panier;
import Entities.Produit;
import Entities.Session;
import Services.BoutiqueService;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ComboBox;
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
import javafx.scene.input.ScrollEvent;
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
    //private ObservableList<String> listrace = new ObservableList<String>();
    
    //ObservableList<Integer> ligne=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Session session = Session.getInstance();
            // TODO
            VBox vbox = new VBox();
            vbox.setPadding(new javafx.geometry.Insets(10));
            vbox.setSpacing(8);
            TilePane tilePane = new TilePane();
            tilePane.setHgap(80);
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
                label2.setFont(new Font("Cambria", 18));
                Label label3 = new Label();
                label3.setText(res.getString(3));
                label3.setVisible(false);
                Label label4 = new Label();
                Label label7 = new Label();
                label7.setText("Race : " +res.getString(5));
                label7.setFont(new Font("Cambria", 18));
                Label label5 = new Label();
                label5.setText(res.getString(6));
                label5.setVisible(false);
                Label label6 = new Label();
                if(label5.getText().equals("0")){
                    label6.setText("EPUISE");
                    label6.setFont(new Font("Cambria", 15));
                    label6.setAlignment(Pos.CENTER);

                }
                Rating rating = new Rating();
               ResultSet res6 = ps.displayRate1(Integer.parseInt(label3.getText()));
                //System.out.println(res6);
                while(res6.next()){
                    //label4.setText(res6.getString(1)+"/5");
                    rating.setRating(res6.getInt(1));
                }
                label7.setTranslateY(-20);
                rating.setTranslateY(-30);
                label6.setTranslateX(140);
                label6.setTranslateY(80);
                
                
                
                //rating.setUpdateOnHover(true);
                rating.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            ps.rate(Integer.parseInt(label3.getText()), (double) newValue,session.IdSession());
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
                vbox1.setSpacing(0);
                //vbox1.setTranslateX(0);
                                vbox1.setTranslateX(50);

                vbox1.getChildren().addAll(label1,label2,label3,label6,label7,label4,rating);
                
                vbox1.setStyle("-fx-border-width: 2;" +"-fx-background-color: rgba(204,229,255,0.7)");
                tilePane.getChildren().add(vbox1);
                tilePane.setStyle("-fx-background-color: rgba(109,207,246,1)");
                

                //list.getItems().set
                label1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("produit.fxml"));
                        Parent root5 = loader.load();
                        ProduitController spc = loader.getController();
                        
                        spc.afficheP(Integer.parseInt(label3.getText()));
                        
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
                Label lb5= new Label("Bienvenue dans notre Store");
                                 lb5.setStyle("-fx-text-fill:orange");

                lb5.setFont(new Font("Cambria", 32));
                Label lb6 = new Label();
                Image image6 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/cart.png",50,50,true,true);
        lb6.setGraphic(new ImageView(image6));
                //lb5.setStyle("-fx-background-colo");
                lb6.setTranslateX(300);
                lb6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                        Parent root5 = loader.load();
                        CartController spc = loader.getController();
                                                
                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });
                Label lb7 = new Label();
                Image image7 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/signout.png",50,50,true,true);
       lb7.setGraphic(new ImageView(image7));
                //lb5.setStyle("-fx-background-colo");
                lb7.setTranslateX(305);
                lb7.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        session.closeSession();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                        Parent root5 = loader.load();
                        AcceuilController spc = loader.getController();
                                                
                        label2.getScene().setRoot(root5);
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });

                //lb5.setStyle("-fx-background-colo");
                hbox1.getChildren().addAll(lb5,lb6,lb7);
                hbox1.setTranslateX(450);
                root1.setTop(hbox1);
                
                
                
                
                root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Horizontal scroll bar
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
                //JFXComboBox combo = new JFXComboBox();
                chercher.setPrefSize(100, 20);
                valider.setTranslateY(15);
                 valider.setStyle(
                
                "-fx-min-width: 20px; " +
                "-fx-min-height: 20px; " +
                "-fx-max-width: 20px; " +
                "-fx-max-height: 20px;"
        );
        Image image2 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/search.png",20,20,true,true);
        valider.setGraphic(new ImageView(image2));
        valider.setText("");
                hbox.getChildren().addAll(chercher,valider);
                hbox.setTranslateY(50);
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
                JFXComboBox raceComboBox = new JFXComboBox();
                raceComboBox.setTranslateY(150);
                raceComboBox.setTranslateX(10);
                raceComboBox.setValue("Filtrer par Race");
    raceComboBox.getItems().addAll("chien","chat");
   // raceComboBox.setPromptText("Filtrer Par race");
    raceComboBox.setEditable(true);        
    raceComboBox.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
           try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));
                        Parent root5 = loader.load();
                        ShopController spc = loader.getController();
                        
                        spc.ProduitRace(t1);
                        
                        //hb.getChildren().addAll(list,btn);
                        
                        label3.getScene().setRoot(root5);
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
        }    
    });
      VBox left = new VBox();
      left.getChildren().addAll(hbox,raceComboBox);
    root1.setLeft(left);
                
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
                        
           Label right= new Label();
           right.setText("hhhhhhhhhhhhhhhhhhhhhhhhh");
           right.setVisible(false);
           root1.setRight(right);
        
           //root1.getCenter().setStyle("-fx-background-color: rgba(109,207,246,1)");
           root1.getCenter().setTranslateX(50);
           root1.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet3.jpg')"+"-fx-background-size 1400 768");
           shoppage.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet3.jpg')"+"-fx-background-size 1400 768");

    }
    
    public void ProduitRace(String race){
           try {
            Session session = Session.getInstance();
            // TODO
            VBox vbox = new VBox();
            vbox.setPadding(new javafx.geometry.Insets(10));
            vbox.setSpacing(8);
            TilePane tilePane = new TilePane();
            tilePane.setHgap(100);
            tilePane.setVgap(50);
            tilePane.setPadding(new javafx.geometry.Insets(50, 5, 5, 5));
            ProduitService ps = new ProduitService();
            ResultSet res = ps.shopRace(race);
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
                label2.setFont(new Font("Cambria", 18));
                Label label3 = new Label();
                label3.setText(res.getString(3));
                label3.setVisible(false);
                Label label4 = new Label();
                Label label7 = new Label();
                //label7.setText("race : " +res.getString(5));
                Label label5 = new Label();
                //label5.setText(res.getString(6));
                label5.setVisible(false);
                Label label6 = new Label();
                if(label5.getText().equals("0")){
                    label6.setText("EPUISE");
                    label6.setFont(new Font("Cambria", 18));
                    label6.setAlignment(Pos.CENTER);
                    label6.setVisible(true);
                }
                Rating rating = new Rating();
               ResultSet res6 = ps.displayRate1(Integer.parseInt(label3.getText()));
                //System.out.println(res6);
                while(res6.next()){
                    //label4.setText(res6.getString(1)+"/5");
                    rating.setRating(res6.getInt(1));
                }
                
                rating.setTranslateY(-50);
                
                
                //rating.setUpdateOnHover(true);
                rating.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            ps.rate(Integer.parseInt(label3.getText()), (double) newValue,session.IdSession());
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
                vbox1.setSpacing(5);
                vbox1.getChildren().addAll(label1,label2,label3,label6,label7,label4,rating);
                vbox1.setTranslateX(30);

                vbox1.setStyle("-fx-border-width: 2;" +"-fx-background-color: rgba(255,255,255,0.9)");

                tilePane.getChildren().add(vbox1);
                tilePane.setStyle("-fx-background-color: rgba(109,207,246,1)");
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
                Label lb5= new Label("Bienvenue dans notre Store");
                lb5.setFont(new Font("Cambria", 32));
                                 lb5.setStyle("-fx-text-fill:orange");
                Label lb6 = new Label();
                Image image6 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/cart.png",50,50,true,true);
        lb6.setGraphic(new ImageView(image6));
                //lb5.setStyle("-fx-background-colo");
                lb6.setTranslateX(300);
                lb6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                        Parent root5 = loader.load();
                        CartController spc = loader.getController();
                                                
                        Scene sc = new Scene(root5);
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });
                Label lb7 = new Label();
                Image image7 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/signout.png",50,50,true,true);
                  lb7.setGraphic(new ImageView(image7));
                //lb5.setStyle("-fx-background-colo");
                lb7.setTranslateX(305);
                lb7.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        session.closeSession();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                        Parent root5 = loader.load();
                        AcceuilController spc = loader.getController();
                                                
                        label2.getScene().setRoot(root5);
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });
                lb5.setTranslateX(400);
                Hyperlink All = new Hyperlink();
                All.setText("Shop");
                All.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));
                        Parent root5 = loader.load();
                        ShopController spc = loader.getController();
                        label1.getScene().setRoot(root5);
                        
                    }
                    catch (IOException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    event.consume();
                });
                
                hbox1.getChildren().addAll(All,lb5,lb6,lb7);
                //hbox1.setTranslateX();
                root1.setTop(hbox1);
                
                
                
                
                root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Horizontal scroll bar
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
                valider.setTranslateY(15);
                 valider.setStyle(
                
                "-fx-min-width: 20px; " +
                "-fx-min-height: 20px; " +
                "-fx-max-width: 20px; " +
                "-fx-max-height: 20px;"
        );
        Image image2 = new Image("file:///C:/Users/Public/Pictures/Sample%20Pictures/search.png",20,20,true,true);
        valider.setGraphic(new ImageView(image2));
        valider.setText("");
                hbox.getChildren().addAll(chercher,valider);
                hbox.setTranslateY(50);
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
                      root1.getCenter().setStyle("-fx-background-color: rgba(109,207,246,0.4)");


    }
    
    }

    
    
    

              
                   
    

