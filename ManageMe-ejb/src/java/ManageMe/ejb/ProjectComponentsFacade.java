/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.ProjectComponents;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import java.util.ArrayList;
import java.util.List;
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
   
   public List<ProjectComponents> getProjectsListByUser(Users user){
       
       List<Projects> listProject = new ArrayList();
       System.out.println(user.getIdUser());
       List<ProjectComponents> projectComponentsList = getEntityManager().
               createQuery("SELECT u FROM ProjectComponents u WHERE u.idUser = :idUser").setParameter("idUser", user).getResultList();
               //createQuery("SELECT p FROM ProjectComponents u, Projects p WHERE u.idUser = :idUser AND u.idProject = p.idProject").setParameter("idUser", user).getResultList();
//       for (ProjectComponents list : projectComponentsList) {
//           System.out.println("Entraaa");
//           System.out.println(list.getIdProject().getIdProject());
//          //listProject.add(list.getIdProject());
//       }    
       return projectComponentsList;
       
   }
   
      public List<ProjectComponents> getUsersListByProject(Projects project){

       List<ProjectComponents> projectComponentsList = getEntityManager().
               createQuery("SELECT u FROM ProjectComponents u WHERE u.idProject = :idProject").setParameter("idProject", project).getResultList();

	return projectComponentsList;

    }



    public ProjectComponents findProjectComponentByUserAndProject(Users user, Long idProject) {
	List<ProjectComponents> resultList = getEntityManager().createQuery("SELECT u FROM ProjectComponents u WHERE u.idUser.idUser = :iduser AND u.idProject.idProject = :idproject").setParameter("iduser", user.getIdUser()).setParameter("idproject", idProject).getResultList();
	if (resultList == null || resultList.isEmpty()) {
	    return null;
	} else {
	    return resultList.get(0);
	}
    }

}
