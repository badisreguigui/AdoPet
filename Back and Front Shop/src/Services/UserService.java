/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Outils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kapio
 */
public class UserService {
Connection con=DataSource.getInstance().getConnection();
   public Statement ste;
   ResultSet res;
    ObservableList<User> log;
   
    public UserService() throws SQLException{
        ste=con.createStatement();
    }
    
    public void insert(User r){
    try {
        String requete = "insert into users(login,password) values (?,?)";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, r.getLogin());
        st.setString(2, r.getPassword());
        st.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public ObservableList<User> Select(User p) throws SQLException{
         
        String requete = "Select login,password from users where login= ? and password = ?";
        PreparedStatement st=con.prepareStatement(requete);
        st.setString(1, p.getLogin());
        st.setString(2, p.getPassword());
        log = FXCollections.observableArrayList();

        res=st.executeQuery();
        while(res.next())
        {
            User u = new User(res.getString(1),res.getString(2));
            log.add(u);
        }
        return log;
    }
}
