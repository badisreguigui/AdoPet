/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Veto;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TESNIME
 */
public class ServiceVeto {
    ResultSet rs;
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    ObservableList<Veto> data;

    public ServiceVeto() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void AjouterVeto( Veto v) throws SQLException
    {
        String req="INSERT INTO users(nom,prenom,email,telephone,rue,ville,gouvernorat,codePostal,image,description,role,login,password,currentDate)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setString(1, v.getNom());
        prs.setString(2, v.getPrenom());
        prs.setString(3,v.getEmail());
        prs.setInt(4, v.getTelephone());
        prs.setString(5,v.getRue());
        prs.setString(6,v.getVille());
        prs.setString(7,v.getGouvernorat());
        prs.setInt(8,v.getCodePostal());
        prs.setString(9,v.getImage());
        prs.setString(10,v.getDescription());
        prs.setString(11,v.getRole());
        prs.setString(12,v.getLogin());
        prs.setString(13,v.getPassword());
        prs.setDate(14,v.getCurrentDate());
        prs.executeUpdate();
    }
    
    public void deleteVeto(String nom) throws SQLException
    {
        String requete="delete from users where nom='"+nom+"'";
        ste=con.createStatement();
        ste.executeUpdate(requete);
    }
    
  
    public ObservableList<Veto> displayAll() throws SQLException
    {
        String requete="select * from users";
       ste=con.createStatement();
       rs=ste.executeQuery(requete);
       data = FXCollections.observableArrayList();
       while(rs.next()){
           Veto v=new Veto(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));
           data.add(v);
       }
       return data;

    }
    public void updateVeto(Veto v,String nom) throws SQLException{
        String rq="update users set nom='"+v.getNom()+"' ,prenom= '"+v.getPrenom()+"' ,email='"+v.getEmail()+"' ,telephone='"+v.getTelephone()+"',rue='"+v.getRue()+"' ,ville='"+v.getVille()+"' ,gouvernorat='"+v.getGouvernorat()+"' ,codePostal='"+v.getCodePostal()+"' ,image='"+v.getImage()+"', description='"+v.getDescription()+"',role='"+v.getRole()+"',login='"+v.getLogin()+"',password='"+v.getPassword()+"' where nom='"+nom+"' ";
        ste=con.createStatement();
        ste.execute(rq);
    }
    
    public ResultSet shop() throws SQLException{
         String requete = "Select * from users order by id desc";
            ste=con.createStatement();
            rs=ste.executeQuery(requete);
            return rs;
     }
    
   

      public Veto getVeto( String nom) throws SQLException
    {
        String requet="select * from users where nom='"+nom+"'";
        ste=con.createStatement();
        rs=ste.executeQuery(requet);
        while(rs.next())
        {
           Veto v=new Veto(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));

            return v;
        }
        return new Veto();
    }
      
      public Veto getVetobyId( int id) throws SQLException
    {
        String requet="select * from users where id='"+id+"'";
        ste=con.createStatement();
        rs=ste.executeQuery(requet);
        while(rs.next())
        {
           Veto v=new Veto(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));

            return v;
        }
        return new Veto();
    }
}
