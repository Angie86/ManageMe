/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.DataUsers;
import ManageMe.entity.Users;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author inftel08
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private DataUsersFacade dataUsersFacade;
    
    
    
    protected Users user;
    protected String nameUsuario;
    protected String fotoUsuario;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNameUsuario() {
        return nameUsuario;
    }

    public void setNameUsuario(String nameUsuario) {
        this.nameUsuario = nameUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    
    
    
    public String doGetIn(){
        
        String emailUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
        nameUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        fotoUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("photo");

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
        
        
        return "profilePage";
    }
    
}
