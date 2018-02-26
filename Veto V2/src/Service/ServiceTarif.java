/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.Tarif;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author TESNIME
 */
public class ServiceTarif {
    
    ResultSet rs;
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;//creation de statement
    ObservableList<Tarif> data;
    final ObservableList data1= FXCollections.observableArrayList();

    public ServiceTarif() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void AjouterTarif( Tarif t) throws SQLException
    {
        String req="INSERT INTO tarif(id_veto,consultation,vaccinationChat,vaccinationChien,sterilisation,analyses)VALUES(?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setInt(1,t.getId_veto());
        prs.setInt(2,t.getConsultation());
        prs.setInt(3,t.getVaccinationChat());
        prs.setInt(4,t.getVaccinationChien());
        prs.setInt(5,t.getSterilisation());
        prs.setInt(6,t.getAnalyses());
   
        prs.executeUpdate();
    }
    
//    public void deleteTarif(int id_tarif) throws SQLException
//    {
//        String requete="delete from tarif where id_tarif='"+id_tarif+"'";
//        ste=con.createStatement();
//        ste.executeUpdate(requete);
//    }
    
    public ObservableList<Tarif> displayAll() throws SQLException
    {
        String requete="select * from tarif";
        List<Tarif> list=new ArrayList<>();
       ste=con.createStatement();
       rs=ste.executeQuery(requete);
       data = FXCollections.observableArrayList();
       while(rs.next()){
           Tarif t=new Tarif(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
           data.add(t);
       }
       return data;

    }
    public void updateTarif(Tarif t,int id_veto) throws SQLException{
        String rq="update tarif set consultation='"+t.getConsultation()+"' ,vaccinationChat='"+t.getVaccinationChat()+"' ,vaccinationChien='"+t.getVaccinationChien()+"',sterilisation='"+t.getSterilisation()+"',analyses='"+t.getAnalyses()+"' where id_veto='"+id_veto+"' ";
        ste=con.createStatement();
        ste.execute(rq);
    }
    
     public Tarif getTarifByVeto( int id_veto) throws SQLException
    {
        String requet="select * from tarif where id_veto='"+id_veto+"'";
        ste=con.createStatement();
        rs=ste.executeQuery(requet);
        while(rs.next())
        {
            Tarif t= new Tarif(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
            return t;
        }
        return new Tarif();
    }
    
    
    public Tarif getTarif( int id_tarif) throws SQLException
    {
        String requet="select * from tarif where id_tarif='"+id_tarif+"'";
        ste=con.createStatement();
        rs=ste.executeQuery(requet);
        while(rs.next())
        {
            Tarif t= new Tarif(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
            return t;
        }
        return new Tarif();
    }
    public ObservableList<String> combo()
    {
        try {
            String query="SELECT type FROM tarif";
            ste=con.createStatement();
            rs=ste.executeQuery(query);
            while(rs.next())
            {
                data1.add(rs.getString("type"));
            }
            ste.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTarif.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data1;
    }
    
}
