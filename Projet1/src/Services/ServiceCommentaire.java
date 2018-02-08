/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import Entities.Commentaire;
import Utils.Datasource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class ServiceCommentaire {
    Connection con=Datasource.getInstance().getConnection();
    public Statement ste;
    public ServiceCommentaire()
    {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AjouterCommentaire(Commentaire v) throws SQLException
    {
        String req="INSERT INTO COMMENTAIRES (description,iduser,idpub) VALUES (?,?,?)";
        PreparedStatement prs=con.prepareCall(req);
        prs.setString(1, v.getDescription());
        prs.setInt(2, v.getIduser());        
        prs.setInt(3, v.getIdpub());
        prs.executeUpdate();
    }
    public void SupprimerCommentaire(Commentaire c) throws SQLException{
        String req = "Delete from commentaires where idcom =" +c.getIdcom();
        PreparedStatement prs=con.prepareCall(req);
        prs.executeUpdate();
    }
    public void ModifierCommentaire(Commentaire p) throws SQLException{
        String requete = "Update commentaires set description=? where idcom = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getDescription());
        st.setInt(2, p.getIdcom());
        st.executeUpdate();
        
    }  
    public void DisplayAll() throws SQLException{
        String req = "Select * from commentaires";
        ste=con.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next())
        {
            System.out.println(res.getString(2)+" "+res.getInt(3));
            
        }
   }    
    
    
}
