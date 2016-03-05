/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.entity.Projects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author inftel08
 */

@ManagedBean
@RequestScoped
public class HistoricBean {
    
    @ManagedProperty(value ="#{userBean}")
    protected UserBean userBean;

    /**
     * Creates a new instance of historicBean
     */
    public HistoricBean() {
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    
    public String doShowHistoric(Projects project){
        userBean.project = project;
        return ("historicPage");
    }

    
    
}
