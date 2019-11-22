/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.efrei.pandax.model.business;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author melaniemarques
 */
@Embeddable
public class PossesionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedia")
    private int idMedia;

    public PossesionPK() {
    }

    public PossesionPK(int idUser, int idMedia) {
        this.idUser = idUser;
        this.idMedia = idMedia;
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
        hash += (int) idUser;
        hash += (int) idMedia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PossesionPK)) {
            return false;
        }
        PossesionPK other = (PossesionPK) object;
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
        return "fr.efrei.pandax.model.business.PossesionPK[ idUser=" + idUser + ", idMedia=" + idMedia + " ]";
    }
    
}
