/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.AutoComplete;
import Entities.Pet;
import Entities.Session;
import Service.ServicePet;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private String filePath;
    private static final int BUFFER_SIZE = 4096;
   
    
    

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
        
        File file2 = new File("/Users/mac/Desktop/edit1.png");
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
        
        File file = new File("/Users/mac/Desktop/reset.png");
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
        

        if (!name.getText().matches("[a-zA-Z]+") && name.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            alert.setHeaderText("error name");
            alert.setContentText("Name box must be filled!");
            alert.showAndWait();
        }
        else if (!breed.getText().matches("[a-zA-Z]+") && breed.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            alert.setHeaderText("error breed");
            alert.setContentText("Breed box must be filled!");
            alert.showAndWait();
        }
//        else if (age.getText().equals("") || Integer.parseInt(age.getText()) == 0) {
        else if (!age.getText().matches("[0-9]+") && age.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")){
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
        else if (!color.getText().matches("[a-zA-Z]+") && color.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            alert.setHeaderText("error color");
            alert.setContentText("Color box must be filled!");
            alert.showAndWait();
        }
        else if (!governate.getText().matches("[a-zA-Z]+") && governate.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            alert.setHeaderText("error governate");
            alert.setContentText("Governate box must be filled!");
            alert.showAndWait();
        }
        else if (!city.getText().matches("[a-zA-Z]+") && city.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            alert.setHeaderText("error city");
            alert.setContentText("City box must be filled!");
            alert.showAndWait();
        }
        else if (!zip.getText().matches("[0-9]+") && zip.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
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
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
            Parent root = loader.load();
            FXMLDisplayAllController spc = loader.getController();
//            spc.setSuccess("Voiture "+marque.getText()+" "+couleur.getText());
//            spc.setValue(marque.getText());
//            marque.getScene().setRoot(root);
            //spc.setNameLabel(name.getText());
            
            // display in the new page the profile you just added now
//            spc.setVal(name.getText());
//            spc.setName(sp.getPet(spc.getVal()).getName_pet());
//            spc.setBreed(sp.getPet(spc.getVal()).getBreed());
//            spc.setAge(sp.getPet(spc.getVal()).getAge());
//            spc.setSex(sp.getPet(spc.getVal()).getSex());
//            spc.setColor(sp.getPet(spc.getVal()).getColor());
//            spc.setGovernate(sp.getPet(spc.getVal()).getGovernate());
//            spc.setCity(sp.getPet(spc.getVal()).getCity());
//            spc.setZip(sp.getPet(spc.getVal()).getZip());
//            spc.setDescription(sp.getPet(spc.getVal()).getDescription());
//            spc.setPet_image(sp.getPet(spc.getVal()).getPet_image()); 
//            spc.setImageview(image1);
//            spc.setImageURL(path);
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
//        fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
//        );
//        Window primaryStage = null;
//        
//        file = fileChooser.showOpenDialog(primaryStage);
//            if(file != null){      
//                path = file.getAbsolutePath();
//                image1 = new Image(file.toURI().toString());            
//                imageview.setImage(image1);
//                System.out.println(path);
//            }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        ArrayList<String> extentions = new ArrayList<String>();
        extentions.add("*.jpg");
        extentions.add("*.png");
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Image Type", extentions);
        fileChooser.getExtensionFilters().add(extentionFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            filePath = file.getPath();
            System.out.println(filePath);
//            ServiceImage serv = new ServiceImage();
//            Images image = new Images();
//            image.setId_local_id(ModifierLocalController.idLocal);
//            image.setImage_name(file.getName());
//            serv.ajouterImage(image);
//            System.out.println(image.getImage_name());

            path = filePath ;
            String ftpUrl = "ftp://%s:%s@%s/%s";
//            String host = "ftp.ezyro.com";
//            String user = "ezyro_21704070";
//            String pass = "r9pil7j24e";
            String host = "185.27.134.11";
            String user = "ezyro_21704070";
            String pass = "r9pil7j24e";

            String uploadPath = "images/"  +file.getName();

            ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);

            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println("File uploaded");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Image Ajouté avec succé");
                alert.show();
//                returnToUrl(event, "ListeImageLocal.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
    
}
