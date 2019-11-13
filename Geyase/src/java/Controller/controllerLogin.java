/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ControllerDatabases.LoginFacade;
import Entitys.Login;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ARMERO
 */
@RequestScoped
@ManagedBean
public class controllerLogin implements Serializable {

    @EJB
    private LoginFacade loginFacade;

    private String usuarioLogiado;
    private String email;
    private String password;

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

    public String getUsuarioLogiado() {
        return usuarioLogiado;
    }

    public void setUsuarioLogiado(String usuarioLogiado) {
        this.usuarioLogiado = usuarioLogiado;
    }

    public void usuarioLogiado() {

    }

    public void iniciarSesion() throws IOException {
        
        //redireccion
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

        List<Login> datos = loginFacade.validarInicioSesion(email, password);

        if (datos.isEmpty()) {
            System.out.println("error a iniciar");
        } else {
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", datos);
            ctx.redirect(ctxPath + "/faces/Home/home.xhtml");
            
      
            
            // setUsuarioLogiado("Bienvenido " + datos.get(0).getUsuario());
            //setUsuarioLogiado(datos.get(0).getUsuario());
            //setUsuarioLogiado(usuarioLogiado = datos.get(0).getUsuario());
            //setUsuarioLogiado(datos.get(0).getEmail()); 

            System.out.println("Bienbenido" + datos.get(0).getUsuario());
        }
        
          
    }

    //Método para cerrar sesión
    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate(); //Cierre de sesion
        }
        return "../index.xhtml";// indicas a donde quieres direccionar después de cerrar sesión 
    }

}
