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

    


public class Produit {

   

        
    public enum race {chien,chat}
        private int idproduit;	
        private String nomproduit;	
        private String description;
        private int prix;
        private int quantite;
        private String imageproduit;
        private String nomcategorie;
        private race nomraceproduit;
        private String nomboutiqueproduit;
        

    public Produit() {
    }
    
    public Produit(int idproduit, String nomproduit, String description, int prix, int quantite, String imageproduit, String nomcategorie, race nomraceproduit, String nomboutiqueproduit) {
        this.idproduit = idproduit;
        this.nomproduit = nomproduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.imageproduit = imageproduit;
        this.nomcategorie = nomcategorie;
        this.nomraceproduit = nomraceproduit;
        this.nomboutiqueproduit = nomboutiqueproduit;
    }

    public Produit(String nomproduit, String description, int prix, int quantite, String imageproduit, String nomcategorie, race nomraceproduit, String nomboutiqueproduit) {
        this.nomproduit = nomproduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.imageproduit = imageproduit;
        this.nomcategorie = nomcategorie;
        this.nomraceproduit = nomraceproduit;
        this.nomboutiqueproduit = nomboutiqueproduit;
    }

    public Produit(String nomproduit, int prix) {
        this.nomproduit = nomproduit;
        this.prix = prix;
    }

    public Produit(int idproduit) {
        this.idproduit = idproduit;
    }
    
    

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImageproduit() {
        return imageproduit;
    }

    public void setImageproduit(String imageproduit) {
        this.imageproduit = imageproduit;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public race getNomraceproduit() {
        return nomraceproduit;
    }

    public void setNomraceproduit(race nomraceproduit) {
        this.nomraceproduit = nomraceproduit; 
   }

    public String getNomboutiqueproduit() {
        return nomboutiqueproduit;
    }

    public void setNomboutiqueproduit(String nomboutiqueproduit) {
        this.nomboutiqueproduit = nomboutiqueproduit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idproduit;
        hash = 61 * hash + Objects.hashCode(this.nomproduit);
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
        final Produit other = (Produit) obj;
        if (this.idproduit != other.idproduit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "idproduit=" + idproduit + ", nomproduit=" + nomproduit + ", description=" + description + ", prix=" + prix + ", quantite=" + quantite + ", imageproduit=" + imageproduit + ", nomcategorie=" + nomcategorie + ", nomraceproduit=" + nomraceproduit + ", nomboutiqueproduit=" + nomboutiqueproduit + '}';
    }
    
    
   
   
        

}
