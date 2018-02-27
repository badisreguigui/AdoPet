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
public class adoption {
    private int id_adoption;
    private int id_user;
    private int id_pet;

    public int getId_adoption() {
        return id_adoption;
    }

    public void setId_adoption(int id_adoption) {
        this.id_adoption = id_adoption;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public adoption(int id_user, int id_pet) {
        this.id_user = id_user;
        this.id_pet = id_pet;
    }

    public adoption() {
    }

    public adoption(int id_adoption, int id_user, int id_pet) {
        this.id_adoption = id_adoption;
        this.id_user = id_user;
        this.id_pet = id_pet;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id_adoption;
        hash = 47 * hash + this.id_user;
        hash = 47 * hash + this.id_pet;
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
        final adoption other = (adoption) obj;
        if (this.id_adoption != other.id_adoption) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_pet != other.id_pet) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adoption{" + "id_adoption=" + id_adoption + ", id_user=" + id_user + ", id_pet=" + id_pet + '}';
    }
    
    
}
