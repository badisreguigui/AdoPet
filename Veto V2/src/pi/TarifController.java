/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entities.Tarif;
import Service.ServiceTarif;
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
import javafx.scene.input.MouseEvent;

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
        try {
            loadTarif();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(TarifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterTarif(ActionEvent event) {
        ServiceTarif st=new ServiceTarif();
        Tarif t =new Tarif(Integer.parseInt(consultaiontext.getText()),Integer.parseInt(chattext.getText()),
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
              ok=FXCollections.observableArrayList();
                ok=st.displayAll();
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
    
    

    
}
