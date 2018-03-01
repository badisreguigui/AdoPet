/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author TESNIME
 */
public class Rdv {
    private int id;
    private int id_veto;
    private int id_user;
    private Date date_rdv;
    private int heure;

    public Rdv() {
    }

    public Rdv(int id_veto, int id_user, Date date_rdv, int heure) {
        this.id_veto = id_veto;
        this.id_user = id_user;
        this.date_rdv = date_rdv;
        this.heure = heure;
    }
    

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_veto() {
        return id_veto;
    }

    public void setId_veto(int id_veto) {
        this.id_veto = id_veto;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }

    @Override
    public String toString() {
        return "Rdv{" + "id=" + id + ", id_veto=" + id_veto + ", id_user=" + id_user + ", date_rdv=" + date_rdv + '}';
    }
    
    
    
}
