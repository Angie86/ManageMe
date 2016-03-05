/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.entity.Projects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;



/**
 *
 * @author inftel08
 */

@ManagedBean
@RequestScoped
public class ScrumBean {

    
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    /**
     * Creates a new instance of scrumBean
     */
    public ScrumBean() {
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    
    public String doShowScrum(Projects project){
        userBean.project = project;
    
        return ("scrumPage");
    }
    
}
