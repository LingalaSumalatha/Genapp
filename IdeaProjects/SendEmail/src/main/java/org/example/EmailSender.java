package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
public class EmailSender
{
    public static void sendEmail(String from, String to, String subject, String message,String attachment)
    {
        String username = "sumalathalingala4@gmail.com";
        // the authenticated password
        String password = "bkdftrdtqzofqzvd";

        //to connect to the mail server and to set the  Properties
        Properties props = new Properties();
        //Setup mail server
        props.put("mail.smtp.host", "smtp.gmail.com");//assuming sending email from through gmails smtp(host)
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //debugs SMTP
        props.setProperty("mail.debug","true");
        //Get session object and pass username and password
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try
        {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(from));

            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);

            MimeBodyPart attach =new MimeBodyPart();
            MimeBodyPart body=new MimeBodyPart();

            //Setting path to the attachment(Image)
            File file =new File(attachment);

            body.setText(message);
            attach.attachFile(file);

            Multipart mp=new MimeMultipart();
            //Attaching file to the bodypart
            mp.addBodyPart(attach);
            //adding message to the bodypart
            mp.addBodyPart(body);

            emailMessage.setContent(mp);
            //send message
            Transport.send(emailMessage);
        }
        catch (MessagingException |IOException e) {
            throw new RuntimeException(e);
        }
    }
}
