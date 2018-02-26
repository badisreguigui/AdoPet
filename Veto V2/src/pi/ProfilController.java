/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entities.Rdv;
import Service.ServiceRdv;
import Service.ServiceVeto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class ProfilController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label desclabel;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prenomlabel;
    @FXML
    private Button rdvbtn;
    @FXML
    private Button budgetbtn;
    private int id;
    @FXML
    private DatePicker dateP;
    @FXML
    private ComboBox<String> combo;
    
    
    
    

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(Image image) {
        this.imageView.setImage(image);
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Label getNomlabel() {
        return nomlabel;
    }

    public void setNomlabel(String value) {
        this.nomlabel.setText(value);
    }

    public Label getDesclabel() {
        return desclabel;
    }

    public void setDesclabel(String value) {
        this.desclabel.setText(value);
    }

    public Label getPrenomlabel() {
        return prenomlabel;
    }

    public void setPrenomlabel(String value) {
        this.prenomlabel.setText(value);
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            
            combo.getItems().addAll("9",
                    "10",
                    "11",
                    "12",
                    "14",
                    "15",
                    "16",
                    "17");
            
            
            
    }    

    @FXML
    private void AfficherFormulaireBudget(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormulaireBudget.fxml"));
        Parent root=loader.load();
        FormulaireBudgetController fbc = loader.getController();
        fbc.setIdprix(id);
        nomlabel.getScene().setRoot(root);
        System.out.println(id);
    }

    @FXML
    private void datepicker(ActionEvent event) {
        
        
    }

    @FXML
    private void prendreRdv(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SORRY");
        Date date = java.sql.Date.valueOf(dateP.getValue());
        int heure=Integer.parseInt(combo.getValue());

        ServiceRdv sr=new ServiceRdv();
     
         Rdv r =new Rdv(id,1,(java.sql.Date) date,heure);
        try {
//            System.out.println("======");
//            System.out.println(id);
//            System.out.println(date);
           // System.out.println(sr.getdate(id, (java.sql.Date) date),heure);
            if(sr.getdate(id, (java.sql.Date) date, heure)>=1)
            {
                alert.setContentText("sorry this day is already reserved");
                alert.showAndWait();
            }
            else{
            sr.AjouterTarif(r);
            
            //////////sms////////
            
            
             try {
			// Construct data
			String apiKey = "apikey=" + "meiJ+twjeI0-185kLRXG85PkHSTSV9B6NwiReae1de";
			//String message = "&message=" + "vous avez pris un rendez-vous le"+date+"à"+heure+" soyez le bienvenu";
                        String message = "&message=" + "vous avez pris un rendez-vous le 29/02/2018 à 14h";

			String sender = "&sender=" + "AdoPet";
			String numbers = "&numbers=" + "0021658928428";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, "message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
			//return "Error "+e;
                        JOptionPane.showMessageDialog(null, e);
		}
            
            
            
            
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    
}
