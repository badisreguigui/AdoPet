/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author mac
 */
public class Rateme {
    private int id_rating;
    private double rateme;
    private int id_pet;
    private int id_user;

    public Rateme() {
    }

    public Rateme(double rateme, int id_pet, int id_user) {
        this.rateme = rateme;
        this.id_pet = id_pet;
        this.id_user = id_user;
    }

    public int getId_rating() {
        return id_rating;
    }

    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public double getRateme() {
        return rateme;
    }

    public void setRateme(double rateme) {
        this.rateme = rateme;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_rating;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.rateme) ^ (Double.doubleToLongBits(this.rateme) >>> 32));
        hash = 97 * hash + this.id_pet;
        hash = 97 * hash + this.id_user;
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
        final Rateme other = (Rateme) obj;
        if (this.id_rating != other.id_rating) {
            return false;
        }
        if (Double.doubleToLongBits(this.rateme) != Double.doubleToLongBits(other.rateme)) {
            return false;
        }
        if (this.id_pet != other.id_pet) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rateme{" + "id_rating=" + id_rating + ", rateme=" + rateme + ", id_pet=" + id_pet + ", id_user=" + id_user + '}';
    }
    
    
    
}
