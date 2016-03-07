/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.InvitationsFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.Invitations;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel07
 */
@ManagedBean
@RequestScoped
public class InviteUserBean {

    @EJB
    private UsersFacade usersFacade;
    @EJB
    private InvitationsFacade invitationsFacade;

    @ManagedProperty(value = "#{userBean}")
    protected UserBean userBean;

    protected String email;

    /**
     * Creates a new instance of InviteUserBean
     */
    public InviteUserBean() {
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String doShowInviteUser(Projects project) {
        userBean.project = project;
        return ("inviteUserPage");
    }

    public String doInvite2() {
    
        
        Users userReceiver = usersFacade.findByEmail(email);
        Invitations invitation = new Invitations();

        if (userReceiver == null) {

            Users newUser = new Users();
            newUser.setEmail(email);
            usersFacade.create(newUser);
            userReceiver = usersFacade.findByEmail(email);
        } else {
            invitation.setIdUserreceiver(userReceiver);

        }
        
        invitationsFacade.sendInvitationEmail(userBean.project, email);

        invitation.setIdUserreceiver(userReceiver);
        invitation.setIdProject(userBean.project);
        invitation.setIdUsersender(userBean.user);
        invitationsFacade.create(invitation);
        return "inviteUserPage";
    }

     public String doInvite() {
         return "chatPage";
     }
}
