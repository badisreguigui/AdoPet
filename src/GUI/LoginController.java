/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private AnchorPane log;
    @FXML
    private JFXButton ajout;
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                                        log.setStyle("-fx-background-image:url('file:///C:/Users/Kapio/Desktop/adopet2.jpg')");

        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        UserService us= new UserService();
        User u = new User(logintxt.getText(), passtxt.getText());
        ObservableList<User> log= us.Select(u);
        //System.out.println(log.toString());
        if(log.isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Invalid ");
        alert.show();
        }
        else if(log.size()==1 && !logintxt.getText().equals("admin")&& !passtxt.getText().equals("admin")) {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
           Parent root = loader.load();
           AcceuilController spc = loader.getController();
            logintxt.getScene().setRoot(root);
                session.addSession(log.get(0));
            
            System.out.println(session.p.toString());
        }
        else if(log.size()==1 && logintxt.getText().equals("admin")&& passtxt.getText().equals("admin")) {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil_1.fxml"));
           Parent root = loader.load();
            AcceuilControllerAdmin sp = loader.getController();
            logintxt.getScene().setRoot(root);
            session.addSession(log.get(0));
            System.out.println(session.p.toString());
        }
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        System.out.println("ok");
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutVeto.fxml"));
                    Parent root5;
                    root5 = loader.load();
                    AjoutVetoController sp=loader.getController();
                    logintxt.getScene().setRoot(root5);

    }
    
    
}
