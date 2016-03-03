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
@Table(name = "PROJECT_COMPONENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectComponents.findAll", query = "SELECT p FROM ProjectComponents p"),
    @NamedQuery(name = "ProjectComponents.findByIdProjectcomponent", query = "SELECT p FROM ProjectComponents p WHERE p.idProjectcomponent = :idProjectcomponent")})
public class ProjectComponents implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_projectComponents", sequenceName = "SEQ_PROJECTCOMPONENTS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_projectComponents")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROJECTCOMPONENT")
    private Long idProjectcomponent;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Projects idProject;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUser;

    public ProjectComponents() {
    }

    public ProjectComponents(Long idProjectcomponent) {
        this.idProjectcomponent = idProjectcomponent;
    }

    public Long getIdProjectcomponent() {
        return idProjectcomponent;
    }

    public void setIdProjectcomponent(Long idProjectcomponent) {
        this.idProjectcomponent = idProjectcomponent;
    }

    public Projects getIdProject() {
        return idProject;
    }

    public void setIdProject(Projects idProject) {
        this.idProject = idProject;
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
        hash += (idProjectcomponent != null ? idProjectcomponent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectComponents)) {
            return false;
        }
        ProjectComponents other = (ProjectComponents) object;
        if ((this.idProjectcomponent == null && other.idProjectcomponent != null) || (this.idProjectcomponent != null && !this.idProjectcomponent.equals(other.idProjectcomponent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManageMe.ejb.ProjectComponents[ idProjectcomponent=" + idProjectcomponent + " ]";
    }
    
}
