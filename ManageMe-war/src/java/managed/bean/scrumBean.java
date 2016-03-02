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
@Named(value = "scrumBean")
@RequestScoped
public class scrumBean {

    /**
     * Creates a new instance of scrumBean
     */
    public scrumBean() {
    }
    
    public String doShowScrum(){
    
        return ("scrumPage");
    }
    
}
