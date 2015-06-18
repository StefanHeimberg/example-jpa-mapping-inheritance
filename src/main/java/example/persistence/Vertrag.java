package example.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vertrag implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VertragArt art;

    public Vertrag(final VertragArt art) {
        this.art = art;
    }

    public Long getId() {
        return id;
    }
    
    public VertragArt getArt() {
        return art;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("id=").append(id).append(",")
                .append("art=").append(art)
                .append("]")
                .toString();
    }
    
}
