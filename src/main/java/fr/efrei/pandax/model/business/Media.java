/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.efrei.pandax.model.business;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author melaniemarques
 */
@Entity
@Table(name = "media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m"),
    @NamedQuery(name = "Media.findById", query = "SELECT m FROM Media m WHERE m.id = :id"),
    @NamedQuery(name = "Media.findByTitle", query = "SELECT m FROM Media m WHERE m.title = :title"),
    @NamedQuery(name = "Media.findByPublished", query = "SELECT m FROM Media m WHERE m.published = :published"),
    @NamedQuery(name = "Media.findByImageUrl", query = "SELECT m FROM Media m WHERE m.imageUrl = :imageUrl"),
    @NamedQuery(name = "Media.findByCity", query = "SELECT m FROM Media m WHERE m.city = :city")})
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "published")
    @Temporal(TemporalType.DATE)
    private Date published;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descript")
    private String descript;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "imageUrl")
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private Collection<Possesion> possesionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "idPublisher", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Publisher idPublisher;
    @JoinColumn(name = "idMediaType", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MediaType idMediaType;

    public Media() {
    }

    public Media(Integer id) {
        this.id = id;
    }

    public Media(Integer id, String title, Date published, String descript, String imageUrl, String city) {
        this.id = id;
        this.title = title;
        this.published = published;
        this.descript = descript;
        this.imageUrl = imageUrl;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public Collection<Possesion> getPossesionCollection() {
        return possesionCollection;
    }

    public void setPossesionCollection(Collection<Possesion> possesionCollection) {
        this.possesionCollection = possesionCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Publisher getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Publisher idPublisher) {
        this.idPublisher = idPublisher;
    }

    public MediaType getIdMediaType() {
        return idMediaType;
    }

    public void setIdMediaType(MediaType idMediaType) {
        this.idMediaType = idMediaType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.Media[ id=" + id + " ]";
    }
    
}
