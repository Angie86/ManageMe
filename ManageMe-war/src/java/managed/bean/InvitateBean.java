/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author inftel08
 */
@Named(value = "invitateBean")
@Dependent
public class InvitateBean {

    /**
     * Creates a new instance of invitateBean
     */
    public InvitateBean() {
    }
    
    public String doShowInvitate(){
    
        return ("invitatePage");
    }
    
}
