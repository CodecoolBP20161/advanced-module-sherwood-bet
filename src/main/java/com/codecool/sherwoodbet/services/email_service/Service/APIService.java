package com.codecool.sherwoodbet.services.email_service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by csyk on 2017.02.10..
 */
public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);

    private static APIService INSTANCE;
  
    private ResourceBundle rb = ResourceBundle.getBundle("emaildata"); // emaildata.properties
    private final String FROM  = rb.getString("address");
    private final String password = rb.getString("password");
  
    private APIService() {
    }

    public static APIService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public void sendEmail(String to, String subject, String text) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try {
            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(FROM, password);
                        }
                    });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setSentDate(new Date());
            Transport.send(msg);
            logger.info("Message sent.");
        } catch (MessagingException e) {
            logger.debug("Error during sending email: " + e);
        }
    }
}