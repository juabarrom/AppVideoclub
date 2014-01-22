package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class ProduccionPK implements Serializable {
    
    private Long idAdg;
    
    private Long idExistencias;

    public ProduccionPK() {
    }

    public ProduccionPK(Long idAdg, Long idExistencias) {
        this.idAdg = idAdg;
        this.idExistencias = idExistencias;
    }

    public boolean equals(Object other) {
        if (other instanceof ProduccionPK) {
            final ProduccionPK otherProduccionPK = (ProduccionPK)other;
            final boolean areEqual = true;
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getIdAdg() {
        return idAdg;
    }

    public void setIdAdg(Long idAdg) {
        this.idAdg = idAdg;
    }

    public Long getIdExistencias() {
        return idExistencias;
    }

    public void setIdExistencias(Long idExistencias) {
        this.idExistencias = idExistencias;
    }
}
