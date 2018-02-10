/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Pet;
import Utils.DataSource;
import java.sql.*;
import java.sql.Connection;
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
public class ServicePet {
    
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    ResultSet rs;

    public ServicePet() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void AjouterPet( Pet p) throws SQLException
    {
        String req="INSERT INTO pet(breed,age,sex,color,governate,city,zip,description,pet_image)VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        
        prs.setString(1, p.getBreed());
        prs.setInt(2, p.getAge());
        prs.setString(3, p.getSex());
        prs.setString(4, p.getColor());
        prs.setString(5, p.getGovernate());
        prs.setString(6, p.getCity());
        prs.setInt(7, p.getZip());
        prs.setString(8, p.getDescription());
        prs.setString(9, p.getPet_image());
        
        prs.executeUpdate();
    }
    
    
    public void DeletePet (int id) throws SQLException{
        String rq="delete from pet where (id_pet = "+id+")";
        ste=con.createStatement();
        ste.execute(rq);
    }
    
    
    public List<Pet> displayAll() throws SQLException{
        String requete="select * from pet";
        List<Pet> list= new ArrayList<>();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            list.add(p);
        }
        return list;
    }
    
    public void UpdatePet (Pet p, int id) throws SQLException{
        String rq="update pet set breed='"+p.getBreed()+"',age='"+p.getAge()+"', sex='"+p.getSex()+"', color='"+p.getColor()+"',governate='"+p.getGovernate()+"', city='"+p.getCity()+"',description='"+p.getDescription()+"',pet_image='"+p.getPet_image()+"' where id_pet='"+id+"' ";
        ste=con.createStatement();
        ste.execute(rq);
    }

    
    
    
}
