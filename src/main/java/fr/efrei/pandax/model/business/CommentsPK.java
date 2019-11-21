package fr.efrei.pandax.model.business;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CommentsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_media")
    private int idMedia;

    public CommentsPK() {
    }

    public CommentsPK(int id, int idUser, int idMedia) {
        this.id = id;
        this.idUser = idUser;
        this.idMedia = idMedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idUser;
        hash += (int) idMedia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentsPK)) {
            return false;
        }
        CommentsPK other = (CommentsPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idMedia != other.idMedia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.CommentsPK[ id=" + id + ", idUser=" + idUser + ", idMedia=" + idMedia + " ]";
    }
    
}
