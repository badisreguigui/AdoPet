/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Session;
import Entities.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
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
        ServiceUser us= new ServiceUser();
        User u = new User(logintxt.getText(), passtxt.getText());
        ObservableList<User> log= us.Select(u);
//        System.out.println(log.get(0).getId());
        //System.out.println(log.toString());
        if(log.isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Invalid ");
        alert.show();
        }
        
        else if(log.size()==1 ) {
            session.addSession(log.get(0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagefront.fxml"));
           Parent root = loader.load();
            
            logintxt.getScene().setRoot(root);
            
            
        }
    }
    
    
}
