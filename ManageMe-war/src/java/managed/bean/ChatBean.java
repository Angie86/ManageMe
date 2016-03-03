/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed.bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author inftel08
 */
@Named(value = "chatBean")
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
