/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Pet;
import Outils.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

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
        String req="INSERT INTO pet(name_pet,breed,age,sex,color,governate,city,zip,description,pet_image,user_id, lat, lng, typePet, type)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        
        prs.setString(1, p.getName_pet());
        prs.setString(2, p.getBreed());
        prs.setInt(3, p.getAge());
        prs.setString(4, p.getSex());
        prs.setString(5, p.getColor());
        prs.setString(6, p.getGovernate());
        prs.setString(7, p.getCity());
        prs.setInt(8, p.getZip());
        prs.setString(9, p.getDescription());
        prs.setString(10, p.getPet_image());
        prs.setInt(11, p.getUser_id());
        prs.setDouble(12, p.getLat());
        prs.setDouble(13, p.getLng());   
        prs.setString(14, p.getTypePet());
        prs.setString(15, p.getType());
        
        prs.executeUpdate();
    }
    
    public void UpdatePet (Pet p, String name) throws SQLException{
        String rq="update pet set name_pet='"+p.getName_pet()+"' ,breed='"+p.getBreed()+"',age='"+p.getAge()+"', sex='"+p.getSex()+"', color='"+p.getColor()+"',governate='"+p.getGovernate()+"', city='"+p.getCity()+"', zip='"+p.getZip()+"',description='"+p.getDescription()+"',pet_image='"+p.getPet_image()+"' where name_pet='"+name+"' ";
        ste=con.createStatement();
        ste.execute(rq);
    }
    
    public void UpdatePetById (Pet p, int id) throws SQLException{
        String rq="update pet set name_pet='"+p.getName_pet()+"' ,breed='"+p.getBreed()+"',age='"+p.getAge()+"', sex='"+p.getSex()+"', color='"+p.getColor()+"',governate='"+p.getGovernate()+"', city='"+p.getCity()+"', zip='"+p.getZip()+"',description='"+p.getDescription()+"',pet_image='"+p.getPet_image()+"', lat='"+p.getLat()+"', lng='"+p.getLng()+"', typePet='"+p.getTypePet()+"', type='"+p.getType()+"' where id_pet="+id;
        ste=con.createStatement();
        ste.execute(rq);
    }
    
    public List<Pet> displayAll() throws SQLException{
//        String requete="SELECT * FROM 	pet WHERE user_id not in (2)";

        // not display matched pet or created by user 2
        String requete = "SELECT * FROM pet where (id_pet NOT IN (select id_pet from matching where id_user = 2) && user_id != 2)";
        //String requete ="SELECT * FROM pet where (id_pet IN (SELECT id_pet FROM matching)) && (user_id not in (2))";
        List<Pet> list= new ArrayList<>();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11));
            list.add(p);
        }
        return list;
    }
    
    public List<Pet> displayAllAdo(int id) throws SQLException{
        String requete="SELECT * FROM 	pet WHERE user_id !="+id;
        //String requete ="SELECT * FROM pet where (id_pet IN (SELECT id_pet FROM matching)) && (user_id not in (2))";
        List<Pet> list= new ArrayList<>();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12) ,rs.getDouble("lat"),rs.getDouble("lng"), rs.getString("typePet"), rs.getString("type"));
            list.add(p);
        }
        return list;
    }
    
    public Pet getPetById(int id) throws SQLException{
        String requete="select * from pet where id_pet = '"+id+"' ";
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11),rs.getInt(12), rs.getDouble("lat"), rs.getDouble("lng"), rs.getString("typePet"), rs.getString("type"));
            return p;
        }
        
        return new Pet();
    }
    
    public String getPetNameById(int id) throws SQLException{
        String name="";
        String requete="select * from pet where id_pet = '"+id+"' ";
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            name = rs.getString(2);
            return name;
        }
        
        return name;
    }
    
    public Pet getPet(String name) throws SQLException{
        String requete="select * from pet where name_pet = '"+name+"' ";
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11));
            return p;
        }
        
        return new Pet();
    }
    
    
    
    public Pet getPetAdo(int id) throws SQLException{
        String requete="select * from pet where id_pet = '"+id+"' ";
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getDouble("lat"), rs.getDouble("lng"));
            return p;
        }
        
        return new Pet();
    }
    
    public Pet getPetMatching(int id) throws SQLException{
        String requete="select * from pet where id_pet = '"+id+"' ";
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11),rs.getInt(12), rs.getDouble("lat"), rs.getDouble("lng"), rs.getString("typePet"), rs.getString("type"));
            return p;
        }
        
        return new Pet();
    }
    
    public ResultSet showMyProfiles (int id) throws SQLException{
        String requete  = "Select * from pet where user_id =" + id;
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        return rs;
    }

    
    public void DeletePet (String name) throws SQLException{
        String rq="delete from pet where (name_pet = '"+name+"')";
        ste=con.createStatement();
        ste.execute(rq);
    }
    
    public void DeletePetById (int id) throws SQLException{
        String rq="delete from pet where (id_pet = '"+id+"')";
        ste=con.createStatement();
        ste.execute(rq);
    }
    
    // select method for the autocomplete page     
    public String[] getData() throws SQLException{
        ArrayList<String> a = new ArrayList<String>();
        String requete="SELECT * FROM 	pet ";

        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        
        while(rs.next()){
            a.add(rs.getString(1));
            a.add(rs.getString(2));
            a.add(rs.getString(3));
        }
        
        return (String[]) a.toArray(new String[a.size()]);
        
    }
    
    public int returnPetsUser (int id_pet) throws SQLException{
        int idUser = 0;
        String requete = "Select user_id from pet where id_pet =" + id_pet;
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {
            idUser = rs.getInt(1);
        }
            
        return idUser;
    }
    
    
    
//    public String[] returnUsersPets(int userId) throws SQLException{
//        ArrayList<String> a = new ArrayList<String>();
//        String requete="SELECT id_pet FROM pet where user_id=" + userId;
//
//        ste=con.createStatement();
//        rs = ste.executeQuery(requete);
//        
//        while(rs.next()){
//            a.add(rs.getString(1));
//        }
//        
//        return (String[]) a.toArray(new String[a.size()]);
//        
//    }
    
    public List<Integer> returnUsersPets(int userId) throws SQLException{
        String requete="SELECT id_pet FROM pet where user_id=" + userId;
        //String requete ="SELECT * FROM pet where (id_pet IN (SELECT id_pet FROM matching)) && (user_id not in (2))";
        List<Integer> list= new ArrayList<>();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            list.add(rs.getInt(1));
        }
        return list;
    }
    
    
    
    
    
    
    /*
    public ObservableList<Pet> displayAll() throws SQLException{
        String requete="select * from pet";
        ObservableList<Pet> data = FXCollections.observableArrayList();
        ste=con.createStatement();
        rs = ste.executeQuery(requete);
        while (rs.next()) {            
            Pet p = new Pet(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11));
            data.add(p);
        }
        return data;
    }
    */
    
    /*
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
            Pet p = new Pet(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11));
            list.add(p);
        }
        return list;
    }

    public void UpdatePet (Pet p, int id) throws SQLException{
        String rq="update pet set breed='"+p.getBreed()+"',age='"+p.getAge()+"', sex='"+p.getSex()+"', color='"+p.getColor()+"',governate='"+p.getGovernate()+"', city='"+p.getCity()+"',description='"+p.getDescription()+"',pet_image='"+p.getPet_image()+"' where id_pet='"+id+"' ";
        ste=con.createStatement();
        ste.execute(rq);
    }
*/
    
    
    
}
