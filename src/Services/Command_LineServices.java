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
import Entities.Command_Line;
import Entities.Order;
import Entities.Produit;
import Entities.User;
import Outils.DataSource;

/**
 *
 * @author Hiba
 */
public class Command_LineServices {
    
    private static Command_LineServices instance;
    
    public static Command_LineServices getInstance()
    {
        if (instance ==null)
        {
            instance=new Command_LineServices();
        }
        return instance;
    }
    
    public void insert(Command_Line c) 
     {
         DataSource d=DataSource.getInstance();
         String req="insert into `command_line` values(?,?,?,?)";
         
            PreparedStatement ste;
        try {
            ste = d.getConnection().prepareStatement(req);
        
            ste.setInt(1,c.getLine_id());
            ste.setInt(2,c.getOrder().getOrder_id());
            ste.setInt(3,c.getProduct().getIdproduit());
            ste.setInt(4,c.getQuantity());
            
           
           ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Probleme de connexion"+ex.getMessage());
        }
        
     }
    
     /*public static List<Command_Line> show()
     {
         List<Command_Line> l=new ArrayList();
         DataSource d=DataSource.getInstance();
         
         
         String req="select* from  `command_line`";
         
         
        try {
            
            Statement ste=d.getConnection().createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
               
               int line_id=result.getInt("Line_Id");
               int order_id=result.getInt("Order_Id");
               int product_id=result.getInt("Poduct_Id");
               int quantity=result.getInt("Quantity");
               
               
               Order o=new Order(order_id);
               Produit p=new Produit(product_id);
               l.add(new Command_Line(line_id,quantity,o,p));
           }
            
        } catch (SQLException ex) {
            System.err.println("probleme"+ex.getMessage());
        }
       return l;
}
     */
     
      public void delete(int line_id)
     {
         DataSource d=DataSource.getInstance();
         
         String req="delete from `command_line` where Line_Id='"+line_id+"' ";
                 
        try {
            
            Statement ste=d.getConnection().createStatement();
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            System.err.println("Probleme de connexion");
            System.out.println(ex.getMessage());
        }  
         
         
     }
      
      
     public static void update(int quantity,int line_id)
     {
         DataSource d=DataSource.getInstance();
         
         
         String req="UPDATE `command_line`  SET "
                                        +"Quantity='"+quantity
                                        +"'WHERE `Line_Id`="+line_id ;
         
        try {
            
            Statement ste=d.getConnection().createStatement();
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            System.err.println("Probleme de connexion");
            System.out.println(ex.getMessage());
        }       
         
     }  
     
    
}
