/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;
 
import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.InvitationsFacade;
import ManageMe.ejb.ProjectComponentsFacade;
import ManageMe.ejb.TaskComponentsFacade;
import ManageMe.ejb.TasksFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.DataUsers;
import ManageMe.entity.Invitations;
import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import ManageMe.entity.TaskComponents;
import ManageMe.entity.Tasks;
import ManageMe.entity.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
 
/**
 *
 * @author inftel06
 */
@ManagedBean
@RequestScoped
public class ProfileProjectBean {
 
    @ManagedProperty(value = "#{userBean}")
    protected UserBean userBean;
 
    @EJB
    private InvitationsFacade invitationsFacade;
    @EJB
    private ProjectComponentsFacade projectComponentsFacade;
    @EJB
    private DataUsersFacade dataUsersFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private TaskComponentsFacade taskComponentsFacade;
    @EJB
    private TasksFacade tasksFacade;
 
    protected String nameProject;
    protected List<DataUsers> listDataUsers;
    protected List<Users> listUsers;
    protected String email;
 
    protected Projects project;
 
    /**
     * Creates a new instance of ProfileProjectBean
     */
    public ProfileProjectBean() {
    }
 
    @PostConstruct
    public void init() {
 
    // System.out.println("Proejt"+ userBean.nameProject);
    //listUsers = usersFacade.findAllUsersByIdProject(userBean.project);
    this.project = userBean.project;
    nameProject = project.getNameProject();
 
    System.out.println("Proejt" + project.getNameProject());
 
    listUsers = new ArrayList();
    List<ProjectComponents> listProjectComps = projectComponentsFacade.getUsersListByProject(this.project);
    for (ProjectComponents listProject : listProjectComps) {
 
        listUsers.add(listProject.getIdUser());
    }
 
    System.out.println("INIT profile Project");
    }
 
    public String getNameProject() {
 
    return nameProject;
    }
 
    public void setNameProject(String nameProject) {
    this.nameProject = nameProject;
    }
 
    public List<DataUsers> getListDataUsers() {
    return listDataUsers;
    }
 
    public void setListDataUsers(List<DataUsers> listDataUsers) {
    this.listDataUsers = listDataUsers;
    }
 
    public List<Users> getListUsers() {
    //return listUsers;
 
    return listUsers;
    }
 
    public void setListUsers(List<Users> listUsers) {
    this.listUsers = listUsers;
    }
 
    public String getEmail() {
    return email;
    }
 
    public void setEmail(String email) {
    this.email = email;
    }
 
    public UserBean getUserBean() {
    return userBean;
    }
 
    public void setUserBean(UserBean userBean) {
    this.userBean = userBean;
    }
 
    public Projects getProject() {
    return project;
    }
 
    public void setProject(Projects project) {
    this.project = project;
    }
 
    public String getPhotoUser(Users user) {
    DataUsers dataUser = dataUsersFacade.findByIdUser(user);
    return dataUser.getPhotoUser();
    }
 
    public String getNameUser(Users user) {
    DataUsers dataUser = dataUsersFacade.findByIdUser(user);
    return dataUser.getNameUser();
    }
 
    public String doNewUserProject() {
 
    return "profileProjectPage";
    }
 
    public List<String> completeMail(String query) {
 
    List<String> listaEmail = usersFacade.findAllMails();
    List<String> results = new ArrayList();
 
    for (String mail : listaEmail) {
        if (mail.contains(query)) {
        results.add(mail);
        }
    }
 
    return results;
    }
 
    public String doShowProfileProject(Projects project) {
    this.project = project;
    return "profileProjectPage";
 
    }
 
    public String doInvite() {
    System.out.println("Entra en invite");
 
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
 
    //Añadido para actualizar
    userBean.listInvitationsProject = new ArrayList();
    List<Invitations> listInvitations = invitationsFacade.findInvitationUser(userBean.user);
    for (Invitations listInvitation : listInvitations) {
        userBean.listInvitationsProject.add(listInvitation.getIdProject());
    }
 
    userBean.numNotify = userBean.listInvitationsProject.size();
 
    return "profileProjectPage";
    }
 
    public Boolean isScrumMaster() {
 
    if (userBean.user.getIdUser() == userBean.project.getIdUser().getIdUser()) {
        return true;
    } else {
        return false;
    }
 
    }
 
    public String deleteProjectComponent(Users user) {
 
    List<Tasks> listTasks = tasksFacade.findTaskByIdProyect(userBean.project.getIdProject());
 
    for (Tasks task : listTasks) {
        List<TaskComponents> taskComponentList = taskComponentsFacade.findTaskComponentByIdTask(task.getIdTask());
 
        for (TaskComponents taskComponent : taskComponentList) {
        if (taskComponent.getIdUser().getIdUser() == user.getIdUser().longValue()) {
            taskComponentsFacade.remove(taskComponent);
        }
        }
 
        taskComponentList = taskComponentsFacade.findTaskComponentByIdTask(task.getIdTask());
        if (taskComponentList == null) {
        tasksFacade.remove(task);
        }
 
    }
 
   
    ProjectComponents projectComponent = projectComponentsFacade.findProjectComponentByUserAndProject(user, userBean.project.getIdProject());
    projectComponentsFacade.remove(projectComponent);
    listUsers = new ArrayList();
    List<ProjectComponents> listProjectComps = projectComponentsFacade.getUsersListByProject(this.project);
    for (ProjectComponents listProject : listProjectComps) {
        listUsers.add(listProject.getIdUser());
    }
 
    return "profileProject";
    }
   
    public void deleteProject(){
   
    }
 
}