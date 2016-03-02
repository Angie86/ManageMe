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
@Table(name = "HISTORIC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historic.findAll", query = "SELECT h FROM Historic h"),
    @NamedQuery(name = "Historic.findByIdHistoric", query = "SELECT h FROM Historic h WHERE h.idHistoric = :idHistoric")})
public class Historic implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_historic", sequenceName = "SEQ_HISTORIC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historic")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HISTORIC")
    private Long idHistoric;
    @JoinColumn(name = "ID_DOCUMENT", referencedColumnName = "ID_DOCUMENT")
    @ManyToOne(optional = false)
    private Document idDocument;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Projects idProject;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUser;

    public Historic() {
    }

    public Historic(Long idHistoric) {
	this.idHistoric = idHistoric;
    }

    public Long getIdHistoric() {
	return idHistoric;
    }

    public void setIdHistoric(Long idHistoric) {
	this.idHistoric = idHistoric;
    }

    public Document getIdDocument() {
	return idDocument;
    }

    public void setIdDocument(Document idDocument) {
	this.idDocument = idDocument;
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
	hash += (idHistoric != null ? idHistoric.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Historic)) {
	    return false;
	}
	Historic other = (Historic) object;
	if ((this.idHistoric == null && other.idHistoric != null) || (this.idHistoric != null && !this.idHistoric.equals(other.idHistoric))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ManageMe.entity.Historic[ idHistoric=" + idHistoric + " ]";
    }
    
}
