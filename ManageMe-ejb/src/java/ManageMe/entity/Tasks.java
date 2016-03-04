/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author inftel06
 */
@Entity
@Table(name = "TASKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),
    @NamedQuery(name = "Tasks.findByIdTask", query = "SELECT t FROM Tasks t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Tasks.findByDescription", query = "SELECT t FROM Tasks t WHERE t.description = :description"),
    @NamedQuery(name = "Tasks.findByDateInit", query = "SELECT t FROM Tasks t WHERE t.dateInit = :dateInit"),
    @NamedQuery(name = "Tasks.findByNameTask", query = "SELECT t FROM Tasks t WHERE t.nameTask = :nameTask"),
    @NamedQuery(name = "Tasks.findByDuration", query = "SELECT t FROM Tasks t WHERE t.duration = :duration")})
public class Tasks implements Serializable {
    @Size(max = 50)
    @Column(name = "STATE")
    private String state;
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_task", sequenceName = "SEQ_TASK", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TASK")
    private Long idTask;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_INIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME_TASK")
    private String nameTask;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DURATION")
    private long duration;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Projects idProject;
    @JoinColumn(name = "ID_USERCREATOR", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUsercreator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTask")
    private Collection<TaskComponents> taskComponentsCollection;

    public Tasks() {
    }

    public Tasks(Long idTask) {
        this.idTask = idTask;
    }

    public Tasks(Long idTask, Date dateInit, String nameTask, long duration) {
        this.idTask = idTask;
        this.dateInit = dateInit;
        this.nameTask = nameTask;
        this.duration = duration;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Projects getIdProject() {
        return idProject;
    }

    public void setIdProject(Projects idProject) {
        this.idProject = idProject;
    }

    public Users getIdUsercreator() {
        return idUsercreator;
    }

    public void setIdUsercreator(Users idUsercreator) {
        this.idUsercreator = idUsercreator;
    }

    @XmlTransient
    public Collection<TaskComponents> getTaskComponentsCollection() {
        return taskComponentsCollection;
    }

    public void setTaskComponentsCollection(Collection<TaskComponents> taskComponentsCollection) {
        this.taskComponentsCollection = taskComponentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManageMe.ejb.Tasks[ idTask=" + idTask + " ]";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
