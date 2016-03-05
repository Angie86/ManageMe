/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.model;

/**
 *
 * @author inftel06
 */
public class ChatRecordModel {
    
    private Long idUser;
    private String message;
    private String dateMessage;
    private String photoUser;

    public ChatRecordModel() {
    }

    public ChatRecordModel(String message) {
        this.message = message;
    }

    
    public ChatRecordModel(Long idUser, String message, String dateMessage, String photoUser) {
        this.idUser = idUser;
        this.message = message;
        this.dateMessage = dateMessage;
        this.photoUser = photoUser;
    }
    
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }
    
    
    
}
