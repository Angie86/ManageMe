/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed.bean;

import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.ProjectsFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.DataUsers;
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
    private DataUsersFacade dataUsersFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ProjectsFacade projectsFacade;

    protected Users user;

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
    public void init() {
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String doNewProject() {
        Projects project = new Projects();
        project.setNameProject(nameProject);
        projectsFacade.create(project);

        return "";
    }

    public void doGetIn() {

        String emailUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
        String nameUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        String fotoUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("photo");

        name = nameUsuario;
        photo = fotoUsuario;
        email = emailUsuario;

        if (usersFacade.findByEmail(email) == null) {
            Users newUser = new Users();
            newUser.setEmail(email);
            usersFacade.create(newUser);
            DataUsers newDataUser = new DataUsers();
            newDataUser.setIdUser(newUser);
            newDataUser.setNameUser(nameUsuario);
            newDataUser.setPhotoUser(fotoUsuario);
            dataUsersFacade.create(newDataUser);
        }
        user = usersFacade.findByEmail(emailUsuario);
        System.out.println(user.getIdUser());
    }

}
