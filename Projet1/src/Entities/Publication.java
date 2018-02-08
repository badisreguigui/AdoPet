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
public class Publication {
    private int idpub;
    private String type;
    private String description;
    private String image;
    private int iduser;

    public Publication(String type, String description, String image, int iduser) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
    }

    public Publication() {
    }

    public int getIdpub() {
        return idpub;
    }

    public void setIdpub(int idpub) {
        this.idpub = idpub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Publication(int idpub, String type, String description, String image, int iduser) {
        this.idpub = idpub;
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
    }

   
   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.idpub;
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
        final Publication other = (Publication) obj;
        if (this.idpub != other.idpub) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "type=" + type + ", description=" + description + ", image=" + image + ", iduser=" + iduser + '}';
    }

    
    
    
    
}
