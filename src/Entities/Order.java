/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Hiba
 */
public class Order {
    private int order_id;
    private int price;
    private Date date;
    private String login;
    

    public Order() {
    }

    public Order(int price, Date date, String login) {
        this.price = price;
        this.date = date;
        this.login = login;
    }

    public Order(int order_id, int price, Date date, String login) {
        this.order_id = order_id;
        this.price = price;
        this.date = date;
        this.login = login;
    }

    public Order(int price, String login) {
        this.price = price;
        this.login = login;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", price=" + price + ", date=" + date + ", login=" + login + '}';
    }
    
    
    
    
    
}
