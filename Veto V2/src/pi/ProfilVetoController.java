/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Service.ServiceVeto;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class ProfilVetoController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private Label desclabel;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prenomlabel;
    @FXML
    private Button updatebtn;
    @FXML
    private Button suppbtn;
    
    private String nom;
    @FXML
    private Button tarifbtn;
    
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    
    

    
    
    public String getNoma() {
        return nom;
        // TODO
    } 

    public Label getDesclabel() {
        return desclabel;
    }

    public void setDesclabel(String desclabel) {
        this.desclabel.setText(desclabel);
    }

    public Label getNomlabel() {
        return nomlabel;
    }

    public void setNomlabel(String nomlabel) {
        this.nomlabel.setText(nomlabel);
    }

    public Label getPrenomlabel() {
        return prenomlabel;
    }

    public void setPrenomlabel(String prenomlabel) {
        this.prenomlabel.setText(prenomlabel);
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(Image imgView) {
        this.imgView.setImage(imgView);
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateProfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfilVeto.fxml"));
        Parent root=loader.load();
        
//        UpdateProfilVetoController upvc =loader.getController();
//        upvc.setVal(textnom.getText());
//        upvc.setVal();
//        upvc.setNomlabel(textnom.getText());
//        upvc.setDesclabel(desctext.getText());
//        upvc.setPrenomlabel(textprenom.getText());
//        upvc.setImgView(image);
        
        nomlabel.getScene().setRoot(root);
        
    }

    @FXML
    private void deleteProfil(ActionEvent event) throws SQLException {
        ButtonType foo = new ButtonType("Yes :(", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("no :D", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.WARNING,
                "Type YES to confirm your action",
                foo,
                bar);

        alert.setTitle("Information Dialog");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == foo) {
            ServiceVeto sv = new ServiceVeto();
            sv.deleteVeto(sv.getVeto(val).getNom());
        }
    }

    @FXML
    private void tarif(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tarif.fxml"));
        Parent root=loader.load();
        
        TarifController tc=loader.getController();
        ServiceVeto sv =new ServiceVeto();
        tc.setValid(sv.getVeto(val).getId());
        
        nomlabel.getScene().setRoot(root);
        
    }
    
}
