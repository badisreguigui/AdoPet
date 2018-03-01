/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import entities.events;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.ServiceEvents;

/**
 * FXML Controller class
 *
 * @author achref kh
 */
public class Events_DisplayMyEventsController implements Initializable {

    @FXML
    private TableView<events> affichage;
    @FXML
    private TableColumn<events, String> nom;
    @FXML
    private TableColumn<events, String> description;
    @FXML
    private TableColumn<events, String> type;
    @FXML
    private TableColumn<events, Date> datedebut;
    @FXML
    private TableColumn<events, Date> datefin;
    @FXML
    private TableColumn<events, Number> nombredeparticipants;
    @FXML
    private TableColumn<events, String> status;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<events, Number> ID;
    @FXML
    private TableColumn<events, String> lieu;
    @FXML
    private TextField enom;
    @FXML
    private ChoiceBox<String> etype;
    @FXML
    private ChoiceBox<String> elieu;
    @FXML
    private DatePicker edatedebut;
    @FXML
    private DatePicker edatefin;
    @FXML
    private TextArea edescription;
    @FXML
    private Label lnom;
    @FXML
    private Label ltype;
    @FXML
    private Label llieu;
    @FXML
    private Label ldescription;
    @FXML
    private Label ldatefin;
    @FXML
    private Label ldatedebut;
    @FXML
    private GridPane update;
    @FXML
    private AnchorPane DisplayMyEvents;
    
    Session s = Session.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayMyEvents.setStyle("-fx-background-image: url('/images/adopet1.jpg');");
        ArrayList<String> types = new ArrayList();
        types.add("meeting coffeshop");
        types.add("concours de beauté");
        types.add("dressage");
        types.add("Information");
        ObservableList<String> list = FXCollections.observableArrayList(types);
        etype.setItems(list);
        ArrayList<String> lieux = new ArrayList();
        lieux.add("Nasr");
        lieux.add("Marsa");
        lieux.add("Menzah");
        lieux.add("Ghazella");
        ObservableList<String> listt = FXCollections.observableArrayList(lieux);
        elieu.setItems(listt);
        update.setVisible(false);
        ServiceEvents sp = new ServiceEvents();
        ObservableList ok = FXCollections.observableArrayList();
        try {
            ok = sp.DisplayMyEvents(s.loginSession());
        } catch (SQLException ex) {
            Logger.getLogger(Events_DisplayMyEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        affichage.setItems(null);
        affichage.setItems(ok);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        nombredeparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrParticipants"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        

        affichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if ("Upcoming".equals(affichage.getSelectionModel().getSelectedItem().getStatus())) {
                    update.setVisible(true);
                    enom.setText(affichage.getSelectionModel().getSelectedItem().getNom());
                    edescription.setText(affichage.getSelectionModel().getSelectedItem().getDescription());
                    etype.getSelectionModel().select(affichage.getSelectionModel().getSelectedItem().getType());  
                    edatedebut.setValue(LocalDate.parse(affichage.getSelectionModel().getSelectedItem().getDatedebut().toString()));
                    edatefin.setValue(LocalDate.parse(affichage.getSelectionModel().getSelectedItem().getDatefin().toString()));
                    elieu.getSelectionModel().select(affichage.getSelectionModel().getSelectedItem().getLieu());
                } else {
                    update.setVisible(false);
                    Alert a = new Alert(Alert.AlertType.WARNING, "evenement en cours / passé", ButtonType.OK);
                    a.showAndWait();
                }

            }
        });

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Events_DisplayAllEvents.fxml"));
        Parent root = loader.load();
        affichage.getScene().setRoot(root);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
        ServiceEvents se = new ServiceEvents();
        LocalDate localDate = edatedebut.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = Date.from(instant);
            java.sql.Date dtSql = new java.sql.Date(date.getTime());

            localDate = edatefin.getValue();
            instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            java.sql.Date dtSql1 = new java.sql.Date(date.getTime());
        events e = new events(enom.getText(),etype.getValue(),edescription.getText(),elieu.getValue(),dtSql,dtSql1);
        se.updateEvent(e,affichage.getSelectionModel().getSelectedItem().getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Events_DisplayAllEvents.fxml"));
        Parent root = loader.load();
        affichage.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
        ServiceEvents se = new ServiceEvents();
        se.deleteEvent(affichage.getSelectionModel().getSelectedItem().getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Events_DisplayAllEvents.fxml"));
        Parent root = loader.load();
        affichage.getScene().setRoot(root);
    }

}
