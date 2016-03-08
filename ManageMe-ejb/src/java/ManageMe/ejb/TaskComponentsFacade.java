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
          
   public void setTaskComponent(Users user,Tasks task){
       TaskComponents taskComponents = new TaskComponents();
       taskComponents.setIdTask(task);
       taskComponents.setIdUser(user);
   
       em.persist(taskComponents);
   }
}
