package org.example;

public class Main {
    public static void main(String[] args)
    {
        String from = "sumalathalingala4@gmail.com";
        String to ="sumalatha@natsoft.us" ;
        String subject = "Test Email";
        String message = "This is a test email message.";
        String attachment="/home/sumalatha/Downloads/wallpaper.png";
        //calling the sendmail method
        EmailSender.sendEmail(from, to, subject, message,attachment);
    }
}