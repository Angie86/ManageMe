/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author inftel07
 */
@Named(value = "profileBean")
@Dependent
public class ProfileBean {

    /**
     * Creates a new instance of ProfileBean
     */
    public String doShowProfile(){
    
        return ("profilePage");
    }
    
}
