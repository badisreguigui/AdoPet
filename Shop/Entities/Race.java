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
public class Race {
    private int idrace;
    private String nomrace;
    private String imagerace;
    private String nomboutique;
    
    public Race() {
    }

    public Race(int idrace, String nomrace, String imagerace, String nomboutique) {
        this.idrace = idrace;
        this.nomrace = nomrace;
        this.imagerace = imagerace;
        this.nomboutique = nomboutique;
    }

    public Race(String nomrace, String imagerace, String nomboutique) {
        this.nomrace = nomrace;
        this.imagerace = imagerace;
        this.nomboutique = nomboutique;
    }

    public int getIdrace() {
        return idrace;
    }

    public void setIdrace(int idrace) {
        this.idrace = idrace;
    }

    public String getNomrace() {
        return nomrace;
    }

    public void setNomrace(String nomrace) {
        this.nomrace = nomrace;
    }

    public String getImagerace() {
        return imagerace;
    }

    public void setImagerace(String imagerace) {
        this.imagerace = imagerace;
    }

    public String getNomboutique() {
        return nomboutique;
    }

    public void setNomboutique(String nomboutique) {
        this.nomboutique = nomboutique;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idrace;
        hash = 89 * hash + Objects.hashCode(this.nomrace);
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
        final Race other = (Race) obj;
        if (this.idrace != other.idrace) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Race{" + "idrace=" + idrace + ", nomrace=" + nomrace + ", imagerace=" + imagerace + ", nomboutique=" + nomboutique + '}';
    }

    
    
   
    
    
   
}
