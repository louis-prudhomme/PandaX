/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.efrei.pandax.model.business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author melaniemarques
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByIdUser", query = "SELECT c FROM Comment c WHERE c.commentPK.idUser = :idUser"),
    @NamedQuery(name = "Comment.findByIdMedia", query = "SELECT c FROM Comment c WHERE c.commentPK.idMedia = :idMedia"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.commentPK.id = :id"),
    @NamedQuery(name = "Comment.findByDateMade", query = "SELECT c FROM Comment c WHERE c.dateMade = :dateMade")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommentPK commentPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateMade")
    @Temporal(TemporalType.DATE)
    private Date dateMade;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idMedia", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Media media;

    public Comment() {
    }

    public Comment(CommentPK commentPK) {
        this.commentPK = commentPK;
    }

    public Comment(CommentPK commentPK, Date dateMade, String content) {
        this.commentPK = commentPK;
        this.dateMade = dateMade;
        this.content = content;
    }

    public Comment(int idUser, int idMedia, int id) {
        this.commentPK = new CommentPK(idUser, idMedia, id);
    }

    public CommentPK getCommentPK() {
        return commentPK;
    }

    public void setCommentPK(CommentPK commentPK) {
        this.commentPK = commentPK;
    }

    public Date getDateMade() {
        return dateMade;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentPK != null ? commentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentPK == null && other.commentPK != null) || (this.commentPK != null && !this.commentPK.equals(other.commentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.Comment[ commentPK=" + commentPK + " ]";
    }
    
}
