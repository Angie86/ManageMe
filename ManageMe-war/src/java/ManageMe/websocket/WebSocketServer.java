/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.websocket;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
//import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.SessionScoped;

@ApplicationScoped
@ServerEndpoint("/actions")
public class WebSocketServer {
    
    @Inject
    private SessionHandler sessionHandler;
    
    @PostConstruct
    public void afterCreate() {
        System.out.println("DeviceWebSocketServer created");
    }    

    @OnOpen
        public void open(Session session) {
             //sessionHandler.removeDevice(id,session);   
            sessionHandler.addSession(session);
         
            System.out.println("DeviceWebSocketServer opened");
    }

    @OnClose
        public void close(Session session) throws IOException {
            
            sessionHandler.removeSession(session);
          
            System.out.println("DeviceWebSocketServer closed");
    }

    public void funcion(){
        
    }    
    @OnError
        public void onError(Throwable error) {
            Logger.getLogger("Error");
    }

    @OnMessage
        public void handleMessage(String message, Session session) {
            try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
                
            if ("add".equals(jsonMessage.getString("action"))) {
                Device device = new Device();
                device.setName(jsonMessage.getString("name"));
                device.setDescription(jsonMessage.getString("description"));
                device.setType(jsonMessage.getString("type"));
                device.setStatus("Off");
                sessionHandler.addDevice(device);
                //sessionHandler.addDevice(device, session);
            }
                               
            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeDevice(id,session);
            }

            if ("toggle".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.toggleDevice(id,session);
            }
        }
    }
}    
