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
 * @author LENOVO
 */
public class Datasource {
    private static Datasource data;
    private Connection con;
    public String user="root";
    public String password="";
    public String url="jdbc:mysql://localhost:3306/projet";
    
    private Datasource()
    {
        try {
            con=DriverManager.getConnection(url, user, password);
            System.out.println("Connection etablie");
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static Datasource getInstance()
    {
        if(data==null)
            data=new Datasource();
        return data;
    }
    public Connection getConnection()
    {
        return con;
    }
}
