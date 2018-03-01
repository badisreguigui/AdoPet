/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Tarif;
import Services.ServiceTarif;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class TarifController implements Initializable {

    @FXML
    private TableView<Tarif> tariftable;
    private TableColumn<Tarif,String> typecln;
    private TableColumn<Tarif,Integer> prixcln;
    @FXML
    private Button ajoutbtn;
    private TextField typetext;
    private TextField prixtext;
    @FXML
    private Button modifbtn;
    @FXML
    private Button backbtn;
    private int iid;
    @FXML
    private TableColumn<?, ?> consultationcln;
    @FXML
    private TableColumn<?, ?> chatcln;
    @FXML
    private TableColumn<?, ?> chiencln;
    @FXML
    private TableColumn<?, ?> sterilisation;
    @FXML
    private TableColumn<?, ?> analysecln;
    @FXML
    private TextField consultaiontext;
    @FXML
    private TextField chattext;
    @FXML
    private TextField chientext;
    @FXML
    private TextField sterilisationtext;
    @FXML
    private TextField analysetext3;
    int valid;
    private ObservableList ok;
    @FXML
    private AnchorPane back;
    @FXML
    private JFXButton goHome;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
    
    
    
    

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
        try {
        back.setStyle("-fx-background-image: url('images/adopet3.jpg');");
            System.out.println(valid);
            loadTarif();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(TarifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterTarif(ActionEvent event) throws SQLException {
        ServiceTarif st=new ServiceTarif();
        
        Tarif t =new Tarif(valid,Integer.parseInt(consultaiontext.getText()),Integer.parseInt(chattext.getText()),
                Integer.parseInt(chientext.getText()),Integer.parseInt(sterilisationtext.getText()),
                Integer.parseInt(analysetext3.getText()));
        
        try {
            st.AjouterTarif(t);
            loadTarif();
        } catch (SQLException ex) {
            Logger.getLogger(TarifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ModifierTarif(ActionEvent event) throws SQLException {
       ModTarif();
       ok.clear();
       tariftable.setItems(null);
                       

        loadTarif();
System.out.println(valid);
        
    }
    
    private void ModTarif()
    {
        ServiceTarif st=new ServiceTarif();
        Tarif t =new Tarif(Integer.parseInt(consultaiontext.getText()),Integer.parseInt(chattext.getText()),
                Integer.parseInt(chientext.getText()),Integer.parseInt(sterilisationtext.getText()),
                Integer.parseInt(analysetext3.getText()));;
        try {
            System.out.println(t);
            System.out.println(iid);
            st.updateTarif(t, iid);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TarifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @FXML
//    private void supprimerTarif(ActionEvent event) throws SQLException {
//        Tarif t = tariftable.getSelectionModel().getSelectedItem();
//        ServiceTarif st = new ServiceTarif();
//        st.deleteTarif(t.getId_tarif());
//        loadTarif();
//    }
    
    private void loadTarif() throws SQLException {
       ServiceTarif st =new ServiceTarif();
        System.out.println(valid);
              ok=FXCollections.observableArrayList();
                ok=st.displayAll1(valid);
                tariftable.setItems(null);
        tariftable.setItems(ok);
        consultationcln.setCellValueFactory(new PropertyValueFactory<>("consultation"));
        chatcln.setCellValueFactory(new PropertyValueFactory<>("vaccinationChat"));
        chiencln.setCellValueFactory(new PropertyValueFactory<>("vaccinationChien"));
        sterilisation.setCellValueFactory(new PropertyValueFactory<>("sterilisation"));
        analysecln.setCellValueFactory(new PropertyValueFactory<>("analyses"));
        
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilVeto.fxml"));
        Parent root=loader.load();
        typetext.getScene().setRoot(root);
    }

    @FXML
    private void modif(MouseEvent event) throws SQLException {
        Object selecteditems= tariftable.getSelectionModel().getSelectedItems().get(0);
        System.out.println(selecteditems);
        String firstcln= selecteditems.toString().split(",")[1].substring(9);
        System.out.println(firstcln);
        
        ServiceTarif st=new ServiceTarif();
         iid= Integer.parseInt(firstcln);
        //System.out.println(typee);
        consultaiontext.setText(""+st.getTarifByVeto(iid).getConsultation());
        chattext.setText(""+st.getTarifByVeto(iid).getVaccinationChat());
        chientext.setText(""+st.getTarifByVeto(iid).getVaccinationChien());
        sterilisationtext.setText(""+st.getTarifByVeto(iid).getSterilisation());
        analysetext3.setText(""+st.getTarifByVeto(iid).getAnalyses());
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                        Parent root5 = loader.load();
                        AcceuilController spc = loader.getController();
                                                
                        consultaiontext.getScene().setRoot(root5);
    }
    
    

    
}
