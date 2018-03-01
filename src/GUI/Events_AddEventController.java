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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceEvents;

/**
 * FXML Controller class
 *
 * @author achref kh
 */
public class Events_AddEventController implements Initializable {

    @FXML
    private Button ajoutEvent;
    @FXML
    private TextField nomevent;
    @FXML
    private TextArea descriptionevent;
    @FXML
    private ChoiceBox<String> typeevent;
    @FXML
    private DatePicker datedebutevent;
    @FXML
    private DatePicker datefinevent;
    @FXML
    private ChoiceBox<String> lieuevent;
    @FXML
    private Label taille;

    int tailledescription = 0;
    @FXML
    private AnchorPane AddEvent;
    
    Session s = Session.getInstance();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(s.loginSession());
        AddEvent.setStyle("-fx-background-image: url('/images/adopet1.jpg');");
        ArrayList<String> types = new ArrayList();
        types.add("meeting coffeshop");
        types.add("concours de beauté");
        types.add("dressage");
        types.add("Information");
        ObservableList<String> list = FXCollections.observableArrayList(types);
        typeevent.setItems(list);

        ArrayList<String> lieux = new ArrayList();
        lieux.add("Nasr");
        lieux.add("Marsa");
        lieux.add("Menzah");
        lieux.add("Ghazella");
        ObservableList<String> listt = FXCollections.observableArrayList(lieux);
        lieuevent.setItems(listt);
        //typeevent.getSelectionModel().select("meeting coffeshop");

        datedebutevent.valueProperty().addListener((e) -> {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = new java.util.Date();
            if (datedebutevent.getValue().minusDays(2).toString().compareTo(dateFormat.format(date)) < 0) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Entrer une date de début valide", ButtonType.OK);
                a.showAndWait();
            }
        });
        datefinevent.valueProperty().addListener((e) -> {
            if (datedebutevent.getValue() == null) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Entrer une date de début", ButtonType.OK);
                a.showAndWait();
            } else {
                if (datefinevent.getValue().toString().compareTo(datedebutevent.getValue().toString()) < 0) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Entrer une date de fin valide", ButtonType.OK);
                    a.showAndWait();
                }
            }

        });

        taille.setText("0/160");

        // TODO
    }

    @FXML
    private void ajouter_evenement(ActionEvent event) throws IOException, ParseException, SQLException {
        int charCount = 0;
        int charCount1 = 0;
        char temp;
        for (int i = 0; i < nomevent.getText().length(); i++) {
            temp = nomevent.getText().charAt(i);

            if (Character.isLetter(temp)) {
                charCount++;
            }
        }
        for (int i = 0; i < descriptionevent.getText().length(); i++) {
            temp = descriptionevent.getText().charAt(i);

            if (Character.isLetter(temp)) {
                charCount1++;
            }
        }
        if (charCount == 0 || datedebutevent.getValue() == null || lieuevent.getValue() == null || typeevent.getValue() == null || charCount1 == 0 || datefinevent.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs", ButtonType.OK);
            a.showAndWait();
        } else {
            ServiceEvents sv = new ServiceEvents();
            LocalDate localDate = datedebutevent.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = Date.from(instant);
            java.sql.Date dtSql = new java.sql.Date(date.getTime());

            localDate = datefinevent.getValue();
            instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            java.sql.Date dtSql1 = new java.sql.Date(date.getTime());

            Stage stage = (Stage) nomevent.getScene().getWindow();
            events e = new events(nomevent.getText(), descriptionevent.getText(), typeevent.getValue(), dtSql, dtSql1, 0, s.loginSession(), lieuevent.getValue());

            try {
                sv.AddEvent(e);
            } catch (SQLException ex) {
                Logger.getLogger(Events_AddEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            sv.MailEvent(e,s.IdSession());
            stage.close();
        }
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) nomevent.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void nbrchar(KeyEvent event) {
        if (tailledescription > 159) {
            event.consume();
        }
    }

    @FXML
    private void nbrchh(KeyEvent event) {

        tailledescription = descriptionevent.getText().length();
        //System.out.println(tailledescription);
        taille.setText(tailledescription + "/160");
    }

    @FXML
    private void ctrlnom(KeyEvent event) {

        if (Character.isAlphabetic(event.getCharacter().charAt(0)) || Character.isWhitespace(event.getCharacter().charAt(0))) {

        } else {
            event.consume();
        }

    }

}
