package example.persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@Entity
public class PFMobileVertrag extends Vertrag {
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String handyNr;

    public String getHandyNr() {
        return handyNr;
    }

    public void setHandyNr(String handyNr) {
        this.handyNr = handyNr;
    }
    
    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("id=").append(getId()).append(",")
                .append("art=").append(getArt()).append(",")
                .append("handyNr=").append(handyNr)
                .append("]")
                .toString();
    }
    
}
