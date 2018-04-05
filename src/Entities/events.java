/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author achref kh
 */
public class events {

    private int id;
    private String nom;
    private String description;
    private String type;
    private Date datedebut;
    private Date datefin;
    private int nbrParticipants;
    private String organisateur;
    private String status;
    private String lieu;

    //AddEvent
    public events(String nom, String description, String type, Date datedebut, Date datefin, int nbrParticipants, String organisateur, String lieu) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrParticipants = nbrParticipants;
        this.organisateur = organisateur;
        this.lieu = lieu;
    }
    //DisplayAllEvents
    public events(int id, String nom, String description, String type, Date datedebut, Date datefin, int nbrParticipants, String organisateur, String lieu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrParticipants = nbrParticipants;
        this.organisateur = organisateur;
        this.lieu = lieu;
    }

    //DisplayMyEvents
    public events(int id, String nom, String description, String type, Date datedebut, Date datefin, String status, int nbrParticipants, String lieu) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;      
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.nbrParticipants = nbrParticipants;
        this.status = status;
    }
    
    //UpdateEvent
    public events(String nom, String type, String description, String lieu, Date datedebut, Date datefin) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.lieu = lieu;
    }

    public events(int id, String nom, String type, Date datedebut, String organisateur, String lieu) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.datedebut = datedebut;
        this.organisateur = organisateur;
        this.lieu = lieu;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final events other = (events) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "events{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type=" + type + ", datedebut=" + datedebut + ", datefin=" + datefin + ", nbrParticipants=" + nbrParticipants + ", organisateur=" + organisateur + ", status=" + status + '}';
    }

}
