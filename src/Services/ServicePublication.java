/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entities.Publication;
import Outils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class ServicePublication {
    Connection con = DataSource.getInstance().getConnection();
    public Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    ResultSet res;
    List<Publication> data2;
     ObservableList<Publication> data;
     ObservableList<String> data1;
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
        String req="INSERT INTO PUBLICATIONS (type,description,image,iduser,date,time,photoName,signall) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareCall(req);
        prs.setString(1, v.getType());
        prs.setString(2, v.getDescription());
        prs.setString(3, v.getImage());
        prs.setInt(4, v.getIduser());
        prs.setString(5, v.getDate());
        prs.setString(6, v.getTime());
        prs.setString(7, v.getPhotoName());
        prs.setString(8,v.getSignall());

        prs.executeUpdate();
    }
    public void SupprimerPublication(Publication p) throws SQLException{
        String req = "Delete from publications where idpub =" +p.getIdpub();
        PreparedStatement prs=con.prepareCall(req);
        prs.executeUpdate();
    }
    public void ModifierPublication(Publication p) throws SQLException{
        String requete = "Update publications set type=?,description=?,image=?,iduser=?,date=?,time=?,photoName=?,signall=? where idpub = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getType());
        st.setString(2, p.getDescription());
        st.setString(3, p.getImage());
        st.setInt(4, p.getIduser());
        st.setString(5, p.getDate());
        st.setString(6, p.getTime());
        st.setString(7, p.getPhotoName());
        st.setString(8, p.getSignall());
        st.setInt(9, p.getIdpub());
        st.executeUpdate();
        
    }
     public ObservableList<Publication> DisplayAll() throws SQLException{
        String requete = "Select * from publications order by idpub";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data = FXCollections.observableArrayList();
        while(res.next())
        {
            Publication b = new Publication(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5));
            data.add(b);
            //System.out.println(res.getInt(1));
        }
        
        return data;
        
    }
       public ObservableList<String> affich() throws SQLException{
        String requete = "Select type,description,image from publications";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data1 = FXCollections.observableArrayList();
        while(res.next())
        {
            data1.add(res.getString(1)+res.getString(2)+res.getString(3));
        }        
        return data1;
       
        
    }
      public List<Publication> DisplayAll2() throws SQLException{
        String requete = "Select * from publications order by idpub";
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        data2=new ArrayList();
        while(res.next())
        {
            Publication b = new Publication(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5));
            data2.add(b);
        }
        
        return data2;
        
    }
      public ResultSet affichage() throws SQLException{
         String requete = "Select * from publications order by idpub DESC";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
     }
     
      public ResultSet affichageDressage(String t1) throws SQLException{
         String requete = "Select * from publications where type='"+t1+"'";
            ste=con.createStatement();
            res=ste.executeQuery(requete);
            return res;
            
     }
      public double countpub(String t) throws SQLException
      {
          String requete="SELECT COUNT(*) FROM publications where type='"+t+"'";
          ste=con.createStatement();
          ResultSet res=ste.executeQuery(requete);
          double x = 0;
          while(res.next())
          {x=res.getDouble(1);
          }
          System.out.println(x);
          return x;
      }
    
    public Publication afficherPublication(int id) {
        String requete = "select * from publications where (publications.idpub=?) ";
        Publication pp = null;
        try {
            pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {            
                pp = new Publication(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6), rs.getString(7));
                System.out.println(pp.toString());
            }
            System.out.println("Affichage terminé !");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pp;
    }
      public Publication afficherPublication2(int id) {
        String requete = "select * from publications where (publications.idpub=?) ";
        Publication pp = null;
        try {
            pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {            
                pp = new Publication(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
                System.out.println(pp.toString());
            }
            System.out.println("Affichage terminé !");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pp;
    }
    public int signal(int idPub,int idUser) throws SQLException{
        String requete="select signall from publications where idpub="+idPub;
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        while (res.next()){
            if (res.getString("signall").contains(","+Integer.toString(idUser)+",")){
                System.out.println("sava ya saif");
                return 0;
            }
        }
        
        requete="update publications set signall=concat(concat(signall,?),',') where idpub="+idPub;
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1,Integer.toString(idUser));
        st.executeUpdate();
        System.out.println("jawi behi");
        
        requete ="SELECT instr(substr(substr(signall,2,length(signall)-2),instr(substr(signall,2,length(signall)-2),',')+1),',') as nbr from publications where idpub="+idPub;
        ste=con.createStatement();
        res=ste.executeQuery(requete);
        Publication p=new Publication(idPub);
        while(res.next()){
            if (res.getInt("nbr")!=0)
                this.SupprimerPublication(p);
            System.out.println("fasakh nayek");
        }
        return 1;
        
    }
    
}
