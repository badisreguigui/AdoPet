/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pet;
import Entities.Session;
import Service.ServicePet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLDisplayProfileController  implements Initializable {

    @FXML
    private Label nameLabel;
    
    //storing the name of the added profile
    private String val;
            
    @FXML
    private Label breedLabel;
    @FXML
    private Label name;
    @FXML
    private Label breed;
    @FXML
    private Label age;
    @FXML
    private Label sex;
    @FXML
    private Label color;
    @FXML
    private Label governate;
    @FXML
    private Label city;
    @FXML
    private Label zip;
    @FXML
    private Label description;
    @FXML
    private Label pet_image;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private ImageView imageview;
    
    //get the URL of image from add controller
    private String imageURL;
    
    private Image image1;
    
    Session session = Session.getInstance();

    
    
    
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
    
    public void setSex(String sex) {
        this.sex.setText(sex);
    }

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
        this.pet_image.setText(pet_image);
    }

    public Label getPet_image() {
        return pet_image;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ImageView getImageview() {
        return imageview;
    }

    public void setImageview(Image image) {
        this.imageview.setImage(image);
    }

    
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
//        ServicePet sp = new ServicePet();
//        name.setText(sp.getPet(val).getName_pet());
//        breed.setText(sp.getPet(val).getBreed());    
    }    


    @FXML
    private void edit(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditProfile.fxml"));
        Parent root = loader.load();
        FXMLEditProfileController spc = loader.getController();
        spc.setVal(name.getText());
        name.getScene().setRoot(root);
        
        ServicePet sp = new ServicePet();
        
        spc.setName(sp.getPet(spc.getVal()).getName_pet());
        spc.setBreed(sp.getPet(spc.getVal()).getBreed());
        spc.setAge(sp.getPet(spc.getVal()).getAge());
        //spc.setSex(sp.getPet(spc.getVal()).getSex());
        
        System.out.println("ktibaaa");
        System.out.println(sp.getPet(spc.getVal()).getSex());
        
        spc.setSex(sp.getPet(spc.getVal()).getSex());
        
        spc.setColor(sp.getPet(spc.getVal()).getColor());
        spc.setGovernate(sp.getPet(spc.getVal()).getGovernate());
        spc.setCity(sp.getPet(spc.getVal()).getCity());
        spc.setZip(sp.getPet(spc.getVal()).getZip());
        spc.setDescription(sp.getPet(spc.getVal()).getDescription());
        spc.setPet_image(sp.getPet(spc.getVal()).getPet_image()); 
        
        System.out.println(sp.getPet(spc.getVal()).getPet_image());
        
        File file = new File(sp.getPet(spc.getVal()).getPet_image());
        image1 = new Image(file.toURI().toString());
        spc.setImageview(image1);

    }

    @FXML
    private void deleteProfile(ActionEvent event) throws SQLException, InterruptedException {
        
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("Are you sure you want to delete your pets profile? \n warning: once deleted you cant get it back." );
//        Optional <ButtonType> action = alert.showAndWait();
//        
//        Button okButton = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
//        okButton.setText("Accept.");
//        
//        if(action.get() == ButtonType.OK){
//            ServicePet sp = new ServicePet();
//            sp.DeletePet(sp.getPet(val).getName_pet());
//        }else
//        {
//            alert.close();
//        }

        ButtonType foo = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.WARNING,
                "Are you sure you want to delete your profile permanently \n Type YES to confirm your action",
                foo,
                bar);

        alert.setTitle("Information Dialog");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == foo) {
            ServicePet sp = new ServicePet();
            sp.DeletePet(sp.getPet(val).getName_pet());
        }

//        TextInputDialog input = new TextInputDialog();
//
//	input.setTitle("Are you sure you want to delete your pets profile?");
//	input.setHeaderText("Type YES to confirm your action");
//	Optional<String> result = input.showAndWait();
//        
//
//	if(result.isPresent()) {
//            if (result.get().equalsIgnoreCase("YES")) {
//                System.out.println("s7i7");
//                    ServicePet sp = new ServicePet();
//                    sp.DeletePet(sp.getPet(val).getName_pet());
//            }else {
//                System.out.println("ghalet");
//                input.wait();
//            }   
//	}
//        else{
//            input.showAndWait();
//        }
        
        
        
    }

    
}
