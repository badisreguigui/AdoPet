/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Publication;
import Entities.Session;
import Services.ServicePublication;
import Services.UserService;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import java.awt.Color;
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
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TO

        VBox tilePane = new VBox();
        tilePane.setPadding(new javafx.geometry.Insets(10, 5, 5, 5));
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setSpacing(10);

        ServicePublication ps = new ServicePublication();
        ResultSet res = null;
        try {
            res = ps.affichage();
        } catch (SQLException ex) {
            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Label title = new Label();

        ComboBox search = new ComboBox();
        search.setValue("Toutes");
        search.getItems().addAll("Nourriture", "Dressage", "Soins", "Autres");
        
        search.setEditable(true);
        search.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagefront.fxml"));
                    Parent root = loader.load();
                    AffichagefrontController afc = loader.getController();
                    System.out.println(newValue);
                    afc.createpage(newValue);
                    title.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        HBox tools = new HBox();
        tools.setSpacing(235);

        Hyperlink ajout = new Hyperlink();
        ajout.setText("Ajouter une publication");
        ajout.setStyle("-fx-text-fill:white");

        
        ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPublication.fxml"));
                Parent root5 = null;
                try {
                    root5 = loader.load();

                } catch (IOException ex) {
                    Logger.getLogger(AjoutPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                }

                root1.getScene().setRoot(root5);

            }
        });
        Hyperlink stat = new Hyperlink();
        stat.setText("Statistiques des publications");
        stat.setStyle("-fx-text-fill:white");
        stat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Stat.fxml"));
                Parent root5 = null;
                try {
                    root5 = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
                }
                StatController controller = loader.getController();
                root1.getScene().setRoot(root5);

            }
        });

        title.setText("Liste des publications");
        title.setFont(Font.font(40));
        
        title.setStyle("-fx-text-fill:orange");
        tools.getChildren().addAll(search, stat, ajout);
        tilePane.getChildren().add(title);

        tilePane.getChildren().add(tools);

        try {
            while (res.next()) {
                Image image = new Image("file:///" + res.getString(4));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
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
                header.setSpacing(450);
                header.getChildren().addAll(type, datetime);

                HBox buttons = new HBox();
                Hyperlink commenter = new Hyperlink("Détails");

                commenter.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePublication.fxml"));
                            Parent root5 = null;
                            try {
                                root5 = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            OnePublicationController controller = loader.getController();
                            controller.loadPublication(Integer.parseInt(id.getText()));
                            controller.createpage(Integer.parseInt(id.getText()));
                            controller.ajoutercommentaire(Integer.parseInt(id.getText()));
                            root1.getScene().setRoot(root5);
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                Label fb = new Label();
                Image img1 = new Image("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\Capture.png");
                ImageView image1 = new ImageView(img1);
                image1.setFitHeight(30);
                image1.setFitWidth(30);
                fb.setGraphic(image1);
                fb.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                System.out.println(res.getInt(5));
                buttons.getChildren().addAll(commenter, fb);
                buttons.setAlignment(Pos.BASELINE_RIGHT);
                UserService su = null;
                try {
                    su = new UserService();
                } catch (SQLException ex) {
                    Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Entities.User u = null;
                try {
                    u = su.selectuser(res.getInt(5));
                } catch (SQLException ex) {
                    Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Label name = new Label();
                System.out.println(u);
                name.setText(u.getNom());
                Label pnom = new Label();
                pnom.setText(" " + u.getPrenom() + " ");
                Label taswira = new Label();
                Image imgt = new Image("file:///" + u.getImage());
                ImageView imaget = new ImageView(imgt);
                imaget.setFitHeight(30);
                imaget.setFitWidth(30);
                taswira.setGraphic(imaget);
                HBox userr = new HBox();
                userr.getChildren().addAll(taswira, pnom, name);

                VBox user = new VBox();
                user.getChildren().addAll(userr, description);
                user.setSpacing(20);

                VBox texte = new VBox();
                texte.getChildren().addAll(header, user);
                texte.setSpacing(30);

                VBox contenu = new VBox();
                contenu.getChildren().addAll(texte, buttons);
                contenu.setSpacing(75);

                HBox post = new HBox();
                post.setPadding(new javafx.geometry.Insets(10));
                post.setSpacing(10);
                post.setStyle("-fx-border-style: solid;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
                post.getChildren().addAll(photo, contenu, id);

                tilePane.getChildren().add(post);
                post.setStyle("-fx-background-color : rgba(241,247,250,0.7)");
                tilePane.setStyle("-fx-background-color : rgba(109,207,246,1)");

            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ScrollPane root = new ScrollPane();
        root.setPadding(new javafx.geometry.Insets(10));
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setContent(tilePane);
        root1.setCenter(root);
        root.setStyle("-fx-background-color : rgba(210,207,207,0.00001)");
        root1.setStyle("-fx-background-color : rgba(210,207,207,0.00001)");
        back.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet3.jpg')");
    }

    public void createpage(String t) {
        // TO

        VBox tilePane = new VBox();
        tilePane.setPadding(new javafx.geometry.Insets(10, 5, 5, 5));
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setSpacing(10);

        ServicePublication ps = new ServicePublication();
        ResultSet res = null;
        try {
            res = ps.affichageDressage(t);
        } catch (SQLException ex) {
            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        HBox tools = new HBox();
        tools.setSpacing(680);

        Hyperlink ajout = new Hyperlink();
        ajout.setText("Ajouter une publication");
        ajout.setStyle("-fx-text-fill:white");

        
        ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPublication.fxml"));
                Parent root5 = null;
                try {
                    root5 = loader.load();

                } catch (IOException ex) {
                    Logger.getLogger(AjoutPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                }

                root1.getScene().setRoot(root5);

            }
        });

        Hyperlink l = new Hyperlink();
        l.setText("Toutes les publications");
        l.setStyle("-fx-text-fill:white");
       
        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagefront.fxml"));
                Parent root5 = null;
                try {
                    root5 = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AffichagefrontController controller = loader.getController();
                root1.getScene().setRoot(root5);

            }
        });
        tools.getChildren().addAll(l, ajout);
       

        tilePane.getChildren().add(tools);

        try {
            while (res.next()) {
                Image image = new Image("file:///" + res.getString(4));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
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
                header.setSpacing(450);
                header.getChildren().addAll(type, datetime);

                HBox buttons = new HBox();
                Hyperlink commenter = new Hyperlink("Détails");

                commenter.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePublication.fxml"));
                            Parent root5 = null;
                            try {
                                root5 = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            OnePublicationController controller = loader.getController();
                            controller.loadPublication(Integer.parseInt(id.getText()));
                            controller.createpage(Integer.parseInt(id.getText()));
                            controller.ajoutercommentaire(Integer.parseInt(id.getText()));
                            root1.getScene().setRoot(root5);
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                Label fb = new Label();
                Image img1 = new Image("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\Capture.png");
                ImageView image1 = new ImageView(img1);
                image1.setFitHeight(30);
                image1.setFitWidth(30);
                fb.setGraphic(image1);
                fb.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                UserService su = null;
                try {
                    su = new UserService();
                } catch (SQLException ex) {
                    Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Entities.User u = null;
                try {
                    u = su.selectuser(res.getInt(5));
                } catch (SQLException ex) {
                    Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Label name = new Label();
                name.setText(u.getNom());
                Label pnom = new Label();
                pnom.setText(" " + u.getPrenom() + " ");
                Label taswira = new Label();
                Image imgt = new Image("file:///" + u.getImage());
                ImageView imaget = new ImageView(imgt);
                imaget.setFitHeight(30);
                imaget.setFitWidth(30);
                taswira.setGraphic(imaget);
                HBox userr = new HBox();
                userr.getChildren().addAll(taswira, pnom, name);

                VBox user = new VBox();
                user.getChildren().addAll(userr, description);
                user.setSpacing(20);

                VBox texte = new VBox();
                texte.getChildren().addAll(header, user);
                texte.setSpacing(30);

                VBox contenu = new VBox();
                contenu.getChildren().addAll(texte, buttons);
                contenu.setSpacing(75);

               HBox post = new HBox();
                post.setPadding(new javafx.geometry.Insets(10));
                post.setSpacing(10);
                post.setStyle("-fx-border-style: solid;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
                post.getChildren().addAll(photo, contenu, id);

                tilePane.getChildren().add(post);
                post.setStyle("-fx-background-color : rgba(241,247,250,0.7)");
                tilePane.setStyle("-fx-background-color : rgba(109,207,246,1)");

            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScrollPane root = new ScrollPane();
        root.setPadding(new javafx.geometry.Insets(10));
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        root.setContent(tilePane);
        root1.setCenter(root);
        root.setStyle("-fx-background-color : rgba(210,207,207,0.00001)");
        root1.setStyle("-fx-background-color : rgba(210,207,207,0.00001)");
        back.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop//adopet2.jpg')");

    }

    public void partagerfb(int id) throws FileNotFoundException {
        ServicePublication sv = new ServicePublication();
        Publication pub = sv.afficherPublication(id);

        String accessToken = "EAACEdEose0cBALtDw0Dx6ZAZBDy83mXqn2miGKge9yNVDBB3YTOr6H4mFoRjIBXInZCgth3MkzIhv3oIQ6Y6SC7RbtbaoTy9t3xJhsp8ZCLq0eQrvfVgJM0hpgHYYjlf3y2qBPl6A3FTOitBtP7j0vlA2qI5qYxEkwlVr7EZAEdZBS2OqwPgOZAJmKtuKEccP62PBVYnELpUAZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        User me = fbClient.fetchObject("me", User.class);
        System.out.println(me.getName());
        System.out.println(me.getLanguages());
        FileInputStream fis = new FileInputStream(new File(pub.getImage()));
        System.out.println(fis.toString());

        FacebookType response = fbClient.publish("me/photos", FacebookType.class,
                BinaryAttachment.with("photo.png", fis), Parameter.with("message", pub.getDescription()));

    }
}
