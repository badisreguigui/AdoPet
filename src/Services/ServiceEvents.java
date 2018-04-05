/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.events;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Outils.DataSource;

/**
 *
 * @author achref kh
 */
public class ServiceEvents {

    Connection con = DataSource.getInstance().getConnection();
    public Statement ste;
    public ResultSet resultat;

    public ServiceEvents() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddEvent(events v) throws SQLException {
        String req = "INSERT INTO `events`(`nom`, `description`, `type`, `datedebut`, `datefin`,`organisateur`,`nbreParticipants`,`status`,`lieu`) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, v.getNom());
        prs.setString(2, v.getDescription());
        prs.setString(3, v.getType());
        prs.setDate(4, v.getDatedebut());
        prs.setDate(5, v.getDatefin());
        prs.setString(6, v.getOrganisateur());
        prs.setInt(7, v.getNbrParticipants());
        prs.setString(9, v.getLieu());
        prs.setString(8, "Upcoming");
        
        prs.executeUpdate();    

        }

        
    
    public void deleteEvent(int a) throws SQLException {
        try {
            String req = "delete from events where id = ?";
            PreparedStatement prs = con.prepareStatement(req);
            prs.setInt(1, a);
            prs.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public void updateEvent(events e, int id) throws SQLException {
        String req = "update events set nom=? ,description=? ,type=? ,datedebut=? ,datefin=? ,lieu=? where id=?";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, e.getNom());
        prs.setString(2, e.getDescription());
        prs.setString(3, e.getType());
        prs.setDate(4, e.getDatedebut());
        prs.setDate(5, e.getDatefin());
        prs.setString(6, e.getLieu());
        prs.setInt(7, id);
        prs.executeUpdate();
    }

    public boolean participerEvent(int idEvent, int idUser) throws SQLException {
        boolean result = false;
        String req = "SELECT `id_event`, `id_user` FROM `participations` WHERE `id_event`=? and `id_user`=?";

        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, idEvent);
        prs.setInt(2, idUser);
        resultat = prs.executeQuery();
        if (resultat.next() == false) {
            String req1 = "INSERT INTO `participations`(`id_event`, `id_user`) VALUES (?,?)";
            prs = con.prepareStatement(req1);
            prs.setInt(1, idEvent);
            prs.setInt(2, idUser);
            prs.executeUpdate();

            String req2 = "update events set nbreParticipants=nbreParticipants+1 where id=?";
            prs = con.prepareStatement(req2);
            prs.setInt(1, idEvent);
            prs.executeUpdate();

            result = true;
        }
        return result;

    }

    
    public ObservableList<events> DisplayEvents(String login) throws SQLException {
        String req = "select id,nom,description,type,datedebut,datefin,nbreParticipants,lieu,organisateur from events where organisateur!=? and status=?";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, login);
        prs.setString(2, "Upcoming");
        resultat = prs.executeQuery();
        ObservableList<events> data = FXCollections.observableArrayList();
        while (resultat.next()) {
            events e = new events(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("description"), resultat.getString("type"), resultat.getDate("datedebut"), resultat.getDate("datefin"), resultat.getInt("nbreParticipants"), resultat.getString("organisateur"),resultat.getString("lieu"));
            data.add(e);
        }
        return data;
    }
    
    public ObservableList<events> DisplayMyEvents(String login) throws SQLException {
        String req = "select id,nom,description,type,datedebut,datefin,nbreParticipants,lieu,status from events where organisateur=? ";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, login);
        resultat = prs.executeQuery();
        ObservableList<events> data = FXCollections.observableArrayList();
        while (resultat.next()) {
            events e = new events(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("description"), resultat.getString("type"), resultat.getDate("datedebut"), resultat.getDate("datefin"),resultat.getString("status"), resultat.getInt("nbreParticipants"), resultat.getString("lieu"));
            data.add(e);
        }
        return data;
    }
    
    public ObservableList<events> DisplayMyParticipation(int idUser) throws SQLException {
        String req = "select events.id,nom,type,datedebut,organisateur,lieu from events join participations on events.id=participations.id_event where participations.id_user=? ";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, idUser);
        resultat = prs.executeQuery();
        ObservableList<events> data = FXCollections.observableArrayList();
        while (resultat.next()) {
            events e = new events(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("type"), resultat.getDate("datedebut"), resultat.getString("organisateur"),resultat.getString("lieu"));
            data.add(e);
        }
        return data;
    }

    public int CountEvents(String login) throws SQLException {
        String req = "select count(*) from events where `organisateur`!=? ";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, login);
        resultat = prs.executeQuery();
        int data = 0;
        while (resultat.next()) {
            data = resultat.getInt("count(*)");
        }
        return data;

    }

    public void PreferenceEvent(int IdUser, String lieu, String type) throws SQLException {

        String req = "select * from preferences where id_user=?";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, IdUser);
        resultat = prs.executeQuery();
        if (resultat.next()) {
            req = "select lieu from events inner join participations on events.id=participations.id_event where participations.id_user= ? ";
            prs = con.prepareStatement(req);
            prs.setInt(1, IdUser);
            resultat = prs.executeQuery();
            List<String> lieux = new ArrayList();
            String a = "";
            while (resultat.next()) {
                a = resultat.getString("lieu");
                lieux.add(a);
            }

            Map<String, Integer> count = new HashMap<>();
            for (String t : lieux) {
                Integer i = count.get(t);
                if (i == null) {
                    i = 0;
                }
                count.put(t, i + 1);
            }
            List<String> preferencelieux = new ArrayList();
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                if (entry.getValue() > 1) {
                    preferencelieux.add(entry.getKey());
                }

            }

            req = "UPDATE `preferences` SET `lieu`='' WHERE id_user=?";
            prs = con.prepareStatement(req);
            prs.setInt(1, IdUser);
            prs.executeUpdate();

            for (int i = 0; i < preferencelieux.size(); i++) {
                req = "UPDATE `preferences` SET `lieu`=concat(?,concat(',',lieu)) WHERE id_user=?";
                prs = con.prepareStatement(req);
                prs.setString(1, preferencelieux.get(i));
                prs.setInt(2, IdUser);
                prs.executeUpdate();

            }
        } else {
            req = "INSERT INTO `preferences`(`id_user`, `lieu`, `type`) VALUES (?,?,?)";
            prs = con.prepareStatement(req);
            prs.setInt(1, IdUser);
            prs.setString(2, lieu);
            prs.setString(3, type);
            prs.executeUpdate();
        }

    }

    //private void participate
    public String selectlieu(int id) throws SQLException {
        String req = "select lieu from events where id=?";
        String result = "";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, id);
        resultat = prs.executeQuery();
        while (resultat.next()) {
            result = resultat.getString("lieu");
        }
        return result;
    }

    //private void participate
    public String selecttype(int id) throws SQLException {
        String req = "select type from events where id=?";
        String result = "";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, id);
        resultat = prs.executeQuery();
        while (resultat.next()) {
            result = resultat.getString("type");
        }
        return result;
    }

    public void MailEvent(events e, int IdUser) throws SQLException {
        
        sendmail se = new sendmail();
        String req = "select id_user,lieu from `preferences` where id_user!=?"; //pour ne pas envoyer un mail au createur de l'event
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, IdUser);
        resultat = prs.executeQuery();
        Map<Integer, String> mailinglist = new HashMap<>();
        while (resultat.next()) {
            mailinglist.put(resultat.getInt("id_user"), resultat.getString("lieu"));
        }
        for (Map.Entry<Integer, String> entry : mailinglist.entrySet()) {
            if (entry.getValue().contains(e.getLieu())) {
                req = "select email from `user` where id=?";
                prs = con.prepareStatement(req);
                prs.setInt(1, entry.getKey());
                resultat = prs.executeQuery();
                String mail = "";
                while (resultat.next()) {
                    mail = resultat.getString("email");
                }
                se.send(mail, e);

            }

        }

    }
    public ObservableList<events> DisplayEventsByName(String login,String recher) throws SQLException {
        String req = "select id,nom,description,type,datedebut,datefin,nbreParticipants,lieu,organisateur from events where organisateur!=? and status=?  and nom like ?";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, login);
        prs.setString(2, "Upcoming");
        prs.setString(3, "%"+recher+"%");
        resultat = prs.executeQuery();
        ObservableList<events> data = FXCollections.observableArrayList();
        while (resultat.next()) {
            events e = new events(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("description"), resultat.getString("type"), resultat.getDate("datedebut"), resultat.getDate("datefin"), resultat.getInt("nbreParticipants"), resultat.getString("organisateur"),resultat.getString("lieu"));
            data.add(e);
        }
        return data;
    }
    
    public ObservableList<events> DisplayEventsByType(String login,String recher) throws SQLException {
        String req = "select id,nom,description,type,datedebut,datefin,nbreParticipants,lieu,organisateur from events where organisateur!=? and status=?  and type=?";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setString(1, login);
        prs.setString(2, "Upcoming");
        prs.setString(3, recher);
        resultat = prs.executeQuery();
        ObservableList<events> data = FXCollections.observableArrayList();
        while (resultat.next()) {
            events e = new events(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("description"), resultat.getString("type"), resultat.getDate("datedebut"), resultat.getDate("datefin"), resultat.getInt("nbreParticipants"), resultat.getString("organisateur"),resultat.getString("lieu"));
            data.add(e);
        }
        return data;
    }
    
    public String getFeedback(int idEvent,int idUser) throws SQLException{
        String req = "select feedback from participations where id_event=? and id_user=? ";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(1, idEvent);
        prs.setInt(2, idUser);
        resultat = prs.executeQuery();
        String a = "";
        while (resultat.next()) {
            a=resultat.getString("feedback");
        }
        return a;
    }
    
    public void addupdatefeedback(String f,int idEvent,int idUser) throws SQLException{
        String req = "update participations set feedback=? where id_user=? and id_event=?";
        PreparedStatement prs = con.prepareStatement(req);
        prs.setInt(3, idEvent);
        prs.setInt(2, idUser);
        prs.setString(1, f);
        prs.executeUpdate();
    }

}
