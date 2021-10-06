package com.training.queue;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	@Value("${random-access-key}")
	private String pass;
	
    public void mail(int totalProcessed, int passedProducts) {

        String to = "mohitkashyap656@gmail.com";

        String from = "pallav945@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("pallav945@gmail.com", pass);

            }

        });


        session.setDebug(true);

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("UPDATE: Batch processing!!");

            message.setText("Dear Product Owner/Manager,\nBatch processing completed successfully.\nTotal Products processed: "+totalProcessed+"\nTotal products passed: "+passedProducts+"\nTotal failed: "+(totalProcessed-passedProducts)+"\nLink to modify failed products: link\nThis is an auto-generated mail. Please do not reply.\n\nTHANKS,\nAtharv,Chandu,Kajal,Mohit,Pallav.");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}