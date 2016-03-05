/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



/**
 *
 * @author inftel07
 */

@ManagedBean
@RequestScoped
public class ProfileBean {

    /**
     * Creates a new instance of ProfileBean
     */
    public String doShowProfile(){
    
        return ("profilePage");
    }
    
}
