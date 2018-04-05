/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Pet;
import Entities.adoption;
import Outils.DataSource;
import java.sql.*;
import java.sql.Connection;
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
public class ServiceAdoption {
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    ResultSet rs;

    public ServiceAdoption() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterAdoption( adoption a) throws SQLException
    {
        String req="INSERT INTO adoption(id_user, id_pet)VALUES(?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        
        prs.setInt(1, a.getId_user());
        prs.setInt(2, a.getId_pet());
        
        prs.executeUpdate();
    }
    
    
    public List<adoption> displayAlladop() throws SQLException{
        String requete="select * from adoption";
        List<adoption> list= new ArrayList<>();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            adoption a = new adoption(rs.getInt(2), rs.getInt(3));
            list.add(a);
        }
        return list;
    }
    
}
