/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.events;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author achref kh
 */
public class sendmail {
    public void send(String too,events e){
        try{
            String host ="smtp.gmail.com" ;
            String user = "achref.khemiri@esprit.tn";
            String pass = "A09195334";
            String to = too;
            String from = "achref.khemiri@esprit.tn";
            String messageText = "events{" + "id=" + Integer.toString(e.getId()) + ", nom=" + e.getNom() + ", description=" + e.getDescription() + ", type=" + e.getType() + ", datedebut=" + e.getDatedebut().toString() + ", datefin=" + e.getDatefin().toString() + ", nbrParticipants=" + Integer.toString(e.getNbrParticipants()) + ", organisateur=" + e.getOrganisateur()+ '}';
            String subject = "Your Is Test Email :";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
