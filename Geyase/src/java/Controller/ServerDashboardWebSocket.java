package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ARMERO
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/progress")
public class ServerDashboardWebSocket {

    private static ArrayList<Session> sessions = new ArrayList();

    @OnOpen
    public void open(Session session) {
        System.out.println("Session opened ==>");
        sessions.add(session);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println(message);
        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(ServerDashboardWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
