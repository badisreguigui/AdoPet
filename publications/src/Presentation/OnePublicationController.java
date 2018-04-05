/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Commentaire;
import Entities.Publication;
import Entities.Session;
import Services.ServiceCommentaire;
import Services.ServicePublication;
import Services.ServiceUser;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class OnePublicationController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label photo;
    @FXML
    private Label supprimer;
    @FXML
    private Label modifier;
    @FXML
    private HBox post;
    @FXML
    private HBox ajoutcom;
    @FXML
    private ListView<VBox> lista;
    Session s = Session.getInstance();
    @FXML
    private HBox visible;
    @FXML
    private VBox signalbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loadPublication(int id) throws SQLException {
        Button b = new Button("Signaler");
        signalbox.getChildren().add(b);
        b.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                try {
                    int a;
                    ServicePublication sp = new ServicePublication();
                    a = sp.signal(id, s.IdSession());
                    if (a == 0) {

                        Alert alert = new Alert(AlertType.CONFIRMATION);

                        alert.setHeaderText("vous avez deja signalé cette publication");
                        alert.show();
                    } else {

                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setHeaderText("vous avez signalé cette publication");
                        alert.show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        post.setPadding(new javafx.geometry.Insets(10));
        post.setSpacing(10);
        post.setStyle("-fx-border-style: solid;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
        ServicePublication sp = new ServicePublication();
        Publication pub = sp.afficherPublication(id);
        Label idp = new Label();
        idp.setText(Integer.toString(pub.getIdpub()));
        idp.setVisible(false);
        Image img = new Image("file:///" + pub.getImage());
        ImageView image = new ImageView(img);
        image.setFitHeight(200);
        image.setFitWidth(200);
        photo.setGraphic(image);
        type.setText(pub.getType());
        description.setText(pub.getDescription());
        date.setText(pub.getDate());
        time.setText(pub.getTime());
        if (pub.getIduser() != s.IdSession()) {
            visible.setVisible(false);
        }

        Image img1 = new Image("file:///C:\\Users\\LENOVO\\Desktop\\x\\delete.png");
        Image img2 = new Image("file:///C:\\Users\\LENOVO\\Desktop\\x\\update.png");
        ImageView image1 = new ImageView(img1);
        ImageView image2 = new ImageView(img2);
        supprimer.setGraphic(image1);
        modifier.setGraphic(image2);
        image1.setFitHeight(30);
        image1.setFitWidth(30);
        image2.setFitHeight(30);
        image2.setFitWidth(30);
        supprimer.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            try {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Suppression");
                alert.setHeaderText("Vous voulez supprimer votre publication");
                alert.setContentText("vous etes sur(e) ?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    sp.SupprimerPublication(pub);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagefront.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    AffichagefrontController afc = loader.getController();
                    description.getScene().setRoot(root);
                } else {
                    alert.hide();
                }

            } catch (SQLException ex) {
                Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        modifier.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifPub.fxml"));
            Parent root2 = null;
            try {
                root2 = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ModifPubController controller = loader.getController();
            try {
                controller.modifPublication(Integer.parseInt(idp.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            time.getScene().setRoot(root2);

        });

        ObservableList<VBox> l = FXCollections.observableArrayList();
        l.add(createpage(id));
        lista.setItems(l);

    }

    public VBox createpage(int id) throws SQLException {
        VBox tilePane = new VBox();
        tilePane.setPadding(new javafx.geometry.Insets(10, 5, 5, 5));
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setSpacing(10);
        ServiceCommentaire sc = new ServiceCommentaire();
        ResultSet res = sc.affichage(id);
        while (res.next()) {

            Label idcom = new Label();
            idcom.setText(Integer.toString(res.getInt(1)));
            idcom.setVisible(false);
            Label sup = new Label();
            Label mod = new Label();

            Image img1 = new Image("file:///C:\\Users\\LENOVO\\Desktop\\x\\delete.png");
            Image img2 = new Image("file:///C:\\Users\\LENOVO\\Desktop\\x\\update.png");
            ImageView image1 = new ImageView(img1);
            ImageView image2 = new ImageView(img2);
            image1.setFitHeight(20);
            image1.setFitWidth(20);
            image2.setFitHeight(20);
            image2.setFitWidth(20);
            sup.setGraphic(image1);
            mod.setGraphic(image2);
            sup.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    ServiceCommentaire sc = new ServiceCommentaire();
                    try {
                        sc.SupprimerCommentaire(Integer.parseInt(idcom.getText()));
                        loadPublication(id);

                    } catch (SQLException ex) {
                        Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            mod.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    
                }
            });
            mod.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCom.fxml"));
                    Parent root5 = null;
                    try {
                        root5 = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichagefrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ModifComController controller = loader.getController();
                    
                        controller.loadCommentaire(id, Integer.parseInt(idcom.getText()));
                  
                     
                      
                   
                    ajoutcom.getScene().setRoot(root5);
                    
                }
            });
            HBox tools = new HBox();
            if (res.getInt(3) != s.IdSession()) {
                tools.setVisible(false);
            }
            tools.getChildren().addAll(sup, mod);

            ServiceUser su = new ServiceUser();
            Entities.User u = su.selectuser(res.getInt(3));

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

            HBox bar = new HBox();
            bar.setSpacing(400);
            bar.getChildren().addAll(userr, tools);

            Label des = new Label();
            des.setText(res.getString(2));
            des.setFont(Font.font(12));
            VBox post = new VBox();
            post.setPadding(new javafx.geometry.Insets(10));
            post.setSpacing(10);
            post.setStyle("-fx-border-style: solid;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
            post.getChildren().addAll(bar, des, idcom);
            tilePane.getChildren().add(post);
        }

        return tilePane;

    }

    void ajoutercommentaire(int id) {
        Button ajout = new Button("Ajouter un commentaire");
        TextField descrip = new TextField();
        descrip.setPrefWidth(453);
        ajoutcom.getChildren().addAll(descrip, ajout);
        ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServiceCommentaire sc = new ServiceCommentaire();
                Commentaire com = new Commentaire(descrip.getText(), s.IdSession(), id);
                try {
                    sc.AjouterCommentaire(com);
                } catch (SQLException ex) {
                    Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    loadPublication(id);
                    descrip.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(OnePublicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichagefront.fxml"));
        Parent root = loader.load();
        AffichagefrontController afc = loader.getController();
        description.getScene().setRoot(root);
    }

}
