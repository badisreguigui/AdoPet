/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Veto;
import Services.ServiceVeto;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */

public class DisplayVetoController implements Initializable {

    private Image image;
    @FXML
    private AnchorPane back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    back.setStyle("-fx-background-image: url('images/adopet3.jpg');");

        //back.getScene().getWindow().hide();
        shop();
        
        
        // TODO
    }   
    
    public  VBox createpage() throws SQLException{
          VBox vbox5 = new VBox();
          Label top = new Label("LES 3 VETOS MOINS CHERS");

            vbox5.setPadding(new javafx.geometry.Insets(10));
            vbox5.setSpacing(8);
            
                Image image1 = new Image("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\edit3.png");
                ImageView imageView1 = new ImageView(image);
                imageView1.setFitHeight(20);
                imageView1.setFitWidth(200);
                Button btn = new Button();
                btn.setGraphic(imageView1);
                btn.setTranslateX(50);
         
        ServiceVeto vs = new ServiceVeto();
        ResultSet rs =vs.displaytop3();
        
         while(rs.next()){
                System.out.println("ook");
                image = new Image("file:///"+rs.getString(11));
                System.out.println(rs.getString(11));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                Label label1 = new Label();
                label1.setGraphic(imageView);
                label1.setTranslateX(50);
                Label label2 = new Label();
                label2.setText(rs.getString(3));
                label2.setMaxWidth(Double.MAX_VALUE);
                label2.setAlignment(Pos.CENTER);
                label2.setTranslateY(10);
                label2.setTranslateX(50);
                Label label3 = new Label();
                label3.setText(rs.getString(4));
                label3.setTranslateX(50);
                //label3.setVisible(false);
                label3.setMaxWidth(Double.MAX_VALUE);
                label3.setAlignment(Pos.CENTER);
                label3.setTranslateY(10);
                Label label4 = new Label();
                label4.setText(rs.getString(12));
                //label4.setTranslateX(50);
                //label4.setVisible(false);
                label4.setMaxWidth(Double.MAX_VALUE);
                label4.setAlignment(Pos.BOTTOM_RIGHT);
                label4.setTranslateY(40);
                label4.setTranslateX(20);
                HBox vbox1 = new HBox();
                vbox1.setPadding(new javafx.geometry.Insets(10));
                vbox1.setSpacing(8);
                vbox1.getChildren().addAll(label1,label2,label3,label4);
                vbox1.setStyle("-fx-background-color : rgba(241,247,250,0.7)");
                vbox1.setSpacing(5);
                //vbox1.setTranslateX(50);
                
                
                
                int a =rs.getInt(1);
                
                label1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
                            Parent root5 = loader.load();
                            ProfilController spc = loader.getController();
                            spc.setId(a);
                            spc.setNomlabel(label2.getText());
                            spc.setPrenomlabel(label3.getText());
                           spc.setDesclabel(label4.getText());
                            String path= vs.getVetobyId(a).getImage();
                            System.out.println(path);
                            spc.setImageView(new Image("file:///"+path));
                            //spc.afficheP(Integer.parseInt(label3.getText()));
                            
           Scene sc = new Scene(root5);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
                    }
                         catch (IOException ex) {
                            Logger.getLogger(DisplayVetoController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                        Logger.getLogger(DisplayVetoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
         event.consume();
     });
                
                        
                         vbox5.getChildren().add(top);

         vbox5.getChildren().add(vbox1);
                
            }
         
        
        
        
        ////////////////////////////////////////all veto////////////////////////////////////
          
        VBox tilePane = new VBox();
        Label tous = new Label("Tous les Vétos");
        tilePane.setSpacing(5);
        tilePane.getChildren().add(vbox5);
        tilePane.getChildren().add(tous);

        tilePane.setStyle("-fx-background-image: url('images/adopet3.jpg');");
            //tilePane.setHgap(50);
            //tilePane.setVgap(50);
            tilePane.setPadding(new javafx.geometry.Insets(50, 5, 5, 5));
        ServiceVeto sv = new ServiceVeto();
            ResultSet res = sv.shop();
            while(res.next()){
                System.out.println("ook");
                image = new Image("file:///"+res.getString(10));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                Label label1 = new Label();
                label1.setGraphic(imageView);
                label1.setTranslateX(50);
                Label label2 = new Label();
                label2.setText(res.getString(2));
                label2.setMaxWidth(Double.MAX_VALUE);
                label2.setAlignment(Pos.CENTER);
                label2.setTranslateY(10);
                label2.setTranslateX(50);
                Label label3 = new Label();
                label3.setText(res.getString(3));
                label3.setTranslateX(50);
                //label3.setVisible(false);
                label3.setMaxWidth(Double.MAX_VALUE);
                label3.setAlignment(Pos.CENTER);
                label3.setTranslateY(10);
                Label label4 = new Label();
                label4.setText(res.getString(11));
                //label4.setTranslateX(50);
                //label4.setVisible(false);
                label4.setMaxWidth(Double.MAX_VALUE);
                label4.setAlignment(Pos.BOTTOM_RIGHT);
                label4.setTranslateY(40);
                label4.setTranslateX(20);
                HBox vbox1 = new HBox();
                vbox1.setPadding(new javafx.geometry.Insets(10));
                vbox1.setSpacing(8);
                vbox1.getChildren().addAll(label1,label2,label3,label4);
                vbox1.setStyle("-fx-background-color : rgba(241,247,250,0.7)");
                vbox1.setSpacing(5);
                //vbox1.setTranslateX(50);
                
                tilePane.getChildren().add(vbox1);
                
                System.out.println(res.getInt(1));
                
                int a =res.getInt(1);
                
                label1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        //System.out.println(Integer.parseInt(label3.getText()));
                        //
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
                            Parent root5 = loader.load();
                            ProfilController spc = loader.getController();
                            spc.setId(a);
                            spc.setNomlabel(label2.getText());
                            spc.setPrenomlabel(label3.getText());
                            spc.setDesclabel(label4.getText());
                            String path= sv.getVetobyId(a).getImage();
                            System.out.println(path);
                            spc.setImageView(new Image("file:///"+path));
                            //spc.afficheP(Integer.parseInt(label3.getText()));
                            
           Scene sc = new Scene(root5);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
                    }
                         catch (IOException ex) {
                            Logger.getLogger(DisplayVetoController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                        Logger.getLogger(DisplayVetoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
         event.consume();
     });
                
                
                
                
            }
            return tilePane;
    }
            
    public void shop(){
        try {
            // TODOtry {
            ScrollPane root = new ScrollPane();
            BorderPane root1 = new BorderPane();
            //root1.setStyle("-fx-background-image:url('file:/C:/Users/TESNIME/Desktop/adopet3.jpg')");
            root1.setStyle("-fx-background-image: url('images/adopet3.jpg');");
            root.setStyle("-fx-background-color: rgba(109,207,246,1)"+"-fx-background-size 1400 768");

                            
          
            
            root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Horizontal scroll bar
            root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
            root.setFitToHeight(true);
            root.setFitToWidth(true);
            root.setContent(createpage());
            root1.setCenter(root);
            
            Scene sc = new Scene(root1, 1360, 768);
           
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayVetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              
                   
    }
  

    
}
