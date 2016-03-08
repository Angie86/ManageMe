/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import ManageMe.entity.TaskComponents;
import ManageMe.entity.Tasks;
import ManageMe.entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inftel06
 */
@Stateless
public class TaskComponentsFacade extends AbstractFacade<TaskComponents> {

    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public TaskComponentsFacade() {
	super(TaskComponents.class);
    }

    public List<TaskComponents> findTaskComponentByIdUser(Long idUser) {
	List<TaskComponents> resultList = getEntityManager().createQuery("SELECT u FROM TaskComponents u WHERE u.idUser.idUser = :idUser").setParameter("idUser", idUser).getResultList();
	if (resultList == null || resultList.isEmpty()) {
	    return null;
	} else {
	    return resultList;
	}
    }
    
   
    public List<TaskComponents> findTaskComponentByIdTask(Long idTask) {
    List<TaskComponents> resultList = getEntityManager().createQuery("SELECT u FROM TaskComponents u WHERE u.idTask.idTask = :idTask").setParameter("idTask", idTask).getResultList();
    if (resultList == null || resultList.isEmpty()) {
        return null;
    } else {
        return resultList;
    }
    }
    
    

          
   public void setTaskComponent(Users user,Tasks task){
       TaskComponents taskComponents = new TaskComponents();
       taskComponents.setIdTask(task);
       taskComponents.setIdUser(user);
   
       em.persist(taskComponents);
   }
   
   
   

    

    }

