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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author melaniemarques
 */
@Entity
@Table(name = "possesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Possesion.findAll", query = "SELECT p FROM Possesion p"),
    @NamedQuery(name = "Possesion.findByIdUser", query = "SELECT p FROM Possesion p WHERE p.possesionPK.idUser = :idUser"),
    @NamedQuery(name = "Possesion.findByIdMedia", query = "SELECT p FROM Possesion p WHERE p.possesionPK.idMedia = :idMedia"),
    @NamedQuery(name = "Possesion.findByDateAcquired", query = "SELECT p FROM Possesion p WHERE p.dateAcquired = :dateAcquired")})
public class Possesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PossesionPK possesionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAcquired")
    @Temporal(TemporalType.DATE)
    private Date dateAcquired;
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idMedia", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Media media;

    public Possesion() {
    }

    public Possesion(PossesionPK possesionPK) {
        this.possesionPK = possesionPK;
    }

    public Possesion(PossesionPK possesionPK, Date dateAcquired) {
        this.possesionPK = possesionPK;
        this.dateAcquired = dateAcquired;
    }

    public Possesion(int idUser, int idMedia) {
        this.possesionPK = new PossesionPK(idUser, idMedia);
    }

    public PossesionPK getPossesionPK() {
        return possesionPK;
    }

    public void setPossesionPK(PossesionPK possesionPK) {
        this.possesionPK = possesionPK;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
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
        hash += (possesionPK != null ? possesionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Possesion)) {
            return false;
        }
        Possesion other = (Possesion) object;
        if ((this.possesionPK == null && other.possesionPK != null) || (this.possesionPK != null && !this.possesionPK.equals(other.possesionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.Possesion[ possesionPK=" + possesionPK + " ]";
    }
    
}
