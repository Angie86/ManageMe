/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.ChatFacade;
import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.ProjectsFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.Chat;
import ManageMe.entity.DataUsers;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import ManageMe.model.ChatRecordModel;
import ManageMe.websocket.WebSocketServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jdk.nashorn.internal.parser.JSONParser;
import org.primefaces.json.JSONObject;

/**
 *
 * @author inftel08
 */
@ManagedBean
@SessionScoped
public class ChatBean {
    @EJB
    private DataUsersFacade dataUsersFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ChatFacade chatFacade;
    @EJB
    private ProjectsFacade projectsFacade;
    
    
    

     
    
    /**
     * Creates a new instance of chatBean
     */
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    protected String messageWritten;

    protected List<ChatRecordModel> lastListChat;
    
    public ChatBean() {
        
    }

    @PostConstruct
    public void init(){
        
        
        
    }
    
    public List<ChatRecordModel> getLastListChat() {
        return lastListChat;
    }

    public void setLastListChat(List<ChatRecordModel> lastListChat) {
        this.lastListChat = lastListChat;
    }

    public String getMessageWritten() {
        return messageWritten;
    }

    public void setMessageWritten(String messageWritten) {
        this.messageWritten = messageWritten;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String doShowChat(Projects project) {
        userBean.project = project;
        Chat oldChat = chatFacade.findChatById(userBean.project.getIdProject()); //CUIDADO!
        lastListChat = new ArrayList();
        
        if (oldChat.getChatRecord() != null) {
            String lastChat = new String(oldChat.getChatRecord());
            Gson gson2 = new Gson();
            java.lang.reflect.Type tipoListaChat = new TypeToken<List<ChatRecordModel>>(){}.getType();
            lastListChat = gson2.fromJson(lastChat, tipoListaChat);
        }
        
        System.out.println("AQUI SHOW CHAT"+ oldChat.getIdChat());
        return ("chatPage");

    }

    public void seeChatMessage() {
        
       messageWritten = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("message");

       
        Date date = new Date();
        Chat oldChat = chatFacade.findChatById(userBean.project.getIdProject());
        List<ChatRecordModel> listaChat = new ArrayList();
        
        if (oldChat.getChatRecord() != null) {
            String lastChat = new String(oldChat.getChatRecord());
            Gson gson2 = new Gson();
            java.lang.reflect.Type tipoListaChat = new TypeToken<List<ChatRecordModel>>(){}.getType();
            listaChat = gson2.fromJson(lastChat, tipoListaChat);
        }

        ChatRecordModel chatRecord = new ChatRecordModel(messageWritten);
        chatRecord.setIdUser(userBean.user.getIdUser());
        chatRecord.setPhotoUser(userBean.dataUsers.getPhotoUser());
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String strDate = sdfDate.format(date);
        System.out.println(strDate);
        chatRecord.setDateMessage(strDate);

        listaChat.add(chatRecord);

        Gson gson = new Gson();
        String representation = gson.toJson(listaChat);
        
        oldChat.setChatRecord(representation.getBytes());
        oldChat.setDateModification(date);
        chatFacade.edit(oldChat);

    }

    
    public String seeNameUser(Long idUser){
        Users userChat = usersFacade.findByIdUser(idUser);
        DataUsers dataUserChat = dataUsersFacade.findByIdUser(userChat);
        return dataUserChat.getNameUser();
    }
    
//    public void saveChatMessage() {
//
//        Date date = new Date();
//        List<ChatRecordModel> lista = new ArrayList();
//        ChatRecordModel chatRecord = new ChatRecordModel(messageWritten);
//        chatRecord.setIdUser(userBean.user.getIdUser());
//        chatRecord.setPhotoUser(userBean.dataUsers.getPhotoUser());
//        chatRecord.setDateMessage(date.toString());
//        lista.add(chatRecord);
//        lista.add(chatRecord);
//        lista.add(chatRecord);
//        final Gson gson = new Gson();
//        final String representation = gson.toJson(lista);
//        Chat pruebaChat = new Chat();
//        pruebaChat.setIdProject(projectsFacade.findProjectById(22L));
//        pruebaChat.setDateModification(date);
//        pruebaChat.setChatRecord(representation.getBytes());
//        chatFacade.create(pruebaChat);
//    }
    
}
