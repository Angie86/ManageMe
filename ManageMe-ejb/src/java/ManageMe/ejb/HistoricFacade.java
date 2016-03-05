/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.Historic;
import ManageMe.entity.Projects;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inftel06
 */
@Stateless
public class HistoricFacade extends AbstractFacade<Historic> {
    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricFacade() {
        super(Historic.class);
    }
    
    public List<Historic> findHistoricByProjectId(Long idProject){
        List<Historic> resultList = getEntityManager().createQuery("SELECT u FROM Historic u WHERE u.idProject.idProject = :idProject").setParameter("idProject", idProject).getResultList();
        if (resultList == null || resultList.isEmpty())
            return null;
        else
            return resultList;
    }
    
}
