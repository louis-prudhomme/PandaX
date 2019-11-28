package fr.efrei.pandax.model.business;

import jdk.jshell.spi.ExecutionControl;

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

@Entity
@Table(name = "possession")
@NamedQueries({
    @NamedQuery(name = "Possession.findAll", query = "SELECT p FROM Possession p"),
    @NamedQuery(name = "Possession.findByUser", query = "SELECT p FROM Possession p " +
            "WHERE p.possessionPK.user = :user"),
    @NamedQuery(name = "Possession.findByMedia", query = "SELECT p FROM Possession p " +
            "WHERE p.possessionPK.media = :media")})
public class Possession implements Serializable, IDTO {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PossessionPK possessionPK;

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

    public Possession() {}

    public Possession(PossessionPK possessionPK) {
        this.possessionPK = possessionPK;
    }

    public Possession(PossessionPK possessionPK, Date dateAcquired) {
        this.possessionPK = possessionPK;
        this.dateAcquired = dateAcquired;
    }

    public Possession(int user, int media) {
        this.possessionPK = new PossessionPK(user, media);
    }

    public PossessionPK getPossessionPK() {
        return possessionPK;
    }

    public void setPossessionPK(PossessionPK possessionPK) {
        this.possessionPK = possessionPK;
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
    public Integer getId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (possessionPK != null ? possessionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Possession)) {
            return false;
        }
        Possession other = (Possession) object;
        if ((this.possessionPK == null && other.possessionPK != null) || (this.possessionPK != null && !this.possessionPK.equals(other.possessionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.Possession[ possessionPK=" + possessionPK + " ]";
    }
    
}