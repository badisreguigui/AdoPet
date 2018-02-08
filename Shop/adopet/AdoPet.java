/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adopet;
import Entities.Boutique;
import Entities.Categorie;
import Entities.Produit;
import Entities.Race;
import Services.BoutiqueService;
import Services.CategorieService;
import Services.ProduitService;
import Services.RaceService;
import java.sql.SQLException;
/**
 *
 * @author Kapio
 */
public class AdoPet {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Race v1= new Race("Chien","image","zooplus");
        Race v2= new Race("Chiensad","image","zooplus");
      
        RaceService rv = new RaceService();
        //rv.insert(v2);
        //rv.insert(v2);
        //rv.Delete(1);
        //rv.DisplayAll();
        //rv.Update(v1);
        //System.out.println("Added");
        //******************************************************************
        
        Boutique b1=new Boutique("zoo", 21505050,"2 Rue Paris", "image1");
        Boutique b2=new Boutique(2,"zooplus", 21050203,"2 Rue London", "image2");
        BoutiqueService bs=new BoutiqueService();
        //bs.insert(b1);
        //bs.Update(b2);
        //bs.DisplayAll();
        //bs.Delete(1);
        //*******************************************************************
        
        Categorie c1 = new Categorie("Croc", "imagecr", "chien");
        Categorie c2 = new Categorie(3,"Croc", "imaged23", "chien");
        CategorieService cs= new CategorieService();
        //cs.insert(c1);
        //cs.insert(c2);
        //cs.Update(c2);
        //cs.DisplayAll();
        //cs.Delete(3);
        
        //*******************************************************************
        
        Produit p1=new Produit("hadai", "hesdads", 505, 15, "imageProduit","Croc","Chien","zooplus");
        Produit p2=new Produit("haddzadazai", "hesdads", 505, 15, "imageProduit","Croc","Chien","zooplus");
        ProduitService ps = new ProduitService();
        //ps.insert(p1);
        //ps.insert(p2);
        //ps.Update(p1);
        //ps.Delete(1);
        //ps.DisplayAll();
    }   
    
}
