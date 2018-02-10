/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TESNIME
 */
public class DataSource {
    private static DataSource data;
    private Connection con;
    public String user="root";
    public String password="root";
    public String url="jdbc:mysql://localhost:3306/pi";
    
    
    private DataSource()
    {
        try {
            con=DriverManager.getConnection(url, user, password);
            System.out.println(" connexion etablie");
                    } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataSource getInstance()//pour assurer une seule instance de connexion
    {
        if (data==null)
            data=new DataSource();
        return data;
        
    }
    
    public Connection getConnection(){
        return con;
    }
    
}
