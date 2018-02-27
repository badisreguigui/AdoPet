/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class LoginController implements Initializable {

    @FXML
    private TextField logintxt;
    @FXML
    private TextField passtxt;
    @FXML
    private Button loginbtn;
    
    Session session = Session.getInstance();
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        UserService us= new UserService();
        User u = new User(logintxt.getText(), passtxt.getText());
        ObservableList log= us.Select(u);
        if(log.isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Invalid ");
        alert.show();
        }
        else if(log.size()==1 && !logintxt.getText().equals("admin")&& !passtxt.getText().equals("admin")) {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));
           Parent root = loader.load();
            ShopController spc = loader.getController();
            logintxt.getScene().setRoot(root);
            session.addSession(u);
            System.out.println(session.p.toString());
        }
        else if(log.size()==1 && logintxt.getText().equals("admin")&& passtxt.getText().equals("admin")) {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("boutcateg.fxml"));
           Parent root = loader.load();
            BoutcategController spc = loader.getController();
            logintxt.getScene().setRoot(root);
            session.addSession(u);
            System.out.println(session.p.toString());
        }
    }
    
    
}
