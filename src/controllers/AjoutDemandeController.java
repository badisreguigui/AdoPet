/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DemandeSitting;
import Entities.OffreSitting;
import Entities.Session;
import Services.ServicePetSitting;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import jfxtras.scene.control.LocalTimeTextField;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class AjoutDemandeController implements Initializable {
    @FXML
    private AnchorPane Anchordemande;
    @FXML
    private Pane panedemande;
    @FXML
    private Label loffre;
    @FXML
    private TextArea Desc;
    @FXML
    private DatePicker dd;
    @FXML
    private DatePicker df;
    @FXML
    private Label ldd;
    @FXML
    private Label ldf;
    @FXML
    private Label ldesc;
    @FXML
    private TextField prix;
    @FXML
    private Label lprix;
    @FXML
    private Label ltel;
    @FXML
    private TextField tel;
    @FXML
    private Label llieu;
    @FXML
    private MenuButton typeA;
    @FXML
    private CheckBox chien;
    @FXML
    private CheckBox chat;
    @FXML
    private CheckBox autre;
    @FXML
    private MenuButton prefM;
    @FXML
    private CheckBox Nouri;
    @FXML
    private CheckBox prome;
    @FXML
    private CheckBox Viste;
    @FXML
    private TextField lieu;
    @FXML
    private LocalTimeTextField hd;
    @FXML
    private LocalTimeTextField hf;
    @FXML
    private JFXButton Btn;
    @FXML
    private TextField titre;
    @FXML
    private ImageView progressing;
    @FXML
    private Label ltitre;
    
    Session s = Session.getInstance();
    @FXML
    private JFXButton back;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(s.IdSession());
        Desc.setPromptText("Bonjour, nous recherchons une personne susceptible de garder notre petit chien (Yorkshire, calme, propre et affectueux ).\n Merci par avance de nous répondre rapidement.");
        progressing.setVisible(false);
        Callback<DatePicker, DateCell> dayCellFactory = (DatePicker dp) -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);
                if( item.isBefore(LocalDate.now()) || ((dp != dd) && (item.isBefore(dd.getValue().plusDays(1)))))
                {
                    setStyle("-fx-background-color: #6D6D6D;");
                    Platform.runLater(() -> setDisable(true));
                }      
            }
        };
        dd.setDayCellFactory(dayCellFactory);
        df.setDayCellFactory(dayCellFactory);
        
            

    }    

    @FXML
    private void ajouterDemande(ActionEvent event) throws SQLException {
        ServicePetSitting sv = new ServicePetSitting();   
        DemandeSitting d = new DemandeSitting();
        d.setTitre(titre.getText());
        d.setNourriture(chat.isSelected());
        d.setAutre(autre.isSelected());
        d.setChien(chien.isSelected());
        d.setDescription(Desc.getText());
        d.setDate_debut_demande(dd.getValue());
        d.setDate_fin_demande(df.getValue());
        d.setChat(chat.isSelected());
        d.setLieu(lieu.getText());
        d.setNum_tel(Integer.parseInt(tel.getText()));
        d.setPrix_souhaite(Float.parseFloat(prix.getText()));
        d.setVisite(Viste.isSelected());
        d.setPromenade(prome.isSelected());
        d.setTmp_debut(Time.valueOf(hd.getLocalTime()));
        d.setTmp_fin(Time.valueOf(hf.getLocalTime()));
        d.setId_user(s.IdSession());
        sv.ajouterDemande(d);
        

        progressing.setVisible(true);
        PauseTransition tr= new PauseTransition(Duration.seconds(1));
        tr.setOnFinished(ev -> {
            System.out.println("Offre ajoutée avec success !");
            progressing.setVisible(false);
        });
        tr.play();
        //succes
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("demande ajoutée avec succés!");
        alert.showAndWait();
        
        //fail
        Integer teli = new Integer(tel.getText());
        if (teli < 8 && teli > 8){
            Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("Erreur !");
            alert2.setHeaderText(null);
            alert2.setContentText("Numéro fournis est incorrect!");
            alert2.showAndWait();
        }
        
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/PetSitting.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();
    }

    @FXML
    private void annuleCA(ActionEvent event) {
        if (chien.isSelected()){
        autre.setSelected(false);
        autre.setDisable(true);
        chat.setSelected(false);
        chat.setDisable(true);
        }else{
            autre.setDisable(false);
            chat.setDisable(false);
        }
            
        }
        

    @FXML
    private void annuleCHA(ActionEvent event) {
        if (chat.isSelected()){
        autre.setSelected(false);
        autre.setDisable(true);
        chien.setSelected(false);
        chien.setDisable(true);
        }else{
            chien.setDisable(false);
            autre.setDisable(false);
            
        }
        
    }

    @FXML
    private void annuleCC(ActionEvent event) {
        if (autre.isSelected()){
        chat.setSelected(false);
        chat.setDisable(true);
        chien.setSelected(false);
        chien.setDisable(true);
        }else{
            chat.setDisable(false);
            chien.setDisable(false);
        }
    }
    
}
