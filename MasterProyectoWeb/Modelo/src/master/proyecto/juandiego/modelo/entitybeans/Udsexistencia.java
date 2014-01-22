package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Udsexistencia.findAll", query = "select o from Udsexistencia o") })
@Table(name = "JBRF_UDSEXISTENCIA")
public class Udsexistencia implements Serializable {
    
    @Id
    @Column(name = "ID_UDSEXISTENCIA", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idUdsexistencia;
    
    @Column(name = "UDS_BLURAY")
    private Integer udsBluray;
    
    @Column(name = "UDS_DVD")
    private Integer udsDvd;
    
    @Column(name = "UDS_JUEGO")
    private Integer udsJuego;
    
    @OneToOne
    @JoinColumn(name = "ID_EXISTENCIA")
    private Existencia Existencia;

    public Udsexistencia() {
    }

    public Udsexistencia(Integer udsBluray,Integer udsDvd,Integer udsJuego) {
        this.udsBluray = udsBluray;
        this.udsDvd = udsDvd;
        this.udsJuego = udsJuego;
    }


    public Long getIdUdsexistencia() {
        return idUdsexistencia;
    }

    public void setIdUdsexistencia(Long idUdsexistencia) {
        this.idUdsexistencia = idUdsexistencia;
    }

    public Integer getUdsBluray() {
        return udsBluray;
    }

    public void setUdsBluray(Integer udsBluray) {
        this.udsBluray = udsBluray;
    }

    public Integer getUdsDvd() {
        return udsDvd;
    }

    public void setUdsDvd(Integer udsDvd) {
        this.udsDvd = udsDvd;
    }

    public Integer getUdsJuego() {
        return udsJuego;
    }

    public void setUdsJuego(Integer udsJuego) {
        this.udsJuego = udsJuego;
    }

    public Existencia getExistencia() {
        return Existencia;
    }

    public void setExistencia(Existencia Existencia) {
        this.Existencia = Existencia;
    }
}
