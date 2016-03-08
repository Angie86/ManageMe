/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.websocket;

import ManageMe.beans.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

@ApplicationScoped
public class SessionHandler {

    private int deviceId = 0;

    private final HashMap<Long, ArrayList<Session>> projectSession = new HashMap<>();
    private final HashMap<Long, ArrayList<Device>> projectMessages = new HashMap<>();

//    private final ArrayList<Session> sessions = new ArrayList<>();
//    private final ArrayList<Device> devices = new ArrayList<>();

    @PostConstruct
    public void afterCreate() {
        System.out.println("DeviceSessionHandler created");
    }

    public void addSession(long idProject, Session session) {
        System.out.println(idProject);

        ArrayList<Session> sessions = projectSession.get(idProject);

        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        sessions.add(session);
        projectSession.put(idProject, sessions);

        ArrayList<Device> deviceList = projectMessages.get(idProject);
        if (deviceList == null) {
            deviceList = new ArrayList();
        }

        for (Device device : deviceList) {

            JsonObject addMessage = createAddMessage(device);
            sendToSession(idProject, session, addMessage);

        }

    }

    public void removeSession(long idProject,Session session) {
        projectSession.remove(idProject);
    }


    public void addDevice(long idProject, Device device) {

        ArrayList<Device> deviceList = projectMessages.get(idProject);

        if (deviceList == null) {
            deviceList = new ArrayList<>();
        }
        deviceList.add(device);
        projectMessages.put(idProject, deviceList);
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(idProject,addMessage);             
             
    }
    
    private JsonObject createAddMessage(Device device) {
   
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .add("description", device.getDescription())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(long idProject,JsonObject message) {
        ArrayList<Session> sessions = projectSession.get(idProject);
        for (Session session : sessions) {

            sendToSession(idProject,session, message);
        }
    }

    private void sendToSession(long idProject,Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            ArrayList<Session> sessions = projectSession.get(idProject);
            if(sessions != null)
            sessions.remove(session);
            Logger.getLogger("");
        }

    }
}
