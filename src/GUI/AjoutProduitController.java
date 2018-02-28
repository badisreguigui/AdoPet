/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categorie;
import Entities.Produit;
import Entities.Produit.race;
import Services.BoutiqueService;
import Services.ProduitService;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class AjoutProduitController implements Initializable {
    @FXML
    private Label nomLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label quantiteLabel;
    @FXML
    private Label categLabel;
    @FXML
    private Label raceLabel;
    @FXML
    private Label boutiqueLabel;
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField prixtxt;
    @FXML
    private TextField quantitetxt;
    @FXML
    private ComboBox<String> categtxt;
    @FXML
    private TextField racetxt;
    @FXML
    private JFXComboBox<String> boutiquetxt;
    @FXML
    private TextArea desctxt;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Button browse;
    @FXML
    private ImageView imageview;
    private File file;
    
    private Image image;
    
    private FileInputStream fis;
    @FXML
    private TextArea imagetxt;
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, String> desc;
    @FXML
    private TableColumn<Produit,Integer> prix;
    @FXML
    private TableColumn<Produit,Integer> quantite;
    @FXML
    private TableColumn<Produit, String> categorie;
    @FXML
    private TableColumn<Produit, String> boutique;
    @FXML
    private TableColumn<Produit, String> raceview;
    @FXML
    private TableColumn<Produit, String> imagedisplay;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button reset;
    @FXML
    private Hyperlink boutcateg;
    ObservableList<Produit> ok=FXCollections.observableArrayList();
    private ObservableList<Produit> filteredData = FXCollections.observableArrayList();
    @FXML
    private TextField filterfield;
    
    Notifications notificationBuilder = Notifications.create().title("AdoPet")
                .text("Votre Produit a été ajouté")
                 .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("");
                        }
                });
    Notifications notificationBuilder1 = Notifications.create().title("AdoPet")
                .text("Votre Produit a été modifié")
                 .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("");
                        }
                });
    Notifications notificationBuilder2 = Notifications.create().title("AdoPet")
                .text("Votre Produit a été supprimé")
                 .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("");
                        }
                });
    @FXML
    private AnchorPane ajout;
    public AjoutProduitController() throws SQLException {
        ProduitService ps = new ProduitService();
        ok=ps.DisplayAll();
        //filteredData.addAll(ok);
        //System.out.println(filteredData);
         ok.addListener(new ListChangeListener<Produit>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produit> change) {
                updateFilteredData();
            }
        });
         
    }

    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            imagetxt.setVisible(false);
             ajout.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')");
            // TODO
            ObservableList ok=FXCollections.observableArrayList();
        ProduitService ps = new ProduitService();
        ok=ps.selectcateg();
        ObservableList ok1=FXCollections.observableArrayList();
            BoutiqueService bs = new BoutiqueService();
        ok1=bs.selectbout();
        categtxt.setItems(ok);
        boutiquetxt.setItems(ok1);
            load();
            racetxt.setPromptText("chien/chat");
            racetxt.setTooltip(new Tooltip("chien/chat"));
            ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(nomtxt, Validator.createEmptyValidator("champ obligatoire"));
        prixtxt.addEventFilter(KeyEvent.KEY_TYPED ,numeric_Validation(5) );
        quantitetxt.addEventFilter(KeyEvent.KEY_TYPED ,numeric_Validation(5) );
        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        filterfield.setPromptText("Filtrer");
       
    }    
            

    @FXML
    private void AjoutProduit(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        ProduitService ps = new ProduitService();
        race r = race.valueOf(racetxt.getText());
        String s= categtxt.getSelectionModel().getSelectedItem().toString();
        String s1= boutiquetxt.getSelectionModel().getSelectedItem().toString();
        
        //System.out.println(s);
         if (nomtxt.getText().isEmpty() || prixtxt.getText().isEmpty() || quantitetxt.getText().isEmpty() || racetxt.getText().isEmpty() || imagetxt.getText().isEmpty() ){
                System.out.println("hhh");
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Vérifier vos champs :)");
        alert1.show();
            }
              else{
             
        Produit p1 = new Produit(nomtxt.getText(), desctxt.getText(), Integer.parseInt(prixtxt.getText()), Integer.parseInt(quantitetxt.getText()),imagetxt.getText(),s, r,s1 );
        
   
           
       
            ps.insert(p1);
            //tableview.getItems().clear();
            //filteredData.clear();
            
            //load();
            //filteredData.add(p1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit : "+nomtxt.getText()+" inséré avec succées");
        alert.show();
                
                
         notificationBuilder.darkStyle();
         notificationBuilder.showConfirm();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
                    Parent root;
                    root = loader.load();
                    AjoutProduitController sp=loader.getController();
                    nomtxt.getScene().setRoot(root);
            nomtxt.clear();
            desctxt.clear();
            prixtxt.clear();
            quantitetxt.clear();
            imagetxt.clear();
            racetxt.clear();
            boutiquetxt.setValue(null);
            categtxt.setValue(null);
            imageview.setImage(null);
            
            
            
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
           Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();*/
            

            
        
        }
    }
    
    @FXML
    private void browse(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         
         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg"),
                 new ExtensionFilter("Text Files", "*.txt"),
         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
         new ExtensionFilter("All Files", "*.*"));
         file = fileChooser.showOpenDialog(null);
            if(file != null){
                imagetxt.setText(file.getPath());
                //System.out.println(file.toPath().toUri().toString());
                 image = new Image(file.toURI().toURL().toString());
                imageview.setImage(image);
 }
    }

    private void load() throws SQLException {
        ProduitService ps =new ProduitService();
              ObservableList ok=FXCollections.observableArrayList();
                ok=ps.DisplayAll();
                //tableview.setItems(null);
       //tableview.setItems(ok);
        filteredData.addAll(ok);
         //ref.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        imagedisplay.setCellValueFactory(new PropertyValueFactory<>("imageproduit"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        raceview.setCellValueFactory(new PropertyValueFactory<>("nomraceproduit"));
        boutique.setCellValueFactory(new PropertyValueFactory<>("nomboutiqueproduit"));
        
         tableview.setItems(filteredData);
         //System.out.println(tableview);
        // Listen for text changes in the filter text field
        filterfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
        
    
    
    private void updateFilteredData() {
        filteredData.clear();

        for (Produit p : ok) {
            if (matchesFilter(p)) {
                filteredData.add(p);
            }
        }

        // Must re-sort table after items changed
        reapplyTableSortOrder();
    }
     private boolean matchesFilter(Produit pr) {
        String filterString = filterfield.getText();
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (pr.getNomproduit().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Produit, ?>> sortOrder = new ArrayList<>(tableview.getSortOrder());
        tableview.getSortOrder().clear();
        tableview.getSortOrder().addAll(sortOrder);
    }

    @FXML
    private void affiche(MouseEvent event) throws SQLException {
        Produit p = tableview.getSelectionModel().getSelectedItem();
        ProduitService ps = new ProduitService();
        BoutiqueService bs = new BoutiqueService();
        ResultSet res= ps.Select(p);
        ObservableList ok=FXCollections.observableArrayList();
            ok=ps.selectcategByID(p);
            ObservableList ok2=FXCollections.observableArrayList();
            ok2=ps.selectboutByID(p);
        while(res.next()){
            nomtxt.setText(res.getString("nomproduit"));
            desctxt.setText(res.getString("description"));
            prixtxt.setText(res.getString("prix"));
            quantitetxt.setText(res.getString("quantite"));
            categtxt.setValue(ok.get(0).toString());
            categtxt.setItems(ok);
            racetxt.setText(res.getString("nomraceproduit"));
            boutiquetxt.setValue(ok2.get(0).toString());
            boutiquetxt.setItems(ok2);
            //System.out.println(ok.get(0).toString());
            imagetxt.setText(res.getString("imageproduit"));
            image = new Image("file:///"+res.getString("imageproduit"));
           // System.out.println("file:///"+res.getString("imageproduit"));
            imageview.setImage(image);
        }
        modifier.setOnAction((e) -> {
            
            try {
                race r = race.valueOf(racetxt.getText());
                String s= categtxt.getSelectionModel().getSelectedItem().toString();
                String s1= boutiquetxt.getSelectionModel().getSelectedItem().toString();
                        if(s.equals("Choisir Autre Catégorie :")){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Choisie une Catégorie valide");
        alert.show();
        }
                        else if(s1.equals("Choisir Autre Boutique :")){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Choisie une Boutique valide");
        alert.show();
                        }
                        else if (nomtxt.getText().isEmpty() || prixtxt.getText().isEmpty() || quantitetxt.getText().isEmpty() || racetxt.getText().isEmpty() || imagetxt.getText().isEmpty()){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vérifier vos champs");
        alert.show();
                        }
        else{
               Produit p1 = new Produit(p.getIdproduit(), nomtxt.getText(), desctxt.getText(), Integer.parseInt(prixtxt.getText()), Integer.parseInt(quantitetxt.getText()),imagetxt.getText(),s, r, s1);
                ps.Update(p1);
                notificationBuilder1.darkStyle();
         notificationBuilder1.showConfirm();
                tableview.refresh();
                //load();
                filteredData.removeAll(p1);
                FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
                    Parent root;
                    root = loader.load();
                    AjoutProduitController sp=loader.getController();
                    nomtxt.getScene().setRoot(root);  
            nomtxt.clear();
            desctxt.clear();
            prixtxt.clear();
            quantitetxt.clear();
            imagetxt.clear();
            racetxt.clear();
            categtxt.setItems(null);
            ObservableList ok1=FXCollections.observableArrayList();
        ok1=ps.selectcateg();
        categtxt.setItems(ok1);
        ObservableList ok3=FXCollections.observableArrayList();
        ok3=bs.selectbout();
        boutiquetxt.setItems(ok3);
            boutiquetxt.setItems(null);
            imageview.setImage(null);
           filteredData.add(p1);
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modifié");
        alert.show();
         
            }} catch (SQLException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Ouups!");
        alert.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
           
            

        });
        
        supprimer.setOnAction((e) -> {
            try {
                ps.Delete(p.getIdproduit());
                notificationBuilder2.darkStyle();
                notificationBuilder2.showConfirm();
                filteredData.remove(p);
                //load();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
                    Parent root;
                    root = loader.load();
                    AjoutProduitController sp=loader.getController();
                    nomtxt.getScene().setRoot(root);  
            nomtxt.clear();
            desctxt.clear();
            prixtxt.clear();
            quantitetxt.clear();
            imagetxt.clear();
            racetxt.clear();
            boutiquetxt.setItems(null);
            categtxt.setItems(null);
            ObservableList ok1=FXCollections.observableArrayList();
        ok1=ps.selectcateg();
        categtxt.setItems(ok1);
        ObservableList ok3=FXCollections.observableArrayList();
        ok3=bs.selectbout();
        boutiquetxt.setItems(ok3);
            imageview.setImage(null);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Supprimé");
        alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Oupss!");
        alert.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    
        });
        
    }

    @FXML
    private void reset(ActionEvent event) throws SQLException {
            nomtxt.clear();
            desctxt.clear();
            prixtxt.clear();
            quantitetxt.clear();
            imagetxt.clear();
            racetxt.clear();
            categtxt.setItems(null);
            ObservableList ok=FXCollections.observableArrayList();
        ProduitService ps = new ProduitService();
        ok=ps.selectcateg();
        categtxt.setItems(ok);
            boutiquetxt.setItems(null);
            ObservableList ok3=FXCollections.observableArrayList();
            BoutiqueService bs = new BoutiqueService();
        ok3=bs.selectbout();
        boutiquetxt.setItems(ok3);
            imageview.setImage(null);
    }

    @FXML
    private void AjoutBC(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boutcateg.fxml"));
           Parent root = loader.load();
            BoutcategController spc = loader.getController();
            /*Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();*/
            nomtxt.getScene().setRoot(root);
    }
    
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
  return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9]")){     
                }
            else{
                e.consume();
            }
        }
    };
}
    }
            
           
        
    
        
   


 
 
    

