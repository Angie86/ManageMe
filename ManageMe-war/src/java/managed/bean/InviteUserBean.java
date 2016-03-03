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
 * @author inftel07
 */
@Named(value = "inviteUserBean")
@Dependent
public class InviteUserBean {

    /**
     * Creates a new instance of InviteUserBean
     */
    public InviteUserBean() {
    }
    
    public String doShowInviteUser(){
    
        return ("inviteUserPage");
    }
    
}
