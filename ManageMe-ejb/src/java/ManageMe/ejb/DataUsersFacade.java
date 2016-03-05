/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.DataUsers;
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
public class DataUsersFacade extends AbstractFacade<DataUsers> {
    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DataUsersFacade() {
        super(DataUsers.class);
    }
    
    public DataUsers findByIdUser(Users user){
        List<DataUsers> resultList = getEntityManager().createQuery("SELECT u FROM DataUsers u WHERE u.idUser.idUser = :id").setParameter("id", user.getIdUser()).getResultList();
        if (resultList == null || resultList.isEmpty())
            return null;
        else
            return resultList.get(0);
    }
    
    
    
}
