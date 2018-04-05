/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import com.jfoenix.controls.JFXButton;
import entities.events;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;
import services.ServiceEvents;

/**
 * FXML Controller class
 *
 * @author achref kh
 */
public class Events_DisplayAllEventsController implements Initializable {

    @FXML
    private TableView<events> Display;
    @FXML
    private TableColumn<events, Number> ID;
    @FXML
    private TableColumn<events, String> nom;
    @FXML
    private TableColumn<events, String> type;
    @FXML
    private TableColumn<events, String> lieu;
    @FXML
    private TableColumn<events, Date> datedebut;
    @FXML
    private TableColumn<events, Date> datefin;
    @FXML
    private TableColumn<events, Number> nombredeparticipants;
    @FXML
    private TextArea description;
    @FXML
    private Label descriptionevent;
    @FXML
    private Button participer;

    @FXML
    private ChoiceBox<String> recherche;
    @FXML
    private TextField recherchee;
    @FXML
    private ChoiceBox<String> rechercheetype;
    @FXML
    private AnchorPane DisplayAllEvents;
    
    Session s = Session.getInstance();
    @FXML
    private JFXButton goHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goHome.setStyle(
                
                "-fx-min-width: 70px; " +
                "-fx-min-height: 70px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        File file4 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\home.png");
        //Image image = new Image(file.toURI().toString());
        String pt4 = file4.toURI().toString();
        Image image4 = new Image(pt4, 70 ,70, false, false);
        goHome.setGraphic(new ImageView(image4));
        goHome.setText("");
        
        // TODO
        System.out.println(s.loginSession());
        DisplayAllEvents.setStyle("-fx-background-image: url('/images/adopet1.jpg');");
        recherchee.setVisible(false);
        rechercheetype.setVisible(false);
        ArrayList<String> types = new ArrayList();
        types.add("meeting coffeshop");
        types.add("concours de beauté");
        types.add("dressage");
        types.add("Information");
        ObservableList<String> listt = FXCollections.observableArrayList(types);
        rechercheetype.setItems(listt);
        description.setVisible(false);
        descriptionevent.setVisible(false);
        participer.setVisible(false);
        Display.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                description.setVisible(true);
                descriptionevent.setVisible(true);
                participer.setVisible(true);
                description.setText(Display.getSelectionModel().getSelectedItem().getDescription());
            }
        });

        ArrayList<String> typesrecherche = new ArrayList();
        typesrecherche.add("");
        typesrecherche.add("Type");
        typesrecherche.add("Nom");
        ObservableList<String> list = FXCollections.observableArrayList(typesrecherche);
        recherche.setItems(list);
        ServiceEvents se = new ServiceEvents();
        recherche.valueProperty().addListener((e) -> {
            if ("".equals(recherche.getValue())) {
                recherchee.setVisible(false);
                rechercheetype.setVisible(false);
                ObservableList ok = FXCollections.observableArrayList();
                        try {
                            ok = se.DisplayEventsByName(s.loginSession(), recherchee.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(Events_DisplayAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Display.setItems(null);
                        Display.setItems(ok);
                        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
                        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
                        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
                        nombredeparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrParticipants"));
            } else {
                recherchee.setVisible(true);
                rechercheetype.setVisible(false);
                
                if ("Nom".equals(recherche.getValue())) {
                    recherchee.textProperty().addListener((d) -> {
                        ObservableList ok = FXCollections.observableArrayList();
                        try {
                            ok = se.DisplayEventsByName(s.loginSession(), recherchee.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(Events_DisplayAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Display.setItems(null);
                        Display.setItems(ok);
                        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
                        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
                        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
                        nombredeparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrParticipants"));
                    });
                }
                if ("Type".equals(recherche.getValue())) {
                    recherchee.setVisible(false);
                    rechercheetype.setVisible(true);
                    rechercheetype.valueProperty().addListener((d) -> {
                        ObservableList ok = FXCollections.observableArrayList();
                        try {
                            ok = se.DisplayEventsByType(s.loginSession(), rechercheetype.getValue());
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Events_DisplayAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Display.setItems(null);
                        Display.setItems(ok);
                        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
                        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
                        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
                        nombredeparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrParticipants"));
                    });
                }
            }
        });
    }

    @FXML
    private void AddEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Events_AddEvent.fxml"));
        Parent root = loader.load();
        Stage popUpStage = new Stage();
        Scene popUpScene = new Scene(root);
        popUpStage.setScene(popUpScene);
//        popUpStage.setOnShown(ev -> {
//            popUpStage.show();
//        });
        Stage stage = (Stage) Display.getScene().getWindow();
        popUpStage.initStyle(StageStyle.UNDECORATED);
        popUpStage.initOwner(stage);
        popUpStage.initModality(Modality.WINDOW_MODAL);
        popUpStage.showAndWait();
    }

    @FXML
    private void DisplayMyEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Events_DisplayMyEvents.fxml"));
        Parent root = loader.load();
        Display.getScene().setRoot(root);

    }

    @FXML
    private void DisplayMyParticipations(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Events_DisplayMyParticipations.fxml"));
        Parent root = loader.load();
        Stage popUpStage = new Stage();
        Scene popUpScene = new Scene(root);
        popUpStage.setScene(popUpScene);
//        popUpStage.setOnShown(ev -> {
//            popUpStage.show();
//        });
        Stage stage = (Stage) Display.getScene().getWindow();
        popUpStage.initStyle(StageStyle.UNDECORATED);
        popUpStage.initOwner(stage);
        popUpStage.initModality(Modality.WINDOW_MODAL);
        popUpStage.showAndWait();
        
    }

    @FXML
    private void load(ActionEvent event) {

        ServiceEvents sp = new ServiceEvents();
        ObservableList ok = FXCollections.observableArrayList();
        try {
            ok = sp.DisplayEvents(s.loginSession());
            
        } catch (SQLException ex) {
            Logger.getLogger(Events_DisplayMyEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Display.setItems(null);
        Display.setItems(ok);
        
        
        
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        nombredeparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrParticipants"));

    }

    @FXML
    private void participer(ActionEvent event) throws SQLException {

        ServiceEvents sp = new ServiceEvents();
        Stage stage = (Stage) Display.getScene().getWindow();
        String title = stage.getTitle();
        if (sp.participerEvent(Display.getSelectionModel().getSelectedItem().getId(),s.IdSession())) {
            Notifications notificationBuilder = Notifications.create()
                    .title("          Succes")
                    .text("Participation enregistrée")
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.showConfirm();

            sp.PreferenceEvent(s.IdSession(),sp.selectlieu(Display.getSelectionModel().getSelectedItem().getId()),sp.selecttype(Display.getSelectionModel().getSelectedItem().getId()));
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Vous etes deja dans cet event", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                        Parent root5 = loader.load();
                        AcceuilController spc = loader.getController();
                                                
                        descriptionevent.getScene().setRoot(root5);
    }

}
