package org.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CFMail {
    public static void sendEmail(CFMailOptions options, String mailText) throws MessagingException {

        String username = "sumalathalingala4@gmail.com";
        String password = "bkdftrdtqzofqzvd";

        Properties properties = new Properties();

        properties.put("mail.smtp.host", options.getHost());
        properties.put("mail.smtp.port", options.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", options.getHost());
        properties.setProperty("mail.debug","true");


        Session session = Session.getInstance(properties,new javax.mail.Authenticator()
        {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username,password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(options.getFrom()));

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(options.getTo()));
        if (options.getCc() != null) {
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(options.getCc()));
        }
        if (options.getBcc() != null) {
            message.setRecipient(Message.RecipientType.BCC, new InternetAddress(options.getBcc()));
        }
        message.setSubject(options.getSubject());
        message.setText(mailText);

        Transport transport = session.getTransport("smtp");
        transport.connect(options.getServer(), options.getUsername(), options.getPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }
}
