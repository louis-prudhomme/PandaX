package fr.efrei.pandax.model.business;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u " +
            "WHERE u.id = :id"),
    @NamedQuery(name = "User.checkCred", query = "SELECT u FROM User u " +
            "WHERE u.pseudo = :pseudo AND u.pwd = :pwd")})
public class User implements Serializable, IDTO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "pseudo")
    private String pseudo;

    @Basic(optional = false)
    @NotNull
    @Expose(serialize = false, deserialize = true)
    @Size(min = 1, max = 128)
    @Column(name = "pwd")
    private String pwd;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "first_name")
    private String firstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "last_name")
    private String lastName;

    @Basic(optional = false)
    @NotNull
    @Expose(serialize = false, deserialize = true)
    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private Collection<Possession> possessionCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Comment> commentCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Media> mediaCollection;

    public User() { }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String pseudo, String pwd, String firstName, String lastName, boolean isAdmin) {
        this.id = id;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @XmlTransient
    public Collection<Possession> getPossessionCollection() {
        return possessionCollection;
    }

    public void setPossessionCollection(Collection<Possession> possessionCollection) {
        this.possessionCollection = possessionCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Media> getMediaCollection() {
        return mediaCollection;
    }

    public void setMediaCollection(Collection<Media> mediaCollection) {
        this.mediaCollection = mediaCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.User[ id=" + id + " ]";
    }
    
}
