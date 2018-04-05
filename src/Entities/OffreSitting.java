package Entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**k
 *
 * @author emna
 */
public class OffreSitting {

    private int id_offre;
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
    private LocalDate date_debut_dispo;
    private LocalDate date_fin_dispo;
    private float prix_demande;
    private Time tmp_debut;
    private Time tmp_fin;
    private int id_user=0;
    

    //constructeur vide
    public OffreSitting() {
    }

    //Constructeur paramétré

    public OffreSitting(int id_offre, String titre, boolean nourriture, boolean visite, String description, boolean chat, boolean chien, boolean autre, boolean promenade, String lieu, int num_tel, LocalDate date_debut_dispo, LocalDate date_fin_dispo, float prix_demande, Time tmp_debut, Time tmp_fin, int id_user) {
        this.id_offre = id_offre;
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
        this.date_debut_dispo = date_debut_dispo;
        this.date_fin_dispo = date_fin_dispo;
        this.prix_demande = prix_demande;
        this.tmp_debut = tmp_debut;
        this.tmp_fin = tmp_fin;
        this.id_user = id_user;
    }

    public OffreSitting(String titre, boolean nourriture, boolean visite, String description, boolean chat, boolean chien, boolean autre, boolean promenade, String lieu, int num_tel, LocalDate date_debut_dispo, LocalDate date_fin_dispo, float prix_demande, Time tmp_debut, Time tmp_fin) {
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
        this.date_debut_dispo = date_debut_dispo;
        this.date_fin_dispo = date_fin_dispo;
        this.prix_demande = prix_demande;
        this.tmp_debut = tmp_debut;
        this.tmp_fin = tmp_fin;
    }
    
    
    

    //Getters
    public int getId_user() {
        return id_user;
    }
    
    public String getTitre() {
        return titre;
    }

    public int getId_offre() {
        return id_offre;
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

    public LocalDate getDate_debut_dispo() {
        return date_debut_dispo;
    }

    public LocalDate getDate_fin_dispo() {
        return date_fin_dispo;
    }

    public float getPrix_demande() {
        return prix_demande;
    }

    public Time getTmp_debut() {
        return tmp_debut;
    }

    public Time getTmp_fin() {
        return tmp_fin;
    }
    

    //Setter
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
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

    public void setDate_debut_dispo(LocalDate date_debut_dispo) {
        this.date_debut_dispo = date_debut_dispo;
    }

    public void setDate_fin_dispo(LocalDate date_fin_dispo) {
        this.date_fin_dispo = date_fin_dispo;
    }

    public void setTmp_debut(Time tmp_debut) {
        this.tmp_debut = tmp_debut;
    }

    public void setTmp_fin(Time tmp_fin) {
        this.tmp_fin = tmp_fin;
    }
    

    public void setPrix_demande(float prix_demande) {
        this.prix_demande = prix_demande;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OffreSitting other = (OffreSitting) obj;
        if (this.id_offre != other.id_offre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OffreSitting{" + "id_offre=" + id_offre + ", titre=" + titre + ", nourriture=" + nourriture + ", visite=" + visite + ", description=" + description + ", chat=" + chat + ", chien=" + chien + ", autre=" + autre + ", promenade=" + promenade + ", lieu=" + lieu + ", num_tel=" + num_tel + ", date_debut_dispo=" + date_debut_dispo + ", date_fin_dispo=" + date_fin_dispo + ", prix_demande=" + prix_demande + ", tmp_debut=" + tmp_debut + ", tmp_fin=" + tmp_fin + '}';
    }

    
    
    
    
    
    
    
}
