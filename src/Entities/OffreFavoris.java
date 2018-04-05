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
public class OffreFavoris {
    private int id_user;
    private int idoffre;
    private int idofav;

    public OffreFavoris() {
    }
    
    
    public OffreFavoris(int id_user, int idoffre, int idofav) {
        this.id_user = id_user;
        this.idoffre = idoffre;
        this.idofav = idofav;
    }

    public int getId_user() {
        return id_user;
    }

    public int getIdoffre() {
        return idoffre;
    }

    public int getIdofav() {
        return idofav;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    public void setIdofav(int idofav) {
        this.idofav = idofav;
    }
    
    

    
    
    
    
}
