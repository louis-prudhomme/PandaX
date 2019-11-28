package fr.efrei.pandax.model.business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comment")
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT m FROM Media m " +
            "WHERE m.id = :id"),
    @NamedQuery(name = "Comment.findByUser", query = "SELECT c FROM Comment c " +
            "WHERE c.user.id = :user"),
    @NamedQuery(name = "Comment.findByMedia", query = "SELECT c FROM Comment c " +
            "WHERE c.media.id = :media"),
    @NamedQuery(name = "Comment.findByMediaAndUser", query = "SELECT c FROM Comment c " +
            "WHERE c.media.id = :media AND c.user.id = :user"),
    @NamedQuery(name = "Comment.findByPk", query = "SELECT c FROM Comment c " +
            "WHERE c.media.id = :media AND c.user.id = :user AND c.id = :id")})
public class Comment implements Serializable, IDTO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_made")
    @Temporal(TemporalType.DATE)
    private Date dateMade = new Date();

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;

    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "media", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Media media;

    public Comment() {}

    public Comment(Date dateMade, String content) {
        this.dateMade = dateMade;
        this.content = content;
    }

    public Comment(Integer id, Date dateMade, String content) {
        this.id = id;
        this.dateMade = dateMade;
        this.content = content;
    }

    public Comment(Integer id, Date dateMade, String content, User user, Media media) {
        this.id = id;
        this.dateMade = dateMade;
        this.content = content;
        this.user = user;
        this.media = media;
    }

    public Comment(Date dateMade, String content, User user, Media media) {
        this.dateMade = dateMade;
        this.content = content;
        this.user = user;
        this.media = media;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash += id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.efrei.pandax.model.business.Comment[ id=" + id + " ]";
    }
    
}
