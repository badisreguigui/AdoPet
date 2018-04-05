/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kapio
 */
public class Panier {
    public static Panier instance;
    
  // public Map<User,List<Command_Line>> p=new HashMap<User,List<Command_Line>>();
    public List<Command_Line> p=new ArrayList();
    

    public Panier() {
    }

    public static Panier getInstance()
    {
        if(instance == null)
        {
            instance=new Panier();
        }
        return instance;
        
    }
    
    public void addLine(Command_Line c)
    {
        
        if (search(c)==false) 
        { 
            System.out.println("moch mawwjoud");
            p.add(c);
           
            
        }
        
        else
        {
            System.out.println("mawjoud");
            for(Command_Line c1:p)
             {
                
               if(c.equals(c1))
                 {
                     c1.quantity++;
                 }
             }
             
        }
            
             
            
            //System.out.println(c);

              
       
    }
            
         
    public void removeLine(Command_Line c)
  {
      p.remove(c);
     
  }
    
    public void show()
    {
       
       for(Command_Line c:p)
       {
           System.out.println(c.toString());
       }
    }
    
    public boolean search(Command_Line c)
    {
     if (p.contains(c))
               return true;
       
     return false;
    }
    
    public void closePanier(){
        p.removeAll(p);
        //p.clear();
    }
    public int QuantiteP(){
        int q=0;
        for(Command_Line c : p)
        {
            q=c.getQuantity();
        }
        return q;
        //p.clear();
    }
    
    
    
}
