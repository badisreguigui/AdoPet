/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Order;
import Entities.Produit;
import Services.OrderServices;
import Services.ProduitService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class AffichageController implements Initializable {

      @FXML
    private TableView<Order> tableview;
    ObservableList<Produit> ok=FXCollections.observableArrayList();
    ObservableList<Order> ok2=FXCollections.observableArrayList();
    private ObservableList<Order> filteredData = FXCollections.observableArrayList();
    private JFXTextField filterfield;
    @FXML
    private TableColumn<Order, String> login;
    @FXML
    private TableColumn<Order, String> date;
    @FXML
    private TableColumn<?, ?> price;

    /**
     * Initializes the controller class.
     */
    public AffichageController() throws SQLException {
        OrderServices os = new OrderServices();
        ok2=os.DisplayOrders();
        //filteredData.addAll(ok);
        //System.out.println(filteredData);
         ok2.addListener(new ListChangeListener<Order>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Order> change) {
                updateFilteredData();
            }
        });
         
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              // TODO
              OrderServices os = new OrderServices();
              ObservableList ok2=FXCollections.observableArrayList();
              ok2=os.DisplayOrders();
              //tableview.setItems(null);
              //tableview.setItems(ok);
              filteredData.addAll(ok2);
              System.out.println(login);
              //ref.setCellValueFactory(new PropertyValueFactory<>("idproduit"));

              price.setCellValueFactory(new PropertyValueFactory<>("price"));
              login.setCellValueFactory(new PropertyValueFactory<>("login"));
              date.setCellValueFactory(new PropertyValueFactory<>("date"));

              //tableview.setItems(null);
              
              tableview.setItems(filteredData);
              //System.out.println(tableview);
              // Listen for text changes in the filter text field
              filterfield.textProperty().addListener(new ChangeListener<String>() {
                  @Override
                  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                      updateFilteredData();
                      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  }
              });
          } catch (SQLException ex) {
              Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
        
    
    private void updateFilteredData() {
        filteredData.clear();

        for (Order p : ok2) {
            if (matchesFilter(p)) {
                filteredData.add(p);
            }
            
        }

        // Must re-sort table after items changed
        reapplyTableSortOrder();
    }
     private boolean matchesFilter(Order pr) {
        String filterString = filterfield.getText();
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (pr.getLogin().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } 
        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Order, ?>> sortOrder = new ArrayList<>(tableview.getSortOrder());
        tableview.getSortOrder().clear();
        tableview.getSortOrder().addAll(sortOrder);
    }
    
    /*public Produit IdProduit() throws IOException{
        
        return tableview.getSelectionModel().getSelectedItem();
        
    }

    @FXML
    private Produit afficher(MouseEvent event) throws IOException {
        if(tableview.getSelectionModel().getSelectedItem()!=null){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
                    Parent root;
                    root = loader.load();
                    AjoutOrderController sp=loader.getController();
                    filterfield.getScene().setRoot(root);
        }
        return tableview.getSelectionModel().getSelectedItem();
        
    }*/
}
