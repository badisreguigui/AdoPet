/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
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
 * @author mac
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
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane.setStyle("-fx-background-image: url('images/adopet1.jpg');");
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        UserService us= new UserService();
        User u = new User(logintxt.getText(), passtxt.getText());
        ObservableList log= us.Select(u);
        //System.out.println(log.toString());
        if(log.isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Invalid ");
        alert.show();
        }
        else if(log.size()==1 && !logintxt.getText().equals("admin")&& !passtxt.getText().equals("admin")) {
            for (Iterator it = log.iterator(); it.hasNext();) {
                User a = (User) it.next();
                session.addSession(a);
            }
            
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdd.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGmaps.fxml"));
           Parent root = loader.load();
//            FXMLAddController spc = loader.getController();
            FXMLMenuController spc = loader.getController();
//            FXMLDisplayAllController spc = loader.getController();
//            FXMLGmapsController spc = loader.getController();
            logintxt.getScene().setRoot(root);
//            tem.out.println(session.p.toString());
        }
        else if(log.size()==1 && logintxt.getText().equals("admin")&& passtxt.getText().equals("admin")) {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDisplayAll.fxml"));
           Parent root = loader.load();
            FXMLDisplayAllController spc = loader.getController();
            logintxt.getScene().setRoot(root);
            session.addSession(u);
            System.out.println(session.p.toString());
        }
    }
    
}
