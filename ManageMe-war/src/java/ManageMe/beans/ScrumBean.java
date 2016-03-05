/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



/**
 *
 * @author inftel08
 */

@ManagedBean
@RequestScoped
public class ScrumBean {

    /**
     * Creates a new instance of scrumBean
     */
    public ScrumBean() {
    }
    
    public String doShowScrum(){
    
        return ("scrumPage");
    }
    
}
