/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.entity.Projects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author Angie
 */
@ManagedBean
@SessionScoped
public class principalBean {


    
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    /**
     * Creates a new instance of principalBean
     */
        
    public principalBean() {
        
    }
    
    @PostConstruct
    public void init(){
         
    }
    
        public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    

    
    public String doShowPrincipal(Projects project){
        userBean.project = project;
        System.out.println(project.getIdProject());
        return "principalPage";
    }
    
    
}
