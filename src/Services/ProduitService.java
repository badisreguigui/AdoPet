/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Categorie;
import Entities.Order;
import Entities.Produit;
import Outils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Kapio
 */
public class ProduitService {
    Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res,res1;
   int countimage;
    ObservableList<Produit> data;
    ObservableList<Order> data3;
    ObservableList<String> data1;
     ObservableList<String> data2;

    public ProduitService() throws SQLException {
        ste=con.createStatement();        
    }
    
     public void insert(Produit r) throws SQLException, FileNotFoundException{
 
        String requete = "insert into produit(nomproduit,description,prix,quantite,imageproduit,nomcategorie,nomraceproduit,nomboutiqueproduit) values (?,?,?,?,?,?,?,?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomproduit());
        st.setString(2, r.getDescription());
        st.setInt(3, r.getPrix());
        st.setInt(4, r.getQuantite());
 st.setString(5, r.getImageproduit());
        st.setString(6, r.getNomcategorie());
        st.setString(7, r.getNomraceproduit().name());
        st.setString(8, r.getNomboutiqueproduit());
        st.executeUpdate();
    }
  
     
     public void Delete(int id) throws SQLException{
        String requete = "Delete from produit where idproduit =" +id ;
        PreparedStatement st=con.prepareStatement(requete);
        st.executeUpdate(requete);
    }
     
     public ObservableList<Produit> DisplayAll() throws SQLException{
        String requete = "Select * from produit order by idproduit";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data = FXCollections.observableArrayList();
         
        while(res.next())
        {
            Produit.race r= Produit.race.valueOf(res.getString(8));
            Produit e = new Produit(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),r,res.getString(9));
           data.add(e);
            
        }
        return data;
        
        
    }
     public void Update(Produit r) throws SQLException{
         
        String requete = "Update produit set nomproduit=?,description=?,prix=?,quantite=?,imageproduit=?,nomcategorie=?,nomraceproduit=?,nomboutiqueproduit=? where idproduit = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomproduit());
        st.setString(2, r.getDescription());
        st.setInt(3, r.getPrix());
        st.setInt(4, r.getQuantite());
        st.setString(5, r.getImageproduit());
        st.setString(6, r.getNomcategorie());
        st.setString(7, r.getNomraceproduit().name());
        st.setString(8, r.getNomboutiqueproduit());
        st.setInt(9, r.getIdproduit());
        
        st.executeUpdate();
        
    }
     public ResultSet Select(Produit p) throws SQLException{
         
        String requete = "Select * from produit where idproduit = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setInt(1, p.getIdproduit());
        
        res=st.executeQuery();
        
        return res;
    }
     public ResultSet SelectbyNom(String nom) throws SQLException{
         
        String requete = "Select * from produit where nomproduit='"+nom+"'";
        PreparedStatement st=con.prepareStatement(requete);      
        res=st.executeQuery();
        
        return res;
    }
     public Produit SelectbyName(String nompr) throws SQLException{
         Produit p = new Produit();
        String requete = "Select * from produit where nomproduit='"+nompr+"'";
        PreparedStatement st=con.prepareStatement(requete);      
        res=st.executeQuery();
        
        while (res.next())
        {
            
          int id=res.getInt(1);
          String nom = res.getString(2);
          String desc = res.getString(3);
          int prix= res.getInt(4);
          int quantite=res.getInt(5);
          String image=res.getString(6);
          String categ= res.getString(7);
          
          Produit.race r = Produit.race.valueOf(res.getString(8));
          String bout= res.getString(9);
          p.setIdproduit(id);
          p.setNomproduit(res.getString(2));
          p.setDescription(res.getString(3));
          p.setPrix(res.getInt(4));
          p.setQuantite(res.getInt(5));
          p.setImageproduit(res.getString(6));
          p.setNomcategorie(res.getString(7));
          p.setNomraceproduit(r);
          p.setNomboutiqueproduit(res.getString(9));
          
        }
        return p;
    }
     
     
     public ObservableList<String> selectcateg() throws SQLException{
         String requete = "Select nomcategorie from categorie";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
         data1 = FXCollections.observableArrayList();
         
        while(res.next())
        {
            
           data1.add(res.getString(1));
            
        }
        return data1;
        }
     public ObservableList<String> selectcategByID(Produit p) throws SQLException{
         String requete = "Select nomcategorie from produit where idproduit= ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setInt(1, p.getIdproduit());
         data1 = FXCollections.observableArrayList();
         res=st.executeQuery();
         String requete1 = "Select nomcategorie from categorie";
        ste=con.createStatement();
        res1=ste.executeQuery(requete1);
        while(res.next())
        {
            
           data1.add(res.getString(1));
           data1.add("Choisir Autre Catégorie :");
            while (res1.next()) {    
                
                data1.add(res1.getString(1));
            }
           //data1.add("Catégories Disponibles");
           
           
            
        }
        return data1;
}
     
     public int SelectImage() throws SQLException{
         int count=0;
        String requete = "SELECT COUNT(imageproduit)FROM produit";
        PreparedStatement st=con.prepareStatement(requete);        
        res=st.executeQuery();
        if(res.next()){
        countimage=res.getInt(1);
         count=countimage;
        }
      return count;
    }
     
     public ResultSet shop() throws SQLException{
         String requete = "Select nomproduit,imageproduit,idproduit,prix,nomraceproduit,quantite from produit order by idproduit desc";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     public ResultSet shopcateg(String nomcat) throws SQLException{
         String requete = "Select nomproduit,imageproduit,idproduit from produit where nomcategorie='"+nomcat+"'";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     public ResultSet shopRace(String race) throws SQLException{
         String requete = "Select nomproduit,imageproduit,idproduit from produit where nomraceproduit='"+race+"'";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     public ResultSet shopBoutique(String bout) throws SQLException{
         String requete = "Select nomproduit,imageproduit,idproduit from produit where nomraceproduit='"+bout+"'";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     
     public ResultSet unProduit(int id) throws SQLException{
         
        String requete = "Select * from produit where idproduit ="+id;
        PreparedStatement st=con.prepareStatement(requete);
        res=st.executeQuery();
        
        return res;
    }
     
     public int countP() throws SQLException{
         int i=0;
         String requete = "select COUNT(*) from produit";
         ste=con.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
             i=res.getInt(1);
            return i;
     }
     
     public ResultSet listcateg() throws SQLException{
         String requete = "Select nomcategorie from categorie";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     public ResultSet stats() throws SQLException{
         String requete = "SELECT * FROM `order` where `Price` > 10000 ORDER BY `Date` LIMIT 3";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     
     public void rate(int id,double rate,int iduser) throws SQLException{
         String requete = "Insert into rate(idpr,rating,iduser) values (?,?,?)";
         PreparedStatement st=con.prepareStatement(requete);
        st.setInt(1, id);
        st.setDouble(2, rate);
        st.setInt(3, iduser);
        st.executeUpdate();
     }
     
     
     public ResultSet displayRate(int id) throws SQLException{
         String requete ="SELECT AVG(rating) FROM `rate` where idpr='"+id+"'";
         ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
      public ResultSet displayRate1(int id) throws SQLException{
         String requete ="SELECT Round(AVG(rating)) FROM `rate` where idpr='"+id+"'";
         ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
      
      public ObservableList<String> selectboutByID(Produit p) throws SQLException{
         String requete = "Select nomboutiqueproduit from produit where idproduit= ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setInt(1, p.getIdproduit());
         data1 = FXCollections.observableArrayList();
         res=st.executeQuery();
         String requete1 = "Select nomboutique from boutique";
        ste=con.createStatement();
        res1=ste.executeQuery(requete1);
        while(res.next())
        {
            
           data1.add(res.getString(1));
           data1.add("Choisir Autre Boutique :");
            while (res1.next()) {    
                
                data1.add(res1.getString(1));
            }
           //data1.add("Catégories Disponibles");
           
           
            
        }
        return data1;
}
   public void updatequantite(int id,int quantity) throws SQLException
     {            
         String requete = "UPDATE produit  SET "
                                        +"quantite='"+quantity
                                        +"'WHERE `idproduit`="+id ;
        PreparedStatement st=con.prepareStatement(requete);

        
        st.executeUpdate();  
         
     }
public Produit searchById(int id) throws SQLException
     {
         Produit p=new Produit();
         
         String req="select * from produit where idproduit='"+id+"';";
         PreparedStatement st=con.prepareStatement(req);
         try {
                        
           ResultSet res= ste.executeQuery(req);
           
            
            
           while(res.next()){
               
          int id1=res.getInt(1);
          String nom = res.getString(2);
          String desc = res.getString(3);
          int prix= res.getInt(4);
          int quantite=res.getInt(5);
          String image=res.getString(6);
          String categ= res.getString(7);
          
          Produit.race r = Produit.race.valueOf(res.getString(8));
          String bout= res.getString(9);
          p.setIdproduit(id1);
          p.setNomproduit(res.getString(2));
          p.setDescription(res.getString(3));
          p.setPrix(res.getInt(4));
          p.setQuantite(res.getInt(5));
          p.setImageproduit(res.getString(6));
          p.setNomcategorie(res.getString(7));
          p.setNomraceproduit(r);
          p.setNomboutiqueproduit(res.getString(9));
           }
            
        } catch (SQLException ex) {
            System.err.println("probleme"+ex.getMessage());
        }
         return p;
     }


}
