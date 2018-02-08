/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Produit;
import Outils.DataSource;
import java.sql.*;


/**
 *
 * @author Kapio
 */
public class ProduitService {
    Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res;

    public ProduitService() throws SQLException {
        ste=con.createStatement();        
    }
    
     public void insert(Produit r) throws SQLException{
         
        String requete = "insert into produit(nomproduit,description,prix,quantite,imageproduit,nomcategorie,nomraceproduit,nomboutiqueproduit) values (?,?,?,?,?,?,?,?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomproduit());
        st.setString(2, r.getDescription());
        st.setInt(3, r.getPrix());
        st.setInt(4, r.getQuantite());
        st.setString(5, r.getImageproduit());
        st.setString(6, r.getNomcategorie());
        st.setString(7, r.getNomraceporduit());
        st.setString(8, r.getNomboutiqueproduit());
        st.executeUpdate();
    }
     
     public void Delete(int id) throws SQLException{
        String requete = "Delete from produit where idproduit =" +id ;
        PreparedStatement st=con.prepareStatement(requete);
        st.executeUpdate(requete);
    }
     
     public void DisplayAll() throws SQLException{
        String requete = "Select * from produit";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        while(res.next())
        {
            System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4)+" "+res.getInt(5)+" "+res.getString(6)+" "+res.getString(7)+" "+res.getString(8)+" "+res.getString(9));
            
        }
        
        
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
        st.setString(7, r.getNomraceporduit());
        st.setString(8, r.getNomboutiqueproduit());
        st.setInt(9, r.getIdproduit());
        
        st.executeUpdate();
        
    }
}
