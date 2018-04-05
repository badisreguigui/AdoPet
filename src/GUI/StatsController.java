/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ProduitService;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Kapio
 */
public class StatsController implements Initializable {

    @FXML
    private BarChart<Integer, Date> barchart;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            XYChart.Series<Integer,Date> series = new XYChart.Series<>();
            ProduitService ps = new ProduitService();
            ResultSet res = ps.stats();
            while(res.next())
            {
                series.getData().add(new XYChart.Data<>(res.getInt(3),res.getDate(4)));
                
            }
            barchart.getData().add(series);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
