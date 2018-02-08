/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.util.Objects;

/**
 *
 * @author Kapio
 */
public class Boutique {
    private int idboutique;
    private String nomboutique;
    private int telboutique;
    private String adresseboutique;
    private String imageboutique;
    
    public Boutique() {
    }

    public Boutique(int idboutique, String nomboutique, int telboutique, String adresseboutique, String imageboutique) {
        this.idboutique = idboutique;
        this.nomboutique = nomboutique;
        this.telboutique = telboutique;
        this.adresseboutique = adresseboutique;
        this.imageboutique = imageboutique;
    }

    public Boutique(String nomboutique, int telboutique, String adresseboutique, String imageboutique) {
        this.nomboutique = nomboutique;
        this.telboutique = telboutique;
        this.adresseboutique = adresseboutique;
        this.imageboutique = imageboutique;
    }

    public int getIdboutique() {
        return idboutique;
    }

    public void setIdboutique(int idboutique) {
        this.idboutique = idboutique;
    }

    public String getNomboutique() {
        return nomboutique;
    }

    public void setNomboutique(String nomboutique) {
        this.nomboutique = nomboutique;
    }

    public int getTelboutique() {
        return telboutique;
    }

    public void setTelboutique(int telboutique) {
        this.telboutique = telboutique;
    }

    public String getAdresseboutique() {
        return adresseboutique;
    }

    public void setAdresseboutique(String adresseboutique) {
        this.adresseboutique = adresseboutique;
    }

    public String getImageboutique() {
        return imageboutique;
    }

    public void setImageboutique(String imageboutique) {
        this.imageboutique = imageboutique;
    }

    @Override
    public String toString() {
        return "Boutique{" + "idboutique=" + idboutique + ", nomboutique=" + nomboutique + ", telboutique=" + telboutique + ", adresseboutique=" + adresseboutique + ", imageboutique=" + imageboutique + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idboutique;
        hash = 17 * hash + Objects.hashCode(this.nomboutique);
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
        final Boutique other = (Boutique) obj;
        if (this.idboutique != other.idboutique) {
            return false;
        }
        return true;
    }
    
    
    
}
