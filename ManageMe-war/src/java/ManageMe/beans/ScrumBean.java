/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.TasksFacade;
import ManageMe.ejb.UsersFacade;
import ManageMe.entity.Projects;
import ManageMe.entity.Tasks;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import ManageMe.entity.Projects;
import java.util.ArrayList;
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
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private TasksFacade tasksFacade;
    
    

    protected String nombreTarea;
    protected Long duracionTarea;
    protected String description;
    protected String fechaInicio;
    protected List<Tasks> listaTasks;
    protected String state;
    
    @ManagedProperty(value="#{userBean}")
    protected UserBean userBean;

  
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
        return "scrumPage";
 }

    @PostConstruct
    public void init(){
        nombreTarea = "";
        duracionTarea = 0L;
        description = "";
        state = "";
        listaTasks = tasksFacade.findTaskByIdProyect(22L);
    }

    
    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    

    public Long getDuracionTarea() {
        return duracionTarea;
    }

    public List<Tasks> getListaTasks() {
        return listaTasks;
    }

    public void setListaTasks(List<Tasks> listaTasks) {
        this.listaTasks = listaTasks;
    }

    public void setDuracionTarea(Long duracionTarea) {
        this.duracionTarea = duracionTarea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    

 

    public String createTask2() {
        
        
        Tasks task = new Tasks();
        task.setNameTask(nombreTarea);
        task.setDescription(description);
        task.setDuration(duracionTarea);
        
        Projects newProject = new Projects();
        newProject.setIdProject(22L);
        task.setIdProject(newProject);
        task.setIdUsercreator(userBean.user);
        task.setState("To Do");
        task.setDateInit(new Date());

        tasksFacade.create(task);
        

        return ("scrumPage");
    }
    
    public void ListTasks() {
        listaTasks = tasksFacade.findTaskByIdProyect(22L);
    }
    
    public String saveTask(Long idTarea) {
        System.out.println("llega al salvar"+idTarea+" y el estado es "+state);
        return ("scrumPage");
    }
    
   
    
    
    //PASAR A INVITEUSERBEAN
    
     protected String busqueda;
    

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    
    public List<String> completeMail(String query){
        
        List<String> listaEmail = usersFacade.findAllMails();
        List<String> results = new ArrayList<String>();

        for (String mail : listaEmail) {
            if (mail.contains(query)) {
                results.add(mail);
            }
        }

        return results;
    }
    
    
    public void doBuscarMail(){
        System.out.println("busqueda " + busqueda);
    }
    
    public String createTask(){
        return "scrumPage";
    }
}
