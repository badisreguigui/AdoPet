/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author TESNIME
 */
public class Veto {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private int telephone;
    private String rue;
    private String ville;
    private String gouvernorat;
    private int codePostal;
    private String image;
    private String description;
    private String role;
    private String login;
    private String password;
    private Date currentDate;

    public Veto() {
    }

    public Veto(String nom, String prenom, String email, int telephone, String rue, String ville, String gouvernorat, int codePostal, String image, String description, String role, String login, String password, Date currentDate) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.ville = ville;
        this.gouvernorat = gouvernorat;
        this.codePostal = codePostal;
        this.image = image;
        this.description = description;
        this.role = role;
        this.login = login;
        this.password = password;
        this.currentDate = currentDate;
    }
    
    

    public Veto(String nom, String prenom, String email, int telephone, String rue, String ville, String gouvernorat, int codePostal, String image, String description, String role, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.ville = ville;
        this.gouvernorat = gouvernorat;
        this.codePostal = codePostal;
        this.image = image;
        this.description = description;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    

    public Veto(int id, String nom, String prenom, String email, int telephone, String rue, String ville, String gouvernorat, int codePostal, String image, String description, String role, String login, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.ville = ville;
        this.gouvernorat = gouvernorat;
        this.codePostal = codePostal;
        this.image = image;
        this.description = description;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nom);
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
        final Veto other = (Veto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Veto{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", ville=" + ville + ", gouvernorat=" + gouvernorat + ", codePostal=" + codePostal + ", image=" + image + ", description=" + description + ", role=" + role + ", login=" + login + ", password=" + password + '}';
    }

    
    

    
    
    
    
}
