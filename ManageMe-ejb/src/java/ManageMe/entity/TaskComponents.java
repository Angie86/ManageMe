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
 * @author inftel06
 */
@Entity
@Table(name = "TASK_COMPONENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskComponents.findAll", query = "SELECT t FROM TaskComponents t"),
    @NamedQuery(name = "TaskComponents.findByIdTaskcomponent", query = "SELECT t FROM TaskComponents t WHERE t.idTaskcomponent = :idTaskcomponent")})
public class TaskComponents implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_taskComponents", sequenceName = "SEQ_TASKCOMPONENTS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_taskcomponents")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TASKCOMPONENT")
    private Long idTaskcomponent;
    @JoinColumn(name = "ID_TASK", referencedColumnName = "ID_TASK")
    @ManyToOne(optional = false)
    private Tasks idTask;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUser;

    public TaskComponents() {
    }

    public TaskComponents(Long idTaskcomponent) {
        this.idTaskcomponent = idTaskcomponent;
    }

    public Long getIdTaskcomponent() {
        return idTaskcomponent;
    }

    public void setIdTaskcomponent(Long idTaskcomponent) {
        this.idTaskcomponent = idTaskcomponent;
    }

    public Tasks getIdTask() {
        return idTask;
    }

    public void setIdTask(Tasks idTask) {
        this.idTask = idTask;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaskcomponent != null ? idTaskcomponent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskComponents)) {
            return false;
        }
        TaskComponents other = (TaskComponents) object;
        if ((this.idTaskcomponent == null && other.idTaskcomponent != null) || (this.idTaskcomponent != null && !this.idTaskcomponent.equals(other.idTaskcomponent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManageMe.ejb.TaskComponents[ idTaskcomponent=" + idTaskcomponent + " ]";
    }
    
}
