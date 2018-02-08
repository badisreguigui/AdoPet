/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation;

import Entities.Publication;
import Entities.Commentaire;
import Services.ServicePublication;
import Services.ServiceCommentaire;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */

public class Projet1 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //**************Gestion des publications**************
        
        Publication p1=new Publication("nourriture","probleme avec ..","url",5);
        Publication p2=new Publication(4,"soins","probleme","url",4);
        ServicePublication  sp=new ServicePublication();
        //sp.AjouterPublication(p1);
        //sp.SupprimerPublication(p1);
        //sp.DisplayAll();
        //sp.ModifierPublication(p2);
       
        
        
        
        //**************Gestion des commentaires**************
        
        Commentaire c1=new Commentaire("commentaire..",2,3);
        Commentaire c2=new Commentaire(6,"commentaire à propos..",2,4);
        ServiceCommentaire sc=new ServiceCommentaire();
        //sc.AjouterCommentaire(c1);
        //sc.SupprimerCommentaire(c2);
        //sc.DisplayAll();
        //sc.ModifierCommentaire(c2);

        
        
        
        

        
    }
    
}
