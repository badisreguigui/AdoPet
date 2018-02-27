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
    
        private int idproduit;	
        private String nomproduit;	
        private String description;
        private int prix;
        private int quantite;
        private String imageproduit;
        private String nomcategorie;
        private String nomraceporduit;
        private String nomboutiqueproduit;

    public Produit() {
    }

    public Produit(int idproduit, String nomproduit, String description, int prix, int quantite, String imageproduit, String nomcategorie, String nomraceporduit, String nomboutiqueproduit) {
        this.idproduit = idproduit;
        this.nomproduit = nomproduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.imageproduit = imageproduit;
        this.nomcategorie = nomcategorie;
        this.nomraceporduit = nomraceporduit;
        this.nomboutiqueproduit = nomboutiqueproduit;
    }

    public Produit(String nomproduit, String description, int prix, int quantite, String imageproduit, String nomcategorie, String nomraceporduit, String nomboutiqueproduit) {
        this.nomproduit = nomproduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.imageproduit = imageproduit;
        this.nomcategorie = nomcategorie;
        this.nomraceporduit = nomraceporduit;
        this.nomboutiqueproduit = nomboutiqueproduit;
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

    public String getNomraceporduit() {
        return nomraceporduit;
    }

    public void setNomraceporduit(String nomraceporduit) {
        this.nomraceporduit = nomraceporduit;
    }

    public String getNomboutiqueproduit() {
        return nomboutiqueproduit;
    }

    public void setNomboutiqueproduit(String nomboutiqueproduit) {
        this.nomboutiqueproduit = nomboutiqueproduit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.idproduit;
        hash = 23 * hash + Objects.hashCode(this.nomproduit);
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
        return "Produit{" + "idproduit=" + idproduit + ", nomproduit=" + nomproduit + ", description=" + description + ", prix=" + prix + ", quantite=" + quantite + ", imageproduit=" + imageproduit + ", nomcategorie=" + nomcategorie + ", nomraceporduit=" + nomraceporduit + ", nomboutiqueproduit=" + nomboutiqueproduit + '}';
    }

    
   
        

}
