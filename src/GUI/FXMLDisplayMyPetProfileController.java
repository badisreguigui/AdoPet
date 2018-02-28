/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServicePet;
import com.jfoenix.controls.JFXButton;
import java.io.File;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class FXMLDisplayMyPetProfileController implements Initializable {

    private int returnedId;
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
    private Label city;
    @FXML
    private Label type;
    @FXML
    private Label typePet;
    @FXML
    private Label description;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton delete;
    
    private Image image1;
    @FXML
    private AnchorPane pane;

    public int getReturnedId() {
        return returnedId;
    }

    public void setReturnedId(int returnedId) {
        this.returnedId = returnedId;
    }

    public Label getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public Label getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed.setText(breed);
    }

    public Label getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age.setText(age);
    }

    public Label getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.setText(sex);
    }

    public Label getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color.setText(color);
    }

    public Label getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city.setText(city);
    }

    public Label getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public Label getTypePet() {
        return typePet;
    }

    public void setTypePet(String typePet) {
        this.typePet.setText(typePet);
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(Image image) {
        this.imageView.setImage(image);
    }
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        pane.setStyle("-fx-background-image: url('images/adopet2.jpg');");

    }    

    @FXML
    private void edit(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditProfile.fxml"));
        Parent root = loader.load();
        FXMLEditProfileController spc = loader.getController();
        spc.setReturnedId(returnedId);
        name.getScene().setRoot(root);
        
        ServicePet sp = new ServicePet();
        
        spc.setName(sp.getPetById(returnedId).getName_pet());
        spc.setBreed(sp.getPetById(returnedId).getBreed());
        spc.setAge(sp.getPetById(returnedId).getAge());       
        spc.setSex(sp.getPetById(returnedId).getSex());  
        spc.setType(sp.getPetById(returnedId).getType());
        spc.setTypePet(sp.getPetById(returnedId).getTypePet());
        spc.setColor(sp.getPetById(returnedId).getColor());
        spc.setGovernate(sp.getPetById(returnedId).getGovernate());
        spc.setCity(sp.getPetById(returnedId).getCity());
        spc.setZip(sp.getPetById(returnedId).getZip());
        spc.setDescription(sp.getPetById(returnedId).getDescription());     
        File file = new File(sp.getPetById(returnedId).getPet_image());
        image1 = new Image(file.toURI().toString());
        spc.setImageview(image1);
        spc.setPath(sp.getPetById(returnedId).getPet_image());
//        spc.setTypeValue(sp.getPetById(returnedId).getType());
//        spc.setSex(sp.getPetById(returnedId).getSex());
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        ButtonType foo = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to delete your profile permanently \n Type YES to confirm your action",
                foo,
                bar);

        alert.setTitle("Information Dialog");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == foo) {
            ServicePet sp = new ServicePet();
            sp.DeletePetById(returnedId);
        }
    }
    
}
