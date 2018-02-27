/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entities.Veto;
import Service.ServiceVeto;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        shop();
        
        // TODO
    }   
    
    public  VBox createpage() throws SQLException{
         
            VBox vbox = new VBox();
            vbox.setPadding(new javafx.geometry.Insets(10));
            vbox.setSpacing(8);
            
        VBox tilePane = new VBox();
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
                Label label2 = new Label();
                label2.setText(res.getString(2));
                label2.setMaxWidth(Double.MAX_VALUE);
                label2.setAlignment(Pos.CENTER);
                label2.setTranslateY(10);
                Label label3 = new Label();
                label3.setText(res.getString(3));
                //label3.setVisible(false);
                label3.setMaxWidth(Double.MAX_VALUE);
                label3.setAlignment(Pos.CENTER);
                label3.setTranslateY(10);
                Label label4 = new Label();
                label4.setText(res.getString(11));
                //label4.setVisible(false);
                label4.setMaxWidth(Double.MAX_VALUE);
                label4.setAlignment(Pos.BOTTOM_RIGHT);
                label4.setTranslateY(40);
                label4.setTranslateX(-30);
                HBox vbox1 = new HBox();
                vbox1.setPadding(new javafx.geometry.Insets(10));
                vbox1.setSpacing(8);
                vbox1.getChildren().addAll(label1,label2,label3,label4);
                
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
            root.setPadding(new javafx.geometry.Insets(10));
            
            // Pane
            /*ProduitService ps = new ProduitService();
           VBox vboxtab = new VBox();
            vboxtab.setPadding(new javafx.geometry.Insets(10));
            vboxtab.setSpacing(8);
            Text title = new Text("Catégories");
            ObservableList ok=FXCollections.observableArrayList();
            ok=ps.selectcateg();
            Text title1 = new Text(ok.toString());
            
            title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            vboxtab.setPrefHeight(100);
            vboxtab.setPrefWidth(100);
            vboxtab.getChildren().addAll(title,title1);*/
           
            
            
            
            root.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Horizontal scroll bar
            root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
            root.setFitToHeight(true);
            root.setFitToWidth(true);
            root.setContent(createpage());
            root1.setCenter(root);
            //root1.setLeft(vboxtab);
            /*HBox hbox = new HBox();
            hbox.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
            hbox.setSpacing(10);            
            Button buttonCurrent = new Button("Current");
            buttonCurrent.setPrefSize(100, 20);
            
            Button buttonProjected = new Button("Projected");
            buttonProjected.setPrefSize(100, 20);
            hbox.getChildren().addAll(buttonCurrent, buttonProjected);
            root1.setTop(hbox);*/
            
            //String css = NewFXMain1.class.getResource("test.css").toString();
            Scene sc = new Scene(root1, 1360, 768);
            //sc.getStylesheets().add(css);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayVetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              
                   
    }
  

    
}
