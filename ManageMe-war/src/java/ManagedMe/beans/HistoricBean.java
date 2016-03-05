/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedMe.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

    /**
     * Creates a new instance of historicBean
     */
    public HistoricBean() {
    }
    
    public String doShowHistoric(){
    
        return ("historicPage");
    }

    
    
}
