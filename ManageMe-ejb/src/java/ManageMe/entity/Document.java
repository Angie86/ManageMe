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
import javax.persistence.Lob;
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
@Table(name = "DOCUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByIdDocument", query = "SELECT d FROM Document d WHERE d.idDocument = :idDocument"),
    @NamedQuery(name = "Document.findByNameDocument", query = "SELECT d FROM Document d WHERE d.nameDocument = :nameDocument"),
    @NamedQuery(name = "Document.findByTypeDocument", query = "SELECT d FROM Document d WHERE d.typeDocument = :typeDocument"),
    @NamedQuery(name = "Document.findByDateDocument", query = "SELECT d FROM Document d WHERE d.dateDocument = :dateDocument")})
public class Document implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PATH_DOCUMENT")
    private String pathDocument;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 200)
//    @Column(name = "DOCUMENT")
//    private String document;
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_document", sequenceName = "SEQ_DOCUMENT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCUMENT")
    private Long idDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME_DOCUMENT")
    private String nameDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TYPE_DOCUMENT")
    private String typeDocument;
    @Column(name = "DATE_DOCUMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDocument;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocument")
    private Collection<Historic> historicCollection;

    public Document() {
    }

    public Document(Long idDocument) {
        this.idDocument = idDocument;
    }

    public Document(Long idDocument, String nameDocument, String typeDocument) {
        this.idDocument = idDocument;
        this.nameDocument = nameDocument;
        this.typeDocument = typeDocument;
    }

    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }


    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Date getDateDocument() {
        return dateDocument;
    }

    public void setDateDocument(Date dateDocument) {
        this.dateDocument = dateDocument;
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
        hash += (idDocument != null ? idDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.idDocument == null && other.idDocument != null) || (this.idDocument != null && !this.idDocument.equals(other.idDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManageMe.ejb.Document[ idDocument=" + idDocument + " ]";
    }

//    public String getDocument() {
//        return document;
//    }
//
//    public void setDocument(String document) {
//        this.document = document;
//    }

    public String getPathDocument() {
        return pathDocument;
    }

    public void setPathDocument(String pathDocument) {
        this.pathDocument = pathDocument;
    }
    
}
