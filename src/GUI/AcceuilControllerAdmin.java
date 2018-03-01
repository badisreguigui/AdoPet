/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class AcceuilControllerAdmin implements Initializable {
    @FXML
    private AnchorPane acc;
    @FXML
    private JFXHamburger mmain;
    @FXML
    private Label welcome;
    @FXML
    private TextArea desc;
    @FXML
    private JFXDrawer menu;
    
    Session session = Session.getInstance();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(session.IdSession());
        try{
            
            VBox box = FXMLLoader.load(getClass().getResource("Menuu_1.fxml"));
            desc.setEditable(false);
            //hamburger setting
            HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(mmain); 
            transition.setRate(-1);
            mmain.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            //drawer setting
            menu.setSidePane(box);
            if (menu.isShown()){
                menu.close();
            }else{
                menu.open();
            }
        });
        } catch (IOException ex) {
            Logger.getLogger(AcceuilControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
