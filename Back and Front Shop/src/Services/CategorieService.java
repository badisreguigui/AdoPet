/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import Entities.Boutique;
import Entities.Categorie;
import Outils.DataSource;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
/**
 *
 * @author Kapio
 */
public class CategorieService {
    Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res;
      ObservableList<Categorie> data;


    public CategorieService() throws SQLException {
        ste=con.createStatement();        
    }
    
     public void insert(Categorie r) throws SQLException{
        String requete = "insert into categorie(nomcategorie) values (?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomcategorie());
        st.executeUpdate();
    }
     
     public void Delete(int id) throws SQLException{
        String requete = "Delete from categorie where idcategorie =" +id ;
        PreparedStatement st=con.prepareStatement(requete);
        st.executeUpdate(requete);
    }
     
     public ObservableList<Categorie> DisplayAll() throws SQLException{
        String requete = "Select * from categorie order by idcategorie";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data = FXCollections.observableArrayList();
        while(res.next())
        {
            Categorie b = new Categorie(res.getInt(1),res.getString(2));
            data.add(b);
        }
        
        return data;
        
    }
     public void Update(Categorie p) throws SQLException{
        String requete = "Update categorie set nomcategorie=? where idcategorie = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getNomcategorie());
        st.setInt(2, p.getIdcategorie());
        st.executeUpdate();
        
    }
     public ResultSet Select(Categorie p) throws SQLException{
         
        String requete = "Select * from categorie where idcategorie = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setInt(1, p.getIdcategorie());
        
        res=st.executeQuery();
        
        return res;
    }
}
