/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Hiba
 */
public class Command_Line {
 private int line_id;
 private Order order=new Order();
 private Produit product=new Produit();
 //private Animal animal=new Animal();
 public int quantity;

    public Command_Line() {
    }

    public Command_Line(int line_id, int quantity,Order order,Produit product) {
        this.line_id = line_id;
        this.quantity = quantity;
        this.order=order;
        this.product=product;
        
    }
    public Command_Line(int line_id, int quantity,Order order) {
        this.line_id = line_id;
        this.quantity = quantity;
        this.order=order;
        
    }


    public Order getOrder() {
        return order;
    }

    public Produit getProduct() {
        return product;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Produit product) {
        this.product = product;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
         return quantity;
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.line_id;
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
        final Command_Line other = (Command_Line) obj;
        if (this.line_id != other.line_id) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Product name :  "+product.getNomproduit()+"    line_id"+line_id+"   quantity:"+quantity ;
    }
    
    
 
 
}
