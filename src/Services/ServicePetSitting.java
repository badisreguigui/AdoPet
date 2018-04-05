package Services;
import Entities.DemandeFavoris;
import Entities.DemandeSitting;
import Entities.OffreFavoris;
import Entities.OffreSitting;
import java.sql.*;
import Outils.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emna
 */

public class ServicePetSitting {
    Connection con=DataSource.getInstance().getConnection();
    public Statement ste;
    
    public ServicePetSitting(){
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePetSitting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //CRUD Offre
    public void ajouterOffre(OffreSitting o) throws SQLException{
        String req="INSERT INTO OffreSitting (nourriture, visite, description, chat, chien, autre, promenade, lieu, num_tel, date_debut_dispo, date_fin_dispo, prix_demande, tmp_debut, tmp_fin, titre, id_user) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setBoolean(1, o.isNourriture());
        prs.setBoolean(2, o.isVisite());
        prs.setString(3, o.getDescription());
        prs.setBoolean(4, o.isChat());
        prs.setBoolean(5, o.isChien());
        prs.setBoolean(6, o.isAutre());
        prs.setBoolean(7, o.isPromenade());
        prs.setString(8, o.getLieu());
        prs.setInt(9, o.getNum_tel());
        prs.setString(10, String.valueOf(o.getDate_debut_dispo()));
        prs.setString(11, String.valueOf(o.getDate_fin_dispo()));
        prs.setFloat(12, o.getPrix_demande());
        prs.setTime(13, o.getTmp_debut());
        prs.setTime(14, o.getTmp_fin());   
        prs.setString(15, o.getTitre());
        prs.setInt(16, o.getId_user());
        prs.executeUpdate();
    }
    
    public ObservableList<OffreSitting> afficherOffres() throws SQLException{
        ObservableList<OffreSitting> offres = FXCollections.observableArrayList();
        ResultSet rst = ste.executeQuery("select * from OffreSitting");
        while(rst.next())
        {
                OffreSitting of = new OffreSitting(rst.getInt ("id_offre"),  rst.getString("titre"), rst.getBoolean("nourriture"), rst.getBoolean("visite"), rst.getString("description"), rst.getBoolean("chat"), rst.getBoolean("chien"), rst.getBoolean("autre"), rst.getBoolean("promenade"), rst.getString("lieu"), rst.getInt("num_tel"), rst.getDate("date_debut_dispo").toLocalDate(), rst.getDate("date_fin_dispo").toLocalDate(), rst.getInt("prix_demande"), rst.getTime("tmp_debut"), rst.getTime("tmp_fin"), rst.getInt("id_user"));
                offres.add(of);
        }
        return offres; 
    }
    
    public OffreSitting afficherOffre(Integer i) throws SQLException{
        String query = "SELECT * FROM OffreSitting WHERE id_offre=\'"+ i + "\' ";
        ResultSet rst = ste.executeQuery(query);
        OffreSitting of = new OffreSitting();
        while(rst.next())
        {
            of = new OffreSitting(rst.getString("titre"), rst.getBoolean("nourriture"), rst.getBoolean("visite"), rst.getString("description"), rst.getBoolean("chat"), rst.getBoolean("chien"), rst.getBoolean("autre"), rst.getBoolean("promenade"), rst.getString("lieu"), rst.getInt("num_tel"), rst.getDate("date_debut_dispo").toLocalDate(), rst.getDate("date_fin_dispo").toLocalDate(), rst.getInt("prix_demande"), rst.getTime("tmp_debut"), rst.getTime("tmp_fin"));

        }
        return of;
    }
    
    public void modifierOffre(OffreSitting o) throws SQLException{
        String req="Update OffreSitting set nourriture=?, visite=?, description=?, chat=?, chien=?, autre=?, promenade=?, lieu=?, num_tel=?, date_debut_dispo=?, date_fin_dispo=?, prix_demande=?, tmp_debut=?, tmp_fin=?, titre=? where id_offre=?";
        PreparedStatement prs=con.prepareStatement(req); 
        prs.setBoolean(1, o.isNourriture());
        prs.setBoolean(2, o.isVisite());
        prs.setString(3, o.getDescription());
        prs.setBoolean(4, o.isChat());
        prs.setBoolean(5, o.isChien());
        prs.setBoolean(6, o.isAutre());
        prs.setBoolean(7, o.isPromenade());
        prs.setString(8, o.getLieu());
        prs.setInt(9, o.getNum_tel());
        prs.setString(10, String.valueOf(o.getDate_debut_dispo()));
        prs.setString(11, String.valueOf(o.getDate_fin_dispo()));
        prs.setFloat(12, o.getPrix_demande());
        prs.setTime(13, o.getTmp_debut());
        prs.setTime(14, o.getTmp_fin());   
        prs.setString(15, o.getTitre());
        prs.setInt(16, o.getId_offre());
        prs.executeUpdate();
    }
    
    public void supprimerOffre(Integer i) throws SQLException{
        String req="Delete from OffreSitting where id_offre=?";
        PreparedStatement prs=con.prepareStatement(req); 
        prs.setInt(1, i);
        prs.executeUpdate();
    }
    
    
    //CRUD demande
    
    public void ajouterDemande(DemandeSitting d) throws SQLException{
        String req="INSERT INTO Demandesitting (nourriture, visite, description, chat, chien, autre, promenade, lieu, num_tel, date_debut_demande, date_fin_demande, prix_souhaite, tmp_debut, tmp_fin, titre, id_user) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setBoolean(1, d.isNourriture());
        prs.setBoolean(2, d.isVisite());
        prs.setString(3, d.getDescription());
        prs.setBoolean(4, d.isChat());
        prs.setBoolean(5, d.isChien());
        prs.setBoolean(6, d.isAutre());
        prs.setBoolean(7, d.isPromenade());
        prs.setString(8, d.getLieu());
        prs.setInt(9, d.getNum_tel());
        prs.setString(10, String.valueOf(d.getDate_debut_demande()));
        prs.setString(11, String.valueOf(d.getDate_fin_demande()));
        prs.setFloat(12, d.getPrix_souhaite());
        prs.setTime(13, d.getTmp_debut());
        prs.setTime(14, d.getTmp_fin());   
        prs.setString(15, d.getTitre());
        prs.setInt(16, d.getId_user());
        prs.executeUpdate();
    }
    
    public void afficherDemande(Integer i) throws SQLException{
        String query = "SELECT * FROM DemandeSitting WHERE id_demande=\'" + i + "\' ";
        ResultSet rst = ste.executeQuery(query);
         
        while(rst.next())
        {
             System.out.print(rst.getInt("id_demande")+"\t"); 
             System.out.print(rst.getBoolean("Nourriture")+"\t");
             System.out.print(rst.getBoolean("visite")+"\t");
             System.out.print(rst.getString("description")+"\t");
             System.out.print(rst.getBoolean("chat")+"\t");
             System.out.print(rst.getBoolean("chien")+"\t");
             System.out.print(rst.getBoolean("autre")+"\t");
             System.out.print(rst.getBoolean("promenade")+"\t");
             System.out.print(rst.getString("lieu")+"\t"); 
             System.out.print(rst.getInt("num_tel")+"\t");
             System.out.print(rst.getString("date_debut_demande")+"\t"); 
             System.out.print(rst.getString("date_fin_demande")+"\t"); 
             System.out.print(rst.getInt("prix_souhaite")+"\t"); 
             System.out.print(rst.getString("date_fin_demande")+"\t"); 
             System.out.print(rst.getInt("tmp_debut")+"\t");
             System.out.print(rst.getInt("tmp_fin")+"\t");
             System.out.print(rst.getString("titre")+"\t");
             System.out.println(rst.getInt("id_user"));

        }
        
    }
    
    public ObservableList<DemandeSitting> afficherDemandes() throws SQLException{
        ObservableList<DemandeSitting> demandes = FXCollections.observableArrayList();
        ResultSet rst = ste.executeQuery("select * from DemandeSitting");
        while(rst.next())
        {
                DemandeSitting de = new DemandeSitting(rst.getInt("id_demande"), rst.getString("titre"), rst.getBoolean("nourriture"), rst.getBoolean("visite"), rst.getString("description"), rst.getBoolean("chat"), rst.getBoolean("chien"), rst.getBoolean("autre"), rst.getBoolean("promenade"), rst.getString("lieu"), rst.getInt("num_tel"), rst.getDate("date_debut_demande").toLocalDate(), rst.getDate("date_fin_demande").toLocalDate(), rst.getInt("prix_souhaite"), rst.getTime("tmp_debut"), rst.getTime("tmp_fin"), rst.getInt("id_user"));
                demandes.add(de);
        }
        return demandes; 
    }
    
    public void modifierDemande(DemandeSitting o) throws SQLException{
        String req="Update demandesitting set nourriture=?, visite=?, description=?, chat=?, chien=?, autre=?, promenade=?, lieu=?, num_tel=?, date_debut_demande=?, date_fin_demande=?, prix_souhaite=?, tmp_debut=?, tmp_fin=?, titre=? where id_demande=?";
        PreparedStatement prs=con.prepareStatement(req); 
        prs.setBoolean(1, o.isNourriture());
        prs.setBoolean(2, o.isVisite());
        prs.setString(3, o.getDescription());
        prs.setBoolean(4, o.isChat());
        prs.setBoolean(5, o.isChien());
        prs.setBoolean(6, o.isAutre());
        prs.setBoolean(7, o.isPromenade());
        prs.setString(8, o.getLieu());
        prs.setInt(9, o.getNum_tel());
        prs.setString(10, String.valueOf(o.getDate_debut_demande()));
        prs.setString(11, String.valueOf(o.getDate_fin_demande()));
        prs.setFloat(12, o.getPrix_souhaite());
        prs.setTime(13, o.getTmp_debut());
        prs.setTime(14, o.getTmp_fin());   
        prs.setString(15, o.getTitre());
        prs.setInt(16, o.getId_demande());
        prs.executeUpdate();
    }
    
    public void supprimerDemande(Integer i) throws SQLException{
        String req="Delete from DemandeSitting where id_demande=?";
        PreparedStatement prs=con.prepareStatement(req); 
         prs.setInt(1, i);
         prs.executeUpdate();
        
    }
    
    public void ajoutOffreFavori(OffreFavoris o) throws SQLException{
        String req="INSERT INTO Offrefavoris (id_offre, id_user, idofav) VALUES(?,?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setInt(1, o.getIdoffre());
        prs.setInt(2, o.getId_user());
        prs.setInt(3, o.getIdofav());  
    } 
    
    public void ajoutDemandeFavori(DemandeFavoris d) throws SQLException{
        String req="INSERT INTO demandefavoris (id_demande, id_user) VALUES(?,?)";
        PreparedStatement prs=con.prepareStatement(req);
        prs.setInt(1, d.getIddemande());
        prs.setInt(2, d.getId_user());
        prs.executeUpdate();
        
    } 
    
    public ObservableList<OffreFavoris> getOFav() throws SQLException{
        ObservableList<OffreFavoris> offresFav = FXCollections.observableArrayList();
        ResultSet rst = ste.executeQuery("select * from Offrefavoris");
        while(rst.next())
        {
                OffreFavoris off = new OffreFavoris(rst.getInt("id_offre"), rst.getInt("id_user"), rst.getInt("idofav"));
                offresFav.add(off);
        }
        return offresFav; 
    }
    
    public ObservableList<DemandeFavoris> getDFav() throws SQLException{
        ObservableList<DemandeFavoris> demandesFav = FXCollections.observableArrayList();
        ResultSet rst = ste.executeQuery("select * from demandefavoris");
        while(rst.next())
        {
                DemandeFavoris df = new DemandeFavoris( rst.getInt("id_demande"), rst.getInt("id_user"), rst.getInt("iddfav"));
                demandesFav.add(df);
        }
        return demandesFav; 
    }
    
    public ObservableList<DemandeFavoris> getDFavById(int i) throws SQLException{
        ObservableList<DemandeFavoris> demandesFav = FXCollections.observableArrayList();
        ResultSet rst = ste.executeQuery("select * from demandefavoris WHERE id_user=\'" + i + "\' ");
        while(rst.next())
        {
                DemandeFavoris df = new DemandeFavoris( rst.getInt("id_demande"), rst.getInt("id_user"), rst.getInt("iddfav"));
                demandesFav.add(df);
        }
        return demandesFav; 
    }
    
    public ObservableList<OffreFavoris> getOFavById(int i) throws SQLException{
        ObservableList<OffreFavoris> offresFav = FXCollections.observableArrayList();
        ResultSet rst = ste.executeQuery("select * from demandefavoris WHERE id_user=\'" + i + "\' ");
        while(rst.next())
        {
                OffreFavoris df = new OffreFavoris( rst.getInt("id_offre"), rst.getInt("id_user"), rst.getInt("idofav"));
                offresFav.add(df);
        }
        return offresFav; 
    }
    
    public void supprimerDemandeFav(int iduser, int iddemande) throws SQLException{
        String req="Delete from Demandefavoris where id_user=? and id_demande=?";
        PreparedStatement prs=con.prepareStatement(req); 
         prs.setInt(1, iduser);
         prs.setInt(2, iddemande);
         prs.executeUpdate();
        
    }
    
    public void supprimerOffreFav(int iduser, int idoffre) throws SQLException{
        String req="Delete from offrefavoris where id_user=? and id_offre=?";
        PreparedStatement prs=con.prepareStatement(req); 
        prs.setInt(1, iduser);
        prs.setInt(2, idoffre);
        prs.executeUpdate();
    }
    
    public int duplicatDemande(int iduser, int iddemande) throws SQLException{
        String req ="select count(*) from demandefavoris where id_demande = "+ iddemande +" and id_user = " + iduser;
        ResultSet rst = ste.executeQuery(req);
        int a=0;
        while(rst.next()){
            a = rst.getInt(1);
        }
        return a;
    }
    
    public int duplicatOffre(int iduser, int idoffre) throws SQLException{
        String req ="select count(*) from offrefavoris where id_offre = "+ idoffre +" and id_user = " + iduser;
        ResultSet rst = ste.executeQuery(req);
        int a=0;
        while(rst.next()){
            a = rst.getInt(1);          
        }
        return a;
    }
               

}