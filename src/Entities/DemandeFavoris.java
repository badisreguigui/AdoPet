/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author emna
 */
public class DemandeFavoris {
    
    private int id_user;
    private int iddemande;
    private int iddfav;

    public int getId_user() {
        return id_user;
    }

    public int getIddemande() {
        return iddemande;
    }

    public int getIddfav() {
        return iddfav;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    public void setIddfav(int iddfav) {
        this.iddfav = iddfav;
    }

    public DemandeFavoris() {
    }

    public DemandeFavoris(int id_user, int iddemande) {
        this.id_user = id_user;
        this.iddemande = iddemande;
    }

    public DemandeFavoris(int id_user, int iddemande, int iddfav) {
        this.id_user = id_user;
        this.iddemande = iddemande;
        this.iddfav = iddfav;
    }
    
    
    
   
    
}
