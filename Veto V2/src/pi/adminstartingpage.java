/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author TESNIME
 */
public class adminstartingpage extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       // Parent root= FXMLLoader.load(getClass().getResource("AjoutVeto.fxml"));
       Parent root= FXMLLoader.load(getClass().getResource("admin.fxml"));
    // Parent root= FXMLLoader.load(getClass().getResource("DisplayVeto.fxml"));
        //Parent root= FXMLLoader.load(getClass().getResource("FormulaireBudget.fxml"));
     
        //Parent root= FXMLLoader.load(getClass().getResource("Profil.fxml"));
        
        Scene scene = new Scene(root, 1360, 768);
        
        primaryStage.setTitle("les vetos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}