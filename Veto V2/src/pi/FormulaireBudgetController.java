/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entities.Tarif;
import Service.ServiceTarif;
import Service.ServiceVeto;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class FormulaireBudgetController implements Initializable {

    @FXML
    private Button retourbtn;
    private String type1;
    private ObservableList data1= FXCollections.observableArrayList();
    @FXML
    private Label prixlabel;
    @FXML
    private CheckBox consultationcheck;
    @FXML
    private CheckBox vaccincheck;
    @FXML
    private Label consultationlbl;
    @FXML
    private Label vaccinlbl;
    
    private int idprix;
    private int prixC;
    private int prixV;
    private int prix1;
    private int prixS;
    private int prixA;
    
    private int somme=0;
    @FXML
    private Label sommelbl;
    @FXML
    private CheckBox chiencheck;
    @FXML
    private CheckBox sterilisationcheck;
    @FXML
    private CheckBox analysecheck;
    @FXML
    private Label chienlbl;
    @FXML
    private Label sterilisationlbl;
    @FXML
    private Label analyselbl;

    public int getIdprix() {
        return idprix;
    }

    public void setIdprix(int idprix) {
        this.idprix = idprix;
    }

    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
      
        ServiceTarif st= new ServiceTarif();
        try {
            loadPrix();
        } catch (SQLException ex) {
            Logger.getLogger(FormulaireBudgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }   
   

    @FXML
    private void retourner(ActionEvent event) throws IOException, SQLException {
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        
         ServiceVeto sv = new ServiceVeto();
            ResultSet res = sv.shop();
            while(res.next()){
        System.out.println(res.getInt(1));
                
                int a =res.getInt(1);
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root=loader.load();
        ProfilController spc = loader.getController();
                            spc.setId(a);
                            spc.setNomlabel(label2.getText());
                            System.out.println(label2.getText());
                            spc.setPrenomlabel(label3.getText());
                            spc.setDesclabel(label4.getText());
                            String path= sv.getVetobyId(a).getImage();
                            System.out.println(path);
                            spc.setImageView(new Image("file:///"+path));
      //  typebox.getScene().setRoot(root);
    }}


//    @FXML
//    private void displayPrix(MouseEvent event) {
//        String a = typebox.getValue();
//        System.out.println(a);
//    }
    
    public void loadPrix() throws SQLException
    {
        ServiceTarif st=new ServiceTarif();
        prixC=st.getTarifByVeto(idprix).getConsultation();
        consultationlbl.setText(""+prixC);
        prixV=st.getTarifByVeto(idprix).getVaccinationChat();
        vaccinlbl.setText(""+prixV);
        prix1=st.getTarifByVeto(idprix).getVaccinationChien();
        chienlbl.setText(""+prix1);
        prixS=st.getTarifByVeto(idprix).getSterilisation();
        sterilisationlbl.setText(""+prixS);
        prixA=st.getTarifByVeto(idprix).getAnalyses();
        analyselbl.setText(""+prixA);
        
        
    }

    @FXML
    private void consultationaction(ActionEvent event) {
        if (consultationcheck.isSelected()){
            System.out.println("ok");
            somme+=prixC;
            sommelbl.setText(""+somme+" TND");
        }
        else {
            somme-=prixC;
            sommelbl.setText(""+somme+" TND");
        }
        

    }

    @FXML
    private void vaccinAction(ActionEvent event) {
        if (vaccincheck.isSelected()){
            System.out.println("ok");
            somme+=prixV;
            sommelbl.setText(""+somme+" TND");
        }
        else {
            somme-=prixV;
            sommelbl.setText(""+somme+" TND");
        }
    }

    @FXML
    private void chienAction(ActionEvent event) {
        if (chiencheck.isSelected()){
            System.out.println("ok");
            somme+=prix1;
            sommelbl.setText(""+somme+" TND");
        }
        else {
            somme-=prix1;
            sommelbl.setText(""+somme+" TND");
        }
    }

    @FXML
    private void sterilisationAction(ActionEvent event) {
        if (sterilisationcheck.isSelected()){
            System.out.println("ok");
            somme+=prixS;
            sommelbl.setText(""+somme+" TND");
        }
        else {
            somme-=prixS;
            sommelbl.setText(""+somme+" TND");
        }
    }

    @FXML
    private void analyseAction(ActionEvent event) {
        if (analysecheck.isSelected()){
            System.out.println("ok");
            somme+=prixA;
            sommelbl.setText(""+somme+" TND");
        }
        else {
            somme-=prixA;
            sommelbl.setText(""+somme+" TND");
        }
    }

    
   
    
}
