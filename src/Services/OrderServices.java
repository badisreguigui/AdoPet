/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.Order;
import Entities.Produit;
import Entities.User;
import Outils.DataSource;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hiba
 */
public class OrderServices {
     Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res,res1;
   int countimage;
    ObservableList<Order> data3;
    private static OrderServices instance;
    
    public static OrderServices getInstance()
    {
        if (instance ==null)
        {
            instance=new OrderServices();
        }
        return instance;
    }
    
    
    public void insert(Order o) 
     {
         DataSource d=DataSource.getInstance();
         String req="INSERT INTO `order`(`User_login`, `Price`, `Date`) VALUES (?,?,sysdate())";
         
            PreparedStatement ste;
        try {
            ste = d.getConnection().prepareStatement(req);
            ste.setString(1,o.getLogin());
            ste.setInt(2,o.getPrice());
            
           
           ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Probleme de connexion");
            System.out.println(ex.getMessage());
        }
        
     }
    
    
    
    public static List<Order> show()
     {
         List<Order> l=new ArrayList();
         DataSource d=DataSource.getInstance();
         
         
         String req="select* from  `order`";
         
         
        try {
            
            Statement ste=d.getConnection().createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
               
               int order_id=result.getInt("Order_Id");
               String user_login=result.getString("User_login");
               int price=result.getInt("Price");
               Date date=result.getDate("Date");
               
               User u=new User();
               l.add(new Order(order_id,price,date,user_login));
           }
            
        } catch (SQLException ex) {
            System.err.println("probleme");
        }
         
         
         
         
       return l;  
     }
    public ObservableList<Order> DisplayOrders() throws SQLException{
        String requete = "SELECT `Order_Id`, `User_login`, `Price`, `Date` FROM `order` order by Order_Id";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data3 = FXCollections.observableArrayList();
         
        while(res.next())
        {
            Order e = new Order(res.getInt(3),res.getDate(4),res.getString(2));
           data3.add(e);
            
        }
        return data3;
        
        
    }
}
