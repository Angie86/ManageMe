/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;
 
import ManageMe.entity.Projects;
import ManageMe.entity.Tasks;
import ManageMe.entity.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
/**
 *
 * @author inftel06
 */
@Stateless
public class TasksFacade extends AbstractFacade<Tasks> {
    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;
 
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 
    public TasksFacade() {
        super(Tasks.class);
    }
   
    public List<Tasks> findTaskByIdProyect(Long idProyecto){
        List<Tasks> resultList = getEntityManager().createQuery("SELECT u FROM Tasks u WHERE u.idProject.idProject = :id_proyecto").setParameter("id_proyecto", idProyecto).getResultList();
        if (resultList == null || resultList.isEmpty())
            return null;
        else
            return resultList;
    }
    public Tasks getTaskById(Long id_Task){
        List<Tasks> resultList = getEntityManager().createQuery("SELECT u FROM Tasks u WHERE u.idTask = :id_Task").setParameter("id_Task", id_Task).getResultList();
        if (resultList == null || resultList.isEmpty())
            return null;
        else
            return resultList.get(0);
    }
    /*
    public String editStatus(Long id_Task,String status){
        String result;
        result = getEntityManager().createQuery("UPDATE Tasks u SET u.state = :status WHERE u.idTask = :id_Task ").setParameter("status", status).setParameter("id_Task",id_Task).toString();
        if (result == null || result.isEmpty())
            return null;
        else
            return result;
    }*/
   
   
    public Tasks createNewTask(Projects project,String nameTask,String description,Date date_ini,Long duration,String state, Users user){
       
        Tasks myTask = new Tasks();
        myTask.setDateInit(date_ini);
        myTask.setDuration(duration);
        myTask.setNameTask(nameTask);
        myTask.setDescription(description);
        myTask.setState(state);
        myTask.setIdProject(project);
        myTask.setIdUsercreator(user);
 
        em.persist(myTask);
     
        return myTask;
   
    }
   
}
