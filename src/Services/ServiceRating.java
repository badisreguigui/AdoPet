/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Rateme;
import Entities.adoption;
import Outils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class ServiceRating {
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    ResultSet rs;

    public ServiceRating() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterRating( Rateme r) throws SQLException
    {
        String req="INSERT INTO rating(rating,id_pet,id_user)VALUES(?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        
        prs.setDouble(1, r.getRateme());
        prs.setInt(2, r.getId_pet());
        prs.setInt(3, r.getId_user());
        
        prs.executeUpdate();
    }
    
    public int checkIfRated(int idUser, int idPet) throws SQLException {
        int ratingCheck = 0;
        String requete = "SELECT COUNT(id_rating) FROM rating WHERE id_user= "+idUser+" && id_pet="+idPet;
//        String requete = "SELECT COUNT(id_rating) FROM rating WHERE id_user=2 && id_pet=123";

        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()){
            ratingCheck = rs.getInt(1); 
        }
        return ratingCheck;
    }
    
    
    public int nbr5stars (int id) throws SQLException{
        int n = 0;
        String req = "SELECT COUNT(id_rating) FROM rating WHERE id_pet = "+id+" && rating = 5;";   
        ste=con.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {            
            n = rs.getInt(1);
        }       
        return n;
    }
    
    public int nbr4stars (int id) throws SQLException{
        int n = 0;
        String req = "SELECT COUNT(id_rating) FROM rating WHERE id_pet = "+id+" && rating = 4;";   
        ste=con.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {            
            n = rs.getInt(1);
        }       
        return n;
    }
    
    public int nbr3stars (int id) throws SQLException{
        int n = 0;
        String req = "SELECT COUNT(id_rating) FROM rating WHERE id_pet = "+id+" && rating = 3;";   
        ste=con.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {            
            n = rs.getInt(1);
        }       
        return n;
    }
    
    public int nbr2stars (int id) throws SQLException{
        int n = 0;
        String req = "SELECT COUNT(id_rating) FROM rating WHERE id_pet = "+id+" && rating = 2;";   
        ste=con.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {            
            n = rs.getInt(1);
        }       
        return n;
    }
    
    public int nbr1stars (int id) throws SQLException{
        int n = 0;
        String req = "SELECT COUNT(id_rating) FROM rating WHERE id_pet = "+id+" && rating = 1;";   
        ste=con.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {            
            n = rs.getInt(1);
        }       
        return n;
    }
    
    
}
