/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.efrei.pandax.model.business;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author melaniemarques
 */
@Entity
@Table(name = "comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.commentsPK.id = :id"),
    @NamedQuery(name = "Comments.findByIdUser", query = "SELECT c FROM Comments c WHERE c.commentsPK.idUser = :idUser"),
    @NamedQuery(name = "Comments.findByIdMedia", query = "SELECT c FROM Comments c WHERE c.commentsPK.idMedia = :idMedia")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommentsPK commentsPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "comment")
    private String comment;

    public Comments() {
    }

    public Comments(CommentsPK commentsPK) {
        this.commentsPK = commentsPK;
    }

    public Comments(CommentsPK commentsPK, String comment) {
        this.commentsPK = commentsPK;
        this.comment = comment;
    }

    public Comments(int id, int idUser, int idMedia) {
        this.commentsPK = new CommentsPK(id, idUser, idMedia);
    }

    public CommentsPK getCommentsPK() {
        return commentsPK;
    }

    public void setCommentsPK(CommentsPK commentsPK) {
        this.commentsPK = commentsPK;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentsPK != null ? commentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.commentsPK == null && other.commentsPK != null) || (this.commentsPK != null && !this.commentsPK.equals(other.commentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.Comments[ commentsPK=" + commentsPK + " ]";
    }
    
}
