package org.example;

import javax.mail.MessagingException;
public class main
{
    public static void main(String[] args) throws MessagingException
    {
        CFMailOptions options = new CFMailOptions("sumalathalingala4@gmail.com","sumalatha@natsoft.us",null,null,"Test Mail");

        // set the correct SMTP server host,port here
         options.setHost("smtp.gmail.com");
         options.setPort(465);

         String mailText = "This is a test email sent from Java using the CFMail class.";

         CFMail.sendEmail(options, mailText);
         System.out.println("Email sent successfully");

    }
}
