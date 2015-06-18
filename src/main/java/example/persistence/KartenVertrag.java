package example.persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
@Entity
public class KartenVertrag extends Vertrag {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private Integer karteId;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer kontoNr;

    public KartenVertrag() {
        super(VertragArt.KARTEVERTRAG);
    }

    public Integer getKarteId() {
        return karteId;
    }

    public void setKarteId(Integer karteId) {
        this.karteId = karteId;
    }

    public Integer getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(Integer kontoNr) {
        this.kontoNr = kontoNr;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("id=").append(getId()).append(",")
                .append("art=").append(getArt()).append(",")
                .append("karteId=").append(karteId).append(",")
                .append("kontoNr=").append(kontoNr)
                .append("]")
                .toString();
    }

}
