/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import entities.events;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceEvents;

/**
 * FXML Controller class
 *
 * @author achref kh
 */
public class Events_DisplayMyParticipationsController implements Initializable {

    @FXML
    private TableColumn<events, String> nom;
    @FXML
    private TableColumn<events, String> type;
    @FXML
    private TableColumn<events, Date> datedebut;
    @FXML
    TableColumn<events, String> organisateur;
    @FXML
    private Button fermer;
    @FXML
    private TableView<events> affichage;
    @FXML
    private TableColumn<events, String> lieu;
    @FXML
    private TextArea feedback;
    @FXML
    private AnchorPane DisplayMyParticipations;
    
    Session s = Session.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayMyParticipations.setStyle("-fx-background-image: url('/images/adopet1.jpg');");
        ServiceEvents sp = new ServiceEvents();
        ObservableList ok = FXCollections.observableArrayList();
        try {
            ok = sp.DisplayMyParticipation(s.IdSession());
        } catch (SQLException ex) {
            Logger.getLogger(Events_DisplayMyEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        affichage.setItems(null);
        affichage.setItems(ok);
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        organisateur.setCellValueFactory(new PropertyValueFactory<>("organisateur"));
        
        affichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ServiceEvents se = new ServiceEvents();
                try {
                    feedback.setText(se.getFeedback(affichage.getSelectionModel().getSelectedItem().getId(),s.IdSession()));
                } catch (SQLException ex) {
                    Logger.getLogger(Events_DisplayMyParticipationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    
    @FXML
    public void fermer() {

        Stage stage = (Stage) affichage.getScene().getWindow();
        stage.close();
    }

    /*@FXML
    private void enregistrer(ActionEvent event) throws IOException {
        BufferedWriter bufferedWriter = null;

        String dirName = "C:\\AdoPet\\Events\\";
        File dir = new File(dirName);
        System.out.println(dir.mkdirs());
        // boolean isCreated = dir.mkdirs();
        File f = new File(dirName + id.getText() + ".txt");
        System.out.println(f.createNewFile());

        bufferedWriter = new BufferedWriter(new FileWriter(f));

        bufferedWriter.write("nom event = " + nom.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("Type event = " + type.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("Lieu event = " + lieu.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("Date début event = " + datedebut.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("Date fin event = " + datefin.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("Nbr participants event = " + nbrparticipants.getText());
        bufferedWriter.newLine();
        bufferedWriter.write("Organisateur event = " + organisateur.getText());
        bufferedWriter.newLine();

        bufferedWriter.close();

    }*/
    @FXML
    private void addfeedback(ActionEvent event) throws SQLException {
        ServiceEvents se = new ServiceEvents();
        se.addupdatefeedback(feedback.getText(),affichage.getSelectionModel().getSelectedItem().getId(), s.IdSession());
        Stage stage = (Stage) affichage.getScene().getWindow();
        stage.close();
    }

}
