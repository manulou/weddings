package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.Email;
import com.manulsoftware.weddings.service.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String USERNAME = "palmtreewedding@gmail.com";
    private static final String PASSWORD = "KWga8m7ZVxxbqrCR9KxMqlKAOCSz3D";
    private static final String RECIPIENT = "brain.free.zone@gmail.com";

    @Override
        public void sendEmail(final Email email) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getEmail()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIPIENT));
        message.setSubject("Enquiry from " + email.getName() + " (" + email.getEmail() + ")");
        message.setText(email.getMessage());
        Transport.send(message);
    }
}
