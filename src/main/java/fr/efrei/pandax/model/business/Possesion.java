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
    @NamedQuery(name = "Possesion.findByUser", query = "SELECT p FROM Possesion p WHERE p.possesionPK.user = :user"),
    @NamedQuery(name = "Possesion.findByMedia", query = "SELECT p FROM Possesion p WHERE p.possesionPK.media = :media"),
    @NamedQuery(name = "Possesion.findByDateAcquired", query = "SELECT p FROM Possesion p WHERE p.dateAcquired = :dateAcquired")})
public class Possesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PossesionPK possesionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_acquired")
    @Temporal(TemporalType.DATE)
    private Date dateAcquired;
    @JoinColumn(name = "user", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;
    @JoinColumn(name = "media", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Media media1;

    public Possesion() {
    }

    public Possesion(PossesionPK possesionPK) {
        this.possesionPK = possesionPK;
    }

    public Possesion(PossesionPK possesionPK, Date dateAcquired) {
        this.possesionPK = possesionPK;
        this.dateAcquired = dateAcquired;
    }

    public Possesion(int user, int media) {
        this.possesionPK = new PossesionPK(user, media);
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

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public Media getMedia1() {
        return media1;
    }

    public void setMedia1(Media media1) {
        this.media1 = media1;
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
