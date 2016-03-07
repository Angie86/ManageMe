/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.InvitationsFacade;
import ManageMe.ejb.ProjectComponentsFacade;
import ManageMe.ejb.ProjectsFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.DataUsers;
import ManageMe.entity.Invitations;
import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author inftel06
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private InvitationsFacade invitationsFacade;

    @EJB
    private ProjectComponentsFacade projectComponentsFacade;
    @EJB
    private DataUsersFacade dataUsersFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ProjectsFacade projectsFacade;

    protected Users user;
    protected DataUsers dataUsers;
    protected String titulationIntroduced;
    protected List<Projects> listInvitationsProject;
    protected int numNotify;

    protected List<Projects> listProjects;

    protected String nameProject;

    protected Projects project;
    
//    @ManagedProperty(value="#{loginBean}")
//    protected LoginBean loginBean;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    @PostConstruct
    public void init() {
        
        //user = loginBean.user;
        
        //Buscar invitaciones usuario 
//        System.out.println("Usuario "+ user.getEmail());
//        
//        dataUsers = dataUsersFacade.findByIdUser(user);

        listProjects = new ArrayList();
//        List<ProjectComponents> listProjectComps = projectComponentsFacade.getProjectsListByUser(user);
//        for (ProjectComponents listProject : listProjectComps) {
//            listProjects.add(listProject.getIdProject());
//        }

        listInvitationsProject = new ArrayList();
//        List<Invitations> listInvitations = invitationsFacade.findInvitationUser(user);
//        for (Invitations listInvitation : listInvitations) {
//            listInvitationsProject.add(listInvitation.getIdProject());
//        }
//
//        numNotify = listInvitationsProject.size();
//        System.out.println("Número de notificaciones" + numNotify);
    }

    public List<Projects> getListInvitationsProject() {
        return listInvitationsProject;
    }

    public void setListInvitationsProject(List<Projects> listInvitationsProject) {
        this.listInvitationsProject = listInvitationsProject;
    }

    public DataUsers getDataUsers() {
        return dataUsers;
    }

    public void setDataUsers(DataUsers dataUsers) {
        this.dataUsers = dataUsers;
    }

    public String getTitulationIntroduced() {
        return titulationIntroduced;
    }

    public void setTitulationIntroduced(String titulationIntroduced) {
        this.titulationIntroduced = titulationIntroduced;
    }

    public List<Projects> getListProjects() {

        return listProjects;
    }

    public void setListProjects(List<Projects> listProjects) {
        this.listProjects = listProjects;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public int getNumNotify() {
        return numNotify;
    }

    public void setNumNotify(int numNotify) {
        this.numNotify = numNotify;
    }

//    public LoginBean getLoginBean() {
//        return loginBean;
//    }
//
//    public void setLoginBean(LoginBean loginBean) {
//        this.loginBean = loginBean;
//    }
    
    

    public String doGetIn() {

        String emailUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
        String nameUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        String fotoUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("photo");

        if (usersFacade.findByEmail(emailUsuario) == null) {
            Users newUser = new Users();
            newUser.setEmail(emailUsuario);
            usersFacade.create(newUser);
            DataUsers newDataUser = new DataUsers();
            newDataUser.setIdUser(newUser);
            newDataUser.setNameUser(nameUsuario);
            if (fotoUsuario.equals("undefined") || fotoUsuario.equals("")) {
                fotoUsuario = "../resources/images/user.png";
            }
            newDataUser.setPhotoUser(fotoUsuario);
            dataUsersFacade.create(newDataUser);
        }
        user = usersFacade.findByEmail(emailUsuario);
        
        
        dataUsers = dataUsersFacade.findByIdUser(user);
        
        project = projectsFacade.findLastProject(user);

        listProjects = new ArrayList();
        List<ProjectComponents> listProjectComps = projectComponentsFacade.getProjectsListByUser(user);
        for (ProjectComponents listProject : listProjectComps) {
            listProjects.add(listProject.getIdProject());
        }

        listInvitationsProject = new ArrayList();
        List<Invitations> listInvitations = invitationsFacade.findInvitationUser(user);
        for (Invitations listInvitation : listInvitations) {
            listInvitationsProject.add(listInvitation.getIdProject());
        }

        numNotify = listInvitationsProject.size();
        System.out.println("Número de notificaciones" + numNotify);
        return "profilePage";

    }

    public String doSetInformation() {
        System.out.println(titulationIntroduced);

        dataUsers.setTitulationUser(titulationIntroduced);
        dataUsersFacade.edit(dataUsers);
        return "profilePage";
    }

    public String doShowChat(Projects project) {
        this.project = project;
        return "chatPage";
    }

    public String doShowProfile() {
        return "profilePage";
        //projectComponentsFacade.getProjectsListByUser(user);
    }

    public void doSignOut() {
        user = new Users();
        dataUsers = new DataUsers();
        titulationIntroduced = "";

        listProjects = new ArrayList();

        nameProject = "";

        project = new Projects();

    }
}
