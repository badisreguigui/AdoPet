/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pet;
import Entities.Session;
import Service.ServicePet;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLEditProfileController implements Initializable {

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
    
    //storing the name of the added profile
    private String val;
    private int returnedId;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView imageview;
    
    
    private FileChooser fileChooser;
    
    private File file;
    
    private Image image1;
    
    private String path;
    
    private String returnedSex;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<String> typePet;
    
    private String typeValue;
    
    private double lat;
    private double lng;
    
    Session session = Session.getInstance();
    @FXML
    private AnchorPane pane;

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
    

    
    
    public String getReturnedSex() {
        return returnedSex;
    }

    public void setReturnedSex(String returnedSex) {
        this.returnedSex = returnedSex;
    }
    
    
    
    
    
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    
    public void setName(String name) {
        this.name.setText(name);
    }
    
    public void setBreed(String breed) {
        this.breed.setText(breed);
    }
    
    public void setAge(int age) {
        this.age.setText(""+age+"");
    }
    
//    public void setSex(String sex) {
//        this.sex.setText(sex);
//    }

    public void setColor(String color) {
        this.color.setText(color);
    }
    
    public void setGovernate(String governate) {
        this.governate.setText(governate);
    }
    
    public void setCity(String city) {
        this.city.setText(city);
    }
    
    public void setZip(int zip) {
        this.zip.setText(""+zip+"");
    }
    
    public void setDescription(String description) {
        this.description.setText(description);
    }
    
    public void setPet_image(String pet_image) {
        this.image.setText(pet_image);
    }

    public ImageView getImageview() {
        return imageview;
    }

    public void setImageview(Image image) {
        this.imageview.setImage(image);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public JFXComboBox<String> getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.setValue(sex);
    }

    public int getReturnedId() {
        return returnedId;
    }

    public void setReturnedId(int returnedId) {
        this.returnedId = returnedId;
    }

    public JFXComboBox<String> getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setValue(type);
    }

    public JFXComboBox<String> getTypePet() {
        return typePet;
    }

    public void setTypePet(String typePet) {
        this.typePet.setValue(typePet);
    }

    
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setStyle("-fx-background-image: url('images/adopet3.jpg');");
        
        add.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 90px; " +
                "-fx-min-height: 90px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        
        File file2 = new File("/Users/mac/Desktop/edit.png");
        //Image image = new Image(file.toURI().toString());
        String pt2 = file2.toURI().toString();
        Image image2 = new Image(pt2, 90 ,90, false, false);
        add.setGraphic(new ImageView(image2));
        add.setText("");
        
        reset.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 100px; " +
                "-fx-min-height: 100px; " +
                "-fx-max-width: 140px; " +
                "-fx-max-height: 140px;"
        );
        
        File file = new File("/Users/mac/Desktop/reset.png");
        //Image image = new Image(file.toURI().toString());
        String pt = file.toURI().toString();
        Image image = new Image(pt, 120 ,120, false, false);
        reset.setGraphic(new ImageView(image));
        reset.setText("");
        
        sex.getItems().add(new String("MALE"));
        sex.getItems().add(new String("FEMALE"));
        
        typePet.getItems().add(new String("DOG"));
        typePet.getItems().add(new String("CAT"));
//        typePet.setValue(new String("DOG"));
        type.getItems().add(new String("ADOPTION"));
        type.getItems().add(new String("COUPLING"));
//        type.setValue(typeValue);
    }    
    

    @FXML
    private void editprofile(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur");
 
        if (name.getText().equals("")) {
            alert.setHeaderText("error name");
            alert.setContentText("Name box must be filled!");
            alert.showAndWait();
        }
        else if (breed.getText().equals("")) {
            alert.setHeaderText("error breed");
            alert.setContentText("Breed box must be filled!");
            alert.showAndWait();
        }
        else if (age.getText().equals("") || Integer.parseInt(age.getText()) == 0) {
            alert.setHeaderText("error age");
            alert.setContentText("Age must be: *filled \n                       *number \n                       *higher than 0!");
            alert.showAndWait();
        }
        else if (color.getText().equals("")) {
            alert.setHeaderText("error color");
            alert.setContentText("Color box must be filled!");
            alert.showAndWait();
        }
        else if (governate.getText().equals("")) {
            alert.setHeaderText("error governate");
            alert.setContentText("Governate box must be filled!");
            alert.showAndWait();
        }
        else if (city.getText().equals("")) {
            alert.setHeaderText("error city");
            alert.setContentText("City box must be filled!");
            alert.showAndWait();
        }
        else if (zip.getText().equals("") || Integer.parseInt(zip.getText()) == 0) {
            alert.setHeaderText("error zip");
            alert.setContentText(" zip must be: *filled \n                       *number \n                       *higher than 0!");
            alert.showAndWait();
        }
        else if (description.getText().equals("")) {
            alert.setHeaderText("error description");
            alert.setContentText("Description box must be filled!");
            alert.showAndWait();
        }
        else {
        ServicePet sp = new ServicePet();
            
        Pet p = new Pet(name.getText() ,breed.getText(), Integer.parseInt(age.getText()), sex.getValue().toString(), color.getText(), governate.getText(), city.getText(), Integer.parseInt(zip.getText()), description.getText(), path, lat , lng, typePet.getValue().toString(), type.getValue().toString());   

//        Pet p = new Pet(name.getText() ,breed.getText(), Integer.parseInt(age.getText()), sex.getValue().toString(), color.getText(), governate.getText(), city.getText(), Integer.parseInt(zip.getText()), description.getText(), path);   
        try {
            
            System.out.println(p);
            System.out.println(p.getGovernate());
            System.out.println(p.getCity());
            //00000000000=Convert bled blasa to lat long=0000000
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
            


            p.setLat(lat);
            p.setLng(lng);
            
            System.out.println(lat);
            System.out.println(lng);
            //00000000000
            System.out.println(path);
            System.out.println(returnedId);
            
            sp.UpdatePetById(p,returnedId);
            
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Your cute pet '"+ name.getText()+"' profile is now updated!" );
            alert2.showAndWait();
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichagefront.fxml"));
//            Parent root;
//                        try {
//                            root = loader.load();
//                             AffichagefrontController afc = loader.getController();
//            description.getScene().setRoot(root);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
            Parent root = loader.load();
            FXMLDisplayAllController afx = loader.getController();
            nameLabel.getScene().setRoot(root);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void reset(ActionEvent event) throws SQLException {
        ServicePet sp = new ServicePet();
        
//        name.setText(sp.getPet(val).getName_pet());
//        breed.setText(sp.getPet(val).getBreed());
//        age.setText(""+sp.getPet(val).getAge()+"");
//        //sex.setText(sp.getPet(val).getSex());
//        color.setText(sp.getPet(val).getColor());
//        governate.setText(sp.getPet(val).getGovernate());
//        city.setText(sp.getPet(val).getCity());
//        zip.setText(""+sp.getPet(val).getZip()+"");
//        description.setText(sp.getPet(val).getDescription());
//        image.setText(sp.getPet(val).getPet_image());

        name.setText(sp.getPetAdo(returnedId).getName_pet());
        breed.setText(sp.getPetAdo(returnedId).getBreed());
        age.setText(""+sp.getPetAdo(returnedId).getAge()+"");
        sex.setValue(sp.getPetAdo(returnedId).getSex());
        color.setText(sp.getPetAdo(returnedId).getColor());
        governate.setText(sp.getPetAdo(returnedId).getGovernate());
        city.setText(sp.getPetAdo(returnedId).getCity());
        zip.setText(""+sp.getPetAdo(returnedId).getZip()+"");
        description.setText(sp.getPetAdo(returnedId).getDescription());
        image.setText(sp.getPetAdo(returnedId).getPet_image());
        type.setValue(sp.getPetAdo(returnedId).getType());
        typePet.setValue(sp.getPetAdo(returnedId).getTypePet());
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
            }
    }
    
}
