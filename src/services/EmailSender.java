package services;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by ivans on 20/04/2017.
 */
public class EmailSender {

    private String username;
    private String password;

    private Properties props;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("javax.net.ssl.debug", "all");
        props.setProperty("mail.host", "smtp.mail.ru");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.connectiontimeout", "60000");
        props.setProperty("mail.smtp.timeout", "60000");
        props.setProperty("mail.transport.protocol", "smtps");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.mime.charset", "UTF-8");
        props.setProperty("java.security.debug", "all");
        props.setProperty("logging.properties", "all");
    }

    public void sendMail(String from, String to, String text)
    {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from /*"from-email@gmail.com"*/));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to /*"to-email@gmail.com"*/));
            message.setSubject("Testing Subject");
            message.setText(text);
            //message.setFileName("lalka.txt");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
