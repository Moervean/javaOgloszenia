package main.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

public class MailService {
    public void sendEmail(String toAddress, String subject, String message) throws MessagingException {
        String userName = "javaservisogloszeniowy@gmail.com";
        String password = "javaserwis123";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");


        properties.put("mail.smtp.user", userName);
        Session session = Session.getDefaultInstance(properties);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);

        Transport t = session.getTransport("smtp");
        t.connect(userName, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }
}
