/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Publication;
import Utils.Datasource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class ServicePublication {
    Connection con=Datasource.getInstance().getConnection();
    public Statement ste;
    public ServicePublication()
    {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AjouterPublication(Publication v) throws SQLException
    {
        String req="INSERT INTO PUBLICATIONS (type,description,image,iduser) VALUES (?,?,?,?)";
        PreparedStatement prs=con.prepareCall(req);
        prs.setString(1, v.getType());
        prs.setString(2, v.getDescription());
        prs.setString(3, v.getImage());
        prs.setInt(4, v.getIduser());
        prs.executeUpdate();
    }
    public void SupprimerPublication(Publication p) throws SQLException{
        String req = "Delete from publications where idpub =" +p.getIdpub();
        PreparedStatement prs=con.prepareCall(req);
        prs.executeUpdate();
    }
    public void ModifierPublication(Publication p) throws SQLException{
        String requete = "Update publications set type=?,description=?,image=? where idpub = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getType());
        st.setString(2, p.getDescription());
        st.setString(3, p.getImage());
        st.setInt(4, p.getIdpub());
        st.executeUpdate();
        
    }
    public void DisplayAll() throws SQLException{
        String req = "Select * from publications";
        ste=con.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next())
        {
            System.out.println(res.getString(2)+" "+res.getString(3)+" "+res.getString(4)+" "+res.getInt(5));
            
        }
   }
    
}
