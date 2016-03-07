/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.InvitationsFacade;
import ManageMe.ejb.ProjectComponentsFacade;
import ManageMe.entity.Invitations;
import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel07
 */
@ManagedBean
@RequestScoped
public class ProfileBean {
    @EJB
    private ProjectComponentsFacade projectComponentsFacade;

    @EJB
    private InvitationsFacade invitationsFacade;
    
    

    @ManagedProperty(value = "#{userBean}")
    protected UserBean userBean;

    protected List<Projects> listInvitationsProject;
    
    @PostConstruct
    public void init(){
    
    listInvitationsProject  = new ArrayList();
        List<Invitations> invitations = invitationsFacade.findInvitationUser(userBean.user);
        for (Invitations invitation : invitations) {
            listInvitationsProject.add(invitation.getIdProject());
        }
    }
    
    public ProfileBean() {
    }

    public List<Projects> getListInvitationsProject() {
        return listInvitationsProject;
    }

    public void setListInvitationsProject(List<Projects> listInvitationsProject) {
        this.listInvitationsProject = listInvitationsProject;
    }
    
    

    /**
     * Creates a new instance of ProfileBean
     */

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String doShowProfile() {

        return ("profilePage");
    }

    public String doRefuseInvitation(Projects project) {
        
        Invitations invitation = invitationsFacade.findInvitationByUserAndProject(project, userBean.user);

        invitationsFacade.remove(invitation);
        
        listInvitationsProject  = new ArrayList();
        List<Invitations> listInvitations = invitationsFacade.findInvitationUser(userBean.user);
        for (Invitations listInvitation : listInvitations) {
                listInvitationsProject.add(listInvitation.getIdProject());     
        }
        
        userBean.numNotify = listInvitationsProject.size();
        userBean.listInvitationsProject = listInvitationsProject;

        return "profilePage";
    }
    
    public String doAcceptInvitation(Projects project){
    
        ProjectComponents projectComponents = new ProjectComponents();
        projectComponents.setIdProject(project);
        projectComponents.setIdUser(userBean.user);
        projectComponentsFacade.create(projectComponents);
        
        userBean.listProjects = new ArrayList();
        List<ProjectComponents> listProjectComps = projectComponentsFacade.getProjectsListByUser(userBean.user);
        for (ProjectComponents listProject : listProjectComps) {
            userBean.listProjects.add(listProject.getIdProject());
        }
        
        
        
        return doRefuseInvitation(project);
        
    }
    

}
