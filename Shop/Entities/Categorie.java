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
public class Categorie {
    private int idcategorie;
    private String nomcategorie;
    private String imagecategorie;
    private String nomrace;

    public Categorie(int idcategorie, String nomcategorie, String imagecategorie, String nomrace) {
        this.idcategorie = idcategorie;
        this.nomcategorie = nomcategorie;
        this.imagecategorie = imagecategorie;
        this.nomrace = nomrace;
    }

    public Categorie() {
    }

    public Categorie(String nomcategorie, String imagecategorie, String nomrace) {
        this.nomcategorie = nomcategorie;
        this.imagecategorie = imagecategorie;
        this.nomrace = nomrace;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getImagecategorie() {
        return imagecategorie;
    }

    public void setImagecategorie(String imagecategorie) {
        this.imagecategorie = imagecategorie;
    }

    public String getNomrace() {
        return nomrace;
    }

    public void setNomrace(String nomrace) {
        this.nomrace = nomrace;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idcategorie;
        hash = 53 * hash + Objects.hashCode(this.nomcategorie);
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
        final Categorie other = (Categorie) obj;
        if (this.idcategorie != other.idcategorie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idcategorie=" + idcategorie + ", nomcategorie=" + nomcategorie + ", imagecategorie=" + imagecategorie + ", nomrace=" + nomrace + '}';
    }
    
    
    
    
}
