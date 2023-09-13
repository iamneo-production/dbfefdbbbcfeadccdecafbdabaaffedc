package com.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        final String fromEmail = "ldemo9433@gmail.com";
        final String password = "nocqkuddoiijcryw";
        final String toEmail = "ldemo9433@gmail.com";

        boolean emailSent = sendEmail(fromEmail, password, toEmail, "Hello from JavaMail",
                "This is a test email sent from JavaMail.");

        if (emailSent) {
            System.out.println("Email sent successfully!");
        } else {
            System.out.println("Email sending failed.");
        }
    }

    public static boolean sendEmail(String fromEmail, String password, String toEmail, String subject,
            String messageText) {
        try {
            final String host = "smtp.gmail.com";
            final String port = "587";

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
