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
public class Matching {
    private int id_matching;
    private int id_user;
    private int id_pet;
    private int matching;

    public Matching(int id_matching, int id_user, int id_pet, int matching) {
        this.id_matching = id_matching;
        this.id_user = id_user;
        this.id_pet = id_pet;
        this.matching = matching;
    }
    
    

    public Matching(int id_user, int id_pet, int matching) {
        this.id_user = id_user;
        this.id_pet = id_pet;
        this.matching = matching;
    }
    
    

    public Matching(int id_user, int id_pet) {
        this.id_user = id_user;
        this.id_pet = id_pet;
    }

    public Matching(int id_pet) {
        this.id_pet = id_pet;
    }

    public int getId_matching() {
        return id_matching;
    }

    public void setId_matching(int id_matching) {
        this.id_matching = id_matching;
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

    public int getMatching() {
        return matching;
    }

    public void setMatching(int matching) {
        this.matching = matching;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id_matching;
        hash = 37 * hash + this.id_user;
        hash = 37 * hash + this.id_pet;
        hash = 37 * hash + this.matching;
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
        final Matching other = (Matching) obj;
        if (this.id_matching != other.id_matching) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_pet != other.id_pet) {
            return false;
        }
        if (this.matching != other.matching) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matching{" + "id_matching=" + id_matching + ", id_user=" + id_user + ", id_pet=" + id_pet + ", matching=" + matching + '}';
    }
    
    

    
      
    
}
