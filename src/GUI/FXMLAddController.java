/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AutoComplete;
import Entities.Pet;
import Entities.Session;
import Services.ServicePet;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLAddController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private JFXTextField name;
    @FXML
    private Label breedLabel;
    @FXML
    private JFXTextField breed;
    @FXML
    private Label ageLabel;
    @FXML
    private JFXTextField age;
    @FXML
    private Label sexLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private Label governateLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label zipLabel;
    @FXML
    private Label imageLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private JFXTextField color;
    @FXML
    private JFXTextField governate;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField zip;
    @FXML
    private JFXTextField image;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXComboBox<String> sex;
    @FXML
    private JFXButton imageupload;
    @FXML
    private ImageView imageview;

    
    //imageVariables
    private FileChooser fileChooser;
    private File file;
    private Image image1;
    private String path;

    private double lat;
    private double lng;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<String> typePet;
    
    Session session = Session.getInstance();
    @FXML
    private AnchorPane pane;
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        add.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 90px; " +
                "-fx-min-height: 90px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        
        File file2 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\edit1.png");
        //Image image = new Image(file.toURI().toString());
        String pt2 = file2.toURI().toString();
        Image image2 = new Image(pt2, 90 ,90, false, false);
        add.setGraphic(new ImageView(image2));
        add.setText("");
        
        reset.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 110px; " +
                "-fx-min-height: 110px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        
        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\reset.png");
        //Image image = new Image(file.toURI().toString());
        String pt = file.toURI().toString();
        Image image = new Image(pt, 120 ,120, false, false);
        reset.setGraphic(new ImageView(image));
        reset.setText("");
        
        
        pane.setStyle("-fx-background-image: url('images/adopet3.jpg');");
        System.out.println(session.IdSession());
        
        
        
        sex.getItems().add(new String("MALE"));
        sex.getItems().add(new String("FEMALE"));
        sex.setValue(new String("MALE"));
        typePet.getItems().add(new String("DOG"));
        typePet.getItems().add(new String("CAT"));
        typePet.setValue(new String("DOG"));
        type.getItems().add(new String("ADOPTION"));
        type.getItems().add(new String("COUPLING"));
        type.setValue(new String("COUPLING"));
        try {
            loadAuto();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void loadAuto() throws SQLException{
        AutoComplete ac = new AutoComplete();
        String[] a = ac.BreedAutoComplete();      
        TextFields.bindAutoCompletion(breed, a);
    }

    @FXML
    private void addprofile(ActionEvent event) throws IOException {
        
        
        // validation using control SFX
//        ValidationSupport validation = new ValidationSupport();
//        
//        validation.registerValidator(name, Validator.createEmptyValidator("text required"));
//        validation.registerValidator(sex, (Control c, Boolean newValue) ->
//                    ValidationResult.fromErrorIf( c, "Checkbox should be checked", !newValue)
//         );
        
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur");
        
        boolean check = false;
        

        if (!name.getText().matches("[a-z A-Z]+")) {
            alert.setHeaderText("error name");
            alert.setContentText("Name box must be filled!");
            alert.showAndWait();
        }
        else if (!breed.getText().matches("[a-z A-Z]+")) {
            alert.setHeaderText("error breed");
            alert.setContentText("Breed box must be filled!");
            alert.showAndWait();
        }
//        else if (age.getText().equals("") || Integer.parseInt(age.getText()) == 0) {
        else if (!age.getText().matches("[0-9]+")){
            alert.setHeaderText("error age");
            alert.setContentText("Age must be: *filled \n                       *number \n                       *lower than 50!");
            alert.showAndWait();
        }
//        else if (age.getText().matches("[0-9]+")){
//            if (Integer.parseInt(age.getText()) > 50) {
//            System.out.println(Integer.parseInt(age.getText())); 
//            alert.setHeaderText("error age");
//            alert.setContentText("Age must be: *filled \n                       *number \n                       *higher than 0!");
//            alert.showAndWait();
//            }
//        }
        else if (!color.getText().matches("[a-z A-Z]+")) {
            alert.setHeaderText("error color");
            alert.setContentText("Color box must be filled!");
            alert.showAndWait();
        }
        else if (!governate.getText().matches("[a-z A-Z]+")) {
            alert.setHeaderText("error governate");
            alert.setContentText("Governate box must be filled!");
            alert.showAndWait();
        }
        else if (!city.getText().matches("[a-z A-Z]+")) {
            alert.setHeaderText("error city");
            alert.setContentText("City box must be filled!");
            alert.showAndWait();
        }
        else if (!zip.getText().matches("[0-9]+")) {
            alert.setHeaderText("error zip");
            alert.setContentText(" zip must be: *filled \n                       *number \n                       *higher than 0!");
            alert.showAndWait();
        }
//        else if (zip.getText().matches("[0-9]+")) {
//            if (Integer.parseInt(zip.getText()) == 0) {
//            alert.setHeaderText("error zip");
//            alert.setContentText(" zip must be: *filled \n                       *number \n                       *higher than 0!");
//            alert.showAndWait();
//            }
//        }
        else if (description.getText().equals("")) {
            alert.setHeaderText("error description");
            alert.setContentText("Description box must be filled!");
            alert.showAndWait();
        }
        else {
        ServicePet sp = new ServicePet();
        Pet p = new Pet(name.getText() ,breed.getText(), Integer.parseInt(age.getText()), sex.getValue().toString(), color.getText(), governate.getText(), city.getText(), Integer.parseInt(zip.getText()), description.getText(), path, session.IdSession(), lat , lng, typePet.getValue().toString(), type.getValue().toString());   
        try {
            
            
            //convert city/gov to lat lng values
            // ============================================================================================================================================

            JSONParser parser = new JSONParser();

            try {
                URL url = new URL("http://maps.google.com/maps/api/geocode/json?address=" + p.getGovernate() + "%" + p.getCity());
                URLConnection connection = url.openConnection();
                InputStream in = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(new File("downloaded.json"));
                byte[] buf = new byte[512];
                while (true) {
                    int len = in.read(buf);
                    if (len == -1) {
                        break;
                    }
                    fos.write(buf, 0, len);
                }
                in.close();
                fos.flush();
                fos.close();

                Object obj = parser.parse(new FileReader(new File("downloaded.json")));

                JSONObject jsonObject = (JSONObject) obj;

                JSONArray rows = (JSONArray) jsonObject.get("results");
                Iterator<JSONObject> iterator = rows.iterator();
                while (iterator.hasNext()) {

                    JSONObject row = (JSONObject) iterator.next().get("geometry");

                    JSONObject rowObj = (JSONObject) row.get("location");

                    lat = (double) rowObj.get("lat");
                    lng = (double) rowObj.get("lng");
                    

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            System.out.println(lat);
            System.out.println(lng);
            System.out.println(p);
            p.setLat(lat);
            p.setLng(lng);
            
            
            System.out.println(p);
            sp.AjouterPet(p);
            
            
            
            // ============================================================================================================================================

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Your cute pet '"+ name.getText()+"' has now his own profile in our application" );
            alert2.showAndWait();
            
            // swtich to specific profile display with pet name check 
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayProfile.fxml"));
            Parent root = loader.load();
            FXMLDisplayProfileController spc = loader.getController();
//            spc.setSuccess("Voiture "+marque.getText()+" "+couleur.getText());
//            spc.setValue(marque.getText());
//            marque.getScene().setRoot(root);
            //spc.setNameLabel(name.getText());
            
            // display in the new page the profile you just added now
            spc.setVal(name.getText());
            spc.setName(sp.getPet(spc.getVal()).getName_pet());
            spc.setBreed(sp.getPet(spc.getVal()).getBreed());
            spc.setAge(sp.getPet(spc.getVal()).getAge());
            spc.setSex(sp.getPet(spc.getVal()).getSex());
            spc.setColor(sp.getPet(spc.getVal()).getColor());
            spc.setGovernate(sp.getPet(spc.getVal()).getGovernate());
            spc.setCity(sp.getPet(spc.getVal()).getCity());
            spc.setZip(sp.getPet(spc.getVal()).getZip());
            spc.setDescription(sp.getPet(spc.getVal()).getDescription());
            spc.setPet_image(sp.getPet(spc.getVal()).getPet_image()); 
            spc.setImageview(image1);
            spc.setImageURL(path);
            //System.out.println(path);
            name.getScene().setRoot(root);
            

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
//            Parent root = loader.load();
//            Scene sc = new Scene(root);
//            Stage st = new Stage();
//            st.setScene(sc);
//            st.show();        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        name.clear();
        breed.clear();
        age.clear();
        sex.setValue(null);
        color.clear();
        governate.clear();
        city.clear();
        zip.clear();
        description.clear();
    }

    @FXML
    private void upload(ActionEvent event) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Window primaryStage = null;
        
        file = fileChooser.showOpenDialog(primaryStage);
            if(file != null){      
                path = file.getAbsolutePath();
                image1 = new Image(file.toURI().toString());            
                imageview.setImage(image1);
                System.out.println(path);
            }
    }
    
}
