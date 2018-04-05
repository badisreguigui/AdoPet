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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimeTextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class ModifoffreController implements Initializable {
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Pane panedemande;
    @FXML
    private Label loffre;
    @FXML
    private TextArea Desc;
    @FXML
    private TextField dd;
    @FXML
    private TextField df;
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
    private TextField hd;
    @FXML
    private TextField hf;
    @FXML
    private JFXButton Btn;
    @FXML
    private TextField titre;
    @FXML
    private Label ltitre;
    @FXML
    private MenuButton typeA;
    @FXML
    private CheckBox chien;
    @FXML
    private CheckBox chat;
    @FXML
    private CheckBox autre;
    @FXML
    private JFXButton back;
    
    ServicePetSitting sv = new ServicePetSitting();
    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ValidationSupport validationSupport = new ValidationSupport();
//        validationSupport.registerValidator(Desc, Validator.createEmptyValidator("champ obligatoire"));
//        validationSupport.registerValidator(Desc, Validator.createEmptyValidator("champ obligatoire"));
        
    }    

    public void setId(int id) {
        this.id = id;
        System.out.println(id);
    }
    
    
    
    public void setting(OffreSitting d){
        Desc.setText(d.getDescription());
        dd.setText(String.valueOf(d.getDate_debut_dispo()));
        df.setText(String.valueOf(d.getDate_fin_dispo()));
        if (d.isChien() == true)
            chien.setSelected(true);
        if (d.isChat()== true)
            chat.setSelected(true);
        if (d.isAutre()== true)
            autre.setSelected(true);
        if (d.isNourriture()== true)
            Nouri.setSelected(true);
        if (d.isPromenade()== true)
            prome.setSelected(true);
        if (d.isVisite() == true)
            Viste.setSelected(true);
        hd.setText(String.valueOf(d.getTmp_debut()));
        hf.setText(String.valueOf(d.getTmp_fin()));
        titre.setText(d.getTitre());
        lieu.setText(d.getLieu());
        tel.setText(String.valueOf(d.getNum_tel()));
        prix.setText(String.valueOf(d.getPrix_demande()));
    }

    @FXML
    private void modifierOffre(ActionEvent event) throws SQLException, IOException {
        OffreSitting o = new OffreSitting();
        o.setTitre(titre.getText());
        o.setNourriture(Nouri.isSelected());
        o.setAutre(autre.isSelected());
        o.setChien(chien.isSelected());
        o.setDescription(Desc.getText());
        o.setDate_debut_dispo(LocalDate.parse(dd.getText()));
        o.setDate_fin_dispo(LocalDate.parse(df.getText()));
        o.setChat(chat.isSelected());
        o.setLieu(lieu.getText());
        o.setNum_tel(Integer.parseInt(tel.getText()));
        o.setPrix_demande(Float.parseFloat(prix.getText()));
        o.setVisite(Viste.isSelected());
        o.setPromenade(prome.isSelected());
        o.setTmp_debut(Time.valueOf(hd.getText()));
        o.setTmp_fin(Time.valueOf(hf.getText()));
        o.setId_offre(id);
        sv.modifierOffre(o);
        Btn.getScene().getWindow().hide();
        Stage fordemande = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AffichageOffres.fxml"));
        Parent root = loader.load();
        AffichageOffresController cnt = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        fordemande.centerOnScreen();
        fordemande.setScene(scene);
        fordemande.setResizable(false);
        fordemande.show();
        }
    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        Stage foroffre = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/Affichageoffres.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        foroffre.centerOnScreen();
        foroffre.setScene(scene);
        foroffre.setResizable(false);
        foroffre.show();


    }
    
}
