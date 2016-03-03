/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inftel06
 */
@Stateless
public class ProjectComponentsFacade extends AbstractFacade<ProjectComponents> {
    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectComponentsFacade() {
        super(ProjectComponents.class);
    }
    
   public void setProjectComponent(Users user,Projects project){
       ProjectComponents projectComponents = new ProjectComponents();
       
       projectComponents.setIdProject(project);
       projectComponents.setIdUser(user);
       em.persist(projectComponents);
            
   }
    
}
