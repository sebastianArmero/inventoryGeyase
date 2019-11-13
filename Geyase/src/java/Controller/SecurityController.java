/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entitys.Login;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Dell
 */
@Named
@ViewScoped
public class SecurityController  implements Serializable {

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Login datos = (Login) context.getExternalContext().getSessionMap().get("login");

            if (datos == null) {
                context.getExternalContext().redirect("../Permissions/permissions.xhtml");
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }

}
