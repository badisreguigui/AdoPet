package Entities;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author emna
 */
public class DemandeSitting {
    
    private int id_demande;
    private String titre;
    private boolean nourriture;
    private boolean visite;
    private String description; 
    private boolean chat;
    private boolean chien;
    private boolean autre;
    private boolean promenade;
    private String lieu;
    private int num_tel;
    private LocalDate date_debut_demande;
    private LocalDate date_fin_demande;
    private float prix_souhaite;
    private Time tmp_debut;
    private Time tmp_fin;
    private int id_user=0;

    public DemandeSitting() {
    }

    public DemandeSitting(int id_demande, String titre, boolean nourriture, boolean visite, String description, boolean chat, boolean chien, boolean autre, boolean promenade, String lieu, int num_tel, LocalDate date_debut_demande, LocalDate date_fin_demande, float prix_souhaite, Time tmp_debut, Time tmp_fin, int id_user) {
        this.id_demande = id_demande;
        this.titre = titre;
        this.nourriture = nourriture;
        this.visite = visite;
        this.description = description;
        this.chat = chat;
        this.chien = chien;
        this.autre = autre;
        this.promenade = promenade;
        this.lieu = lieu;
        this.num_tel = num_tel;
        this.date_debut_demande = date_debut_demande;
        this.date_fin_demande = date_fin_demande;
        this.prix_souhaite = prix_souhaite;
        this.tmp_debut = tmp_debut;
        this.tmp_fin = tmp_fin;
        this.id_user = id_user;
    }

    //Getters
    
    public int getId_user() {
        return id_user;
    }

    public int getId_demande() {
        return id_demande;
    }

    public boolean isNourriture() {
        return nourriture;
    }

    public boolean isVisite() {
        return visite;
    }

    public String getDescription() {
        return description;
    }

    public boolean isChat() {
        return chat;
    }

    public boolean isChien() {
        return chien;
    }

    public boolean isAutre() {
        return autre;
    }

    public boolean isPromenade() {
        return promenade;
    }

    public String getLieu() {
        return lieu;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public LocalDate getDate_debut_demande() {
        return date_debut_demande;
    }

    public LocalDate getDate_fin_demande() {
        return date_fin_demande;
    }

    public float getPrix_souhaite() {
        return prix_souhaite;
    }

    public Time getTmp_debut() {
        return tmp_debut;
    }

    public Time getTmp_fin() {
        return tmp_fin;
    }

    public String getTitre() {
        return titre;
    }
    
    
    
    //Setters
    

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public void setNourriture(boolean nourriture) {
        this.nourriture = nourriture;
    }

    public void setVisite(boolean visite) {
        this.visite = visite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    public void setChien(boolean chien) {
        this.chien = chien;
    }

    public void setAutre(boolean autre) {
        this.autre = autre;
    }

    public void setPromenade(boolean promenade) {
        this.promenade = promenade;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setDate_debut_demande(LocalDate date_debut_demande) {
        this.date_debut_demande = date_debut_demande;
    }

    public void setDate_fin_demande(LocalDate date_fin_demande) {
        this.date_fin_demande = date_fin_demande;
    }

    public void setPrix_souhaite(float prix_souhaite) {
        this.prix_souhaite = prix_souhaite;
    } 

    public void setTmp_debut(Time tmp_debut) {
        this.tmp_debut = tmp_debut;
    }

    public void setTmp_fin(Time tmp_fin) {
        this.tmp_fin = tmp_fin;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final DemandeSitting other = (DemandeSitting) obj;
        if (this.id_demande != other.id_demande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DemandeSitting{" + "id_demande=" + id_demande + ", nourriture=" + nourriture + ", visite=" + visite + ", description=" + description + ", chat=" + chat + ", chien=" + chien + ", autre=" + autre + ", promenade=" + promenade + ", lieu=" + lieu + ", num_tel=" + num_tel + ", date_debut_demande=" + date_debut_demande + ", date_fin_demande=" + date_fin_demande + ", prix_souhaite=" + prix_souhaite + ", tmp_debut=" + tmp_debut + ", tmp_fin=" + tmp_fin + ", titre=" + titre + ", id_user=" + id_user + '}';
    }

    

    
    
    
}

