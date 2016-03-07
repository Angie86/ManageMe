/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.ejb;

import ManageMe.entity.DataUsers;
import ManageMe.entity.Invitations;
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inftel06
 */
@Stateless
public class InvitationsFacade extends AbstractFacade<Invitations> {
    @EJB
    private DataUsersFacade dataUsersFacade;
    @EJB
    private Mail mail;

    
    @PersistenceContext(unitName = "ManageMe-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvitationsFacade() {
        super(Invitations.class);
    }

    public void sendInvitationEmail(Projects project, String email) {
        DataUsers dataUser = em.find(DataUsers.class, project.getIdUser().getIdUser());
        //String destino = transaction.getMemberNumber().getEmail();
        String asunto;
        String mensaje;
        //if (transaction.getStateOrder().equals("pagado")) {
            asunto = "Invitación al proyecto " + project.getNameProject()+ " desde ManageMe";
            mensaje = "Ha sido invitado al proyecto " + project.getNameProject()
                    + " por " + dataUser.getNameUser() + "." + "\n"
                    + " Puede acudir a la página de ManageMe para aceptar la invitación.\n\n" 
                    + "Un saludo, \n ManageMe";
            
        System.out.println("Email"+asunto+mensaje+email);           
        mail = new Mail(asunto, mensaje, email);
        mail.sendMail();

    }

    public List<Invitations> findInvitationUser(Users user) {
        
        List<Invitations> invitations = getEntityManager().createQuery("SELECT u FROM Invitations u WHERE u.idUserreceiver = :idUserreceiver").setParameter("idUserreceiver", user).getResultList();
       
        
        return invitations;
       
    }
    
    public Invitations findInvitationByUserAndProject(Projects project, Users user){
        System.out.println("hola" + project + "  " + user);
    List<Invitations> invitations = getEntityManager().createQuery("SELECT u FROM Invitations u WHERE u.idUserreceiver = :idUserreceiver AND u.idProject = :idProject").
            setParameter("idUserreceiver", user).
            setParameter("idProject", project).
            getResultList();
      
    return invitations.get(0);   
    }
    

    

}
