/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import Entities.Commentaire;
import Entities.Publication;
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
     
    private PreparedStatement pst;
    private ResultSet rs;
    ResultSet res;
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
        prs.setInt(3,v.getIdpub());
        prs.executeUpdate();
    }
    public void SupprimerCommentaire(int id) throws SQLException{
        String req = "Delete from commentaires where idcom ="+id;
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
      public ResultSet affichage(int id) throws SQLException{
         String requete = "Select * from commentaires where idpub="+id;
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
    public void DisplayAll(int id) throws SQLException{
        String req = "Select * from commentaires where idpub="+id;
        ste=con.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next())
        {
            System.out.println(res.getString(2)+" "+res.getInt(3));
            
        }
   }    
       public Commentaire afficherCommentaire(int id) {
        String requete = "select * from publications where (commentaires.idcom=?) ";
        Commentaire pp = null;
        try {
            pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {            
                pp = new Commentaire(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4));
                System.out.println(pp.toString());
            }
            System.out.println("Affichage terminé !");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pp;
    }
    
}
