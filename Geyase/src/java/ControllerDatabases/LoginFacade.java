/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerDatabases;

import Entitys.Login;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dell
 */
@Stateless
public class LoginFacade extends AbstractFacade<Login> {

    @PersistenceContext(unitName = "GeyasePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }
     public List<Login> validarInicioSesion(String email, String password){
        Query cons = em.createNativeQuery("SELECT * FROM login WHERE email = '"+email+"' and password = '"+password+"'", Login.class);
        
       return cons.getResultList();
        
    }
    
}
