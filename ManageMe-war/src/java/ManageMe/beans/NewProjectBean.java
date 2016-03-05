/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;


import ManageMe.ejb.ChatFacade;
import ManageMe.ejb.ProjectComponentsFacade;
import ManageMe.ejb.ProjectsFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.Chat;
import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author inftel08
 */

@ManagedBean
@RequestScoped
public class NewProjectBean implements Serializable {
    @EJB
    private ChatFacade chatFacade;
    @EJB
    private ProjectComponentsFacade projectComponentsFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ProjectsFacade projectsFacade;
    
   
    @ManagedProperty(value="#{userBean}")
    protected UserBean userBean;


    private String name;
    private String description;
    
    
    public NewProjectBean() {
    }
    
    @PostConstruct
    public void init(){
    
        
    //name = userBean.user.getEmail();
    
    } 
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

    public ProjectsFacade getProjectsFacade() {
        return projectsFacade;
    }

    public void setProjectsFacade(ProjectsFacade projectsFacade) {
        this.projectsFacade = projectsFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }



    /**
     * Creates a new instance of NewProjectBean
     */

    
    public String doNewProject(){

        Projects  project = projectsFacade.createNewProject(name,description,userBean.user);
        projectComponentsFacade.setProjectComponent(userBean.user,project);
        userBean.project = project;
        System.out.println("llega");
        Chat newChat = new Chat();
        newChat.setIdProject(userBean.project);
        System.out.println("Nombre del proyecto " + userBean.project.getNameProject());
             
        newChat.setDateModification(new Date());
        
        chatFacade.create(newChat);
        
        
        userBean.listProjects = new ArrayList();
        List<ProjectComponents> listProjectComps = projectComponentsFacade.getProjectsListByUser(userBean.user);
        for (ProjectComponents listProject : listProjectComps) {
            System.out.println(listProject.getIdProject().getNameProject());
            userBean.listProjects.add(listProject.getIdProject());
        }
        //Crear Chat nuevo
        return "";
    } 
    
}
