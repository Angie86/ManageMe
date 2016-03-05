/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author inftel06
 */
@Entity
@Table(name = "CHAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c"),
    @NamedQuery(name = "Chat.findByIdChat", query = "SELECT c FROM Chat c WHERE c.idChat = :idChat"),
    @NamedQuery(name = "Chat.findByDateModification", query = "SELECT c FROM Chat c WHERE c.dateModification = :dateModification")})
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq_chat", sequenceName = "SEQ_CHAT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chat")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CHAT")
    private Long idChat;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @Lob
    @Column(name = "CHAT_RECORD")
    private byte[] chatRecord;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Projects idProject;

    public Chat() {
    }

    public Chat(Long idChat) {
        this.idChat = idChat;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public byte[] getChatRecord() {
        return chatRecord;
    }

    public void setChatRecord(byte[] chatRecord) {
        this.chatRecord = chatRecord;
    }

    public Projects getIdProject() {
        return idProject;
    }

    public void setIdProject(Projects idProject) {
        this.idProject = idProject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChat != null ? idChat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.idChat == null && other.idChat != null) || (this.idChat != null && !this.idChat.equals(other.idChat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManageMe.ejb.Chat[ idChat=" + idChat + " ]";
    }
    
}
