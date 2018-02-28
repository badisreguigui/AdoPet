/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

/**
 *
 * @author LENOVO
 */
public class Commentaire {
    private int idcom;
    private String description;
    private int iduser;    
    private int idpub;

    public Commentaire(int idcom, String description, int iduser, int idpub) {
        this.idcom = idcom;
        this.description = description;
        this.iduser = iduser;
        this.idpub = idpub;
    }

    public Commentaire(String description, int iduser, int idpub) {
        this.description = description;
        this.iduser = iduser;
        this.idpub = idpub;
    }

    public int getIdcom() {
        return idcom;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdpub() {
        return idpub;
    }

    public void setIdpub(int idpub) {
        this.idpub = idpub;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idcom;
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
        final Commentaire other = (Commentaire) obj;
        if (this.idcom != other.idcom) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "description=" + description + ", iduser=" + iduser + ", idpub=" + idpub + '}';
    }
    
    
}
