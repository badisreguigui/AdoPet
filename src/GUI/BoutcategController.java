/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Boutique;
import Entities.Categorie;
import Services.BoutiqueService;
import Services.CategorieService;
import Services.ProduitService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class BoutcategController implements Initializable {

    @FXML
    private TextField nombout;
    @FXML
    private TextField telbout;
    @FXML
    private TextArea adressebout;
    @FXML
    private TextField nomcateg;
    private TextArea imgcateg;
    @FXML
    private TextArea imgbout;
    @FXML
    private ImageView imgviewbout;
    private ImageView imgviewcateg;
    @FXML
    private Button browseboutique;
    @FXML
    private Button ajoutBout;
    @FXML
    private Button ajoutcateg;
    private File file;
    
    private Image image;
    @FXML
    private Button resetbout;
    @FXML
    private Button resetcateg;
    @FXML
    private TableView<Boutique> tablebout;
    @FXML
    private TableColumn<Boutique, String> nomboutique;
    @FXML
    private TableColumn<Boutique, Integer> telboutique;
    @FXML
    private TableColumn<Boutique, String> adresse;
    @FXML
    private TableColumn<Boutique, String> imgboutique;
    @FXML
    private TableView<Categorie> tablecateg;
    @FXML
    private TableColumn<Categorie, String> nomcategorie;
    @FXML
    private Button modifierboutique;
    @FXML
    private Button supprimerboutique;
    @FXML
    private Button supprimercateg;
    @FXML
    private Button modifiercateg;
    @FXML
    private Hyperlink ajoutP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadboutique();
            loadcategorie();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(BoutcategController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void browseboutique(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg"),
                 new FileChooser.ExtensionFilter("Text Files", "*.txt"),
         new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
         new FileChooser.ExtensionFilter("All Files", "*.*"));
         file = fileChooser.showOpenDialog(null);
            if(file != null){
                imgbout.setText(file.getPath());
                //System.out.println(file.toPath().toUri().toString());
                 image = new Image(file.toURI().toURL().toString());
                imgviewbout.setImage(image);
 }
    }

    @FXML
    private void ajoutBoutique(ActionEvent event) throws SQLException {
        BoutiqueService ps = new BoutiqueService();
        Boutique b1 = new Boutique(nombout.getText(), Integer.parseInt(telbout.getText()),adressebout.getText(),imgbout.getText());
         try {
            ps.insert(b1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Boutique : "+nombout.getText()+" inséré avec succées");
        alert.show();
            nombout.clear();
            telbout.clear();
            adressebout.clear();
            imgbout.clear();
            imgviewbout.setImage(null);
            loadboutique();
            
            
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
           Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();*/
            

        } catch (SQLException ex) {
            
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Fail!");
        alert.show();
        }
    }

    private void loadboutique() throws SQLException {
       BoutiqueService ps =new BoutiqueService();
              ObservableList ok=FXCollections.observableArrayList();
                ok=ps.DisplayAll();
                tablebout.setItems(null);
        tablebout.setItems(ok);
         //idbout.setCellValueFactory(new PropertyValueFactory<>("idboutique"));
        nomboutique.setCellValueFactory(new PropertyValueFactory<>("nomboutique"));
        telboutique.setCellValueFactory(new PropertyValueFactory<>("telboutique"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresseboutique"));
        imgboutique.setCellValueFactory(new PropertyValueFactory<>("imageboutique"));
        
        
    }

    @FXML
    private void resetbout(ActionEvent event) {
            nombout.clear();
            telbout.clear();
            adressebout.clear();
            imgbout.clear();
            imgviewbout.setImage(null);
    }

    @FXML
    private void afficheboutique(MouseEvent event) throws SQLException {
        Boutique p = tablebout.getSelectionModel().getSelectedItem();
        BoutiqueService ps = new BoutiqueService();
        ResultSet res= ps.Select(p);
        while(res.next()){
            nombout.setText(res.getString("nomboutique"));
            telbout.setText(res.getString("telboutique"));
            adressebout.setText(res.getString("adresseboutique"));
            imgbout.setText(res.getString("imageboutique"));
            image = new Image("file:///"+res.getString("imageboutique"));
           // System.out.println("file:///"+res.getString("imageproduit"));
            imgviewbout.setImage(image);
        }
        modifierboutique.setOnAction((e) -> {
            
            try {
               
        Boutique b1 = new Boutique(p.getIdboutique(),nombout.getText(), Integer.parseInt(telbout.getText()),adressebout.getText(),imgbout.getText());
                ps.Update(b1);
                tablebout.refresh();
                loadboutique();
            nombout.clear();
            telbout.clear();
            adressebout.clear();
            imgbout.clear();
            imgviewbout.setImage(null);
           
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Updated");
        alert.show();
         
            } catch (SQLException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Fail!");
        alert.show();
            }
           
            

        });
        supprimerboutique.setOnAction((e) -> {
            try {
                ps.Delete(p.getIdboutique());
                loadboutique();
                nombout.clear();
            telbout.clear();
            adressebout.clear();
            imgbout.clear();
            imgviewbout.setImage(null);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Deleted");
        alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Fail!");
        alert.show();
            }
           
    
        });
    }
// ****************************************CATEGORIES***************************************************   
    
    private void browsecateg(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg"),
                 new FileChooser.ExtensionFilter("Text Files", "*.txt"),
         new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
         new FileChooser.ExtensionFilter("All Files", "*.*"));
         file = fileChooser.showOpenDialog(null);
            if(file != null){
                imgcateg.setText(file.getPath());
                //System.out.println(file.toPath().toUri().toString());
                image = new Image(file.toURI().toURL().toString());
                imgviewcateg.setImage(image);
 }
    }
    
       @FXML
    private void ajoutCateg(ActionEvent event) throws SQLException {
           CategorieService ps = new CategorieService();
        Categorie b1 = new Categorie(nomcateg.getText());
         try {
            ps.insert(b1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Boutique : "+nombout.getText()+" inséré avec succées");
        alert.show();
            nomcateg.clear();
            loadcategorie();
            
            
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
           Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();*/
            

        } catch (SQLException ex) {
            
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Fail!");
        alert.show();
        }
    }
     private void loadcategorie() throws SQLException {
       CategorieService ps =new CategorieService();
              ObservableList ok=FXCollections.observableArrayList();
                ok=ps.DisplayAll();
                tablecateg.setItems(null);
        tablecateg.setItems(ok);
         //idcateg.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
        nomcategorie.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        
        
    }

    @FXML
    private void affichecategorie(MouseEvent event) throws SQLException {
        Categorie p = tablecateg.getSelectionModel().getSelectedItem();
        CategorieService ps = new CategorieService();
        ResultSet res= ps.Select(p);
        while(res.next()){
            nomcateg.setText(res.getString("nomcategorie"));

        }
        modifiercateg.setOnAction((e) -> {
            
            try {
               
                Categorie b1 = new Categorie(p.getIdcategorie(),nomcateg.getText());
                ps.Update(b1);
                tablecateg.refresh();
                loadcategorie();
            nomcateg.clear();
           
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Updated");
        alert.show();
         
            } catch (SQLException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Fail!");
        alert.show();
            }
           
            

        });
        supprimercateg.setOnAction((e) -> {
            try {
                ps.Delete(p.getIdcategorie());
                loadcategorie();
                nomcateg.clear();
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Deleted");
        alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Fail!");
        alert.show();
            }
           
    
        });
    }
    
    @FXML
    private void resetcateg(ActionEvent event) {
        nomcateg.clear();
    }

    @FXML
    private void formAjoutProduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
           Parent root = loader.load();
            AjoutProduitController spc = loader.getController();
            nombout.getScene().setRoot(root);
            
    }
}
