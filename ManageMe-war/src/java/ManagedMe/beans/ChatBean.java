/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedMe.beans;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel08
 */
@ManagedBean
@RequestScoped
public class ChatBean {

    /**
     * Creates a new instance of chatBean
     */
    public ChatBean() {
    }
    
    public String doShowChat(){
    
        return ("chatPage");
    }
    
}