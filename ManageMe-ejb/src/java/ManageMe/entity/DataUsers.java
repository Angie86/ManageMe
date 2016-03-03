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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author noussairelharrak
 */
@Entity
@Table(name = "DATA_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataUsers.findAll", query = "SELECT d FROM DataUsers d"),
    @NamedQuery(name = "DataUsers.findByIdDatauser", query = "SELECT d FROM DataUsers d WHERE d.idDatauser = :idDatauser"),
    @NamedQuery(name = "DataUsers.findByNameUser", query = "SELECT d FROM DataUsers d WHERE d.nameUser = :nameUser"),
    @NamedQuery(name = "DataUsers.findByTitulationUser", query = "SELECT d FROM DataUsers d WHERE d.titulationUser = :titulationUser")})
public class DataUsers implements Serializable {
    @Size(max = 200)
    @Column(name = "PHOTO_USER")
    private String photoUser;

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_dataUsers", sequenceName = "SEQ_DATAUSERS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dataUsers")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DATAUSER")
    private Long idDatauser;
    @Size(max = 50)
    @Column(name = "NAME_USER")
    private String nameUser;
    @Size(max = 50)
    @Column(name = "TITULATION_USER")
    private String titulationUser;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Users idUser;

    public DataUsers() {
    }

    public DataUsers(Long idDatauser) {
	this.idDatauser = idDatauser;
    }

    public Long getIdDatauser() {
	return idDatauser;
    }

    public void setIdDatauser(Long idDatauser) {
	this.idDatauser = idDatauser;
    }

    public String getNameUser() {
	return nameUser;
    }

    public void setNameUser(String nameUser) {
	this.nameUser = nameUser;
    }

    public String getPhotoUser() {
	return photoUser;
    }

    public void setPhotoUser(String photoUser) {
	this.photoUser = photoUser;
    }

    public String getTitulationUser() {
	return titulationUser;
    }

    public void setTitulationUser(String titulationUser) {
	this.titulationUser = titulationUser;
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
	hash += (idDatauser != null ? idDatauser.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof DataUsers)) {
	    return false;
	}
	DataUsers other = (DataUsers) object;
	if ((this.idDatauser == null && other.idDatauser != null) || (this.idDatauser != null && !this.idDatauser.equals(other.idDatauser))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ManageMe.entity.DataUsers[ idDatauser=" + idDatauser + " ]";
    }


    
}
