/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import Entities.Race;
import Outils.DataSource;
import java.sql.*;

/**
/**
 *
 * @author Kapio
 */
public class RaceService {
    Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res;

    public RaceService() throws SQLException {
        ste=con.createStatement();        
    }
    
     public void insert(Race r) throws SQLException{
        String requete = "insert into Race(nomrace,imagerace,nomboutique) values (?,?,?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomrace());
        st.setString(2, r.getImagerace());
        st.setString(3, r.getNomboutique());
        st.executeUpdate();
    }
     
     public void Delete(int id) throws SQLException{
        String requete = "Delete from Race where idrace =" +id ;
        PreparedStatement st=con.prepareStatement(requete);
        st.executeUpdate(requete);
    }
     
     public void DisplayAll() throws SQLException{
        String requete = "Select * from Race";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        while(res.next())
        {
            System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getString(4));
            
        }
        
        
    }
     public void Update(Race p) throws SQLException{
        String requete = "Update Race set nomrace=?,imagerace=?,nomboutique=? where idrace = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getNomrace());
        st.setString(2, p.getImagerace());
        st.setString(3, p.getNomboutique());
        st.setInt(3, p.getIdrace());
        st.executeUpdate();
        
    }
}
