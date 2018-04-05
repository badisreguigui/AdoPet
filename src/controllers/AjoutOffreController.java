/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.OffreSitting;
import Entities.Session;
import Services.ServicePetSitting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import java.awt.Desktop.Action;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import jfxtras.scene.control.CalendarTimeTextField;
import jfxtras.scene.control.LocalDatePicker;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.LocalTimeTextField;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class AjoutOffreController implements Initializable {
    @FXML
    private JFXButton Btn;
    @FXML
    private CheckBox Viste;
    @FXML
    private CheckBox Nouri;
    @FXML
    private TextArea Desc;
    @FXML
    private CheckBox prome;
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
    private TextField lieu;
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
    private Label loffre;
    @FXML
    private MenuButton prefM;
    @FXML
    private LocalTimeTextField hd;
    @FXML
    private LocalTimeTextField hf;
    @FXML
    private ImageView progressing;
    @FXML
    private TextField titre;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Pane panedemande;
    @FXML
    private Label ltitre;
    
    private String imageFile;
    
    Session s = Session.getInstance();
    @FXML
    private JFXButton back;
    
    
    
 
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Desc.setPromptText("Bonjour, \n Je suis quelqu'un de respectueux et responsable et J'adore les animaux! Je vous propose mes services pour prendre soin de vos boules d'amour...");
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
    private void ajouterOffre(ActionEvent event) throws SQLException {
        ServicePetSitting sv = new ServicePetSitting();   
        OffreSitting o = new OffreSitting();
        o.setTitre(titre.getText());
        o.setNourriture(chat.isSelected());
        o.setAutre(autre.isSelected());
        o.setChien(chien.isSelected());
        o.setDescription(Desc.getText());
        o.setDate_debut_dispo(dd.getValue());
        o.setDate_fin_dispo(df.getValue());
        o.setChat(chat.isSelected());
        o.setLieu(lieu.getText());
        o.setNum_tel(Integer.parseInt(tel.getText()));
        o.setPrix_demande(Float.parseFloat(prix.getText()));
        o.setVisite(Viste.isSelected());
        o.setPromenade(prome.isSelected());
        o.setTmp_debut(Time.valueOf(hd.getLocalTime()));
        o.setTmp_fin(Time.valueOf(hf.getLocalTime()));
        o.setId_user(s.IdSession());
        sv.ajouterOffre(o);
        

        progressing.setVisible(true);
        PauseTransition tr= new PauseTransition(Duration.seconds(1));
        tr.setOnFinished(ev -> {
            System.out.println("Offre ajoutée avec success !");
            progressing.setVisible(false);
        });
        tr.play();
        //Succes
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Offre ajoutée avec succés!");
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
        
                   // alert2.setContentText("Vérifier les informations fournis !");

        
    }

    @FXML
    private void ajoutPhoto(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            imageFile = selectedFile.toURI().toURL().toString();
            Image image = new Image(imageFile);       
        } else {
            System.out.println("file doesn't exist");
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
}

        
        

        
    

    
    

