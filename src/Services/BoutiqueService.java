/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Boutique;
import Outils.DataSource;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
/**
 *
 * @author Kapio
 */
public class BoutiqueService {
    Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res;
   ObservableList<Boutique> data;
   ObservableList<String> data1;

    public BoutiqueService() throws SQLException {
        ste=con.createStatement();        
    }
    
     public void insert(Boutique r) throws SQLException{
        String requete = "insert into Boutique(nomboutique,telboutique,adresseboutique,imageboutique) values (?,?,?,?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getNomboutique());
        st.setInt(2, r.getTelboutique());
        st.setString(3, r.getAdresseboutique());
        st.setString(4, r.getImageboutique());
        st.executeUpdate();
    }
     
     public void Delete(int id) throws SQLException{
        String requete = "Delete from boutique where idboutique =" +id ;
        PreparedStatement st=con.prepareStatement(requete);
        st.executeUpdate(requete);
    }
     
     public ObservableList<Boutique> DisplayAll() throws SQLException{
        String requete = "Select * from boutique";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data = FXCollections.observableArrayList();
        while(res.next())
        {
            Boutique b = new Boutique(res.getInt(1),res.getString(2),res.getInt(3),res.getString(4),res.getString(5));
            data.add(b);
        }
        
        return data;
        
    }
     public void Update(Boutique p) throws SQLException{
        String requete = "Update boutique set nomboutique=?,telboutique=?,adresseboutique=?,imageboutique=? where idboutique = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getNomboutique());
        st.setInt(2, p.getTelboutique());
        st.setString(3, p.getAdresseboutique());
        st.setString(4, p.getImageboutique());
        st.setInt(5, p.getIdboutique());
        st.executeUpdate();
        
    }
     
     public ResultSet Select(Boutique p) throws SQLException{
         
        String requete = "Select * from boutique where idboutique = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setInt(1, p.getIdboutique());
        
        res=st.executeQuery();
        
        return res;
    }
     
     public ObservableList<String> selectbout() throws SQLException{
         String requete = "Select nomboutique from boutique";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
         data1 = FXCollections.observableArrayList();
         
        while(res.next())
        {
            
           data1.add(res.getString(1));
            
        }
        return data1;
        }
     
     
}