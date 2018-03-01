/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Veto;
import Services.ServiceVeto;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author TESNIME
 */
public class AdminController implements Initializable {

    
     private ObservableList ok;
    @FXML
    private TableView<Veto> tableVeto;
    @FXML
    private TableColumn<Veto,Integer> idcolumn;
    @FXML
    private TableColumn<Veto,String> nomcolumn;
    @FXML
    private TableColumn<Veto,String> prenomcolumn;
    @FXML
    private TableColumn<Veto,Integer> telcolumn;
    @FXML
    private TableColumn<Veto,String> villecolumn;
    @FXML
    private TableColumn<Veto,Integer> codepostalcolumn;
    @FXML
    private TableColumn<Veto,String> imagecolumn;
    @FXML
    private TableColumn<Veto,String> desccolumn;
    @FXML
    private TableColumn<Veto,String> mailcolumn;
    @FXML
    private TableColumn<Veto,String> ruecolumn;
    @FXML
    private TableColumn<Veto,String> gouvernoratcolumn;
    @FXML
    private Button delete;
    @FXML
    private TextField rechercheid;
    @FXML
    private AnchorPane back;
    @FXML
    private Label title;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            back.setStyle("-fx-background-image: url('images/adopet1.jpg');");
            title.setStyle("-fx-text-fill:orange");
delete.setStyle(
                
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        File file3 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\1.png");
        //Image image = new Image(file.toURI().toString());
        String pt3 = file3.toURI().toString();
        Image image3 = new Image(pt3, 75 ,75, false, false);
        delete.setGraphic(new ImageView(image3));
        delete.setText("");

            loadVeto();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            checkDate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    private void loadVeto() throws SQLException {
       ServiceVeto vs =new ServiceVeto();
             ok=FXCollections.observableArrayList();
                ok=vs.displayAll();
                tableVeto.setItems(null);
        tableVeto.setItems(ok);
         idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telcolumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        ruecolumn.setCellValueFactory(new PropertyValueFactory<>("rue"));
        villecolumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        gouvernoratcolumn.setCellValueFactory(new PropertyValueFactory<>("gouvernorat"));
        codepostalcolumn.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        imagecolumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        desccolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Veto v = tableVeto.getSelectionModel().getSelectedItem();
        ServiceVeto sv = new ServiceVeto();
        sv.deleteVeto(v.getNom());
        loadVeto();
    }
    public void checkDate() throws SQLException{
        
           ServiceVeto sv = new ServiceVeto();
            ResultSet res = sv.shop();
            while(res.next()){
                    System.out.println(res.getInt("id"));
                    java.sql.Date dbSqlDate = res.getDate("currentDate");                
                    String[] separated = dbSqlDate.toString().split(" ");
                    String date = separated[0];

                    String[] separated_date = date.split("-");

                    String strYear = separated_date[0];
                    String strMonth = separated_date[1];
                    String strDate = separated_date[2];

                    System.out.println(strYear);
                    System.out.println(strMonth);
                    System.out.println(strDate);
                    
                    //getcurrent time
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date datelyoum = new Date();
                    System.out.println(dateFormat.format(datelyoum));
                    
                    
                    String[] separated2 = dateFormat.format(datelyoum).split(" ");
                    String datee = separated2[0];

                    String[] separated_datelyoum = datee.split("/");

                    String strYearl = separated_datelyoum[0];
                    String strMonthl = separated_datelyoum[1];
                    String strDatel = separated_datelyoum[2];

                    System.out.println(strYearl);
                    System.out.println(strMonthl);
                    System.out.println(strDatel);
                    
                    if(Integer.parseInt(strDatel)>= Integer.parseInt(strDate))
                    if(Integer.parseInt(strMonth) < 9){
                        if (Integer.parseInt(strMonthl) < Integer.parseInt(strMonth) + 3) {
                            System.out.println("fqsqkh");
                        }
                    }else {
                        int diff = 12 - Integer.parseInt(strMonth);
                        int chharaamjdid = 3-diff;
                        int newyear = Integer.parseInt(strYear) +1;
                        if ((Integer.parseInt(strMonthl) > chharaamjdid) && (newyear == Integer.parseInt(strYearl))) {
                            System.out.println("fasakh");
                        }
                        
                    }


                
            }
    }

    @FXML
    private void recherche(ActionEvent event) {
        
        rechercheid.setOnKeyPressed(new EventHandler<KeyEvent>(){                
        // methode bch n desabli el enter 5ater enter traja3li display l 3adi, confli m3a enter mte3 autocomplete    
        @Override
        public void handle(KeyEvent event1){
                if (!event1.getCode().equals(KeyCode.ENTER)){
                                    FilteredList<Veto> filteredData = new FilteredList<>(ok, e-> true);
        
                                    rechercheid.textProperty().addListener((observable, oldValue, newValue) -> {
                                        filteredData.setPredicate(Veto -> {
                                            if (newValue == null || newValue.isEmpty()) {
                                                return true;
                                            }

                                            String lowerCaseFilter = newValue.toLowerCase();     


                                            if (Veto.getNom().toLowerCase().contains(lowerCaseFilter)) {
                                                return true;
                                            }
                                            return false; // Does not match.


                                        });

                                    });

                                    SortedList<Veto> sortedData = new SortedList<>(filteredData);
                                    sortedData.comparatorProperty().bind(tableVeto.comparatorProperty());
                                    tableVeto.setItems(sortedData); 
                                                    }
                }
        });
    }
}
