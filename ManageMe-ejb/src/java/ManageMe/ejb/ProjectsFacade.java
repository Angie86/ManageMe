/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.Projects;
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
public class ProjectsFacade extends AbstractFacade<Projects> {
    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectsFacade() {
        super(Projects.class);
    }
    
    public Projects createNewProject(String name, String description, Users user){
        
        Projects project = new Projects();
        project.setDescription(description);
        project.setNameProject(name);
        project.setIdUser(user);
        em.persist(project);
        System.out.println(project.getIdProject());
        return project;
    
    }
    
    public Projects findProjectById(Long idProject){
        List<Projects> resultList = getEntityManager().createQuery("SELECT u FROM Projects u WHERE u.idProject = :idProject").setParameter("idProject", idProject).getResultList();
        if (resultList == null || resultList.isEmpty())
            return null;
        else
            return resultList.get(0);
    }
    
}
