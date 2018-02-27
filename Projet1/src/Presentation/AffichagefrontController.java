/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Publication;
import Entities.Session;
import Services.ServicePublication;
import Services.ServiceUser;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class AffichagefrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane root1;

    
    Session session = Session.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
           try {
            ScrollPane root = new ScrollPane();
            root.setPadding(new javafx.geometry.Insets(10));
            root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
            root.setFitToHeight(true);
            root.setFitToWidth(true);
            root.setContent(createpage());
            root1.setCenter(root);
        } catch (SQLException ex) {
            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VBox createpage() throws SQLException {
        // System.out.println(session.IdSession());
         VBox tilePane = new VBox();
        tilePane.setPadding(new javafx.geometry.Insets(10, 5, 5, 5));
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setSpacing(10);

        ServicePublication ps = new ServicePublication();
        ResultSet res = ps.affichage();
        Label title = new Label();
        
        ComboBox search= new ComboBox();
         search.getItems().addAll("nourriture","dressage","soins");
        search.setEditable(true);
        search.valueProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue observable, String oldValue, String newValue) {
                 try {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagefront.fxml"));
                     Parent root = loader.load();
                     AffichagefrontController afc = loader.getController();
                     System.out.println(newValue);
                     afc.createpage2(newValue);
                     title.getScene().setRoot(root);
                 } catch (IOException ex) {
                     Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         });
        HBox tools=new HBox();
        tools.setSpacing(365);
     

              
        Button ajout= new Button("Ajouter une nouvelle publication");
        ajout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPublication.fxml"));
                    Parent root5 = null;
                    try {
                        root5 = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    root1.getScene().setRoot(root5);

                }
            });
       
             
          
        title.setText("Liste des publications");
        title.setFont(Font.font(32));
        tools.getChildren().addAll(search,ajout);
        tilePane.getChildren().add(title);
   
        tilePane.getChildren().add(tools);

        while (res.next()) {
            Image image = new Image("file:///" + res.getString(4));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            Label photo = new Label();
            photo.setGraphic(imageView);

            Label id = new Label();
            id.setText(Integer.toString(res.getInt(1)));
            id.setVisible(false);

            Label type = new Label();
            type.setText(res.getString(2));
            type.setFont(Font.font(18));

            Label description = new Label();
            description.setText(res.getString(3));
            description.setFont(Font.font(14));

            HBox datetime = new HBox();
            Label date = new Label();
            date.setText(res.getString(6));
            Label separator = new Label();
            separator.setText(" at ");
            Label time = new Label();
            time.setText(res.getString(7));
            datetime.getChildren().addAll(date, separator, time);

            HBox header = new HBox();
            header.setSpacing(200);
            header.getChildren().addAll(type, datetime);

            HBox buttons = new HBox();
            Hyperlink commenter = new Hyperlink("Détails");

            commenter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePublication.fxml"));
                    Parent root5 = null;
                    try {
                        root5 = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    OnePublicationController controller = loader.getController();
                    try {
                        controller.loadPublication(Integer.parseInt(id.getText()));
                        controller.createpage(Integer.parseInt(id.getText()));
                        controller.ajoutercommentaire(Integer.parseInt(id.getText()));
                     
                      
                    } catch (SQLException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    root1.getScene().setRoot(root5);

                }
            });
            Label fb= new Label();
            Image img1 = new Image("file:///C:\\Users\\LENOVO\\Desktop\\x\\Capture.png");
            ImageView image1 = new ImageView(img1);
               image1.setFitHeight(30);
        image1.setFitWidth(30);
            fb.setGraphic(image1);
            fb.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    try {
                        partagerfb(Integer.parseInt(id.getText()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
            });

            Button partager = new Button("Partager");
            partager.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        partagerfb(Integer.parseInt(id.getText()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            buttons.getChildren().addAll(commenter, fb);
            buttons.setAlignment(Pos.BASELINE_RIGHT);
            ServiceUser su= new ServiceUser();
             Entities.User u=su.selectuser(res.getInt(5));
             
            Label name= new Label();
            name.setText(u.getNom());
            Label pnom=new Label();
            pnom.setText(" "+u.getPrenom()+" ");
            Label taswira= new Label();
            Image imgt = new Image("file:///"+u.getImage());
            ImageView imaget = new ImageView(imgt);
               imaget.setFitHeight(30);
        imaget.setFitWidth(30);
            taswira.setGraphic(imaget);
            HBox userr=new HBox();
            userr.getChildren().addAll(taswira,pnom,name);
            
            VBox user =new VBox();
            user.getChildren().addAll(userr,description);
                    
            VBox texte = new VBox();
            texte.getChildren().addAll(header, user);

            VBox contenu = new VBox();
            contenu.getChildren().addAll(texte, buttons);
            contenu.setSpacing(75);

            HBox post = new HBox();
            post.setPadding(new javafx.geometry.Insets(10));
            post.setSpacing(10);
            post.setStyle("-fx-border-style: solid;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
            post.getChildren().addAll(photo, contenu, id);

            tilePane.getChildren().add(post);

        }
        return tilePane;
    }
    public VBox createpage2(String t1) throws SQLException {
      //   System.out.println(session.IdSession());
         VBox tilePane = new VBox();
        tilePane.setPadding(new javafx.geometry.Insets(10, 5, 5, 5));
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setSpacing(10);

        ServicePublication ps = new ServicePublication();
        ResultSet res = ps.affichageDressage(t1);
       
        Label title = new Label();
        
       
        HBox tools=new HBox();
        tools.setSpacing(365);
     

              
        Button ajout= new Button("Ajouter une nouvelle publication");
        ajout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPublication.fxml"));
                    Parent root5 = null;
                    try {
                        root5 = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    root1.getScene().setRoot(root5);

                }
            });
       
             
          
        title.setText("Liste des publications");
        title.setFont(Font.font(32));
        tools.getChildren().addAll(ajout);
        tilePane.getChildren().add(title);
   
        tilePane.getChildren().add(tools);

        while (res.next()) {
            Image image = new Image("file:///" + res.getString(4));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            Label photo = new Label();
            photo.setGraphic(imageView);

            Label id = new Label();
            id.setText(Integer.toString(res.getInt(1)));
            id.setVisible(false);

            Label type = new Label();
            type.setText(res.getString(2));
            type.setFont(Font.font(18));

            Label description = new Label();
            description.setText(res.getString(3));
            description.setFont(Font.font(14));

            HBox datetime = new HBox();
            Label date = new Label();
            date.setText(res.getString(6));
            Label separator = new Label();
            separator.setText(" at ");
            Label time = new Label();
            time.setText(res.getString(7));
            datetime.getChildren().addAll(date, separator, time);

            HBox header = new HBox();
            header.setSpacing(200);
            header.getChildren().addAll(type, datetime);

            HBox buttons = new HBox();
            Hyperlink commenter = new Hyperlink("Détails");

            commenter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePublication.fxml"));
                    Parent root5 = null;
                    try {
                        root5 = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    OnePublicationController controller = loader.getController();
                    try {
                        controller.loadPublication(Integer.parseInt(id.getText()));
                        controller.createpage(Integer.parseInt(id.getText()));
                        controller.ajoutercommentaire(Integer.parseInt(id.getText()));
                     
                      
                    } catch (SQLException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    root1.getScene().setRoot(root5);

                }
            });
            Label fb= new Label();
            Image img1 = new Image("file:///C:\\Users\\LENOVO\\Desktop\\x\\Capture.png");
            ImageView image1 = new ImageView(img1);
               image1.setFitHeight(30);
        image1.setFitWidth(30);
            fb.setGraphic(image1);
            fb.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    try {
                        partagerfb(Integer.parseInt(id.getText()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
            });

            Button partager = new Button("Partager");
            partager.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        partagerfb(Integer.parseInt(id.getText()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            buttons.getChildren().addAll(commenter, fb);
            buttons.setAlignment(Pos.BASELINE_RIGHT);
            ServiceUser su= new ServiceUser();
             Entities.User u=su.selectuser(res.getInt(5));
             
            Label name= new Label();
            name.setText(u.getNom());
            Label pnom=new Label();
            pnom.setText(" "+u.getPrenom()+" ");
            Label taswira= new Label();
            Image imgt = new Image("file:///"+u.getImage());
            ImageView imaget = new ImageView(imgt);
               imaget.setFitHeight(30);
        imaget.setFitWidth(30);
            taswira.setGraphic(imaget);
            HBox userr=new HBox();
            userr.getChildren().addAll(taswira,pnom,name);
            
            VBox user =new VBox();
            user.getChildren().addAll(userr,description);
                    
            VBox texte = new VBox();
            texte.getChildren().addAll(header, user);

            VBox contenu = new VBox();
            contenu.getChildren().addAll(texte, buttons);
            contenu.setSpacing(75);

            HBox post = new HBox();
            post.setPadding(new javafx.geometry.Insets(10));
            post.setSpacing(10);
            post.setStyle("-fx-border-style: solid;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
            post.getChildren().addAll(photo, contenu, id);

            tilePane.getChildren().add(post);

        }
        return tilePane;
    }

    
    public void partagerfb(int id) throws FileNotFoundException
    {
        ServicePublication sv= new ServicePublication();
        Publication pub= sv.afficherPublication(id);
        
        String accessToken = "EAACEdEose0cBAI5jL4lYjDWSBmeZCmJC2W3y4fZArZBzHiZBeZAZAUjDHecUwMMmmsfH5FhXzMhQdq52AsWQQKTnYo98MfgZC2Xe2gSGiYdM9C5C7wH3WlDaPfXiFPTKoTASNnVnsleS95ZBKJZBxUkRIk2V98mLrf2H9mrObQ0SmsUp4mL8gbyTSqWQXS4UzuXnObsLIT3ob9AZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        User me = fbClient.fetchObject("me", User.class);
        System.out.println(me.getName());
        System.out.println(me.getLanguages());
        FileInputStream fis= new FileInputStream(new File(pub.getImage()));
        System.out.println(fis.toString());
        
        FacebookType response = fbClient.publish("me/photos", FacebookType.class,
                BinaryAttachment.with("photo.png",fis),Parameter.with("message", pub.getDescription()));
      
    }
}