/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author noussairelharrak
 */
@Entity
@Table(name = "INVITATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitations.findAll", query = "SELECT i FROM Invitations i"),
    @NamedQuery(name = "Invitations.findByIdInvitation", query = "SELECT i FROM Invitations i WHERE i.idInvitation = :idInvitation")})
public class Invitations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_invitation", sequenceName = "SEQ_INVITATION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_invitation")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INVITATION")
    private Long idInvitation;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Projects idProject;
    @JoinColumn(name = "ID_USERSENDER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUsersender;
    @JoinColumn(name = "ID_USERRECEIVER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUserreceiver;

    public Invitations() {
    }

    public Invitations(Long idInvitation) {
	this.idInvitation = idInvitation;
    }

    public Long getIdInvitation() {
	return idInvitation;
    }

    public void setIdInvitation(Long idInvitation) {
	this.idInvitation = idInvitation;
    }

    public Projects getIdProject() {
	return idProject;
    }

    public void setIdProject(Projects idProject) {
	this.idProject = idProject;
    }

    public Users getIdUsersender() {
	return idUsersender;
    }

    public void setIdUsersender(Users idUsersender) {
	this.idUsersender = idUsersender;
    }

    public Users getIdUserreceiver() {
	return idUserreceiver;
    }

    public void setIdUserreceiver(Users idUserreceiver) {
	this.idUserreceiver = idUserreceiver;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (idInvitation != null ? idInvitation.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Invitations)) {
	    return false;
	}
	Invitations other = (Invitations) object;
	if ((this.idInvitation == null && other.idInvitation != null) || (this.idInvitation != null && !this.idInvitation.equals(other.idInvitation))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ManageMe.entity.Invitations[ idInvitation=" + idInvitation + " ]";
    }
    
}
