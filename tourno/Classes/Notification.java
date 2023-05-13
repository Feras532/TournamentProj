package Classes;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.*;
import javax.mail.internet.*;

public class Notification {


    public static void sendNotification(Paricipant participant, Team team,Tournament tournament){

        final String username = "s201951430@kfupm.edu.sa"; // Replace with your email address
        final String password = "#Emailtest221"; // Replace with your email password
    
        String host = "smtp.office365.com"; // Replace with your email provider's SMTP server hostname
        int port = 587; // Replace with your email provider's SMTP server port number
    
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
    
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
          });
    
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("s201951430@kfupm.edu.sa")); // Replace with your email address


            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(participant.getEmailString())); // Replace with recipient email address
            message.setSubject("Tournament registeration confirmation"); // Replace with subject line
            if(!tournament.getGameObj().getIsTeamGame()){
                message.setText("Dear "+ participant.getFirstName() +" " + participant.getLastName() +"  "
                +participant.getUserID()+","+ "\n\nYou have successfully registered in "+ tournament.getName()+"."); // Replace with email body text
            }
            else{
                String teamMembers = "";
                for (Paricipant p : team.getPlayers()) {
                    if(!p.getUserID().equals(participant.getUserID())){
                        teamMembers += p.getFirstName()+" "+p.getLastName()+"   "+p.getUserID()+"\n";
                    }
                }

                /*message.setText("Dear "+ participant.getFirstName() +" " + participant.getLastName() +
                "   "+participant.getUserID()+"," + "\n\nYou have successfully registered in "+ tournament.getName()+".\n"
                +"The team members are: \n"+ teamMembers); */// Replace with email body text
                System.out.println("Dear "+ participant.getFirstName() +" " + participant.getLastName() +
                "  "+participant.getUserID()+"," + "\n\nYou have successfully registered in "+ tournament.getName()+".\n"
                +"The team members are: \n"+ teamMembers);
            }
            
            Transport.send(message);
    
            System.out.println("Email sent successfully!");
    
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
