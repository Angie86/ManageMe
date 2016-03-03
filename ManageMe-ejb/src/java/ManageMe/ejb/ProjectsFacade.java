/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.Projects;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author noussairelharrak
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
    
     public List<String> getListProjectsbyIdUser(int idUser) {

        //Query q = em.createQuery("SELECT p  FROM Projects p WHERE p.idProject.");

        //List<String> listaCiudadSearch = q.getResultList();

        return null;//listaCiudadSearch;
    }
    
}
