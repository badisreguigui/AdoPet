/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author TESNIME
 */
public class Tarif {
    private int id_tarif;
    private int id_veto;
    private int consultation;
    private int vaccinationChat;
    private int vaccinationChien;
    private int sterilisation;
    private int analyses;

    public Tarif() {
    }

    public Tarif(int id_veto, int consultation, int vaccinationChat, int vaccinationChien, int sterilisation, int analyses) {
        this.id_veto = id_veto;
        this.consultation = consultation;
        this.vaccinationChat = vaccinationChat;
        this.vaccinationChien = vaccinationChien;
        this.sterilisation = sterilisation;
        this.analyses = analyses;
    }

    public Tarif(int consultation, int vaccinationChat, int vaccinationChien, int sterilisation, int analyses) {
        this.consultation = consultation;
        this.vaccinationChat = vaccinationChat;
        this.vaccinationChien = vaccinationChien;
        this.sterilisation = sterilisation;
        this.analyses = analyses;
    }
    
    

    public Tarif(int id_tarif, int id_veto, int consultation, int vaccinationChat, int vaccinationChien, int sterilisation, int analyses) {
        this.id_tarif = id_tarif;
        this.id_veto = id_veto;
        this.consultation = consultation;
        this.vaccinationChat = vaccinationChat;
        this.vaccinationChien = vaccinationChien;
        this.sterilisation = sterilisation;
        this.analyses = analyses;
    }

    public int getId_tarif() {
        return id_tarif;
    }

    public void setId_tarif(int id_tarif) {
        this.id_tarif = id_tarif;
    }

    public int getId_veto() {
        return id_veto;
    }

    public void setId_veto(int id_veto) {
        this.id_veto = id_veto;
    }

    public int getConsultation() {
        return consultation;
    }

    public void setConsultation(int consultation) {
        this.consultation = consultation;
    }

    public int getVaccinationChat() {
        return vaccinationChat;
    }

    public void setVaccinationChat(int vaccinationChat) {
        this.vaccinationChat = vaccinationChat;
    }

    public int getVaccinationChien() {
        return vaccinationChien;
    }

    public void setVaccinationChien(int vaccinationChien) {
        this.vaccinationChien = vaccinationChien;
    }

    public int getSterilisation() {
        return sterilisation;
    }

    public void setSterilisation(int sterilisation) {
        this.sterilisation = sterilisation;
    }

    public int getAnalyses() {
        return analyses;
    }

    public void setAnalyses(int analyses) {
        this.analyses = analyses;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_tarif;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarif other = (Tarif) obj;
        if (this.id_tarif != other.id_tarif) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarif{" + "id_tarif=" + id_tarif + ", id_veto=" + id_veto + ", consultation=" + consultation + ", vaccinationChat=" + vaccinationChat + ", vaccinationChien=" + vaccinationChien + ", sterilisation=" + sterilisation + ", analyses=" + analyses + '}';
    }

    
    

    
    
}
