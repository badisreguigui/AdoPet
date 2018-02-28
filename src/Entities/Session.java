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
public class Session {
    public static Session instance;
    
  // public Map<User,List<Command_Line>> p=new HashMap<User,List<Command_Line>>();
    public List<User> p=new ArrayList();
    

    public Session() {
    }

    public static Session getInstance()
    {
        if(instance == null)
        {
            instance=new Session();
        }
        return instance;
        
    }
    
    public void addSession(User c)
    {
        
        if (p.isEmpty()) 
        { 
            System.out.println("moch mawwjoud");
            p.add(c);
            //System.out.println(c);
            
        }
        
        else
        {
            System.out.println("mawjoud");
            
        }
            
             
            
            //System.out.println(c);

              
       
    }
            
         
    public void removeSession(User c)
  {
      p.remove(c);
     
  }
    
    public void show()
    {
       
       for(User c:p)
       {
           System.out.println(c.toString());
       }
    }
    
    public boolean search(User c)
    {
     if (p.contains(c))
               return true;
       
     return false;
    }
    
    public void closeSession(){
        p.removeAll(p);
        //p.clear();
    }
    public String loginSession(){
      String loginaa="";
        for(int i=0;i<p.size();i++){
          loginaa= p.get(i).getLogin();
        }
        return loginaa;  
    }
    public int IdSession(){
      int id=0;
        for(int i=0;i<p.size();i++){
          id=p.get(i).getId();
        }
        return id;
    }
    
}
