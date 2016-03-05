/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.Tasks;
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
    
}
