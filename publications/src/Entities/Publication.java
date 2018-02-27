/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.util.logging.Logger;

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
    private String date;
    private String time;
    private String photoName;
    private String signall;

    public Publication(String type, String description, String image, int iduser, String date, String time, String photoName, String signall) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
        this.date = date;
        this.time = time;
        this.photoName = photoName;
        this.signall = signall;
    }

    public String getSignall() {
        return signall;
    }

    public void setSignall(String signall) {
        this.signall = signall;
    }
    
      
    public Publication() {
    }

    public Publication(String type, String description, String image, int iduser, String date, String time, String photoName) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
        this.date = date;
        this.time = time;
        this.photoName = photoName;
    }

    public Publication(String type, String description, String image, int iduser) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
    }    

    public Publication(String type, String description, String image) {
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public Publication(int idpub, String type, String description, String image, int iduser) {
        this.idpub = idpub;
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
    }
    
    public Publication(int id, String type, String description, String image, int iduser, String date, String time) {
        this.idpub = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
        this.date = date;
        this.time = time;
    }  

    public Publication(String type, String description, String image, int iduser, String date, String time) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
        this.date = date;
        this.time = time;
    }   

    public Publication(int idpub, String type, String description, String image, int iduser, String date, String time, String photoName) {
        this.idpub = idpub;
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser = iduser;
        this.date = date;
        this.time = time;
        this.photoName = photoName;
    }

    public Publication(int idpub) {
        this.idpub = idpub;
    }
    

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "Publication{" + "idpub=" + idpub + ", type=" + type + ", description=" + description + ", image=" + image + ", iduser=" + iduser + ", date=" + date + ", time=" + time + '}';
    }
    
    
    

    
    
    
    
}
