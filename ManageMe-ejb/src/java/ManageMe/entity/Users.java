/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author inftel06
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByIdUser", query = "SELECT u FROM Users u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_users", sequenceName = "SEQ_USERS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private Long idUser;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsercreator")
    private Collection<Tasks> tasksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<TaskComponents> taskComponentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<ProjectComponents> projectComponentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<DataUsers> dataUsersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsersender")
    private Collection<Invitations> invitationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserreceiver")
    private Collection<Invitations> invitationsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Historic> historicCollection;

    public Users() {
    }

    public Users(Long idUser) {
        this.idUser = idUser;
    }

    public Users(Long idUser, String email) {
        this.idUser = idUser;
        this.email = email;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    @XmlTransient
    public Collection<TaskComponents> getTaskComponentsCollection() {
        return taskComponentsCollection;
    }

    public void setTaskComponentsCollection(Collection<TaskComponents> taskComponentsCollection) {
        this.taskComponentsCollection = taskComponentsCollection;
    }

    @XmlTransient
    public Collection<ProjectComponents> getProjectComponentsCollection() {
        return projectComponentsCollection;
    }

    public void setProjectComponentsCollection(Collection<ProjectComponents> projectComponentsCollection) {
        this.projectComponentsCollection = projectComponentsCollection;
    }

    @XmlTransient
    public Collection<DataUsers> getDataUsersCollection() {
        return dataUsersCollection;
    }

    public void setDataUsersCollection(Collection<DataUsers> dataUsersCollection) {
        this.dataUsersCollection = dataUsersCollection;
    }

    @XmlTransient
    public Collection<Invitations> getInvitationsCollection() {
        return invitationsCollection;
    }

    public void setInvitationsCollection(Collection<Invitations> invitationsCollection) {
        this.invitationsCollection = invitationsCollection;
    }

    @XmlTransient
    public Collection<Invitations> getInvitationsCollection1() {
        return invitationsCollection1;
    }

    public void setInvitationsCollection1(Collection<Invitations> invitationsCollection1) {
        this.invitationsCollection1 = invitationsCollection1;
    }

    @XmlTransient
    public Collection<Historic> getHistoricCollection() {
        return historicCollection;
    }

    public void setHistoricCollection(Collection<Historic> historicCollection) {
        this.historicCollection = historicCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManageMe.ejb.Users[ idUser=" + idUser + " ]";
    }

}
