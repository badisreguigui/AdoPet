/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pet;
import Entities.adoption;
import Service.ServiceAdoption;
import Service.ServicePet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Voiture v1= new Voiture("BMW","noire");
        //ServiceVoiture sv=new ServiceVoiture();
        
        Pet p1 = new Pet("qqqqq",4,"qqqqqq","eee","eeee","eeee",4,"eeeee","eeee");
        adoption a1 = new adoption(1,3);
        ServicePet sp = new ServicePet();
        ServiceAdoption sa = new ServiceAdoption();
        
        try {
            //sp.AjouterPet(p1);
            //System.out.println("Pet added");
            //sp.DeletePet(2);
            //System.out.println("Pet deleted"); 
            sa.AjouterAdoption(a1);
            sp.UpdatePet(p1, 3);
            sp.displayAll().forEach(System.out::println);
            sa.displayAlladop().forEach(System.out::println);
        } catch (SQLException ex) {
            Logger.getLogger(Pi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
