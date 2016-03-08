/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.ProjectsFacade;
import ManageMe.ejb.TasksFacade;
import ManageMe.entity.Projects;
import ManageMe.entity.Tasks;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import ManageMe.entity.Projects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author inftel08
 */
@ManagedBean
@ViewScoped
public class ScrumBean {

    @EJB
    private ProjectsFacade projectsFacade;
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
        userBean.project = project; return "scrumPage";
 }

    @PostConstruct
    public void init(){
        nombreTarea = "";
        duracionTarea = 0L;
        description = "";
        state = "ini";
        listaTasks = tasksFacade.findTaskByIdProyect(userBean.project.getIdProject());
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


    

 

    public String createTask() {
        
        
        Tasks task = new Tasks();
        task.setNameTask(nombreTarea);
        task.setDescription(description);
        task.setDuration(duracionTarea);
        
        Projects projectNew = projectsFacade.findProjectById(userBean.project.getIdProject());
        task.setIdProject(projectNew);
        task.setIdUsercreator(userBean.user);
        task.setState("To Do");
        task.setDateInit(new Date());

        tasksFacade.create(task);
        

        return ("scrumPage");
    }
    
    public void ListTasks() {
        listaTasks = tasksFacade.findTaskByIdProyect(22L);
    }

    public void changeInput(ValueChangeEvent event) {
        this.state = event.getNewValue().toString();
    }
    
    public String saveTask(Long idTarea, String estado) {
        
        state = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("entrada");
        
        String stateDB = "";
        switch (state) {
        case "toDoUl":
            stateDB = "To Do";
            break;
        case "doingUl":
            stateDB = "Doing";
            break;
        case "doneUl":
            stateDB = "Done";
        }
        
        //tasksFacade.editStatus(idTarea, state);
        Tasks myTask = new Tasks();
          
        myTask = tasksFacade.getTaskById(idTarea);
        myTask.setState(stateDB);
        tasksFacade.edit(myTask);
        
        System.out.println("idTarea "+idTarea+" y el estado es "+stateDB);
        this.state="ini";
        return ("scrumPage");
    } 
}