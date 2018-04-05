/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Matching;
import Outils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class ServiceMatching {
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    ResultSet rs;

    public ServiceMatching() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterMatching (Matching m) throws SQLException
    {
        String req="INSERT INTO Matching (id_user, id_pet, matching)VALUES(?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setInt(1, m.getId_user());   
        prs.setInt(2, m.getId_pet()); 
        prs.setInt(3, m.getMatching());   
        prs.executeUpdate();
    }
    
    public List<Integer> returnMatchedPetsOfUser(int userId) throws SQLException{
        String requete="SELECT id_pet FROM matching where id_user=" + userId;
        //String requete ="SELECT * FROM pet where (id_pet IN (SELECT id_pet FROM matching)) && (user_id not in (2))";
        List<Integer> list= new ArrayList<>();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            list.add(rs.getInt(1));
        }
        return list;
    }
    
    
//    public Pet getPetAdo(int id) throws SQLException{
//        String requete="select * from pet where id_pet = '"+id+"' ";
//        ste=con.createStatement();
//        rs = ste.executeQuery(requete);
//        while (rs.next()) {            
//            Pet p = new Pet(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getDouble("lat"), rs.getDouble("lng"));
//            return p;
//        }
//        
//        return new Pet();
//    }
    
}
