/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ControllerDatabases.LoginFacade;
import Entitys.Login;
import java.io.Serializable;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

/**
 *
 * @author ARMERO
 */
@SessionScoped
@ManagedBean
public class controllerCreateUser implements Serializable {

    @EJB
    LoginFacade controllerLogin;

    private String usuario;
    private String email;
    private String password;
    private String mensaje = "Usuario creado,Bienvenido a Inventarios Geyase";
    private String asunto = "Usuario Creado ";

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void create() {
        //redireccion
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();

        try {
            Login obj = new Login();
            obj.setUsuario(usuario);
            obj.setEmail(email);
            obj.setPassword(password);

            enviarMensaje();

            controllerLogin.create(obj);
            //redireccion
            ctx.redirect(ctxPath + "/faces/Users/loginView.xhtml");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("INFORMACION", "USUARIO CREADA"));

        } catch (Exception e) {
            System.out.println("Error, el correo ya existe");
            System.out.println("error" + e);
        }

    }

    //enviar mensaje email
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
            message.setContent(" <p style='font-style: italic;font-size: 20px;color: rgb(21, 73, 94);text-align: -webkit-center;'>Gracias por registrarse en nuestra página web inventarios Geyase, ahora puedes disfrutar de nuestros servicios</p>", "text/html");

            message.setSubject(asunto);
            fromAddress = new InternetAddress(username);
            message.setFrom(fromAddress);

            toAddress = new InternetAddress(email);
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

    public String redirigir() {
        return "Users/loginView.xhtml";
    }

    public String redirigir2() {
        return "Users/userCreate.xhtml";
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

}
