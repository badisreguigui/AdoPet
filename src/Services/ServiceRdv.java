/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Rdv;
import Outils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TESNIME
 */
public class ServiceRdv {
     ResultSet rs;
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    
    public ServiceRdv() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void AjouterTarif( Rdv r) throws SQLException
    {
        String req="INSERT INTO rdv(id,id_veto,id_user,date_rdv,heure)VALUES(?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setInt(1,r.getId());
        prs.setInt(2,r.getId_veto());
        prs.setInt(3,r.getId_user());
        prs.setDate(4,r.getDate_rdv());
        prs.setInt(5,r.getHeure());
   
        prs.executeUpdate();
    }
    public int getdate(int id_veto,Date date_rdv,int heure) throws SQLException
    {
        int d=0;
        String req="select count(id) from rdv where id_veto="+id_veto+" && date_rdv='"+date_rdv+"' && heure="+heure+" ";
        ste=con.createStatement();
        rs=ste.executeQuery(req);
        while(rs.next())
        {
            d=rs.getInt(1);
        }
        return d;
        
    }
     
    
    
}
