/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.ProjectComponentsFacade;
import ManageMe.ejb.ProjectsFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.DataUsers;
import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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

    List<Projects> listProjects;

    protected String nameProject;
    
    protected Projects project;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    @PostConstruct
    public void init() {
        listProjects = new ArrayList();
//        listProjects = new ArrayList();
        System.out.println("entra init");
        
//        System.out.println(user.getEmail());

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
    
    

    public void doGetIn() {

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
        System.out.println("dataUs" + dataUsers.getNameUser());

        List<ProjectComponents> listProjectComps = projectComponentsFacade.getProjectsListByUser(user);
        for (ProjectComponents listProject : listProjectComps) {
            System.out.println(listProject.getIdProject().getNameProject());
            listProjects.add(listProject.getIdProject());
        }

    }

    public String doSetInformation() {
        System.out.println(titulationIntroduced);

        dataUsers.setTitulationUser(titulationIntroduced);
        dataUsersFacade.edit(dataUsers);
        return "profilePage";
    }

    public String doShowProfile() {
        return "profilePage";
        //projectComponentsFacade.getProjectsListByUser(user);
    }

    
    public void doSignOut(){
        user = new Users();
   dataUsers = new DataUsers();
    titulationIntroduced = ""; 

    listProjects = new ArrayList();

    nameProject = "";
    
    project = new Projects();

    }
    
    
    public String doShowHistoric(Projects project){
        this.project = project;
        return "historicPage";
    }
    
    public String doShowChat(Projects project){
        this.project = project;
        return "chatPage";
    }
    
    public String doShowScrum(Projects project){
        this.project = project;
        return "scrumPage";
    }
    
    public String doShowInviteUser(Projects project){
        this.project = project;
        return "inviteUserPage";
    }
}
