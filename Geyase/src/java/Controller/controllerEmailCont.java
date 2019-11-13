/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Dell
 */
@RequestScoped
@ManagedBean
public class controllerEmailCont {

    private String mensaje;
    private String asunto;
    private String paraQuien = "armerosebas7@gmail.com";

    public void enviarMensaje() {

        String smtp = "smtp.gmail.com";
        int port = 587;
        String username = "sebarmero@gmail.com";
        String password = "gtxmeqxlahzpdqsv";

        Properties props = null;
        Session session = null;
        MimeMessage message = null;
        Address fromAddress = null;
        Address toAddress = null;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        message = new MimeMessage(session);
        try {
            message.setContent(mensaje, "text/plain");
            message.setSubject(asunto);
            fromAddress = new InternetAddress(username);
            message.setFrom(fromAddress);

            toAddress = new InternetAddress(paraQuien);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(smtp, port, username, password);
            if (!transport.isConnected()) {
                System.out.println("Inicio de sesion incorrecto");
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Envio mensaje");
        } catch (MessagingException me) {
            me.printStackTrace();
            System.out.println("Error");
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getParaQuien() {
        return paraQuien;
    }

    public void setParaQuien(String paraQuien) {
        this.paraQuien = paraQuien;
    }
}
