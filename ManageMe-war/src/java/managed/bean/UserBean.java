/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed.bean;

import ManageMe.ejb.ProjectsFacade;
import ManageMe.entity.Projects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author inftel06
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    @EJB
    private ProjectsFacade projectsFacade;
    
    
    List<Projects> listProjects;
    protected String email;
    protected String name;
    protected String idUser;
    
    protected String photo;
    
    protected String nameProject;
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    @PostConstruct
    public void init(){
        //email = ContextFaces;
        name = "";
        listProjects = new ArrayList();
        
    } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    public String doNewProject(){
        Projects project = new Projects();
        project.setNameProject(nameProject);
        projectsFacade.create(project);
        
        
        return "";
    } 
    
    public void doShowIndex(){
        
        String emailUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
        String nameUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        String fotoUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("photo");
        System.out.println("Hola: "+nameUsuario+"\n\n\nCon email:"+emailUsuario);
        name=nameUsuario;
        photo = fotoUsuario;
        email = emailUsuario;
        
        //return "indexPage";
    }
    
    
}
