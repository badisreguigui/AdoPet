/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import Entities.Categorie;
import Outils.DataSource;
import java.sql.*;

/**
/**
 *
 * @author Kapio
 */
public class CategorieService {
    Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res;

    public CategorieService() throws SQLException {
        ste=con.createStatement();        
    }
    
     public void insert(Categorie r) throws SQLException{
        String requete = "insert into categorie(nomcategorie,imagecategorie,nomrace) values (?,?,?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomcategorie());
        st.setString(2, r.getImagecategorie());
        st.setString(3, r.getNomrace());
        st.executeUpdate();
    }
     
     public void Delete(int id) throws SQLException{
        String requete = "Delete from categorie where idcategorie =" +id ;
        PreparedStatement st=con.prepareStatement(requete);
        st.executeUpdate(requete);
    }
     
     public void DisplayAll() throws SQLException{
        String requete = "Select * from categorie";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        while(res.next())
        {
            System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getString(4));
            
        }
        
        
    }
     public void Update(Categorie p) throws SQLException{
        String requete = "Update categorie set nomcategorie=?,imagecategorie=?,nomrace=? where idcategorie = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getNomcategorie());
        st.setString(2, p.getImagecategorie());
        st.setString(3, p.getNomrace());
        st.setInt(4, p.getIdcategorie());
        st.executeUpdate();
        
    }
}
